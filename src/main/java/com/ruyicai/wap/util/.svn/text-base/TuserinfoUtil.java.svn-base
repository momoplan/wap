package com.ruyicai.wap.util;

import static com.ruyicai.wap.Global.rbint;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

/**
 * 用户信息
 * 
 */

public class TuserinfoUtil {
	private static final Logger logger = Logger
			.getLogger(TuserinfoUtil.class);
	public static String lottery = rbint.getString("lottery");
	public static String mgr = rbint.getString("mgr");
	public static String scorecenter = rbint.getString("scorecenter");
	private static String  usersCenterUrl = rbint.getString("usersCenterUrl");
	private static String  msgcenter = rbint.getString("msgcenter");

	/**
	 * 修改用户密码(lottery)
	 * @param userno
	 * @param newPassword
	 * @return
	 */
	public static String resetPassword(String userno,String newPassword){
		String errorCode = "";
		String url = lottery +"tuserinfoes/resetPassword";
		String parameter = "userno="+userno+"&password="+newPassword;
		String result = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("修改密码lottery返回："+result);
		try {
			JSONObject jsonObject = JSONObject.fromObject(result);
			errorCode = jsonObject.getString("errorCode");
		} catch (JSONException e) {
			logger.error("TuserinfoUtil-resetPassword:修改用户密码异常");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return errorCode;
	}
	
//	/**
//	 * 修改用户信息(lottery)
//	 * @param nickname
//	 * @param name
//	 * @param certid
//	 * @param phone
//	 * @param email
//	 * @param address
//	 * @param qq
//	 * @param msn
//	 * @return
//	 */
//	public static String updateUserinfo(String userno ,String nickname,String name,String certid,String phone,String email,String address,String qq,String msn){
//		String url = lottery + "tuserinfoes/modify";
//		String parameter = "userno="+userno+"&nickname="+nickname+"&name=" + name + "&certid=" + certid
//				+ "&phone=" + phone + "&email=" + email+"&address="+address+"&qq=" + qq + "&msn=" + msn;
//		// POST 提交数据
//		String re = CommonUtil.setUrlByPOST(url, parameter);
//		logger.info(lottery + "tuserinfoes/modify?" + parameter);
//		logger.info("调用lottery修改返回:" + re);
//		JSONObject json;
//		String errorcode = "";
//		try {
//			json = JSONObject.fromObject(re);
//			errorcode = json.getString("errorCode");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return errorcode;
//	}
	/**
	 * 修改用户信息(lottery)
	 * @param nickname
	 * @param name
	 * @param certid
	 * @return
	 */
	public static String updateUserinfo(String userno ,String nickname,String name,String certid){
		String url = lottery + "tuserinfoes/modify";
		String parameter = "userno="+userno+"&nickname="+nickname+"&name=" + name + "&certid=" + certid;
		// POST 提交数据
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info(lottery + "tuserinfoes/modify?" + parameter);
		logger.info("调用lottery修改返回:" + re);
		JSONObject json;
		String errorcode = "";
		try {
			json = JSONObject.fromObject(re);
			errorcode = json.getString("errorCode");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return errorcode;
	}
	public static String updateUserinfo1(String userno ,String name,String certid){
		String url = lottery + "tuserinfoes/modify";
		String parameter = "userno="+userno+"&name=" + name + "&certid=" + certid;
		// POST 提交数据
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info(lottery + "tuserinfoes/modify?" + parameter);
		logger.info("调用lottery修改返回:" + re);
		JSONObject json;
		String errorcode = "";
		try {
			json = JSONObject.fromObject(re);
			errorcode = json.getString("errorCode");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return errorcode;
	}
	/**
	 * 绑定解绑手机号(lottery)
	 * @param nickname
	 * @param name
	 * @param certid
	 * @param phone
	 * @param email
	 * @param address
	 * @param qq
	 * @param msn
	 * @return
	 */
	public static String bingMobile(String userno ,String mobileid){
		String url = lottery + "tuserinfoes/modify";
		String parameter = "userno="+userno+"&mobileid="+mobileid;
		// POST 提交数据
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info(lottery + "tuserinfoes/modify?" + parameter);
		logger.info("调用lottery绑定手机号返回:" + re);
		JSONObject json;
		String errorcode = "";
		try {
			json = JSONObject.fromObject(re);
			errorcode = json.getString("errorCode");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return errorcode;
	}
	
	public static String validateUserInfo(String nickName,String name,String certID){
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		if("".equals(nickName)||nickName==null){
			return "昵称不能为空！";
		}else if(nickName.length()<4 ||nickName.length()>16){
			return "昵称长度不正确！";
		}else{
			Matcher m = p.matcher(nickName);
			if(m.find()) 
				return "昵称不能带特殊字符！";
		}
		if("".equals(name)||name==null){
			return "昵称不能为空！";
		}else{
			Matcher m = p.matcher(name);
			if(m.find()) 
				return "真实姓名不能带特殊字符！";
		}
		
		if (certID == null || (certID.trim()).length() != 15
				&& (certID.trim()).length() != 18) {
			return "身份证号码必须是15位或18位！";
		}
		Pattern pattern1 = Pattern.compile("^[0-9]{15}");
		Pattern pattern2 = Pattern.compile("^[0-9]{17}[0-9a-zA-Z]{1}");
		Matcher matcher1 = pattern1.matcher(certID);
		Matcher matcher2 = pattern2.matcher(certID);
		boolean isMatcher1 = matcher1.matches();
		boolean isMatcher2 = matcher2.matches();
		if (!isMatcher1 && !isMatcher2) {
			return "身份证号码格式错误！";
		}
		if (certID.length() == 15) {
			String userAge = certID.substring(6, 12);
			userAge = "19" + userAge;
			SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
			String sysTime = simple.format(new Date());
			try {
				Date userDate = simple.parse(userAge);
				Date sysDate = simple.parse(sysTime);
				Long day = (sysDate.getTime() - userDate.getTime())
						/ (24 * 60 * 60 * 1000);
				long years = Math.round(day / 365);
				if (years < 18) {
					return "你还不满18周岁!";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			String userAge = certID.substring(6, 14);
			SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
			String sysTime = simple.format(new Date());

			try {
				Date userDate = simple.parse(userAge);
				Date sysDate = simple.parse(sysTime);
				Long day = (sysDate.getTime() - userDate.getTime())
						/ (24 * 60 * 60 * 1000);
				long years = Math.round(day / 365);
				if (years < 18) {
					return "你还不满18周岁!";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	public static void getBalance(Model model,String userno){
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
			model.addAttribute("balanceTotal",
					Double.parseDouble(balance) / 100);// 总金额
			model.addAttribute("balanceFree",
					Double.parseDouble(freezebalance) / 100);// 冻结金额
			model.addAttribute("balanceTobet", Double.parseDouble(balance)
					/ 100 - Double.parseDouble(freezebalance) / 100);
			model.addAttribute(
					"balanceDraw",
					Double.// 可提现金额
					parseDouble(balance) / 100
							- Double.parseDouble(freezebalance) / 100 >= Double
							.parseDouble(drawbalance) / 100 ? Double
							.parseDouble(drawbalance) / 100 : Double
							.parseDouble(balance)
							/ 100
							- Double.parseDouble(freezebalance) / 100);

		}
	}
	/**
	 * 用户留言
	 * @param jsonObject
	 * @return
	 */
	public static String saveMsg(JSONObject jsonObject){
		String url = msgcenter + "msg/saveMsg";
		String parameter = "body=" + jsonObject;
		logger.info("用户留言："+url+"?"+parameter);
		String re = CommonUtil.setUrlByPOST(url, parameter);
		return re;
	}
	/**
	 * 用户留言查询
	 * @param condition
	 * @param startLine
	 * @param endLine
	 * @return
	 */
	public static String selectMsgs(JSONObject condition,String startLine,String endLine){
		String url = msgcenter + "msg/selectMsgs";
		String parameter = "condition=" + condition+"&startLine="+startLine+"&endLine="+endLine;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		return re;
	}
	/**
	 * 查看积分
	 * @param userno
	 * @return
	 */
	public static String findScoreByUserno(String userno){
		String url = scorecenter + "findScoreByUserno";
		String parameter = "userno=" + userno;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		return re;
	}
	/**
	 * 查询积分明细
	 * @param userno
	 * @return
	 */
	public static String findScoreDetailByUserno(String userno,String startLine,String endLine){
		String url = scorecenter + "findScoreDetailByUserno";
		String parameter = "userno=" + userno+"&startLine=" + startLine+"&endLine=" + endLine;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		return re;
	}
	/**
	 * 积分兑换
	 * @param userno
	 * @return
	 */
	public static String transScore(String userno,String score){
		String url = scorecenter + "transScore2Money";
		String parameter = "userno=" + userno+"&score=" + score;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		return re;
	}
	/**
	 * 增加积分
	 * @param userno
	 * @return
	 */
	public static String addTuserinfoScore(String userno,int scoreType){
		String url = scorecenter + "addTuserinfoScore";
		String parameter = "userno=" + userno+"&scoreType=" + scoreType;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		return re;
	}
	public static String validateScore(String tradScore,String score,String handsel){
		int s = 500;
		String regex = "^[1-9][0-9]*$";
		if("".equals(tradScore)||tradScore==null){
			return "兑换积分不能为空";
		}else if(!tradScore.matches(regex)){
			return "兑换积分输入不正确";
		}else if(Integer.parseInt(tradScore)/s<1&&Integer.parseInt(tradScore)%s!=0){
			return "兑换积分必须是"+s+"的整倍数";
		}else if(Integer.parseInt(tradScore)>Integer.parseInt(score)){
			return "兑换积分不能大于总积分";
		}else if(Integer.parseInt(score)<s){
			return "对不起，您的积分不足";
		}
		if("".equals(handsel)||handsel==null){
			return "兑换彩金不能为空";
		}else if(!handsel.matches(regex)){
			return "兑换彩金输入不正确";
		}else if(Integer.parseInt(handsel)*s!=Integer.parseInt(tradScore)){
			return "兑换积分和彩金输入不匹配";
		}
		return "";
	}
	/**
	 * 手机绑定发送短信
	 * @param jsessionid
	 * @param new_phone
	 * @return
	 */
	//user/phonebind!sendMsgForPhoneBind;jsessionid=40832F7AB20AECB58FB441C768D2B79D?new_phone=13717504105
	public static JSONObject sendMsgForPhoneBind(String jsessionid, String new_phone){
		String url = usersCenterUrl + "user/phonebind!sendMsgForPhoneBind;jsessionid="+jsessionid;
		String parameter = "new_phone="+new_phone;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("发送短信,后台返回内容："+re);
		JSONObject js = JSONObject.fromObject(re);
		return js;
	}
	/**
	 * 手机绑定发送短信
	 * @param new_phone
	 * @return
	 */
	//user/phonebind!sendMsgForWapPhoneBind?new_phone=13717504105
	public static JSONObject sendMsgForWapPhoneBind( String new_phone){
		String url = usersCenterUrl + "user/phonebind!sendMsgForWapPhoneBind";
		String parameter = "new_phone="+new_phone;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("发送短信,后台返回内容："+re);
		JSONObject js = JSONObject.fromObject(re);
		return js;
	}
	/**
	 * 发送短信
	 * @param userMobile
	 * @param text
	 * @return
	 */
	public static JSONObject sendMsgForWap(String usermobile,String text){
		String url = usersCenterUrl + "user/phonebind!sendMsgForWap";
		String parameter = "usermobile="+usermobile+"&text="+text;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("发送短信,后台返回内容："+re);
		JSONObject js = JSONObject.fromObject(re);
		return js;
	}
	/**
	 * @param jsessionid
	 * @param new_phone
	 * @param userno
	 * @param username
	 * @param rand
	 * @return
	 */
	//http://users.ruyicai.com/user/phonebind!userBindPhone;jsessionid=F831F519CA5A86C8D3C19671AEBE714D?mobileid=13120207500&userno=00000117&username=1234&rand=6276
	public static JSONObject userBindPhone(String jsessionid, String new_phone,String userno,String username,String rand){
		String url = usersCenterUrl + "user/phonebind!userBindPhone;jsessionid="+jsessionid;
		String parameter = "&new_phone="+new_phone+"&userno="+userno+"&username="+username+"&rand="+rand;
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("发送短信,后台返回内容："+re);
		JSONObject js = JSONObject.fromObject(re);
		return js;
	}
	/**
	 * 调用后台lottery短信接口(暂时用)
	 * /sms/send
	 * 
	 *      @RequestParam("mobileIds") String[] mobileIds,
			@RequestParam("text") String text) {
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean sendSms(String mobileids, String text) throws JSONException, UnsupportedEncodingException{
		String url = msgcenter + "sms/send";
		String parameter = "mobileIds=" + mobileids +"&text="+URLEncoder.encode(text, "UTF-8");
		String re = CommonUtil.setUrlByPOST(url, parameter);
		logger.info("发送短信,后台返回内容："+re);
		JSONObject js = JSONObject.fromObject(re);
	    String errorCode = js.getString("errorCode");
	    if("0".equals(errorCode)){
	    	return true;
	    }
		return false;
	}
	/**
	 * 查询用户设置
	 * @param userno
	 * @param smstype事件类型， 1为开奖
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject findUserSetting(String userno, BigDecimal smstype) throws JSONException, UnsupportedEncodingException{
		String url = msgcenter + "usersetting/findUserSetting?userno=" + userno +"&smstype="+smstype;
		String re = HttpUtil.getMethod(url);
		logger.info("查询用户设置,后台返回内容："+re);
		JSONObject jsonObject = JSONObject.fromObject(re);
		return jsonObject;
	}
	/**
	 * 修改用户某一事件设置是否发送
	 * @param userno
	 * @param smstype事件类型， 1为开奖
	 * @param needToSend是否有效 0:不发送,1:发送
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean setUserSMSTiming(String userno, BigDecimal smstype,int needToSend) throws JSONException, UnsupportedEncodingException{
		String url = msgcenter + "usersetting/setUserSMSTiming?userno=" + userno +"&smstype="+smstype+"&needToSend="+needToSend;
		String re = HttpUtil.getMethod(url);
		logger.info("修改用户某一事件设置是否发送,后台返回内容："+re);
		JSONObject js = JSONObject.fromObject(re);
	    String errorCode = js.getString("errorCode");
	    if("0".equals(errorCode)){
	    	return true;
	    }
		return false;
	}
	/**
	 * 修改短信、邮件、iphone设置是否发送
	 * @param id 记录编号
	 * @param needToSend 是否有效 0:不发送,1:发送
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	public static JSONObject setSendChannel(long id,int needToSend) throws JSONException, UnsupportedEncodingException{
		String url = msgcenter + "usersetting/setSendChannel?id=" + id +"&needToSend="+needToSend;
		String re = HttpUtil.getMethod(url);
		logger.info("修改用户某一事件设置是否发送,后台返回内容："+re);
		JSONObject js = JSONObject.fromObject(re);
		return js;
	}
	
	/**
	 * 查询是否自动登录
	 * @param userno
	 * @return
	 */
	public static String hasTuserloginfo(String userno) {
		String url = lottery+"tuserinfoes/hasTuserloginfo";
		StringBuffer paramStr = new StringBuffer();
		paramStr.append("userno="+userno);
		
		String result = HttpUtil.sendByPostUtF(url, paramStr.toString());
		logger.info("查询是否自动登录返回结果:"+result+",paramStr:"+paramStr.toString());
		return result;
	}
	
	/**
	 * 判断是否自动登录
	 * @param random
	 * @return
	 */
	public static String getTuserloginfo(String random) {
		String url = lottery+"tuserinfoes/getTuserloginfo";
		StringBuffer paramStr = new StringBuffer();
		paramStr.append("random="+random);
		
		String result = HttpUtil.sendByPostUtF(url, paramStr.toString());
		logger.info("判断是否自动登录返回结果:"+result+",paramStr:"+paramStr.toString());
		return result;
	}
	
	/**
	 * 取消自动登录
	 * @param userno
	 * @return
	 */
	public static String deleteTuserloginfo(String userno) {
		String url = lottery+"tuserinfoes/deleteTuserloginfo";
		StringBuffer paramStr = new StringBuffer();
		paramStr.append("userno="+userno);
		
		String result = HttpUtil.sendByPostUtF(url, paramStr.toString());
		logger.info("取消自动登录返回结果:"+result+",paramStr:"+paramStr.toString());
		return result;
	}
	
	/**
	 * 删除Token记录
	 * @param id 记录编号
	 * @return
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean removeToken(long id) throws JSONException, UnsupportedEncodingException{
		String url = msgcenter + "usersetting/removeToken?id=" + id;
		String re = HttpUtil.getMethod(url);
		logger.info("删除Token记录,后台返回内容："+re);
		JSONObject js = JSONObject.fromObject(re);
	    String errorCode = js.getString("errorCode");
	    if("0".equals(errorCode)){
	    	return true;
	    }
		return false;
	}
}

