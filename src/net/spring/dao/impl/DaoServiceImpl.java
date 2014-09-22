package net.spring.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lxf.domain.AbstractMybatisBaseRepository;

import static net.lxf.util.MyBatisStatementConstant.*;

import net.spring.dao.mapper.Mapper;
import net.spring.entity.Product;
import net.spring.entity.User;


@Repository	//dao组件
public class DaoServiceImpl extends AbstractMybatisBaseRepository implements Mapper {
	

	@Override
	public List<User> findAllUser(int limit) {
//		三种情况
//		List<User> list = getSqlSession().selectList("net.spring.dao.mapper.Mapper.findAllUser",limit);
//		List<User> list = getSqlSession().selectList(Mapper.class.getName()+".queryOneProduct",limit);
//		List<User> list = getSqlSession().selectList("findAllUser",limit);/*调用Mapper接口中的方法*/
		List<User> list = getSqlSession().selectList(STMT_FINDALLUSER,limit);/*调用Mapper接口中的方法*/
		return list;
	}
 
	@Override 
	public int accountUser() {
		//没有参数的时候传入null
//		return (Integer) sqlSession.selectOne("net.spring.dao.mapper.Mapper.accountUser",null);
		return 1;
	}

	@Override
	public Product queryOneProduct(int id) {
//		return (Product) sqlSession.selectOne(Mapper.class.getName()+".queryOneProduct", id);
		return null;
	}

	@Override
	public void saveBaseInfomation(Map map) {
		getSqlSession().insert(STMT_SAVEBASEINFOMATION, map);
	}

}
