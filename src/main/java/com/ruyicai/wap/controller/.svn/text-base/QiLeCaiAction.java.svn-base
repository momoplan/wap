package com.ruyicai.wap.controller;
import static com.ruyicai.wap.Global.rbint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
@RequestMapping("/qilecai")
@Controller
public class QiLeCaiAction{

	private static final Logger logger = Logger.getLogger(QiLeCaiAction.class);
 

	/**
	 * 七乐彩单式自选投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/selfSelectionSingleBetDetail.jspx",method=RequestMethod.POST)
	public String selfSelectionSingleBetDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String zhuma = request.getParameter("zhuma");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
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
		String parameter = "zhuma="+zhuma+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" zhuma:" + zhuma
				+ " beishu:" + beishu + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		String term = CommonUtil.getTerm("F47102");
		Map map = new HashMap();
		map.put("zhuma", zhuma);
		map.put("beishu", beishu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String message = VerificationAlgorithmUtil
				.verifyQilecaiSingleSelfSelection(map);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/qilecai/SingleSelfSelection";
		}
		Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		Collections.sort(vector);
		String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
		zhuma = CommonUtil.getSortString(zhuma);
		int zhushu = 1;
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		int amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		request.setAttribute("zhushu", zhushu * beishuInt);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		logger.info(" zhushu:"
				+ (zhushu * beishuInt) + " beishu:" + beishu + " addNumber:"
				+ addNumber + " amount:" + amount + " newzhuma:" + newzhuma
				+ " zhuma:" + zhuma + " type:" + type + " term" + term);
		return "wap/qilecai/BetDetail";
	}

	/**
	 * 七乐彩复式自选投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/selfSelectionMultipleBetDetail.jspx",method=RequestMethod.POST)
	public String selfSelectionMultipleBetDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String zhuma = request.getParameter("zhuma");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger
					.info( " addNumber:"
							+ addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" zhuma:" + zhuma
				+ " beishu:" + beishu + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		if (zhuma.length()<16) {
			request.setAttribute("err_msg", "注码个数不能小于8");
			return "wap/BetSuccess";
		}if (zhuma.length()>32) {
			request.setAttribute("err_msg", "注码个数不能大于16");
			return "wap/BetSuccess";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		String term = CommonUtil.getTerm("F47102");
		Map map = new HashMap();
		map.put("zhuma", zhuma);
		map.put("beishu", beishu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String message = VerificationAlgorithmUtil
				.verifyQilecaiMultipleSelfSelection(map);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/qilecai/MultipleSelfSelection";
		}
		zhuma = CommonUtil.getSortString(zhuma);
		Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		if (vector.size() == 7) {
			type = "ZXDS";
		}
		Collections.sort(vector);
		String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector);
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		int zhushu = LotteryAlgorithmUtil.getQilecaiNumber(vector.size());
		int amount = beishuInt * zhushu * LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request
					.setAttribute(
							"message",
							MessageUtil.QLCA_selfSelectionMultipleBetDetail_AmountError);
			return "wap/qilecai/MultipleSelfSelection";
		}
		request.setAttribute("zhushu", zhushu * beishuInt);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("zhuma", zhuma);
		System.out.println(zhuma);
		request.setAttribute("type", type);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		logger.info(" zhushu:"
				+ (zhushu * beishuInt) + " beishu:" + beishu + " addNumber:"
				+ addNumber + " amount:" + amount + " newzhuma:" + newzhuma
				+ " zhuma:" + zhuma + " type:" + type + " term" + term);
		return "wap/qilecai/BetDetail";
	}

	/**
	 * 七乐彩单式机选投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/singleAutoSelection.jspx",method=RequestMethod.POST)
	public String singleAutoSelection(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String zhushuStr = request.getParameter("zhushuStr");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null) {
				addNumber = "1";
			}
			logger
					.info(" addNumber:"
							+ addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhushuStr="+zhushuStr+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" zhushuStr:" + zhushuStr
				+ " beishu:" + beishu + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(zhushuStr)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		String term = CommonUtil.getTerm("F47102");
		String zhuma = "";
		String message = VerificationAlgorithmUtil
				.verifyDoubleBallSingleAutoParams(zhushuStr,beishu,addNumber);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/qilecai/SingleAutoSelection";
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
			Vector singleArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(7, 30));
			Collections.sort(singleArray);
			newzhuma += LotteryAlgorithmUtil
					.joinStringArrayWithComma(singleArray)
					+ "<br/>";
			// 在每注前面加上玩法和倍数代码
			zhuma += "00" + CommonUtil.getNewString("01")
			+ LotteryAlgorithmUtil.joinStringArray(singleArray) + "^";

		}
		zhuma = zhuma.substring(4);
		if (zhuma.endsWith("^"))
			zhuma = zhuma.substring(0, zhuma.length() - 1);
		int amount = zhushu * Integer.parseInt(multNo)
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request.setAttribute(
							"err_msg",
							MessageUtil.QLCA_selfSelectionMultipleBetDetail_AmountError);
			return "wap/qilecai/SingleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", Integer.parseInt(multNo) * zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("zhushuStr", zhushuStr);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (Integer.parseInt(multNo) * zhushu) + " beishu:" + beishu
				+ " addNumber:" + addNumber + " amount:" + amount
				+ " newzhuma:" + newzhuma + " zhuma:" + zhuma + " type:" + type
				+ " term" + term);
		// 转到确认投注页面
		return "wap/qilecai/BetDetail";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="/singleAuto.jspx",method=RequestMethod.POST)
	public String singleAuto(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String zhushuStr = "1";
		String beishu = "1";
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null) {
				addNumber = "1";
			}
			logger
					.info(" addNumber:"
							+ addNumber);
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		logger.info(" zhushuStr:" + zhushuStr
				+ " beishu:" + beishu + " type:" + type);
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		String term = CommonUtil.getTerm("F47102");
		String zhuma = "";
		String message = VerificationAlgorithmUtil
				.verifyDoubleBallSingleAutoParams(zhushuStr,beishu,addNumber);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("zhushuStr", zhushuStr);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/qilecai/SingleAutoSelection";
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
			Vector singleArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(7, 30));
			Collections.sort(singleArray);
			newzhuma += LotteryAlgorithmUtil
					.joinStringArrayWithComma(singleArray)
					+ "<br/>";
			// 在每注前面加上玩法和倍数代码
			zhuma += "00" + CommonUtil.getNewString(multNo)
					+ LotteryAlgorithmUtil.joinStringArray(singleArray) + "^";
		}
		zhuma = zhuma.substring(4);
		if (zhuma.endsWith("^"))
			zhuma = zhuma.substring(0, zhuma.length() - 1);
		int amount = zhushu * Integer.parseInt(multNo)
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request
					.setAttribute(
							"err_msg",
							MessageUtil.QLCA_selfSelectionMultipleBetDetail_AmountError);
			return "wap/qilecai/SingleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", Integer.parseInt(multNo) * zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("zhushuStr", zhushuStr);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (Integer.parseInt(multNo) * zhushu) + " beishu:" + beishu
				+ " addNumber:" + addNumber + " amount:" + amount
				+ " newzhuma:" + newzhuma + " zhuma:" + zhuma + " type:" + type
				+ " term" + term);
		// 转到确认投注页面
		return "wap/qilecai/BetDetail";
	}

	/**
	 * 七乐彩单式机选修改投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/singleAutoSelectionUpdate.jspx",method=RequestMethod.POST)
	public String singleAutoSelectionUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		// 从jsp中获取机选信息进行修改
		String didan = request.getParameter("didan");
		String beishu = request.getParameter("beishu");
		String zhushu = request.getParameter("zhushu");
		String amount = request.getParameter("amount");
		String zhuma = request.getParameter("zhuma");
		String nzm = request.getParameter("nzm");
		String newzhuma = request.getParameter("newzhuma");
		String type = request.getParameter("type");
		String term = CommonUtil.getTerm("F47102");
		String zhushuStr = request.getParameter("zhushuStr");
		String addNumber = request.getParameter("addNumber");
		logger.info("注码zhuma:" + zhuma + "转化的n注码 ：" + nzm + "新注码：" + newzhuma
				+ "倍数：" + beishu + "追期：" + addNumber + "金额：" + amount);
		// 把计算出来的结果设置到request
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("didan", didan);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("yuanhangshu", zhushuStr);
		request.setAttribute("term", term);
		request.setAttribute("nzm", nzm);
		request.setAttribute("zhushuStr", zhushuStr);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		logger.info(" beishu:" + beishu
				+ " addNumber:" + addNumber + " amount:" + amount
				+ " newzhuma:" + newzhuma + " zhuma:" + zhuma + " type:" + type
				+ " term" + term);
		// 转到确认投注页面
		return "wap/qilecai/QilecaiUpdate";
	}

	/**
	 * 七乐彩单式机选修改投注后走详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/singleAutoSelectionUpdateBet.jspx",method=RequestMethod.POST)
	public String singleAutoSelectionUpdateBet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 对修改的注码进行格式验证
		String didan = request.getParameter("didan");
		String yuanhangshu = request.getParameter("yuanhangshu");
		String term = CommonUtil.getTerm("F47102");
		String zmlenStr = request.getParameter("zmLen");
		int zmlen = Integer.parseInt(zmlenStr);
		String yuanbeishu = request.getParameter("yuanbeishu");
		String amt = request.getParameter("amount");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String trem = "";
		term = request.getParameter("term");
		String addNumber = request.getParameter("addNumber");
		// 定义接收每行的注码
		String[] n = new String[zmlen];
		int row = 0;
		for (int j = 0; j < zmlen; j++) {
			if (!"".equals(request.getParameter("n" + j).trim())) {
				n[row] = request.getParameter("n" + j).trim();
				String[] nrow = n[row].split(",");
				int len = nrow.length;
				Vector v = new Vector();
				for (int i = 0; i < len; i++) {
					v.add(nrow[i]);
				}
				Collections.sort(v);
				Iterator it = v.iterator();
				String srow = "";
				while (it.hasNext()) {
					srow += it.next() + ",";
				}
				n[row] = srow.substring(0, srow.length() - 1);
				logger.info("注码：" + n[row]);
				row = row + 1;// 此时row便是新注码的注数
				logger.info("注数：" + row);
			}
		}
		
		// 将注码数组组成带分号的字符串
		String nzm = "";
		String zhuma = "";
		for (int i = 0; i < row; i++) {
			nzm = nzm + n[i] + ";";
		}
		String s1 = nzm.replace(",", "");
		String[] s2 = s1.split(";");
		for (int i = 0; i < s2.length; i++) {
			if (i > 0) {
				s2[i] = "^0001" + s2[i];
			}
			zhuma = zhuma + s2[i];
		}
		logger.info("nzm格式：" + nzm + "zhuma格式：" + zhuma);
		String newzhuma = "";
		for (int i = 0; i < row; i++) {
			newzhuma = newzhuma + n[i] + "<br/>";
		}
		logger.info("newzhuma格式：" + newzhuma);

		if (row == 0) {
			request.setAttribute("certErr", "提示：请至少选择一注<br/>");
			request.setAttribute("zhushu", 1);
			request.setAttribute("nzm", nzm);
			request.setAttribute("didan", didan);
			request.setAttribute("type", type);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("amount", 1);
			request.setAttribute("beishu", beishu);
			request.setAttribute("term", term);
			request.setAttribute("zhushuStr", 0);
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/QilecaiUpdate";
		}

		// 对倍数进行验证
		if (!beishu.matches("[1-9][0-9]{0,1}")) {
			request.setAttribute("certErr", "提示：倍数不合法<br/>");
			request.setAttribute("zhushuStr", yuanhangshu);
			request.setAttribute("zhushu", zmlenStr);
			request.setAttribute("nzm", nzm);
			request.setAttribute("didan", didan);
			request.setAttribute("type", type);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("amount", 1);
			request.setAttribute("beishu", beishu);
			request.setAttribute("term", term);
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/QilecaiUpdate";
		}
		if (Integer.parseInt(beishu)>50) {
			request.setAttribute("certErr", "提示：倍数不合法<br/>");
			request.setAttribute("zhushuStr", yuanhangshu);
			request.setAttribute("zhushu", zmlenStr);
			request.setAttribute("nzm", nzm);
			request.setAttribute("didan", didan);
			request.setAttribute("type", type);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("amount", 1);
			request.setAttribute("beishu", beishu);
			request.setAttribute("term", term);
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/QilecaiUpdate";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		// 计算金额
		int amount = row * Integer.parseInt(beishu) * LotteryAlgorithmUtil.priceOfCaipiao;
		if(amount > 100000){
			request.setAttribute("certErr", "提示：单次投注金额不能超过十万元,您本次金额为" + amount + "元！<br/>");
			request.setAttribute("zhushuStr", yuanhangshu);
			request.setAttribute("nzm", nzm);
			request.setAttribute("type", type);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("amount", amount);
			request.setAttribute("beishu", beishu);
			request.setAttribute("didan", didan);
			request.setAttribute("term", term);
			for (int ii = 0; ii < row; ii++) {
				request.setAttribute("n" + ii, n[ii]);
			}
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/QilecaiUpdate";
		}
		logger.info("总金额：" + amount);
		// 验证每行注码的格式
		for (int i = 0; i < row; i++) {
			if (n[i].length() != 0
					&& !n[i]
							.matches("(([0-2][1-9],)|(30,)|(20,)|(10,)){6}([0-2][1-9]|30|10|20)")) {
				int m = i + 1;
				request.setAttribute("certErr", "提示：第" + m + "行注码格式不正确<br/>");
				request.setAttribute("zhushuStr", yuanhangshu);
				request.setAttribute("nzm", nzm);
				request.setAttribute("type", type);
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("amount", amount);
				request.setAttribute("beishu", beishu);
				request.setAttribute("didan", didan);
				request.setAttribute("term", term);
				for (int ii = 0; ii < row; ii++) {
					request.setAttribute("n" + ii, n[ii]);
				}
				request.setAttribute("addNumber", addNumber);
				return "wap/qilecai/QilecaiUpdate";
			}
		}
		// 验证注码是否有重复注码
		String[] s1Array = s1.split(";");
		logger.info("验证注码是否有重复注码" + s1Array);
		Map map = new HashMap();
		for (int i = 0; i < s1Array.length; i++) {
			logger.info("每行注码验证时的格式：" + s1Array[i]);
			map.put("s1Array", s1Array[i]);
			Vector vector = LotteryAlgorithmUtil
					.getStringArrayFromString(((String) map.get("s1Array"))
							.trim());
			if (!VerificationAlgorithmUtil.verifyRepeat(vector)) {
				request.setAttribute("certErr", "提示：第" + (i + 1)
						+ "行注码不能重复!<br/>");
				request.setAttribute("zhushuStr", yuanhangshu);
				request.setAttribute("nzm", nzm);
				request.setAttribute("didan", didan);
				request.setAttribute("type", type);
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("amount", 1);
				request.setAttribute("beishu", beishu);
				request.setAttribute("term", term);
				request.setAttribute("addNumber", addNumber);
				return "wap/qilecai/QilecaiUpdate";
			}
		}
		// 对追期进行验证
		if (!addNumber.matches("[1-9][0-9]{0,1}")) {
			request.setAttribute("zhushuStr", yuanhangshu);
			request.setAttribute("certErr", "提示：追期不合法<br/>");
			request.setAttribute("nzm", nzm);
			request.setAttribute("type", type);
			request.setAttribute("didan", didan);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("amount", 1);
			request.setAttribute("beishu", beishu);
			request.setAttribute("term", term);
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/QilecaiUpdate";
		}
		
		String zhushu = "" + row;
		int zhushuInt = Integer.parseInt(zhushu);
		if (beishu.matches("[1-9][0-9]{0,1}")) {
			zhushu = Integer.parseInt(zhushu) * Integer.parseInt(beishu) + "";
		} else {
			zhushu = Integer.parseInt(zhushu) * 1 + "";
		}
		logger.info("新注码的总注数：" + row + "接收的新注码：" + n);
		
		addNumber = CommonUtil.QJToBJChange(addNumber);
		// 把计算出来的结果设置到request
		request.setAttribute("amount", amount);
		request.setAttribute("didan", didan);
		request.setAttribute("type", type);
		request.setAttribute("nzm", nzm);
		request.setAttribute("term", term);
		request.setAttribute("beishu", beishu);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("zhushuStr", yuanhangshu);
		request.setAttribute("newzhuma", newzhuma);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		logger.info(" addNumber:" + addNumber
				+ " type:" + type + " term:" + term);
		// 转到确认投注页面(投注详细界面)
		return "wap/qilecai/BetDetail";
	}

	/**
	 * 七乐彩复式机选投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/multipleAutoSelection.jspx",method=RequestMethod.POST)
	public String multipleAutoSelection(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
//		String zhushuStr = request.getParameter("zhushu");

		String beishu = request.getParameter("beishu");
		String geshuStr = request.getParameter("num");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
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
		String parameter = "num="+geshuStr+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" beishu:" + beishu + " type:" + type + " geshuStr:"
				+ geshuStr);
		if (VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		String term = CommonUtil.getTerm("F47102");
		String message = VerificationAlgorithmUtil
				.verifyDoubleBallSingleAutoParams("1",beishu,addNumber);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("message", message);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/qilecai/MultipleAutoSelection";
		}
		// 计算注码,注数,金额,倍数
		String newzhuma = "";
		int geshu = Integer.parseInt(geshuStr);// 得到复式号码个数
		String zhuma = "";
			Vector singleArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(geshu, 30));
			Collections.sort(singleArray);
			newzhuma = LotteryAlgorithmUtil
					.joinStringArrayWithComma(singleArray)
					+ "<br/>";
			zhuma = "1001*"+LotteryAlgorithmUtil.joinStringArray(singleArray) + "^";
		int multNo = 0;
		if (beishu.trim().equals("")) {
			multNo = 1;
		} else {
			multNo = Integer.parseInt(beishu);
		}
		int zhushu = LotteryAlgorithmUtil.getQilecaiNumber(geshu);
		int amount = zhushu * multNo * LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request
					.setAttribute(
							"message",
							MessageUtil.QLCA_selfSelectionMultipleBetDetail_AmountError);
			return "wap/qilecai/MultipleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", multNo * zhushu);
		request.setAttribute("zhuFirst", "1");
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("geshu", geshuStr);
		request.setAttribute("term", term);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		logger.info(" zhushu:"
				+ (multNo * zhushu) + " geshu:"
				+ geshuStr + " beishu:" + beishu + " addNumber:" + addNumber
				+ " amount:" + amount + " newzhuma:" + newzhuma + " zhuma:"
				+ zhuma + " type:" + type + " term" + term);
		// 转到确认投注页面
		return "wap/qilecai/BetDetail";
	}

	/**
	 * 七乐彩复式机选修改投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/multipleAutoSelectionUpdate.jspx",method=RequestMethod.POST)
	public String multipleAutoSelectionUpdate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String zhuFirst = request.getParameter("zhuFirst");
		String zhuma = request.getParameter("zhuma");
		String hangshu = request.getParameter("zhuFirst");
		String amount = request.getParameter("amount");
		String zhushuStr = request.getParameter("zongzhushu");
		String geshuStr = request.getParameter("geshuStr");
		String beishu = request.getParameter("beishu");
		String nzm = request.getParameter("nzm");
		String type = request.getParameter("type");
		String addNumber = request.getParameter("addNumber");
		// 获取当前期号
		String term = CommonUtil.getTerm("F47102");
		logger.info("zhushuStr:" + zhushuStr + "beishu:" + beishu + "geshuStr:"
				+ geshuStr + "type:" + type + "addNumber:" + addNumber + "行数:"
				+ hangshu);
		Map map = new HashMap();
		map.put("zhushu", zhushuStr);
		map.put("beishu", beishu);
		String newzhuma = zhuma.replace(",", "<br/>");
		// 把计算出来的结果设置到request
		request.setAttribute("amount", amount);
		request.setAttribute("hangshu", hangshu);
		request.setAttribute("zhuFirst", zhuFirst);
		request.setAttribute("zhushu", zhushuStr);
		request.setAttribute("beishu", beishu);
		request.setAttribute("geshuStr", geshuStr);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("nzm", nzm);
		request.setAttribute("geshu", hangshu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		logger.info(" 总注数:" + zhushuStr
				+ " geshuStr:" + geshuStr + " beishu:" + beishu + " addNumber:"
				+ addNumber + " newzhuma:" + newzhuma + " zhuma:" + zhuma
				+ " type:" + type + " term" + term + "行数：" + hangshu);
		// 转到确认投注页面
		return "wap/qilecai/MultipleQilecaiUpdate";
	}

	/**
	 * 七乐彩复式机选修改投注后走详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/multipleAutoSelectionUpdateBet.jspx",method=RequestMethod.POST)
	public String multipleAutoSelectionUpdateBet(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取当前期号
		String term = CommonUtil.getTerm("F47102");
		String zhuFirst = request.getParameter("zhuFirst");
		String zhushu = request.getParameter("zhushu");
		String hangshu = request.getParameter("hangshu");
		int hangshuInt = Integer.parseInt(hangshu);
		String zhushuStr = request.getParameter("zongzhushu");
		String geshuStr = request.getParameter("geshuStr");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String addNumber = request.getParameter("addNumber");
		if(beishu.matches("[1-9][0-9]{0,1}")){
			zhushu = "" + Integer.parseInt(zhushuStr) * Integer.parseInt(beishu);
		}
		logger.info("zhushuStr:" + zhushuStr + "beishu:" + beishu + "geshuStr:"
				+ geshuStr + "type:" + type + "addNumber:" + addNumber + "行数:"
				+ hangshu + "注数zhushu：" + zhushu + "金额："
				+ request.getParameter("amount") + "n注码："
				+ request.getParameter("nzm") + "注码zhuma："
				+ request.getParameter("zhuma"));
		// 定义接收每行的注码
		String[] n = new String[hangshuInt];
		int row = 0;
		for (int j = 0; j < hangshuInt; j++) {
			if (!"".equals(request.getParameter("n" + j).trim())) {
				n[row] = request.getParameter("n" + j).trim();
				String[] nrow = n[row].split(",");
				int len = nrow.length;
				Vector v = new Vector();
				for (int i = 0; i < len; i++) {
					v.add(nrow[i]);
				}
				Collections.sort(v);
				Iterator it = v.iterator();
				String srow = "";
				while (it.hasNext()) {
					srow += it.next() + ",";
				}
				n[row] = srow.substring(0, srow.length() - 1);
				logger.info("注码：" + n[row]);
				row = row + 1;// 此时row便是新注码的行数
				logger.info("注数：" + row);
			}
		}
		hangshu = "" + row;
		logger.info("新注码的总行数：" + row + "接收的新注码：" + n);
		// 将注码数组组成带分号的字符串
		String nzm = "";
		String zhuma = "";
		for (int i = 0; i < row; i++) {
			nzm = nzm + n[i] + ";";
		}
		String s1 = nzm.replace(",", "");
		String[] s2 = s1.split(";");
		for (int i = 0; i < s2.length; i++) {
			if (i > 0) {
				s2[i] = "^1001*" + s2[i];
			}
			zhuma = zhuma + s2[i];
		}
		logger.info("nzm格式：" + nzm + "zhuma格式：" + zhuma);
		String newzhuma = "";
		for (int i = 0; i < row; i++) {
			newzhuma = newzhuma + n[i] + "<br/>";
		}
		logger.info("newzhuma格式：" + newzhuma);

		if (row == 0) {
			request.setAttribute("certErr", "提示：请至少选择一注<br/>");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("nzm", nzm);
			request.setAttribute("zhuFirst", zhuFirst);
			request.setAttribute("type", type);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("hangshu", "1");
			request.setAttribute("amount", 1);
			request.setAttribute("beishu", beishu);
			request.setAttribute("term", term);
			request.setAttribute("geshu", geshuStr);
			request.setAttribute("geshuStr", geshuStr);
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/MultipleQilecaiUpdate";
		}

		// 对倍数进行验证
		if (!beishu.matches("[1-9][0-9]{0,1}")) {
			request.setAttribute("certErr", "提示：倍数不合法<br/>");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("zhuFirst", zhuFirst);
			request.setAttribute("nzm", nzm);
			request.setAttribute("type", type);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("hangshu", hangshu);
			request.setAttribute("geshu", geshuStr);
			request.setAttribute("amount", 1);
			request.setAttribute("beishu", beishu);
			request.setAttribute("term", term);
			request.setAttribute("geshuStr", geshuStr);
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/MultipleQilecaiUpdate";
		}
		if (Integer.parseInt(beishu)>50) {
			request.setAttribute("certErr", "提示：倍数不合法<br/>");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("zhuFirst", zhuFirst);
			request.setAttribute("nzm", nzm);
			request.setAttribute("type", type);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("hangshu", hangshu);
			request.setAttribute("geshu", geshuStr);
			request.setAttribute("amount", 1);
			request.setAttribute("beishu", beishu);
			request.setAttribute("term", term);
			request.setAttribute("geshuStr", geshuStr);
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/MultipleQilecaiUpdate";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		// 计算金额
		int amount = 0;
		
		logger.info("总金额：" + amount);
		// 验证每行注码的格式
		for (int i = 0; i < row; i++) {
			if (n[i].length() != 0
					&& !n[i].matches("(([0-2][1-9],)|(30,)|(20,)|(10,)){7,15}(([0-2][1-9]|30|10|20))")) {
				int m = i + 1;
				request.setAttribute("certErr", "提示：第" + m + "行注码格式不正确<br/>");
				request.setAttribute("zhushu", zhushu);
				request.setAttribute("nzm", nzm);
				request.setAttribute("type", type);
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("amount", amount);
				request.setAttribute("hangshu", hangshu);
				request.setAttribute("geshuStr", geshuStr);
				request.setAttribute("geshu", geshuStr);
				request.setAttribute("beishu", beishu);
				request.setAttribute("zhuFirst", zhuFirst);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("term", term);
				for (int ii = 0; ii < row; ii++) {
					request.setAttribute("n" + ii, n[ii]);
				}
				request.setAttribute("addNumber", addNumber);
				return "wap/qilecai/MultipleQilecaiUpdate";
			}
			if (n[i].length() != Integer.parseInt(geshuStr) * 3 - 1) {
				int m = i + 1;
				request.setAttribute("certErr", "提示：第" + m + "行注码格式不正确<br/>");
				request.setAttribute("zhushu", zhushu);
				request.setAttribute("nzm", nzm);
				request.setAttribute("type", type);
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("amount", amount);
				request.setAttribute("hangshu", hangshu);
				request.setAttribute("geshuStr", geshuStr);
				request.setAttribute("geshu", geshuStr);
				request.setAttribute("beishu", beishu);
				request.setAttribute("zhuFirst", zhuFirst);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("term", term);
				for (int ii = 0; ii < row; ii++) {
					request.setAttribute("n" + ii, n[ii]);
				}
				request.setAttribute("addNumber", addNumber);
				return "wap/qilecai/MultipleQilecaiUpdate";
			}
		}
		// 验证注码是否有重复注码
		String[] s1Array = s1.split(";");
		logger.info("验证注码是否有重复注码" + s1Array);
		Map map = new HashMap();
		for (int i = 0; i < s1Array.length; i++) {
			logger.info("每行注码验证时的格式：" + s1Array[i]);
			map.put("s1Array", s1Array[i]);
			Vector vector = LotteryAlgorithmUtil
					.getStringArrayFromString(((String) map.get("s1Array"))
							.trim());
			if (!VerificationAlgorithmUtil.verifyRepeat(vector)) {
				request.setAttribute("certErr", "提示：第" + (i + 1)
						+ "行注码不能重复!<br/>");
				request.setAttribute("zhushu", zhushu);
				request.setAttribute("nzm", nzm);
				request.setAttribute("type", type);
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("amount", amount);
				request.setAttribute("hangshu", hangshu);
				request.setAttribute("geshu", geshuStr);
				request.setAttribute("beishu", beishu);
				request.setAttribute("zhuFirst", zhuFirst);
				request.setAttribute("term", term);
				request.setAttribute("geshuStr", geshuStr);
				request.setAttribute("addNumber", addNumber);
				return "wap/qilecai/MultipleQilecaiUpdate";
			}
		}
		// 对追期进行验证
		if (!addNumber.matches("[1-9][0-9]{0,1}")) {
			request.setAttribute("certErr", "提示：追期不合法<br/>");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("nzm", nzm);
			request.setAttribute("type", type);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("amount", amount);
			request.setAttribute("hangshu", hangshu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("geshu", geshuStr);
			request.setAttribute("zhuFirst", zhuFirst);
			request.setAttribute("term", term);
			request.setAttribute("geshuStr", geshuStr);
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/MultipleQilecaiUpdate";
		}
		addNumber = CommonUtil.QJToBJChange(addNumber);
		if(beishu.matches("[1-9][0-9]{0,1}")){
			zhushu = "" + LotteryAlgorithmUtil.getQilecaiNumber(Integer.parseInt(geshuStr)) * Integer.parseInt(hangshu) * Integer.parseInt(beishu);
		}
		if(beishu.matches("[1-9][0-9]{0,1}")){
			amount = Integer.parseInt(zhushu) * LotteryAlgorithmUtil.priceOfCaipiao;
		}
		if (amount > 100000) {
			request.setAttribute("certErr", "提示：单次投注金额不能超过十万元，您本次金额为" + amount + "元！<br/>");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("nzm", nzm);
			request.setAttribute("type", type);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("amount", amount);
			request.setAttribute("hangshu", hangshu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("geshu", geshuStr);
			request.setAttribute("zhuFirst", zhuFirst);
			request.setAttribute("term", term);
			request.setAttribute("geshuStr", geshuStr);
			request.setAttribute("addNumber", addNumber);
			return "wap/qilecai/MultipleQilecaiUpdate";
		}
		if(beishu.matches("[1-9][0-9]{0,1}")){
			amount = Integer.parseInt(zhushu) * LotteryAlgorithmUtil.priceOfCaipiao;;
		}
		// 把计算出来的结果设置到request
		request.setAttribute("amount", amount);
		request.setAttribute("type", type);
		request.setAttribute("nzm", nzm);
		request.setAttribute("term", term);
		request.setAttribute("beishu", beishu);
		request.setAttribute("geshuStr", geshuStr);
		request.setAttribute("geshu", geshuStr);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuFirst", hangshu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		logger.info(" 总注数:" + zhushuStr
				+ " geshuStr:" + geshuStr + " beishu:" + beishu + " addNumber:"
				+ addNumber + " newzhuma:" + newzhuma + " zhuma:" + zhuma
				+ " type:" + type + " term" + term + "行数：" + hangshu);
		// 转到确认投注页面
		return "wap/qilecai/BetDetail";
	}

	/**
	 * 七乐彩定胆机选投注明细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/dingDanAutoSelection.jspx",method=RequestMethod.POST)
	public String dingDanAutoSelection(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String didan = request.getParameter("didan");
		String zhushu = request.getParameter("zhushuStr");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
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
		String parameter = "didan="+didan+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber+"&zhushuStr="+zhushu;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" didan:" + didan
				+ " zhushu:" + zhushu + " beishu:" + beishu + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(didan)
				|| VerificationAlgorithmUtil.isStringFilter(zhushu)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhushu = CommonUtil.QJToBJChange(zhushu);
		beishu = CommonUtil.QJToBJChange(beishu);
		// 获取当前期号
		String term = CommonUtil.getTerm("F47102");
		// 验证注数和倍数
		Map map = new HashMap();
		map.put("danma", didan);
		map.put("zhushu", zhushu);
		map.put("beishu", beishu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String message = VerificationAlgorithmUtil.verifyQilecaiDingDan(map);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("zhuma", didan);
			request.setAttribute("zhushuStr", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("message", message);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/qilecai/DingDanAutoSelection";
		}
		// 计算注码,注数,金额,倍数
		String newzhuma = "";
		int zhuShu = Integer.parseInt(zhushu);
		String zhuma = "";
		String multNo = "";
		if (beishu.trim().equals("")) {
			multNo = "1";
		} else {
			multNo = beishu;
		}
		for (int i = 0; i < zhuShu; i++) {
			Vector singleArray = LotteryAlgorithmUtil
					.getStringArrayFromIntArray(LotteryAlgorithmUtil
							.getRandomIntArrayWithinRange(
									7 - didan.length() / 2, 30));
			Collections.sort(singleArray);
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(didan
					+ LotteryAlgorithmUtil.joinStringArray(singleArray));
			if (!VerificationAlgorithmUtil.verifyRepeat(vector)) {
				i--;
				continue;
			}
			Vector vector2 = LotteryAlgorithmUtil
					.getStringArrayFromString(didan);
			newzhuma += (LotteryAlgorithmUtil.joinStringArrayWithComma(vector2)
					+ ","
					+ LotteryAlgorithmUtil
							.joinStringArrayWithComma(singleArray) + "<br/>");
			zhuma += "00"
					+ CommonUtil.getNewString("01")
					+ CommonUtil
							.getSortString(didan
									+ LotteryAlgorithmUtil
											.joinStringArray(singleArray))
					+ "^";
		}
		logger.info(" zhuma:" + zhuma);
		if (zhuma.endsWith("^")) {
			zhuma = zhuma.substring(0, zhuma.length() - 1);
		}
		zhuma = zhuma.substring(4);
		int amount = zhuShu * Integer.parseInt(multNo)
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.verifyAmount(amount)) {
			request
					.setAttribute(
							"err_msg",
							MessageUtil.QLCA_selfSelectionMultipleBetDetail_AmountError);
			return "wap/qilecai/DingDanAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", zhuShu * Integer.parseInt(multNo));
		request.setAttribute("zhushuStr", zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("didan", didan);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (zhuShu * Integer.parseInt(multNo)) + " didan:" + didan
				+ " beishu:" + beishu + " addNumber:" + addNumber + " amount:"
				+ amount + " newzhuma:" + newzhuma + " zhuma:" + zhuma
				+ " type:" + type + " term" + term);
		// 转到确认投注页面
		return "wap/qilecai/BetDetail";

	}

	/**
	 *七乐彩胆拖自选投注
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

		String danma = request.getParameter("danma");
		String tuoma = request.getParameter("tuoma");
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
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
		String parameter = "danma="+danma+"&tuoma="+tuoma+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		logger.info(" danma:" + danma + " tuoma:"
				+ tuoma + " beishu:" + beishu + " type:" + type);
		if (VerificationAlgorithmUtil.isStringFilter(danma)
				|| VerificationAlgorithmUtil.isStringFilter(tuoma)
				|| VerificationAlgorithmUtil.isStringFilter(beishu)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		beishu = CommonUtil.QJToBJChange(beishu);
		String zhuma = "";
		// 获取当前期号
		String term = CommonUtil.getTerm("F47102");
		Map map = new HashMap();
		map.put("danma", danma);
		map.put("tuoma", tuoma);
		map.put("beishu", beishu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String message = VerificationAlgorithmUtil
				.verifyQilecaiDantuoSelfSelection(map);
		logger.info(" message:" + message);
		if (message != null) {
			request.setAttribute("danma", danma);
			request.setAttribute("tuoma", tuoma);
			request.setAttribute("beishu", beishu);
			request.setAttribute("message", message);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/qilecai/DantuoSelfSelection";
		}
		Vector danmaVector = LotteryAlgorithmUtil
				.getStringArrayFromString(danma);
		Collections.sort(danmaVector);
		Vector tuomaVector = LotteryAlgorithmUtil
				.getStringArrayFromString(tuoma);
		Collections.sort(tuomaVector);
		if (danmaVector.size() + tuomaVector.size() == 7) {
			type = "ZXDS";
			zhuma = CommonUtil.getSortString(danma + tuoma);
		} else {
			zhuma = CommonUtil.getSortString(danma) + "*"
					+ CommonUtil.getSortString(tuoma);
		}
		String newdanma = LotteryAlgorithmUtil
				.joinStringArrayWithComma(danmaVector);
		String newtuoma = LotteryAlgorithmUtil
				.joinStringArrayWithComma(tuomaVector);
		int zhushu = LotteryAlgorithmUtil.getQilecaiDantuoNumber(danmaVector
				.size(), tuomaVector.size());
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		int amount = zhushu * beishuInt * LotteryAlgorithmUtil.priceOfCaipiao;
		request.setAttribute("type", type);
		request.setAttribute("zhushu", zhushu * beishuInt);
		request.setAttribute("newdanma", newdanma);
		request.setAttribute("newtuoma", newtuoma);
		request.setAttribute("amount", amount);
		request.setAttribute("beishu", beishu);
		request.setAttribute("term", term);
		request.setAttribute("type", type);
		request.setAttribute("zhuma", zhuma);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info(" zhushu:"
				+ (zhushu * beishuInt) + " beishu:" + beishu + " addNumber:"
				+ addNumber + " amount:" + amount + " zhuma:" + zhuma
				+ " newdanma:" + newdanma + "newtuoma:" + newtuoma + " type:"
				+ type + " term" + term);
		return "wap/qilecai/DantuoBetDetail";
	}

	/**
	 *七乐彩投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/bet.jspa")
	public String bet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		
		String term = CommonUtil.getTerm("F47102");
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");

		String zhuma, amount, beishu, zhushu, type, addNumber = "", token, channel="",lotNo="";
		lotNo="F47102";
		channel = WapUtil.getChannelId(request);
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			// 获取输入页面的参数
			zhushu = request.getParameter("zhushu"); // 注数
			beishu = request.getParameter("beishu"); // 倍数
			zhuma = request.getParameter("zhuma"); // 注码
			amount = request.getParameter("amount"); // 金额
			type = request.getParameter("type");
			token = request.getParameter("token");// 判断是否重复提交
			if (rbint.getString("addNumberSwitch").equals("1")) {
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
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");
			String[] addNumbers = null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
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
			type = types == null || types[0].equals("") ? "0" : types[0];
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers == null || addNumbers[0].equals("") ? "0"
						: addNumbers[0];
			}
		}
		logger.info("userno:" + userno + " zhushu:" + zhushu
				+ " beishu:" + beishu + " zhuma" + zhuma + " addNumber:"
				+ addNumber + " amount:" + amount + " type:" + type);
		
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			String ttssBet = "";
			String wanfa = "";
			logger.info("userno:" + userno + " zhuma:" + zhuma);
			if (type.trim().equals("ZXDS") || type.trim().equals("JXDD")
					|| type.trim().equals("JXDS")) {

					zhuma = CommonUtil.generateQilecaiZhuma("F47102",
							CommonUtil.QLC_ZXDS, beishu, zhuma, zhushu);
					wanfa = "QLC_ZXDS";
					if (buyways.equals("hemai")) {
//						boolean flag = CommonUtil.getBalanceResult(userno, amount);
//						if(flag){
//							request.setAttribute("message","您的余额不足，请先充值！");
//							return "wap/charge/chargeIndex";
//						}
						CommonUtil.getRandom(request);
						request.setAttribute("zhushu", zhushu);
						request.setAttribute("beishu", beishu);
						request.setAttribute("amt", amount);
						request.setAttribute("newbetcode", zhuma);
						request.setAttribute("lotno", lotNo);
						return "wap/hemai/buyhemai";
					} else if (buyways.equals("presented")) {
//						boolean flag = CommonUtil.getBalanceResult(userno, amount);
//						if(flag){
//							request.setAttribute("message","您的余额不足，请先充值！");
//							return "wap/charge/chargeIndex";
//						}
						CommonUtil.getRandom(request);
						request.setAttribute("zhushu", zhushu);
						request.setAttribute("beishu", beishu);
						request.setAttribute("amt", amount);
						request.setAttribute("newbetcode", zhuma);
						request.setAttribute("lotno", lotNo);
						return "wap/zengcai/zengcai";
					} else {
						boolean flag = CommonUtil.getBalanceResult(userno, amount);
						if(flag){
							request.setAttribute("message","您的余额不足，请先充值！");
							return "wap/charge/chargeIndex";
						}
						ttssBet = JsonToJrtLotUtil.sendToBet(userno, lotNo, term, zhuma, beishu, "2", wanfa, amount, addNumber, channel);

					}
				request.setAttribute("err_msg", ttssBet);
				return "wap/BetSuccess";
			}
			if (type.trim().equals("ZXFS")) {
				zhuma = CommonUtil.generateQilecaiZhuma("F47102",
						CommonUtil.QLC_ZXFS, beishu, zhuma, zhushu);
				wanfa="QLC_ZXFS";
			}
			if (type.trim().equals("ZXDT")) {
				zhuma = CommonUtil.generateQilecaiZhuma("F47102",
						CommonUtil.QLC_ZXDT, beishu, zhuma, zhushu);
				wanfa="QLC_ZXDT";
			}
			logger.info("userno:" + userno + " zhuma" + zhuma);
			if (buyways.equals("hemai")) {
				request.setAttribute("zhushu", zhushu);
				request.setAttribute("beishu", beishu);
				request.setAttribute("amt", amount);
				request.setAttribute("newbetcode", zhuma);
				request.setAttribute("lotno", lotNo);
				return "wap/hemai/buyhemai";
			} else if (buyways.equals("presented")) {
				request.setAttribute("zhushu", zhushu);
				request.setAttribute("beishu", beishu);
				request.setAttribute("amt", amount);
				request.setAttribute("newbetcode", zhuma);
				request.setAttribute("lotno", lotNo);
				return "wap/zengcai/zengcai";
			} else {
				ttssBet=JsonToJrtLotUtil.sendToBet(userno, lotNo, term, zhuma, beishu, "2", wanfa, amount, addNumber, channel);

			}
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}
}
