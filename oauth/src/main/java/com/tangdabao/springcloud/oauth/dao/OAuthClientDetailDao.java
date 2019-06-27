package com.tangdabao.springcloud.oauth.dao;

import com.ihuizhi.chess.cloud.oauth.entity.OAuthClientDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OAuthClientDetailDao {
	public OAuthClientDetail queryUserByName(String username);
}
