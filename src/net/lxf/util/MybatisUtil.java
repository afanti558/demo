package net.lxf.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * 管理Mybatis的SqlSession创建和关闭
 * @author lxf
 *
 */
public class MybatisUtil {
	private SqlSession sqlSession= null;
	
	public MybatisUtil(){
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
			this.sqlSession = factory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  SqlSession getSqlSession(){
		return this.sqlSession;
	}
	
	public void closeSqlSession(SqlSession sqlSession){
		if(this.sqlSession != null){
			sqlSession.close();
		}
	}
}
