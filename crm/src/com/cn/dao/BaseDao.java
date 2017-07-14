package com.cn.dao;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import com.cn.domain.PageBean;

/**
 * 以后所有的dao都需要继承BaseDao接口
 * 自定义泛型接口
 * @author heting
 *
 */
public interface BaseDao<T> {
	
	public void save(T t);
	
	public void delete(T t);
	
	public void update(T t);
	
	public T findById(Long id);
	
	public List<T> findAll();
	
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
}
