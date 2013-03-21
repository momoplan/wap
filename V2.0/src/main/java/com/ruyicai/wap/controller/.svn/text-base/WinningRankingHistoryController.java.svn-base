package com.ruyicai.wap.controller;

import static com.ruyicai.wap.Global.rbint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;

@RequestMapping("/winningRankingHistory")
@Controller
public class WinningRankingHistoryController {
	private static final String ranking = rbint.getString("ranking");
	private static final Logger logger = Logger.getLogger(WinningRankingHistoryController.class);
	/**
	 * 用户中奖历史排行
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping("/userRankingHistory.jspx")
	public String userRankingHistory(
			@RequestParam(value="type",defaultValue="5") int type,
			Model model){
		logger.info("用户中奖历史排行");
		//得到时间格式
		String time = getTime(type);
		List<Map<String, String>> userRankingHistoryList = getUserRankingHistoryList(time, type);
		model.addAttribute("userRankingHistoryList", userRankingHistoryList);
		model.addAttribute("type", type);
		return "wap/userRankingHistory";
	}
	/**
	 * 中奖历史排行根据排行类型得到时间格式
	 * DAY(1, "日统计"), WEEK(2, "周统计"), MONTH(3, "月统计"), YEAR(4, "年统计"), ALL(5, "总排行")
	 * 日统计和周统计。格式是yyyy-MM-dd月统计yyyy-MM年统计yyyy总排行all
	 * @param type
	 * @return
	 */
	public String getTime(int type){
		String time = "";
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateTime = dateFormat.format(date);
		if(type==1){
			time = dateTime;
		}else if(type==2){
			time = dateTime;
		}else if(type==3){
			time = dateTime.substring(0, dateTime.length()-3);
		}else if(type==4){
			time = dateTime.substring(0, dateTime.length()-6);
		}else if(type==5){
			time = "all";
		}
		return time;
	}
	/**
	 * 中奖历史排行处理后台返回结果，得到中奖历史排行列表
	 * @param time
	 * @param type
	 * @return
	 */
	public List<Map<String, String>> getUserRankingHistoryList(String time,int type){
		List<Map<String, String>> userRankingHistoryList = new ArrayList<Map<String,String>>();
		String result = selectUserRankingHistory(time, type);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		logger.info("中奖历史排行处理后台返回结果:"+jsonObject);
		if("0".equals(errorCode)){
			String str = jsonObject.getString("value");
			JSONArray jsonArray =JSONArray.fromObject(str);
			for(int i=0;i<jsonArray.size();i++){
				Map<String, String> userRankingHistoryMap = new HashMap<String, String>();
				JSONObject userRankingHistory = jsonArray.getJSONObject(i);
				String userName = userRankingHistory.getString("username");
				String nickName = userRankingHistory.getString("nickname");
				String mobileId = userRankingHistory.getString("mobileId");
				String prizeAmt = userRankingHistory.getString("prizeAmt");
				nickName = getNickName(userName, nickName, mobileId);
				prizeAmt = prizeAmt.substring(0, prizeAmt.length()-2)+"."+prizeAmt.substring(prizeAmt.length()-2);
				userRankingHistoryMap.put("nickName", nickName);
				userRankingHistoryMap.put("prizeAmt", prizeAmt);
				userRankingHistoryList.add(userRankingHistoryMap);
			}
		}
		return userRankingHistoryList;
	}
	/**
	 * 中奖历史排行调用后台接口
	 * @param time
	 * @param type
	 * @return
	 */
	public String selectUserRankingHistory(String time,int type){
		String url = ranking+"selectUserRankingHistory";
		String parameter = "time="+time+"&type="+type;
		String result = CommonUtil.setUrlByPOST(url, parameter);
		return result;
	}
	/**
	 * 中奖历史排行得到页面显示用户名称(昵称，用户名，手机号)
	 * @param userName
	 * @param nickName
	 * @param mobileId
	 * @return
	 */
	public String getNickName(String userName,String nickName,String mobileId){
		if(nickName!=null&&!"".equals(nickName)&&!"null".equals(nickName)){
			return nickName;
		}else if(userName!=null&&!"".equals(userName)&&!"null".equals(userName)){
			return getHideName(userName);
		}else if(mobileId!=null&&!"".equals(mobileId)&&!"null".equals(mobileId)){
			return getHideName(mobileId);
		}
		return "";
	}
	/**
	 * 中奖历史排行手机号格式隐藏中间四位
	 * @param name
	 * @return
	 */
	public String getHideName(String name){
		String hideName = "";
		boolean verifyMobileId = VerificationAlgorithmUtil
				.verifyMobileId(name.trim());
		if (verifyMobileId && name.length() > 10) {
			hideName = name.substring(0, 3) + "****"
					+ name.substring(7, 11);
		}else{
			hideName = name;
		}
		return hideName;
	}
	
}
