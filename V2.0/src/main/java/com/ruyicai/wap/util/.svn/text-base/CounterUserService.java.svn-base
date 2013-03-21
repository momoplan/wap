package com.ruyicai.wap.util;
/**
 * 
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
* 创建者：
* 创建日期：
* 修改记录：
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;


public class CounterUserService{ 
	private static final Logger logger = Logger.getLogger(CounterUserService.class);
	
	private static String DATE_XML_FIEL =  Thread.currentThread().getContextClassLoader().getResource("")+"counterUser.xml";
	private static Properties p = new Properties(); 
	
	
	public CounterUserService(){
		super();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jrtWAPSite.properties");    
		try {    
			p.load(inputStream);    
		} catch (IOException e1) {    
			e1.printStackTrace();    
		}    
		if(p.getProperty("CounterUserXml_file_type").equals("true")){
			DATE_XML_FIEL = p.getProperty("CounterUserXml_file");
		}else{
			DATE_XML_FIEL = DATE_XML_FIEL.replace("file:/", "");
		}
	}
	/**
	 * 用户登录
	 * @param request
	 * @param userName
	 * @param pass
	 * @return
	 */
	public static boolean login(HttpServletRequest request ,String userName , String pass  ){
		SAXBuilder sb=new SAXBuilder();//建立构造器
        Document doc;
		try {
			doc = sb.build(new FileInputStream(DATE_XML_FIEL));
			//读入指定文件
	        Element root=doc.getRootElement();//获得根节点
	        List countToTypeList=root.getChildren();//获取整个用户列表
	        for(int i=countToTypeList.size()-1;i>=0;i--) {
	            Element user=(Element)countToTypeList.get(i);//取得节点实例
	            if(userName.equals(user.getChild("username").getText())&&pass.equals(user.getChild("pass").getText())){
	            	//存储session
	            	request.getSession().setAttribute("Channel", user.getChild("channel").getText());
	            	request.getSession().setAttribute("userName", userName);
	            	request.setAttribute("channelId", user.getChild("channel").getText());
	            	return true;
	            }
	        }
		}  catch (Exception e) {
			logger.error("渠道账号登录时出现错误:"+e.getMessage());
			return false;
		}
		return false;
	}
	/**
	 * 新建一个渠道用户
	 * @param request
	 * @param userName
	 * @param pass
	 * @param channel 渠道编号
	 * @return
	 */
	public boolean register(HttpServletRequest request, String userName, String pass ,String channel) {
		SAXBuilder sb=new SAXBuilder();//建立构造器
        Document doc;
		try {
			doc = sb.build(new FileInputStream(DATE_XML_FIEL));
			//读入指定文件
	        Element root=doc.getRootElement();//获得根节点
	        List countToTypeList=root.getChildren();//获取整个用户列表
	        for(int i=countToTypeList.size()-1;i>=0;i--) {
	            Element user=(Element)countToTypeList.get(i);//取得节点实例
	            if(userName.equals(user.getChild("username").getText())){ //判断 是否存在重名的用户
	            	return false;
	            }
	        }
	        //创建节点person   
    	    Element element = new Element("User");   
    	    //给person节点添加子节点并赋值   
    	    element.addContent(new Element("username").setText(userName));   
    	    element.addContent(new Element("pass").setText(pass));
    	    element.addContent(new Element("channel").setText(channel)); 
    	    root.addContent(element);
    	    
	        XMLOutputter output = new XMLOutputter();   
	        output.output(doc,new FileOutputStream(DATE_XML_FIEL));
	        
	        //存储session
        	request.getSession().setAttribute("Channel", channel);
        	request.setAttribute("channelId", channel);
        	return true;
	        
	        
		}  catch (Exception e) {
			logger.error("创建渠道账号时出现错误:"+e.getMessage());
			return false;
		}
	}
	


}
