package com.chenbro.gateway.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName LeyouCorsConfiguration
 * @Description TODO
 * @Author z8777
 * @Date 2020/12/16 16:57
 * @Version 1.0
 **/
@Configuration
public class LeyouCorsConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        // 初始化cors配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许跨域的域名，如果要携带cookie,不能写*。  *: 代表所有域名都可以跨域访问
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.setAllowCredentials(true);        // 允许携带cookie
        corsConfiguration.addAllowedMethod("*");            // 代表所有的请求方法： GET POST PUT DELETE
        corsConfiguration.addAllowedHeader("*");            // 允许携带任何头信息

        // 初始化cors配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        // 返回corFilter 实例， 参数： cors配置源对象
        return new CorsFilter(configurationSource);
    }
}
