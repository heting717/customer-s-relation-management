package com.cn.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cn.domain.User;
import com.cn.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6259594293594562529L;

	private User user = new User();
	@Override
	public User getModel() {
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 注册
	 * @return
	 */
	public String regist(){
		userService.save(user);
		return LOGIN;
	}
	/**
	 * 通过登陆名，判断，登录名是否已经存在
	 * @return
	 */
	public String checkCode(){
		//调用业务层，查询
		User u = userService.checkCode(user.getUser_code());
		//获取response对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("test/html;charset=UTF-8");
		try {
			//获取输出流
			PrintWriter writer = response.getWriter();
			//进行判断
			if(u !=null){
				//说明登陆名查询到用户了，说明登陆名已经存在，不能注册
				writer.print("no");
			}else{
				//说明：不存在登录名，可以注册
				writer.print("yes");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return NONE;
	}
	/**
	 * 登陆
	 */
	public String login(){
		User existUser = userService.login(user);
		//判断，登录名或者密码错误
		if(existUser == null){
			return LOGIN;
		}else{
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);//将用户添加到session中
			return "loginOK";
		}
	}
	/**
	 * 安全退出
	 */
	public String exit(){
		ServletActionContext.getRequest().getSession().removeAttribute("existUser");//将用户从session中销毁
		return LOGIN;
	}
}
