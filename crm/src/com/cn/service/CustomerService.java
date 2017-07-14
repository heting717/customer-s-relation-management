package com.cn.service;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.cn.domain.Customer;
import com.cn.domain.PageBean;



public interface CustomerService {

	public void save(Customer customer);

	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize,DetachedCriteria criteria);

	public Customer findById(Long cust_id);

	public void delete(Customer customer);

	public void update(Customer customer);

	public List<Customer> findAll();

	public List<Object[]> findBySource();

	


}
