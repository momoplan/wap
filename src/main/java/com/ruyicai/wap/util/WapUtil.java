package com.ruyicai.wap.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

import com.ruyicai.wap.Global;
import com.ruyicai.wap.bean.Channel;

public class WapUtil {
	private static final Logger logger = Logger.getLogger(WapUtil.class);

	private static List<Channel> list;
	public static Channel defaultChannel;

	public static String getTemplateFilePath(HttpServletRequest request,
			String name) {
		Channel channel = getChannel(request);
		String tmp = "/template/" + channel.getChannelId() + "/" + name;
		return tmp;
	}

	/**
	 * 
	 * @param request
	 * @return 在URL请求中加参数 reloadTemplate＝1 可以重新从库中load配置
	 * 
	 * 
	 */
	public static Channel getChannel(HttpServletRequest request) {
		String servname = request.getServerName();
		if (list == null || list.isEmpty() || defaultChannel == null
				|| request.getParameter("reloadTemplate") != null) {
			exportChannel(request.getSession().getServletContext());
		}
		if (list != null)
			for (Channel channel : list) {
				if (servname.equals(channel.getDomain())) {
					return channel;
				}
			}
		return defaultChannel;
	}


	public static String getChannelId(HttpServletRequest request) {
		return getChannel(  request).getChannelId();
	}

	public static void exportChannel(ServletContext sc) {
		String contentPath = sc.getRealPath("/");
		logger.info("load Template from database ContentPath ROOT:"
				+ contentPath);
		list = Global.newsDAO.findAllChannels();
		String heads = "<%@ page pageEncoding=\"UTF-8\"%><%@ taglib prefix=\"decorator\" uri=\"http://www.opensymphony.com/sitemesh/decorator\"%><%@taglib uri=\"http://www.opensymphony.com/sitemesh/page\" prefix=\"page\"%>";
		if (list != null) {
			for (Channel channel : list) {
				try {
					if (defaultChannel == null
							&& channel.getChannelId().equals(
									Global.DefaultChannelId)) {
						defaultChannel = channel;
					}
					File tmpFile = new File(contentPath + "/template/"
							+ channel.getChannelId() + "/homeTemplate.jsp");
					if (!tmpFile.getParentFile().exists()) {
						tmpFile.getParentFile().mkdirs();
					}
					FileOutputStream fileout = new FileOutputStream(tmpFile,
							false);
					OutputStreamWriter out = new OutputStreamWriter(fileout,
							"utf-8");
					out.write(heads);
					out.write(channel.getHomeTemplate());
					out.close();
					fileout.close();

					File tmpFile2 = new File(contentPath + "/template/"
							+ channel.getChannelId() + "/nonTemplate.jsp");
					if (!tmpFile2.getParentFile().exists()) {
						tmpFile2.getParentFile().mkdirs();
					}
					FileOutputStream fileout2 = new FileOutputStream(tmpFile2,
							false);
					OutputStreamWriter out2 = new OutputStreamWriter(fileout2,
							"utf-8");
					out2.write(heads);
					out2.write(channel.getNonTemplate());
					out2.close();
					fileout2.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if (defaultChannel == null) {
			throw new RuntimeException("defaultChannel not set.");
		}
	}

	public static String prevPage(HttpServletRequest request,
			HttpServletResponse response) {
		String prevUrl = request.getHeader("Referer");
		if (prevUrl == null) {
			prevUrl = request.getSession().getServletContext().getContextPath();
		}
		return prevUrl.replace("&", "&amp;");
	}

	public static void setContentType(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType(getContentType(request, response));
	}

	public static String getContentType(HttpServletRequest request,
			HttpServletResponse response) {
		String acceptHeader = request.getHeader("accept");
		String type;
		if(acceptHeader==null){
			type = "text/html;charset=UTF-8";
		}else if (acceptHeader.indexOf("application/vnd.wap.xhtml+xml") != -1){
			type = "application/vnd.wap.xhtml+xml;charset=UTF-8";
		}
		else if (acceptHeader.indexOf("application/xhtml+xml") != -1){
			type = "application/xhtml+xml;charset=UTF-8";
		}
		else{
			type = "text/html;charset=UTF-8";
		}
		return type;
	}

	public static void pubDeadLine(Model model, String playName) {
		String deadline = CommonUtil.getDeadline(playName, 0);
		if (deadline == null)
			deadline = "";
		model.addAttribute("deadline", deadline);
	}
}
