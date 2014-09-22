package net.spring.dao.mapper;

import java.util.List;
import java.util.Map;

import net.spring.entity.Product;
import net.spring.entity.User;

public interface Mapper {
	/**
	 * 返回前limit条用户
	 * @return List<User>
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-29下午1:37:38
	 * @param limit
	 * @return
	 */
	public List<User> findAllUser(int limit);
	/**
	 * 
	 * @return
	 */
	public int accountUser();
	/**
	 * 返回一条产品记录
	 * @param id
	 * @return
	 */
	public Product queryOneProduct(int id);
	
	/**
	 * 保存基本信息
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-5 上午9:49:19
	 * @param map
	 */
	public void saveBaseInfomation(Map map);
}
