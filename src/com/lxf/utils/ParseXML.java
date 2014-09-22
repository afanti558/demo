package com.lxf.utils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public  class ParseXML {
	/**
	 * 解析xml,将xml文件保存在Map中,这里的xml结构比较简单，根节点下面只有一层子节点，例如：
	 * <?xml version="1.0" encoding="UTF-8"?>
		<infomation>
			<name>xiaozhang</name>
			<age>30</age>
		</infomation>
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-11 上午11:11:46
	 * @param inFile
	 * @throws Exception
	 */
	public static void parseXML(File inFile) throws Exception{
		// 将解析结果存储在HashMap中  
	    Map<String, String> map = new HashMap<String, String>();  
	    // 从request中取得输入流  
//	    InputStream inputStream = request.getInputStream();  
	    // 读取输入流  
	    SAXReader reader = new SAXReader();  
	    Document document = reader.read(inFile);  
	    // 得到xml根元素  
	    Element root = document.getRootElement();  
	    // 得到根元素的所有子节点  
	    List<Element> elementList = root.elements();  
	    // 遍历所有子节点  
	    for (Element e : elementList)  
//	        map.put(e.getName(), e.getText()); 
	    	System.out.println(e.getName()+"------>"+e.getText());
	}
	
	/**还没有完成
	 * 解析xml,将xml文件保存在Map中，例如：
	 * <?xml version="1.0" encoding="UTF-8"?>
		<infomation>
			<name>xiaozhang</name>
			<age>30</age>
			<tel>
				<no1>12345</no1>
				<no2>67890</no2>
			</tel>
		</infomation>
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-11 上午11:11:46
	 * @param inFile
	 * @throws Exception
	 */
	public static void parseXML2(File inFile) throws Exception{
		// 将解析结果存储在HashMap中  
	    Map<String, String> map = new HashMap<String, String>();  
	    // 从request中取得输入流  
//	    InputStream inputStream = request.getInputStream();  
	    // 读取输入流  
	    SAXReader reader = new SAXReader();  
	    Document document = reader.read(inFile);  
	    // 得到xml根元素  
	    Element root = document.getRootElement();  
	    // 得到根元素的所有子节点  
	    List<Element> elementList = root.elements();  
	    //迭代List集合
	    Iterator it = elementList.iterator();
	    while(it.hasNext()){
//	    	List subElement =  it.next();
	    	// 遍历所有子节点  
	    	for (Element e : elementList)  
//	        map.put(e.getName(), e.getText()); 
	    		System.out.println(e.getName()+"------>"+e.getText());
	    }
	}
	
	
	public static void main(String args []) throws Exception{
		File inFile = new File("G:"+File.separator+"test.xml");
		parseXML(inFile);
	}
}
