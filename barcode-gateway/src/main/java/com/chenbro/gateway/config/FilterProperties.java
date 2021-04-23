package com.chenbro.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName FilterProperties
 * @Description TODO
 * @Author z8777
 * @Date 2021/1/6 20:12
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "barcode.filter")
public class FilterProperties {

    private List<String> allowPaths;

    public List<String> getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }
}
