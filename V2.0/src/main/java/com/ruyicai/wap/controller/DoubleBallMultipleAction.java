package com.ruyicai.wap.controller;

/**
 * DoubleBallMultipleAction 双色球复式业务操作
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
 * 创建者：
 * 创建日期：
 * 修改记录：
 */
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

//双色球复式机选
@RequestMapping("/doubleballMultipleSelfSelection")
@Controller
public class DoubleBallMultipleAction {

	private static final Logger logger = Logger
			.getLogger(DoubleBallMultipleAction.class);

	@RequestMapping(value = "/showSelfBetDetails.jspx", method = RequestMethod.POST)
	public String showSelfBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		// 获取输入页面的参数
		String redNumbers = request.getParameter("redNumbers"); // 红球注码
		String blueNumbers = request.getParameter("blueNumbers"); // 蓝球注码
		String multNo = request.getParameter("multNo")==null?"1":request.getParameter("multNo"); // 倍数
		String type = request.getParameter("type");
		String addNumber = request.getParameter("addNumber")==null?"1":request.getParameter("addNumber");
		String parameter = "redNumbers="+redNumbers+"&blueNumbers="+blueNumbers+"&multNo="+multNo+"&addNumber="+addNumber+"&type="+type;
		request.getSession().setAttribute("parameter", parameter);
		// 验证输入参数合法性
		Map<String, String> map = new HashMap<String, String>();
		map.put("redZhuma", redNumbers);
		map.put("blueZhuma", blueNumbers);
		map.put("beishu", multNo);
		map.put("addNumber", addNumber);
		String ret = VerificationAlgorithmUtil.verifyDoubleBallMultipleSelfBet(map);
		if (ret == null) {
			ret = "验证无误";
			logger.info("METHOD:DoubleBallMultipleAction.java/showSelfBetDetails.jspx ,双色球复试自选参数验证结果 :"
					+ ret);
		}else {
			request.setAttribute("err_msg", ret);
			request.setAttribute("redNumbers", redNumbers);
			request.setAttribute("blueNumbers", blueNumbers);
			request.setAttribute("multNo", multNo);
			request.setAttribute("addNumber", addNumber);
			logger.info("METHOD:DoubleBallMultipleAction.java/showSelfBetDetails.jspx ,双色球复试自选参数验证结果 :"
					+ ret);
			return "wap/DoubleBall/MultipleSelfSelection";
		}
		addNumber = CommonUtil.QJToBJChange(addNumber);
		redNumbers = CommonUtil.QJToBJChange(redNumbers);
		blueNumbers = CommonUtil.QJToBJChange(blueNumbers);
		multNo = CommonUtil.QJToBJChange(multNo);

		// 计算注码,注数,金额,倍数
		redNumbers = CommonUtil.getSortString(redNumbers);
		blueNumbers = CommonUtil.getSortString(blueNumbers);
		Vector<String> redArray = LotteryAlgorithmUtil
				.getStringArrayFromString(redNumbers);
		Vector<String> blueArray = LotteryAlgorithmUtil
				.getStringArrayFromString(blueNumbers);
		char betType = LotteryAlgorithmUtil.getDoubleBallType(redArray.size(),
				blueArray.size());
		String newzhuma = LotteryAlgorithmUtil
				.joinStringArrayWithComma(redArray)
				+ "+"
				+ LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray);
		int zhushu = LotteryAlgorithmUtil.getDoubleBallNumber(redArray.size(),
				blueArray.size());
		int beishu = 0;
		if (multNo.trim().equals("")) {
			beishu = 1;
		} else {
			beishu = Integer.parseInt(multNo);
		}
		int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request.setAttribute("err_msg",
					MessageUtil.DBMA_showSelfBetDetails_AmountError);
			return "wap/DoubleBall/MultipleSelfSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("beishu", multNo);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("bet_No", redNumbers + "~" + blueNumbers);
		request.setAttribute("betType", "" + betType);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("addNumber", addNumber);
		// 转到确认投注页面
		return "wap/DoubleBall/MultipleBetDetail";
	}

	// 选号修改功能
	@RequestMapping(value = "/modifyDetails.jspx", method = RequestMethod.POST)
	public String modifyDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		// 获取输入页面的参数
		String redNumbers = request.getParameter("redNumbers"); // 红球注码
		String blueNumbers = request.getParameter("blueNumbers"); // 蓝球注码
		String redballCount = (String) request.getParameter("redballCount");
		String blueballCount = (String) request.getParameter("blueballCount");
		String transctionId = (String) request.getParameter("transctionId");
		String multNo = request.getParameter("multNo"); // 倍数
		String type = request.getParameter("type");
		String addNumber = request.getParameter("addNumber");
		if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			request.setAttribute("mutNo", multNo);
			request.setAttribute("redNumbers", redNumbers);
			request.setAttribute("blueNumbers", blueNumbers);
			request.setAttribute("redballCount", redballCount);
			request.setAttribute("blueballCount", blueballCount);
			request.setAttribute("transctionId", transctionId);
			request.setAttribute("addNumber", addNumber);
			return "wap/DoubleBall/ModifyMultipleAutoSelection";
		}
		addNumber = CommonUtil.QJToBJChange(addNumber);
		logger.info(" redNumbers:" + redNumbers + " blueNumbers:" + blueNumbers
				+ " multNo:" + multNo + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(redNumbers)
				|| VerificationAlgorithmUtil.isStringFilter(blueNumbers)
				|| VerificationAlgorithmUtil.isStringFilter(multNo)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			request.setAttribute("mutNo", multNo);
			request.setAttribute("redNumbers", redNumbers);
			request.setAttribute("blueNumbers", blueNumbers);
			request.setAttribute("redballCount", redballCount);
			request.setAttribute("blueballCount", blueballCount);
			request.setAttribute("transctionId", transctionId);
			request.setAttribute("addNumber", addNumber);
			return "wap/DoubleBall/ModifyMultipleAutoSelection";
		}
		redNumbers = CommonUtil.QJToBJChange(redNumbers);
		blueNumbers = CommonUtil.QJToBJChange(blueNumbers);
		multNo = CommonUtil.QJToBJChange(multNo);
		// 验证输入参数合法性
		String ret = "类型错误";
		Map<String, String> map = new HashMap<String, String>();
		map.put("redZhuma", redNumbers);
		map.put("blueZhuma", blueNumbers);
		map.put("beishu", multNo);
		map.put("addNumber", addNumber);
		ret = VerificationAlgorithmUtil.verifyDoubleBallMultipleSelfBet(map);
		logger.info(" message:" + ret);
		if (ret != null) {
			request.setAttribute("err_msg", ret);
			request.setAttribute("redNumbers", redNumbers);
			request.setAttribute("blueNumbers", blueNumbers);
			request.setAttribute("multNo", multNo);
			request.setAttribute("redballCount", redballCount);
			request.setAttribute("blueballCount", blueballCount);
			request.setAttribute("transctionId", transctionId);
			request.setAttribute("addNumber", addNumber);
			return "wap/DoubleBall/ModifyMultipleAutoSelection";
		} else {
			// 计算注码,注数,金额,倍数
			redNumbers = CommonUtil.getSortString(redNumbers);
			blueNumbers = CommonUtil.getSortString(blueNumbers);
			Vector redArray = LotteryAlgorithmUtil
					.getStringArrayFromString(redNumbers);
			Vector blueArray = LotteryAlgorithmUtil
					.getStringArrayFromString(blueNumbers);
			char betType = LotteryAlgorithmUtil.getDoubleBallType(
					redArray.size(), blueArray.size());
			String newzhuma = LotteryAlgorithmUtil
					.joinStringArrayWithComma(redArray)
					+ "+"
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray);
			int zhushu = LotteryAlgorithmUtil.getDoubleBallNumber(
					redArray.size(), blueArray.size());
			int beishu = 0;
			if (multNo.trim().equals("")) {
				beishu = 1;
			} else {
				beishu = Integer.parseInt(multNo);
			}
			int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
			if (!CommonUtil.verifyAmount(amount)) {
				request.setAttribute("err_msg",
						MessageUtil.DBMA_showSelfBetDetails_AmountError);
				request.setAttribute("mutNo", multNo);
				request.setAttribute("redNumbers", redNumbers);
				request.setAttribute("blueNumbers", blueNumbers);
				request.setAttribute("redballCount", redballCount);
				request.setAttribute("blueballCount", blueballCount);
				request.setAttribute("transctionId", transctionId);
				request.setAttribute("addNumber", addNumber);
				return "wap/DoubleBall/ModifyMultipleAutoSelection";
			}
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", multNo);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("redballCount", redNumbers.length() / 2 + "");
			request.setAttribute("blueballCount", blueNumbers.length() / 2 + "");
			request.setAttribute("bet_No", redNumbers + "~" + blueNumbers);
			request.setAttribute("betType", "" + betType);
			request.setAttribute("type", type);
			request.setAttribute("term", term);
			request.setAttribute("redballCount", redballCount);
			request.setAttribute("blueballCount", blueballCount);
			request.setAttribute("transctionId", transctionId);
			request.setAttribute("addNumber", addNumber);
		}
		return "wap/DoubleBall/MultipleBetDetail";
	}

	/**
	 * 复试机选
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showAutoBetDetails.jspx")
	public String showAutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		// 获取输入页面的参数
		String redballCount = request.getParameter("redballCount"); // 红球个数
		String blueballCount = request.getParameter("blueballCount"); // 蓝球注码
		String multNo = request.getParameter("multNo"); // 倍数
		String type = request.getParameter("type"); // 类型
		String addNumber = request.getParameter("addNumber");
		String parameter = "redballCount="+redballCount+"&blueballCount="+blueballCount+"&multNo="+multNo+"&addNumber="+addNumber+"&type="+type;
		request.getSession().setAttribute("parameter", parameter);
		// 验证输入参数合法性
		Map<String, String> map = new HashMap<String, String>();
		map.put("redNumber", redballCount);
		map.put("blueNumber", blueballCount);
		map.put("beishu", multNo);
		map.put("addNumber", addNumber);
		String ret = VerificationAlgorithmUtil
				.verifyDoubleBallMultipleAutoBet(map);
		if (ret == null) {
			ret = "验证无误";
			logger.info("METHOD:DoubleBallMultipleAction.java/showAutoBetDetails ,双色球复试机选参数验证结果 :"
					+ ret);
		} else {
			request.setAttribute("err_msg", ret);
			request.setAttribute("redballCount", redballCount);
			request.setAttribute("blueballCount", blueballCount);
			request.setAttribute("multNo", multNo);
			request.setAttribute("addNumber", addNumber);
			logger.info("METHOD:DoubleBallMultipleAction.java/showAutoBetDetails ,双色球复试机选参数验证结果 :"
					+ ret);
			return "wap/DoubleBall/MultipleAutoSelection";
		}
		addNumber = CommonUtil.QJToBJChange(addNumber);
		redballCount = CommonUtil.QJToBJChange(redballCount);
		blueballCount = CommonUtil.QJToBJChange(blueballCount);
		multNo = CommonUtil.QJToBJChange(multNo);

		// 计算注码,注数,金额,倍数
		int rdCount = Integer.parseInt(redballCount);
		int bdCount = Integer.parseInt(blueballCount);
		char betType = LotteryAlgorithmUtil.getDoubleBallType(rdCount, bdCount);
		Vector<String> redArray = LotteryAlgorithmUtil
				.getStringArrayFromIntArray(LotteryAlgorithmUtil
						.getRandomIntArrayWithinRange(rdCount, 33));
		Vector<String> blueArray = LotteryAlgorithmUtil
				.getStringArrayFromIntArray(LotteryAlgorithmUtil
						.getRandomIntArrayWithinRange(bdCount, 16));
		String redNumbers = LotteryAlgorithmUtil.joinStringArray(redArray);
		String blueNumbers = LotteryAlgorithmUtil.joinStringArray(blueArray);
		String newzhuma = LotteryAlgorithmUtil
				.joinStringArrayWithComma(redArray)
				+ "+"
				+ LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray);
		int zhushu = LotteryAlgorithmUtil.getDoubleBallNumber(rdCount, bdCount);
		int beishu = 0;
		if (multNo.trim().equals("")) {
			beishu = 1;
		} else {
			beishu = Integer.parseInt(multNo);
		}
		int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request.setAttribute("err_msg",
					MessageUtil.DBMA_showSelfBetDetails_AmountError);
			return "wap/DoubleBall/MultipleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("beishu", multNo);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("betType", "" + betType);
		request.setAttribute("bet_No", redNumbers + "~" + blueNumbers);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("redballCount", redballCount);
		request.setAttribute("blueballCount", blueballCount);
		request.setAttribute("addNumber", addNumber);
		return "wap/DoubleBall/MultipleBetDetail";
	}

	/**
	 * 投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/placeBet.jspa")
	public String placeBet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
				.getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("F47104");
		String zhushu, beishu, amount, bet_No, betType, addNumber = "", token, channel = "";
		channel = WapUtil.getChannelId(request);
		// 获取输入页面的参数
		zhushu = request.getParameter("zhushu"); // 注数
		beishu = request.getParameter("beishu"); // 倍数
		amount = request.getParameter("amount"); // 金额
		bet_No = request.getParameter("bet_No");// 不带","的注码
		betType = request.getParameter("betType"); // 类型
		token = request.getParameter("token");// 判断是否重复提交
		addNumber = request.getParameter("addNumber");
		String buyways = request.getParameter("buyways");
		
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			String FSType = null;
			String wanfa = "";
			switch (betType.charAt(0)) {
			case 'S':
				FSType = CommonUtil.DB_RSBS;
				wanfa = "DB_RSBS";
				break;
			case 'R':
				FSType = CommonUtil.DB_RMBS;
				wanfa = "DB_RMBS";
				break;
			case 'B':
				FSType = CommonUtil.DB_RSBM;
				wanfa = "DB_RSBM";
				break;
			case 'D':
				FSType = CommonUtil.DB_RMBM;
				wanfa = "DB_RMBM";
				break;
			}
			String ttssBet = "";
			String bet_code = CommonUtil.generateDoubleBallZhuma(FSType,
					beishu, bet_No);
			logger.info("userno:" + userno + " bet_code:" + bet_code);
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
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				ttssBet = JsonToJrtLotUtil.sendToBet(userno, "F47104", term,
						bet_code, beishu, "2", wanfa, amount, addNumber,
						channel);
			}else if(buyways.equals("presented")){
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
				return "wap/zengcai/zengcai";
			}
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

}
