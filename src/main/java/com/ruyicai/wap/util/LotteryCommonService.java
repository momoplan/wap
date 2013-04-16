package com.ruyicai.wap.util;

import static com.ruyicai.wap.Global.rbint;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

public class LotteryCommonService {

	private static Logger logger = Logger.getLogger(LotteryCommonService.class);
	
	private static String lotteryUrl = rbint.getString("lottery");
	
	/**
	 * 注册
	 * @param mobileId
	 * @param password
	 * @param certId
	 * @param channel
	 * @return
	 */
	public static String register(String mobileId, String password,
			String certId, String channel, String state) {
		String registerUrl = lotteryUrl + "tuserinfoes/register";
		String param = "mobileid="+mobileId+"&password="+password+"&channel="+channel+"&certid="+certId+"&userState="+state;
		try {
			String resultStr = HttpUtil.sendByPostUtF(registerUrl, param);
			logger.info("注册时返回结果:"+resultStr+",mobileId="+mobileId);
			return resultStr;
		} catch (Exception e) {
			logger.error("mobileId="+mobileId+",注册时发生异常:"+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据手机查询用户是否存在
	 * @param mobileid
	 * @return
	 * @throws IOException 
	 */
	public static String queryUsersByMobileid(String mobileid, String subChannel) {
		try {
			String urlStr = lotteryUrl + "tuserinfoes?";
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("json");
			paramStr.append("&find=ByMobileid");
			paramStr.append("&mobileid=").append(mobileid);
			if (subChannel != null && !subChannel.trim().equals("")) {
				paramStr.append("&subChannel=").append(subChannel);
			}
			logger.info("根据手机查询用户是否存在,url="+urlStr+paramStr.toString());
			String resultStr = HttpUtil.sendByPostUtF(urlStr, paramStr.toString());
			logger.info("mobileid="+mobileid+",根据手机查询用户是否存在,返回结果:"+resultStr);
			/*if (resultStr!=null && !resultStr.trim().equals("")) {
				JSONObject fromObject = JSONObject.fromObject(resultStr);
				if (fromObject.has("errorCode")) {
					return fromObject.getString("errorCode");
				}
			}*/
			return resultStr;
		} catch (Exception e) {
			logger.error("mobileid="+mobileid+",根据手机查询用户是否存在,发生异常:"+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据用户编号查询用户是否存在
	 * @param mobileid
	 * @return
	 * @throws IOException 
	 */
	public static String queryUsersByUserNo(String userNo) {
		try {
			String urlStr = lotteryUrl + "tuserinfoes?";
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("json");
			paramStr.append("&find=ByUserno");
			paramStr.append("&userno=").append(userNo);
			logger.info("根据用户编号查询用户是否存在,url="+urlStr+paramStr.toString());
			String resultStr = HttpUtil.sendByPostUtF(urlStr, paramStr.toString());
			logger.info("userNo="+userNo+",根据用户编号查询用户是否存在,返回结果:"+resultStr);
			/*if (resultStr!=null && !resultStr.trim().equals("")) {
				JSONObject fromObject = JSONObject.fromObject(resultStr);
				if (fromObject.has("errorCode")) {
					return fromObject.getString("errorCode");
				}
			}*/
			return resultStr;
		} catch (Exception e) {
			logger.error("userNo="+userNo+",根据用户编号查询用户是否存在,发生异常:"+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改密码
	 * @param mobileId
	 * @param password
	 * @return
	 */
	public static String resetPassword(String userno, String password) {
		String resetPasswordUrl = lotteryUrl + "tuserinfoes/resetPassword";
		try {
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("userno="+userno);
			paramStr.append("&password="+password);
			String resultStr = HttpUtil.sendByPostUtF(resetPasswordUrl, paramStr.toString());
			logger.info("修改密码的返回结果:"+resultStr+",userno="+userno+",password="+password);
			if (resultStr!=null && !resultStr.trim().equals("")) {
				JSONObject fromObject = JSONObject.fromObject(resultStr);
				if (fromObject.has("errorCode")) {
					return fromObject.getString("errorCode");
				}
			}
		} catch (Exception e) {
			logger.error("userno="+userno+",修改密码发生异常:"+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 修改用户信息
	 * @param mobileId
	 * @param password
	 * @return
	 */
	public static String modifyUserinfo(String userno, String certId) {
		String updateUserInfoUrl = lotteryUrl + "tuserinfoes/modify";
		try {
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("userno="+userno);
			paramStr.append("&certid="+certId);
			//paramStr.append("&password="+"123456");
			String resultStr = HttpUtil.sendByPostUtF(updateUserInfoUrl, paramStr.toString());
			logger.info("修改用户信息的返回结果:"+resultStr+",userno="+userno+",certid="+certId);
			if (resultStr!=null && !resultStr.trim().equals("")) {
				JSONObject fromObject = JSONObject.fromObject(resultStr);
				if (fromObject.has("errorCode")) {
					return fromObject.getString("errorCode");
				}
			}
		} catch (Exception e) {
			logger.error("userno="+userno+",修改用户信息发生异常:"+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得batchcode期号以后的num期
	 * @param lotno
	 * @param batchcode
	 * @param num
	 * @return
	 */
	public static String getAfterIssue(String lotno, String batchcode, String num) {
		String getAfterIssueUrl = lotteryUrl + "select/getAfterIssue";
		try {
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("lotno="+lotno);
			paramStr.append("&batchcode="+batchcode);
			paramStr.append("&num="+num);
			String resultStr = HttpUtil.sendByPostUtF(getAfterIssueUrl, paramStr.toString());
			logger.info("getAfterIssue的返回结果:"+resultStr+",lotno="+lotno+",batchcode="+batchcode+",num="+num);
			if (resultStr!=null && !resultStr.trim().equals("")) {
				JSONObject fromObject = JSONObject.fromObject(resultStr);
				if (fromObject.has("errorCode")&&fromObject.getString("errorCode").equals("0")) {
					return fromObject.getString("value");
				}
			}
		} catch (Exception e) {
			logger.error("getAfterIssue发生异常:"+e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据彩种获取当前期号
	 * @param lotNo
	 * @return
	 */
	public static String getCurrentBatchCodeByLotNo(String lotNo) {
		String getIssueUrl = lotteryUrl + "select/getIssue";
		String batchCode = "";
		try {
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("lotno="+lotNo);
			String resultStr = HttpUtil.sendByPostUtF(getIssueUrl, paramStr.toString());
			logger.info("getCurrentBatchCodeByLotNo的返回结果:"+resultStr+",lotno="+lotNo);
			if (resultStr!=null && !resultStr.trim().equals("")) {
				JSONObject fromObject = JSONObject.fromObject(resultStr);
				if (fromObject.has("errorCode")&&fromObject.getString("errorCode").equals("0")) {
					JSONObject valueObject = (JSONObject)fromObject.get("value");
					JSONObject idObject = (JSONObject)valueObject.get("id");
					batchCode = idObject.getString("batchcode");
				}
			}
		} catch (Exception e) {
			logger.error("getCurrentBatchCodeByLotNo发生异常:"+e.toString());
			e.printStackTrace();
		}
		return batchCode;
	}
	
	
	/**
	 * 获取某场比赛的信息(供竟彩使用)
	 * @param lotno
	 * @param day
	 * @param weekid
	 * @param teamid
	 * @return
	 */
	public static String getjingcaimatches(String lotno, String day, String weekid, String teamid) {
		String getIssueUrl = lotteryUrl + "select/getjingcaimatches";
		try {
			StringBuffer paramStr = new StringBuffer();
			paramStr.append("lotno="+lotno);
			paramStr.append("&day="+day);
			paramStr.append("&weekid="+weekid);
			paramStr.append("&teamid="+teamid);
			String resultStr = HttpUtil.sendByPostUtF(getIssueUrl, paramStr.toString());
			logger.info("getjingcaimatches的返回结果:"+resultStr+",lotno="+lotno+",day="+day+",weekid="+weekid+",teamid="+teamid);
			return resultStr;
		} catch (Exception e) {
			logger.error("getjingcaimatches发生异常:"+e.toString());
			e.printStackTrace();
		}
		return "";
	}
	
}
