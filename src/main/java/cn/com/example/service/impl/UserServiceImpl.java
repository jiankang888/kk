package cn.com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.example.mapper.UserMapper;
import cn.com.example.model.User;
import cn.com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	public User selectUserById(int userId) {
		// TODO Auto-generated method stub
		return userMapper.selectUserById(userId);
	}
	
}
