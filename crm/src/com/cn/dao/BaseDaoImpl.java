package com.cn.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.cn.domain.Customer;
import com.cn.domain.PageBean;
/**
 * 以后所有的Dao的实现类，都可以 继承BaseDaoImpl,增删改查分页方法不用再写了
 * @author heting
 *
 * @param <T>
 */
@SuppressWarnings("all")//压制黄线警告
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	//定义成员属性
	private Class clazz;
	public BaseDaoImpl(){
		//this表示子类，c表示就是CustomerDaoImpl的class对象
		Class c = this.getClass();
		//CustomerDaoImpl extends BaseDaoImpl<Customer>
		//第2步：获取到BaseDaoImpl<Customer>
		Type type = c.getGenericSuperclass();
		
		//目的：把type接口转换成子接口
		if(type instanceof ParameterizedType){
			ParameterizedType ptype = (ParameterizedType) type;
			
			//获取customer
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
	}
	/**
	 * 添加
	 */
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	/**
	 * 删除
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	/**
	 * 修改
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	/**
	 * 通过主键查询
	 */
	public T findById(Long id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}
	/**
	 * 查询所有数据
	 */
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}
	/**
	 * 分页查询
	 */

	public PageBean<T> findByPage(Integer pageCode, Integer pageSize,DetachedCriteria criteria) {
		PageBean<T> page = new PageBean<T>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		//先查询总记录数，select count(*)
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			//总的记录数
			page.setTotalCount(totalCount);
		}
		
		//强调：把select count(*) 先清空，变成select * ..
		criteria.setProjection(null);
		
		//提供分页的查询
		List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria,(pageCode-1)*pageSize, pageSize);
		
		//分页查询数据，每页显示的数据，使用limit
		page.setBeanList(beanList);
		
		return page;
	}

}
