package com.cn.service;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.CustomerDao;
import com.cn.domain.Customer;
import com.cn.domain.PageBean;
import com.cn.service.CustomerService;




@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}


	public void save(Customer customer) {
		customerDao.save(customer);
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize,DetachedCriteria criteria) {
		return customerDao.findByPage(pageCode,pageSize,criteria);
	}

	/**
	 * 通过主键，查询客户
	 */
	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	/**
	 * 删除客户
	 */
	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}
	/**
	 * 更新客户
	 */

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	/**
	 * 查询所有客户
	 */
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	/**
	 * 统计客户的来源
	 */
	@Override
	public List<Object[]> findBySource() {
		return customerDao.findBySource();
	}

}
