package net.lxf.web.base.controller;

import static net.lxf.util.WebURLMappingConstant.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lxf.util.IpTimeStamp;
import net.spring.entity.User;
import net.spring.service.Service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller  //表示这是一个控制器类组件，这些注解注入都需要在springmvc的配置文件中配置，还有@Service和@Repository
public class HelloWorldController {

	@Autowired
	private Service service;//注入接口 
	
	/**
	 * 基本框架的搭建
	 * 这里使用ModelAndView进行页面的跳转，另外用String同样可以进行跳转,但不能携带参数到页面
	 */ 
	@RequestMapping( value = WELCOME)//		"/welcome"
	public ModelAndView welcome(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("welcome:controller");
		List<User> list = service.findAllUser(5);
		if(list != null){
			Iterator<User> it = list.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
			System.out.println("数据库已经连通");
		}
		//携带参数跳转
//		RedirectAttributes redirectAttributes
		return new ModelAndView("redirect:/welcome2");
	}
	
	@RequestMapping("/welcome2")
	public String welcome2(HttpServletRequest request,HttpServletResponse response){
		System.out.println("welcome2:controller");
		return "welcome";
	}
	/**
	 * 测试文件上传
	 */
	@RequestMapping( value = "fileupload1")
	@ResponseBody
	public void fileUpload(HttpServletRequest request,HttpServletResponse response) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();//创建磁盘工厂
		factory.setRepository(new File("C:/Users/ycl/uploadtemp/"));//设置临时文件夹
		ServletFileUpload upload = new ServletFileUpload(factory);//创建处理工具
		upload.setFileSizeMax(3*1024*1024);//设置最大上传文件大小
		List<FileItem> itemlist = upload.parseRequest(request);//接受全部内容
		if(itemlist != null && itemlist.size() > 0){
			IpTimeStamp ipts = new IpTimeStamp(request.getRemoteAddr());
			Iterator<FileItem> iter = itemlist.iterator();
			Map map = new HashMap();
			while(iter.hasNext()){
				FileItem item = iter.next();
				if(!item.isFormField()){//文件域
					File saveFile = null;
					InputStream in = null;
					OutputStream out = null;
					in = item.getInputStream();
					String random = ipts.getIpTimeRand();
					out = new FileOutputStream(new File("C:/Users/ycl/upload/"+random
							+"."+item.getName().substring(item.getName().lastIndexOf(".")+1)));
					map.put("picture", random+"."+item.getName().substring(item.getName().lastIndexOf(".")+1));
					byte data[] = new byte[1024];
					int temp = 0;
					while((temp = in.read(data,0,1024)) != -1){
						out.write(data);
					}
					in.close();
					out.close();
				}else{					//普通文本域
					String name = item.getName();//取得表单名称
					String value = item.getString();//取得表单内容
					map.put("name",value);
				}
			}
//			service.saveBaseInfomation(map); 
		}
	}
	
}










