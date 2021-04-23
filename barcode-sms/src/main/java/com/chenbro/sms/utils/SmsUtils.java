package com.chenbro.sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.chenbro.sms.config.SmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @ClassName SmsUtils
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/24 15:52
 * @Version 1.0
 **/
@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsUtils {

    private SmsProperties prop;

    // 产品名称:云通信短信API产品,开发者无需替换
    final String product = "Dysmsapi";
    // 产品域名,开发者无需替换
    final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    final String accessKeyId = "yourAccessKeyId";//你的accessKeyId,参考本文档步骤2
    final String accessKeySecret = "yourAccessKeySecret";//你的accessKeySecret，参考本文档步骤2

    private static final Logger logger = LoggerFactory.getLogger(SmsUtils.class);

    public CommonResponse sendSms(String phone, String code, String signName, String template) throws ClientException {

        //初始化ascClient,暂时不支持多region（请勿修改）
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        //组装请求对象
        CommonRequest request = new CommonRequest();
        //使用post提交
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion("2017-05-25");
        // 使用类名
        request.setSysAction("SmsUtils");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        //必填:待发送手机号
        request.putQueryParameter("PhoneNumbers", phone);
        //必填:短信签名-可在短信控制台中找到
        request.putQueryParameter("SignName", signName);
        //必填:短信模板-可在短信控制台中找到
        request.putQueryParameter("TemplateCode", template);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        //参考：request.setTemplateParam("","{\"code\":\"1111\"}")
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.putQueryParameter("OutId", "10");
        //hint 此处可能会抛出异常，注意catch
        CommonResponse response = client.getCommonResponse(request);
        logger.info("发送短信消息返回信息：{}", response.getData());
        return response;
    }


}
