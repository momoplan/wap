package com.ruyicai.wap.controller;

import static com.ruyicai.wap.Global.rbint;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ruyicai.wap.Global;
import com.ruyicai.wap.WapFilter;
import com.ruyicai.wap.bean.MsgRequest;
import com.ruyicai.wap.bean.Score;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.ErrorCode;
import com.ruyicai.wap.util.HttpUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.StringUtils;
import com.ruyicai.wap.util.Tools;
import com.ruyicai.wap.util.TuserinfoUtil;
import com.ruyicai.wap.util.URLEncoder;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/userWap")
@Controller
public class TuserInfoActionWap{

	private static final Logger logger = Logger
			.getLogger(TuserInfoActionWap.class);
	private String mobile = "";
	private String lottery = rbint.getString("lottery");

	/**
	 * 用户登录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author 李净滔
	 */
	@RequestMapping(value="/login.jspx",method=RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String userName = "";
		ModelMap mmap = new ModelMap(); 

		try {
			String userno = "";// 用户编号
			String resPass = ""; // 密码
			String state = ""; // 状态
			String mobile_Id = "";
			String nickName = "";//昵称
			userName = request.getParameter("userName"); // 用户名
			String pass = request.getParameter("password"); // 密码
			logger.info("Method:login,userName:" + userName + ",pass:" + pass);
			String urlStr = lottery + "tuserinfoes?";
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("json");
			paramStr.append("&find=ByUserName");
			paramStr.append("&userName=").append(userName);
			String resStr = HttpUtil.sendByPostUtF(urlStr, paramStr.toString());
			logger.info("查询用户返回结果:" + resStr);
			if (resStr == null || resStr.trim().equals("")) {
				request.setAttribute("message",
						MessageUtil.TIAW_login_ExceptionMsg);
				request.setAttribute("userName", userName);
				logger.error("Method:login,userName:" + userName
						+ ",lottery返回为空");
				return "wap/userinfo/login";
			}
			JSONObject loginJson = JSONObject.fromObject(resStr);
			if (loginJson.getString("errorCode") != null
					&& !loginJson.getString("errorCode").trim().equals("")
					&& loginJson.getString("errorCode").equals("0")) {
				JSONObject valueJsonObject = (JSONObject) loginJson
						.get("value");
				userno = valueJsonObject.getString("userno");
				resPass = valueJsonObject.getString("password");
				state = valueJsonObject.getString("state");
				mobile_Id = valueJsonObject.getString("mobileid"); 
				nickName = valueJsonObject.getString("nickname");
			} else if (loginJson.getString("errorCode") != null
					&& !loginJson.getString("errorCode").trim().equals("")
					&& loginJson.getString("errorCode").equals("100002")) {
				request.setAttribute("message", "未注册");
				request.setAttribute("userName", userName);
				return "wap/userinfo/login";
			} else {
				request.setAttribute("message", "登录失败");
				request.setAttribute("userName", userName);
				return "wap/userinfo/login";
			}
			if (state.equals("0")) { // 关闭用户
				request.setAttribute("message", "未注册");
				request.setAttribute("userName", userName);
				return "wap/userinfo/login";
			} else if (state.equals("2")) { // 用户暂停
				request.setAttribute("message", "用户暂停");
				request.setAttribute("userName", userName);
				return "wap/userinfo/login";
			} else if (state.equals("1") && resPass != null
					&& !resPass.equals(Tools.EncoderByMd5(pass))) { // 状态正常，密码错误
				request.setAttribute("message", "用户名或密码错误");
				request.setAttribute("userName", userName);
				return "wap/userinfo/login";
			} else if (state.equals("1") && resPass != null
					&& resPass.equals(Tools.EncoderByMd5(pass))) { // 状态正常，密码正确
				try{
					String result = TuserinfoUtil.addTuserinfoScore(userno, 8);
					JSONObject jsonObject = JSONObject.fromObject(result);
					String errorCode = jsonObject.getString("errorCode");
					boolean value = jsonObject.getBoolean("value");
					if("0".equals(errorCode)){
						logger.info("用户登录调用增加积分接口,"+(value==true ? "每天第一次登录增加积分" : "没有增加"));
					}
				}catch (Exception e) {
					logger.error("用户登录调用增加积分接口异常："+e.getMessage());
				}
				
				TuserInfoBean user=new TuserInfoBean();
				user.setUserName(userName);
				user.setMobileid( mobile_Id);
				user.setNickname(nickName);
				user.setUserno(userno);
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("sessionid", request.getSession().getId());
				String url = (String) request.getSession().getAttribute("url");
				String str = (String) request.getSession().getAttribute("str");
				String strHM = (String) request.getSession().getAttribute("strHM");
				String parameter = (String) request.getSession().getAttribute("parameter");
				if(str!=null&&!"".equals(str)&&parameter!=null&&!"".equals(parameter)){
					int i = str.indexOf("/w/");
					request.getRequestDispatcher(str.substring(i+2)+"?"+parameter).forward(request,response);
					return null;
				}
				if(strHM!=null&&strHM.indexOf("orderhm/caseLotDetail.jspx")>-1){
					int i=strHM.indexOf("/w/");
					request.getRequestDispatcher(strHM.substring(i+2)).forward(request,response);
					return null;
				}
//				if(url!=null&&!"".equals(url)){
//					String str = url.substring(0,url.indexOf("?"));
//					System.out.println("str:"+str);
//					String p = url.substring(url.indexOf("?")+1);
//					System.out.println("p:"+p);
//					String s[] = p.split("\\&");
//					for(int i=0;i<s.length;i++){
//						String s1[] = s[i].split("\\=");
//						System.out.println(s1[0]+","+s1[1]);
////						mmap.addAttribute(s1[0], s1[1]);
//						request.setAttribute(s1[0], s1[1]);
//					}
//					request.getSession().removeAttribute("url");
//					request.getRequestDispatcher(url.substring(2)).forward(request,response);
//					return null;
//				}
				return "redirect:/";
			} else {
				request.setAttribute("message", "登录失败");
				request.setAttribute("userName", userName);
				return "wap/userinfo/login";
			}
		} catch (Exception e) {
			logger.error( "用户登录出现异常:" + e.toString());
			e.printStackTrace();
			request.setAttribute("message", "登录失败");
			request.setAttribute("userName", userName);
			return "wap/userinfo/login";
		}
	}

	/**
	 * 用户登录退出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author 沈鹏兰
	 */
	@RequestMapping(value="/loginOut.jspx",method=RequestMethod.GET)
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		String channel = "";
		try {
			if (request.getSession().getAttribute("CHANNEL") != null) {
				channel = request.getSession().getAttribute("CHANNEL")
						.toString();
			}
			logger.info("Method:loginOut,Mobile:"
					+ request.getSession().getAttribute("mobile_code") + "用户退出登录");
			request.getSession().removeAttribute("sessionId");
			request.getSession().removeAttribute("userno");
			request.getSession().removeAttribute("userName");
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();// 令会话失效
		} catch (Exception e) {
			logger.error("Method:loginOut,Mobile:"
					+ request.getSession().getAttribute("sessionId") + ",异常"
					+ e.getMessage());
		}
		if ("387".equals(channel)) {
			request.getSession().setAttribute("CHANNEL", "387");
			return "wap/t/crossmo/wapindex";
		} else {
			return "wap/wapindex";
		}
	}
	/**
	 * 获取CN
	 * 
	 * @param request
	 * @param resp
	 * @return
	 */
	public static String getCn(HttpServletRequest request,
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
	/**
	 * lottery 注册接口(新)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value="/register.jspx",method=RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			logger.info("---------------------用户信息注册开始-----------------------");
			String userName = request.getParameter("userName"); // 用户名
			String password = request.getParameter("password"); // 密码
			String realPass = request.getParameter("realPass"); // 确认密码
			String token = request.getParameter("token"); // 是否重复提交
			String channel = "";
			String checkbox = request.getParameter("checkbox");//用户协议是否选中1选中0未选中
			String userID = (request.getParameter("userID") == null || request
					.getParameter("userID").equals("")) ? ""
					: request.getParameter("userID");

            channel =getCn(request, response);
            String cn = request.getParameter("cn");
            if("809".equals(cn)){
            	channel = cn;
            }else {
            	cn = (String) request.getSession().getAttribute("cn");
            	if("809".equals(cn)){
                	channel = cn;
                }
            }
			logger.info("Method:register,获取用户填写信息：：：userName:" + userName
					+ ",password:" + password + ",userID:" + userID + "channel"
					+ channel);
			if(!"1".equals(checkbox)){
				request.setAttribute("message", "必须同意用户协议");
				request.setAttribute("userName", userName);
				logger.info("Method:register,checkbox:" + checkbox
						+ "注册失败，没有选中" );
				return "wap/userinfo/register";
			}
			String isExecute = (String) request.getSession().getAttribute(token);
			
			// 验证手机号 密码
			String registerMessage = VerificationAlgorithmUtil
					.checkRegisterInfo(userName, password, realPass, userID);
			logger.info("用户填写信息验证结果：(为NULL说明参数正确)" + registerMessage);
			if (registerMessage == null) {
				if ("false".equals(isExecute)) {
					request.getSession().setAttribute(token, "true");
				}else{
					request.setAttribute("err_msg", "请勿重复提交");
					return "wap/BetSuccess";
				}
				String reValue = CommonUtil.register(userName, password,
						"", channel);
				logger.info("注册返回码" + reValue);
				if ("0".equals(reValue)) {
					// 注册完之后登录
					login(request, response);
					if (request.getSession().getAttribute("CHANNEL") != null
							&& "387".equals(request.getSession()
									.getAttribute("CHANNEL").toString())) {
						return "wap/t/crossmo/wapindex";
					} else {
						request.setAttribute("userName", userName);
						return "wap/userinfo/registerSuccess";
					}
				} else {
					String ttss = CommonUtil.getErrorStringFromCode(reValue);
					getRandom(request, model);
					request.setAttribute("message", ttss);
					request.setAttribute("userName", userName);
					logger.error(reValue + ":Method:register,Mobile:" + mobile
							+ "注册失败" + ttss);
					return "wap/userinfo/register";
				}
			} else {
				getRandom(request, model);
				request.setAttribute("message", registerMessage);
				request.setAttribute("userName", userName);
				logger.info(mobile + "注册信息验证失败");
				return "wap/userinfo/register";
			}
		} catch (Exception e) {
			logger.error("用户注册发生异常:" + e.getMessage());
			getRandom(request, model);
			request.setAttribute("message", "注册失败,请稍后再试!");
		}
		return "wap/userinfo/register";
	}
	@RequestMapping("/registerIndex.jspx")
	public String registerIndex(HttpServletRequest request, HttpServletResponse response,Model model){
		getRandom(request, model);
		return "wap/userinfo/register";
		
	}
	/**
	 * 修改密码
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/changePassword.jspa")
	public String changePassword(HttpServletRequest request, HttpServletResponse response) {
		try {
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userName = tuserInfoBean.getUserName();
			String userno = tuserInfoBean.getUserno();
			String oldPassword, newPassword, realNewPass;
			// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
			// 从request.getAttribute 中获取 与表单同名同类型的obj
			if (request.getAttribute("outFormData") == null
					|| request.getAttribute("outFormData").equals("")) {
				// 获取页面参数
				oldPassword = request.getParameter("oldPassword");
				newPassword = request.getParameter("newPassword");
				realNewPass = request.getParameter("realNewPass");
			} else {// 获取request.getAttribute中的存储
				String[] oldPasswords = request.getAttribute("oldPassword") == null ? null
						: (String[]) request.getAttribute("oldPassword");
				String[] newPasswords = request.getAttribute("newPassword") == null ? null
						: (String[]) request.getAttribute("newPassword");
				String[] realNewPasss = request.getAttribute("realNewPass") == null ? null
						: (String[]) request.getAttribute("realNewPass");
				// 获取输入页面的参数
				oldPassword = oldPasswords == null
						|| oldPasswords[0].equals("") ? "0" : oldPasswords[0];
				newPassword = newPasswords == null
						|| newPasswords[0].equals("") ? "0" : newPasswords[0];
				realNewPass = realNewPasss == null
						|| realNewPasss[0].equals("") ? "0" : realNewPasss[0];
			}
			

			logger.info("Method:changePassword,userName:" + userName
					+ ",oldPassword:" + oldPassword + ",newPassword:"
					+ newPassword + ",realNewPass:" + realNewPass);
			if (!VerificationAlgorithmUtil.verifyPassword(oldPassword)) {
				request.setAttribute("message",
						MessageUtil.TIAW_changePassword_OldPasswordError);
				logger.error("Method:changePassword,userName:" + userName
						+ ",密码修改失败:原密码格式错误");
				return "wap/userinfo/updateUserPassword";
			}
			if (!VerificationAlgorithmUtil.verifyPassword(newPassword)) {
				request.setAttribute("message",
						MessageUtil.TIAW_changePassword_NewPasswordError);
				logger.error("Method:changePassword,userName:" + userName
						+ ",密码修改失败:新密码为6-16位");
				return "wap/userinfo/updateUserPassword";
			}
			if (!realNewPass.equals(newPassword)) {
				request.setAttribute("message",
						MessageUtil.TIAW_changePassword_NewOldPasswordError);
				logger.error("Method:changePassword,userName:" + userName
						+ ",密码修改失败:密码与确认密码不一致");
				return "wap/userinfo/updateUserPassword";
			}
			String resPass = "";
			String state = "";
			JSONObject jsonObject = CommonUtil.getUserinfoByUserno(userno);
			if (jsonObject.getString("errorCode") != null
					&& !jsonObject.getString("errorCode").trim().equals("")
					&& jsonObject.getString("errorCode").equals("0")) {
				JSONObject valueJsonObject = (JSONObject) jsonObject
						.get("value");
				userno = valueJsonObject.getString("userno");
				resPass = valueJsonObject.getString("password");
				state = valueJsonObject.getString("state");
				if (state.equals("1") && resPass != null
						&& resPass.equals(Tools.EncoderByMd5(oldPassword))) { // 状态正常，密码正确
					String errorcode = TuserinfoUtil.resetPassword(userno,
							newPassword);
					if ("0".equals(errorcode)) {
						request.setAttribute("message", "密码修改成功");
						logger.info("修改密码成功errorCode:" + errorcode);
						return "wap/userinfo/updateUserPassword";
					} else {
						logger.info("修改密码失败errorCode:" + errorcode);
						request.setAttribute("message", "密码修改失败，请稍后再试");
						return "wap/userinfo/updateUserPassword";
					}
				} else {
					request.setAttribute("message", "密码修改失败，原密码错误");
					return "wap/userinfo/updateUserPassword";
				}
			} else {
				request.setAttribute("message", "系统忙，请稍后再试");
				return "wap/userinfo/updateUserPassword";
			}
		} catch (Exception e) {
			logger.error("Method:changePassword,Mobile:" + mobile
					+ ",密码修改失败:异常" + e.getMessage());
		}
		return "wap/userinfo/updateUserPassword";// 修改失败
	}

	/**
	 * 找回密码
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getPassword.jspx",method=RequestMethod.POST)
	public String getPassword(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userName="", bindingMobile="",mobileid="";
			// 获取页面参数
			userName = request.getParameter("userName");
			bindingMobile = request.getParameter("bindingMobile");
			logger.info("Method:getPassword,userName:" + userName
					+ ",bindingMobile:" + bindingMobile);
			if("".equals(userName)||userName ==null){
				request.setAttribute("message", "用户名不能为空");
				request.setAttribute("userName", userName);
				request.setAttribute("bindingMobile", bindingMobile);
				return "wap/userinfo/getPassword";
			}
			if("".equals(bindingMobile)||bindingMobile ==null){
				request.setAttribute("message", "手机号不能为空");
				request.setAttribute("userName", userName);
				request.setAttribute("bindingMobile", bindingMobile);
				return "wap/userinfo/getPassword";
			}
			String userno = "";
			String state = "";
			JSONObject jsonObject = CommonUtil.getUserinfoByUserName(userName);
			if (jsonObject.getString("errorCode") != null
					&& !jsonObject.getString("errorCode").trim().equals("")
					&& jsonObject.getString("errorCode").equals("0")) {
				JSONObject valueJsonObject = (JSONObject) jsonObject
						.get("value");
				mobileid = valueJsonObject.getString("mobileid");
				userno = valueJsonObject.getString("userno");
				state = valueJsonObject.getString("state");
				if(!bindingMobile.equals(mobileid)){
					request.setAttribute("message", "手机号不正确");
					request.setAttribute("userName", userName);
					request.setAttribute("bindingMobile", bindingMobile);
					return "wap/userinfo/getPassword";
				}
				if (state.equals("1")) { // 状态正常
					String newPassword = CommonUtil.getUuid().substring(0, 12);
					String errorcode = TuserinfoUtil.resetPassword(userno,
							newPassword);
					if ("0".equals(errorcode)) {
						String text = "尊敬的如意彩用户，您的如意彩账户新密码为："+newPassword+"，请您登录后及时修改密码。";
//						JSONObject object = TuserinfoUtil.sendMsgForWap(mobileid, URLEncoder.encode(text, "UTF-8"));
						boolean lean=true;
						lean = TuserinfoUtil.sendSms(mobileid, text);

						if(lean){
							request.setAttribute("userName", userName);
							request.setAttribute("message", "恭喜您，找回成功，密码已经发送到您的手机，请注意查收");
							logger.info("修改密码成功errorCode:" + errorcode);
							return "wap/userinfo/getPasswordResult";
						}else{
							logger.info("修改密码失败errorCode:" + errorcode);
							request.setAttribute("userName", userName);
							request.setAttribute("message", "密码找回失败，请稍后再试");
							return "wap/userinfo/getPasswordResult";
						}
						
					} else {
						logger.info("修改密码失败errorCode:" + errorcode);
						request.setAttribute("userName", userName);
						request.setAttribute("message", "密码找回失败，请稍后再试");
						return "wap/userinfo/getPasswordResult";
					}

				} else {
					request.setAttribute("message", "用户名不正确");
					request.setAttribute("userName", userName);
					request.setAttribute("bindingMobile", bindingMobile);
					return "wap/userinfo/getPassword";
				}
			} else {
				request.setAttribute("message", "用户名不正确，请重新填写");
				request.setAttribute("userName", userName);
				request.setAttribute("bindingMobile", bindingMobile);
				return "wap/userinfo/getPassword";
			}
		} catch (Exception e) {
			logger.error("Method:changePassword,Mobile:" + mobile
					+ ",密码修改失败:异常" + e.getMessage());
		}
		
		return "wap/userinfo/getPasswordResult";// 修改失败
	}

	/**
	 * 修改个人信息--获取用户原信息
	 */
	@RequestMapping(value="/getUserinfo.jspa")
	public String getUserinfo(HttpServletRequest request, HttpServletResponse response) {

		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userName = tuserInfoBean.getUserName();
		String userno = tuserInfoBean.getUserno();
		try {
			// 根据用户ID查询用户信息
			JSONObject userinfo = CommonUtil.getUserinfoByUserno(userno);
			if ("0".equals(userinfo.get("errorCode"))) {
				request.getSession().setAttribute("userinfo", userinfo);
			} else {
				return "wap/userinfo/login";
			}
		} catch (Exception e) {
			logger.error("获取用户信息失败:" + e.getMessage());
			return "wap/userinfo/login";
		}
		return "wap/userinfo/updateUserinfo";
	}

	public String formatStr(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}

	/**
	 * 修改个人信息
	 * 
	 */
	@RequestMapping(value="/changeUserinfo.jspa")
	public String changeUserinfo(HttpServletRequest request, HttpServletResponse response)
			throws PatternSyntaxException, JSONException,
			UnsupportedEncodingException {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String userName = tuserInfoBean.getUserName();
		boolean bool = true;
		String nickName = request.getParameter("nickName"); // 昵称
		logger.info("昵称----------"+nickName);
		String name = request.getParameter("name");// 用户名
		String certID = request.getParameter("certID");// 身份证
		String type = request.getParameter("type");//是否已经完善了昵称1完善0未完善
		logger.info("type----------"+type);
		certID = CommonUtil.QJToBJChange(certID);
		// 验证昵称是否带有特殊字符
		if (VerificationAlgorithmUtil.isStringFilter(nickName)) {
			request.setAttribute("nickNames",
					MessageUtil.TIAW_changeUserinfo_NICKNAME);
			logger.info(mobile + "昵称不能带有特殊字符");
			bool = false;
		}
		int l = chineseLength(nickName);
		if (l<4 ||l>16) {
			request.setAttribute("nickNames",
					"昵称长度不正确");
			logger.info(mobile + "昵称长度不正确");
			bool = false;
		}else if("0".equals(type)){
			if(nickName.indexOf(" ")!=-1){
				request.setAttribute("nickNames",
						"昵称不能带空格");
				bool = false;
			}
		}
		// 验证用户名
		if (VerificationAlgorithmUtil.isEmptyString(name)) {
			request.setAttribute("messagename",
					MessageUtil.TIAW_changeUserinfo_NameNotEmpty);
			logger.info(mobile + "用户名为空");
			bool = false;
		}
		// 验证用户名是否带有特殊字符
		if (VerificationAlgorithmUtil.isStringFilter(name)) {
			request.setAttribute("messagename",
					MessageUtil.TIAW_changeUserinfo_STRINGFILTER);
			logger.info(mobile + "用户名不能带有特殊字符");
			bool = false;
		}
		// 验证身份证
		if (VerificationAlgorithmUtil.checkUserID(certID) != null) {
			request.setAttribute("certID",
					VerificationAlgorithmUtil.checkUserID(certID));// MessageUtil.TIAW_changeUserinfo_CertIdError);
			logger.info(certID + VerificationAlgorithmUtil.checkUserID(certID));
			bool = false;
		}
		if (bool) {
			String errorCode = "";
			if("1".equals(type)){
				errorCode = TuserinfoUtil.updateUserinfo1(userno,StringUtils.encodeUrlString(formatStr(name)), certID);
			}else{
				errorCode = TuserinfoUtil.updateUserinfo(userno, StringUtils.encodeUrlString(nickName),
						StringUtils.encodeUrlString(formatStr(name)), certID);
			}
			JSONObject jsonObject = CommonUtil.getUserinfoByUserno(userno);
			JSONObject jsonObjectVale = jsonObject.getJSONObject("value");
			if ("0".equals(errorCode)) {
				String mobileid = jsonObjectVale.getString("mobileid");
				String str ="";
				if(!"".equals(mobileid)&&!"null".equals(mobileid)&&!" ".equals(mobileid)&&mobileid!=null){
					String result = TuserinfoUtil.addTuserinfoScore(userno, 1);
					JSONObject object = JSONObject.fromObject(result);
					String errorcode = object.getString("errorCode");
					boolean value = object.getBoolean("value");
					if("0".equals(errorcode)){
						logger.info("调用增加积分接口,完善信息"+(value==true ? "第一次增加积分" : "已经增加过没有增加"));
					}
				}else{
					str =  "<br/>温馨提示：您还没有绑定手机号(首次完善信息会赠送积分)<br/><a href='/w/userWap/bindingMobileDetail.jspa'>马上去绑定>></a>";
				}
				
				request.setAttribute("message","信息完善成功！"+str);
				logger.info(mobile + ",修改个人信息成功");
				if ("0".equals(jsonObject.get("errorCode"))) {
					request.getSession().setAttribute("userinfo", jsonObject);
				} else {
					return "wap/userinfo/login";
				}
				return "wap/userinfo/updateUserinfo";
			}
			if ("100105".equals(errorCode)) {
				request.setAttribute("message", "昵称已存在");
				logger.info(mobile + ",修改个人信息失败，昵称已存在");
				if ("0".equals(jsonObject.get("errorCode"))) {
					request.getSession().setAttribute("userinfo", jsonObject);
				} else {
					return "wap/userinfo/login";
				}
				return "wap/userinfo/updateUserinfo";
			} else {
				request.setAttribute("message",
						MessageUtil.TIAW_changeUserinfo_Fail);
				logger.error(mobile + ",修改个人信息失败,errorCode:" + errorCode);
				if ("0".equals(jsonObject.get("errorCode"))) {
					request.getSession().setAttribute("userinfo", jsonObject);
				} else {
					return "wap/userinfo/login";
				}
				return "wap/userinfo/updateUserinfo";
			}
		} else {
			return "wap/userinfo/updateUserinfo";
		}
	}
	
	/**
	 * 绑定手机号
	 */
	@RequestMapping(value="/bindingMobileDetail.jspa")
	public String bindingMobileDetail(HttpServletRequest request,
			HttpServletResponse response) {

		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userName = tuserInfoBean.getUserName();
		String userno = tuserInfoBean.getUserno();
		String mobileid = "";
		try {
			// 根据用户ID查询用户信息
			JSONObject userinfo = CommonUtil.getUserinfoByUserno(userno);

			if ("0".equals(userinfo.get("errorCode"))) {
				JSONObject jsonObject = userinfo.getJSONObject("value");
				mobileid = formatStr(jsonObject.getString("mobileid"));
				request.setAttribute("userName", userName);
				request.setAttribute("mobileid", mobileid);
			} else {
				return "wap/userinfo/login";
			}
		} catch (Exception e) {
			logger.error("获取用户信息失败:" + e.getMessage());
			return "wap/userinfo/login";
		}
		return "wap/userinfo/bindingMobileDetail";
	}
	/**
	 * 绑定手机号
	 */
	@RequestMapping(value="/bindingMobileGetCode.jspa")
	public String bindingMobileGetCode(HttpServletRequest request,
			HttpServletResponse response) {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String userName = tuserInfoBean.getUserName();
		String mobileid = (String) request.getParameter("mobileid");
		String type = (String) request.getParameter("type");
	// 验证绑定手机号
		boolean bool = true;
		if (mobileid == null || "".equals(mobileid)) {
			request.setAttribute("message", "手机号不能为空");
			logger.info(mobileid + "手机号不能为空");
			bool = false;
		}
		if (!VerificationAlgorithmUtil.verifyMobile(mobileid)) {
			request.setAttribute("message", "手机号格式不正确");
			logger.info(mobileid + "手机号格式不正确");
			bool = false;
		}
		if (bool) {
			try {
				// 发送短信 Unknown column 'createtime' in 'where clause'
				// 发送短信
				String bingCode = new CommonUtil().code();
				logger.info("开始查询");
				int count = Global.newsDAO.selectPhoneInfoCount(userName);
				logger.info("count:"+count);
				if(count>=3){
					request.setAttribute("bindingMobile", mobileid);
					request.setAttribute("userName", userName);
					request.setAttribute("message", "对不起，您今天操作次数已经达到上限");
					return "wap/userinfo/bindingMobileDetail";
				}
				int result = Global.newsDAO.insertPhoneInfo(userno, userName, mobileid, bingCode);
				logger.info("添加用户绑定手机号验证码信息：userno:"+userno+",userName:"+userName+",mobileid:"+mobileid+",bingCode:"+bingCode);
				logger.info("添加"+(result==1 ? "成功" : "失败"));
				String smscodes = "";
				if ("binding".equals(type)) {
					smscodes = "尊敬的用户,您本次绑定手机号所使用的验证码是" + bingCode
							+ "(验证码30分钟后失效).祝您幸运赢大奖!本条信息免费";

				} else {
					smscodes = "尊敬的用户,您本次取消绑定手机号所使用的验证码是" + bingCode
							+ "(验证码30分钟后失效).祝您幸运赢大奖!本条信息免费";

				}
				logger.info("短信发送内容：" + smscodes);
				// 发送短信
				boolean lean=true;
				lean = TuserinfoUtil.sendSms(mobileid, smscodes);
				int newcount = Global.newsDAO.selectPhoneInfoCount(userName);
				if (lean) {
					String message = "验证码已发送到您绑定的手机,请注意查收(您今天还可以获取"+(3-newcount)+"次验证码)";
					request.setAttribute("message", message);
					request.setAttribute("userName", userName);
					request.setAttribute("mobileid", mobileid);
					request.setAttribute("type", type);
					request.getSession().setAttribute("bingCode", bingCode);
					return "wap/userinfo/bindingMobileGetCode";
				} else {
					String message = "短信发送失败,请重新获取,如有问题,请致电客服帮助解决";
					request.setAttribute("message", message);
					request.setAttribute("userName", userName);
					request.setAttribute("mobileid", mobileid);
					request.setAttribute("type", type);
					request.getSession().setAttribute("bingCode", "");
					return "wap/userinfo/bindingMobileGetCode";
				}
//				JSONObject jsonObject = TuserinfoUtil.sendMsgForWapPhoneBind(mobileid);
//				String errorCode = jsonObject.getString("errorCode");
//				int newcount = Global.newsDAO.selectPhoneInfoCount(userName);
//				if ("0".equals(errorCode)) {
//					String bingCode = jsonObject.getString("value");
//					int result = Global.newsDAO.insertPhoneInfo(userno, userName, mobileid, bingCode);
//					logger.info("添加用户绑定手机号验证码信息：userno:"+userno+",userName:"+userName+",mobileid:"+mobileid+",bingCode:"+bingCode);
//					logger.info("添加"+(result==1 ? "成功" : "失败"));
//
//					String message = "验证码已发送到您绑定的手机,请注意查收(您今天还可以获取"+(3-newcount)+"次验证码)";
//					request.setAttribute("message", message);
//					request.setAttribute("userName", userName);
//					request.setAttribute("mobileid", mobileid);
//					request.setAttribute("type", type);
//					request.getSession().setAttribute("bingCode", bingCode);
//					return "wap/userinfo/bindingMobileGetCode";
//				} else {
//					String message = "短信发送失败,请重新获取,如有问题,请致电客服帮助解决";
//					request.setAttribute("message", message);
//					request.setAttribute("userName", userName);
//					request.setAttribute("mobileid", mobileid);
//					request.setAttribute("type", type);
//					request.getSession().setAttribute("bingCode", "");
//					return "wap/userinfo/bindingMobileGetCode";
//				}

			} catch (Exception e) {
				logger.error("用户绑定手机号发送验证码异常:" + e.getMessage());
				request.setAttribute("bindingMobile", mobileid);
				request.setAttribute("userName", userName);
				request.setAttribute("message", "服务器忙，请稍后再试");
				return "wap/userinfo/bindingMobileDetail";
			}
		} else {
			request.setAttribute("bindingMobile", mobileid);
			request.setAttribute("userName", userName);
			return "wap/userinfo/bindingMobileDetail";
		}
	}

	/**
	 * 绑定手机号
	 * 
	 */
	@RequestMapping(value="/bindingMobile.jspa")
	public String bindingMobile(HttpServletRequest request, HttpServletResponse response)
			throws PatternSyntaxException, JSONException,
			UnsupportedEncodingException {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();

		String mobileid = request.getParameter("mobileid"); // 绑定手机号
		String userName = request.getParameter("userName");// 用户名
		String checkcode = request.getParameter("checkcode");// 用户验证码
		String type = request.getParameter("type");// 用户操作状态
//		String jsessionid = (String) request.getSession().getAttribute("sessionid");

		String bingCode = (String) request.getSession()
				.getAttribute("bingCode");
		if (bingCode == null || "".equals(bingCode)) {
			request.setAttribute("message", "验证码已过期，请重新获取");
			request.setAttribute("userName", userName);
			request.setAttribute("mobileid", mobileid);
			request.setAttribute("type", type);
			return "wap/userinfo/bindingMobileGetCode";
		}
		if (!bingCode.equals(checkcode)) {
			request.setAttribute("message", "验证码不正确，请重新填写");
			request.setAttribute("userName", userName);
			request.setAttribute("mobileid", mobileid);
			request.setAttribute("type", type);
			return "wap/userinfo/bindingMobileGetCode";
		}
		request.getSession().removeAttribute("bingCode");
		// 验证绑定手机号
		boolean bool = true;
		if (mobileid == null || "".equals(mobileid)) {
			request.setAttribute("message", "手机号不能为空");
			logger.info(mobileid + "手机号不能为空");
			bool = false;
		}
		if (!VerificationAlgorithmUtil.verifyMobile(mobileid)) {
			request.setAttribute("message", "手机号格式不正确");
			logger.info(mobileid + "手机号格式不正确");
			bool = false;
		}

		if (bool) {
			String errorCode = "";
			errorCode = TuserinfoUtil.bingMobile(userno, mobileid);
//			JSONObject object = TuserinfoUtil.userBindPhone(jsessionid, mobileid, userno, userName, checkcode);
//			logger.info("手机绑定返回："+object);
//			errorCode = object.getString("errorCode");
			logger.info("绑定手机号返回errorCode：" + errorCode);
			JSONObject jsonObject = CommonUtil.getUserinfoByUserno(userno);
			if ("0".equals(errorCode)) {
				logger.info(userName + ",绑定手机号成功");
				request.setAttribute("message", "绑定成功");
				if ("0".equals(jsonObject.get("errorCode"))) {
					JSONObject jsonObject1 = jsonObject.getJSONObject("value");
					mobileid = formatStr(jsonObject1.getString("mobileid"));
					String nickName = jsonObject1.getString("nickname");
					String name = jsonObject1.getString("name");
					String certid = jsonObject1.getString("certid");
					if(!"".equals(nickName)&&!" ".equals(nickName)&&!"null".equals(nickName)&&nickName!=null
							&&!"".equals(name)&&!" ".equals(name)&&!"null".equals(name)&&name!=null
							&&!"".equals(certid)&&!"111111111111111111".equals(certid)&&!"null".equals(certid)&&certid!=null){
						String result = TuserinfoUtil.addTuserinfoScore(userno, 1);
						JSONObject object = JSONObject.fromObject(result);
						String errorcode = object.getString("errorCode");
						boolean value = object.getBoolean("value");
						if("0".equals(errorcode)){
							logger.info("调用增加积分接口,完善信息"+(value==true ? "第一次增加积分" : "已经增加过没有增加"));
						}
					}
					request.setAttribute("mobileid", mobileid);
					request.setAttribute("userName", userName);
				} else {
					return "wap/userinfo/login";
				}
				return "wap/userinfo/bindingMobileDetail";
			} else {
				request.setAttribute("message", "绑定手机号失败，请稍后再试");
				if ("0".equals(jsonObject.get("errorCode"))) {
					JSONObject jsonObject1 = jsonObject.getJSONObject("value");
					mobileid = formatStr(jsonObject1.getString("mobileid"));
					request.setAttribute("mobileid", mobileid);
					request.setAttribute("userName", userName);
				}
				logger.error(userName + ",绑定手机失败,errorCode:" + errorCode);
				return "wap/userinfo/bindingMobileDetail";
			}
		} else {
			request.setAttribute("userName", userName);
			return "wap/userinfo/bindingMobileDetail";
		}
	}

	/**
	 * 取消绑定手机号
	 * 
	 */
	@RequestMapping(value="/cancelBindingMobile.jspa")
	public String cancelBindingMobile(HttpServletRequest request,
			HttpServletResponse response) throws PatternSyntaxException,
			JSONException, UnsupportedEncodingException {

		String mobileid = request.getParameter("mobileid"); // 绑定手机号
		String userName = request.getParameter("userName");// 用户名

		String userno = "";
		if (!"".equals(userName) && userName != null) {
			userno = CommonUtil.getUsernoByUserName(userName);
		} else {
			return "wap/userinfo/login";
		}

		String errorCode = "";
		errorCode = TuserinfoUtil.bingMobile(userno, " ");
		logger.info("取消手机号绑定返回errorCode：" + errorCode);
		JSONObject jsonObject = CommonUtil.getUserinfoByUserno(userno);
		if ("0".equals(errorCode)) {
			logger.info(userName + ",取消手机号绑定成功");
			request.setAttribute("message", "取消成功");
			if ("0".equals(jsonObject.get("errorCode"))) {
				JSONObject jsonObject1 = jsonObject.getJSONObject("value");
				mobileid = formatStr(jsonObject1.getString("mobileid"));
				request.setAttribute("mobileid", mobileid);
				request.setAttribute("userName", userName);
			} else {
				return "wap/userinfo/login";
			}
			return "wap/userinfo/bindingMobileDetail";
		} else {
			request.setAttribute("message", "取消手机号绑定失败，请稍后再试");
			if ("0".equals(jsonObject.get("errorCode"))) {
				JSONObject jsonObject1 = jsonObject.getJSONObject("value");
				mobileid = formatStr(jsonObject1.getString("mobileid"));
				request.setAttribute("mobileid", mobileid);
				request.setAttribute("userName", userName);
			}
			logger.error(userName + ",取消手机号绑定失败,errorCode:" + errorCode);
			return "wap/userinfo/bindingMobileDetail";
		}
	}

	@RequestMapping(value="/toBindingAndsendSMS.jspa")
	public String toBindingAndsendSMS(HttpServletRequest request,
			HttpServletResponse response) {
		String actionForward = "";
		String bindingMobileGetCode = "";
		String bindingMobile = "";
		bindingMobileGetCode = request.getParameter("bindingMobileGetCode");
		bindingMobile = request.getParameter("bindingMobile");
		if (!"".equals(bindingMobileGetCode) && bindingMobileGetCode != null) {
			actionForward = this.bindingMobileGetCode(request, response);
		}
		if (!"".equals(bindingMobile) && bindingMobile != null) {
			try {
				actionForward = this.bindingMobile(request, response);
			} catch (PatternSyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return actionForward;
	}
	/**
	 * 查询用户余额(lottery接口)
	 * 
	 * @param maping
	 * @param form
	 *            request response
	 * @throws JSONException
	 * @responseDate balance余额
	 */
	@RequestMapping(value="/getLotteryAccount.jspa")
	public String getLotteryAccount(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();		
		String url = lottery + "select/getAccount";
		String parameter = "userno=" + userno;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		JSONObject ojb = JSONObject.fromObject(re);
		String errorCode = ojb.getString("errorCode");
		logger.info("取到的userno：" + userno + "errorCode=" + errorCode
				+ "调用Lottery返回的re：" + re);
		String balance = "", freezebalance = "", drawbalance = "";
		if (errorCode.equals("0") || errorCode == "0") {
			JSONObject value = ojb.getJSONObject("value");
			balance = value.getString("balance");// 总金额
			freezebalance = value.getString("freezebalance");// 冻结金额
			drawbalance = value.getString("drawbalance");// 可提现金额
			logger.info("用lottery接口获取的剩余金额balance：" + balance
					+ "冻结金额freezebalance：" + freezebalance
					+ "可提现金额drawbalance：" + drawbalance);
			BigDecimal balancebd = new BigDecimal(balance);
			BigDecimal freezebalancebd = new BigDecimal(freezebalance);
			BigDecimal drawbalancebd = new BigDecimal(drawbalance);
			BigDecimal bd = new BigDecimal(Double.toString(100));
			double balanceDouble = (balancebd.divide(bd,100,BigDecimal.ROUND_HALF_UP).doubleValue());//总金额
			double freezebalanceDouble = (freezebalancebd.divide(bd,100,BigDecimal.ROUND_HALF_UP).doubleValue());//冻结金额
			double drawbalanceDouble = (drawbalancebd.divide(bd,100,BigDecimal.ROUND_HALF_UP).doubleValue());
			double s = (new BigDecimal(balanceDouble+"").subtract(new BigDecimal(freezebalanceDouble+""))).doubleValue();
			double freeDrawBalance = s>=drawbalanceDouble ? drawbalanceDouble
					: s ;//可提现金额
			double ableToBet =  s ;//可投注金额
			logger.info("账户金额balanceDouble:"+balanceDouble+",冻结金额freezebalanceDouble:"+freezebalanceDouble
					+",可提现金额freeDrawBalance:"+freeDrawBalance+",可投注金额ableToBet:"+ableToBet);
			String balanceStr = balanceDouble+"";
			if(balanceStr.substring(balanceStr.indexOf(".")+1).length()==1){
				balanceStr = balanceStr+"0";
			}
			String freezebalanceStr = freezebalanceDouble+"";
			if(freezebalanceStr.substring(freezebalanceStr.indexOf(".")+1).length()==1){
				freezebalanceStr = freezebalanceStr+"0";
			}
			String ableToBetStr = ableToBet+"";
			if(ableToBetStr.substring(ableToBetStr.indexOf(".")+1).length()==1){
				ableToBetStr = ableToBetStr+"0";
			}
			String freeDrawBalanceStr = freeDrawBalance+"";
			if(freeDrawBalanceStr.substring(freeDrawBalanceStr.indexOf(".")+1).length()==1){
				freeDrawBalanceStr = freeDrawBalanceStr+"0";
			}
			request.setAttribute("balance", balanceStr);
			request.setAttribute("FreezeBalanceFloat",freezebalanceStr);
			request.setAttribute("AbleToBet", ableToBetStr);
			request.setAttribute("ableToTcash", freeDrawBalanceStr);
			return "wap/query/balanceSelectResult1";

		}
		request.setAttribute("error", ErrorCode.Select_NotExist.value);
		return "wap/query/balanceSelectResult1";
	}


	/**
	 * 用户留言
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/saveMsg.jspx",method=RequestMethod.POST)
	public String saveMsg(Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = "";
		if(tuserInfoBean!=null){
			userno = tuserInfoBean.getUserno();
		}
		String content = request.getParameter("content")==null ? "" :request.getParameter("content") ;
		String detail = request.getParameter("detail")==null ? "" : request.getParameter("detail");
		if ("".equals(content) || content == null) {
			model.addAttribute("messageError","内容不能为空！");
			return "wap/userinfo/feedBack";
		}
		logger.info("content="+content+",detail="+detail);
		JSONObject jsonObject = new JSONObject();
		content = StringUtils.encodeUrlString(content);
		jsonObject.put("userno", userno);
		jsonObject.put("content", content);
		jsonObject.put("detail", detail);
		String result = TuserinfoUtil.saveMsg(jsonObject);
		JSONObject object = JSONObject.fromObject(result);
		String errorCode = object.getString("errorCode");
		if("0".equals(errorCode)){

			//不登陆 不给积分
			if(!"".equals(userno) ){
				String result1 = TuserinfoUtil.addTuserinfoScore(userno, 7);
				JSONObject jsonObject1 = JSONObject.fromObject(result1);
				String errorCode1 = jsonObject1.getString("errorCode");
				boolean value = jsonObject1.getBoolean("value");
				if("0".equals(errorCode1)){
					logger.info("用户留言调用增加积分接口,"+(value==true ? "每天前3次留言增加积分" : "没有增加"));
				}
			}
			model.addAttribute("messageError","留言成功！");
		}else if("000002".equals(errorCode)){
			model.addAttribute("messageError","10分钟内不要重复提交留言！");
		}else{
			model.addAttribute("messageError","留言失败！");
		}
		return "wap/userinfo/feedBackResult";
	}
	/**
	 * 留言查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/selectMsgs.jspa")
	public String selectMsgs(Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String startLine = request.getParameter("startLine")==null ? "0" : request.getParameter("startLine");
		String endLine = request.getParameter("endLine")==null ? "5" : request.getParameter("endLine");
		JSONObject condition = new JSONObject();
		condition.put("EQS_userno", userno);
		String result = TuserinfoUtil.selectMsgs(condition, startLine, endLine);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			List<MsgRequest> msgRequests= new ArrayList<MsgRequest>();
			MsgRequest msgRequest = null;
			for(int i = 0;i<jsonArray.size()&&i<20;i++){
				msgRequest = new MsgRequest();
				JSONObject object = jsonArray.getJSONObject(i);
				String content = object.get("content")==null ? "" : object.getString("content");
				String reply = object.get("reply")==null ? "" : object.getString("reply");
				if("null".equals(reply)){
					reply = "";
				}
				msgRequest.setContent(content);
				msgRequest.setReply(reply);
				msgRequests.add(msgRequest);
			}
			Boolean nextPage = false;
			if(Integer.parseInt(totalPage)>Integer.parseInt(currentPageNo)){
				nextPage = true;
			}
			Boolean upPage = false;
			if(Integer.parseInt(currentPageNo)>1){
				upPage = true;
			}
			model.addAttribute("startLine",startLine);
			model.addAttribute("endLine",Integer.parseInt(endLine));
			model.addAttribute("nowPage",currentPageNo);
			model.addAttribute("nextPage",nextPage);
			model.addAttribute("upPage",upPage);
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("msgRequests",msgRequests);

		}
		return "wap/userinfo/feedBackDetail";
	}
//	/**
//	 * 用户反馈查询
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value="/selectfeedBack.jspx",method=RequestMethod.POST)
//	public String selectfeedBack(HttpServletRequest request, HttpServletResponse response) {
//		List<FeedBack> feedBacks = new ArrayList<FeedBack>();
//		String startPage = request.getParameter("startPage") == null ? "0"
//				: request.getParameter("startPage");
//		String lineNumber = request.getParameter("lineNumber") == null ? "10"
//				: request.getParameter("lineNumber");
//		int count = Global.feedBackDAO.selectFeedBackCount();
//		feedBacks = Global.feedBackDAO.selectFeedBack(
//				Integer.parseInt(startPage), Integer.parseInt(lineNumber));
//		int maxLine = (count % 10) == 0 ? (count / 10) : (count / 10) + 1;
//		request.setAttribute("feedBacks", feedBacks);
//		request.setAttribute("startPage", Integer.parseInt(startPage));
//		request.setAttribute("lineNumber", Integer.parseInt(lineNumber));
//		request.setAttribute("MaxLine", maxLine);
//		return "wap/userinfo/feedBackList";
//	}
//
//	/**
//	 * 用户反馈解答
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value="/insertAnswerFeedBack.sjpx",method=RequestMethod.POST)
//	public String insertAnswerFeedBack(HttpServletRequest request,
//			HttpServletResponse response) {
//		try {
//			String answer = request.getParameter("answer");
//			String id = request.getParameter("id");
//			FeedBack feedBack = new FeedBack();
//			if ("".equals(answer) || answer == null) {
//				request.setAttribute("message", "内容不能为空");
//				return "wap/userinfo/feedBack";
//			}
//			logger.info("用户反馈解答,answer=" + answer);
//			Global.feedBackDAO.updateAnswerFeedBack(answer,
//					Integer.parseInt(id));
//			feedBack = Global.feedBackDAO.selectFeedBackById(Integer
//					.parseInt(id));
//			request.setAttribute("feedBack", feedBack);
//			request.setAttribute("message", "提交成功");
//		} catch (Exception e) {
//			request.setAttribute("message", "提交失败");
//			e.printStackTrace();
//		}
//		return "wap/userinfo/insertAnswerFeedBack";
//	}
//
//	/**
//	 * 用户反馈解答修改
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value="/updateAnswerFeedBack.jspx",method=RequestMethod.POST)
//	public String updateAnswerFeedBack(HttpServletRequest request,
//			HttpServletResponse response) {
//		try {
//			String answer = request.getParameter("answer");
//			String id = request.getParameter("id");
//			FeedBack feedBack = new FeedBack();
//			if ("".equals(answer) || answer == null) {
//				request.setAttribute("message", "内容不能为空");
//				return "wap/userinfo/updateAnswerFeedBack";
//			}
//			logger.info("用户反馈解答,answer=" + answer);
//			Global.feedBackDAO.updateAnswerFeedBack(answer,
//					Integer.parseInt(id));
//			feedBack = Global.feedBackDAO.selectFeedBackById(Integer
//					.parseInt(id));
//			request.setAttribute("feedBack", feedBack);
//			request.setAttribute("mess", "mess");
//			request.setAttribute("message", "提交成功");
//		} catch (Exception e) {
//			request.setAttribute("message", "提交失败");
//			e.printStackTrace();
//		}
//		return "wap/userinfo/updateAnswerFeedBack";
//	}
//
//	/**
//	 * 用户反馈解答查询
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value="/selectAnswerFeedBackById.jspx",method=RequestMethod.POST)
//	public String selectAnswerFeedBackById(HttpServletRequest request,
//			HttpServletResponse response) {
//		FeedBack feedBack = new FeedBack();
//		String id = request.getParameter("id");
//		String type = request.getParameter("type");
//		feedBack = Global.feedBackDAO.selectFeedBackById(Integer.parseInt(id));
//		request.setAttribute("feedBack", feedBack);
//		if ("HF".equals(type)) {
//			return "wap/userinfo/insertAnswerFeedBack";
//
//		} else {
//			return "wap/userinfo/updateAnswerFeedBack";
//
//		}
//	}
//
//	/**
//	 * 用户反馈查询
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	@RequestMapping(value="/selectFeedBackList.jspx",method=RequestMethod.POST)
//	public String selectFeedBackList(HttpServletRequest request,
//			HttpServletResponse response) {
//		List<FeedBack> feedBacks = new ArrayList<FeedBack>();
//		String startPage = request.getParameter("startPage") == null ? "0"
//				: request.getParameter("startPage");
//		String lineNumber = request.getParameter("lineNumber") == null ? "10"
//				: request.getParameter("lineNumber");
//		int count = Global.feedBackDAO.selectUserFeedBackCount();
//		feedBacks = Global.feedBackDAO.selectUserFeedBack(
//				Integer.parseInt(startPage), Integer.parseInt(lineNumber));
//		int maxLine = (count % 10) == 0 ? (count / 10) : (count / 10) + 1;
//		request.setAttribute("feedBacks", feedBacks);
//		request.setAttribute("startPage", Integer.parseInt(startPage));
//		request.setAttribute("lineNumber", Integer.parseInt(lineNumber));
//		request.setAttribute("MaxLine", maxLine);
//		return "wap/userinfo/selectFeedBackList";
//	}
	
	/**
	 * 查询积分
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findScoreByUserno.jspa")
	public void findScoreByUserno(Model model, HttpServletRequest request, HttpServletResponse response){
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String result = TuserinfoUtil.findScoreByUserno(userno);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String score = jsonObjectValue.getString("score");
			model.addAttribute("score",score);
		}
	}
	/**
	 * 查询积分明细
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/findScoreDetailByUserno.jspa")
	public String findScoreDetailByUserno(Model model, HttpServletRequest request, HttpServletResponse response){
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		findScoreByUserno(model, request, response);
		String startLine = request.getParameter("startLine")==null ? "0" : request.getParameter("startLine");
		String endLine = request.getParameter("endLine")==null ? "10" : request.getParameter("endLine");
		String result = TuserinfoUtil.findScoreDetailByUserno(userno,startLine,endLine);
		logger.info("查询积分返回："+result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			String totalPage = jsonObjectValue.getString("totalPage");
//			String maxResult = jsonObjectValue.getString("maxResult");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			List<Score> scores = new ArrayList<Score>();
			Score score = null;
			for(int i = 0;i<jsonArray.size()&&i<10;i++){
				score = new Score();
				JSONObject object = jsonArray.getJSONObject(i);
				score.setId((i+1+(Integer.parseInt(currentPageNo)-1)*10)+"");
				String createTime = object.getString("createTime");
				if(createTime!=null&&!"".equals(createTime)&&!"null".equals(createTime)){
					createTime = dateFormat.format(new Date(Long.parseLong(createTime)));
				}else{
					continue;
				}
				score.setCreateTime(createTime);
				if("-1".equals(object.getString("scoreType"))){
					score.setScore("-"+object.getString("score"));
				}else{
					score.setScore("+<a style='color:red'>"+object.getString("score")+"</a>");
				}
				String scoreType = getScoreType(object.getString("scoreType"));
				score.setScoreType(scoreType);
				scores.add(score);
			}
			Boolean nextPage = false;
			if(Integer.parseInt(totalPage)>Integer.parseInt(currentPageNo)){
				nextPage = true;
			}
			Boolean upPage = false;
			if(Integer.parseInt(currentPageNo)>1){
				upPage = true;
			}
			model.addAttribute("startLine",startLine);
			model.addAttribute("endLine",Integer.parseInt(endLine));
			model.addAttribute("nowPage",currentPageNo);
			model.addAttribute("nextPage",nextPage);
			model.addAttribute("upPage",upPage);
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("scores",scores);

		}
		return "wap/userinfo/findScoreDetail";
	}
	public static String getScoreType(String scoreType){
		if("1".equals(scoreType)){
			scoreType = "注册并完善信息";
		}else if("2".equals(scoreType)){
			scoreType = "普通投注";
		}else if("3".equals(scoreType)){
			scoreType = "发起追号";
		}else if("4".equals(scoreType)){
			scoreType = "发起合买";
		}else if("5".equals(scoreType)){
			scoreType = "参与合买";
		}else if("6".equals(scoreType)){
			scoreType = "用户充值";
		}else if("7".equals(scoreType)){
			scoreType = "留言建议";
		}else if("8".equals(scoreType)){
			scoreType = "用户登录";
		}else if("99".equals(scoreType)){
			scoreType = "赠送积分";
		}else if("-1".equals(scoreType)){
			scoreType = "兑换彩金";
		}
		return scoreType;
	}
	@RequestMapping("/transScore.jspa")
	public String transScore(Model model, HttpServletRequest request, HttpServletResponse response){
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String tradScore = request.getParameter("tradScore")==null ? "" : request.getParameter("tradScore");//兑换积分
		String score = request.getParameter("score")==null ? "" : request.getParameter("score");//总积分
		String handsel  = request.getParameter("handsel")==null ? "" : request.getParameter("handsel");//彩金
		String validateResult = TuserinfoUtil.validateScore(tradScore, score, handsel);
		if(!"".equals(validateResult)){
			model.addAttribute("messageError",validateResult);
			return "wap/userinfo/transScoreResult";
		}
		String result = TuserinfoUtil.transScore(userno, tradScore);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			model.addAttribute("messageError","兑换成功");
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String money = jsonObjectValue.getString("money");
			model.addAttribute("money",Integer.parseInt(money)/100);
			JSONObject jsonObjectTuserinfoScore = jsonObjectValue.getJSONObject("tuserinfoScore");
			String newScore = jsonObjectTuserinfoScore.getString("score");
			model.addAttribute("score",newScore);
			model.addAttribute("errorCode",errorCode);
			return "wap/userinfo/transScoreResult";
		}
		model.addAttribute("errorCode",errorCode);
		model.addAttribute("messageError","兑换失败，请稍后重试");
		return "wap/userinfo/transScoreResult";
	}
	/**
      * 获取字符串的长度，如果有中文，则每个中文字符计为2位
      *
      * @param value
      *            指定的字符串
      * @return 字符串的长度
      */
     public int chineseLength(String value) {
         int valueLength = 0;
         String chinese = "[\u0391-\uFFE5]";
         /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
         for (int i = 0; i < value.length(); i++) {
             /* 获取一个字符 */
             String temp = value.substring(i, i + 1);
             /* 判断是否为中文字符 */
             if (temp.matches(chinese)) {
                 /* 中文字符长度为2 */
                 valueLength += 2;
             } else {
                 /* 其他字符长度为1 */
                 valueLength += 1;
             }
         }
         return valueLength;
     }
     /**
      * 查询消息设置信息
     * @param smstype 1中奖2开奖4追号
     * @param model
     * @param request
     * @return
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/findUserSetting.jspa")
	public String findUserSetting(
			@RequestParam(value = "smstype", defaultValue = "") String smstype,
			Model model,HttpServletRequest request) throws JSONException, UnsupportedEncodingException{
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();

		if(!"".equals(smstype)&&smstype!=null){
			BigDecimal bigDecimal = new BigDecimal(smstype);
			JSONObject jsonObject = TuserinfoUtil.findUserSetting(userno, bigDecimal);
			String errorCode = jsonObject.getString("errorCode");
			if("0".equals(errorCode)){
				JSONArray array = jsonObject.getJSONObject("value").getJSONArray("sendChannels");
				
				for (int i = 0; i < array.size(); i++) {
					JSONObject object = array.getJSONObject(i);
					//0短信1邮件2IOS
					model.addAttribute("sendtype"+object.getString("sendtype"), object.getString("needToSend"));
					model.addAttribute("id"+object.getString("sendtype"), object.getString("id"));
					logger.info("查询消息设置信息："+"sendtype"+object.getString("sendtype")+"=="+object.getString("sendtype")+"::needToSend:"+object.getString("needToSend"));
				}
			}
		}
		
		return "wap/userinfo/buyLotterySettingDetail";
	}
    /**
     * 取消设置
     * @param id
     * @param model
     * @param request
     * @return
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/cancelUserSetting.jspa")
	public String cancelUserSetting(
			@RequestParam(value = "id", defaultValue = "") String id,
			Model model,HttpServletRequest request) throws JSONException, UnsupportedEncodingException{
		if(!"".equals(id)&&id!=null){
			long l = Long.parseLong(id);
			JSONObject jsonObject = TuserinfoUtil.setSendChannel(l, 0);
			String errorCode = jsonObject.getString("errorCode");
			if("0".equals(errorCode)){
				model.addAttribute("messageError", "取消成功！");
			}else{
				model.addAttribute("messageError", "取消失败！");
			}
		}else{
 			return "wap/userinfo/login";
 		}
		
		return "wap/userinfo/settingResult";
	}
    /**
     * 定制(目前只用于短信)
     * @param id
     * @param model
     * @param request
     * @return
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/updateUserSetting.jspa")
 	public String updateUserSetting(
 			@RequestParam(value = "id", defaultValue = "") String id,
 			Model model,HttpServletRequest request) throws JSONException, UnsupportedEncodingException{
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userName = tuserInfoBean.getUserName();
		String userno = tuserInfoBean.getUserno();
    	if(!"".equals(id)&&id!=null){
 			JSONObject userinfo = CommonUtil.getUserinfoByUserno(userno);
 			String mobile= "";
			if ("0".equals(userinfo.get("errorCode"))) {
				JSONObject jsonObject = userinfo.getJSONObject("value");
				mobile = formatStr(jsonObject.getString("mobileid"));
				if("".equals(mobile)||mobile==null||"null".equals(mobile)){
					model.addAttribute("messageError", "请先绑定手机号！");
					return "wap/userinfo/settingResult";
				}
			}else{
				return "wap/userinfo/login";
			}
 			long l = Long.parseLong(id);
 			JSONObject jsonObject = TuserinfoUtil.setSendChannel(l, 1);
 			String errorCode = jsonObject.getString("errorCode");
 			if("0".equals(errorCode)){
 				model.addAttribute("messageError", "定制成功！");
 			}else{
 				model.addAttribute("messageError", "定制失败！");
 			}
 		}else{
 			return "wap/userinfo/login";
 		}
 		
 		return "wap/userinfo/settingResult";
 	}
    /**
     * 查询是否设置自动登录
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/hasTuserloginfo.jspa")
   	public String hasTuserloginfo(
   			Model model,HttpServletRequest request){
   		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
   		String userno = tuserInfoBean.getUserno();
   		String result = TuserinfoUtil.hasTuserloginfo(userno);
   		JSONObject jsonObject = JSONObject.fromObject(result);
   		String errorCode = jsonObject.getString("errorCode");
   		if("0".equals(errorCode)){
   			String value = jsonObject.getString("value");
   			if(Integer.parseInt(value)>0){
   				model.addAttribute("check", "1");
   			}else{
   				model.addAttribute("check", "0");
   			}
   		}
   		return "wap/userinfo/androidSettingDetail";
   	}
    /**
    * 取消自动登录
    * @param model
    * @param request
    * @return
    */
   @RequestMapping("/deleteTuserloginfo.jspa")
  	public String deleteTuserloginfo(
  			Model model,HttpServletRequest request){
  		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
  		String userno = tuserInfoBean.getUserno();
  		String result = TuserinfoUtil.deleteTuserloginfo(userno);
  		JSONObject jsonObject = JSONObject.fromObject(result);
  		String errorCode = jsonObject.getString("errorCode");
  		if("0".equals(errorCode)){
  			model.addAttribute("messageError", "取消成功！");
  		}else{
  			model.addAttribute("messageError", "取消失败！");
  		}
  		return "wap/userinfo/settingResult";
  	}
	public void getRandom(HttpServletRequest request, Model model) {
		Random rdm = new Random();
		int transctionId = rdm.nextInt();
		model.addAttribute("token", transctionId + "");
		request.setAttribute("token", transctionId + "");
		request.getSession().setAttribute(transctionId + "", "false");
	}
}
