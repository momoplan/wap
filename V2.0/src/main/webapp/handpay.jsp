<?xml version="1.0" encoding="UTF-8"?>
<%@page import="com.ruyicai.wap.bean.TuserInfoBean"%>
<%
	//TEST
	// 	session.setA
	TuserInfoBean user  =new TuserInfoBean();
	user.setNickname("情殇");
	user.setUserName("13717504105");
	user.setUserno("00000068");
	request.getSession().setAttribute("user", user);
	response.sendRedirect("/w/handpay/home.jspx");
%>