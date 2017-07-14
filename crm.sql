USE crm;
CREATE TABLE `sys_user` (
  `user_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_code` VARCHAR(32) NOT NULL COMMENT '用户账号',
  `user_name` VARCHAR(64) NOT NULL COMMENT '用户名称',
  `user_password` VARCHAR(32) NOT NULL COMMENT '用户密码',
  `user_state` CHAR(1) NOT NULL COMMENT '1:正常,0:暂停',
  PRIMARY KEY (`user_id`)
) ENGINE=INNODB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
DROP TABLE sys_user;

CREATE TABLE `base_dict` (
  `dict_id` VARCHAR(32) NOT NULL COMMENT '数据字典id(主键)',
  `dict_type_code` VARCHAR(10) NOT NULL COMMENT '数据字典类别代码',
  `dict_type_name` VARCHAR(64) NOT NULL COMMENT '数据字典类别名称',
  `dict_item_name` VARCHAR(64) NOT NULL COMMENT '数据字典项目名称',
  `dict_item_code` VARCHAR(10) DEFAULT NULL COMMENT '数据字典项目(可为空)',
  `dict_sort` INT(10) DEFAULT NULL COMMENT '排序字段',
  `dict_enable` CHAR(1) NOT NULL COMMENT '1:使用 0:停用',
  `dict_memo` VARCHAR(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
INSERT  INTO `base_dict`(`dict_id`,`dict_type_code`,`dict_type_name`,`dict_item_name`,`dict_item_code`,`dict_sort`,`dict_enable`,`dict_memo`) VALUES ('1','001','客户行业','教育培训 ',NULL,1,'1',NULL),('10','003','公司性质','民企',NULL,3,'1',NULL),('12','004','年营业额','1-10万',NULL,1,'1',NULL),('13','004','年营业额','10-20万',NULL,2,'1',NULL),('14','004','年营业额','20-50万',NULL,3,'1',NULL),('15','004','年营业额','50-100万',NULL,4,'1',NULL),('16','004','年营业额','100-500万',NULL,5,'1',NULL),('17','004','年营业额','500-1000万',NULL,6,'1',NULL),('18','005','客户状态','基础客户',NULL,1,'1',NULL),('19','005','客户状态','潜在客户',NULL,2,'1',NULL),('2','001','客户行业','电子商务',NULL,2,'1',NULL),('20','005','客户状态','成功客户',NULL,3,'1',NULL),('21','005','客户状态','无效客户',NULL,4,'1',NULL),('22','006','客户级别','普通客户',NULL,1,'1',NULL),('23','006','客户级别','VIP客户',NULL,2,'1',NULL),('24','007','商机状态','意向客户',NULL,1,'1',NULL),('25','007','商机状态','初步沟通',NULL,2,'1',NULL),('26','007','商机状态','深度沟通',NULL,3,'1',NULL),('27','007','商机状态','签订合同',NULL,4,'1',NULL),('3','001','客户行业','对外贸易',NULL,3,'1',NULL),('30','008','商机类型','新业务',NULL,1,'1',NULL),('31','008','商机类型','现有业务',NULL,2,'1',NULL),('32','009','商机来源','电话营销',NULL,1,'1',NULL),('33','009','商机来源','网络营销',NULL,2,'1',NULL),('34','009','商机来源','推广活动',NULL,3,'1',NULL),('4','001','客户行业','酒店旅游',NULL,4,'1',NULL),('5','001','客户行业','房地产',NULL,5,'1',NULL),('6','002','客户信息来源','电话营销',NULL,1,'1',NULL),('7','002','客户信息来源','网络营销',NULL,2,'1',NULL),('8','003','公司性质','合资',NULL,1,'1',NULL),('9','003','公司性质','国企',NULL,2,'1',NULL);
CREATE TABLE `cst_linkman` (
  `lkm_id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
  `lkm_name` VARCHAR(16) DEFAULT NULL COMMENT '联系人姓名',
  `lkm_cust_id` BIGINT(32) NOT NULL COMMENT '客户id',
  `lkm_gender` CHAR(1) DEFAULT NULL COMMENT '联系人性别',
  `lkm_phone` VARCHAR(16) DEFAULT NULL COMMENT '联系人办公电话',
  `lkm_mobile` VARCHAR(16) DEFAULT NULL COMMENT '联系人手机',
  `lkm_email` VARCHAR(64) DEFAULT NULL COMMENT '联系人邮箱',
  `lkm_qq` VARCHAR(16) DEFAULT NULL COMMENT '联系人qq',
  `lkm_position` VARCHAR(16) DEFAULT NULL COMMENT '联系人职位',
  `lkm_memo` VARCHAR(512) DEFAULT NULL COMMENT '联系人备注',
  PRIMARY KEY (`lkm_id`),
  KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),
  CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `sale_visit` (
  `visit_id`BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '拜访编号(主键)',
  `visit_cust_id` BIGINT(32) DEFAULT NULL COMMENT '客户id',
  `visit_user_id` BIGINT(32) DEFAULT NULL COMMENT '负责人id',
  `visit_time` DATE DEFAULT NULL COMMENT '拜访时间',
  `visit_interviewee` VARCHAR(32) DEFAULT NULL COMMENT '被拜访人',
  `visit_addr` VARCHAR(128) DEFAULT NULL COMMENT '拜访地点',
  `visit_detail` VARCHAR(256) DEFAULT NULL COMMENT '拜访详情',
  `visit_nexttime` DATE DEFAULT NULL COMMENT '下次拜访时间',
  PRIMARY KEY (`visit_id`),
  KEY `FK_sale_visit_cust_id` (`visit_cust_id`),
  KEY `FK_sale_visit_user_id` (`visit_user_id`),
  CONSTRAINT `FK_sale_visit_cust_id` FOREIGN KEY (`visit_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_sale_visit_user_id` FOREIGN KEY (`visit_user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=INNODB DEFAULT CHARSET=utf8;

SELECT * FROM cst_customer c,base_dict d WHERE d.dict_id =c.cust_source

