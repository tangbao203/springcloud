package com.tangdabao.springcloud.zuul.controller;

import com.tangdabao.springcloud.zuul.utils.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/test")
@RestController
public class TestController {

    @RequestMapping("/token1")
    public String token1(String username,String password,String clientId,String secret){
        try {
            String requestUrl = "http://localhost:7990/oauth/token";
            return getAccessTokent(requestUrl, username, password, clientId, secret,"read");
        }catch (Exception e){
            e.printStackTrace();
        }

        return "error";
    }

    @RequestMapping("/token2")
    public String token(String clientId,String secret){
        try {
            String requestUrl = "http://localhost:7990/oauth/token";
            return getAccessTokent(requestUrl, clientId, secret, clientId, secret,"read");
        }catch (Exception e){
            e.printStackTrace();
        }

        return "error";
    }

    /**
     *
     * 使用password 方式获取token
     * 例如：curl -d 'grant_type=password&username=user&password=123456&client_id=10000&client_secret=123456789&scope=read' http://localhost:7990/oauth/token
     *   {"access_token":"f586f6d9-4c02-4bed-85ae-87e09fcd170d","token_type":"bearer","refresh_token":"7d98e4a2-f43f-4013-96fa-1d1d4e4e8ea4","expires_in":2591999,"scope":"read"}
     * @param oauthTokenUrl
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     * @param scope
     * @return String result json
     * @throws IOException
     */
    public static String getAccessTokent(String oauthTokenUrl,String username,String password,String clientId,String clientSecret,String scope) throws IOException{
        StringBuffer params=new StringBuffer();

        params.append("grant_type=password&username=").append(username)
                .append("&password=").append(password)
                .append("&client_id=").append(clientId)
                .append("&client_secret=").append(clientSecret)
                .append("&scope=").append(scope);

        return HttpUtil.sendPost(oauthTokenUrl, params.toString());
    }
}
