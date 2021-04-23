package com.chenbro.auth.client;

import com.chenbro.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName UserClient
 * @Description TODO
 * @Author z8777
 * @Date 2021/1/6 14:44
 * @Version 1.0
 **/
@FeignClient("user-service")
public interface UserClient extends UserApi {
}
