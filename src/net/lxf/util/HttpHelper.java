package net.lxf.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HttpHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

	public static final String BASE_URL = "http://app.tcdai.com/android/action";
	
	public static final String BASECOMMON_URL = "http://192.168.5.145:8080/commonServer/mobile/";

	public static final String QUICKAPPLY_URL = BASECOMMON_URL + "quickApply";

	public static final String REGISTER_URL = BASECOMMON_URL + "register";

	public static final String LOGIN_URL = BASECOMMON_URL + "login";

	public static final String LOGOUT_URL = BASECOMMON_URL + "logout";

	public static final String BID_URL = BASECOMMON_URL + "bid";

	public static final String LOAN_URL = BASECOMMON_URL + "loan";

	public static final String LOANLIST_URL = BASECOMMON_URL + "loan/list";

	public static final String LOANDETAIL_URL = BASECOMMON_URL + "loan/detail";

	public static final String LENDLIST_URL = BASECOMMON_URL + "lend/list";

	public static final String LENDETAIL_URL = BASECOMMON_URL + "lend/detail";

	public static final String CONFIG_URL = BASECOMMON_URL + "config";

	public static final String BORROWACTION_URL = BASECOMMON_URL+ "borrowAction";

	public static final String LOANACTION_URL = BASECOMMON_URL + "loanAction";

	public static final String BORROWLOANDETAIL_URL = BASECOMMON_URL + "borrowLoan/detail";

	public static final String GETCITYBYNAME_URI = BASECOMMON_URL + "getCityByName";

	/**
	 * 请求数据
	 * 
	 * @param requrl
	 * @param params
	 * @return
	 */
	public static String getRequestResult(String requrl, String params) {
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
			con.setRequestProperty("Connection", "Keep-Alive");
			con.setRequestProperty("Charset", "UTF-8");
			dos = new DataOutputStream(con.getOutputStream());
			if (params != null) {
				// String param = URLEncoder.encode(params,"UTF-8");
				dos.write(params.getBytes());
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
			LOGGER.info("MalformedURLException：", e.fillInStackTrace());
		} catch (IOException e) {
			LOGGER.info("IOException：", e.fillInStackTrace());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return result.toString();

	}

	/**
	 * 请求数据
	 * 
	 * @param requrl
	 * @param params
	 * @return
	 */
	public static String getRequestResults(String requrl, String params) {
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
			LOGGER.info("MalformedURLException：", e.fillInStackTrace());
		} catch (IOException e) {
			LOGGER.info("IOException：", e.fillInStackTrace());
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return result.toString();

	}

	/**
	 * 获取远程访问IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void main(String[] args) {
		 
		// 相册列表
//		 String param = "{\"siteId\":4459,\"id\":22,\"token\":\"96e79218965e\"}";
		 System.out.println(getRequestResults("http://192.168.1.110:8080/hello",null));
		
	}

}
