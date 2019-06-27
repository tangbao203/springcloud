package com.tangdabao.springcloud.zuul.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * oauth配置
 */
@Component
@ConfigurationProperties(prefix = "chess-oauth")
public class OauthConfig {
    /**
     * 启用oauth服务
     */
    private boolean enabled;
    /**
     * oauth服务地址
     */
    private String check_token_url;
    /**
     * 检查授权列表
     */
    private List<String> authenticated_list;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCheck_token_url() {
        return check_token_url;
    }

    public void setCheck_token_url(String check_token_url) {
        this.check_token_url = check_token_url;
    }

    public List<String> getAuthenticated_list() {
        return authenticated_list;
    }

    public void setAuthenticated_list(List<String> authenticated_list) {
        this.authenticated_list = authenticated_list;
    }
}
