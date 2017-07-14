package com.cn.action;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.alibaba.fastjson.annotation.JSONField;
import com.cn.domain.Customer;
import com.cn.domain.Dict;
import com.cn.domain.PageBean;
import com.cn.service.CustomerService;
import com.cn.utils.FastJsonUtil;
import com.cn.utils.UploadUtils;



public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{

	
	private static final long serialVersionUID = -8876151471501110413L;
	//手动new，模型驱动封装数据
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	//提供service的成员属性，提供set方法
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	//属性驱动的方式
	//当前页，默认值就是1
	private Integer pageCode = 1;
	public void setPageCode(Integer pageCode) {
		if(pageCode ==null){
			pageCode = 1;
		}
		this.pageCode = pageCode;
	}
	//每页显示的数据的条数
	private Integer pageSize = 2;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 分页查询方法
	 */
	public String findByPage(){
		//调用Service业务层
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		
		//拼接查询的条件
		String cust_name = customer.getCust_name();
		System.out.println("cust_name:"+cust_name);
		if(cust_name != null && !cust_name.trim().isEmpty()){
			//说明，客户的名称输入值了
			criteria.add(Restrictions.like("cust_name", "%"+cust_name+"%"));
		}
		
		//拼接客户级别
		Dict level = customer.getLevel();
		if(level != null && !level.getDict_id().trim().isEmpty()){
			//说明，客户级别肯定选择了一个级别
			criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
		}
		//客户来源
		Dict source = customer.getSource();
		if(source != null && !source.getDict_id().trim().isEmpty()){
			//说明，客户级别肯定选择了一个级别
			criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
		}
		
		
		//查询
		PageBean<Customer> page = customerService.findByPage(pageCode,pageSize,criteria);
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		//栈顶是map<"page",page对象
		vs.set("page",page);
		
		
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
	 * 文件上传，需要在CustomerAction类中定义成员的属性，命名是有规则的
	 * private File upload;  //表示要上传的文件，这里的upload是和add.jsp页面的<input type="file" name="upload"/>name属性名是相同的
	 * private String uploadFileName;//表示是上传文件的名称（没有中文乱码），命名：上面名称+FileName
	 * private String uploadContentType;//表示上传文件的MIME类型
	 * 
	 */
	//要上传的文件
	private File upload;
	//文件名称
	private String uploadFileName;
	//文件的MIME类型
	private String uploadContentType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 * 保存客户的方法
	 * @return
	 * @throws IOException 
	 */
	public String save() throws IOException{
		if(uploadFileName != null){
			//打印
			System.out.println("文件类型"+uploadContentType);
			//把文件的名称处理一下
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			//把文件上传到D:\\important_software\\apache_tomcat8.0\\apache-tomcat-8.0.37\\webapps\\upload
			String path = "D:\\important_software\\apache_tomcat8.0\\apache-tomcat-8.0.37\\webapps\\upload\\";
			
			//创建file对象
			File file = new File(path+uuidName);
			//简单方式
			FileUtils.copyFile(upload, file);
			
			//把上传的文件的路径，保存到客户表中
			customer.setFilePath(path+uuidName);
			
		}
		
		
		
		customerService.save(customer);
		return "save";
	}
	/**
	 * 删除客户
	 * @return
	 */
	public String delete(){
		//删除客户，获取客户的信息获取到上传文件的路径
		customer = customerService.findById(customer.getCust_id());
		//获取上传文件的路径
		String filePath = customer.getFilePath();
		//删除客户
		customerService.delete(customer);
		//再删除文件
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
		return "delete";
	}
	/**
	 * 跳转到初始化修改页面
	 * @return
	 */
	public String initUpdate(){
		//customer默认压栈，Action默认压栈，model是Action类的书写getModel(返回customer对象)
		customer = customerService.findById(customer.getCust_id());
		return "initUpdate";
	}
	/**
	 * 修改客户的功能
	 * @return
	 * @throws IOException 
	 */
	public String update() throws IOException{
		//判断，说明客户上传了新的图片
		if(uploadFileName != null){
			//先删除旧的图片
			String oldFilePath = customer.getFilePath();
			if(oldFilePath != null && !oldFilePath.trim().isEmpty()){
				//说明，旧的路径存在，删除图片
				File f = new File(oldFilePath);
				f.delete();
			}
			//上传新的图片
			//先处理文件的名称的问题
			String uuidName = UploadUtils.getUUIDName(uploadFileName);
			//把文件上传到D:\\important_software\\apache_tomcat8.0\\apache-tomcat-8.0.37\\webapps\\upload
			String path = "D:\\important_software\\apache_tomcat8.0\\apache-tomcat-8.0.37\\webapps\\upload\\";
			
			//创建file对象
			File file = new File(path+uuidName);
			//简单方式
			FileUtils.copyFile(upload, file);
			
			//把上传的文件的路径，保存到客户表中
			customer.setFilePath(path+uuidName);
		}
		//更新客户的信息
		customerService.update(customer);
		
		return "update";
	}
	/**
	 * 查询所有的客户
	 * @return
	 */
	public String findAll(){
		List<Customer> list = customerService.findAll();
		//转换成json
		String jsonString = FastJsonUtil.toJSONString(list);//转json时会出现json的死循环问题，因为Customer类中有linkmans的集合，Linkman类中有Customer对象，
		                                                    //处理是在set的集合（即linkmans的集合）上加@JSONField(serialize=false)//默认不把set集合进行json的转换
		HttpServletResponse response = ServletActionContext.getResponse();
		
		FastJsonUtil.write_json(response, jsonString);
		
		return NONE;
	}
	/**
	 * 统计来源客户的数量
	 * @return
	 */
	public String findBySource(){
		List<Object[]> list = customerService.findBySource();
		//压栈
		ValueStack vs = ActionContext.getContext().getValueStack();
		vs.set("list", list);
		
		return "findBySource";
	}
}
