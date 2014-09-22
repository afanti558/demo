package com.raipeng.test;

import java.util.Iterator;
import java.util.List;

import net.lxf.util.DateUtils;
import net.lxf.util.IpTimeStamp;
import net.spring.controller.HelloWorldController;
import net.spring.dao.impl.DaoServiceImpl;
import net.spring.dao.mapper.Mapper;
import net.spring.entity.Product;
import net.spring.entity.User;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestService {
	private static Mapper dao = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new DaoServiceImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	public void testList(){
		List<User> list = dao.findAllUser(5);
		if(list != null){
			System.out.println(list.size());
		}else{
			System.out.println("list為null");
		}
		//輸出查詢結果
		Iterator<User> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	@Test
	public void testAccount() {
		int count = dao.accountUser();
		System.out.println(count);
	}
	
	@Test
	public void testQueryOneProduct() {
		Product product = dao.queryOneProduct(40);
		System.out.println(product);
	}
	
	@Test
	public void testController() {
		HelloWorldController controller = new HelloWorldController();
		
	}
	
	@Test
	public void testBuchong() {
		System.out.println(buchong(123));
	}
	
	/**
	 * 将给定的数字签名补充0，使其工占有6位
	 */
	public String buchong(int n) {
		String str = n+"";
		int len = str.length();
		str = "000000"+str;
		str = str.substring(len);
		return str;
	}
	
	/**
	 * 测试取得唯一文件名：ip+时间戳+三位随机数
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-28上午9:02:26
	 */
	@Test
	public void testOnlyFileName() {
		IpTimeStamp filename = new IpTimeStamp("192.168.1.110");
//		System.out.println(filename.getIpTimeRand("192.168.1.110"));
		System.out.println(filename.getsuf("lxf.jpg"));
	}
	
	/**
	 * 将字符串按照指定时间样式返回
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-29 下午3:51:25
	 */
	@Test
	public void testGetDate(){
		System.out.println(DateUtils.getDate("1"));
	}
	
}



















