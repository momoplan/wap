package com.ruyicai.wap;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding("utf-8");
		HttpServletResponse resp = (HttpServletResponse) response;
		if (req.getSession().getAttribute("user") == null) {
			String uri = req.getRequestURI();
			if (uri.indexOf("/handpay/") > 0) {// for handpay
				resp.sendRedirect("/w/handpay.jsp");
			} else {
				String parameters = "";
				Map parameterMap = req.getParameterMap();
				  Set key = parameterMap.keySet();
				  System.out.println(key);
				  int i=1;
				  String parakey="";
				  String paravalue ="";
				  for(Object aaa: key.toArray()){
			         parakey = aaa.toString();
			         paravalue = ((String[])parameterMap.get(aaa))[0];
			         if(i==1){
			            parameters += parakey+"="+paravalue; 
			         }else{
			            parameters += "&"+parakey+"="+paravalue; 
			         }
			         i++;
			       }
				String str = req.getHeader("Referer");
				
				
				req.getSession().setAttribute("str", str);
				req.getSession().setAttribute("url", uri+"?"+parameters);
				resp.sendRedirect("/w/wap/userinfo/login.jspx");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
