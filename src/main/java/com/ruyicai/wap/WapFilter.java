package com.ruyicai.wap;

//url参数==》session ==> domain 
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;

public class WapFilter implements Filter {

	public void destroy() {
	}

	public void setNickName(HttpServletRequest request) {
//		String user_name = (String) request.getSession().getAttribute(
//				"userName");
//		String nick_Name = (String) request.getSession().getAttribute(
//				"nickName");
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession().getAttribute(
				"user");
		String uri = request.getRequestURI();
		int i = uri.indexOf("loginOut.jspx");
		String user_name ="";
		String nick_Name ="";
		if(tuserInfoBean!=null&&i==-1){
			user_name = tuserInfoBean.getUserName();
			nick_Name = tuserInfoBean.getNickname();
		}
		 
		String user_session = (String) request.getSession().getAttribute(
				"sessionid");

		if (user_name != null && !"".equals(user_name) && user_session != null
				&& !"".equals(user_session)&&i==-1) {
			if (nick_Name != null && !"".equals(nick_Name)
					&& !"null".equals(nick_Name)) {
				user_name = nick_Name;
			} else {
				boolean verifyMobileId = VerificationAlgorithmUtil
						.verifyMobileId(user_name.trim());
				if (verifyMobileId && user_name.length() > 10) {
					user_name = user_name.substring(0, 3) + "****"
							+ user_name.substring(7, 11);
				}
			}
		}
		request.setAttribute("user_name", user_name);
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		req.setCharacterEncoding("utf-8");
		setNickName(req);
		// 获取UA==MOBILE == IP 信息，用于电信日志
		String UA = req.getHeader("user-agent");
		String Mobile = req.getHeader("x-up-calling-line-id");
		// set ContentType
		WapUtil.setContentType((HttpServletRequest) request, resp);
		// add jsessionid and cn to url
		String cn = getCn(req, resp);
		req.setAttribute("cn", cn);
		req.getSession(true).setAttribute("CHANNEL", cn);
		// reponse body
		WrapperResponse wrap = new WrapperResponse(
				(HttpServletResponse) response);
		chain.doFilter(request, wrap);
		String buffer = wrap.getContent().trim();
		// 打印电信日志
		if (cn.equals("521")) {
			// add hulianxinglong log
			Logger logger = Logger.getLogger("0000000017");
			// 拼接互联星空log日志
			String title =getTitle(buffer);
			if (title != null)
				title = title.replaceAll("[|]", "");
			if (Mobile == null || Mobile.equals("") || Mobile.equals("null")) {
				Mobile = "";
			}
			logger.info(Mobile + "|sports|ruyicai_caipiao|" + title
					+ "|" + UA);
		}
		// replace tags.
		String cnstr;
		if (cn.equals(Global.DefaultChannelId)) {
			cnstr = "";
		} else {
			cnstr = "cn=" + cn;
		}
		String addParam = "";
		if (req.getSession().getId() != null
				&& req.getSession().getId().length() > 0) {
			addParam = ";jsessionid=" + req.getSession().getId();
		}
		String sbf = getReplaceString(urlPattern, buffer, cnstr, addParam);
		sbf = getReplaceString(formPattern, sbf, cnstr, addParam);
		response.getWriter().write(sbf);

	}

	/**
	 * 获取页面title
	 * 
	 * @return
	 */
	private static String getTitle(String body) {
		Matcher mat = titlePattern.matcher(body);
		if (mat.find() && mat.start() > -1) {
			return mat.group(1);
		}
		return null;
	}

	/**
	 * 获取CN
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	private static String getCn(HttpServletRequest request,
			HttpServletResponse resp) {
		String cn = request.getParameter("cn");
		Object sessioncn = request.getSession(true).getAttribute("CHANNEL");

		if (cn == null || cn.equals("") || !cn.matches("\\d+")) {
			cn = (String) sessioncn;
		}
		if (sessioncn == null) {
			// UV统计
			Cookie cookie1 = new Cookie("CHANNEL", cn);
			cookie1.setMaxAge(24 * 60000);
			resp.addCookie(cookie1);
		}
		if (cn == null || cn.equals("") || !cn.matches("\\d+")) {
			cn = WapUtil.getChannel(request).getChannelId();
		}
		if (cn == null || cn.equals("") || !cn.matches("\\d+")) {
			cn = Global.DefaultChannelId;
		}
		return cn;
	}

	public static String getReplaceString(Pattern pat, String buffer,
			String cn, String addParam) {
		StringBuffer sbf = new StringBuffer();
		Matcher mats = pat.matcher(buffer);
		int start = 0;
		while (mats.find()) {
			sbf.append(buffer.substring(start, mats.start()));
			start = mats.start();
			String str = buffer.substring(start, mats.end());
			String gurl = mats.group(3);
			String repurl;
			if (gurl.indexOf("://") < 0) {
				repurl = repString(cn, addParam, gurl);
				if (!gurl.startsWith(cntPath))
					repurl = cntPath
							+ (repurl.length() > 0 && repurl.charAt(0) == '/' ? repurl
									.substring(1) : repurl);
			} else {
				repurl = gurl;
			}
			String result = str.replace(gurl, repurl);
			sbf.append(result);
			start = mats.end();
		}
		if (start != buffer.length()) {
			sbf.append(buffer.substring(start, buffer.length()));
		}
		return sbf.toString();
	}

	public static String repString(final String cn, final String jsid,
			final String url) {
		int idx = url.indexOf("?");
		if (idx > 0) {
			if (url.indexOf(";jsessionid=") < 0) {
				return url.substring(0, idx) + jsid + url.substring(idx)
						+ (cn.length() > 1 ? "&amp;" + cn : cn);
			} else {
				return url.substring(0, idx) + url.substring(idx)
						+ (cn.length() > 1 ? "&amp;" + cn : cn);
			}
		} else {
			if (url.indexOf(";jsessionid=") < 0) {
				return url + jsid + (cn.length() > 1 ? "?" + cn : "");
			} else {
				return url + (cn.length() > 1 ? "?" + cn : cn);
			}
		}
	}

	public static final Pattern titlePattern = Pattern
			.compile("<title>([^<]*)</title>");
	public static final Pattern urlPattern = Pattern
			.compile("(<a[\\s\\n\\r]+(?:[^\\s>][\\s\\n\\r]*){0,})href[\\s\\n\\r]*=[\\s\\n\\r]*(\"|'|)([^\"'\\s>]*)(\"|'|)[\\s\\n\\r]*>[^<]*(</a>)");

	public static final Pattern formPattern = Pattern
			.compile("(<form[\\s\\n\\r]+(?:[^\\s>][\\s\\n\\r]*){0,})action[\\s\\n\\r]*=[\\s\\n\\r]*(\"|'|)([^\"'\\s>]*)");

	public static String cntPath = "";

	public void init(FilterConfig fConfig) throws ServletException {
		cntPath = fConfig.getServletContext().getContextPath() + "/";
	}

}
