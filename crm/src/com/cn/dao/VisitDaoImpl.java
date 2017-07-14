package com.cn.dao;


import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.cn.domain.Visit;
/**
 * 客户拜访的持久层
 * @author heting
 *
 */
@Repository(value="visitDao")
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao {

	/*不用
	 * @Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	会报错
	*/
	@Resource(name="sessionFactory")
	public void set2SessionFactory(SessionFactory sessionFactory){
		//关键，调用父类方法
		super.setSessionFactory(sessionFactory);
	}
	

}
