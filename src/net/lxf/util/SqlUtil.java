package net.lxf.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
	private static int n = 32;
	private static Connection con = null;
	/**
	 * 向数据库中批量插入数据
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@192.168.5.112:1521:rxbxgc","test1","test1");
//			getFileName("G:\\pic\\xfsj\\xfsj");
/*******************************************************/
			File file = new File("G:\\lu");
			File[] files = file.listFiles();
			for(int i = 0;i<files.length;i++){
				System.out.println(files[i]);
			}
			
/*******************************************************/
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量插入数据
	 * @param str
	 * @throws SQLException
	 */
	public static void getFileName(String str) throws SQLException{
		File file = new File(str);
		if(!file.isDirectory()){//不是文件夹
			String picpath = "pic/zdzp/"+str.substring(12).replace("\\", "/");
			
//			String sql = "insert into Rp_studio_worksimage(id,workid,image) values(STUDIOWORK_SEQ.NEXTVAL,790,?)";
			String sql = "insert into Rp_studio_happymoment(id,studioid,image,imageid) values (HAPPYMOMENT_SEQ.NEXTVAL,'4459',?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql) ;
			pstmt.setString(1, picpath);
			pstmt.setInt(2,n++);
			pstmt.execute();
			con.commit();
			pstmt.close();
//			System.out.println(     "pic/zdzp/"+str.substring(12).replace("\\", "/")     );
		}else{
			String [] str1 = file.list();
			for(int i = 0;i<str1.length;i++){
				getFileName(str+"\\"+str1[i]);
			}
		}
	}
}






