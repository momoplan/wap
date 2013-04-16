package com.ruyicai.wap.controller;

/**
 * DoubleBallDantuoAction 双色球胆拖业务
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
 * 创建者：
 * 创建日期：
 * 修改记录：
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;

@RequestMapping("/doubleBallDantuo")
@Controller
public class DoubleBallDantuoAction {

	private static final Logger logger = Logger
			.getLogger(DoubleBallMultipleAction.class);

	/**
	 * 胆拖机选投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showAutoBetDetails.jspx")
	public String showAutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 取得页面参数
		String redballDanmaStr = request.getParameter("redballDanma"); // 红球胆码个数
		String redballTuomaStr = request.getParameter("redballTuoma"); // 红球拖码个数
		String blueballCount = request.getParameter("blueballCount"); // 蓝球个数
		String beishu = request.getParameter("beishu"); // 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = request.getParameter("addNumber");
		String parameter = "redballDanma="+redballDanmaStr+"&redballTuoma="+redballTuomaStr
				+"&blueballCount="+blueballCount+"&addNumber="+addNumber+"&beishu="+beishu+"&pageType="+pageType;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" addNumber:" + addNumber);
		if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		addNumber = CommonUtil.QJToBJChange(addNumber);
		if (VerificationAlgorithmUtil.isStringFilter(redballDanmaStr)
				|| VerificationAlgorithmUtil.isStringFilter(redballTuomaStr)
				|| VerificationAlgorithmUtil.isStringFilter(blueballCount)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		redballDanmaStr = CommonUtil.QJToBJChange(redballDanmaStr);
		redballTuomaStr = CommonUtil.QJToBJChange(redballTuomaStr);
		blueballCount = CommonUtil.QJToBJChange(blueballCount);
		beishu = CommonUtil.QJToBJChange(beishu);
		// 验证参数的合法性
		Map<String, String> map = new HashMap<String, String>();
		map.put("redballDanma", redballDanmaStr);
		map.put("redballTuoma", redballTuomaStr);
		map.put("blueballCount", blueballCount);
		map.put("beishu", beishu);
		map.put("addNumber", addNumber);
		String message = VerificationAlgorithmUtil
				.verifyDoubleBallDantuoAutoBet(map);
		if (message == null) {
			message = "验证无误";
			logger.info("METHOD:DoubleBallDantuoAction.java/showAutoBetDetails.jspx ,双色球胆拖机选参数验证结果 :"
					+ message);
		} else {
			request.setAttribute("message", message);
			request.setAttribute("redballDanmaStr", redballDanmaStr);
			request.setAttribute("redballTuomaStr", redballTuomaStr);
			request.setAttribute("blueballCount", blueballCount);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			logger.info("METHOD:DoubleBallDantuoAction.java/showAutoBetDetails.jspx ,双色球胆拖机选参数验证结果 :"
					+ message);
			return "wap/DoubleBall/DantuoAutoSelection";
		}
		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		// 将String转成int
		int redballDanmaInt = Integer.parseInt(redballDanmaStr.trim());
		int redballTuomaInt = Integer.parseInt(redballTuomaStr.trim());
		int blueballCountInt = Integer.parseInt(blueballCount.trim());
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		// 随机生成红球注码
		Vector<Integer> vector = LotteryAlgorithmUtil
				.getRandomIntArrayWithinRangeDantuo(redballDanmaInt
						+ redballTuomaInt, 33);
		Vector<Integer> vector1 = LotteryAlgorithmUtil
				.getRandomIntArrayWithinRangeDantuo(blueballCountInt, 16);
		// 从红球注码取出胆码和拖码
		Vector<Integer> redballDanmaVector = new Vector<Integer>();
		Vector<Integer> redballTuomaVector = new Vector<Integer>();
		for (int i = 0; i < vector.size(); i++) {
			if (i < redballDanmaInt) {
				redballDanmaVector.add(vector.get(i));
			} else {
				redballTuomaVector.add(vector.get(i));
			}
		}
		// 注数
		int zhushu = LotteryAlgorithmUtil.getDoubleBallDantuoNumber(
				redballDanmaVector.size(), redballTuomaVector.size(),
				vector1.size());
		// 金额
		int amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		// 将不带"0"的注码前加"0"
		Vector<String> redballDanmaVector1 = LotteryAlgorithmUtil
				.getStringArrayFromIntArray(redballDanmaVector);
		Collections.sort(redballDanmaVector1);
		Vector<String> redballTuomaVector1 = LotteryAlgorithmUtil
				.getStringArrayFromIntArray(redballTuomaVector);
		Collections.sort(redballTuomaVector1);
		Vector<String> blueballVector = LotteryAlgorithmUtil
				.getStringArrayFromIntArray(vector1);
		Collections.sort(blueballVector);
		// 取得带","的注码
		String newRedballDanma = LotteryAlgorithmUtil
				.joinStringArrayWithComma(redballDanmaVector1);
		String newRedballTuoma = LotteryAlgorithmUtil
				.joinStringArrayWithComma(redballTuomaVector1);
		String newBlueball = LotteryAlgorithmUtil
				.joinStringArrayWithComma(blueballVector);
		// 取得不带","的注码
		String redballDanma = LotteryAlgorithmUtil
				.joinStringArray(redballDanmaVector1);
		String redballTuoma = LotteryAlgorithmUtil
				.joinStringArray(redballTuomaVector1);
		String blueball = LotteryAlgorithmUtil.joinStringArray(blueballVector);
		String zhuma = redballDanma + "*" + redballTuoma + "~" + blueball;
		if (!CommonUtil.verifyAmount(amount)) {
			request.setAttribute("redballDanmaStr", redballDanmaStr);
			request.setAttribute("redballTuomaStr", redballTuomaStr);
			request.setAttribute("blueballCount", blueballCount);
			request.setAttribute("beishu", beishu);
			request.setAttribute("message",	MessageUtil.DBMA_showSelfBetDetails_AmountError);
			return "wap/DoubleBall/DantuoAutoSelection";
		}
		request.setAttribute("newRedballDanma", newRedballDanma);
		request.setAttribute("newRedballTuoma", newRedballTuoma);
		request.setAttribute("newBlueball", newBlueball);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("term", term);
		request.setAttribute("blueballNumber", blueballVector.size()); // 蓝球的个数
		request.setAttribute("pageType", pageType);
		request.setAttribute("redballDanma", redballDanmaStr);
		request.setAttribute("redballTuoma", redballTuomaStr);
		request.setAttribute("addNumber", addNumber);
		return "wap/DoubleBall/DantuoBetDetail";
	}

	/**
	 * 胆拖自选投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showSelfBetDetails.jspx", method = RequestMethod.POST)
	public String showSelfBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 取得页面参数
		String redballDanma = (String) request.getParameter("redballDanma");
		String redballTuoma = (String) request.getParameter("redballTuoma");
		String blueball = (String) request.getParameter("blueball");
		String beishu = (String) request.getParameter("beishu");
		String pageType = (String) request.getParameter("pageType");
		String addNumber = request.getParameter("addNumber");
		String parameter = "redballDanma="+redballDanma+"&redballTuoma="+redballTuoma+"&blueball="+blueball+"&addNumber="+addNumber
				+"&beishu="+beishu+"&pageType="+pageType;
		request.getSession().setAttribute("parameter", parameter);

		if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/DoubleBall/DantuoSelfSelection";
		}
		addNumber = CommonUtil.QJToBJChange(addNumber);
		if (VerificationAlgorithmUtil.isStringFilter(redballDanma)
				|| VerificationAlgorithmUtil.isStringFilter(redballTuoma)
				|| VerificationAlgorithmUtil.isStringFilter(blueball)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/DoubleBall/DantuoSelfSelection";
		}
		redballDanma = CommonUtil.QJToBJChange(redballDanma);
		redballTuoma = CommonUtil.QJToBJChange(redballTuoma);
		blueball = CommonUtil.QJToBJChange(blueball);
		beishu = CommonUtil.QJToBJChange(beishu);
		// 验证输入数据的合法性
		Map<String, String> map = new HashMap<String, String>();
		map.put("redballDanma", redballDanma);
		map.put("redballTuoma", redballTuoma);
		map.put("blueball", blueball);
		map.put("beishu", beishu);
		map.put("addNumber", addNumber);
		String message = VerificationAlgorithmUtil
				.verifyDoubleBallDantuoSelfBet(map);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("redballDanma", redballDanma);
			request.setAttribute("redballTuoma", redballTuoma);
			request.setAttribute("blueball", blueball);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/DoubleBall/DantuoSelfSelection";
		}
		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		redballDanma = CommonUtil.getSortString(redballDanma);
		redballTuoma = CommonUtil.getSortString(redballTuoma);
		blueball = CommonUtil.getSortString(blueball);
		// 注码
		String zhuma = redballDanma + "*" + redballTuoma + "~" + blueball;
		// 将注码转成数组
		Vector<String> redballDanmaVector = LotteryAlgorithmUtil
				.getStringArrayFromString(redballDanma);
		Vector<String> redballTuomaVector = LotteryAlgorithmUtil
				.getStringArrayFromString(redballTuoma);
		Vector<String> blueballVector = LotteryAlgorithmUtil
				.getStringArrayFromString(blueball);
		// 带","的注码
		String newRedballDanma = LotteryAlgorithmUtil
				.joinStringArrayWithComma(redballDanmaVector);
		String newRedballTuoma = LotteryAlgorithmUtil
				.joinStringArrayWithComma(redballTuomaVector);
		String newBlueball = LotteryAlgorithmUtil
				.joinStringArrayWithComma(blueballVector);
		// 计算注数
		int zhushu = LotteryAlgorithmUtil.getDoubleBallDantuoNumber(
				redballDanmaVector.size(), redballTuomaVector.size(),
				blueballVector.size());
		// 将String转成int
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		// 金额
		int amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request.setAttribute("message", message);
			request.setAttribute("redballDanma", redballDanma);
			request.setAttribute("redballTuoma", redballTuoma);
			request.setAttribute("blueball", blueball);
			request.setAttribute("beishu", beishu);
			request.setAttribute("message",
					MessageUtil.DBMA_showSelfBetDetails_AmountError);
			return "wap/DoubleBall/DantuoSelfSelection";
		}
		request.setAttribute("newRedballDanma", newRedballDanma);
		request.setAttribute("newRedballTuoma", newRedballTuoma);
		request.setAttribute("newBlueball", newBlueball);
		request.setAttribute("beishu", beishu);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("amount", amount);
		request.setAttribute("term", term);
		request.setAttribute("blueballNumber", blueballVector.size());
		request.setAttribute("pageType", pageType);
		request.setAttribute("addNumber", addNumber);

		return "wap/DoubleBall/DantuoBetDetail";
	}

	/**
	 * 胆拖投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dantuoBet.jspa")
	public String dantuoBet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
				.getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("F47104");
		String buyways = request.getParameter("buyways");
		String zhushu = request.getParameter("zhushu");
		String blueballNumber, beishu, amount, zhuma, addNumber = "", token, channel = "";
		channel = WapUtil.getChannelId(request);
		blueballNumber = request.getParameter("blueballNumber");
		zhuma = request.getParameter("zhuma");// 注码,不带","
		beishu = request.getParameter("beishu"); // 倍数
		amount = request.getParameter("amount"); // 金额
		token = request.getParameter("token"); // 判断是否重复提交
		addNumber = request.getParameter("addNumber");
		
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			// 执行正常的投注
			String ttssBet = "";
			// 根据蓝球的个数生成注码
			String bet_code = "";
			String wanfa = "";
			int blueballNumberInt = Integer.parseInt(blueballNumber);
			if (blueballNumberInt == 1) {
				bet_code = CommonUtil.generateDantuoZhuma("F47104",
						CommonUtil.DB_RTBS, beishu, zhuma); // 蓝单
				wanfa = "DB_RTBS";
			} else {
				bet_code = CommonUtil.generateDantuoZhuma("F47104",
						CommonUtil.DB_RTBM, beishu, zhuma); // 蓝复
				wanfa = "DB_RTBM";
			}
			// 取得投注的返回码
			if (buyways.equals("hemai")) {
//				boolean flag = CommonUtil.getBalanceResult(userno, amount);
//				if(flag){
//					request.setAttribute("message","您的余额不足，请先充值！");
//					return "wap/charge/chargeIndex";
//				}
				CommonUtil.getRandom(request);
				request.setAttribute("zhushu", zhushu);
				request.setAttribute("beishu", beishu);
				request.setAttribute("amt", amount);
				request.setAttribute("newbetcode", bet_code);
				request.setAttribute("lotno", "F47104");
				return "wap/hemai/buyhemai";
			} else if (buyways.equals("daigou")) {
//				boolean flag = CommonUtil.getBalanceResult(userno, amount);
//				if(flag){
//					request.setAttribute("message","您的余额不足，请先充值！");
//					return "wap/charge/chargeIndex";
//				}
				ttssBet = JsonToJrtLotUtil.sendToBet(userno, "F47104", term,
						bet_code, beishu, "2", wanfa, amount, addNumber, channel);
			}else if(buyways.equals("presented")){
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				CommonUtil.getRandom(request);
				request.setAttribute("zhushu", zhushu);
				request.setAttribute("beishu", beishu);
				request.setAttribute("amt", amount);
				request.setAttribute("newbetcode", bet_code);
				request.setAttribute("lotno", "F47104");
				return "wap/zengcai/zengcai";
			}
			
			
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

}
