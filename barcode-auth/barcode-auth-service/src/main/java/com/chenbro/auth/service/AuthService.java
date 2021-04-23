package com.chenbro.auth.service;

import com.chenbro.auth.client.UserClient;
import com.chenbro.auth.config.JwtProperties;
import com.chenbro.common.pojo.UserInfo;
import com.chenbro.common.utils.JwtUtils;
import com.chenbro.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName AuthService
 * @Description TODO
 * @Author z8777
 * @Date 2021/1/6 14:24
 * @Version 1.0
 **/
@Service
public class AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties jwtProperties;

    public String authorize(String usernmae, String password) {
        // 1.根据用户名和密码查询
        User user = userClient.loginUser(usernmae, password);
        // 2.判断用户是否为空
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }

        try {
            // 3.若不为空，JwtUtils生成jwt类型的token
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            return JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
