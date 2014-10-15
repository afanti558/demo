package net.spring.dao.mapper;

import java.util.List;
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

	
}
