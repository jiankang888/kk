package cn.com.position.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.position.service.PositionService;

@Controller
@RequestMapping("my_position")
public class PositionController {
	
	private  Logger logger = LogManager.getLogger(PositionController.class);

	
	@Autowired
	private PositionService positionService;
	
	@RequestMapping(value="/position" , method= RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAllPosition(){
		
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			result.put("data", positionService.getAllPosition());
			result.put("success", "true");
			logger.info("logger in success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("success", "false");
		}		
		
		return result;
	}
	

}
