package com.cn.service;

import org.hibernate.criterion.DetachedCriteria;

import com.cn.domain.Linkman;
import com.cn.domain.PageBean;

public interface LinkmanService {

	PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize,DetachedCriteria criteria);

	void save(Linkman linkman);

	void delete(Linkman linkman);

	Linkman findById(Long lkm_id);

	void update(Linkman linkman);

	

	
}
