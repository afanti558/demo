package net.spring.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.spring.dao.mapper.Mapper;
import net.spring.entity.User;

@Service //service组件
public class ServiceImpl implements net.spring.service.Service{
	
	@Autowired
	private Mapper dao;//注入接口

	@Override
	public List<User> findAllUser(int limit) {
		return dao.findAllUser(limit);
	}

	@Override
	public void saveBaseInfomation(Map map) {
		dao.saveBaseInfomation(map);
	}
	
}
