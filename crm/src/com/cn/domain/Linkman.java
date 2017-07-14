package com.cn.domain;

public class Linkman {
	/*
	 * `lkm_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
  `lkm_name` VARCHAR(16) DEFAULT NULL COMMENT '联系人姓名',
  `lkm_cust_id` BIGINT(32) NOT NULL COMMENT '客户id',
  `lkm_gender` CHAR(1) DEFAULT NULL COMMENT '联系人性别',
  `lkm_phone` VARCHAR(16) DEFAULT NULL COMMENT '联系人办公电话',
  `lkm_mobile` VARCHAR(16) DEFAULT NULL COMMENT '联系人手机',
  `lkm_email` VARCHAR(64) DEFAULT NULL COMMENT '联系人邮箱',
  `lkm_qq` VARCHAR(16) DEFAULT NULL COMMENT '联系人qq',
  `lkm_position` VARCHAR(16) DEFAULT NULL COMMENT '联系人职位',
  `lkm_memo` VARCHAR(512) DEFAULT NULL COMMENT '联系人备注',
	 */
	private Long lkm_id;
	private String lkm_name;
	private String lkm_gender;
	private String lkm_phone;
	private String lkm_mobile;
	private String lkm_email;
	private String lkm_qq;
	private String lkm_position;
	private String lkm_memo;
	//对多
	private Customer customer;
	
	
	public Long getLkm_id() {
		return lkm_id;
	}
	public void setLkm_id(Long lkm_id) {
		this.lkm_id = lkm_id;
	}
	public String getLkm_name() {
		return lkm_name;
	}
	public void setLkm_name(String lkm_name) {
		this.lkm_name = lkm_name;
	}
	public String getLkm_gender() {
		return lkm_gender;
	}
	public void setLkm_gender(String lkm_gender) {
		this.lkm_gender = lkm_gender;
	}
	public String getLkm_phone() {
		return lkm_phone;
	}
	public void setLkm_phone(String lkm_phone) {
		this.lkm_phone = lkm_phone;
	}
	public String getLkm_mobile() {
		return lkm_mobile;
	}
	public void setLkm_mobile(String lkm_mobile) {
		this.lkm_mobile = lkm_mobile;
	}
	public String getLkm_email() {
		return lkm_email;
	}
	public void setLkm_email(String lkm_email) {
		this.lkm_email = lkm_email;
	}
	public String getLkm_qq() {
		return lkm_qq;
	}
	public void setLkm_qq(String lkm_qq) {
		this.lkm_qq = lkm_qq;
	}
	public String getLkm_position() {
		return lkm_position;
	}
	public void setLkm_position(String lkm_position) {
		this.lkm_position = lkm_position;
	}
	public String getLkm_memo() {
		return lkm_memo;
	}
	public void setLkm_memo(String lkm_memo) {
		this.lkm_memo = lkm_memo;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
