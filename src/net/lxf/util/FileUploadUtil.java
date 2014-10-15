package net.lxf.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.lxf.util.IpTimeStamp;

public class FileUploadUtil {
	
	private HttpServletRequest request = null;//取得HttpServletRequest对象
	
	private List<FileItem> items= null;//保存全部上传文件
	
	private Map<String,List<String>> params = new HashMap<String,List<String>>();//保存所有的参数
	
	private Map<String,FileItem> files = new HashMap<String,FileItem>();//保存所有上传文件
	
	private int maxSize = 3*1024*1024;//默认上传文件的大小
	
	
	/**
	 * 构造,实例化FileUploadTools类
	 * @param request request对象
	 * @param max 最大上传文件限制
	 * @param tempDir 上传临时保存路径
	 */
	@SuppressWarnings("unchecked")
	public FileUploadUtil(HttpServletRequest request,int maxSize,String tempDir) throws Exception{
		this.request = request;
		DiskFileItemFactory factory = new DiskFileItemFactory();//创建磁盘工厂
		if(tempDir != null){//判断是否需要进行临时上传目录
			factory.setRepository(new File(tempDir));//设置临时文件保存目录
		}
		ServletFileUpload upload = new ServletFileUpload(factory);//创建处理工具
		if(maxSize>0){//如果给定了大小限制则使用新的大小
			this.maxSize = maxSize;
		}
		try {
			this.items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}//接受全部内容
		this.init();
	}
	
	/**
	 * 初始化参数，区分普通参数或上传文件
	 * author linxiaofan
	 * time 2014年9月15日  下午11:27:38
	 */
	private void init(){
		Iterator<FileItem> iter = this.items.iterator();/* 类似于表单的一行全部信息 */
		while(iter.hasNext()){
			FileItem item = iter.next();
			if(item.isFormField()){//普通的文本参数
				String name = item.getFieldName();
				String value = item.getString();//取得参数值
				List<String> temp = null;//保存内容
				if(this.params.containsKey(name)){//判断内容是否已经存放,比如复选框的情况
					temp = this.params.get(name);
				}else{
					temp = new ArrayList<String>();
				}
				temp.add(value);
				this.params.put(name, temp);
			}else{//file组件
				String newFileName = new IpTimeStamp(this.request.getRemoteAddr()).getTimeStamp() + "." + item.getName().split("\\.")[1];
				this.files.put(newFileName,item);
			}
		}
	}
	/**
	 * 根据参数名称取得全部内容
	 * author linxiaofan
	 * time 2014年9月15日  下午11:06:24
	 * @param name
	 * @return
	 */
	public String getParameter(String name){
		String ret = null;
		List<String> temp = this.params.get(name);
		if(null != temp){
			ret = temp.get(0);
		}
		return ret;
	}
	
	/**
	 * 根据参数名称取得一组参数内容
	 * author linxiaofan
	 * time 2014年9月15日  下午11:07:30
	 * @param name
	 * @return
	 */
	public String [] getParameterValues(String name){
		String ret[] = null;
		List<String> temp = this.params.get(name);
		if(temp != null){
			ret = temp.toArray(new String[]{});
		}
		return ret;
	}
	
	/**
	 * 取得全部的上传文件
	 * author linxiaofan
	 * time 2014年9月15日  下午11:09:31
	 * @return
	 */
	public Map<String,FileItem> getUploadFiles(){
		return this.files;
	}
	
	/**
	 * 自动保存全部上传文件，并将已经上传的文件的名称返回给调用出
	 * 当对象创建文成以后，文件与变量Map<String,FileItem> files已经被实例化赋予了需要的值，保存的新文件名称和待保存的文件域的全部信息
	 * author linxiaofan
	 * time 2014年9月15日  下午11:09:52
	 * @param saveDir	保存的位置
	 * @return
	 * @throws IOException
	 */
	public List<String> saveAll(String saveDir) throws IOException{	//交给调用处处理
		List<String> names = new ArrayList<String>();
		if(this.files.size()>0){
			File saveFile = null;
			InputStream input = null;
			OutputStream out = null;
			Set<String> keys = this.files.keySet();//取得新文件名称
			Iterator<String> iter = keys.iterator();
			while(iter.hasNext()){
				String tempName = iter.next();
				FileItem item = this.files.get(tempName);//取得新文件名对应的待保存的文件域信息
				String fileName = tempName;
				saveFile = new File(saveDir+fileName);//保存的文件全称
				names.add(fileName);//用于返回保存的文件名
				try {
					input = item.getInputStream();
					out = new FileOutputStream(saveFile);
					int temp = 0;
					byte data[] = new byte[512];
					while((temp = input.read(data,0,512)) != -1){
						out.write(data);
					}
				} catch (IOException e) {
					throw e;
				}finally{
					try {
						input.close();
						out.close();
					} catch (IOException e1) {
						throw e1;
					}
				}
			}
		}else{
			//没有文件域
		}
		return names;
	}

}