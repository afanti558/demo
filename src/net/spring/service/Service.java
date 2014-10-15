package net.spring.service;

import java.util.List;

import net.spring.entity.User;

public interface Service {
	/**
	 * 返回前limit调用户记录
	 * @return List<User>
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-4 下午2:04:39
	 * @param limit
	 * @return
	 */
	public List<User> findAllUser(int limit);

}
