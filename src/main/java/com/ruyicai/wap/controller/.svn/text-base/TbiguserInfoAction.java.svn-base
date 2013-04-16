package com.ruyicai.wap.controller;


import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.TuserinfoUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;

@Controller
public class TbiguserInfoAction{
	private static final Logger logger = Logger.getLogger(TbiguserInfoAction.class);
	private static ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");
	private static final String lottery = rbint.getString("lottery");
	
	/**
	 * @param userno
	 * @param outuserno
	 * @param type
	 * @return
	 */
	public  String bindBigUser(String userno,String outuserno,String type ){
	    logger.info("~~~~~~~~~~~~~~绑定开始~~~~~~~~~~~~");
		String url ="";
		String parameter = "";
		String errorcode ="";
	    url = lottery+"tbiguserinfoes/bind";
		parameter = "userno="+userno+"&type="+type+"&outuserno="+outuserno;
		logger.info("url:"+url+"?"+parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);
		try {
			JSONObject  json = JSONObject.fromObject(re);
			logger.info("合作方用户绑定lottery返回："+re);
			errorcode =	json.getString("errorCode");
			logger.info("~~~~~~~~~~~~~~绑定结束~~~~~~~~~~~~");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorcode;
	}

	/**
	 *申请账户
	 * @throws IOException 
	 */
	@RequestMapping(value="/BigUserRegister.jspx",method=RequestMethod.POST)
	public String BigUserRegister(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mobileid = (String) request.getSession().getAttribute("mobile");
		String user_id = (String )request.getSession().getAttribute("user_id");
		ServletContext servletContext = request.getSession().getServletContext();
		mobileid = (String) servletContext.getAttribute("mobile");
		user_id = (String) servletContext.getAttribute("user_id");
		servletContext.removeAttribute("mobile");
		servletContext.removeAttribute("user_id");
		String channel = WapUtil.getChannelId(request);
		try {
			if(mobileid != null){
			String passwd = "";
			String userno= "";
			passwd = CommonUtil.getUuid().substring(0, 12);
			String errorcode = CommonUtil.registerBigUser(mobileid, passwd, "111111111111111111", channel, "");
			if ("0".equals(errorcode)) {
			     //注册成功, 开始绑定合作方, 以便联合登录
				logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~注册成功, 开始绑定合作方, 以便联合登录");

				userno= CommonUtil.getUserno(mobileid);
				String re = bindBigUser(userno, user_id, "000002");
				if(!"0".equals(re)){
					logger.error("~~~~~~~~~~~~~~~合作方账户绑定失败");
					request.setAttribute("message", "帐号申请成功,请使用互联星空账户登录绑定");
					return "wap/userinfo/login";
				}
				logger.info("~~~~~~~~~~~~账户绑定成功~~~~~~~~~~~~~~");
				// 注册完之后登录
				JSONObject jsonObject= CommonUtil.getUserinfo(mobileid);
				JSONObject jsonObject2 = jsonObject.getJSONObject("value");
				
				String jsonObject1="";
				String Code = "";
				String userName = "";
				String nickName = "";
				try {
					 jsonObject1 = jsonObject.getString("value");
					 userName = jsonObject2.getString("userName");
					 Code = jsonObject.getString("errorCode");
					 nickName = jsonObject2.getString("nickname");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info("reValue:" + jsonObject);
				if ("0".equals(Code)) {
					logger.info("手机号：" + mobileid);
					logger.info("sessionid" + jsonObject1);
//					request.getSession().setAttribute("mobile_code", mobileid);
//					request.getSession().setAttribute("userno", userno);
//					request.getSession().setAttribute("userName", userName);
//					request.getSession().setAttribute("sessionid", userno);
					TuserInfoBean user=new TuserInfoBean();
					user.setUserName(userName);
					user.setMobileid( mobileid);
					user.setNickname(nickName);
					user.setUserno(userno);
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("sessionid", request.getSession().getId());
//					PVUtil.sendLoginUrl(mobileid, "W", (String)request.getSession().getAttribute("sessionid"), (String)request.getSession().getAttribute("mobile_code"));
					//将用户名保存到COOKIE中。
//					Cookie cookie = new Cookie("mobile_Id", URLEncoder.encode(mobileid,"utf-8"));
//			        cookie.setMaxAge(60 * 60 * 24 * 30 * 1); //用户名保留1个月
//			        response.addCookie(cookie);
//					logger.info("Method:login,mobile_Id:"+mobileid+",登录成功");
				}else{
					request.setAttribute("message", "登录失败");
					logger.error(Code + ":Method:login,mobile_Id:" + mobileid
							+ ",登录失败" );
					return "wap/userinfo/login";
				}
			    //调用如意彩登录接口登录成功跳到首页
				request.setAttribute("flag","1" );
				logger.info("Method:login,mobile_Id:"+mobileid+",登录成功跳转到首页");
				return "wap/wapindex";
			} else {
				request.setAttribute("message","用户申请不成功");
				logger.error(errorcode + ":Method:register,Mobile:"
						+ mobileid + "注册失败");
				return "wap/userinfo/login";
			}
			}
			return "userinfo/register";
		} catch (JSONException e) {
		     logger.debug("大客户注册出现异常"+ ":Method:vnetForEncrypt,Mobile:"
						+ mobileid);
		     return "wap/userinfo/register";
		}
		
	}
	
	/**
	 * 合作方大客户登录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/buser/bigUserLogin.jspx")
	public String bigUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mobileid = request.getParameter("mobileid");
		String pwd = request.getParameter("pwd");
		logger.info("Method:login,mobile_Id:" + mobileid + ",passLength:"
				+ pwd.length());
		if (mobileid == null) {
			request.setAttribute("message",
					MessageUtil.TIAW_login_MobileNotEmpty);
			logger
					.error("Method:login,mobile_Id:" + mobileid
							+ ",手机号码未输入");
			return "wap/userinfo/login";
		}
		if ((mobileid.trim()).length() != 11) {
			request.setAttribute("message",
					MessageUtil.TIAW_login_MobileLengthError);
			logger.error("Method:login,mobile_Id:" + mobileid
					+ ",手机号码格式错误");
			return "wap/userinfo/login";
		}
		boolean isOk = VerificationAlgorithmUtil.verifyMobileId(mobileid
				.trim());
		if (!isOk) {
			request.setAttribute("message",
					MessageUtil.TIAW_login_MobilePatternError);
			logger
					.error("Method:login,mobile_Id:" + mobileid
							+ ",手机号格式错误");
			return "wap/userinfo/login";
		}
		mobileid = (mobileid.trim());
		pwd = (pwd.trim());
		String userno = CommonUtil.getUserno(mobileid);//先查询这个手机号是够注册过
		if("".equals(userno) || userno==null){
			//在登录中, 发现这个用户并不存在，那么系统自动给其注册并绑定
			logger.info("Method:login,mobile_Id:" + mobileid+ ",用户的userno不存在,系统自动给用户注册并且绑定");
			return BigUserRegister(request, response);
		}
		//开始登陆
		logger.info("Method:login,mobile_Id:" + mobileid+ "~~~~~~~~~~~~大用户存在,认证码匹配,开始进行后台登录");
		JSONObject jsonObject= CommonUtil.getUserinfo(mobileid);
		
		String errorCode = "";
		String userName = "";
		String nickName = "";
		try {
			JSONObject jsonObject2 = jsonObject.getJSONObject("value");
			userName = jsonObject2.getString("userName");
			 errorCode = jsonObject.getString("errorCode");
			 nickName = jsonObject2.getString("nickname");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if("0".equals(errorCode)){
			//查询该user_id 是否系统中存在
//			request.getSession().setAttribute("mobile_code", mobileid);
//			request.getSession().setAttribute("userno", userno);
//			request.getSession().setAttribute("userName", userName);
//	    	request.getSession().setAttribute("sessionid", mobileid);
			TuserInfoBean user=new TuserInfoBean();
			user.setUserName(userName);
			user.setMobileid( mobileid);
			user.setNickname(nickName);
			user.setUserno(userno);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("sessionid", request.getSession().getId());
//	    	Cookie cookie = new Cookie("mobile_Id", URLEncoder.encode(mobileid,"utf-8"));
//	        cookie.setMaxAge(60 * 60 * 24 * 30 * 1); //用户名保留1个月
//	        response.addCookie(cookie);
			logger.info("Method:login,mobile_Id:" + mobileid+ "~~~~~~~~~~~~大用户存在,认证码匹配通过,后台登录成功");
	    	return "wapindex";
		}
		return "wap/userinfo/login";
		
	}
	
	/**
	 * 用户在互联星空访问如意彩，Vnet传送用户的加密信息
	 * 如意彩接收并解密  ， 验证
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/buser.do") 
	public String vnetForEncrypt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//判断互联星空的用户是否存在
		String mobileid = (String) request.getSession().getAttribute("mobile");
	    String vnetcode = request.getParameter("vnet_user_request_code");//互联星空用户携带的验证码
	    if("".equals(vnetcode)||vnetcode==null){
	    	logger.info("互联星空验证码为空");
	    	request.getSession().setAttribute("nomobile", "nomobile");
	    	return "wap/wapindex";
	    }
		String recode = (String) request.getSession().getAttribute("code");
		String user_id =(String) request.getSession().getAttribute("user_id");//用户的平台ID
		ServletContext servletContext = request.getSession().getServletContext();
		mobileid = (String) servletContext.getAttribute("mobile");//获得用户加密信息里的手机号
		user_id = (String) servletContext.getAttribute("user_id");//获得用户加密信息里的平台ID
		if(mobileid!=null&&!"".equals(mobileid)){
			recode = (String) servletContext.getAttribute(mobileid);//获得存放在作用域的验证码，用于验证用户携带的验证码
			servletContext.removeAttribute(mobileid);
		}
		logger.info("参数信息：vnetcode:"+vnetcode+"recode:"+recode+"mobileid:"+mobileid);
		if(vnetcode != null && recode != null&& vnetcode.equals(recode)&&mobileid !=null&&!"".equals(mobileid)){
		//查询该user_id 是否系统中存在
			logger.info("~~~~~~~~~查询此手机号是否注册过");
			String userno = CommonUtil.getUserno(mobileid);//查询这个手机号是否注册过
			if("".equals(userno) || userno==null){
				//自动申请账户
				logger.info("Method:login,mobile_Id:" + mobileid+ ",用户的userno不存在,系统自动给用户注册并且绑定");
				return BigUserRegister(request, response);
			}else{
				servletContext.removeAttribute("mobile");
				servletContext.removeAttribute("user_id");
			}
			logger.info("Mobile:"+mobileid+"已经注册 userno="+userno);
		    String errorCode =	CommonUtil.getErrorcodeByUserno(user_id);//查询是否有这个用户是否绑定	
			if(!"0".equals(errorCode)&&!"100022".equals(errorCode)){//说明该大用户没有绑定
				
				logger.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~用户没有绑定, 开始绑定合作方, 以便联合登录");
				String re = bindBigUser(userno, user_id, "000002");
				if(!"0".equals(re)){
					logger.error("~~~~~~~~~~~~~~~合作方账户绑定失败");
					request.setAttribute("message", "帐号没有绑定,请使用互联星空账户登录绑定");
					return "wap/userinfo/login";
				}
			}
			
			//binged
			logger.info("~~~~~~~~~~~~大用户存在,认证码匹配,开始进行后台登录userno::"+userno);
			//查询大用户userno
			JSONObject jsonObject= CommonUtil.getUserinfo(mobileid);
			String jsonObject1="";
			String Code = "";
			String userName = "";
			String nickName = "";
			try {
				 jsonObject1 = jsonObject.getString("value");
				 Code = jsonObject.getString("errorCode");
				 JSONObject jsonObject2 = jsonObject.getJSONObject("value");
					userName = jsonObject2.getString("userName");
					 nickName = jsonObject2.getString("nickname");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if("0".equals(Code)){
//		    	request.getSession().setAttribute("mobile_code", mobileid);
//		    	request.getSession().setAttribute("userno", userno);
//		    	request.getSession().setAttribute("userName", userName);
//		    	request.getSession().setAttribute("sessionid", userno);
		    	String result = TuserinfoUtil.addTuserinfoScore(userno, 8);
				JSONObject object = JSONObject.fromObject(result);
				String errorcode = object.getString("errorCode");
				boolean value = object.getBoolean("value");
				if("0".equals(errorcode)){
					logger.info("用户登录调用增加积分接口,"+(value==true ? "每天第一次登录增加积分" : "没有增加"));
				}
				TuserInfoBean user=new TuserInfoBean();
				user.setUserName(userName);
				user.setMobileid( mobileid);
				user.setNickname(nickName);
				user.setUserno(userno);
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("sessionid", request.getSession().getId());
//		    	Cookie cookie = new Cookie("mobile_Id", URLEncoder.encode(mobileid,"utf-8"));
//		        cookie.setMaxAge(60 * 60 * 24 * 30 * 1); //用户名保留1个月
//		        response.addCookie(cookie);
		    	// JM 如果跳转页面有所指定 那么 表示这个次登录 是操作中途登录 需要返回到操作  
				if (request.getSession().getAttribute("toLoginFormData") != null
						&& !((String) request.getSession().getAttribute(
								"toLoginFormData")).equals("")) {
					String tologin = (String) request.getSession().getAttribute(
							"toLoginFormData");
					tologin = tologin.replace("<jrtsessionid>", request
							.getSession().getId());
					response.sendRedirect(tologin);
					logger.info("手机号：" + mobileid + "跳转路径：" + tologin);
				}
				logger.info("~~~~~~~~~~~~大用户存在,认证码匹配通过,后台登录成功");
			    //
		    	return "wap/wapindex";
		    }else{
		    	logger.error("免登陆后台自动登陆失败,method:vnetForEncrypt,返回参数"+jsonObject1);
		    	return "wap/userinfo/login";
		    }
		}
		//如果vnet传过来的认证码参数不对，则提示用户直接去登录
		if(mobileid ==null||"".equals(mobileid)){
			return "wapindex";
		}
		return "wap/userinfo/login";
	}
}
