package com.ruyicai.wap.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruyicai.wap.Global;
import com.ruyicai.wap.bean.WapNews;
@RequestMapping("/news")
@Controller
public class NewsController {
	private static final Logger logger = Logger.getLogger(NewsController.class);
	
	@RequestMapping("/wapNewsList.jspx")
	public String wapNewsList(
			@RequestParam(value ="type",defaultValue="2") String type,
			@RequestParam(value ="startLine",defaultValue="0") int startLine,
			@RequestParam(value ="endLine",defaultValue="10") int endLine,
			@RequestParam(value ="nowPage",defaultValue="1") int nowPage,
			Model model){
		List<WapNews> wapNews = new ArrayList<WapNews>();
		wapNews = selectWapNewsList(type, startLine, endLine);
		int count = selectWapNeswCount(type);
		int maxLine = 10;
		int totalPage = count%maxLine==0 ? count/maxLine : count/maxLine +1;
		boolean upFlag = false;
		boolean nextFlag = false;
		logger.info("count:"+count+"totalPage:"+totalPage+"nowPage:"+nowPage+"type:"+type+"startLine:"+startLine+"endLine:"+endLine);
		if(nowPage>1){
			upFlag = true;
		}
		if(nowPage<totalPage){
			nextFlag= true;
		}
		String typeName = getTypeName(type);
		model.addAttribute("startLine", startLine);
		model.addAttribute("maxLine", maxLine);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("upFlag", upFlag);
		model.addAttribute("nextFlag", nextFlag);
		model.addAttribute("wapNews", wapNews);
		model.addAttribute("type", type);
		model.addAttribute("typeName", typeName);
		return "wap/news/wapNewsList";
	}
	public List<WapNews> selectWapNewsList(String type,int startLine,int endLine){
		List<WapNews> wapNews = new ArrayList<WapNews>();
		wapNews = Global.wapNewsDao.selectWapNewsList(type, startLine, endLine);
		return wapNews;
	}
	public String getTypeName(String type){
		String typeName = "";
		if("1".equals(type)){
			typeName = "彩民趣闻";
		}else if("2".equals(type)){
			typeName = "专家推荐";
		}else if("3".equals(type)){
			typeName = "足彩天地";
		}else if("4".equals(type)){
			typeName = "如意活动";
		}else if("6".equals(type)){
			typeName = "如意彩新闻";
		}else{
			typeName = "新闻资讯";
		}
		return typeName;
	}
	
	@RequestMapping("/wapNews.jspx")
	public String wapNews(
		@RequestParam(value="id",defaultValue="") String id,
		@RequestParam(value="type",defaultValue="2") String type,
		Model model){
		WapNews news = new WapNews();
		news = selectWapNews(id);
		String updatetime = news.getUpdatetime();
		updatetime = updatetime.substring(0, 10);
		news.setUpdatetime(updatetime);
		String typeName = getTypeName(type);
		model.addAttribute("type", type);
		model.addAttribute("typeName", typeName);
		model.addAttribute("news", news);
		return "wap/news/wapNews";
	}
	public WapNews selectWapNews(String id){
		WapNews news = new WapNews();
		news = Global.wapNewsDao.selectWapNews(id);
		return news;
	}
	
	public int selectWapNeswCount(String type){
		int count = 0;
		count = Global.wapNewsDao.selectWapNewsCount(type);
		return count; 
	}
}
