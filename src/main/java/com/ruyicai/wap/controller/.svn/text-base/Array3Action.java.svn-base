package com.ruyicai.wap.controller;

/**
 * Array3Action 排列3业务操作
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
 * 创建者：tianqk
 * 创建日期：
 * 修改记录：
 */
import static com.ruyicai.wap.Global.rbint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buybal.lot.lottype.Array5Util;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;

@RequestMapping("/array3")
@Controller
public class Array3Action {

	private static final Logger logger = Logger.getLogger(Array3Action.class);

	/**
	 * 排列3机选投注信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/autoSelectionDetail.jspx",method=RequestMethod.POST)
	public String autoSelectionDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		String zhushu = request.getParameter("zhushu");
		String beishu = request.getParameter("beishu");
		logger.info(" zhushu" + zhushu
				+ " beishu" + beishu);
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null) {
				addNumber = "1";
			}
			logger.info(" addNumber" + addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		if (VerificationAlgorithmUtil.isStringFilter(zhushu)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhushu = CommonUtil.QJToBJChange(zhushu);
		beishu = CommonUtil.QJToBJChange(beishu);
		int zhushuInt = Integer.parseInt(zhushu);
		int beishuInt = Integer.parseInt(beishu);
		String newzhuma = "";
		String zhuma = "";
		for (int i = 0; i < zhushuInt; i++) {
			// 随机生成3D注码
			Vector vector = LotteryAlgorithmUtil
					.getRandomIntArrayWithinRange3D(10);
			// Collections.sort(vector);
			zhuma += CommonUtil.A3_ZXFS
					+ LotteryAlgorithmUtil.joinStringArrayWithComma(vector)
					+ ";";
			// 转成赠送明细里的数组
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(vector)
					+ "<br/>";
		}
		if (zhuma.endsWith(";"))
			zhuma = zhuma.substring(0, zhuma.length() - 1);
		logger.info(" zhuma:" + zhuma
				+ " newzhuma" + newzhuma);
		// 金额
		Integer amount = zhushuInt * beishuInt
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.TCverifyAmount(amount)) {

			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "BetSuccess";
		}
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("zhushu", zhushuInt);
		request.setAttribute("beishu", beishu);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber",
					String.valueOf(Integer.parseInt(addNumber)));
		}
		logger.info(" zhuma:" + zhuma
				+ " zhushu:" + (zhushuInt * beishuInt) + " beishu:" + beishu
				+ " newzhuma:" + newzhuma + " amount:" + amount);
		request.setAttribute("pageType", "autoSelection");
		return "wap/array3/Array3DBetDetail";
	}

	/**
	 * 随机投注（随机注码已经生成）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/autoDetail.jspx",method=RequestMethod.POST)
	public String autoDetail( 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获取当前期号
		String zhushu = "1";
		String beishu = "1";
		String newzhuma = request.getParameter("newzhuma");
		String zhuma = request.getParameter("betNo");
		logger.info(" zhushu" + zhushu
				+ " beishu" + beishu);
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber" + addNumber);
			if (addNumber == null) {
				addNumber = "1";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		zhushu = CommonUtil.QJToBJChange(zhushu);
		beishu = CommonUtil.QJToBJChange(beishu);
		int zhushuInt = Integer.parseInt(zhushu);
		int beishuInt = Integer.parseInt(beishu);
		// 金额
		Integer amount = zhushuInt * beishuInt
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.TCverifyAmount(amount)) {

			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/BetSuccess";
		}
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("zhushu", zhushuInt * beishuInt);
		request.setAttribute("beishu", beishu);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber",
					String.valueOf(Integer.parseInt(addNumber)));
		}
		logger.info(" zhuma:" + zhuma
				+ " zhushu:" + (zhushuInt * beishuInt) + " beishu:" + beishu
				+ " newzhuma:" + newzhuma + " amount:" + amount);
		request.setAttribute("pageType", "autoSelection");
		return "wap/array3/Array3DBetDetail";
	}

	/**
	 * 排列三直选复式投注信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/directSelectionSingleDetail.jspx",method=RequestMethod.POST)
	public String directSelectionSingleDetail(
			 HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取请求参数
		String hundreds_No = request.getParameter("hundreds_No");// 百位数
		String tens_No = request.getParameter("tens_No");// 十位数
		String units_No = request.getParameter("units_No");// 个位数
		String beishu_No = request.getParameter("beishu_No");// 倍数
		String pageType = request.getParameter("pageType");
		hundreds_No = CommonUtil.QJToBJChange(hundreds_No);
		tens_No = CommonUtil.QJToBJChange(tens_No);
		units_No = CommonUtil.QJToBJChange(units_No);
		beishu_No = CommonUtil.QJToBJChange(beishu_No);

		// 将注码前加"0"
		String hundreds_No1 = LotteryAlgorithmUtil.addZero3D(hundreds_No);
		String tens_No1 = LotteryAlgorithmUtil.addZero3D(tens_No);
		String units_No1 = LotteryAlgorithmUtil.addZero3D(units_No);
		logger.info(" hundreds_No:"
				+ hundreds_No1 + " tens_No:" + tens_No1 + " units_No:"
				+ units_No1 + " beishu_No:" + beishu_No);
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber" + addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "hundreds_No="+hundreds_No+"&tens_No="+tens_No+"&units_No="+units_No+"&addNumber="+addNumber
				+"&beishu_No="+beishu_No+"&pageType="+pageType;
		request.getSession().setAttribute("parameter", parameter);

		if (VerificationAlgorithmUtil.isStringFilter(hundreds_No)
				|| VerificationAlgorithmUtil.isStringFilter(tens_No)
				|| VerificationAlgorithmUtil.isStringFilter(units_No)
				|| VerificationAlgorithmUtil.isStringFilter(beishu_No)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/array3/Array3DirectSingle";
		}
		Map map = new HashMap();
		map.put("hundreds_No", hundreds_No1);
		map.put("tens_No", tens_No1);
		map.put("units_No", units_No1);
		map.put("beishu", beishu_No);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil
				.verify3DDirectSelectionSingle(map);
		logger.info(" message:" + errorMessage);
		if (errorMessage != null) {
			request.setAttribute("hundreds_No", hundreds_No);
			request.setAttribute("tens_No", tens_No);
			request.setAttribute("units_No", units_No);
			request.setAttribute("beishu_No", beishu_No);
			request.setAttribute("err_msg", errorMessage);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array3/Array3DirectSingle";
		} else {
			String beishuStr = "";
			if (beishu_No.trim().equals("")) {
				beishuStr = "1";
			} else {
				beishuStr = beishu_No;
			}
			// 合成字符串
			String zhuma = "";
			String newzhuma = "";
			if (pageType == "ZXFS" || pageType.equals("ZXFS")) {
				zhuma = CommonUtil.A3_ZXFS + hundreds_No + "," + tens_No + ","
						+ units_No;
				newzhuma = hundreds_No + "," + tens_No + "," + units_No;
			}

			// 注数
			Integer zhushu = hundreds_No.length() * tens_No.length()
					* units_No.length();
			// 倍数
			int beishu = 1;
			if (!beishu_No.equals("")) {
				beishu = Integer.parseInt(beishu_No);
			} else {
				beishu = 1;
			}
			// 金额
			Integer amount = zhushu * beishu
					* LotteryAlgorithmUtil.priceOfCaipiao;
			
			if (!CommonUtil.TCverifyAmount(amount)) {

				logger.info("单次投注金额不能超过两万元，amt" + amount);
				request.setAttribute("err_msg", MessageUtil.TC_AmountError);
				return "wap/BetSuccess";
			}
			// 将参数设置在request中
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("zhushu", zhushu * beishu);
			request.setAttribute("beishu", beishu_No);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("amount", amount);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info(" zhushu:"
					+ (zhushu * beishu) + " beishu:" + beishu_No
					+ " addNumber:" + addNumber + " amount:" + amount
					+ " newzhuma:" + newzhuma + " zhuma:" + zhuma);
			return "wap/array3/Array3DBetDetail";
		}

	}

	/**
	 * 排列三 组六单式
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/array3Group6Detail.jspx",method=RequestMethod.POST)
	public String array3Group6Detail(
			 HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		String term = CommonUtil.getTerm("T01002");
		String zhuma = request.getParameter("zhuma"); // 不带","注码
		String beishuStr = request.getParameter("beishu"); // 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:" + addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishuStr+"&pageType="+pageType+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" zhuma:" + zhuma
				+ " beishuStr:" + beishuStr + " term:" + term + " pageType:"
				+ pageType);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)
				|| VerificationAlgorithmUtil.isStringFilter(beishuStr)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishuStr = CommonUtil.QJToBJChange(beishuStr);
		// 验证注码的合法性
		Map map = new HashMap();
		map.put("zhuma", zhuma);
		map.put("beishu", beishuStr);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil.verifyGroup6Single(map);
		logger.info(" message:" + errorMessage);
		if (errorMessage != null) {
			request.setAttribute("err_msg", errorMessage);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishuStr);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array3/Array3Group6";
		} else {

			// 将不带"0"的注码转成数组
			Vector vector = LotteryAlgorithmUtil
					.getStringArrayFromString3D(zhuma);
			zhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
			zhuma = CommonUtil.A3_ZXZX + zhuma;
			// 将注码数组转成带","的新注码
			String newzhuma = LotteryAlgorithmUtil
					.joinStringArrayWithComma(vector);
			// 组6单式的注数
			Integer zhushu = 1;
			// 将倍数字符串转成Integer
			Integer beishu = 0;
			if (beishuStr.trim().equals("")) {
				beishu = 1;
			} else {
				beishu = Integer.parseInt(beishuStr);
			}
			// 金额
			Integer amount = zhushu * beishu
					* LotteryAlgorithmUtil.priceOfCaipiao;
			if (!CommonUtil.TCverifyAmount(amount)) {

				logger.info("单次投注金额不能超过两万元，amt" + amount);
				request.setAttribute("err_msg", MessageUtil.TC_AmountError);
				return "wap/BetSuccess";
			}
			// 将参数设置在request中
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("zhushu", zhushu * beishu);
			request.setAttribute("beishu", beishuStr);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("amount", amount);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info(" zhushu:"
					+ (zhushu * beishu) + " beishu:" + beishuStr
					+ " addNumber:" + addNumber + " amount:" + amount
					+ " newzhuma:" + newzhuma + " zhuma:" + zhuma);
			return "wap/array3/Array3DBetDetail";
		}

	}

	/**
	 * 排列三组选投注信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/array3GroupDetail.jspx",method=RequestMethod.POST)
	public String array3GroupDetail(
			 HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取请求参数
		String hundreds_No = request.getParameter("hundreds_No");// 百位数
		String tens_No = request.getParameter("tens_No");// 十位数
		String units_No = request.getParameter("units_No");// 个位数
		String beishu_No = request.getParameter("beishu_No");// 倍数
		String pageType = request.getParameter("pageType");
		hundreds_No = CommonUtil.QJToBJChange(hundreds_No);
		tens_No = CommonUtil.QJToBJChange(tens_No);
		units_No = CommonUtil.QJToBJChange(units_No);
		beishu_No = CommonUtil.QJToBJChange(beishu_No);
		// 将注码前加"0"
		String hundreds_No1 = LotteryAlgorithmUtil.addZero3D(hundreds_No);
		String tens_No1 = LotteryAlgorithmUtil.addZero3D(tens_No);
		String units_No1 = LotteryAlgorithmUtil.addZero3D(units_No);
		logger.info(" hundreds_No1:"
				+ hundreds_No1 + " tens_No1:" + tens_No1 + " units_No1:"
				+ units_No1 + " beishu_No" + beishu_No);
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:" + addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/array3/Array3Group6";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		if (VerificationAlgorithmUtil.isStringFilter(hundreds_No)
				|| VerificationAlgorithmUtil.isStringFilter(tens_No)
				|| VerificationAlgorithmUtil.isStringFilter(units_No)
				|| VerificationAlgorithmUtil.isStringFilter(beishu_No)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/array3/Array3Group6";
		}
		Map map = new HashMap();
		map.put("hundreds_No", hundreds_No1);
		map.put("tens_No", tens_No1);
		map.put("units_No", units_No1);
		map.put("beishu", beishu_No);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil
				.verify3DDirectSelectionSingle(map);
		logger.info(" errorMessage:"
				+ errorMessage);
		if (errorMessage != null) {
			request.setAttribute("hundreds_No", hundreds_No);
			request.setAttribute("tens_No", tens_No);
			request.setAttribute("units_No", units_No);
			request.setAttribute("beishu_No", beishu_No);
			request.setAttribute("err_msg", errorMessage);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array3/Array3Group6";
		} else if (hundreds_No.equals(tens_No) && tens_No.equals(units_No)) {
			request.setAttribute("err_msg", "不能出现豹子号！");
			return "wap/array3/Array3Group6";
		} else {
			String beishuStr = "";
			if (beishu_No.trim().equals("")) {
				beishuStr = "1";
			} else {
				beishuStr = beishu_No;
			}
			// 合成字符串
			String zhuma = "";
			String newzhuma = "";
			if (pageType == "group" || pageType.equals("group")) {
				zhuma = CommonUtil.A3_ZXZX + hundreds_No + "," + tens_No + ","
						+ units_No;
				newzhuma = hundreds_No + "," + tens_No + "," + units_No;
			}
			// 注数
			Integer zhushu = 1;
			// 倍数
			int beishu = 1;
			if (!beishu_No.equals("")) {
				beishu = Integer.parseInt(beishu_No);
			} else {
				beishu = 1;
			}
			// 金额
			Integer amount = zhushu * beishu
					* LotteryAlgorithmUtil.priceOfCaipiao;
			if (!CommonUtil.TCverifyAmount(amount)) {

				logger.info("单次投注金额不能超过两万元，amt" + amount);
				request.setAttribute("err_msg", MessageUtil.TC_AmountError);
				return "wap/BetSuccess";
			}
			// 将参数设置在request中
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("zhushu", zhushu * beishu);
			request.setAttribute("beishu", beishu_No);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("amount", amount);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info(" zhushu:"
					+ (zhushu * beishu) + " beishu:" + beishu_No
					+ " addNumber:" + addNumber + " amount:" + amount
					+ " newzhuma:" + newzhuma + " zhuma:" + zhuma);
			return "wap/array3/Array3DBetDetail";
		}

	}

	/**
	 * p3直选和值投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/heZhiDetail.jspx",method=RequestMethod.POST)
	public String heZhiDetail( 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取页面参数
		String hezhi = request.getParameter("hezhi");
		String beishu = request.getParameter("beishu");
		String pageType = request.getParameter("pageType");
		String addNumber = "";
		hezhi = CommonUtil.QJToBJChange(hezhi);
		beishu = CommonUtil.QJToBJChange(beishu);
		logger.info(" hezhi:" + hezhi + " beishu"
				+ beishu + " pageType:" + pageType);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:" + addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "hezhi="+hezhi+"&beishu="+beishu+"&pageType="+pageType+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		if (VerificationAlgorithmUtil.isStringFilter(hezhi)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		// 验证数据合法性
		String message = "";
		Map map = new HashMap();
		map.put("hezhi", hezhi);
		map.put("beishu", beishu);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		if (pageType.trim().equals("group6HZ")) {
			message = VerificationAlgorithmUtil.verify3DGroup6HeZhi(map);
		} else if (pageType.trim().equals("directSelectionHZ")) {
			message = VerificationAlgorithmUtil.verifyArray3HeZhi(map);
		} else {
			message = VerificationAlgorithmUtil.verifyGroup3HeZhi(map);
		}
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("hezhi", hezhi);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			if (pageType.trim().equals("directSelectionHZ")) {
				return "wap/array3/Array3DirectSelectionHeZhi";
			}
			if (pageType.trim().equals("group3HZ")) {
				return "wap/array3/Array3Group3HeZhi";
			}
			if (pageType.trim().equals("group6HZ")) {
				return "wap/array3/Array3Group6HeZhi";
			}
			if (pageType.trim().equals("groupHZ")) {
				return "wap/array3/Array3GroupHeZhi";
			}
		}
		// 将String转成int
		int hezhiInt = Integer.parseInt(hezhi);
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		// 注数
		int zhushu = 0;
		String zhuma = "";
		String newzhuma = "";
		if (pageType.trim().equals("directSelectionHZ")) {
			zhuma = CommonUtil.A3_HZZX + hezhi;
			zhushu = LotteryAlgorithmUtil.getArray3HeZhiNumber(hezhiInt);
			newzhuma = hezhi;
		}
		if (pageType.trim().equals("group3HZ")) {
			zhuma = CommonUtil.A3_Z3HZ + hezhi;
			zhushu = LotteryAlgorithmUtil.getGroup3HeZhi3DNumber(hezhiInt);
			newzhuma = hezhi;

		}
		if (pageType.trim().equals("group6HZ")) {
			zhuma = CommonUtil.A3_Z6HZ + hezhi;
			zhushu = LotteryAlgorithmUtil.getGroup6HeZhi3DNumber(hezhiInt);
			newzhuma = hezhi;
		}
		if (pageType.trim().equals("groupHZ")) {
			zhuma = CommonUtil.A3_ZXHZ + hezhi;
			zhushu = LotteryAlgorithmUtil.getGroup3HeZhi3DNumber(hezhiInt)
					+ LotteryAlgorithmUtil.getGroup6HeZhi3DNumber(hezhiInt);
			newzhuma = hezhi;
		}
		// 金额
		int amount = beishuInt * zhushu * LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.TCverifyAmount(amount)) {

			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/BetSuccess";
		}
		request.setAttribute("zhushu", zhushu * beishuInt);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("hezhi", hezhi);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("pageType", pageType);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (zhushu * beishuInt) + " beishu:" + beishu + " addNumber:"
				+ addNumber + " amount:" + amount + " newzhuma:" + newzhuma
				+ " zhuma:" + zhuma + " hezhi:" + hezhi);
		return "wap/array3/Array3DBetDetail";
	}

	/**
	 * 组3包号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/array3BaoHaoDetail.jspx",method=RequestMethod.POST)
	public String array3BaoHaoDetail(
			 HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取输入页面的参数
		String zhuma = request.getParameter("zhuma"); // 注码
		String beishuStr = request.getParameter("beishu"); // 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:" + addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishuStr+"&pageType="+pageType+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" zhuma:" + zhuma
				+ " beishuStr:" + beishuStr + " pageType:" + pageType);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)
				|| VerificationAlgorithmUtil.isStringFilter(beishuStr)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishuStr = CommonUtil.QJToBJChange(beishuStr);
		// 将注码加"0"
		String zhuma1 = LotteryAlgorithmUtil.addZero3D(zhuma);
		// 验证输入参数合法性
		Map map = new HashMap();
		map.put("zhuma", zhuma1);
		map.put("beishu", beishuStr);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = "";
		if (pageType.equals("group3BH")) {
			errorMessage = VerificationAlgorithmUtil
					.verify3DGroup3Multiple(map);
		}
		if (pageType.equals("group6BH")) {
			errorMessage = VerificationAlgorithmUtil
					.veritfy3DGroup6Multiple(map);
		}
		logger.info(" errorMessage:"
				+ errorMessage);
		if (errorMessage != null) {
			request.setAttribute("err_msg", errorMessage);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishuStr);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			if (pageType.equals("group3BH")) {
				return "wap/array3/Array3Group3BH";
			}
			if (pageType.equals("group6BH")) {
				return "wap/array3/Array3Group6BH";
			}
		} else {
			int zhushu = 0;
			String newzhuma = zhuma;
			if (pageType.equals("group3BH")) {
				zhuma1 = CommonUtil.A3_Z3BH + zhuma;
				zhushu = LotteryAlgorithmUtil.getGroup3Multiple3DNumber(zhuma
						.length());
			}
			if (pageType.equals("group6BH")) {
				zhuma1 = CommonUtil.A3_Z6BH + zhuma;
				zhushu = LotteryAlgorithmUtil.getGroup6Multiple3DNumber(zhuma
						.length());
			}

			// 将倍数转成int
			int beishu = 0;
			if (beishuStr.trim().equals("")) {
				beishu = 1;
			} else {
				beishu = Integer.parseInt(beishuStr);
			}
			// 计算金额
			int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
			if (!CommonUtil.TCverifyAmount(amount)) {

				logger.info("单次投注金额不能超过两万元，amt" + amount);
				request.setAttribute("err_msg", MessageUtil.TC_AmountError);
				return "wap/BetSuccess";
			}
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu * beishu);
			request.setAttribute("beishu", beishuStr);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma1);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info(" zhushu:"
					+ (zhushu * beishu) + " beishu:" + beishuStr
					+ " addNumber:" + addNumber + " amount:" + amount
					+ " newzhuma:" + newzhuma + " zhuma:" + zhuma1
					+ " pageType:" + pageType);
			// 转到确认投注页面
			return "wap/array3/Array3DBetDetail";
		}
		return "wap/wapindex";
	}

	/**
	 * 组6包号
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/group6MultipleDetail.jspx",method=RequestMethod.POST)
	public String group6MultipleDetail(
			 HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取输入页面的参数
		String zhuma = request.getParameter("zhuma"); // 注码
		String beishu = request.getParameter("beishu"); // 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:" + addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		logger.info(" zhuma:" + zhuma);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishu = CommonUtil.QJToBJChange(beishu);
		// 将注码加"0"
		String zhuma1 = LotteryAlgorithmUtil.addZero3D(zhuma);
		// 验证输入参数合法性
		Map map = new HashMap();
		map.put("zhuma", zhuma1);
		map.put("beishu", beishu);
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil
				.veritfy3DGroup6Multiple(map);
		logger.info(" message:" + errorMessage);
		if (errorMessage != null) {
			request.setAttribute("err_msg", errorMessage);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			//to do
			return "";
		} else {
			// 计算注码,注数,金额,倍数
			Vector vector = LotteryAlgorithmUtil
					.getStringArrayFromString(zhuma1);
			// 对注码数组进行排序
			Collections.sort(vector);
			int len = vector.size();
			// 将不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil
					.getStringArrayFromString3D(zhuma);
			// 对数组进行排序
			Collections.sort(vector2);
			// 获得带","的注码
			String newzhuma = LotteryAlgorithmUtil
					.joinStringArrayWithComma(vector2);
			int zhushu = LotteryAlgorithmUtil.getGroup6Multiple3DNumber(len);
			int beishuInt = 0;
			if (beishu.trim().equals("")) {
				beishuInt = 1;
			} else {
				beishuInt = Integer.parseInt(beishu);
			}
			int amount = zhushu * beishuInt
					* LotteryAlgorithmUtil.priceOfCaipiao;
			if (!CommonUtil.TCverifyAmount(amount)) {

				logger.info("单次投注金额不能超过两万元，amt" + amount);
				request.setAttribute("err_msg", MessageUtil.TC_AmountError);
				return "wap/BetSuccess";
			}
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu * beishuInt);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma1);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			logger.info(" zhushu:" + zhushu
					* beishuInt + " beishu:" + "beishu" + " newzhuma:"
					+ newzhuma + " zhuma" + zhuma1);
			// 转到确认投注页面
			//to do
			return "wap/array3/Array3DBetDetail";
		}
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
	@RequestMapping(value="/bet.jspa")
	public String bet( 
			HttpServletRequest request, HttpServletResponse response,Model model)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String lotNo="T01002";
		String term = CommonUtil.getTerm("T01002");
		String channel = WapUtil.getChannelId(request);
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");

		String zhushu,beishu, amount, zhuma, pageType, addNumber = "1", token;
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			// 获取页面参数
			zhushu = request.getParameter("zhushu");// 注数
			zhuma = request.getParameter("zhuma");// 注码,不带","
			beishu = request.getParameter("beishu"); // 倍数
			amount = request.getParameter("amount"); // 金额
			pageType = request.getParameter("pageType");
			token = request.getParameter("token");
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
			String[] pageTypes = request.getAttribute("pageType") == null ? null
					: (String[]) request.getAttribute("pageType");
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
			pageType = pageTypes == null || pageTypes[0].equals("") ? "0"
					: pageTypes[0];
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				addNumber = addNumbers == null || addNumbers[0].equals("") ? "0"
						: addNumbers[0];
			}
		}
	
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			// 执行正常的投注
		
			logger.info("userno:" + userno + "zhushu:" + zhushu
					+ " beishu:" + beishu + " zhuma:" + zhuma + " amount:"
					+ amount + " pageType:" + pageType + " addNumber:"
					+ addNumber);
			String wanfa="";
			if (pageType.equals("group3BH")) {
				wanfa = "A3_Z3BH";
			}
			if (pageType.equals("group6BH")) {
				wanfa = "A3_Z6BH" ;
			}
			if (pageType.trim().equals("directSelectionHZ")) {
				wanfa = "A3_HZZX" ;
			}
			if (pageType.trim().equals("group3HZ")) {
				wanfa = "A3_Z3HZ";
			}
			if (pageType.trim().equals("group6HZ")) {
				wanfa = "A3_Z6HZ";
			}
			if (pageType.trim().equals("groupHZ")) {
				wanfa ="A3_ZXHZ";
			}
			if (pageType == "ZXFS" || pageType.equals("ZXFS")) {
				wanfa = "A3_ZXFS" ;
			}
			if (pageType == "group" || pageType.equals("group")) {
				wanfa = "A3_ZXZX" ;
			}
			// 取得单式的注码
			String bet_code = "";
			String ttssBet = "";
			bet_code=zhuma;
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
				request.setAttribute("lotno", lotNo);
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
				request.setAttribute("lotno", lotNo);
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
			ttssBet = JsonToJrtLotUtil.sendToBet(userno, lotNo, term, bet_code, beishu, "2", wanfa, amount, addNumber, channel);
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}
	// 单式机选修改
	@RequestMapping(value="/modifyBetDetail.jspx",method=RequestMethod.POST)

	public String modifyBetDetail(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String zhushu = request.getParameter("zhushu");
		String amount = request.getParameter("amount");
		String type = request.getParameter("type");
		String term = request.getParameter("term");
		String beishu = request.getParameter("beishu");
		String autoMethod = request.getParameter("autoMethod");
		String zhuma = request.getParameter("zhuma");
		String addNumber = "";
		if (rbint.getString("array3addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
		}
		StringBuffer s1 = new StringBuffer();
		String s2 = "";
		String newzhuma = "";
		// 从前台页面接受显示格式的注码，并生成投注格式的注码
		for (int i = 0; i < Integer.parseInt(zhushu); i++) {
			String s = request.getParameter("i" + i);
			if ("".equals(s) || s == null) {
				continue;
			}
			s2 = s1.append(s + "<br/>").toString();
		}
		zhuma = s2;
		String[] zhushuStr = s2.split("<br/>");
		if (Array5Util.checkBeishu(beishu) == false) {
			request.setAttribute("err_msg", "倍数格式错误");
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("array3addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array3/Array3modifyAutoSelection";
		}
		int newzhushu = zhushuStr.length ;
		String newzhushuStr = newzhushu + "";
		// 计算新金额
		int amt = newzhushu * Integer.parseInt(beishu) * 2;
		String newamount = amt + "";
		if (Array5Util.checkAddNumber(addNumber) == false) {
			request.setAttribute("err_msg", "追期格式错误");
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array3/Array3modifyAutoSelection";
		}
		// 验证注码格式
			for (int i = 0; i < zhushuStr.length; i++) {
				if (Array5Util.checkArray3Code(zhushuStr[i]) == false) {
					request.setAttribute("err_msg", "注码格式错误");
					request.setAttribute("zhuma", zhuma);
					request.setAttribute("beishu", beishu);
					if (rbint.getString("addNumberSwitch").equals("1")) {
						request.setAttribute("addNumber", addNumber);
					}
					return "wap/array3/Array3modifyAutoSelection";
				}
			}
			if (amt > 20000) {
				logger.info("单次投注金额不能超过两万元，amt" + amt);
				request.setAttribute("err_msg", MessageUtil.TC_AmountError);
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				if (rbint.getString("addNumberSwitch").equals("1")) {
					request.setAttribute("addNumber", "1");
				}
				return "wap/array3/Array3modifyAutoSelection";
			}

		// 生成显示格式注码
		newzhuma = zhuma;
		String zhumaStr = "";
		for (int i = 0; i < zhushuStr.length; i++) {
			zhumaStr = zhumaStr+"1|"+zhushuStr[i]+";";
		}

		request.setAttribute("zhuma", zhumaStr);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhushu", newzhushuStr);
		request.setAttribute("amount", newamount);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("pageType", type);
		request.setAttribute("term", term);
		request.setAttribute("autoMethod", autoMethod);
		return "wap/array3/Array3DBetDetail";
	}
}
