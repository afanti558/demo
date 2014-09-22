package net.lxf.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadUtil {
	
	private HttpServletRequest request;
	
	private int maxSize = 3*1024*1024;//默认情况下
	
	private Map<String,List<String>> params = new HashMap<String,List<String>>();//保存所有参数
	
	private List<FileItem> items = null;
	
	private Map<String,FileItem> files = new HashMap<String,FileItem>();
	
	public FileUploadUtil(HttpServletRequest request,int maxSize,String tempDir) throws Exception{
		this.request = request;
		DiskFileItemFactory factory = new DiskFileItemFactory();//创建磁盘工厂
		if(tempDir != null){
			factory.setRepository(new File(tempDir));
		}
		ServletFileUpload upload = new ServletFileUpload(factory);//创建处理工具
		if(maxSize > 0){//用户自定义了，不在使用默认的
			this.maxSize = maxSize;
		}
		upload.setSizeMax(maxSize);
		try {
			this.items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		this.init();
	}
	
	public void init(){
		Iterator<FileItem> iter = this.items.iterator();
		IpTimeStamp ipts = new IpTimeStamp(this.request.getRemoteAddr());
		while(iter.hasNext()){
			FileItem item = iter.next();
			if(item.isFormField()){//普通表单
				String name = item.getName();//取得表单名称
				String value = item.getString();//取得表单内容
				List<String> temp = null;
				if(this.params.containsKey(name)){//判断内容是否已经存放
					temp = this.params.get(name);
				}else{
					temp = new ArrayList<String>();
				}
				temp.add(value);
				this.params.put(name, temp);
			}else{
				String fileName = ipts.getIpTimeRand()+"."+item.getName().split("\\.")[1];
				this.files.put(fileName,item);
			}
		}
	}
	
	/**
	 * 根据参数的名称取得参数的内容，将所有上传内容保存在items属性中
	 * @return String
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-30 下午2:04:16
	 * @param name
	 * @return
	 */
	public String getParameter(String name){
		
		return null;
	}
	
	/**
	 * 根据参数名称取得一组参数内容
	 * @return String[]
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-30 下午2:05:40
	 * @param name
	 * @return
	 */
	public  String[] getParameterValues(String name){
		return null;
	}
	
	/**
	 * 取得全部上传文件
	 * @return Map<String,FileItem>
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-30 下午2:07:00
	 * @return
	 */
	public Map<String,FileItem> getUploadFiles(){
		return null;
	}
	
	/**
	 * 自动保存全部的上传文件，并把已经上传的文件名称返回给调用处
	 * @return List<String>
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-30 下午2:08:01
	 * @param saveDir
	 * @return
	 */
	public List<String> saveAll(String saveDir){
		return null;
	}
}
