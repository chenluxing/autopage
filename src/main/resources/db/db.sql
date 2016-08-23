CREATE TABLE `tb_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_code` varchar(255) DEFAULT NULL COMMENT '代码',
  `group_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `group_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

CREATE TABLE `tb_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT NULL COMMENT '元素组ID',
  `element_code` varchar(255) DEFAULT NULL COMMENT '代码',
  `element_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `element_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `element_type` tinyint(2) DEFAULT NULL COMMENT '类型',
  `element_etc` varchar(255) DEFAULT NULL COMMENT '示例数据',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;

CREATE TABLE `tb_element_html_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `element_id` int(11) DEFAULT NULL,
  `show_seq` tinyint(5) DEFAULT NULL,
  `ehg_desc` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `tb_element_html` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eh_code` varchar(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `eh_value` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '值',
  `eh_suffix` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '末尾追加',
  `eh_group_id` int(11) DEFAULT NULL COMMENT '同一个td标记',
  `eh_parent_id` int(11) DEFAULT NULL COMMENT '父节点ID',
  `html_type` tinyint(2) DEFAULT NULL COMMENT '类型',
  `eh_desc` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '备注',
  `show_seq` tinyint(5) DEFAULT NULL COMMENT '显示顺序',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



