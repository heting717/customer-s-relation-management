package com.cn.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.cn.domain.Dict;
/**
 * ³Ö¾Ã²ã
 * @author heting
 *
 */
public class DictDaoImpl extends HibernateDaoSupport implements DictDao {

	@Override
	public List<Dict> findByCode(String dict_type_code) {
		return (List<Dict>) this.getHibernateTemplate().find("from Dict where dict_type_code = ?", dict_type_code);
	}

}
