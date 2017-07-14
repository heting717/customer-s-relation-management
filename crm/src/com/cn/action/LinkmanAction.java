package com.cn.action;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.cn.domain.Customer;
import com.cn.domain.Linkman;
import com.cn.domain.PageBean;
import com.cn.service.LinkmanService;
import com.opensymphony.xwork2.ModelDriven;

public class LinkmanAction extends BaseAction implements ModelDriven<Linkman>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3319414037708464328L;
	
	private Linkman linkman = new Linkman();
	public Linkman getModel() {
		return linkman;
	}
	private LinkmanService linkmanService;
	public void setLinkmanService(LinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}

	/**
	 * 分页功能
	 * @return
	 */
	public String findByPage(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		//获取到联系人的名称
		String lkm_name = linkman.getLkm_name();
		System.out.println("联系人名称："+lkm_name);
		if(lkm_name != null && !lkm_name.trim().isEmpty()){
			criteria.add(Restrictions.like("lkm_name", "%"+lkm_name+"%"));
		}
		
		
		//获取客户
		Customer customer = linkman.getCustomer();
		if(customer != null && customer.getCust_id()!= null){
			//拼接查询的条件
			criteria.add(Restrictions.eq("customer.cust_id", customer.getCust_id()));
		}
		
		//调用业务层
		PageBean<Linkman> page = linkmanService.findByPage(this.getPageCode(),this.getPageSize(),criteria);
		//压栈
		this.setVs("page", page);
		return "page";
	}
	
	/**
	 * 初始化到添加页面
	 * @return
	 */
	public String initAddUI(){
	
		return "initAddUI";
	}
	/**
	 * 保存联系人的方法
	 * @return
	 */
	public String save(){
		linkmanService.save(linkman);
		return "save";
	}
	/**
	 * 删除联系人
	 * 
	 * @return
	 */
	public String delete(){
		
		//删除客户
		linkmanService.delete(linkman);

		return "delete";
	}
	/**
	 * 跳转到初始化修改页面
	 * @return
	 */
	public String initUpdate(){
		//linkman默认压栈，Action默认压栈，model是Action类的书写getModel(返回linkman对象)
		linkman = linkmanService.findById(linkman.getLkm_id());
		return "initUpdate";
	}
	/**
	 * 修改客户的功能
	 * @return
	 * @throws IOException 
	 */
	public String update(){
		//更新联系人的信息
		linkmanService.update(linkman);
		
		return "update";
	}


}
