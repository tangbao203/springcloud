package com.tangdabao.springcloud.oauth.dao;

import com.tangdabao.springcloud.oauth.entity.OAuthClientDetail;
import org.apache.ibatis.annotations.Mapper;

public interface OAuthClientDetailDao {
	public OAuthClientDetail queryUserByName(String username);
}
