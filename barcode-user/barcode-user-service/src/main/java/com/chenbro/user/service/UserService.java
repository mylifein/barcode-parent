package com.chenbro.user.service;

import com.chenbro.common.utils.NumberUtils;
import com.chenbro.user.mapper.UserMapper;
import com.chenbro.user.pojo.User;
import com.chenbro.user.utils.CodecUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/24 11:04
 * @Version 1.0
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify:";

    /* *
     * @Description //TODO 校验数据是否可用
     * @Date 2020/12/24 11:56
     * @return
     **/
    public Boolean checkUser(String data, Integer type) {
        User record = new User();
        switch (type) {
            case 1:         // 用户名
                record.setUsername(data);
                break;
            case 2:         // 手机
                record.setPhone(data);
                break;
            default:
                return null;
        }
        return userMapper.selectCount(record) == 0;
    }

    public void sendVerifyCode(String phone) {
        if (StringUtils.isBlank(phone)) {
            return;
        }
        // 生成验证码
        String code = NumberUtils.generateCode(6);

        // 发送消息到RabbitMQ
        Map<String, String> msg = new HashMap<>();
        msg.put("phone", phone);
        msg.put("code", code);
        amqpTemplate.convertAndSend("BARCODE.SMS.EXCHANGE", "VERIFYCODE.SMS", msg);
        // 把验证码保存到Redis中
        redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);
    }

    public void register(User user, String code) {
        // 查询Redis中的验证码
        String redisCode = redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());

        // 1.校验验证码
        if (!StringUtils.equals(code, redisCode)) {
            return;
        }
        // 2.生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        // 3.加盐加密
        user.setPassword(CodecUtils.md5Hex(user.getPassword(), salt));

        // 4.新增用户
        user.setId(null);
        user.setCreated(new Date());
        userMapper.insertSelective(user);
    }

    public User queryUser(String username, String password) {
        User record = new User();
        record.setUsername(username);
        User user = userMapper.selectOne(record);
        // 判断user是否为空
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        // 获取盐，对用户输入的密码加盐加密
        password = CodecUtils.md5Hex(password, user.getSalt());
        // 和数据库中的密码比较
        if (StringUtils.equals(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
