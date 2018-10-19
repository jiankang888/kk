package cn.com.login.service;

import java.util.List;

import cn.com.login.model.LoginModel;

public interface LoginService {
	
	public void register(LoginModel loginModel);
	
	public LoginModel login(String nickName);
	
	public List<LoginModel> findAllAccount();
		
}
