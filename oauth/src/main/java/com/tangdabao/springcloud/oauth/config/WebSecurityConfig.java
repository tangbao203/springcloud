package com.tangdabao.springcloud.oauth.config;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring security 配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${spring.security.user.name:user}")
	private String username;
	@Value("${spring.security.user.password:123456}")
	private String password;

	private final String ACCESS_ROLE_NAME="USER";

	@Autowired
	private AuthProvider authProvider;

	@Autowired
	private SysUserDetailslService sysUserDetailslService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(authProvider);

		//http basic授权取消csrf
//		http.httpBasic()
//				.and().csrf().disable()
//				//要求所有的/oauth/** 服务都必须得到USER授权
//				.authorizeRequests()
//				.antMatchers("/oauth/**")
//				.hasRole(ACCESS_ROLE_NAME);
	}

	//比较简单的内存方式 设置用户的权限
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(username).password(password).roles(ACCESS_ROLE_NAME);
	}


	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new AuthPasswordEncoder();
	}
}
