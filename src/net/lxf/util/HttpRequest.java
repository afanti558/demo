package net.lxf.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//import com.sun.imageio.plugins.common.InputStreamAdapter;

public final class HttpRequest {
	public static String getRequestResults(String requrl,String params){
		URL url = null;
		HttpURLConnection con = null;
		DataOutputStream dos = null;
		InputStream is = null;
		StringBuffer result = new StringBuffer();
		try {
			url = new URL(requrl);
			con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(20 * 1000);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestMethod("POST");
			con.setRequestProperty("Connection", "Keep-Alive");// 持久链接
			con.setRequestProperty("Charset", "UTF-8");
			dos = new DataOutputStream(con.getOutputStream());
			if (params != null) {
				String param = "param=" + params;
				dos.write(param.getBytes("UTF-8"));
				dos.flush();
			}
			is = con.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			is.close();
			dos.close();
		} catch (MalformedURLException e) {
			 e.fillInStackTrace();
		} catch (IOException e) {
			 e.fillInStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return result.toString();
	}
	
	/**
	 * 方法测试
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-4 下午1:28:37
	 * @param args
	 */
	public static void main(String[] args) {
		String param = "{\"name\":\"fuck!\"}";
		System.out.println(getRequestResults("http://192.168.1.110:8080/springmvcdemo/utiltest",param));
	}
}








