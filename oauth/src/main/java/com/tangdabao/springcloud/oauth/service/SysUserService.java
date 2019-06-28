package com.tangdabao.springcloud.oauth.service;

import com.tangdabao.springcloud.oauth.dao.OAuthClientDetailDao;
import com.tangdabao.springcloud.oauth.entity.OAuthClientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {
	@Autowired
	private OAuthClientDetailDao oAuthClientDetailDao;
	
	public OAuthClientDetail queryUserByName(String username){
		return oAuthClientDetailDao.queryUserByName(username);
	}
}
