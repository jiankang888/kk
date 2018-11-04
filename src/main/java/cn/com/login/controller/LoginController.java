package cn.com.login.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import cn.com.login.model.LoginModel;
import cn.com.login.service.LoginService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private JmsOperations jmsOperations;

	private Logger logger = LogManager.getLogger(LoginController.class);
	
	
    @RequestMapping(value="/register" , method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> register(@Valid @RequestBody LoginModel loginModel , BindingResult bindingResult){
    	
    	Map<String, String> result =  new HashMap<String, String>();
    	
    	if( bindingResult.hasErrors() ) {
    		List<ObjectError> errorList = bindingResult.getAllErrors();
    		for(ObjectError objectError : errorList) {
    			logger.info(objectError.getDefaultMessage());
    			result.put("data", objectError.getDefaultMessage());
    		}
    		
    		result.put("success", "false");
    		return result;
    	}
    	
    	try {
    		loginService.register(loginModel);
    		result.put("success", "true");
    		jmsOperations.convertAndSend("kk.topic", "jms message");
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
			logger.info(jmsOperations.receive("kk.topic").toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("success", "false");
		}
    	
    	return result;
    	
    }
    
    @RequestMapping(value="/login/allAccount" , method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> findAllAccount(HttpServletRequest request , HttpServletResponse response){
    	
    	Map<String, Object> result = new HashMap<>();
    	
    	
    	try {
    		HttpSession session = request.getSession();
    		String sessionId = session.getId();
    		session.setMaxInactiveInterval(30);
    		session.setAttribute("name1", "jixiang");
    		session.setAttribute("name2", "jiankang");
    		if ( session.isNew() ) {
    			logger.info("first connect");
    		} else {
    			logger.info("sessionId is" + sessionId);
                Enumeration<String> enumeration = session.getAttributeNames();
                while(enumeration.hasMoreElements()) {
                	String name = enumeration.nextElement();
                	logger.info(name + " is " + session.getAttribute(name));
                }
    		}
    			
    		result.put("Data", loginService.findAllAccount());
			result.put("success", "true");
			response.sendRedirect("http://www.baidu.com");
			response.setIntHeader("Refresh", 5);
		} catch (Exception e) {
			// TODO: handle exception
			result.put("success", "false");
			e.printStackTrace();
		}
    	
    	return result;
    	
    }	
}
