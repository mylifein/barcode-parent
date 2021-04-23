package com.chenbro.auth.controller;

import com.chenbro.auth.config.JwtProperties;
import com.chenbro.auth.service.AuthService;
import com.chenbro.common.pojo.UserInfo;
import com.chenbro.common.utils.CookieUtils;
import com.chenbro.common.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author z8777
 * @Date 2021/1/6 14:23
 * @Version 1.0
 **/
@EnableConfigurationProperties(JwtProperties.class)
@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("authorize")
    public ResponseEntity<Void> authorize(@RequestParam("username") String usernmae,
                                          @RequestParam("password") String password,
                                          HttpServletRequest request,
                                          HttpServletResponse response) {
        String token = authService.authorize(usernmae, password);
        if (StringUtils.isBlank(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        CookieUtils.setCookie(request, response, jwtProperties.getCookieName(), token, jwtProperties.getExpire() * 60);
        return ResponseEntity.ok(null);
    }

    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(@CookieValue("CB_TOKEN") String token,
                                           HttpServletRequest request,
                                           HttpServletResponse response) {
        try {
            // 通过Jwt 工具类使用公钥解析jwt
            UserInfo user = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());

            if (ObjectUtils.isEmpty(user)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // 刷新jwt中的有效时间
            token = JwtUtils.generateToken(user, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            // 刷新cookie中的有效时间
            CookieUtils.setCookie(request, response, jwtProperties.getCookieName(), token, jwtProperties.getExpire() * 60);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
