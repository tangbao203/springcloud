package com.tangdabao.springcloud.oauth.config;

import com.tangdabao.springcloud.oauth.entity.OAuthClientDetail;
import com.tangdabao.springcloud.oauth.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserDetailslService implements UserDetailsService {
	@Autowired
	private SysUserService sysUserService;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//获取用户的详细信息
		OAuthClientDetail user=sysUserService.queryUserByName(username);
		if(user==null)
			throw new UsernameNotFoundException("user not found");
		
		return new AuthUserDetails(user);
	}



}
