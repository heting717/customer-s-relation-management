package com.cn.service;

import java.util.List;

import com.cn.domain.Dict;

public interface DictService {

	List<Dict> findByCode(String dict_type_code);

}
