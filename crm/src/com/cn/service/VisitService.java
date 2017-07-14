package com.cn.service;

import org.hibernate.criterion.DetachedCriteria;

import com.cn.domain.PageBean;
import com.cn.domain.Visit;

public interface VisitService {

	PageBean<Visit> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria);

	void save(Visit visit);

}
