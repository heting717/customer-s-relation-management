package com.cn.dao;

import java.util.List;

import com.cn.domain.Dict;

public interface DictDao {

	List<Dict> findByCode(String dict_type_code);

}
