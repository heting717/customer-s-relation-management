package com.cn.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.LinkmanDao;
import com.cn.domain.Linkman;
import com.cn.domain.PageBean;
/**
 * 业务层
 * @author heting
 *
 */
@Transactional
public class LinkmanServiceImpl implements LinkmanService {
	private LinkmanDao linkmanDao;
	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}
	/**
	 * 分页的方法
	 */
	public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize,
			DetachedCriteria criteria) {
		return linkmanDao.findByPage(pageCode, pageSize, criteria);
	}
	/**
	 * 保存
	 */
	public void save(Linkman linkman) {
		linkmanDao.save(linkman);
	}
	/**
	 * 删除
	 */
	public void delete(Linkman linkman) {
		linkmanDao.delete(linkman);
	}
	/**
	 * 根据联系人id查找联系人
	 */
	public Linkman findById(Long lkm_id) {
		return linkmanDao.findById(lkm_id);
	}
	/**
	 * 更新联系人
	 */
	public void update(Linkman linkman) {
		linkmanDao.update(linkman);
	}
	
	
}
