package com.tangdabao.springcloud.oauth.config;

import com.ihuizhi.chess.cloud.oauth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ChessAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private SysUserDetailslService userService;

	@Autowired
	private SysUserService sysUserService;
	
	@Override
	public Authentication authenticate(Authentication authParam) throws AuthenticationException {
		//具体如何认证的实现
		if(authParam.getName()==null){
			throw new BadCredentialsException("username not found");
		}
		
		if(authParam.getCredentials()==null){
			throw new BadCredentialsException("password not found");
		}

		UserDetails user = userService.loadUserByUsername(authParam.getName());

		if(user==null || !authParam.getCredentials().equals(user.getPassword())){
			throw new BadCredentialsException("password not correct");
		}
		
		return new UsernamePasswordAuthenticationToken(authParam.getName(),authParam.getCredentials(),user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> paramClass) {
		return true;
	}



}
