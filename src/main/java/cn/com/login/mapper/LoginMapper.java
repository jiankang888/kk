package cn.com.login.mapper;

import java.util.List;

import cn.com.login.model.LoginModel;

public interface LoginMapper {
	
	public void register(LoginModel loginModel);
	
	public LoginModel login(String nickName);
		
	public List<LoginModel> findAllAccount();
	
}
