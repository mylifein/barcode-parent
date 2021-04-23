package com.chenbro.user.controller;

import com.chenbro.user.pojo.User;
import com.chenbro.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/24 11:05
 * @Version 1.0
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /* *
     * @Description //TODO 校验数据是否可用
     * @Date 2020/12/24 11:56
     * @return
     **/
    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkUser(@PathVariable("data") String data, @PathVariable("type") Integer type) {
        Boolean result = userService.checkUser(data, type);
        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("code")
    public ResponseEntity<Void> sendVerifyCode(@RequestParam("phone") String phone) {
        userService.sendVerifyCode(phone);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid User user, @RequestParam("code") String code) {
        userService.register(user, code);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("login")
    public ResponseEntity<User> loginUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.queryUser(username, password);
        if (ObjectUtils.isEmpty(user)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

}
