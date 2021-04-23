package com.chenbro.sms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName SmsProperties
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/24 15:42
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "barcode.sms")
public class SmsProperties {

    String accessId;

    String accessKeySecret;

    String signName;

    String verifyCodeTemplate;


    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getVerifyCodeTemplate() {
        return verifyCodeTemplate;
    }

    public void setVerifyCodeTemplate(String verifyCodeTemplate) {
        this.verifyCodeTemplate = verifyCodeTemplate;
    }
}
