package cn.com.login.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginModel{
	
	private Integer id;
	@NotBlank
	@Size(min=5,max=200,message="This nickName length is too small or to too large. ")
	private String nickName;
	@NotBlank
	private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
