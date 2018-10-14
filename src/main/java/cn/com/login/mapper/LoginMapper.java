package cn.com.login.mapper;

import cn.com.login.model.LoginModel;

public interface LoginMapper {
	
	public void register(LoginModel loginModel);
	
	public LoginModel login(String nickName);
		
	
}
