package com.ruyicai.wap.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruyicai.wap.Global;
import com.ruyicai.wap.bean.WapNews;
import com.ruyicai.wap.rss.bean.Channel;
import com.ruyicai.wap.rss.bean.Image;
import com.ruyicai.wap.rss.bean.Items;
@RequestMapping("/rss")
@Controller
public class RssNewAction{
	private static final Logger logger = Logger.getLogger(RssNewAction.class);
	private static final ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	private static final String link = "http://wap.ruyicai.com/w/news/wapNewsList.jspx";
	private static final String linknew = "http://wap.ruyicai.com/w/news/wapNews.jspx";
	private static final String type="1";
	
	@RequestMapping(value = "/rssNews.jspx", method = RequestMethod.GET)
	public void createRssXMl(){
		//数据库读取新闻
		List<WapNews> wapNews = new ArrayList<WapNews>();
		wapNews = Global.wapNewsDao.selectWapNewsList("1", 0, 20);
		Channel channel = new Channel();
		Set<Image> setImages = new HashSet<Image>();
		Image image = new Image();
		image.setLink(link+"?type="+type);
		image.setUrl("http://wap.ruyicai.com/w/wap/images/ruyicai.png");
		setImages.add(image);
		Set<Items> setItems = new HashSet<Items>();
		channel.setLink(link+"?type="+type);
		channel.setTtl("30");
		channel.setPubDate(new Date());
		channel.setImage(setImages);
		for (int i = 0; i < wapNews.size(); i++) {
			WapNews news = wapNews.get(i);
			Items items = new Items();
			items.setTitle(news.getVol_title());
			items.setAuthor("如意彩 RUYICAI.COM");
			String content = news.getVol_content();
			if(content.length()<100){
				items.setDescription(content);
			}else{
				items.setDescription(news.getVol_content().substring(0, 100));
			}
			items.setGuid(linknew+"?id="+news.getId()+"&type="+type);
			items.setLink(linknew+"?id="+news.getId()+"&type="+type);
			items.setPubDate(news.getCreatetime());
			items.setLastBuildDate(news.getUpdatetime());
			setItems.add(items);
		}
		channel.setItems(setItems);
		//调用生成方法
		StringBuffer rssXmlString;
		try {
		rssXmlString = createRss(channel);
		//把得到的rssXmlString写入到XML文档里
		String str=String.valueOf(rssXmlString);
		logger.info("rssXML 信息组合完毕 准备写入xml文件");
		String path = rbint.getString("rssFilePath");
		logger.info("获取到写入地址"+path+"xwrss.xml");
		File txt=new File(path+"xwrss.xml");
		if(txt.exists()){
			txt.delete();
		}else{
		   logger.info("创建XML文件开始");
			txt.createNewFile();
		   logger.info("创建XML文件成功");
		}
		   byte bytes[]=new byte[1024];
		   bytes=str.getBytes("utf-8");  
		   int b=bytes.length;   
		   logger.info("rss源开始写入");
		   FileOutputStream fos=new FileOutputStream(txt);
		   fos.write(bytes,0,b);
		   fos.flush();
		   logger.info("rss源写入完毕...");
		   fos.close();
		   logger.info("rss源写入成功...");
		} catch (Exception e) {
			logger.debug("rss源 xml文件生成异常"+e.getMessage());
		}
    }
	
	
	
	/**
	 * 生成RSS
	 * @return String
	 * @throws Exception 
	 */
	public StringBuffer createRss(Channel channel) throws Exception{
		Set<Items> set=channel.getItems();
		if(set==null){
			throw new Exception("RSS的Item节点为空");
		}
		StringBuffer sb=new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb.append("<rss version=\"2.0\">\n");
		sb.append("<channel>\n");
		sb.append("<title>"+ channel.getTitle() +"</title>\n");
		sb.append("<description>"+ channel.getDescription() +"</description>\n");
		sb.append("<link><![CDATA["+ channel.getLink() +"]]></link>\n");
		sb.append("<language>"+ channel.getLanguage() +"</language>\n");
		sb.append("<copyright>"+ channel.getCopyright() +"</copyright>\n");
		sb.append("<generator>"+ channel.getGenerator() +"</generator>\n");
		Iterator iterator=set.iterator();
		while(iterator.hasNext()){
			Items item=(Items)iterator.next();
			sb.append("<item>\n");
			sb.append("<title>"+ item.getTitle() +"</title>\n");
			sb.append("<author>"+ item.getAuthor() +"</author>\n");
			sb.append("<description><![CDATA[ "+ item.getDescription() +"]]></description>\n");
			sb.append("<pubDate>"+ item.getPubDate() +"</pubDate>\n");
			sb.append("<lastBuildDate>"+ item.getLastBuildDate() +"</lastBuildDate>\n");
			sb.append("<link><![CDATA["+ item.getLink() +"]]></link>\n");
			sb.append("<guid><![CDATA["+ item.getGuid() +"]]></guid>\n");
			sb.append("</item>\n");
		}
		sb.append("</channel>\n");
		sb.append("</rss>\n");
		
		return sb;
	}
	
}
