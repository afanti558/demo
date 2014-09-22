package com.lxf.domain;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMybatisBaseRepository extends SqlSessionDaoSupport {
	@Autowired
    public final void init(SqlSessionTemplate sessionTemplate) {
        this.setSqlSessionTemplate(sessionTemplate);
    }
}
