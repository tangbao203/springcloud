package com.tangdabao.springcloud.oauth.config;

import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        //@todo 密码编码方式
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        //@todo 如何判断密码正确
        return s.equals(charSequence.toString());
    }
}
