package com.ruyicai.wap.controller;

import static com.ruyicai.wap.Global.rbint;

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
@RequestMapping("/daletou")
@Controller
public class DaLeTouAction{

	private static final Logger logger = Logger.getLogger(DaLeTouAction.class);

	/**
	 * 大乐透单式机选
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/showAutoBetDetails.jspx",method=RequestMethod.POST)
	public String showAutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		String zhushuStr = request.getParameter("zhushuStr");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String numZJ = request.getParameter("ZJnum") == null ? "2"
				: (String) request.getParameter("ZJnum");
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger
					.info(" addNumber:"
							+ addNumber);
			if (addNumber == null) {
				addNumber = "1";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/daletou/SingleAutoSelection";
			}

		}
		String parameter = "zhushuStr="+zhushuStr+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber+"&ZJnum="+numZJ;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" zhushuStr:" + zhushuStr
				+ " beishu:" + beishu + " type:" + type + " numZJ" + numZJ);
		if (VerificationAlgorithmUtil.isStringFilter(zhushuStr)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/daletou/SingleAutoSelection";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		String zhuma = "";
	
		String message = VerificationAlgorithmUtil
				.verifyDoubleBallSingleAutoParams(zhushuStr,beishu,addNumber);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/SingleAutoSelection";
		}

		// 计算注码,注数,金额,倍数
		String newzhuma = "";
		String multNo = "";
		if (beishu.trim().equals("")) {
			multNo = "1";
		} else {
			multNo = beishu;
		}
		int zhushu = Integer.parseInt(zhushuStr);
		for (int i = 0; i < zhushu; i++) {
			Vector redArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(5, 35));
			Vector blueArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(2, 12));
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(redArray)
					+ "|"
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray)
					+ "<br/>";
			zhuma += LotteryAlgorithmUtil.joinStringArrayWithSpace(redArray)
					+ "-"
					+ LotteryAlgorithmUtil.joinStringArrayWithSpace(blueArray)
					+ ";";
		}
		int amount = 0;
		if (zhuma.endsWith(";"))
			zhuma = zhuma.substring(0, zhuma.length() - 1);

		logger.info(" newzhuma:" + newzhuma
				+ " zhuma:" + zhuma);
		if (numZJ.equals("2") || numZJ == "2") {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiao;
		} else {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiaoZJ;
		}
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/daletou/SingleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", Integer.parseInt(multNo) * zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("oneMoney", numZJ);
		request.setAttribute("zhushuStr", zhushuStr);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (Integer.parseInt(multNo) * zhushu) + " beishu:" + beishu
				+ " addNumber:" + addNumber + " amount:" + amount
				+ " newzhuma:" + newzhuma + " zhuma:" + zhuma + " type:" + type
				+ " zhushuStr:" + zhushuStr);
		// 转到确认投注页面
		return "wap/daletou/BetDetail";
	}

	/**
	 * 修改单式机选注码
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ModifySingleAutoBetDetails.jspx",method=RequestMethod.POST)
	public String ModifySingleAutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		int size = Integer.parseInt(request.getParameter("size"));
		String zhushuStr = request.getParameter("zhushu");
		int num = 0; // 传入的真实注码个数
		String zm = "";
		StringBuffer sb = new StringBuffer();
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String newzhuma = "";
		String err_msg = "";
		String message = "";
		String redNumber = "";
		String blueNumber = "";
		String zhuma = "";
		String numZJ = request.getParameter("numZJ") == null ? "2"
				: (String) request.getParameter("numZJ");
		String addNumber = "";
		String[] br = null;
		boolean flag = false;
		Map map = new HashMap();
		beishu = CommonUtil.QJToBJChange(beishu);
		// 验证追期是否合法
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger
					.info( " addNumber:"
							+ addNumber);
		}
		for (int i = 0; i < size; i++) {
			zm = request.getParameter("zhu" + i);
			if (zm != null && !"".equals(zm)) {

				num++;

				br = zm.split("\\|");
				if (br.length == 2 && br[0].split(",").length == 5
						&& br[1].split(",").length == 2) {
					redNumber = br[0].replaceAll(",", "");
					blueNumber = br[1].replaceAll(",", "");
					map.put("redZhuma", redNumber);
					map.put("blueZhuma", blueNumber);
					map.put("beishu", beishu);
					map.put("addNumber", addNumber);
					err_msg = VerificationAlgorithmUtil
							.verifyDaLeTouAutoBet(map);
					if (err_msg != null && !("").equals(err_msg)) {
						flag = true;
						message = err_msg;
					}
					logger.info(" message:"
							+ err_msg);
					if (err_msg == null || "".equals(err_msg)) {
						redNumber = CommonUtil.getSortNumString(redNumber);
						blueNumber = CommonUtil.getSortNumString(blueNumber);
						System.out.println("redNumber:" + redNumber);
						zm = redNumber + "|" + blueNumber;
					}
				} else {
					flag = true;
					err_msg = "注码格式错误";
				}
				sb.append(zm + "<br/>");
			}
		}
		newzhuma = sb.toString();
		zhuma = newzhuma.replaceAll(",", " ").replaceAll("\\|", "-")
				.replaceAll("<br/>", ";");
		addNumber = CommonUtil.QJToBJChange(addNumber);
		if (flag) {
			if ("倍数不合法".equals(err_msg)) {
				// beishu = "1";
				err_msg = "倍数不合法";
			}
			if ("追号期数不合法".equals(err_msg)) {
				// addNumber = "1";
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
			request.setAttribute("beishu", beishu);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("oneMoney", numZJ);
			request.setAttribute("zhushuStr", zhushuStr);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/ModifySingleAutoSelection";
		}
		if (num == 0) {
			request.setAttribute("err_msg", "请至少选择一注");
			request.setAttribute("beishu", beishu);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("oneMoney", numZJ);
			request.setAttribute("zhushuStr", zhushuStr);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/ModifySingleAutoSelection";
		}

		logger.info(" zhushuStr:" + zhushuStr
				+ " beishu:" + beishu + " type:" + type);

		// 计算注码,注数,金额,倍数

		String multNo = "";
		if (beishu.trim().equals("")) {
			multNo = "1";
		} else {
			multNo = beishu;
		}
		int zhushu = num;
		int amount = 0;
		logger.info(" newzhuma:" + newzhuma
				+ " zhuma:" + zhuma);
		if (numZJ.equals("2") || numZJ == "2") {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiao;
		} else {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiaoZJ;
		}
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("err_msg", "单次" + MessageUtil.TC_AmountError);
			request.setAttribute("beishu", beishu);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("oneMoney", numZJ);
			request.setAttribute("zhushuStr", zhushuStr);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/ModifySingleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", Integer.parseInt(multNo) * zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("oneMoney", numZJ);
		request.setAttribute("zhushuStr", zhushuStr);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (Integer.parseInt(multNo) * zhushu) + " beishu:" + beishu
				+ " addNumber:" + addNumber + " newzhuma:" + newzhuma
				+ " zhuma:" + zhuma + " type:" + type + " zhushuStr:"
				+ zhushuStr);
		// + " amount:" + amount
		// 转到确认投注页面
		return "wap/daletou/BetDetail";
	}

	/**
	 * 修改复式机选注码
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ModifyMutipleAutoBetDetails.jspx",method=RequestMethod.POST)
	public String ModifyMutipleAutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		int size = Integer.parseInt(request.getParameter("size"));
		String zhushuStr = request.getParameter("zhushuStr");
		int num = 0; // 传入的真实注码个数
		String zm = "";
		StringBuffer sb = new StringBuffer();
		String beishu = request.getParameter("beishu");
		String redSize = request.getParameter("redSize");
		String blueSize = request.getParameter("blueSize");
		String type = request.getParameter("type");
		String newzhuma = "";
		String zhuma = newzhuma.replaceAll(",", " ").replaceAll("\\|", "-")
				.replaceAll("<br/>", ";");
		String numZJ = request.getParameter("numZJ") == null ? "2"
				: (String) request.getParameter("numZJ");
		String addNumber = "";
		String redNumber = "";
		String blueNumber = "";
		boolean flag = false;
		String err_msg = "";
		String message = "";
		String[] br = null;
		Map map = new HashMap();
		beishu = CommonUtil.QJToBJChange(beishu);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger
					.info(" addNumber:"
							+ addNumber);
		}

		for (int i = 0; i < size; i++) {
			zm = request.getParameter("zhu" + i);
			if (zm != null && !"".equals(zm)) {
				num++;
				br = zm.split("\\|");
				if (br.length == 2
						&& br[0].split(",").length == Integer.parseInt(redSize)
						&& br[1].split(",").length == Integer
								.parseInt(blueSize)) {
					redNumber = br[0].replaceAll(",", "");
					blueNumber = br[1].replaceAll(",", "");
					map.put("redZhuma", redNumber);
					map.put("blueZhuma", blueNumber);
					map.put("beishu", beishu);
					map.put("addNumber", addNumber);
					err_msg = VerificationAlgorithmUtil
							.verifyDaLeTouMultipleSelfBet(map);
					if (err_msg != null && !("").equals(err_msg)) {
						flag = true;
						message = err_msg;
					}
					logger.info(" message:"
							+ err_msg);
					if (err_msg == null || "".equals(err_msg)) {
						redNumber = CommonUtil.getSortNumString(redNumber);
						blueNumber = CommonUtil.getSortNumString(blueNumber);
						System.out.println("redNumber:" + redNumber);
						zm = redNumber + "|" + blueNumber;
					}
				} else {
					err_msg = "注码格式错误";
					flag = true;
				}
				sb.append(zm + "<br/>");

			}

		}

		newzhuma = sb.toString();
		zhuma = newzhuma.replaceAll(",", " ").replaceAll("\\|", "-")
				.replaceAll("<br/>", ";");
		if (flag) {
			if ("倍数不合法".equals(err_msg)) {
				// beishu = "1";
				err_msg = "倍数不合法";
			}
			if ("追号期数不合法".equals(err_msg)) {
				// addNumber = "1";
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
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("beishu", beishu);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("redSize", redSize);
			request.setAttribute("blueSize", blueSize);
			request.setAttribute("oneMoney", numZJ);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/ModifyMultipleAutoSelection";
		}

		addNumber = CommonUtil.QJToBJChange(addNumber);

		logger.info(" zhushuStr:" + zhushuStr
				+ " beishu:" + beishu + " type:" + type);

		if (num == 0) {
			request.setAttribute("err_msg", "请至少选择一注");
			request.setAttribute("beishu", beishu);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("redSize", redSize);
			request.setAttribute("blueSize", blueSize);
			request.setAttribute("oneMoney", numZJ);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/ModifyMultipleAutoSelection";
		}
		// 计算注码,注数,金额,倍数

		String multNo = "";
		if (beishu.trim().equals("")) {
			multNo = "1";
		} else {
			multNo = beishu;
		}
		int zhushu = LotteryAlgorithmUtil.getDaleTouNumber(Integer
				.parseInt(redSize), Integer.parseInt(blueSize))
				* num;

		int amount = 0;
		logger.info(" newzhuma:" + newzhuma
				+ " zhuma:" + zhuma);
		if (numZJ.equals("2") || numZJ == "2") {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiao;
		} else {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiaoZJ;
		}
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("err_msg", "单次" + MessageUtil.TC_AmountError);
			request.setAttribute("beishu", beishu);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("redSize", redSize);
			request.setAttribute("blueSize", blueSize);
			request.setAttribute("oneMoney", numZJ);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/ModifyMultipleAutoSelection";
		}

		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", zhushu * Integer.parseInt(multNo));
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("redSize", redSize);
		request.setAttribute("blueSize", blueSize);
		request.setAttribute("oneMoney", numZJ);
		request.setAttribute("zhushuStr", num + "");
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (Integer.parseInt(multNo) * zhushu) + " beishu:" + beishu
				+ " addNumber:" + addNumber + " newzhuma:" + newzhuma
				+ " zhuma:" + zhuma + " type:" + type + " zhushuStr:"
				+ zhushuStr);
		// + " amount:" + amount
		// 转到确认投注页面
		return "wap/daletou/BetDetail";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/showBetDetails.jspx",method=RequestMethod.POST)
	public String showBetDetails(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获取当前期号
		String zhushuStr = "1";
		String beishu = "1";
		String type = request.getParameter("type");
		String newzhuma = request.getParameter("newzhuma");
		String zhuma = request.getParameter("betNo");

		String numZJ = "2";
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger
					.info(" addNumber:"
							+ addNumber);
			if (addNumber == null || addNumber.equals("")) {
				addNumber = "1";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		logger.info(" zhushuStr:" + zhushuStr
				+ " beishu:" + beishu + " type:" + type + " numZJ" + numZJ);
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		String message = VerificationAlgorithmUtil
				.verifyDoubleBallSingleAutoParams(zhushuStr,beishu,addNumber);
		logger.info( " message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/SingleAutoSelection";
		}
		// 计算注码,注数,金额,倍数
		String multNo = "";
		if (beishu.trim().equals("")) {
			multNo = "1";
		} else {
			multNo = beishu;
		}
		int zhushu = Integer.parseInt(zhushuStr);
		int amount = 0;
		logger.info(" newzhuma:" + newzhuma
				+ " zhuma:" + zhuma);
		if (numZJ.equals("2") || numZJ == "2") {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiao;
		} else {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiaoZJ;
		}
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/daletou/SingleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", Integer.parseInt(multNo) * zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("oneMoney", numZJ);
		request.setAttribute("zhushuStr", zhushuStr);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		logger.info(" zhushu:"
				+ (Integer.parseInt(multNo) * zhushu) + " beishu:" + beishu
				+ " addNumber:" + addNumber + " amount:" + amount
				+ " newzhuma:" + newzhuma + " zhuma:" + zhuma + " type:" + type
				+ " zhushuStr:" + zhushuStr);
		// 转到确认投注页面
		return "wap/daletou/BetDetail";
	}

	/**
	 * 大乐透复式机选
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/showAutoMultipleDetail.jspx",method=RequestMethod.POST)
	public String showAutoMultipleDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取当前期号
		String redSize = request.getParameter("redSize");// 前区号码个数
		String blueSize = request.getParameter("blueSize");// 后区号码个数
		String zhushuStr = request.getParameter("zhushu");
		String beishu = request.getParameter("beishu");
		String numZJ = request.getParameter("ZJnum");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger
					.info(" addNumber:"
							+ addNumber);
			if (addNumber == null) {
				addNumber = "1";
			}
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/daletou/MultipleAutoSelection";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "redSize="+redSize+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber
				+"&blueSize="+blueSize+"&zhushu="+zhushuStr+"&ZJnum="+numZJ;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" zhushuStr:" + zhushuStr
				+ " beishu:" + beishu + " redSize:" + redSize + " blueSize:"
				+ blueSize + " numZJ:" + numZJ + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(zhushuStr)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/daletou/MultipleAutoSelection";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		String zhuma = "";
		Map map = new HashMap();
		map.put("redSize", redSize);
		map.put("blueSize", blueSize);
		map.put("zhushu", zhushuStr);
		map.put("beishu", beishu);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String message = VerificationAlgorithmUtil.verifyDaLeTouAutoBet(map);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("redSize", redSize);
			request.setAttribute("blueSize", blueSize);
			request.setAttribute("message", message);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/MultipleAutoSelection";
		}

		int redSizeInt = Integer.parseInt(redSize);
		int blueSizeInt = Integer.parseInt(blueSize);
		// 计算注码,注数,金额,倍数
		String newzhuma = "";
		String multNo = "";
		if (beishu.trim().equals("")) {
			multNo = "1";
		} else {
			multNo = beishu;
		}
		int zhushu = Integer.parseInt(zhushuStr);
		int amount = 0;
		for (int i = 0; i < zhushu; i++) {
			Vector redArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(redSizeInt, 35));
			Vector blueArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(blueSizeInt, 12));
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(redArray)
					+ "|"
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray)
					+ "<br/>";
			zhuma += LotteryAlgorithmUtil.joinStringArrayWithSpace(redArray)
					+ "-"
					+ LotteryAlgorithmUtil.joinStringArrayWithSpace(blueArray)
					+ ";";
		}
		zhushu = zhushu
				* LotteryAlgorithmUtil
						.getDaleTouNumber(redSizeInt, blueSizeInt);
		if (zhuma.endsWith(";"))
			zhuma = zhuma.substring(0, zhuma.length() - 1);

		logger.info(" zhuma:" + zhuma
				+ " newzhuma:" + zhuma);
		if (numZJ.equals("2") || numZJ == "2") {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiao;
		} else {
			amount = zhushu * Integer.parseInt(multNo)
					* LotteryAlgorithmUtil.priceOfCaipiaoZJ;
		}
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("message", MessageUtil.TC_AmountError);
			return "wap/daletou/MultipleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("redSize", redSize);
		request.setAttribute("blueSize", blueSize);
		request.setAttribute("zhushu", Integer.parseInt(multNo) * zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("zhushuStr", zhushuStr);
		request.setAttribute("oneMoney", numZJ);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (Integer.parseInt(multNo) * zhushu) + " beishu:" + beishu
				+ " addNumber:" + addNumber + " amount:" + amount
				+ " newzhuma:" + newzhuma + " zhuma:" + zhuma + " type:" + type
				+ " redSize:" + redSize + " blueSize:" + blueSize
				+ " zhushuStr:" + zhushuStr);
		// 转到确认投注页面
		return "wap/daletou/BetDetail";
	}

	/**
	 * 大乐透复式自选
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/showSelfBetDetail.jspx",method=RequestMethod.POST)
	public String showSelfBetDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取当前期号
		// 获取输入页面的参数
		String redNumbers = request.getParameter("redNumbers"); // 红球注码
		String blueNumbers = request.getParameter("blueNumbers"); // 蓝球注码
		String multNo = request.getParameter("multNo"); // 倍数
		String numZJ = request.getParameter("ZJnum");
		String type = request.getParameter("type"); // 类型
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger
					.info(" addNumber:"
							+ addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/daletou/SingleSelfSelection";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "redNumbers="+redNumbers+"&blueNumbers="+blueNumbers+"&type="+type+"&addNumber="+addNumber
				+"&multNo="+multNo+"&ZJnum="+numZJ;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" redNumbers:" + redNumbers
				+ " blueNumbers:" + blueNumbers + " multNo:" + multNo
				+ " numZJ:" + numZJ + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(redNumbers)
				|| VerificationAlgorithmUtil.isStringFilter(blueNumbers)
				|| VerificationAlgorithmUtil.isStringFilter(multNo)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/daletou/SingleSelfSelection";
		}
		redNumbers = CommonUtil.QJToBJChange(redNumbers);
		blueNumbers = CommonUtil.QJToBJChange(blueNumbers);
		multNo = CommonUtil.QJToBJChange(multNo);
		logger.info(" redNumbers:" + redNumbers
				+ " blueNumbers:" + blueNumbers + " multNo:" + multNo);
		// 验证输入参数合法性
		String ret = "类型错误";
		// 单式
		Map map = new HashMap();
		map.put("redZhuma", redNumbers);
		map.put("blueZhuma", blueNumbers);
		map.put("beishu", multNo);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		ret = VerificationAlgorithmUtil.verifyDaLeTouMultipleSelfBet(map);
		logger.info("message:" + ret);
		if (ret != null) {
			request.setAttribute("err_msg", ret);
			request.setAttribute("redNumbers", redNumbers);
			request.setAttribute("blueNumbers", blueNumbers);
			request.setAttribute("multNo", multNo);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/SingleSelfSelection";
		} else {
			// 计算注码,注数,金额,倍数
			redNumbers = CommonUtil.getSortString(redNumbers);
			blueNumbers = CommonUtil.getSortString(blueNumbers);
			Vector redArray = LotteryAlgorithmUtil
					.getStringArrayFromString(redNumbers);
			Vector blueArray = LotteryAlgorithmUtil
					.getStringArrayFromString(blueNumbers);
			String newzhuma = LotteryAlgorithmUtil
					.joinStringArrayWithComma(redArray)
					+ "|"
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(blueArray);
			String zhuma = LotteryAlgorithmUtil
					.joinStringArrayWithSpace(redArray)
					+ "-"
					+ LotteryAlgorithmUtil.joinStringArrayWithSpace(blueArray);
			int zhushu = LotteryAlgorithmUtil.getDaleTouNumber(redArray.size(),
					blueArray.size()); // 单式投注 注数为1
			int beishu = 0;
			if (multNo.trim().equals("")) {
				beishu = 1;
			} else {
				beishu = Integer.parseInt(multNo);
			}
			int amount = 0;
			if (numZJ.equals("2") || numZJ == "2") {
				amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
			} else {
				amount = zhushu * beishu
						* LotteryAlgorithmUtil.priceOfCaipiaoZJ;
			}
			if (!CommonUtil.TCverifyAmount(amount)) {
				request.setAttribute("err_msg", MessageUtil.TC_AmountError);
				return "wap/daletou/SingleSelfSelection";
			}
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu * beishu);
			request.setAttribute("beishu", multNo);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("type", type);
			request.setAttribute("oneMoney", numZJ);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info(" zhushu:"
					+ (zhushu * beishu) + " beishu:" + multNo + " addNumber:"
					+ addNumber + " amount:" + amount + " newzhuma:" + newzhuma
					+ " zhuma:" + zhuma + " type:" + type);
			// 转到确认投注页面

		}
		return "wap/daletou/BetDetail";
	}

	/**
	 *大乐透胆拖自选投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/selfSelectionDantuoBetDetail.jspx",method=RequestMethod.POST)
	public String selfSelectionDantuoBetDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	
		String reddanma = request.getParameter("reddanma");
		String redtuoma = request.getParameter("redtuoma");
		String bluedanma = request.getParameter("bluedanma");
		String bluetuoma = request.getParameter("bluetuoma");
		String numZJ = request.getParameter("ZJnum");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger
					.info(" addNumber:"
							+ addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "reddanma="+reddanma+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber
				+"&redtuoma="+redtuoma+"&bluedanma="+bluedanma+"&bluetuoma="+bluetuoma+"&ZJnum="+numZJ;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" bluedanma:" + bluedanma
				+ " bluetuoma:" + bluetuoma + " reddanma:" + reddanma
				+ " redtuoma:" + redtuoma + " beishu:" + beishu + " numZJ:"
				+ numZJ + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(bluedanma)
				|| VerificationAlgorithmUtil.isStringFilter(bluetuoma)
				|| VerificationAlgorithmUtil.isStringFilter(reddanma)
				|| VerificationAlgorithmUtil.isStringFilter(redtuoma)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		String zhuma = "";
		// 获取当前期号
		Map map = new HashMap();
		map.put("reddanma", reddanma);
		map.put("redtuoma", redtuoma);
		map.put("bluedanma", bluedanma);
		map.put("bluetuoma", bluetuoma);
		map.put("beishu", beishu);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String message = VerificationAlgorithmUtil
				.verifyDaLeTouDantuoSelfSelection(map);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("reddanma", reddanma);
			request.setAttribute("redtuoma", redtuoma);
			request.setAttribute("bluedanma", bluedanma);
			request.setAttribute("bluetuoma", bluetuoma);
			request.setAttribute("beishu", beishu);
			request.setAttribute("message", message);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/DantuoSelfSelection";
		}
		Vector danmaVector = LotteryAlgorithmUtil
				.getStringArrayFromString(reddanma);
		Collections.sort(danmaVector);
		Vector tuomaVector = LotteryAlgorithmUtil
				.getStringArrayFromString(redtuoma);
		Collections.sort(tuomaVector);

		Vector danmaVectorH = LotteryAlgorithmUtil
				.getStringArrayFromString(bluedanma);
		Collections.sort(danmaVectorH);
		Vector tuomaVectorH = LotteryAlgorithmUtil
				.getStringArrayFromString(bluetuoma);
		Collections.sort(tuomaVectorH);

		zhuma = LotteryAlgorithmUtil.joinStringArrayWithSpace(danmaVector)
				+ "$"
				+ LotteryAlgorithmUtil.joinStringArrayWithSpace(tuomaVector)
				+ "-"
				+ LotteryAlgorithmUtil.joinStringArrayWithSpace(danmaVectorH)
				+ "$"
				+ LotteryAlgorithmUtil.joinStringArrayWithSpace(tuomaVectorH);

		int zhushu = LotteryAlgorithmUtil.getDaLeTouNumberDT(
				danmaVector.size(), tuomaVector.size(), danmaVectorH.size(),
				tuomaVectorH.size());
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		int amount = 0;
		if (numZJ.equals("2") || numZJ == "2") {
			amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		} else {
			amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiaoZJ;
		}
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("message", MessageUtil.TC_AmountError);
			return "wap/daletou/DantuoSelfSelection";
		}
		request.setAttribute("type", type);
		request.setAttribute("zhushu", zhushu * beishuInt);
		// request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("reddanma", LotteryAlgorithmUtil
				.joinStringArrayWithComma(danmaVector));
		request.setAttribute("redtuoma", LotteryAlgorithmUtil
				.joinStringArrayWithComma(tuomaVector));
		request.setAttribute("bluedanma", LotteryAlgorithmUtil
				.joinStringArrayWithComma(danmaVectorH));
		request.setAttribute("bluetuoma", LotteryAlgorithmUtil
				.joinStringArrayWithComma(tuomaVectorH));
		request.setAttribute("amount", amount);
		request.setAttribute("beishu", beishu);
		request.setAttribute("type", type);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("oneMoney", numZJ);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (zhushu * beishuInt) + " beishu:" + beishu + " addNumber:"
				+ addNumber + " amount:" + amount + " zhuma:" + zhuma
				+ " type:" + type + " reddanma:"
				+ LotteryAlgorithmUtil.joinStringArrayWithComma(danmaVector)
				+ " redtuoma:"
				+ LotteryAlgorithmUtil.joinStringArrayWithComma(tuomaVector)
				+ " bluedanma:"
				+ LotteryAlgorithmUtil.joinStringArrayWithComma(danmaVectorH)
				+ " bluetuoma:"
				+ LotteryAlgorithmUtil.joinStringArrayWithComma(tuomaVectorH));
		return "wap/daletou/DanTuoBetDetail";
	}

	/**
	 * 大乐透12选 2明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/shengxiaoBetDetail.jspx",method=RequestMethod.POST)
	public String shengxiaoBetDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String zhuma = request.getParameter("zhuma");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger
					.info(" addNumber:"
							+ addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("message", "输入框中不能输入特殊字符");
				return "wap/daletou/ShengXiaoSelection";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" zhuma:" + zhuma
				+ " beishu:" + beishu + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/daletou/ShengXiaoSelection";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		Map map = new HashMap();
		map.put("zhuma", zhuma);
		map.put("beishu", beishu);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String message = VerificationAlgorithmUtil.verifyDaLeTouSX(map);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/ShengXiaoSelection";
		}

		Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		Collections.sort(vector);
		zhuma = LotteryAlgorithmUtil.joinStringArrayWithSpace(vector);
		String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		int zhushu = LotteryAlgorithmUtil.getDaletouSXNumber(vector.size());
		int amount = beishuInt * zhushu * LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("message", MessageUtil.TC_AmountError);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/daletou/ShengXiaoSelection";
		}
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info( " zhushu:"
				+ (zhushu * beishuInt) + " beishu:" + beishu + " addNumber:"
				+ addNumber + " amount:" + amount + " newzhuma:" + newzhuma
				+ " zhuma:" + zhuma);
		return "wap/daletou/BetDetail";
	}

	/**
	 *大乐透胆拖投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/dantuobet.jspa")
	public String dantuobet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("T01001");
		String channel = WapUtil.getChannelId(request);
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");

		String oneMoney, zhuma, amount, beishu, zhushu, certID, type, addNumber = "1", token, reddanma, redtuoma, bluedanma, bluetuoma;
		logger.info("reddanma:" + request.getParameter("reddanma"));
		logger.info("redtuoma:" + request.getParameter("redtuoma"));
		logger.info("bluedanma:" + request.getParameter("bluedanma"));
		logger.info("bluetuoma:" + request.getParameter("bluetuoma"));
		logger.info("type:" + request.getParameter("type"));
		logger.info("token:" + request.getParameter("token"));

		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			// 获取输入页面的参数
			zhushu = request.getParameter("zhushu"); // 注数
			beishu = request.getParameter("beishu"); // 倍数
			amount = request.getParameter("amount"); // 金额
			reddanma = request.getParameter("reddanma");
			redtuoma = request.getParameter("redtuoma");
			bluedanma = request.getParameter("bluedanma");
			bluetuoma = request.getParameter("bluetuoma");
			type = request.getParameter("type");
			oneMoney = request.getParameter("oneMoney");
			token = request.getParameter("token");// 判断是否重复提交
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber");
			}
		} else {// 获取request.getAttribute中的存储
			String[] zhushus = request.getAttribute("zhushu") == null ? null
					: (String[]) request.getAttribute("zhushu");
			String[] beishus = request.getAttribute("beishu") == null ? null
					: (String[]) request.getAttribute("beishu");
			String[] amounts = request.getAttribute("amount") == null ? null
					: (String[]) request.getAttribute("amount");
			String[] reddanmas = request.getAttribute("reddanma") == null ? null
					: (String[]) request.getAttribute("reddanma");
			String[] redtuomas = request.getAttribute("redtuoma") == null ? null
					: (String[]) request.getAttribute("redtuoma");
			String[] bluedanmas = request.getAttribute("bluedanma") == null ? null
					: (String[]) request.getAttribute("bluedanma");
			String[] bluetuomas = request.getAttribute("bluetuoma") == null ? null
					: (String[]) request.getAttribute("bluetuoma");
			String[] oneMoneys = request.getAttribute("oneMoney") == null ? null
					: (String[]) request.getAttribute("oneMoney");
			String[] types = request.getAttribute("type") == null ? null
					: (String[]) request.getAttribute("type");
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");
			String[] addNumbers = null;
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber") == null ? null
						: (String[]) request.getAttribute("addNumber");
			}
			// 获取输入页面的参数
			zhushu = zhushus == null || zhushus[0].equals("") ? "0"
					: zhushus[0]; // 注数
			beishu = beishus == null || beishus[0].equals("") ? "0"
					: beishus[0]; // 倍数
			reddanma = reddanmas == null || reddanmas[0].equals("") ? "0"
					: reddanmas[0];
			redtuoma = redtuomas == null || redtuomas[0].equals("") ? "0"
					: redtuomas[0];
			bluedanma = bluedanmas == null || bluedanmas[0].equals("") ? "0"
					: bluedanmas[0];
			bluetuoma = bluetuomas == null || bluetuomas[0].equals("") ? "0"
					: bluetuomas[0];
			oneMoney = oneMoneys == null || oneMoneys[0].equals("") ? "0"
					: oneMoneys[0];
			amount = amounts == null || amounts[0].equals("") ? "0"
					: amounts[0]; // 金额
			type = types == null || types[0].equals("") ? "0" : types[0];
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				addNumber = addNumbers == null || addNumbers[0].equals("") ? "0"
						: addNumbers[0];
			}
		}
		logger.info("userno:" + userno + " reddanma:" + reddanma
				+ " redtuoma:" + redtuoma + " bluedanma:" + bluedanma
				+ " bluetuoma:" + bluetuoma + " addNumber:" + addNumber);
	
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			// 执行正常的投注
			reddanma = reddanma.replace(",", "");
			redtuoma = redtuoma.replace(",", "");
			bluedanma = bluedanma.replace(",", "");
			bluetuoma = bluetuoma.replace(",", "");
			logger.info("userno:" + userno + " reddanma:" + reddanma
					+ " redtuoma:" + redtuoma + " bluedanma:" + bluedanma
					+ " bluetuoma:" + bluetuoma);
			Vector danmaVector = LotteryAlgorithmUtil
					.getStringArrayFromString(reddanma);
			Collections.sort(danmaVector);
			Vector tuomaVector = LotteryAlgorithmUtil
					.getStringArrayFromString(redtuoma);
			Collections.sort(tuomaVector);

			Vector danmaVectorH = LotteryAlgorithmUtil
					.getStringArrayFromString(bluedanma);
			Collections.sort(danmaVectorH);
			Vector tuomaVectorH = LotteryAlgorithmUtil
					.getStringArrayFromString(bluetuoma);
			Collections.sort(tuomaVectorH);

			zhuma = LotteryAlgorithmUtil.joinStringArrayWithSpace(danmaVector)
					+ "$"
					+ LotteryAlgorithmUtil
							.joinStringArrayWithSpace(tuomaVector)
					+ "-"
					+ LotteryAlgorithmUtil
							.joinStringArrayWithSpace(danmaVectorH)
					+ "$"
					+ LotteryAlgorithmUtil
							.joinStringArrayWithSpace(tuomaVectorH);
			if(danmaVectorH==null||danmaVectorH.size()==0){
				zhuma = LotteryAlgorithmUtil.joinStringArrayWithSpace(danmaVector)
				+ "$"
				+ LotteryAlgorithmUtil
						.joinStringArrayWithSpace(tuomaVector)
				+ "-"
//				+ LotteryAlgorithmUtil
//						.joinStringArrayWithSpace(danmaVectorH)
//				+ "$"
				+ LotteryAlgorithmUtil
						.joinStringArrayWithSpace(tuomaVectorH);
			}
			logger.info("userno:" + userno + " zhuma" + zhuma);
			String oneAmount=oneMoney;
			String ttssBet = "";
			String wanfa="DLT_DT";
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
				request.setAttribute("newbetcode", zhuma);
				request.setAttribute("oneMoney", oneAmount);
				request.setAttribute("lotno", "T01001");
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
				request.setAttribute("oneMoney", oneAmount);
				request.setAttribute("newbetcode", zhuma);
				request.setAttribute("lotno", "T01001");
				return "wap/zengcai/zengcai";
			} else {
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				ttssBet = JsonToJrtLotUtil.sendToBet(userno, "T01001", term, zhuma, beishu, oneAmount, wanfa, amount, addNumber, channel);

			}
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

	/**
	 *大乐透投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/bet.jspa")
	public String bet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("T01001");
		String channel = WapUtil.getChannelId(request);

		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		String oneMoney, zhuma, amount, beishu, zhushu,type, addNumber = "1", token;
		logger.info("zhushu:" + request.getParameter("zhushu"));
		logger.info("beishu:" + request.getParameter("beishu"));
		logger.info("zhuma:" + request.getParameter("zhuma"));
		logger.info("amount:" + request.getParameter("amount"));
		logger.info("type:" + request.getParameter("type"));
		logger.info("token:" + request.getParameter("token"));

		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			// 获取输入页面的参数
			zhushu = request.getParameter("zhushu"); // 注数
			beishu = request.getParameter("beishu"); // 倍数
			zhuma = request.getParameter("zhuma"); // 注码
			logger.info("action zhuma:" + zhuma);
			amount = request.getParameter("amount"); // 金额
			oneMoney = request.getParameter("oneMoney");// 是否追加
			type = request.getParameter("type");
			token = request.getParameter("token");// 判断是否重复提交
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber");
			}
		} else {// 获取request.getAttribute中的存储
			String[] zhushus = request.getAttribute("zhushu") == null ? null
					: (String[]) request.getAttribute("zhushu");
			String[] beishus = request.getAttribute("beishu") == null ? null
					: (String[]) request.getAttribute("beishu");
			String[] zhumas = request.getAttribute("zhuma") == null ? null
					: (String[]) request.getAttribute("zhuma");
			String[] amounts = request.getAttribute("amount") == null ? null
					: (String[]) request.getAttribute("amount");
			String[] types = request.getAttribute("type") == null ? null
					: (String[]) request.getAttribute("type");
			String[] oneMoneys = request.getAttribute("oneMoney") == null ? null
					: (String[]) request.getAttribute("oneMoney");
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");
			String[] addNumbers = null;
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber") == null ? null
						: (String[]) request.getAttribute("addNumber");
			}
			// 获取输入页面的参数
			zhushu = zhushus == null || zhushus[0].equals("") ? "0"
					: zhushus[0]; // 注数
			beishu = beishus == null || beishus[0].equals("") ? "0"
					: beishus[0]; // 倍数
			zhuma = zhumas == null || zhumas[0].equals("") ? "0" : zhumas[0]; // 注码
			amount = amounts == null || amounts[0].equals("") ? "0"
					: amounts[0]; // 金额
			oneMoney = oneMoneys == null || oneMoneys[0].equals("") ? "0"
					: oneMoneys[0];
			type = types == null || types[0].equals("") ? "0" : types[0];
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				addNumber = addNumbers == null || addNumbers[0].equals("") ? "0"
						: addNumbers[0];
			}
		}
		logger.info("userno:" + userno + " zhushu:" + zhushu
				+ " beishu:" + beishu + " zhuma:" + zhuma + " addNumber:"
				+ addNumber + "oneMoney:" + oneMoney);
		
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			// 执行正常的投注
			String ttssBet = "";
			String wanfa="";
			if("DSJX".equals(type)){
				wanfa = "DLT_DS";
			}
			if("FSZX".equals(type)||"JXFS".equals(type)){
				wanfa = "DLT_FS";
			}
			if("SXZX".equals(type)){
				wanfa = "DLT_12X2";
			}
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
				request.setAttribute("newbetcode", zhuma);
				request.setAttribute("oneMoney", oneMoney);
				request.setAttribute("lotno", "T01001");
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
				request.setAttribute("newbetcode", zhuma);
				request.setAttribute("oneMoney", oneMoney);
				request.setAttribute("lotno", "T01001");
				return "wap/zengcai/zengcai";
			} else {
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				ttssBet = JsonToJrtLotUtil.sendToBet(userno, "T01001", term, zhuma, beishu, oneMoney, wanfa, amount, addNumber, channel);

			}

			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

}
