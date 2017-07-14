package com.cn.service;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.VisitDao;
import com.cn.domain.PageBean;
import com.cn.domain.Visit;

/**
 * 客户拜访业务层
 * @author heting
 *Service(value="visitService") = <bean id="visitService" class=".."></bean>
 */
@Service(value="visitService")
@Transactional
public class VisitServiceImpl implements VisitService {
	
	@Resource(name="visitDao")
	private VisitDao visitDao;

	/**
	 * 分页查询
	 */
	public PageBean<Visit> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria) {
		return visitDao.findByPage(pageCode, pageSize, criteria);
	}

	/**
	 * 保存
	 */
	public void save(Visit visit) {
		visitDao.save(visit);
	}

	
}
