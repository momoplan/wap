package com.ruyicai.wap.controller;

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

//双色球单式自选
@RequestMapping("/doubleballSingleSelfSelection")
@Controller
public class DoubleBallSingleAction {

	private static final Logger logger = Logger
			.getLogger(DoubleBallSingleAction.class);

	@RequestMapping(value = "/showSelfBetDetails.jspx", method = RequestMethod.POST)
	public String showSelfBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		// 获取输入页面的参数
		String redNumbers = request.getParameter("redNumbers"); // 红球注码
		String blueNumbers = request.getParameter("blueNumbers"); // 蓝球注码
		String multNo = request.getParameter("multNo"); // 倍数
		String type = request.getParameter("type"); // 类型
		String addNumber = request.getParameter("addNumber");
		String parameter = "redNumbers="+redNumbers+"&blueNumbers="+blueNumbers+"&multNo="+multNo+"&addNumber="+addNumber+"&type="+type;
		request.getSession().setAttribute("parameter", parameter);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		redNumbers = CommonUtil.QJToBJChange(redNumbers);
		blueNumbers = CommonUtil.QJToBJChange(blueNumbers);
		multNo = CommonUtil.QJToBJChange(multNo);
		if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/DoubleBall/SingleSelfSelection";
		}
		if (VerificationAlgorithmUtil.isStringFilter(redNumbers)
				|| VerificationAlgorithmUtil.isStringFilter(blueNumbers)
				|| VerificationAlgorithmUtil.isStringFilter(multNo)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/DoubleBall/SingleSelfSelection";
		}
		// 验证输入参数合法性
		String ret = "类型错误";
		// 单式
		Map<String, String> map = new HashMap<String, String>();
		map.put("redZhuma", redNumbers);
		map.put("blueZhuma", blueNumbers);
		map.put("beishu", multNo);
		map.put("addNumber", addNumber);
		ret = VerificationAlgorithmUtil.verifyDoubleBallSingleSelfParams(map);
		logger.info(" ret" + ret);
		if (ret != null) {
			request.setAttribute("err_msg", ret);
			request.setAttribute("redNumbers", redNumbers);
			request.setAttribute("blueNumbers", blueNumbers);
			request.setAttribute("multNo", multNo);
			request.setAttribute("addNumber", addNumber);
			return "wap/DoubleBall/SingleSelfSelection";
		} else {
			// 计算注码,注数,金额,倍数
			redNumbers = CommonUtil.getSortString(redNumbers);
			blueNumbers = CommonUtil.getSortString(blueNumbers);
			Vector<String> redArray = LotteryAlgorithmUtil
					.getStringArrayFromString(redNumbers);
			String newzhuma = LotteryAlgorithmUtil
					.joinStringArrayWithComma(redArray) + "+" + blueNumbers;
			int zhushu = 1; // 单式投注 注数为1
			int beishu = 0;
			if (multNo.trim().equals("")) {
				beishu = 1;
			} else {
				beishu = Integer.parseInt(multNo);
			}
			int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;

			if (!CommonUtil.verifyAmount(amount)) {
				request.setAttribute("err_msg",
						MessageUtil.DBSA_showSelfBetDetails_AmountError);
				return "wap/DoubleBall/SingleSelfSelection";
			}
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", multNo);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("bet_No", redNumbers + "~" + blueNumbers);
			request.setAttribute("type", type);
			request.setAttribute("term", term);
			request.setAttribute("addNumber", addNumber);
			// 转到确认投注页面
			return "wap/DoubleBall/SingleBetDetail";
		}
	}

	/**
	 * 单式机选修改功能
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyAutoBetDetails.jspx", method = RequestMethod.POST)
	public String modifyAutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		// 获取输入页面的参数
		String multNo = request.getParameter("beishu"); // 倍数
		String type = request.getParameter("type"); // 类型
		String zhushuStr = request.getParameter("zhushu"); // 注数
		String betNo = "";// 投注码
		String token = request.getParameter("token");
		String newzhuma = ""; // 新的注码
		boolean flag = false;
		StringBuffer sb = new StringBuffer();
		StringBuffer sbet = new StringBuffer();
		String bet = "";
		int size = 0;
		int num = 0; // 传入的真实注码个数
		String zm = "";
		String[] zhuzu = null;
		String redNumber = "";
		String blueNumber = "";
		String err_msg = "";
		String message = "";
		String addNumber = "";

		// 验证输入参数合法性
		String ret = "类型错误";
		Map<String, String> map = new HashMap<String, String>();
		multNo = CommonUtil.QJToBJChange(multNo);
		type = CommonUtil.QJToBJChange(type);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		addNumber = request.getParameter("addNumber");
		if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
			flag = true;
		}
		if (zhushuStr != null || "".equals(zhushuStr)) {
			size = Integer.parseInt(zhushuStr);
		}
		for (int i = 0; i < size; i++) {
			zm = request.getParameter("zhuma" + i);

			if (zm != null && !"".equals(zm)) {
				num++;
				zhuzu = zm.split("\\+");
				if (zhuzu.length == 2 && zhuzu[0].split(",").length == 6
						&& zhuzu[1].split(",").length == 1) {
					redNumber = zhuzu[0].replaceAll(",", "");
					blueNumber = zhuzu[1];
					map.put("redZhuma", redNumber);
					map.put("blueZhuma", blueNumber);
					map.put("zhushu", zhushuStr);
					map.put("beishu", multNo);
					map.put("addNumber", addNumber);
					err_msg = VerificationAlgorithmUtil
							.verifyDoubleBallSingleSelfParams(map);
					if (err_msg != null && !("").equals(err_msg)) {
						flag = true;
						message = err_msg;
					}
					if (err_msg == null || "".equals(err_msg)) {
						redNumber = CommonUtil.getSortNumString(redNumber);
						zm = redNumber + "+" + blueNumber;
						bet = redNumber.replaceAll(",", "") + "~" + blueNumber;

					}
				} else {
					err_msg = "注码格式错误";
					flag = true;
				}
				if (num == 1) {
					sbet.append(bet + "^");
				} else {
					sbet.append("0001" + bet + "^");
				}
				sb.append(zm + "<br/>");
			}
		}
		newzhuma = sb.toString();
		betNo = sbet.toString();
		if (flag) {
			if ("倍数不合法".equals(err_msg)) {
				err_msg = "倍数不合法";
			} else if ("追号期数不合法".equals(err_msg)) {
				err_msg = "追号期数不合法";
			} else {
				err_msg = "注码格式错误";
			}
		}
		if (message != null && !("").equals(message)) {
			err_msg = message;
		}
		if (err_msg != null && !("").equals(err_msg) && flag) {
			request.setAttribute("err_msg", err_msg);
			request.setAttribute("beishu", multNo);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("term", term);
			request.setAttribute("zhushu", zhushuStr);
			request.setAttribute("zhuma", betNo);
			request.setAttribute("token", token);
			request.setAttribute("addNumber", addNumber);
			return "wap/DoubleBall/ModifySingleAutoSelection";
		}
		if (num == 0) {
			request.setAttribute("err_msg", "请至少选择一注");
			request.setAttribute("beishu", multNo);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", betNo);
			request.setAttribute("token", token);
			request.setAttribute("term", term);
			request.setAttribute("zhushu", zhushuStr);
			request.setAttribute("addNumber", addNumber);
			return "wap/DoubleBall/ModifySingleAutoSelection";
		}
		// 计算注码,注数,金额,倍数
		String betNoStr = "";
		int zhushu = num;
		// 在每注前面加上玩法和倍数代码
		betNoStr = CommonUtil.DB_RSBS + CommonUtil.getNewString("01") + betNo;
		betNoStr = betNoStr.substring(4);
		if (betNoStr.endsWith("^"))
			betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		int amount = zhushu * Integer.parseInt(multNo)
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request.setAttribute("err_msg", "单次"
					+ MessageUtil.DBSA_showSelfBetDetails_AmountError);
			request.setAttribute("beishu", multNo);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", betNo);
			request.setAttribute("token", token);
			request.setAttribute("term", term);
			request.setAttribute("zhushu", zhushuStr);
			request.setAttribute("addNumber", addNumber);
			return "wap/DoubleBall/ModifySingleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("beishu", multNo);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("bet_No", betNoStr);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("token", token);
		request.setAttribute("addNumber", addNumber);

		// 转到确认投注页面
		return "wap/DoubleBall/SingleBetDetail";
	}

	/**
	 * 双色球单式机选
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showAutoBetDetails.jspx", method = RequestMethod.POST)
	public String showAutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		// 获取输入页面的参数
		String multNo = request.getParameter("multNo"); // 倍数
		String type = request.getParameter("type"); // 类型
		String zhushuStr = request.getParameter("zhushu"); // 注数
		String addNumber = request.getParameter("addNumber");
		String parameter = "multNo="+multNo+"&type="+type+"&zhushu="+zhushuStr+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		// 转换为全角字符
		multNo = CommonUtil.QJToBJChange(multNo);
		type = CommonUtil.QJToBJChange(type);
		zhushuStr = CommonUtil.QJToBJChange(zhushuStr);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		// 验证输入参数合法性
		String ret = VerificationAlgorithmUtil
				.verifyDoubleBallSingleAutoParams(zhushuStr, multNo, addNumber);
		if (ret == null) {
			ret = "验证无误";
			logger.info("METHOD:DoubleBallSingleAction/showAutoBetDetails ,双色球机选参数验证结果 :"
					+ ret);
		} else {
			request.setAttribute("err_msg", ret);
			request.setAttribute("multNo", multNo);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("addNumber", addNumber);
			logger.info("METHOD:DoubleBallSingleAction/showAutoBetDetails ,双色球机选参数验证结果 :"
					+ ret);
			return "wap/DoubleBall/SingleAutoSelection";
		}
		// 计算注码,注数,金额,倍数
		String newzhuma = "";
		String betNoStr = "";
		int zhushu = Integer.parseInt(zhushuStr);
		for (int i = 0; i < zhushu; i++) {
			Vector<String> redArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(6, 33));
			Vector<String> blueArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(1, 16));
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(redArray)
					+ "+"
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray)
					+ "<br/>";
			betNoStr += CommonUtil.DB_RSBS + CommonUtil.getNewString("01")
					+ // 在每注前面加上玩法和倍数代码
					LotteryAlgorithmUtil.joinStringArray(redArray) + "~"
					+ LotteryAlgorithmUtil.joinStringArray(blueArray) + "^";
		}
		// 0001020408242629~10^0001071215181926~09^0001051218283033~13^0001071516282931~12^0001010211172330~15^
		betNoStr = betNoStr.substring(4);
		if (betNoStr.endsWith("^"))
			betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		int amount = zhushu * Integer.parseInt(multNo)
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request.setAttribute("err_msg",
					MessageUtil.DBSA_showSelfBetDetails_AmountError);
			return "wap/DoubleBall/SingleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("beishu", multNo);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("bet_No", betNoStr);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("addNumber", addNumber);
		// 转到确认投注页面
		return "wap/DoubleBall/SingleBetDetail";
	}

	/**
	 * 随机选号 投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/AutoBetDetails.jspx", method = RequestMethod.POST)
	public String AutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		// 获取输入页面的参数
		String multNo = "1"; // 倍数
		String type = request.getParameter("type"); // 类型
		String zhushuStr = "1"; // 注数
		String betNo = request.getParameter("betNo");// 注码
		String newzhuma = request.getParameter("newzhuma");
		String addNumber = request.getParameter("addNumber") == null ? "1"
				: request.getParameter("addNumber");

		// 验证输入参数合法性
		String ret = VerificationAlgorithmUtil
				.verifyDoubleBallSingleAutoParams(zhushuStr, multNo, addNumber);
		if (ret == null) {
			ret = "验证无误";
			logger.info("METHOD:DoubleBallSingleAction/AutoBetDetails.jspx ,双色球随机选号参数验证结果 :"
					+ ret);
		} else {
			request.setAttribute("err_msg", ret);
			request.setAttribute("multNo", multNo);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("addNumber", addNumber);
			logger.info("METHOD:DoubleBallSingleAction/AutoBetDetails.jspx ,双色球随机选号参数验证结果 :"
					+ ret);
			return "wap/DoubleBall/SingleAutoSelection";
		}
		addNumber = CommonUtil.QJToBJChange(addNumber);
		multNo = CommonUtil.QJToBJChange(multNo);
		type = CommonUtil.QJToBJChange(type);
		zhushuStr = CommonUtil.QJToBJChange(zhushuStr);
		// 计算注数,金额,倍数
		int zhushu = Integer.parseInt(zhushuStr);
		int amount = zhushu * Integer.parseInt(multNo)
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request.setAttribute("err_msg",
					MessageUtil.DBSA_showSelfBetDetails_AmountError);
			return "wap/DoubleBall/SingleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("beishu", multNo);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("bet_No", betNo);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("addNumber", addNumber);

		// 转到确认投注页面
		return "wap/DoubleBall/SingleBetDetail";
	}

	/*
	 * 双色球定胆
	 */
	@RequestMapping(value = "/showDingdanBetDetails.jspx", method = RequestMethod.POST)
	public String showDingdanBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 取得页面参数
		String redballDanma = (String) request.getParameter("redballDanma");
		String blueball = (String) request.getParameter("blueball");
		String beishu = (String) request.getParameter("beishu");
		String zhushu = (String) request.getParameter("zhushu");
		String type = request.getParameter("type"); // 类型
		String addNumber = request.getParameter("addNumber");
		String parameter = "redballDanma="+redballDanma+"&beishu="+beishu+"&zhushu="+zhushu+"&addNumber="+addNumber+"&blueball="+blueball;
		request.getSession().setAttribute("parameter", parameter);
		redballDanma = CommonUtil.QJToBJChange(redballDanma);
		blueball = CommonUtil.QJToBJChange(blueball);
		beishu = CommonUtil.QJToBJChange(beishu);
		zhushu = CommonUtil.QJToBJChange(zhushu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		if (VerificationAlgorithmUtil.isStringFilter(redballDanma)
				|| VerificationAlgorithmUtil.isStringFilter(blueball)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)
				|| VerificationAlgorithmUtil.isStringFilter(zhushu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		// 验证输入数据的合法性
		Map<String, String> map = new HashMap<String, String>();
		map.put("redballDanma", redballDanma);
		map.put("blueball", blueball);
		map.put("beishu", beishu);
		map.put("zhushu", zhushu);
		map.put("addNumber", addNumber);
		String message = VerificationAlgorithmUtil
				.verifyDoubleBallDingDanSelfBet(map);
		logger.info("message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("redballDanma", redballDanma);
			request.setAttribute("blueball", blueball);
			request.setAttribute("beishu", beishu);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("addNumber", addNumber);
			return "wap/DoubleBall/DantuoDingdanSelection";
		}
		// 获取当前期号
		String term = CommonUtil.getTerm("F47104");
		redballDanma = CommonUtil.getSortString(redballDanma);
		String beishuS = "";
		if (beishu.trim().equals("")) {
			beishuS = "1";
		} else {
			beishuS = beishu;
		}
		String newzhuma = "";
		String betNoStr = "";
		int zhushuS = Integer.parseInt(zhushu);
		Vector<String> redDanma = LotteryAlgorithmUtil
				.getStringArrayFromString(redballDanma);
		for (int i = 0; i < zhushuS; i++) {
			Vector<String> redTuoma = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(6 - redDanma.size(),
									33));
			if (!VerificationAlgorithmUtil.verifyRepeat(LotteryAlgorithmUtil
					.getStringArrayFromString(LotteryAlgorithmUtil
							.joinStringArray(redDanma)
							+ LotteryAlgorithmUtil.joinStringArray(redTuoma)))) {
				i--;
				continue;
			}
			// 将注码进行排序
			String zhuByAsc = LotteryAlgorithmUtil
					.getStringByASC(LotteryAlgorithmUtil
							.joinStringArrayWithComma(redDanma)
							+ ","
							+ LotteryAlgorithmUtil
									.joinStringArrayWithComma(redTuoma));
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(redDanma)
					+ ","
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(redTuoma)
					+ "+" + blueball + "<br/>";
			betNoStr += CommonUtil.DB_RSBS + CommonUtil.getNewString("01") + // 在每注前面加上玩法和倍数代码
					zhuByAsc + "~" + blueball + "^";
		}
		betNoStr = betNoStr.substring(4);
		if (betNoStr.endsWith("^"))
			betNoStr = betNoStr.substring(0, betNoStr.length() - 1);
		int amount = zhushuS * Integer.parseInt(beishuS)
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request.setAttribute("err_msg",
					MessageUtil.DBSA_showSelfBetDetails_AmountError);
			return "wap/DoubleBall/DantuoDingdanSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("redballDanma", redballDanma);
		request.setAttribute("blueball", blueball);
		request.setAttribute("zhushu", zhushuS);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("bet_No", betNoStr);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("addNumber", addNumber);
		// 转到确认投注页面
		return "wap/DoubleBall/DingdanSingleBetDetail";
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
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		String term = CommonUtil.getTerm("F47104");
		String zhushu, beishu, amount, bet_No, addNumber = "", token, channel = "", lotNo = "F47104";
		channel = WapUtil.getChannelId(request);
		// 获取输入页面的参数
		zhushu = request.getParameter("zhushu"); // 注数
		beishu = request.getParameter("beishu"); // 倍数
		amount = request.getParameter("amount"); // 金额
		bet_No = request.getParameter("bet_No");// 不带","的注码
		token = request.getParameter("token");// 判断是否重复提交
		addNumber = request.getParameter("addNumber");
		
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			String ttssBet = "";
			String wanfa = "";
			String bet_code = CommonUtil.generateDoubleBallZhuma(
					CommonUtil.DB_RSBS, beishu, bet_No);
			wanfa = "DB_RSBS";
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
			} else if (buyways.equals("presented")) {
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
			} else {
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				ttssBet = JsonToJrtLotUtil.sendToBet(userno, lotNo, term,
						bet_code, beishu, "2", wanfa, amount, addNumber,
						channel);
			}
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

}
