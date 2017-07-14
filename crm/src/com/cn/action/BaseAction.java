package com.cn.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8021804624950246255L;
	
	
	//属性驱动的方式
		//当前页，默认值就是1
		private Integer pageCode = 1;
		
		public Integer getPageCode() {
			return pageCode;
		}
		public void setPageCode(Integer pageCode) {
			if(pageCode ==null){
				pageCode = 1;
			}
			this.pageCode = pageCode;
		}
		
		//每页显示的数据的条数
		private Integer pageSize = 2;
		
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		/**
		 * 调用值栈对象的set方法
		 */
		public void setVs(String key,Object obj){
			ActionContext.getContext().getValueStack().set(key, obj);
		}
		/**
		 * 调用值栈的push的方法
		 * @param obj
		 */
		public void pushVs(Object obj){
			ActionContext.getContext().getValueStack().push(obj);
		}
}
