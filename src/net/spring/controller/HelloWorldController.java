package net.spring.controller;

import static net.lxf.util.WebURLMappingConstant.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.lxf.util.DateUtils;
import net.lxf.util.IpTimeStamp;
import net.spring.entity.User;
import net.spring.service.Service;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Controller  //表示这是一个控制器类组件，这些注解注入都需要在springmvc的配置文件中配置，还有@Service和@Repository
public class HelloWorldController {

	@Autowired
	private Service service;//注入接口 
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	private String path = null;//跳转页面名称
	
	
	/**
	 * 基本功能的实现
	 * @return ModelAndView
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-29 下午4:06:59
	 * @param request
	 * @return
	 */
	@RequestMapping( value = WELCOME)
	public ModelAndView helloWorld(HttpServletRequest request) {
		LOGGER.info("welcome test! (" + request.getRemoteAddr()+ ")::" + DateUtils.getSysDate());
		
		Map<String,List<User>> map = new HashMap<String,List<User>>();
		List<User> list = service.findAllUser(5);
//		List<User> list = null;
		if(list != null){
			map.put("userlist", list);
		}
		this.path = "welcome";
		return new ModelAndView(path,map);
	}
	
	/* 首页  */
	@RequestMapping( value = ROOT)
	public ModelAndView mainpage(HttpServletRequest request,HttpServletResponse response) {
		LOGGER.info("welcome home! (" + request.getRemoteAddr()+ ")::" + DateUtils.getSysDate());
		this.path = "verifycode";
		return new ModelAndView(path); 
	}
	
	/**
	 * 用文件域上传文件
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-4 上午11:02:46
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping( value = FILEUPLOAD)
	@ResponseBody
	public void fileUpload(HttpServletRequest request,HttpServletResponse response) throws Exception {
		LOGGER.info("welcome fileupload! (" + request.getRemoteAddr()+ ")::" + DateUtils.getSysDate());
		
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
			service.saveBaseInfomation(map); 
		}
	}
	
	/**
	 * http请求模拟
	 * @return String
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-4 上午11:46:25
	 * @return
	 */
	@RequestMapping( value = "utiltest")
	@ResponseBody
	public String utilTest(@RequestParam(value = "param") String jsonstr){
		JSONObject entity;//保存所有参数
		String str = null;
		try {
			entity = new JSONObject(jsonstr);
			str = entity.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
		}
			return "this is result!";
	}
	
	/**
	 * 生成验证码
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-4 下午3:55:48
	 * @param request
	 * @param response
	 */

	@RequestMapping("generateImage")
	@ResponseBody
	public void randomImage(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("randomImage!");
		BufferedImage img = new BufferedImage(70, 28, BufferedImage.TYPE_INT_RGB);
		// 得到该图片的绘图对象
		Graphics g = img.getGraphics();
		Random r = new Random();
		Color c = new Color(200, 150, 255);
		g.setColor(c);
		// 填充整个图片的颜色
		g.fillRect(0, 0, 70, 28);
		// 向图片中输出数字和字母
		StringBuffer sb = new StringBuffer();
		char[] ch = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		int index, len = ch.length;
		for (int i = 0; i < 4; i++) {
			index = r.nextInt(len);
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));// 输出的字体和大小
			g.drawString("" + ch[index], (i * 15) + 3, 18);// 写什么数字，在图片的什么位置画
			sb.append(ch[index]);
		}
		request.getSession().setAttribute("randomKey", sb.toString());
		try {
			OutputStream out = response.getOutputStream();
			ImageIO.write(img, "JPG", out);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException:", e.fillInStackTrace());
		}
	}
	
	/**
	 * 保存表单提交过来的个人信息
	 * @return void
	 * @description 
	 * @author linxiaofan
	 * @time 2014-9-5 上午9:44:03
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="insert")
	public void saveBaseInfomation(HttpServletRequest request,HttpServletResponse response){
		LOGGER.info("welcome saveBaseInfomation! (" + request.getRemoteAddr()+ ")::" + DateUtils.getSysDate());
		Map map = new HashMap();
		map.put("name",request.getParameter("name"));
		map.put("sex",request.getParameter("sex"));
		map.put("age",request.getParameter("age"));
		map.put("addr",request.getParameter("addr"));
		service.saveBaseInfomation(map);
	}

/*******************验证码开始************************/
	// 验证码图片的宽度。         
    private int width = 60;         
    // 验证码图片的高度。         
    private int height = 20;         
    // 验证码字符个数         
    private int codeCount = 4;   
    //**********
    private int x = 0;       
    // 字体高度         
    private int fontHeight;
    //********
    private int codeY; 
    
    char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',         
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',         
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };         
    /**
     * 初始化验证图片属性       
     * @return void
     * @description 
     * @author linxiaofan
     * @time 2014-9-11 下午4:02:22
     * @throws ServletException
     */
    public void initxuan() throws ServletException {         
        // 从web.xml中获取初始信息         
        // 宽度         
        String strWidth ="80";         
        // 高度         
        String strHeight ="30";         
        // 字符个数         
        String strCodeCount = "4";         
        // 将配置的信息转换成数值         
        try {         
            if (strWidth != null && strWidth.length() != 0) {         
                width = Integer.parseInt(strWidth);         
            }         
            if (strHeight != null && strHeight.length() != 0) {         
                height = Integer.parseInt(strHeight);         
            }         
            if (strCodeCount != null && strCodeCount.length() != 0) {         
                codeCount = Integer.parseInt(strCodeCount);         
            }         
        } catch (NumberFormatException e) {  
        	e.printStackTrace();
        } 
        //*********
        x = width / (codeCount + 1);         
        fontHeight = height - 2;         
        codeY = height - 4;         
    }  
    
  /**
   * 
   * @return void
   * @description 
   * @author linxiaofan
   * @time 2014-9-11 下午4:02:34
   * @param req
   * @param resp
   * @throws ServletException
   * @throws java.io.IOException
   */
    @RequestMapping(value="xuan/verifyCode",method=RequestMethod.GET)
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException { 
        initxuan();
        // 定义图像buffer         
        BufferedImage buffImg = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);         
        Graphics2D g = buffImg.createGraphics();         
        // 创建一个随机数生成器类         
        Random random = new Random();         
        // 将图像填充为白色         
        g.setColor(Color.WHITE);         
        g.fillRect(0, 0, width, height);         
        // 创建字体，字体的大小应该根据图片的高度来定。         
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);         
        // 设置字体。         
        g.setFont(font);         
        // 画边框。         
        g.setColor(Color.BLACK);         
        g.drawRect(0, 0, width - 1, height - 1);         
        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。         
        g.setColor(Color.BLACK);         
        for (int i = 0; i < 10; i++) {         
            int x = random.nextInt(width);         
            int y = random.nextInt(height);         
            int xl = random.nextInt(12);         
            int yl = random.nextInt(12);         
            g.drawLine(x, y, x + xl, y + yl);         
        }         
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。         
        StringBuffer randomCode = new StringBuffer();         
        int red = 0, green = 0, blue = 0;         
        // 随机产生codeCount数字的验证码。         
        for (int i = 0; i < codeCount; i++) {         
            // 得到随机产生的验证码数字。         
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);         
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。         
            red = random.nextInt(255);         
            green = random.nextInt(255);         
            blue = random.nextInt(255);         
            // 用随机产生的颜色将验证码绘制到图像中。         
            g.setColor(new Color(red, green, blue));         
            g.drawString(strRand, (i + 1) * x, codeY);         
            // 将产生的四个随机数组合在一起。         
            randomCode.append(strRand);         
        }         
        // 将四位数字的验证码保存到Session中。         
        HttpSession session = req.getSession();         
        session.setAttribute("validateCode", randomCode.toString());         
        // 禁止图像缓存。         
        resp.setHeader("Pragma", "no-cache");         
        resp.setHeader("Cache-Control", "no-cache");         
        resp.setDateHeader("Expires", 0);         
        resp.setContentType("image/jpeg");         
        // 将图像输出到Servlet输出流中。         
        ServletOutputStream sos = resp.getOutputStream();         
        ImageIO.write(buffImg, "jpeg", sos);         
        sos.close();         
    }         
	
	/**
	 * @return void
	 * @description 验证码验证
	 * @author linxiaofan
	 * @time 2014-9-11 下午3:56:12
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
     @RequestMapping(value="resultServlet/validateCode",method=RequestMethod.POST)
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {         
         response.setContentType("text/html;charset=utf-8");         
         String validateC = (String) request.getSession().getAttribute("validateCode");//对比用的验证码       
         String veryCode = request.getParameter("c");//输入的验证码      
         PrintWriter out = response.getWriter();         
         if(veryCode==null||"".equals(veryCode)){         
             out.println("验证码为空");         
         }else{         
             if(validateC.equals(veryCode)){         
                 out.println("验证码正确");         
             }else{         
                 out.println("验证码错误");         
             }         
         }         
         out.flush();         
         out.close();         
     }         
 /*******************验证码开始结束************************/
}










