package cn.com.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.login.model.LoginModel;
import cn.com.login.service.LoginService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
    @RequestMapping(value="/register" , method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> register(@RequestBody LoginModel loginModel){
    	
    	Map<String, String> result =  new HashMap<String, String>();
    	
    	try {
    		loginService.register(loginModel);
    		result.put("success", "true");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

        return result;
    }
    
    @RequestMapping(value="/login/{nickName}" , method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@PathVariable("nickName") String nickName) {
    	
    	Map<String, Object> result = new HashMap<String , Object>();
    	
    	try {
    		result.put("success", "true");
    		result.put("data", loginService.login(nickName));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("success", "false");
		}
    	
    	return result;
    	
    }
    
    @RequestMapping(value="/login/allAccount" , method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAllAccount(){
    	
    	Map<String, Object> result = new HashMap<>();
    	
    	try {
    		result.put("Data", loginService.findAllAccount());
			result.put("success", "true");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("success", "false");
			e.printStackTrace();
		}
    	
    	return result;
    	
    }	
}
