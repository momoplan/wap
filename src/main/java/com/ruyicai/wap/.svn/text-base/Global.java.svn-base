package com.ruyicai.wap;

import java.util.ResourceBundle;

import com.ruyicai.wap.iface.ClientDao;
import com.ruyicai.wap.iface.FeedBackDAO;
import com.ruyicai.wap.iface.NewsDAO;
import com.ruyicai.wap.iface.WapNewsDao;
public class Global {

	public static ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	public static String STATIC_URL = rbint.getString("staticUrl");
	public static NewsDAO newsDAO;
	public static FeedBackDAO feedBackDAO;
	public static ClientDao clientDao;
	public static WapNewsDao wapNewsDao;
	public static void setWapNewsDao(WapNewsDao wapNewsDao) {
		Global.wapNewsDao = wapNewsDao;
	}


	public static String DefaultChannelId = "1";

	public void setDefaultChannelId(String defaultChannelId) {
		DefaultChannelId = defaultChannelId;
	}


	public static void setRbint(ResourceBundle rbint) {
		Global.rbint = rbint;
	}


	public static void setSTATIC_URL(String sTATIC_URL) {
		STATIC_URL = sTATIC_URL;
	}


	public void setNewsDAO(NewsDAO news) {
		newsDAO = news;
	}


	public void setFeedBackDAO(FeedBackDAO feedBack) {
		feedBackDAO = feedBack;
	}


	public void setClientDao(ClientDao clientDao) {
		Global.clientDao = clientDao;
	}
	
	
}
