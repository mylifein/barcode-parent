package com.chenbro.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.chenbro.sms.config.SmsProperties;
import com.chenbro.sms.utils.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.Map;

/**
 * @ClassName SmsListener
 * @Description TODO 监听 消息队列并发送消息
 * @Author z8777
 * @Date 2020/12/25 15:14
 * @Version 1.0
 **/
@Component
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsProperties smsProperties;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "BARCODE.SMS.QUEUE", durable = "true"),
            exchange = @Exchange(value = "BARCODE.SMS.EXCHANGE", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"VERIFYCODE.SMS"}
    ))
    public void sendSms(Map<String, String> msg) throws ClientException {
        if (CollectionUtils.isEmpty(msg)) {
            return;
        }
        String phone = msg.get("phone");
        String code = msg.get("code");
        if (StringUtils.isNoneBlank(phone) && StringUtils.isNoneBlank(code)) {
//            smsUtils.sendSms(phone, code, smsProperties.getSignName(), smsProperties.getVerifyCodeTemplate());
        }
    }
}
