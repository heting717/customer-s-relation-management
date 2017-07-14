package com.cn.service;

import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.UserDao;
import com.cn.domain.User;
import com.cn.utils.MD5Utils;

/**
 * 用户的业务层
 * @author heting
 *
 */
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public User checkCode(String user_code) {
		return userDao.checkCode(user_code);
	}
	@Override
	public void save(User user) {
		//给密码加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		//用户的状态默认是1状态
		user.setUser_state("1");
		//调用持久层
		userDao.save(user);
		
		
	}
	/**
	 * 登陆通过登陆名和密码做校验
	 * 先给密码加密，再查询
	 */
	public User login(User user) {
		//给密码加密
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}
	
}
