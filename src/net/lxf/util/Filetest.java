package net.lxf.util;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class Filetest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	/**
	 * 打印出给定目录下面的全部文件全路径
	 */
	@Test
	public void test() {
		File file = new File("G:\\pic");
		getFileFullName(file);
	}
	
	public void getFileFullName(File file){
		if(!file.isDirectory()){
			System.out.println(file.getAbsolutePath());
		}else if(file.isDirectory()){
			File[] f = file.listFiles();
			for(int i = 0;i<f.length;i++){
				getFileFullName(new File(f[i].getAbsolutePath()));
			}
		}
	}

}
