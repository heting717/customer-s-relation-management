package com.cn.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.cn.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 用户的拦截器，判断用户是否已经登录，执行下一个拦截器
 * 如果没有登录，返回到登陆页面（不能对所有的请求都拦截，login和regist方法）
 * 继承指定拦截器
 * @author heting
 *
 */
public class UserInterceptor extends MethodFilterInterceptor{

	/**
	 * 拦截目标Action的方法
	 */
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//获取session
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user == null){
			return "login";
		}
		
		return invocation.invoke();
	}

}
