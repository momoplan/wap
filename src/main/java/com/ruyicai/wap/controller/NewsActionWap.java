package com.ruyicai.wap.controller;

/**
 * NewsActionWap 新闻操作
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
 * 创建者：
 * 创建日期：
 * 修改记录：
 */
import static com.ruyicai.wap.Global.rbint;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruyicai.wap.Global;
import com.ruyicai.wap.bean.Category;
import com.ruyicai.wap.bean.Commentary;
import com.ruyicai.wap.bean.News;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/newsAction")
@Controller
public class NewsActionWap{
	private static final Logger logger = Logger.getLogger(NewsActionWap.class);

	public static Category getCategory(String nId) {
		String type = "";
		String categoryId = "0";
		if (!"".equals(nId) && nId != null) {
			if ("CaiPiaoJiangTang".equals(nId)) {
				type = "彩票讲堂";
				categoryId = "8";
			} else if ("CaiShiXinWen".equals(nId)) {
				type = "G3彩新闻";
				categoryId = "9";
			} else if ("MeiTiBaoDao".equals(nId)) {
				type = "媒体报道";
				categoryId = "6";
			} else if ("TouTiaoXinWen".equals(nId)) {
				type = "头条新闻";
				categoryId = "10";
			} else if ("ReDianGongGao".equals(nId)) {
				type = "G3彩头条";
				categoryId = "11";
			} else if ("ZiXunXinWen".equals(nId)) {
				type = "资讯新闻";
				categoryId = "12";
			} else if ("TCXinWen".equals(nId)) {
				type = "体彩新闻";
				categoryId = "13";
			} else if ("ZhongJiang".equals(nId)) {
				type = "最新中奖";
				categoryId = "14";
			} else if ("FuCaiXinWen".equals(nId)) {
				type = "福彩新闻";
				categoryId = "15";
			} else if ("TianYaTouTiao".equals(nId)) {
				type = "天涯头条";
				categoryId = "17";
			} else if ("ZuCaiZiXun".equals(nId)) {
				type = "足彩资讯";
				categoryId = "19";
			} else if ("ZhuanJiaZhanJi".equals(nId)) {
				type = "专家战绩";
				categoryId = "20";
			} else if ("ZhuanJiaDuanxin".equals(nId)) {
				type = "专家短信";
				categoryId = "21";
			} else if ("ZhuanJiaHeMai".equals(nId)) {
				type = "专家合买";
				categoryId = "22";
			} else if ("JieRi".equals(nId)) {
				type = "节日";
				categoryId = "23";
			} else if ("ZuCaiFenXi".equals(nId)) {
				type = "足彩分析";
				categoryId = "25";
			} else if ("ZuCaiPeiLv".equals(nId)) {
				type = "足彩赔率";
				categoryId = "24";
			}

		}
		Category cate = new Category();
		cate.setId(categoryId);
		cate.setType(nId);
		cate.setCategoryName(type);
		return cate;
	}
	/**
	 * @param request
	 *            
	 * @param startLine
	 *            页数
	 * @param lineNumber
	 *            每页显示记录数
	 * @param 如果
	 *            startLine 为0 lineNumber 为2 则显示 最新的2条记录
	 * @param return Map 的值对 例子:（key="ZiXunXinWen<资讯新闻>", value= List<News>） key
	 *        是XML中的栏目ID value 存储该栏目下的所有内容
	 */

	private static String[] indexNewsCategoryId = { "TCXinWen", "FuCaiXinWen",
			"TouTiaoXinWen", "ZhongJiang", "ZuCaiZiXun", "ZhuanJiaDuanxin",
			"ZhuanJiaHeMai", "JieRi", "ZuCaiFenXi", "ZuCaiPeiLv" };

	public Map<String, Object> getNewsTypeAndContentListToIndexInMap(
			HttpServletRequest request) {

		int startLine = Integer.parseInt(rbint.getString("startLineToIndex"));
		int lineNumber = Integer.parseInt(rbint.getString("lineNumberToIndex"));

		Map<String, Object> ntll = new HashMap<String, Object>();
		for (String str : indexNewsCategoryId) {
			ntll.put(str, getNewsListLogic(request, str, startLine, lineNumber));
		}
		String chanel = WapUtil.getChannelId(request);
		ntll.put("ZiXunXinWen", Global.newsDAO.getNewsByChannelId(chanel,"12", 0, 5));
		ntll.put("TianYaTouTiao", Global.newsDAO.getNewsByChannelId(chanel,"17", 0, 2));
		ntll.put("HLXKTouTiao", Global.newsDAO.getNewsByChannelId(chanel,"50", 0, 2));
		return ntll;
	}
	/**
	 * 获取指定新闻栏目ID的列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping(value="/getNewsList.jspx",method=RequestMethod.GET)
	public String getNewsList(HttpServletRequest request, HttpServletResponse response) {
		try {
			int lineNumber = 20;
			String nId = request.getParameter("nId") == null ? "" : request
					.getParameter("nId");

			int startPage = Integer
					.parseInt(request.getParameter("startPage") == null ? "0"
							: request.getParameter("startPage"));
			//获取新闻信息
			Category category = getCategory(nId);
			//新闻栏目ID
			String categoryId = category.getId();
			request.setAttribute("ListType", category.getCategoryName());
			String chanel = WapUtil.getChannelId(request);
           //根据渠道查询新闻信息
			List<News> list = Global.newsDAO.getNewsByChannelId(chanel,categoryId,
					startPage * lineNumber, lineNumber);
			int count = Global.newsDAO.getCountNewsByChannelId(chanel);
			int maxPage = count / lineNumber;
			if (count % lineNumber != 0) {
				count++;
			}
			request.setAttribute("MaxLine", maxPage);
			request.setAttribute("newsList", list);
			request.setAttribute("startPage", startPage);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageUtil.NAW_ExceptionMsg);
			logger.error("查看开奖信息时出现错误:" + e.getMessage());
			return "wap/wapindex";
		}
		return "wap/news/newsList";
	}

	/**
	 * 获取新闻的详细记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getNewsContent.jspx",method=RequestMethod.GET)
	public String getNewsContent(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 获取指定栏目的ID条件
			String nId = request.getParameter("newsId");
			// 获取指定栏目中的指定ID的文章
			String id = request.getParameter("id");
			News nb = Global.newsDAO.getNewsContent(id);
			request.setAttribute("id", id);
			request.setAttribute("nId", nId);
			request.setAttribute("nb", nb);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageUtil.NAW_ExceptionMsg);
			logger.error("查看新闻的详细记录出现错误:" + e.getMessage());
			return "wap/wapindex";
		}
		return "wap/news/newsContent";
	}

	/**
	 * 返回指定栏目的所有所有新闻的列表
	 * 
	 * @param id
	 *            对应匹配 news标签的id 获得指定栏目的整个列表
	 * @param startLine
	 *            开始的记录
	 * @param lineNumber
	 *            表示指定获取记录的数量 0 为不限制
	 */
	public static List<News> getNewsListLogic(HttpServletRequest request,
			String nId, int startLine, int lineNumber) {
		Category category = getCategory(nId);
		request.setAttribute("ListType", category.getCategoryName());
		List<News> list = Global.newsDAO.getNewsList(category.getId(),startLine, lineNumber);
		if (list != null)
			return list;
		else
			return new LinkedList<News>();
	}

	// ----------------------------------

	/**
	 * 实现新闻的分类和分页显示
	 * 
	 * @param request
	 * @param nId
	 * @param startLine
	 * @param lineNumber
	 * @param flag
	 * @return
	 * @throws FileNotFoundException
	 * @throws JDOMException
	 * @throws IOException
	 */
	public List getNewsListByCaiZhong(HttpServletRequest request, String categoryId,
			int startLine, int lineNumber, String flag)
			throws FileNotFoundException, IOException {
		List newsList = new ArrayList();
		News nb;
		News news;
		String type = "";
		if("38".equals(categoryId)){
			type = "双色球";
		}else if("39".equals(categoryId)){
			type = "福彩3D";
		}else if("40".equals(categoryId)){
			type = "大乐透";
		}else if("41".equals(categoryId)){
			type = "排列三";
		}else if("42".equals(categoryId)){
			type = "七星彩";
		}else if("43".equals(categoryId)){
			type = "七乐彩";
		}else if("44".equals(categoryId)){
			type = "胜负彩";
		}else if("45".equals(categoryId)){
			type = "排列五";
		}else if("48".equals(categoryId)){
			type = "彩民故事";
		}else if("49".equals(categoryId)){
			type = "公告信息";
		}
		if (flag.equals("index")) {// 首页各取3条

			return Global.newsDAO
					.getNewsList(categoryId, startLine, lineNumber);
		} else if (flag.equals("page")) {// wap分页是取出全部数据再分页
			// newsPageList = Global.newsDao.getNewsPageByCaizhong(categoryId);

			request.setAttribute("ListType", type);
			return Global.newsDAO
					.getNewsList(categoryId, startLine, lineNumber);

		}
		return newsList;
	}

	/**
	 * 实现新闻的分类和分页显示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getNewsInfo.jspx",method=RequestMethod.GET)
	public String getNewsInfo(HttpServletRequest request, HttpServletResponse response) {
		String categoryId = request.getParameter("categoryId");
		String flag = request.getParameter("flag");
		List newsList = new ArrayList();
		int newscount =0;
		Map map = new HashMap();
		try {
			int startPage = Integer
					.parseInt(request.getParameter("startPage") == null ? "0"
							: request.getParameter("startPage"));
			int lineNumber = 0;// Integer.parseInt(p.getProperty("lineNumberToNewsLine"));
			if ("index".equals(flag)) {
				lineNumber = 3;
				List shuangseqiu = getNewsListByCaiZhong(request,
						"38", startPage, lineNumber, flag);

				List fucai3d = getNewsListByCaiZhong(request, "39",
						startPage, lineNumber, flag);

				List daletou = getNewsListByCaiZhong(request, "40",
						startPage, lineNumber, flag);

				List pailiesan = getNewsListByCaiZhong(request, "41",
						startPage, lineNumber, flag);

				List qixingcai = getNewsListByCaiZhong(request, "42",
						startPage, lineNumber, flag);

				List qilecai = getNewsListByCaiZhong(request, "43",
						startPage, lineNumber, flag);

				List shengfucai = getNewsListByCaiZhong(request, "44",
						startPage, lineNumber, flag);
				List pailiewu = getNewsListByCaiZhong(request, "45",
						startPage, lineNumber, flag);

				request.setAttribute("shuangseqiu", shuangseqiu);
				request.setAttribute("fucai3d", fucai3d);
				request.setAttribute("daletou", daletou);
				request.setAttribute("pailiesan", pailiesan);
				request.setAttribute("qixingcai", qixingcai);
				request.setAttribute("qilecai", qilecai);
				request.setAttribute("shengfucai", shengfucai);
				request.setAttribute("pailiewu", pailiewu);

				return "wap/news/newsSortList";

			} else {
				lineNumber = 20;
				newsList = getNewsListByCaiZhong(request, categoryId, startPage,
						lineNumber, flag);
				newscount = Global.newsDAO.getNewsCount(categoryId);
				request.setAttribute("pageTitle", "专家荐号");
				request.setAttribute("newsList", newsList);
				request.setAttribute("startPage", startPage);
				request.setAttribute("lineNumber", lineNumber);
				request.setAttribute("MaxLine", (newscount/lineNumber)*lineNumber);
				request.setAttribute("flag", flag);
				return "wap/news/newsPageList";
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageUtil.NAW_ExceptionMsg);
			logger.error("查看新闻时出现错误:" + e.getMessage());
			return "wap/wapindex";
		}

	}

	/**
	 * 实现新闻内容的显示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getNewsContentById.jspx",method=RequestMethod.GET)
	public String getNewsContentById(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String categoryId = request.getParameter("categoryId");
			String id = request.getParameter("id");
			int start = Integer.parseInt(request.getParameter("start"));
			Date date = new Date();
			String nowDate = DateFormat.getDateTimeInstance().format(date);
			int end = 2;
			News nb = Global.newsDAO.getNewsContent(id);
			List list = getNewsNextList(nowDate, categoryId, start, end);
			request.setAttribute("newsNextList", list);
			request.setAttribute("id", id);
			request.setAttribute("categoryId", categoryId);
			request.setAttribute("nb", nb);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", MessageUtil.NAW_ExceptionMsg);
			logger.error("查看新闻的详细记录出现错误:" + e.getMessage());
			return "wap/wapindex";
		}
		return "wap/news/newsContentList";
	}

	/**
	 * 查询当前新闻后面要显示的新闻
	 * 
	 * @param pubDate
	 * @param nId
	 * @param start
	 * @param end
	 * @return
	 */
	private List<News> getNewsNextList(String pubDate, String categoryId, int start,
			int end) {
		String type = "";
		if("38".equals(categoryId)){
			type = "双色球";
		}else if("39".equals(categoryId)){
			type = "福彩3D";
		}else if("40".equals(categoryId)){
			type = "大乐透";
		}else if("41".equals(categoryId)){
			type = "排列三";
		}else if("42".equals(categoryId)){
			type = "七星彩";
		}else if("43".equals(categoryId)){
			type = "七乐彩";
		}else if("44".equals(categoryId)){
			type = "胜负彩";
		}else if("45".equals(categoryId)){
			type = "排列五";
		}else if("48".equals(categoryId)){
			type = "彩民故事";
		}else if("49".equals(categoryId)){
			type = "公告信息";
		}
		return Global.newsDAO.getNewsList(categoryId, start, end);
	}
	
	/**
	 * 返回指定栏目的所有所有新闻的列表
	 * 
	 * @param id
	 *            对应匹配 news标签的id 获得指定栏目的整个列表
	 * @param startLine
	 *            开始的记录
	 * @param lineNumber
	 *            表示指定获取记录的数量 0 为不限制
	 */
	public static List<News> getHLXKNewsList(
			String nId, int startLine, int lineNumber) {
		Category category = getCategory(nId);
		List<News> list = Global.newsDAO.getNewsList(category.getId(),startLine, lineNumber);
		if (list != null)
			return list;
		else
			return new LinkedList<News>();
	}
	
	/**
	 * 添加新闻评论
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/insertNewsCommentary.jspa")
	public String insertNewsCommentary(HttpServletRequest request,
			HttpServletResponse response) {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userName = tuserInfoBean.getUserName();
		String content = request.getParameter("content")==null ? "" : (String)request.getParameter("content");
		String newsid = request.getParameter("newsid")==null ?"" : (String)request.getParameter("newsid"); 
		logger.info("添加新闻评论：userName:"+userName+"content:"+content+"newsid:"+newsid);
		if("".equals(content)||content == null){
			request.setAttribute("message", "评论内容不能为空！");
			return "wap/news/commentaryError";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String createtime = dateFormat.format(date);
		logger.info("新闻评论参数：userName="+userName+"newsid="+newsid+"content="+content+"createtime="+createtime);
		Commentary commentary =new Commentary();
		commentary.setMobileid(userName);
		commentary.setNewsid(Integer.parseInt(newsid));
		commentary.setContent(content);
		commentary.setCreatetime(createtime);
		int result = Global.newsDAO.insertNewsCommentary(commentary);
		request.setAttribute("message", "评论成功！等待审核...");
		return "wap/news/commentaryError";
	}
	
	/**
	 * 查询新闻评论内容(管理)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectNewsCommentary.jspx",method=RequestMethod.GET)
	public String selectNewsCommentary(HttpServletRequest request,
			HttpServletResponse response) {
		String state = request.getParameter("state")==null ? "1" : (String)request.getParameter("state");
		String contentstate = request.getParameter("contentstate")==null ?"1" : (String)request.getParameter("contentstate");
		String startPage = request.getParameter("startPage") == null ? "0" :request.getParameter("startPage");
		String lineNumber = request.getParameter("lineNumber") == null ? "10" :request.getParameter("lineNumber");	

		logger.info("查询新闻评论：state:"+state+"contentstate:"+contentstate);
		List<Commentary>  commentaries = Global.newsDAO.selectNewsCommentary(Integer.parseInt(state), Integer.parseInt(contentstate), Integer.parseInt(startPage), Integer.parseInt(lineNumber));
		int count = Global.newsDAO.selectNewsCommentaryCount(Integer.parseInt(state), Integer.parseInt(contentstate));
		int maxLine = (count%10) == 0 ? (count/10) : (count/10) + 1;
		 logger.info("分页信息参数：startPage:"+startPage+",lineNumber:"+lineNumber+",maxLine:"+maxLine+",count:"+count);

		request.setAttribute("commentaries", commentaries);
		request.setAttribute("startPage", Integer.parseInt(startPage));
		request.setAttribute("lineNumber", Integer.parseInt(lineNumber));
		request.setAttribute("contentstate", Integer.parseInt(contentstate));
		request.setAttribute("state", Integer.parseInt(state));
		request.setAttribute("MaxLine", maxLine);
		return "wap/news/commentaryList";
	}
	/**
	 * 查询新闻评论内容(用户)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectNewsCommentaryList.jspx",method=RequestMethod.GET)
	public String selectNewsCommentaryList(HttpServletRequest request,
			HttpServletResponse response) {
		String state = request.getParameter("state")==null ? "1" : (String)request.getParameter("state");
		String contentstate = request.getParameter("contentstate")==null ?"0" : (String)request.getParameter("contentstate");
		String newsId = request.getParameter("newsId")==null ?"" : (String)request.getParameter("newsId");

		String startPage = request.getParameter("startPage") == null ? "0" :request.getParameter("startPage");
		String lineNumber = request.getParameter("lineNumber") == null ? "10" :request.getParameter("lineNumber");	

		logger.info("查询新闻评论：state:"+state+"contentstate:"+contentstate);
		List<Commentary>  commentaries = Global.newsDAO.selectNewsCommentary(Integer.parseInt(newsId),Integer.parseInt(state), Integer.parseInt(contentstate), Integer.parseInt(startPage), Integer.parseInt(lineNumber));
		int count = Global.newsDAO.selectNewsCommentaryCount(Integer.parseInt(newsId),Integer.parseInt(state),Integer.parseInt(contentstate));
		int maxLine = (count%10) == 0 ? (count/10) : (count/10) + 1;
		 logger.info("分页信息参数：startPage:"+startPage+",lineNumber:"+lineNumber+",maxLine:"+maxLine+",count:"+count);

		request.setAttribute("commentaries", commentaries);
		request.setAttribute("startPage", Integer.parseInt(startPage));
		request.setAttribute("lineNumber", Integer.parseInt(lineNumber));
		request.setAttribute("contentstate", Integer.parseInt(contentstate));
		request.setAttribute("state", Integer.parseInt(state));
		request.setAttribute("MaxLine", maxLine);
		return "wap/news/newsCommentaryList";
	}
	/**
	 * 删除恢复新闻评论
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/deleteNewsCommentary.jspx",method=RequestMethod.GET)
	public String deleteNewsCommentary(HttpServletRequest request,
			HttpServletResponse response) {
		String state = request.getParameter("state")==null ? "" : (String)request.getParameter("state");
		String id = request.getParameter("id")==null ?"" : (String)request.getParameter("id"); 
		logger.info("删除恢复新闻评论：state(0删除1未删除):"+state+"评论id:"+id);
		int result = Global.newsDAO.deleteNewsCommentary(Integer.parseInt(state), Integer.parseInt(id));
		request.setAttribute("message", "操作成功！");
		return "wap/news/commentaryResult";
	}
	/**
	 * 审核新闻评论
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/toAddNumberBet.jspx?",method=RequestMethod.GET)
	public String updateNewsCommentary(HttpServletRequest request,
			HttpServletResponse response) {
		String contentstate = request.getParameter("contentstate")==null ? "" : (String)request.getParameter("contentstate");
		String id = request.getParameter("id")==null ?"" : (String)request.getParameter("id"); 
		logger.info("审核新闻评论：contentstate(0审核1未审核):"+contentstate+"评论id:"+id);
		int result = Global.newsDAO.updateNewsCommentaryByContentstate(Integer.parseInt(contentstate), Integer.parseInt(id));
		logger.info("返回信息：result:"+result);
		request.setAttribute("message", "操作成功成功！");
		return "wap/news/commentaryResult";
	}
}
