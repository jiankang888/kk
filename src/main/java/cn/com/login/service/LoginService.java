package cn.com.login.service;

import cn.com.login.model.LoginModel;

public interface LoginService {
	
	public void register(LoginModel loginModel);
	
	public LoginModel login(String nickName);
		

}
