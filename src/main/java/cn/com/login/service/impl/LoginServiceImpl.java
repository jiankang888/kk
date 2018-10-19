package cn.com.login.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.login.mapper.LoginMapper;
import cn.com.login.model.LoginModel;
import cn.com.login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	@Transactional
	public void register(LoginModel loginModel) {
		loginMapper.register(loginModel);
	}

	@Override
	public LoginModel login(String nickName) {
		// TODO Auto-generated method stub
		return loginMapper.login(nickName);
	}

	@Override
	public List<LoginModel> findAllAccount() {
		// TODO Auto-generated method stub
		return loginMapper.findAllAccount();
	}
	
	

}
