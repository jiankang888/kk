package cn.com.position.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.position.mapper.PositionMapper;
import cn.com.position.model.PositionModel;
import cn.com.position.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService{
	
	@Autowired
	private PositionMapper positionMapper;

	@Override
	public List<PositionModel> getAllPosition() {
		// TODO Auto-generated method stub
		return positionMapper.getAllPosition();
	}
	
	

}
