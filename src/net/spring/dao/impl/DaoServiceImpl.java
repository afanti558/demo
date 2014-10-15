package net.spring.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;



import net.spring.dao.mapper.Mapper;
import net.spring.entity.AbstractMybatisBaseRepository;
import net.spring.entity.User;


@Repository	//dao组件
public class DaoServiceImpl extends AbstractMybatisBaseRepository implements Mapper {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser(int limit) {
		//三种情况
//		List<User> list = getSqlSession().selectList("net.spring.dao.mapper.Mapper.findAllUser",limit);
//		List<User> list = getSqlSession().selectList(Mapper.class.getName()+".queryOneProduct",limit);
//		List<User> list = getSqlSession().selectList("findAllUser",limit);
		List<User> list = getSqlSession().selectList("findAllUser",limit);/*调用Mapper接口中的方法*/
		return list;
	}
 
}
