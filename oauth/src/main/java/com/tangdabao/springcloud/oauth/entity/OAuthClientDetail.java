package com.tangdabao.springcloud.oauth.entity;

public class OAuthClientDetail {
	private String username;
	private String password;

	public OAuthClientDetail(OAuthClientDetail oauth) {
		username=oauth.getUsername();
		password=oauth.getPassword();
	}
	public OAuthClientDetail() {}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
