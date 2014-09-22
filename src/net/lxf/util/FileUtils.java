package net.lxf.util;

import java.io.File;

public class FileUtils {
	/**
	 * 创建目录
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-30 下午1:43:49
	 * @param file
	 */
	public static void createDirectory(File file) {
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public static void main(String args []){
		File file = new File("H:/linxiaofan");
		createDirectory(file);
	}
}
