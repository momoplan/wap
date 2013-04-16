package com.ruyicai.wap.util;

/**
 * 验证类。
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
 * 创建者：
 * 创建日期：
 * 修改记录：
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class VerificationAlgorithmUtil {
	/**
	 * 发起合买 参数验证
	 * @param subamount
	 * @param baodiamt
	 * @param commission
	 * @param describe
	 * @param isPublic
	 * @param lowestamt
	 * @return
	 */
	public static String validateBetHemai(String amt,String subamount,String baodiamt,String describe,String lowestamt){
		if((subamount.equals("0") || subamount.equals("")) && (baodiamt.equals("0") || baodiamt.equals(""))){
			return  MessageUtil.RUYICAI_HEMAI_BAODIANDBUYAMT_NULL;
		}
		//验证是否是整数
		if(!isInteger(subamount)){
			return MessageUtil.RUYICAI_HEMAI_BUYAMT_NUM;
		}
		if(!isInteger(baodiamt)){
			return MessageUtil.RUYICAI_HEMAI_BAODI_NUM;
		}
		if(!isInteger(lowestamt)){
			return MessageUtil.RUYICAI_HEMAI_lostAMT_NUM;
		}
		//合买订单金额至少6元
		if(Long.valueOf(amt)<6){
			return "发起合买金额不能小于6元";
		}
		//最低跟单金额至少1元
		if(Long.valueOf(lowestamt)<1){
			return MessageUtil.RUYICAI_HEMAI_lostAMT_ONE;
		}
		//总金额不能小于认购金额
		if(Long.valueOf(amt) < Long.valueOf(subamount)){
			return MessageUtil.RUYICAI_HEMAI_BUYAMT_DAYU_TOALAMT;
		}
		//投注总金额=> 保底金额+认购金额
		if(Long.valueOf(amt)<(Long.valueOf(subamount)+Long.valueOf(baodiamt))){
			return MessageUtil.RUYICAI_HEMAI_BUYAMTANDBAODI_DAYU_TOALAMT;
		}
		//投注总金额 =>最低认购金额+认购金额
		if(Long.valueOf(amt)<(Long.valueOf(subamount)+Long.valueOf(lowestamt))){
			return MessageUtil.RUYICAI_HEMAI_BUYAMTANMINAMT_DAYU_TOALAMT;
		}
		//方案宣传不超过50个字符
		if(describe.getBytes().length>50){
			return MessageUtil.RUYICAI_HEMAI_describe_NULL;
		}
		if(describe.indexOf("%")>-1){
			return "方案描述不能带%";
		}
		
		return "";
	}
	/**
	 * 赠彩验证
	 * @return
	 */
	public static String valitZengCai(String giftMessages , String mobiles){
		if("".equals(mobiles.trim())){
			return MessageUtil.RUYICAI_ZENGCAI_MOBILE_NULL;
		}
		String regex = "^((13|14|15|18)\\d{9}\\,)*(13|14|15|18)\\d{9}$";
		String regex2 = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";
		Pattern p = Pattern.compile(regex2);     
	    Matcher m = p.matcher(mobiles);  
	    Pattern p1 = Pattern.compile(regex);     
	    Matcher m1 = p1.matcher(mobiles); 
	    	if(mobiles.indexOf(",")<0){
				if(!m.matches()){
					return MessageUtil.RUYICAI_ZENGCAI_MOBILE_ERROR;
				}
			}else{
				if(!m1.matches()){
					return MessageUtil.RUYICAI_ZENGCAI_MOBILE_ERROR;
				}
			}
//		if(mobiles.indexOf(",")<0){
//			if(!regex2.matches(mobiles)){
//				return messageUtil.RUYICAI_ZENGCAI_MOBILE_ERROR;
//			}
//		}else{
//			if(!regex.matches(mobiles)){
//				return messageUtil.RUYICAI_ZENGCAI_MOBILE_ERROR;
//			}
//		}
		if(giftMessages.length()>20){
			return MessageUtil.RUYICAI_ZENGCAI_GIFTMESSAGE_ERROR;
		}
		return "";
	}
		/**
		 * 验证卡号是不是整数
		 * 
		 * @Title:
		 
		 * @param:
		 * @return:
		 * @exception:
		 */
		public static boolean isInteger(String str) {
			Pattern pattern = Pattern.compile("^([0-9]{1}[0-9]*)");
			Matcher matcher = pattern.matcher(str);
			boolean isMatch = matcher.matches();
			if (!isMatch) {
				return false;
			}
			return true;
		}
//	/**
//	 * 验证字符串中是否含有特殊字符
//	 * 
//	 * @param str
//	 * @return
//	 */
//	public static boolean verifySpecialSign(String str) {
//		if (str.contains("&") || str.contains("<") || str.contains(">")) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * 验证双色球定胆机选输入数据的合法性
	 * 
	 * @param redballDanma
	 * @param redballTuoma
	 * @param blueball
	 * @param beishu
	 * @return
	 */
	// String redballDanma, String redballTuoma, String blueball, String beishu
	public static String verifyDoubleBallDingDanSelfBet(Map<String ,String> map) {
		Pattern pattern1 = Pattern
				.compile("(0[1-9]|1[0-9]|2[0-9]|3[0-3]){1,5}");
		Pattern pattern3 = Pattern.compile("(0[1-9]|1[0-6]){1}");
		if (map.get("redballDanma") != null) {
			Matcher matcher1 = pattern1.matcher((String) map
					.get("redballDanma"));
			if (!matcher1.matches()) {
				return "红球胆码不合法";
			}
			Vector<String> danmaVector = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("redballDanma"));
			if (!verifyRepeat(danmaVector)) {
				return "红球胆码不能重复";
			}

		}
		if (map.get("blueball") != null) {
			Matcher matcher3 = pattern3.matcher((String) map.get("blueball"));
			if (!matcher3.matches()) {
				return "蓝球注码不合法";
			}
		}
		if (map.get("zhushu") != null) {
			// 验证注数
			if (!verifyZhushu((String) map.get("zhushu"))) {
				return "注数不合法";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证双色球单式自选输入数据的合法性
	 * 
	 * @param redZhuma
	 *            红球号码
	 * @param blueZhuma
	 *            蓝球号码
	 * @param beishu
	 *            倍数
	 * @return 错误消息（字符串） null表示正确
	 */
	public static String verifyDoubleBallSingleSelfParams(Map<String, String> map) {
		if (map.get("redZhuma") != null) {
			// 如果红球号码串为null或""不合法
			if (!verifyEmpty((String) map.get("redZhuma"))) {
				return "红球号码不能为空";
			}
			// 红球号码为6个01-33的数字连在一起
			String regex = "(0[1-9]|[1-2]\\d|3[0-3]){6}";
			if (!((String) map.get("redZhuma")).matches(regex)) {
				return "红球号码不合法";
			}
			Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString((String) map.get("redZhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "红球号码有重复的数字";
			}
		}
		if (map.get("blueZhuma") != null) {
			// 如果蓝球号码串为null或""不合法
			if (!verifyEmpty((String) map.get("blueZhuma"))) {
				return "蓝球号码不能为空";
			}
			// 蓝球号码为1个01-16的数字
			String regex = "(0[1-9]|1[0-6]){1}";
			if (!((String) map.get("blueZhuma")).matches(regex)) {
				return "蓝球号码不合法";
			}
		}
		if (map.get("beishu") != null) {
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证双色球单式机选输入数据的合法性
	 * 
	 * @param zhushu
	 *            注数
	 * @param beishu
	 *            倍数
	 * @return 错误消息（字符串） null表示正确
	 */
	public static String verifyDoubleBallSingleAutoParams(String zhushu,
			String beishu, String addNumber) {
		if (isStringFilter(addNumber)) {
			return "输入框中不能输入特殊字符";
		}
		if (isStringFilter(beishu)
				||isStringFilter(zhushu)) {
			return "输入框中不能输入特殊字符";
		}
		// 验证注数
		if (!verifyZhushu(zhushu)) {
			return "注数不合法";
		}
		// 验证倍数
		if (!verifyMultNo(beishu)) {
			return "倍数不合法";
		}
		if (!verifyAddNumber(addNumber)){
			return "追号期数不合法";
		}

		return null;
	}

	/**
	 * 验证大乐透复式机选输入数据的合法性
	 * 
	 * @param redNumber
	 *            红球个数
	 * @param blueNumber
	 *            蓝球号码
	 * @param beishu
	 *            倍数
	 * @return 错误消息（字符串） null表示正确
	 */
	// String redNumber, String blueNumber, String beishu
	public static String verifyDaLeTouAutoBet(Map<String ,String> map) {
		if (map.get("redZhuma") != null) {
			// 如果红球号码串为null或""不合法
			if (!verifyEmpty((String) map.get("redZhuma"))) {
				return "前区号码不能为空";
			}
			// 红球号码为6-20个01-33的数字连在一起
			String regex = "(0[1-9]|[1-2]\\d|3[0-5]){5}";
			if (!((String) map.get("redZhuma")).matches(regex)) {
				return "前区号码不合法";
			}
			// 将注码转换成数组
			Vector<String> v = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString((String) map
					.get("redZhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "前区号码有重复的数字";
			}
		}
		if (map.get("blueZhuma") != null) {
			// 如果蓝球号码串为null或""不合法
			if (!verifyEmpty((String) map.get("blueZhuma"))) {
				return "后区号码不能为空";
			}
			// 蓝球号码为1-16个01-12的数字
			String regex = "(0[1-9]|1[0-2]){2}";
			if (!((String) map.get("blueZhuma")).matches(regex)) {
				return "后区号码不合法";
			}
			Vector<String> v = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("blueZhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "后区号码有重复的数字";
			}
		}
		if (map.get("redSize") != null) {
			// 如果红球个数为null或""不合法
			if (!verifyEmpty((String) map.get("redSize"))) {
				return "前区个数不能为空";
			}
			// 红球个数为6-20之间的数字
			try {
				int r = Integer.parseInt((String) map.get("redSize"));
				if (r < 5 || r > 15) {
					return "前区个数必须在5-15之间";
				}
			} catch (Exception e) {
				return "前区个数必须是个数字";
			}
		}
		if (map.get("zhushu") != null) {
			if (!verifyZhushu((String) map.get("zhushu"))) {
				return "注数不合法";
			}
		}
		if (map.get("blueSize") != null) {
			// 如果蓝球个数为null或""不合法
			if (!verifyEmpty((String) map.get("blueSize"))) {
				return "后区个数不能为空";
			}
			// 蓝球个数为1-16之间的数字
			try {
				int b = Integer.parseInt((String) map.get("blueSize"));
				if (b < 2 || b > 12) {
					return "后区个数必须在2-12之间";
				}
			} catch (Exception e) {
				return "后区个数必须是个数字";
			}
		}
		if (map.get("beishu") != null) {
			// 如果倍数为null或""不合法
			/*
			 * if (!verifyEmpty((String)map.get("beishu"))) { return "倍数不能为空"; }
			 */
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证双色球复式自选输入数据的合法性
	 * 
	 * @param redZhuma
	 *            红球号码
	 * @param blueZhuma
	 *            蓝球号码
	 * @param beishu
	 *            倍数
	 * @return 错误消息（字符串） null表示正确
	 */
	
	public static String verifyDoubleBallMultipleSelfBet(Map<String ,String> map) {
		if (VerificationAlgorithmUtil.isStringFilter((String) map.get("addNumber"))) {
			return "输入框中不能输入特殊字符";
		}
		if (VerificationAlgorithmUtil.isStringFilter((String) map.get("redZhuma"))
				|| VerificationAlgorithmUtil.isStringFilter((String) map.get("blueZhuma"))
				|| VerificationAlgorithmUtil.isStringFilter((String) map.get("beishu"))) {
			return "输入框中不能输入特殊字符";
		}
		if (map.get("redZhuma") != null) {
			// 如果红球号码串为null或""不合法
			if (!verifyEmpty((String) map.get("redZhuma"))) {
				return "红球号码不能为空";
			}
			// 红球号码为6-20个01-33的数字连在一起
			String regex = "(0[1-9]|[1-2]\\d|3[0-3]){6,20}";
			if (!((String) map.get("redZhuma")).matches(regex)) {
				return "红球号码不合法";
			}
			// 将注码转换成数组
			Vector<String> v = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString((String) map
					.get("redZhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "红球号码有重复的数字";
			}
		}
		if (map.get("blueZhuma") != null) {
			// 如果蓝球号码串为null或""不合法
			if (!verifyEmpty((String) map.get("blueZhuma"))) {
				return "蓝球号码不能为空";
			}
			// 蓝球号码为1-16个01-16的数字
			String regex = "(0[1-9]|1[0-6]){1,16}";
			if (!((String) map.get("blueZhuma")).matches(regex)) {
				return "蓝球号码不合法";
			}
			Vector<String> v = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("blueZhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "蓝球号码有重复的数字";
			}
		}
		if (map.get("beishu") != null) {
			// 如果倍数为null或""不合法
			/*
			 * if (!verifyEmpty((String)map.get("beishu"))) { return "倍数不能为空"; }
			 */
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}

		return null;
	}

	/**
	 * 验证大乐透复式自选输入数据的合法性
	 * 
	 * @param redZhuma
	 *            红球号码
	 * @param blueZhuma
	 *            蓝球号码
	 * @param beishu
	 *            倍数
	 * @return 错误消息（字符串） null表示正确
	 */
	
	public static String verifyDaLeTouMultipleSelfBet(Map<String ,String> map) {
		if (map.get("redZhuma") != null) {
			// 如果红球号码串为null或""不合法
			if (!verifyEmpty((String) map.get("redZhuma"))) {
				return "前区号码不能为空";
			}
			// 红球号码为6-20个01-33的数字连在一起
			if (map.get("redZhuma").toString().length() > 30
					|| map.get("redZhuma").toString().length() < 10) {
				return "前区号码个数不能大于15或者小于5";
			}
			String regex = "(0[1-9]|[1-2]\\d|3[0-5]){5,17}";
			if (!((String) map.get("redZhuma")).matches(regex)) {
				return "前区号码不合法";
			}
			// 将注码转换成数组
			Vector<String> v = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString((String) map
					.get("redZhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "前区号码有重复的数字";
			}
		}
		if (map.get("blueZhuma") != null) {
			// 如果蓝球号码串为null或""不合法
			if (!verifyEmpty((String) map.get("blueZhuma"))) {
				return "后区号码不能为空";
			}
			// 蓝球号码为1-16个01-12的数字
			if (map.get("blueZhuma").toString().length() > 24
					|| map.get("blueZhuma").toString().length() < 4) {
				return "后区号码个数不能大于12或者小于2";
			}
			String regex = "(0[1-9]|1[0-2]){2,12}";
			if (!((String) map.get("blueZhuma")).matches(regex)) {
				return "后区号码不合法";
			}
			Vector<String> v = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("blueZhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "后区号码有重复的数字";
			}
		}
		if (map.get("beishu") != null) {
			// 如果倍数为null或""不合法
			/*
			 * if (!verifyEmpty((String)map.get("beishu"))) { return "倍数不能为空"; }
			 */
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}

		return null;
	}

	/**
	 * 验证双色球复式机选输入数据的合法性
	 * 
	 * @param redNumber
	 *            红球个数
	 * @param blueNumber
	 *            蓝球号码
	 * @param beishu
	 *            倍数
	 * @return 错误消息（字符串） null表示正确
	 */
	// String redNumber, String blueNumber, String beishu
	public static String verifyDoubleBallMultipleAutoBet(Map<String, String> map) {
		if (VerificationAlgorithmUtil.isStringFilter((String) map.get("addNumber"))) {
			return "输入框中不能输入特殊字符";
		}
		if (VerificationAlgorithmUtil.isStringFilter((String) map.get("redNumber"))
				|| VerificationAlgorithmUtil.isStringFilter((String) map.get("blueNumber"))
				|| VerificationAlgorithmUtil.isStringFilter((String) map.get("beishu"))) {
			return "输入框中不能输入特殊字符";
		}
		if (map.get("redNumber") != null) {
			// 如果红球个数为null或""不合法
			if (!verifyEmpty((String) map.get("redNumber"))) {
				return "红球个数不能为空";
			}
			// 红球个数为6-20之间的数字
			try {
				int r = Integer.parseInt((String) map.get("redNumber"));
				if (r < 6 || r > 20) {
					return "红球个数必须在6-20之间";
				}
			} catch (Exception e) {
				return "红球个数必须是个数字";
			}
		}
		if (map.get("blueNumber") != null) {
			// 如果蓝球个数为null或""不合法
			if (!verifyEmpty((String) map.get("blueNumber"))) {
				return "蓝球个数不能为空";
			}
			// 蓝球个数为1-16之间的数字
			try {
				int b = Integer.parseInt((String) map.get("blueNumber"));
				if (b < 1 || b > 16) {
					return "蓝球个数必须在1-16之间";
				}
			} catch (Exception e) {
				return "蓝球个数必须是个数字";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证双色球胆拖机选输入数据的合法性
	 * 
	 * @param redballDanma
	 * @param redballTuoma
	 * @param blueballCount
	 * @param beishu
	 * @return
	 */
	// String redballDanma, String redballTuoma, String blueballCount, String
	// beishu
	
	public static String verifyDoubleBallDantuoAutoBet(Map<String ,String> map) {
		if (map.get("redballDanma") != null && map.get("redballTuoma") != null) {
			Pattern pattern = Pattern.compile("\\d{1,2}");
			if (!verifyEmpty((String) map.get("redballDanma"))) {
				return "红球胆码不能为空";
			}
			Matcher matcher = pattern.matcher((String) map.get("redballDanma"));
			if (!matcher.matches()) {
				return "红球胆码为数字";
			}
			int redballDanmaInt = 0;
			try {
				redballDanmaInt = Integer.parseInt((String) map
						.get("redballDanma"));
			} catch (Exception e) {
				return "红球胆码的个数为数字";
			}
			if (!(redballDanmaInt >= 1 && redballDanmaInt <= 5)) {
				return "红球胆码的个数为1-5个";
			}

			if (!verifyEmpty((String) map.get("redballTuoma"))) {
				return "红球拖码不能为空";
			}
			Matcher matcher1 = pattern
					.matcher((String) map.get("redballTuoma"));
			if (!matcher1.matches()) {
				return "红球拖码为数字";
			}
			int redballTuomaInt = 0;
			try {
				redballTuomaInt = Integer.parseInt((String) map
						.get("redballTuoma"));
			} catch (Exception e) {
				return "红球拖码的个数为数字";
			}
			if (!(redballTuomaInt >= 2 && redballTuomaInt <= 20)) {
				return "红球拖码的个数为2-20个";
			}

			if (redballDanmaInt + redballTuomaInt < 7) {
				return "胆码与拖码的总数不能小于7";
			}
			if (redballDanmaInt + redballTuomaInt > 25) {
				return "胆码与拖码的总数不能大于25";
			}
		}
		if (map.get("blueballCount") != null) {
			Pattern pattern = Pattern.compile("\\d{1,2}");
			if (!verifyEmpty((String) map.get("blueballCount"))) {
				return "蓝球个数不能为空";
			}
			Matcher matcher2 = pattern.matcher((String) map
					.get("blueballCount"));
			if (!matcher2.matches()) {
				return "蓝色球个数为数字";
			}
			int blueballCountInt = Integer.parseInt((String) map
					.get("blueballCount"));
			if (blueballCountInt == 0) {
				return "蓝色球个数不能为0";
			}
			if (blueballCountInt >= 17) {
				return "蓝色球个数太多";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证双色球胆拖自选输入数据的合法性
	 * 
	 * @param redballDanma
	 * @param redballTuoma
	 * @param blueball
	 * @param beishu
	 * @return
	 */
	// String redballDanma, String redballTuoma, String blueball, String beishu
	public static String verifyDoubleBallDantuoSelfBet(Map<String ,String> map) {
		if (map.get("redballDanma") == "" || map.get("redballDanma").equals("")) {
			return "红球胆码不能为空";
		}
		if (map.get("redballTuoma") == "" || map.get("redballTuoma").equals("")) {
			return "红球拖码不能为空";
		}
		if (map.get("blueball") == "" || map.get("blueball").equals("")) {
			return "蓝球不能为空";
		}
		Pattern pattern1 = Pattern
				.compile("(0[1-9]|1[0-9]|2[0-9]|3[0-3]){1,5}");
		Pattern pattern2 = Pattern
				.compile("(0[1-9]|1[0-9]|2[0-9]|3[0-3]){2,20}");
		Pattern pattern3 = Pattern.compile("(0[1-9]|1[0-6]){1,16}");
		if (map.get("redballDanma") != null && map.get("redballTuoma") != null) {
			Matcher matcher1 = pattern1.matcher((String) map
					.get("redballDanma"));
			if (!matcher1.matches()) {
				return "红球胆码不合法";
			}
			Vector<String> danmaVector = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("redballDanma"));
			if (!verifyRepeat(danmaVector)) {
				return "红球胆码不能重复";
			}
			Matcher matcher2 = pattern2.matcher((String) map
					.get("redballTuoma"));
			if (!matcher2.matches()) {
				return "红球拖码不合法";
			}
			Vector<String> tuomaVector = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("redballTuoma"));
			if (!verifyRepeat(tuomaVector)) {
				return "红球拖码不能重复";
			}
			String redball = (String) map.get("redballDanma")
					+ (String) map.get("redballTuoma");
			Vector<String> V= LotteryAlgorithmUtil
					.getStringArrayFromString(redball);
			if (!verifyRepeat(V)) {
				return "红球胆码与红球拖码不能重复";
			}
			Vector<String> redballDanmaVector = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("redballDanma"));
			Vector<String> redballTuomaVector = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("redballTuoma"));
			int redballDanmaInt = redballDanmaVector.size();
			int redballTuomaInt = redballTuomaVector.size();
			if (redballDanmaInt + redballTuomaInt < 7) {
				return "胆码与拖码总数不能小于7";
			}
			if (redballDanmaInt + redballTuomaInt > 25) {
				return "胆码与拖码总数不能大于25";
			}
		}
		if (map.get("blueball") != null) {
			Matcher matcher3 = pattern3.matcher((String) map.get("blueball"));
			if (!matcher3.matches()) {
				return "蓝球注码不合法";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证组3复式数据的合法性
	 * 
	 * @param str用户输入的注码
	 * @return
	 */
	// String zhuma, String beishu
	public static String verify3DGroup3Multiple(Map<String ,String> map) {
		if (map.get("zhuma") != null) {
			// 如果字符串为null或""不合法
			if (!verifyEmpty((String) map.get("zhuma"))) {
				return "注码不能为空";
			}
			// 注码为00-09
			String regex = "(0\\d){2,10}";
			if (!((String) map.get("zhuma")).matches(regex)) {
				return "注码格式错误";
			}
			// 将注码转换成数组
			Vector<String> v = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString((String) map
					.get("zhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证排列三组6单式数据的合法性
	 * 
	 * @param zhuma
	 *            注码
	 * @param beishu
	 *            倍数
	 * @return
	 */
	// String zhuma, String beishu
	public static String verifyGroup6Single(Map<String ,String> map) {
		if (map.get("zhuma") != null) {
			// 如果字符串为null或""不合法
			if (!verifyEmpty((String) map.get("zhuma"))) {
				return "注码不能为空";
			}
			// 注码为0-9
			String regex = "(\\d){3}";
			if (!((String) map.get("zhuma")).matches(regex)) {
				return "注码格式有误";
			}
			// 将不带0的注码转换成数组
			Vector<String> v = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString3D((String) map
					.get("zhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证组6单式数据的合法性
	 * 
	 * @param zhuma
	 *            注码
	 * @param beishu
	 *            倍数
	 * @return
	 */
	public static String verify3DGroup6Single(Map<String,String> map) {
		if (map.get("zhuma") != null) {
			// 如果字符串为null或""不合法
			if (!verifyEmpty((String) map.get("zhuma"))) {
				return "注码不能为空";
			}
			// 注码为00-09
			String regex = "(0\\d){3}";
			if (!((String) map.get("zhuma")).matches(regex)) {
				return "注码格式有误";
			}
			// 将注码转换成数组
			Vector<String> v = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString((String) map
					.get("zhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证组3单式数据的合法性
	 * 
	 * @param zhuma1
	 * @param zhuma2
	 * @param beishu
	 * @return
	 */
	// String zhuma1, String zhuma2, String beishu
	public static String verify3DGroup3Single(Map<String ,String> map) {
		if (map.get("zhuma1") != null && map.get("zhuma2") != null) {
			// 注码1不能为空
			if (!verifyEmpty((String) map.get("zhuma1"))) {
				return "注码不能为空 ";
			}
			// 注码为1位数字
			String regex = "(0\\d){1}";
			if (!((String) map.get("zhuma1")).trim().matches(regex)) {
				return "注码格式有误";
			}
			// 注码2不能为空
			if (!verifyEmpty((String) map.get("zhuma2"))) {
				return "注码不能为空";
			}
			if (!((String) map.get("zhuma2")).trim().matches(regex)) {
				return "注码格式有误";
			}
			// 注码1和注码2不能重复
			if (((String) map.get("zhuma2")).trim().equals(
					((String) map.get("zhuma1")).trim())) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证直选单式数据的合法性
	 * 
	 * @param zhuma1
	 * @param zhuma2
	 * @param beishu
	 * @return范晓辉
	 * @author
	 */
	// String hundreds_No, String tens_No,String units_No, String beishu
	public static String verify3DDirectSelectionSingle(Map<String ,String> map) {
		Pattern pattern = Pattern.compile("(0\\d){1,10}");
		if (map.get("hundreds_No") != null) {
			Matcher matcher = pattern.matcher((String) map.get("hundreds_No"));
			// 百位不能为空
			if (!verifyEmpty((String) map.get("hundreds_No"))) {
				return "百位不能为空";
			}
			if (!matcher.matches()) {
				return "注码不合法";
			}
			Vector<String> hundreds_NoVector = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("hundreds_No"));
			if (!verifyRepeat(hundreds_NoVector)) {
				return "百位注码不能重复";
			}
		}
		if (map.get("tens_No") != null) {
			Matcher matcher = pattern.matcher((String) map.get("tens_No"));
			// 个位不能为空
			if (!verifyEmpty((String) map.get("tens_No"))) {
				return "十位不能为空";
			}
			if (!matcher.matches()) {
				return "注码不合法";
			}
			Vector<String> tens_NoVector = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("tens_No"));
			if (!verifyRepeat(tens_NoVector)) {
				return "十位注码不能重复";
			}
		}
		if (map.get("units_No") != null) {
			Matcher matcher = pattern.matcher((String) map.get("units_No"));
			// 十位不能为空
			if (!verifyEmpty((String) map.get("units_No"))) {
				return "个位不能为空";
			}
			if (!matcher.matches()) {
				return "注码不合法";
			}
			Vector<String> units_NoVector = LotteryAlgorithmUtil
					.getStringArrayFromString((String) map.get("units_No"));
			if (!verifyRepeat(units_NoVector)) {
				return "个位注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if ((map.get("hundreds_No").toString().length() / 2)
				* (map.get("units_No").toString().length() / 2)
				* (map.get("tens_No").toString().length() / 2) > 600) {
			return "注数超过限制,不能超过600注";
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证直选复式 百位，十位 个位是否有重复
	 * 
	 * @param zhuma1
	 * @param zhuma2
	 * @param beishu
	 * @return田庆康
	 * @author
	 */
	public static String isRep(String bai, String shi, String ge) {

		String b = bai;
		for (int i = 0; i < b.length(); i++) {
			char c = b.charAt(i);
			if (b.indexOf(c, i + 1) > -1) {
				return "百位数字有重复";
			}
		}

		String s = shi;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (s.indexOf(c, i + 1) > -1) {
				return "十位数字有重复";
			}
		}

		String g = ge;
		for (int i = 0; i < g.length(); i++) {
			char c = g.charAt(i);
			if (g.indexOf(c, i + 1) > -1) {
				return "个位数字有重复";
			}
		}
		return null;
	}

	/**
	 * 验证3D胆拖复式输入数据的合法性
	 * 
	 * @param danma
	 * @param tuoma
	 * @param beishu
	 * @return
	 */
	// String danma, String tuoma, String beishu
	
	public static String verify3DDantuoMultiple(Map<String ,String> map) {
		Pattern pattern1 = Pattern.compile("(\\d){1,2}");
		Pattern pattern2 = Pattern.compile("(\\d){1,9}");
		if (map.get("danma") != null && map.get("tuoma") != null) {
			if (!verifyEmpty((String) map.get("danma"))) {
				return "胆码不能为空";
			}
			Matcher matcher1 = pattern1.matcher((String) map.get("danma"));
			if (!matcher1.matches()) {
				return "胆码不合法";
			}
			Matcher matcher2 = pattern2.matcher((String) map.get("tuoma"));
			if (!matcher2.matches()) {
				return "拖码不合法";
			}
			String zhuma = (String) map.get("danma")
					+ (String) map.get("tuoma");
			Vector<String> zhumaVector = LotteryAlgorithmUtil
					.getStringArrayFromString3D(zhuma);
			if (!verifyRepeat(zhumaVector)) {
				return "胆码注码与拖码注码不能重复";
			}
			Vector<String> danmaVector = LotteryAlgorithmUtil
					.getStringArrayFromString3D((String) map.get("danma"));
			Vector<String> tuomaVector = LotteryAlgorithmUtil
					.getStringArrayFromString3D((String) map.get("tuoma"));
			int danmaInt = danmaVector.size();
			int tuomaInt = tuomaVector.size();
			if (danmaInt + tuomaInt < 4) {
				return "胆码与拖码总数不能小于4";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}

		return null;
	}

	/**
	 * 验证3D和值输入数据的合法性
	 * 
	 * @param hezhi
	 * @param beishu
	 * @return
	 */
	// String hezhi, String beishu
	public static String verify3DHeZhi(Map<String ,String> map) {
		Pattern pattern = Pattern.compile("([0-9]|1[0-9]|2[0-7])");
		if (map.get("hezhi") != null) {
			if (!verifyEmpty((String) map.get("hezhi"))) {
				return "和值不能为空";
			}
			Matcher matcher = pattern.matcher((String) map.get("hezhi"));
			if (!matcher.matches()) {
				return "和值格式不正确";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	
	public static String verifyArray3HeZhi(Map<String ,String> map) {
		Pattern pattern = Pattern.compile("([0-9]|1[0-9]|2[0-7])");
		if (map.get("hezhi") != null) {
			if (!verifyEmpty((String) map.get("hezhi"))) {
				return "和值不能为空";
			}
			Matcher matcher = pattern.matcher((String) map.get("hezhi"));
			if (!matcher.matches()) {
				return "和值格式不正确";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证排列3直选和值输入数据的合法性
	 * 
	 * @param hezhi
	 * @param beishu
	 * @return
	 */
	// String hezhi, String beishu
	
	public static String verifyGroup3HeZhi(Map<String ,String> map) {
		Pattern pattern = Pattern.compile("([1-9]|1[0-9]|2[0-6])");
		if (map.get("hezhi") != null) {
			if (!verifyEmpty((String) map.get("hezhi"))) {
				return "和值不能为空";
			}
			Matcher matcher = pattern.matcher((String) map.get("hezhi"));
			if (!matcher.matches()) {
				return "和值格式不正确";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证3D组6和值输入数据的合法性
	 * 
	 * @param hezhi
	 * @param beishu
	 * @return
	 */
	// String hezhi, String beishu
	
	public static String verify3DGroup6HeZhi(Map<String ,String> map) {
		Pattern pattern = Pattern.compile("([3-9]|1[0-9]|2[0-4])");
		if (map.get("hezhi") != null) {
			if (!verifyEmpty((String) map.get("hezhi"))) {
				return "和值不能为空";
			}
			Matcher matcher = pattern.matcher((String) map.get("hezhi"));
			if (!matcher.matches()) {
				return "和值格式不正确";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证七乐彩定胆输入数据的合法性
	 * 
	 * @param danma
	 * @param zhushu
	 * @param beishu
	 * @return
	 */
	// String danma, String zhushu, String beishu
	
	public static String verifyQilecaiDingDan(Map<String ,String> map) {
		Pattern pattern = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|30){1,6}");
		if (map.get("danma") != null) {
			if (!verifyEmpty((String) map.get("danma"))) {
				return "胆码不能为空";
			}

			Matcher matcher = pattern.matcher((String) map.get("danma"));
			if (!matcher.matches()) {
				return "胆码格式不正确";
			}
			Vector<String> V = LotteryAlgorithmUtil
					.getStringArrayFromString(((String) map.get("danma"))
							.trim());
			if (!verifyRepeat(V)) {
				return "胆码不能重复";
			}
		}
		if (map.get("zhushu") != null) {
			if (!verifyEmpty((String) map.get("zhushu"))) {
				return "注数不能为空";
			}
			if (!verifyZhushu((String) map.get("zhushu"))) {
				return "注数不合法";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证七乐彩单式自选投注参数的合法性
	 * 
	 * @param zhuma
	 * @param beishu
	 * @return
	 */
	// String zhuma, String beishu
	
	public static String verifyQilecaiSingleSelfSelection(Map<String ,String> map) {
		Pattern pattern = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|30){7}");
		if (map.get("zhuma") != null) {
			if (!verifyEmpty((String) map.get("zhuma"))) {
				return "注码不能为空";
			}
			Matcher matcher = pattern.matcher((String) map.get("zhuma"));
			if (!matcher.matches()) {
				return "注码格式不正确";
			}
			Vector<String> V = LotteryAlgorithmUtil
					.getStringArrayFromString(((String) map.get("zhuma"))
							.trim());
			if (!verifyRepeat(V)) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证七乐彩复式自选数据的合法性
	 * 
	 * @param zhuma
	 * @param beishu
	 * @return
	 */
	// String zhuma, String beishu
	
	public static String verifyQilecaiMultipleSelfSelection(Map<String ,String> map) {
		if (map.get("zhuma") != null) {
			if (!verifyEmpty((String) map.get("zhuma"))) {
				return "注码不能为空";
			}
			Pattern pattern = Pattern
					.compile("(0[1-9]|1[0-9]|2[0-9]|30){7,30}");
			Matcher matcher = pattern.matcher((String) map.get("zhuma"));
			if (!matcher.matches()) {
				return "注码格式不正确";
			}
			Vector<String> V = LotteryAlgorithmUtil
					.getStringArrayFromString(((String) map.get("zhuma"))
							.trim());
			if (!verifyRepeat(V)) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法 ";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证 大乐透12选2数据的合法性
	 * 
	 * @param zhuma
	 * @param beishu
	 * @return
	 */
	// String zhuma, String beishu
	public static String verifyDaLeTouSX(Map<String ,String> map) {
		if (map.get("zhuma") != null) {
			if (!verifyEmpty((String) map.get("zhuma"))) {
				return "注码不能为空";
			}
			Pattern pattern = Pattern.compile("(0[1-9]|1[0-2]){2,12}");
			Matcher matcher = pattern.matcher((String) map.get("zhuma"));
			if (!matcher.matches()) {
				return "注码格式不正确";
			}
			Vector<String> V = LotteryAlgorithmUtil
					.getStringArrayFromString(((String) map.get("zhuma"))
							.trim());
			if (!verifyRepeat(V)) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法 ";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证七乐彩胆拖自选数据的合法性
	 * 
	 * @param danma
	 * @param tuoma
	 * @param beishu
	 * @return
	 */
	// String danma, String tuoma, String beishu
	public static String verifyQilecaiDantuoSelfSelection(Map<String ,String> map) {
		if (map.get("danma") != null && map.get("tuoma") != null) {
			if (!verifyEmpty((String) map.get("danma"))) {
				return "胆码不能为空";
			}
			Pattern pattern = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|30){1,6}");
			Matcher matcher = pattern.matcher((String) map.get("danma"));
			if (!matcher.matches()) {
				return "胆码注码格式不正确";
			}
			if (!verifyEmpty((String) map.get("tuoma"))) {
				return "拖码不能为空";
			}
			Pattern pattern1 = Pattern
					.compile("(0[1-9]|1[0-9]|2[0-9]|30){2,29}");
			Matcher matcher1 = pattern1.matcher((String) map.get("tuoma"));
			if (!matcher1.matches()) {
				return "拖码注码格式不正确";
			}
			Vector<String> vector1 = LotteryAlgorithmUtil
					.getStringArrayFromString(((String) map.get("danma"))
							.trim());
			Vector<String> vector2 = LotteryAlgorithmUtil
					.getStringArrayFromString(((String) map.get("tuoma"))
							.trim());
			if (vector1.size() + vector2.size() < 8) {
				return "胆码与拖码总数不能小于8";
			}
			Vector<String> V = LotteryAlgorithmUtil
					.getStringArrayFromString(((String) map.get("danma"))
							.trim() + ((String) map.get("tuoma")).trim());
			if (!verifyRepeat(V)) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证大乐透胆拖自选数据的合法性
	 * 
	 * @param danma
	 * @param tuoma
	 * @param beishu
	 * @return
	 */
	// String danma, String tuoma, String beishu
	
	public static String verifyDaLeTouDantuoSelfSelection(Map<String ,String> map) {

		Pattern pattern = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|3[0-5]){0,4}");
		Matcher matcher = pattern.matcher((String) map.get("reddanma"));
		Pattern patternH = Pattern.compile("(0[1-9]|1[0-2]){0,1}");
		Matcher matcherH = patternH.matcher((String) map.get("bluedanma"));
		if (!matcher.matches()) {
			return "前区胆码注码格式不正确";
		}
		if (!matcherH.matches()) {
			return "后区胆码注码格式不正确";
		}
		Pattern pattern1 = Pattern
				.compile("(0[1-9]|1[0-9]|2[0-9]|3[0-5]){0,15}");
		Matcher matcherT = pattern1.matcher((String) map.get("redtuoma"));
		Pattern patternH1 = Pattern.compile("(0[1-9]|1[0-2]){2,12}");
		Matcher matcherT1 = patternH1.matcher((String) map.get("bluetuoma"));
		if (!matcherT.matches()) {
			return "前区拖码注码格式不正确";
		}
		if (!matcherT1.matches()) {
			return "后区拖码注码格式不正确";
		}
		Vector<String> vector1 = LotteryAlgorithmUtil
				.getStringArrayFromString(((String) map.get("reddanma")).trim());
		Vector<String> vector2 = LotteryAlgorithmUtil
				.getStringArrayFromString(((String) map.get("redtuoma")).trim());
		if (vector1.size() + vector2.size() < 6) {
			return "前区胆码与拖码总数不能小于6";
		}
		Vector<String> vector3 = LotteryAlgorithmUtil
				.getStringArrayFromString(((String) map.get("bluedanma"))
						.trim());
		Vector<String> vector4 = LotteryAlgorithmUtil
				.getStringArrayFromString(((String) map.get("bluetuoma"))
						.trim());
		if (vector3.size() + vector4.size() < 2) {
			return "后区区胆码与拖码总数不能小于2";
		}
		Vector<String> V = LotteryAlgorithmUtil
				.getStringArrayFromString(((String) map.get("reddanma")).trim()
						+ ((String) map.get("redtuoma")).trim());
		if (!verifyRepeat(V)) {
			return "前区注码不能重复";
		}
		Vector<String> vectorH = LotteryAlgorithmUtil
				.getStringArrayFromString(((String) map.get("bluedanma"))
						.trim() + ((String) map.get("bluetuoma")).trim());
		if (!verifyRepeat(vectorH)) {
			return "后区注码不能重复";
		}

		if (map.get("beishu") != null) {
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	// 验证注码是否重复
	public static boolean verifyRepeat(Vector<String> v) {
		for (int i = 0; i < v.size(); i++) {
			for (int j = v.size() - 1; j > i; j--) {
				if (v.get(i).equals(v.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	// 验证注数
	public static boolean verifyZhushu(String zhushu) {
		// 如果注数为null或""不合法
		if (!verifyEmpty(zhushu)) {
			return false;
		}
		// 注数为1-999的数字
		// String regex = "[1-9]{1}[0-9]{0,2}";
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,2}");
		Matcher matcher = pattern.matcher(zhushu);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	// 验证参与合买 认购金额
	/**
	 * @param case_buyNumByUser
	 *            认购金额
	 * @param basenum
	 *            剩余金额
	 * @param buyAmtByUser
	 *            最低认购金额
	 * @param safeAmt
	 *            保底金额
	 * @param newbaodiAmt
	 *            可保底金额
	 * @return
	 */
	public static String verifyHM(String case_buyNumByUser, String basenum,
			String buyAmtByUser, String safeAmt, String newbaodiAmt) {
		if (!verifyEmpty(case_buyNumByUser)) {
			return "认购金额不能为空";
		}
		if (!verifyEmpty(safeAmt)) {
			return "保底金额不能为空";
		}
		if("0".equals(safeAmt)&&"0".equals(case_buyNumByUser)){
			return "请输入认购金额";
		}
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,5}");
		if (!"0".equals(case_buyNumByUser)) {
			Matcher matcher = pattern.matcher(case_buyNumByUser);
			if (!matcher.matches()) {
				return "认购金额输入不合法";
			}
		}
		if (!"0".equals(safeAmt)) {
			Matcher matcher1 = pattern.matcher(safeAmt);
			if (!matcher1.matches()) {
				return "保底金额输入不合法";
			}
		}

		if ((Integer.valueOf(case_buyNumByUser) + Integer.valueOf(safeAmt)) > Integer
				.valueOf(basenum)) {
			return "金额输入不正确";
		}
		if (Integer.valueOf(buyAmtByUser) < Integer.valueOf(basenum)) {
			if (Integer.valueOf(case_buyNumByUser) < Integer
					.valueOf(buyAmtByUser)
					&& Integer.valueOf(case_buyNumByUser) != 0) {
				return "认购金额不能小于最低认购金额";
			}
		} else {
			int cb = Integer.valueOf(case_buyNumByUser);
			int b = Integer.valueOf(basenum);
			if (cb != b) {
				return "认购金额输入不正确";
			}
		}
		if (Integer.valueOf(case_buyNumByUser) > Integer.valueOf(basenum)) {
			return "认购金额不能大于剩余金额";
		}
		if (Integer.valueOf(safeAmt) > Integer.valueOf(newbaodiAmt)) {
			return "保底金额不能大于剩余保底金额";
		}
		if (Integer.valueOf(case_buyNumByUser) < 1
				&& Integer.valueOf(safeAmt) < 1) {
			return "认购金额和保底金额至少一个不小于1元";
		}

		return "";
	}

	// 验证倍数
	public static boolean verifyMultNo(String multNo) {
		// 验证倍数是否为空
		if (!verifyEmpty(multNo)) {
			return false;
		}
		/*
		 * if (multNo.trim().equals("")) { return true; }
		 */
		// 倍数为数字
		// String re = "^\\d{1,2}$";
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(multNo);
		if (!matcher.matches()) {
			return false;
		}
		// 倍数不大于50
		int num = Integer.parseInt(multNo);
		if (!(num > 0 && num <= 50)) {
			return false;
		}
		return true;
	}

	// 验证字符串是否为空
	public static boolean verifyEmpty(String str) {
		// 如果字符串为null或""不合法
		if (str == null || str.trim() == null || str.trim().equals("")) {
			return false;
		}
		return true;
	}

	/**
	 * 验证组6复式数据的合法性
	 * 
	 * @param zhuma
	 *            注码
	 * @param beishu
	 *            倍数
	 * @return
	 */
	// String zhuma, String beishu
	
	public static String veritfy3DGroup6Multiple(Map<String ,String> map) {
		if (map.get("zhuma") != null) {
			// 如果字符串为null或""不合法
			if (!verifyEmpty((String) map.get("zhuma"))) {
				return "注码不能为空";
			}
			// 注码为00-09
			String regex = "(0\\d){4,9}";
			if (!((String) map.get("zhuma")).matches(regex)) {
				return "注码为4-9个数字";
			}
			// 将注码转换成数组
			Vector<String> v = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString((String) map
					.get("zhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 验证单选单复式数据的合法性
	 * 
	 * @param zhuma
	 *            注码
	 * @param beishu
	 *            倍数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String verify3DSingleSelectSingleMultiple(Map map) {
		if (map.get("zhuma") != null) {
			// 如果字符串为null或""不合法
			if (!verifyEmpty((String) map.get("zhuma"))) {
				return "注码不能为空";
			}
			// 注码为00-09
			String regex = "(0\\d){3,9}";
			if (!((String) map.get("zhuma")).matches(regex)) {
				return "注码为3-9个数字";
			}
			// 将注码转换成数组
			Vector<String> v = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString((String) map
					.get("zhuma"));
			// 验证注码是否重复
			if (!verifyRepeat(v)) {
				return "注码不能重复";
			}
		}
		if (map.get("beishu") != null) {
			// 验证倍数
			if (!verifyMultNo((String) map.get("beishu"))) {
				return "倍数不合法";
			}
		}
		if (map.get("addNumber") != null) {
			if (!verifyAddNumber((String) map.get("addNumber"))) {
				return "追号期数不合法";
			}
		}
		return null;
	}

	/**
	 * 如意号码年月日份的验证
	 * 
	 * @param year
	 * @return
	 */
	public static String verifyYear(String year, String month, String day) {
		if (!verifyEmpty(year)) {
			return "年份不能为空";
		}
		String re = "^([1-2]\\d{3})$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(year);
		if (!matcher.matches()) {
			return "年份不符合要求";
		}
		// 获取当前时间
		String currentTime = CommonUtil.getCurrentTime();
		String year1 = currentTime.substring(0, 4);
		if (Integer.parseInt(year) > Integer.parseInt(year1)) {

			return "年份超出当前日历年份";
		}
		String verifyMonthAndDay = verifyMonthAndDay(month, day);
		if (verifyMonthAndDay != null) {
			return verifyMonthAndDay;
		}
		// 判断是否是闰年
		if (new GregorianCalendar().isLeapYear(Integer.parseInt(year))) {
			if (Integer.parseInt(month) == 2) {
				if (Integer.parseInt(day) > 29) {
					return "月份不合法";
				}
			}
		} else {
			if (Integer.parseInt(month) == 2) {
				if (Integer.parseInt(day) > 28) {
					return "月份不合法";
				}
			}
		}
		return null;
	}

	/**
	 * 如意号码验证月份日份
	 * 
	 * @param month
	 * @param day
	 * @return
	 */
	public static String verifyMonthAndDay(String month, String day) {
		if (!verifyEmpty(month)) {
			return "月份不能为空";
		}
		if (!verifyEmpty(day)) {
			return "日份不能为空";
		}
		String re = "^\\d{1,2}$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher1 = pattern.matcher(month);
		if (!matcher1.matches()) {
			return "月份不符合要求";
		}
		Matcher matcher2 = pattern.matcher(day);
		if (!matcher2.matches()) {
			return "日份不符合要求";
		}

		// 月份为1-12
		if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
			return "月份不合法";
		}

		if (Integer.parseInt(month) == 1 || Integer.parseInt(month) == 3
				|| Integer.parseInt(month) == 5 || Integer.parseInt(month) == 7
				|| Integer.parseInt(month) == 8
				|| Integer.parseInt(month) == 10
				|| Integer.parseInt(month) == 12) {
			if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
				return "日分不合法";
			}
		} else if (Integer.parseInt(month) == 4 || Integer.parseInt(month) == 6
				|| Integer.parseInt(month) == 9
				|| Integer.parseInt(month) == 11) {
			if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 30) {
				return "日分不合法";
			}
		} else if (Integer.parseInt(month) == 2) {
			if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 29) {
				return "日分不合法";
			}
		}
		// 日分为1-31
		if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > 31) {
			return "日分不合法";
		}
		return null;
	}

	/**
	 * 验证手机号码的合法性
	 * 
	 * @param mobileId
	 * @return
	 */
	public static boolean verifyMobileId(String mobileId) {
		String re = "^(13[0-9]|15[0-9]|18[0-9])\\d{8}$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(mobileId);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 验证用户名的合法性
	 * 
	 * @param mobileId
	 * @return
	 */
	public static boolean verifyUserName(String userName) {
		String re = "^[a-zA-Z0-9_]{1,}$";
		Pattern pattern = Pattern.compile(re);
		Matcher matcher = pattern.matcher(userName);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 验证用非数字字符分隔的手机号的合法性
	 * 
	 * @param mobileId
	 * @return
	 */
	public static boolean verifyMobileIds(String mobileId) {
		mobileId = mobileId + "@";
		String regex = "((13[0-9]|15[0-9]|18[0-9])\\d{8}([^0-9]))+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mobileId);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证追号期数
	 * 
	 * @param addNumber
	 * @return
	 */
	public static boolean verifyAddNumber(String addNumber) {
		// 验证追号期数是否为空
		if (!verifyEmpty(addNumber)) {
			return false;
		}
		/*
		 * if (addNumber.trim().equals("")) { return true; }
		 */
		// 追号期数为数字
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(addNumber);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证参数是否为空
	 * 
	 * @Title:
	 
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyParamEmpty(String str) {
		if (str == null || str.trim().replace("'", "").length() == 0) {
			return false;
		}
		return true;
	}

	public static boolean verifyParamEmpty1(String str) {
		if (str == null || str.trim().length() == 0) {
			return false;
		}
		return true;
	}

	public static boolean verifyParamEmpty2(String str) {
		if (str == null || str.trim().replace("'", "").equals("")) {
			return false;
		}
		return true;
	}

	public static boolean verifyParamEmpty3(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		return true;
	}

	public static boolean verifyParamEmpty4(String str) {
		if (str == null || str.trim().replace("'", "").length() < 16) {
			return false;
		}
		return true;
	}

	/**
	 * 验证金额格式
	 * 
	 * @Title:
	 
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyMoneyPattern(String str) {
		Pattern pattern = Pattern.compile("^([1-9]{1}[0-9]*)");
		Matcher matcher = pattern.matcher(str);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证如意号码是否为空
	 * 
	 * @Title:
	 
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyRuyiNumberEmpty(String str) {
		if (str == null || str.trim().replace("'", "").length() != 16) {
			return false;
		}
		return true;
	}

	/**
	 * 验证如意号码的格式
	 * 
	 * @Title:
	 
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyRuyiNumberPattern(String str) {
		Pattern cardNoPattern = Pattern.compile("^[0-9]{16}");
		Matcher cardNomacther = cardNoPattern.matcher(str);
		if (!cardNomacther.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证如意卡密码是否为空
	 * 
	 * @Title:
	 
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyRuyiPasswordEmpty(String str) {
		if (str == null || str.trim().replace("'", "").length() != 6) {
			return false;
		}
		return true;
	}

	/**
	 * 验证如意卡密码的格式
	 * 
	 * @Title:
	 
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyRuyiPasswordPattern(String str) {
		Pattern cardPassPattern = Pattern.compile("^[0-9]{6}");
		Matcher cardPassMatcher = cardPassPattern.matcher(str);
		if (!cardPassMatcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证电话格式
	 * 
	 * @Title:
	 
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyPhonePattern(String str) {
		Pattern patternPhone = Pattern
				.compile("^(13[0-9]|15[0-9]|18[0-9])\\d{8}$");
		Matcher matcherPhone = patternPhone.matcher(str.trim());
		boolean isMatchPhone = matcherPhone.matches();
		if (!isMatchPhone) {
			return false;
		}
		return true;
	}

	/**
	 * 验证卡号
	 * 
	 * @Title:
	 
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyCardNo(String str) {
		Pattern pattern = Pattern.compile("^([1-9]{1}[0-9]*)");
		Matcher matcher = pattern.matcher(str);
		boolean isMatch = matcher.matches();
		if (!isMatch || str.trim().replace("'", "").length() < 10) {
			return false;
		}
		return true;
	}

	/**
	 * 用户注册信息验证
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author沈鹏兰
	 */
	public static String checkRegisterInfo(String userName, String password,
			String realPass, String userID) {
		boolean verifyMobileId = VerificationAlgorithmUtil
				.verifyUserName(userName);
		if (userName == null || !verifyMobileId||userName.length()>16||userName.length()<4) {
			return "注册失败!用户名格式不正确";
		}
		if (password == null || ((password.trim())).length() < 6
				|| ((password.trim())).length() > 16) {
			return "注册失败!密码为6-16位";
		}
		if (!password.equals(realPass)) {
			return "注册失败!密码与确认密码不相同";
		}
//		VerificationAlgorithmUtil.checkUserID(userID);
		return null;
	}

	/**
	 * 注册信息验证:验证身份证号码
	 * 
	 * @param mobile_code
	 * @param userID
	 * @return
	 * @author 沈鹏兰
	 */
	public static String checkUserID(String userID) {
		if (userID == null || (userID.trim()).length() != 15
				&& (userID.trim()).length() != 18) {
			return "身份证号码必须是15位或18位";
		}
		Pattern pattern1 = Pattern.compile("^[0-9]{15}");
		Pattern pattern2 = Pattern.compile("^[0-9]{17}[0-9a-zA-Z]{1}");
		Matcher matcher1 = pattern1.matcher(userID);
		Matcher matcher2 = pattern2.matcher(userID);
		boolean isMatcher1 = matcher1.matches();
		boolean isMatcher2 = matcher2.matches();
		if (!isMatcher1 && !isMatcher2) {
			return "身份证号码格式错误";
		}
		if (userID.length() == 15) {
			String userAge = userID.substring(6, 12);
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
			String userAge = userID.substring(6, 14);
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
		return null;
	}

	/**
	 * 验证银行卡号
	 * 
	 * @Title:
	 
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyBankNumber(String str) {
		Pattern bnpattern = Pattern.compile("^([0-9]{16,21})");
		Matcher bnmatcher = bnpattern.matcher(str);
		if (!bnmatcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证密码
	 * 
	 * @Title:
	 * @param:
	 * @return:
	 * @exception:
	 */
	public static boolean verifyPassword(String str) {
		if (str == null || str.trim().length() < 6 || str.trim().length() > 16) {
			return false;
		}
		return true;
	}

	/**
	 * 验证字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyString(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	// 过滤特殊字符
	public static boolean isStringFilter(String str)
			throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}

	/**
	 * 验证联系电话
	 * 
	 * @param phoneCode
	 * @return
	 */
	public static boolean isPhoneCode(String phoneCode) {
		boolean b = true;
		if (!VerificationAlgorithmUtil.isEmptyString(phoneCode)) {
			Pattern p = Pattern
					.compile("\\d{10,12}||\\d{10,12}(_\\d{10,12}){1}");
			Matcher m = p.matcher(phoneCode);
			b = m.matches();
		}
		return b;
	}
	/**
	 * 验证手机号
	 * 
	 * @param phoneCode
	 * @return
	 */
	public static boolean verifyMobile(String mobileid) {
		boolean b = true;
		if (!VerificationAlgorithmUtil.isEmptyString(mobileid)) {
			Pattern p = Pattern
					.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
			Matcher m = p.matcher(mobileid);
			b = m.matches();
		}
		return b;
	}
	
	/**
	 * 电子邮件验证
	 * 
	 * @param emial
	 * @return
	 */
	public static boolean isEmailCode(String emial) {
		boolean b = true;
		if (!VerificationAlgorithmUtil.isEmptyString(emial)) {
			// Pattern p = Pattern.compile("\\w+@(\\w+\\.)+[a-z]{2,3}");
			Pattern p = Pattern
					.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
			Matcher m = p.matcher(emial);
			b = m.matches();
		}
		return b;
	}

	/**
	 * 验证QQ号码 最小5位,最大9位
	 * 
	 * @param qq
	 * @return
	 */
	public static boolean isQQCode(String qq) {
		boolean b = true;
		if (!VerificationAlgorithmUtil.isEmptyString(qq)) {
			Pattern p = Pattern.compile("[0-9]{5,11}");
			Matcher m = p.matcher(qq);
			b = m.matches();
		}
		return b;
	}

	/**
	 * 验证开始日期与结束日期
	 * 
	 */
	public static String getDateByString(String beginDate, String endDate) {
		Pattern pattern = Pattern.compile("\\d{8}");
		Matcher matcher = pattern.matcher(beginDate);
		Matcher matcher1 = pattern.matcher(endDate);
		if (matcher.matches() && matcher1.matches()) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			beginDate = beginDate.substring(0, 4) + "-"
					+ beginDate.substring(4, 6) + "-" + beginDate.substring(6);
			endDate = endDate.substring(0, 4) + "-" + endDate.substring(4, 6)
					+ "-" + endDate.substring(6);
			String leepYear = verifyYear(beginDate.substring(0, 4),
					beginDate.substring(5, 7), beginDate.substring(8));
			String leepYear1 = verifyYear(endDate.substring(0, 4),
					endDate.substring(5, 7), endDate.substring(8));
			String mess = verifyMonthAndDay(endDate.substring(5, 7),
					endDate.substring(8));
			String mess1 = verifyMonthAndDay(beginDate.substring(5, 7),
					beginDate.substring(8));
			if (leepYear != null) {
				return leepYear;
			}
			if (leepYear1 != null) {
				return leepYear1;
			}
			if (mess != null) {
				return mess;
			}
			if (mess1 != null) {
				return mess1;
			}
			try {

				Date d1 = df.parse(beginDate);
				Date d2 = df.parse(endDate);
				if (d2.getTime() < d1.getTime()) {
					return "开始日期与结束日期错误";
				} else
					return null;

			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (beginDate.equals("0") && endDate.equals("30000000")) {
			return null;
		} else {
			return "日期输入有误";
		}
		return null;
	}

	/**
	 * 验证
	 * 
	 * @return
	 */
	public String verificationCard(String cardNumber, String cardPwd) {
		String message = "";
		Pattern pattern = Pattern.compile("^[0-9]*[1-9][0-9]*$");
		Matcher matcher = pattern.matcher(cardNumber);
		if (cardNumber == null || "".equals(cardNumber)) {
			message = MessageUtil.Rruyicard_IS_NULL;
		} else if (cardPwd == null || "".equals(cardPwd)) {
			message = MessageUtil.RruyicardPWD_IS_NULL;
		} else {
			if (cardNumber.length() != 16) {
				message = MessageUtil.Rruyicard_LENGTH_IS_ERROR;
			} else if (!matcher.matches()) {
				message = MessageUtil.Rruyicard_TYPE_IS_ERROR;
			} else if (cardPwd.length() != 6) {
				message = MessageUtil.RruyicardPWD_LENGTH_IS_ERROR;
			}
		}

		return message;
	}

	public static String check3DCode(String zhuma) {
		
			// 如果字符串为null或""不合法
			if (!verifyEmpty(zhuma)) {
				return "注码不能为空";
			}
			// 注码为00-09
			Pattern pattern = Pattern.compile("([0][0-9],){2}[0][0-9]");
			Matcher matcher = pattern.matcher(zhuma);
			if (!matcher.matches()) {
				return "注码格式有误";
			}
		return "";
	}
	/**
	 * 验证竞彩倍数
	 * @param beishu
	 * @return
	 */
	public static String validateJCBeishu(String beishu){
		if("".equals(beishu)||beishu==null){
			return "倍数不能为空";
		}
		Pattern pattern = Pattern.compile("^[1-9]\\d{0,3}$||10000");
		Matcher matcher = pattern.matcher(beishu);
		if (!matcher.matches()) {
			return "倍数不正确";
		}
		return "";
	}
	public static String zcbetcode(String code){
		Pattern pattern = Pattern.compile("^[0||1||3]{1,3}");
		Matcher matcher = pattern.matcher(code);
		if (!matcher.matches()) {
			return "投注注码不合法,<br>注码格式为胜3 ，负1，平0";
		}
		boolean re = checkDuplicate(code);
		if(re == false){
			return "投注注码重复！";
		}
		return "";
	}
	public static String zcbetcodeT01005(String code){
		Pattern pattern = Pattern.compile("^[0||1||2||3]{1,4}");
		Matcher matcher = pattern.matcher(code);
		if (!matcher.matches()) {
			return "投注注码不合法,<br>注码格式为0为无进球，1为进一球，2为两球，3为进3球或以上.";
		}
		boolean re = checkDuplicate(code);
		if(re == false){
			return "投注注码重复！";
		}
		return "";
	}
	/**
	 * 足彩 验证
	 * @param lotno
	 * @param betcode
	 * @param beishu
	 * @return
	 */
	public static String validateZcParam(String lotno ,String betcode , String beishu){
		String [] betcodeArr = betcode.split("\\,")	;
		String remessage = "";
		if(lotno.equals("T01003")){
	        for (int i = 0; i < betcodeArr.length; i++) {
	        	String reStr = zcbetcode(betcodeArr[i]);
	        	if(!reStr.equals("")){
	        		remessage = "第"+(i+1)+"场对阵，"+reStr;
	        		break;
	        	}
		}
	    }
		if (lotno.equals("T01004")) {
			int j = 0;
			for (String code : betcodeArr) {
				if (code.equals("#")) {
					j++;
				}
			}
			if (j< 5) {
				remessage = "赛事选择错误，至多选择九场赛事！";
			}
			if (j> 5) {
				remessage = "赛事选择错误，至少选择九场赛事！";
			}
			//把#替换为3  ， 这样做的目的是简单的实现序列不错乱
			betcode = betcode.replace("#", "3");
			String [] bet = betcode.split("\\,");
			for (int i = 0; i < bet.length; i++) {
				String reStr = zcbetcode(bet[i]);
				if (!reStr.equals("")) {
					remessage = "第" + (i+1) + "场对阵，" + reStr;
					break;
				}
			}
		}
		if(lotno.equals("T01005")){
			for (int i = 0; i < betcodeArr.length; i++) {
				String reStr = zcbetcodeT01005(betcodeArr[i]);
				if (!reStr.equals("")) {
					remessage =  reStr;
					break;
				}
			}
		}
		if(lotno.equals("T01006")){
			for (int i = 0; i < betcodeArr.length; i++) {
				String reStr = zcbetcode(betcodeArr[i]);
				if (!reStr.equals("")) {
					remessage =  reStr;
					break;
				}
			}
		}
		if(!remessage.equals("")){
			return remessage;
		}
        //验证倍数
        remessage = validateJCBeishu(beishu);
		return remessage;
	}
	
	public static void main(String[] args) {
		validateZcParam("T01004", "3,3,3,#,#,1,#,1,3,0,#,3,1,#", "-3");
	}
	
	
	/**
	 * 判断注码中是否有重复的数字
	 * @param zhuma 按位的注码
	 * @return 判断结果 true(无) or false(有)
	 */
	public static boolean checkDuplicate(String zhuma) {
		char zhumaArray[] = zhuma.toCharArray();
		for(int i = 0;i < zhumaArray.length; i++) {
			for(int j = i+1; j < zhumaArray.length;j++) {
				if(zhumaArray[i]==zhumaArray[j])
					return false;
			}
		}
		return true;
	}
	/**
	 * 验证22选5机选
	 * @param beishu
	 * @param zhushu
	 * @param addNumber
	 * @return
	 */
	public static String validate22Select5Aotu(String beishu,String zhushu,String addNumber){
		//判断是否为空
		if("".equals(beishu)||beishu==null){
			return "倍数不能为空";
		}
		if("".equals(zhushu)||zhushu==null){
			return  "注数不能为空";
		}
		if("".equals(addNumber)||addNumber==null){
			return  "追期不能为空";
		}
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(beishu);
		if (!matcher.matches()) {
			return "倍数不正确";
		}
		matcher = pattern.matcher(zhushu);
		if(!matcher.matches()){
			return "注数不正确";
		}
		matcher = pattern.matcher(addNumber);
		if(!matcher.matches()){
			return "追期不正确";
		}
		return "" ;
	}
	/**
	 * 验证22选5自选
	 * @param beishu
	 * @param zhuma
	 * @param addNumber
	 * @return
	 */
	public static String validate22Select5Self(String beishu,String zhuma,String addNumber){
		//判断是否为空
		if("".equals(beishu)||beishu==null){
			return "倍数不能为空";
		}
		if("".equals(zhuma)||zhuma==null){
			return  "注码不能为空";
		}
		if("".equals(addNumber)||addNumber==null){
			return  "追期不能为空";
		}
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(beishu);
		if (!matcher.matches()) {
			return "倍数不正确";
		}
		pattern = Pattern.compile("(0[1-9]|1[0-9]|2[0-2]){5,18}");
		matcher = pattern.matcher(zhuma);
		if(!matcher.matches()){
			return "注码不正确";
		}
		List<String> list = LotteryAlgorithmUtil.getArrayFromString(zhuma);
		if(!LotteryAlgorithmUtil.verifyRepeat(list)){
			return "注码不能重复";
		}
		pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		matcher = pattern.matcher(addNumber);
		if(!matcher.matches()){
			return "追期不正确";
		}
		return "" ;
	}
	/**
	 * 验证22选5胆拖
	 * @param beishu
	 * @param zhuma
	 * @param addNumber
	 * @return
	 */
	public static String validate22Select5DanTuo(String beishu,String danma,String tuoma,String addNumber){
		//判断是否为空
		if("".equals(beishu)||beishu==null){
			return "倍数不能为空";
		}
		if("".equals(danma)||danma==null){
			return  "胆码不能为空";
		}
		if("".equals(danma)||danma==null){
			return  "拖码不能为空";
		}
		if("".equals(addNumber)||addNumber==null){
			return  "追期不能为空";
		}
		Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		Matcher matcher = pattern.matcher(beishu);
		if (!matcher.matches()) {
			return "倍数不正确";
		}
		pattern = Pattern.compile("(0[1-9]|1[0-9]|2[0-2]){1,4}");
		matcher = pattern.matcher(danma);
		if(!matcher.matches()){
			return "胆码不正确";
		}
		pattern = Pattern.compile("(0[1-9]|1[0-9]|2[0-2]){5,21}");
		matcher = pattern.matcher(tuoma);
		if(!matcher.matches()){
			return "拖码不正确";
		}
		List<String> danmaList = LotteryAlgorithmUtil.getArrayFromString(danma);
		List<String> tuomaList = LotteryAlgorithmUtil.getArrayFromString(tuoma);
		List<String> list = LotteryAlgorithmUtil.getArrayFromString(danma+tuoma);
		if(!LotteryAlgorithmUtil.verifyRepeat(danmaList)){
			return "胆码不能重复";
		}
		if(!LotteryAlgorithmUtil.verifyRepeat(tuomaList)){
			return "拖码不能重复";
		}
		if(!LotteryAlgorithmUtil.verifyRepeat(list)){
			return "胆码和拖码不能重复";
		}
		pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
		matcher = pattern.matcher(addNumber);
		if(!matcher.matches()){
			return "追期不正确";
		}
		return "" ;
	}
}
