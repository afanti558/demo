package net.lxf.web.fileupload.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lxf.util.FileUploadUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileuploadController {

	@RequestMapping("/upload")
	public String upload(){
		return "fileupload";
	}
	
	/**
	 * 
	 * author linxiaofan
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/upload/fileupload")
	@ResponseBody
	public void fileupload(HttpServletRequest req, HttpServletResponse resp) {
		String temPath = req.getSession().getServletContext().getRealPath("/")+"uploadtemp"+File.separator;
		String path = req.getSession().getServletContext().getRealPath("/")+"upload"+File.separator;
		FileUploadUtil fileUploadUtil = null;
		try {
			fileUploadUtil = new FileUploadUtil(req, 0, temPath);
			fileUploadUtil.saveAll(path);
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		
		
//		resp.setContentType("text/html;charset=utf-8");
//		resp.setCharacterEncoding("gbk");
//		PrintWriter out;
//		try {
//			out = resp.getWriter();
//			out.write("上传成功");
 //		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
