package com.chenbro.gateway.filer;

import com.chenbro.common.utils.CookieUtils;
import com.chenbro.common.utils.JwtUtils;
import com.chenbro.gateway.config.FilterProperties;
import com.chenbro.gateway.config.JwtProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName LoginFilter
 * @Description TODO
 * @Author z8777
 * @Date 2021/1/6 16:26
 * @Version 1.0
 **/
@Component
@EnableConfigurationProperties({JwtProperties.class, FilterProperties.class})
public class LoginFilter extends ZuulFilter {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private FilterProperties filterProperties;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        // 获取白名单
        List<String> allowPaths = filterProperties.getAllowPaths();
        // 获取当前请求路径
        // 初始化运行上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取Request对象
        HttpServletRequest request = context.getRequest();
        // 获取请求路径
        String url = request.getRequestURL().toString();
        for(String allowUrl : allowPaths){
            if (StringUtils.contains(url, allowUrl)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        // 初始化运行上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 获取Request对象
        HttpServletRequest request = context.getRequest();

        String token = CookieUtils.getCookieValue(request, jwtProperties.getCookieName());

/*        if (StringUtils.isBlank(token)) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }*/
        try {
            JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
        } catch (Exception e) {
            e.printStackTrace();
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
