package com.chenbro.user.api;

import com.chenbro.user.pojo.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName api
 * @Description TODO
 * @Author z8777
 * @Date 2021/1/6 14:40
 * @Version 1.0
 **/
public interface UserApi {

    @PostMapping("login")
    public User loginUser(@RequestParam("username") String username, @RequestParam("password") String password);
}
