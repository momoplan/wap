package com.ruyicai.wap.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;

@RequestMapping("/select5from22")
@Controller
public class Select5From22BetController {
	private static final Logger logger = Logger
			.getLogger(Select5From22BetController.class);

	/**
	 * 22选5机选
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/autoSelect.jspx")
	public String autoSelect(Model model) {
		getDeadlineAndWincode(model);
		model.addAttribute("zhushu", "1");
		model.addAttribute("beishu", "1");
		model.addAttribute("addNumber", "1");
		return "wap/22select5/22select5AutoSelect";
	}

	/**
	 * 22选5自选
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/selfSelect.jspx")
	public String selfSelect(Model model) {
		getDeadlineAndWincode(model);
		model.addAttribute("beishu", "1");
		model.addAttribute("addNumber", "1");
		return "wap/22select5/22select5SelfSelect";
	}

	/**
	 * 22选5胆拖
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/dantuoSelect.jspx")
	public String dantuoSelect(Model model) {
		getDeadlineAndWincode(model);
		model.addAttribute("beishu", "1");
		model.addAttribute("addNumber", "1");
		return "wap/22select5/22select5DantuoSelect";
	}

	/**
	 * 22选5机选详细
	 * 
	 * @param lotno
	 * @param beishu
	 * @param zhushu
	 * @param addNumber
	 * @param model
	 * @return
	 */
	@RequestMapping("/autoSelectDetail.jspx")
	public String autoSelectDetail(
			@RequestParam(value = "lotno", defaultValue = "T01013") String lotno,
			@RequestParam(value = "beishu", defaultValue = "") String beishu,
			@RequestParam(value = "zhushu", defaultValue = "") String zhushu,
			@RequestParam(value = "addNumber", defaultValue = "") String addNumber,
			Model model, HttpServletRequest request) {
		String parameter = "lotno="+lotno+"&beishu="+beishu+"&zhushu="+zhushu+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		// 验证参数
		logger.info("lotno:" + lotno + ",beishu:" + beishu + ",zhushu:"
				+ zhushu + ",addNumber:" + addNumber);
		String result = VerificationAlgorithmUtil.validate22Select5Aotu(beishu,
				zhushu, addNumber);
		getDeadlineAndWincode(model);
		if (!"".equals(result)) {
			logger.info("result:" + result);
			model.addAttribute("lotno", lotno);
			model.addAttribute("beishu", beishu);
			model.addAttribute("zhushu", zhushu);
			model.addAttribute("addNumber", addNumber);
			model.addAttribute("messageError", result);
			return "wap/22select5/22select5AutoSelect";
		}
		// 随机生成注码

		String betCode = "";// 投注注码
		String betCodeView = "";// 页面显示注码
		for (int i = 0; i < Integer.parseInt(zhushu); i++) {
			// 随机生成注码
			List<String> list = LotteryAlgorithmUtil
					.getRandomCodeArrayWithinRange(5, 22);
			// Collections.sort(list);
			// 将不带0的注码加0
			List<String> listZero = LotteryAlgorithmUtil
					.getStringArrayAddZero(list);
			Collections.sort(listZero);
			// 将带0的注码生成字符串
			String code = LotteryAlgorithmUtil.getStringFromArray2(listZero)
					+ "^";
			betCode += LotteryAlgorithmUtil.getBetCode(code, "DS");
			// 将带0的注码生成带,的字符串
			betCodeView += LotteryAlgorithmUtil.getStringFromArray(listZero)
					+ "<br/>";
		}

		// 计算金额
		int amount = Integer.parseInt(beishu) * Integer.parseInt(zhushu) * 2;
		logger.info("lotno:" + lotno + ",beishu:" + beishu + ",zhushu:"
				+ zhushu + ",addNumber:" + addNumber + ",amount:" + amount
				+ ",betCode:" + betCode + ",betCodeView:" + betCodeView);
		if (amount > 20000) {
			model.addAttribute("err_msg", "金额不得超过20000");
			return "wap/BetSuccess";
		}
		model.addAttribute("lotno", lotno);
		model.addAttribute("beishu", beishu);
		model.addAttribute("zhushu", zhushu);
		model.addAttribute("addNumber", addNumber);
		model.addAttribute("amount", amount);
		model.addAttribute("betCode", betCode);
		model.addAttribute("betCodeView", betCodeView);
		model.addAttribute("type", "DS");
		getRandom(request, model);
		return "wap/22select5/22select5BetDetail";
	}

	/**
	 * 22选5自选详细
	 * 
	 * @param lotno
	 * @param beishu
	 * @param zhuma
	 * @param addNumber
	 * @param model
	 * @return
	 */
	@RequestMapping("/selfSelectDetail.jspx")
	public String selfSelectDetail(
			@RequestParam(value = "lotno", defaultValue = "T01013") String lotno,
			@RequestParam(value = "beishu", defaultValue = "") String beishu,
			@RequestParam(value = "zhuma", defaultValue = "") String zhuma,
			@RequestParam(value = "addNumber", defaultValue = "") String addNumber,
			Model model, HttpServletRequest request) {
		// 验证参数
		String parameter = "lotno="+lotno+"&beishu="+beishu+"&zhuma="+zhuma+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("lotno:" + lotno + ",beishu:" + beishu + ",zhuma:" + zhuma
				+ ",addNumber:" + addNumber);
		String result = VerificationAlgorithmUtil.validate22Select5Self(beishu,
				zhuma, addNumber);
		getDeadlineAndWincode(model);
		if (!"".equals(result)) {
			model.addAttribute("lotno", lotno);
			model.addAttribute("beishu", beishu);
			model.addAttribute("zhuma", zhuma);
			model.addAttribute("addNumber", addNumber);
			model.addAttribute("messageError", result);
			return "wap/22select5/22select5SelfSelect";
		}
		String betCode = "";// 投注注码
		String betCodeView = "";// 页面显示注码
		// 将注码转换成list
		List<String> list = LotteryAlgorithmUtil.getArrayFromString(zhuma);
		Collections.sort(list);
		// 将注码加,
		betCodeView = LotteryAlgorithmUtil.getStringFromArray(list) + "<br/>";
		betCode = LotteryAlgorithmUtil.getStringFromArray2(list) + "^";
		// 计算注数
		int zhushu = LotteryAlgorithmUtil.get22select5Number(list.size());
		if (zhushu > 10000) {
			model.addAttribute("lotno", lotno);
			model.addAttribute("beishu", beishu);
			model.addAttribute("zhuma", zhuma);
			model.addAttribute("addNumber", addNumber);
			model.addAttribute("messageError", "注数不能大于10000");
			return "wap/22select5/22select5SelfSelect";
		}
		if (list.size() > 5) {
			betCode = LotteryAlgorithmUtil.getBetCode(betCode, "FS");
			model.addAttribute("type", "FS");
		} else {
			betCode = LotteryAlgorithmUtil.getBetCode(betCode, "DS");
			model.addAttribute("type", "DS");
		}
		// 计算金额
		int amount = Integer.parseInt(beishu) * zhushu * 2;
		logger.info("lotno:" + lotno + ",beishu:" + beishu + ",zhushu:"
				+ zhushu + ",addNumber:" + addNumber + ",amount:" + amount
				+ ",betCode:" + betCode + ",betCodeView:" + betCodeView);
		if (amount > 20000) {
			model.addAttribute("err_msg", "金额不得超过20000");
			return "wap/BetSuccess";
		}
		model.addAttribute("lotno", lotno);
		model.addAttribute("beishu", beishu);
		model.addAttribute("zhushu", zhushu);
		model.addAttribute("addNumber", addNumber);
		model.addAttribute("amount", amount);
		model.addAttribute("betCode", betCode);
		model.addAttribute("betCodeView", betCodeView);
		getRandom(request, model);
		return "wap/22select5/22select5BetDetail";
	}

	/**
	 * 22选5胆拖详细
	 * 
	 * @param lotno
	 * @param beishu
	 * @param danma
	 * @param tuoma
	 * @param addNumber
	 * @param model
	 * @return
	 */
	@RequestMapping("/dantuoSelectDetail.jspx")
	public String dantuoSelectDetail(
			@RequestParam(value = "lotno", defaultValue = "T01013") String lotno,
			@RequestParam(value = "beishu", defaultValue = "") String beishu,
			@RequestParam(value = "danma", defaultValue = "") String danma,
			@RequestParam(value = "tuoma", defaultValue = "") String tuoma,
			@RequestParam(value = "addNumber", defaultValue = "") String addNumber,
			Model model, HttpServletRequest request) {
		// 验证参数
		String parameter = "lotno="+lotno+"&beishu="+beishu+"&danma="+danma+"&addNumber="+addNumber+"&tuoma="+tuoma;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("lotno:" + lotno + ",beishu:" + beishu + ",danma:" + danma
				+ ",tuoma:" + tuoma + ",addNumber:" + addNumber);
		String result = VerificationAlgorithmUtil.validate22Select5DanTuo(
				beishu, danma, tuoma, addNumber);
		getDeadlineAndWincode(model);
		if (!"".equals(result)) {
			model.addAttribute("lotno", lotno);
			model.addAttribute("beishu", beishu);
			model.addAttribute("danma", danma);
			model.addAttribute("tuoma", tuoma);
			model.addAttribute("addNumber", addNumber);
			model.addAttribute("messageError", result);
			return "wap/22select5/22select5DantuoSelect";
		}
		String betCode = "";// 投注注码
		String betCodeView = "";// 页面显示注码
		// 将注码转换成list
		List<String> danmalist = LotteryAlgorithmUtil.getArrayFromString(danma);
		List<String> tuomalist = LotteryAlgorithmUtil.getArrayFromString(tuoma);
		Collections.sort(danmalist);
		Collections.sort(tuomalist);
		// 将注码加,
		String newdanma = LotteryAlgorithmUtil.getStringFromArray(danmalist);
		String newtuoma = LotteryAlgorithmUtil.getStringFromArray(tuomalist);
		betCodeView = "胆码：" + newdanma + "<br/>拖码：" + newtuoma + "<br/>";
		betCode = danma + "*" + tuoma + "^";
		// 计算注数
		int zhushu = LotteryAlgorithmUtil.get22select5DantuoNumber(
				danmalist.size(), tuomalist.size());
		if (zhushu > 10000) {
			model.addAttribute("lotno", lotno);
			model.addAttribute("beishu", beishu);
			model.addAttribute("danma", danma);
			model.addAttribute("tuoma", tuoma);
			model.addAttribute("addNumber", addNumber);
			model.addAttribute("errorMessage", "注数不能大于10000");
			return "wap/22select5/22select5DantuoSelect";
		}
		betCode = LotteryAlgorithmUtil.getBetCode(betCode, "DT");
		// 计算金额
		int amount = Integer.parseInt(beishu) * zhushu * 2;
		logger.info("lotno:" + lotno + ",beishu:" + beishu + ",zhushu:"
				+ zhushu + ",addNumber:" + addNumber + ",amount:" + amount
				+ ",betCode:" + betCode + ",betCodeView:" + betCodeView);
		if (amount > 20000) {
			model.addAttribute("err_msg", "金额不得超过20000");
			return "wap/BetSuccess";
		}
		model.addAttribute("lotno", lotno);
		model.addAttribute("beishu", beishu);
		model.addAttribute("zhushu", zhushu);
		model.addAttribute("addNumber", addNumber);
		model.addAttribute("amount", amount);
		model.addAttribute("betCode", betCode);
		model.addAttribute("betCodeView", betCodeView);
		model.addAttribute("type", "DT");
		getRandom(request, model);
		return "wap/22select5/22select5BetDetail";
	}

	/**
	 * 22选5确认投注
	 * 
	 * @param lotno
	 * @param beishu
	 * @param betCode
	 * @param amount
	 * @param zhushu
	 * @param addNumber
	 * @param model
	 * @param request
	 * @return
	 * @throws JSONException
	 * @throws ParseException
	 */
	@RequestMapping("/betDetail.jspa")
	public String betDetail(
			@RequestParam(value = "lotno", defaultValue = "T01013") String lotno,
			@RequestParam(value = "beishu", defaultValue = "") String beishu,
			@RequestParam(value = "betCode", defaultValue = "") String betCode,
			@RequestParam(value = "amount", defaultValue = "") String amount,
			@RequestParam(value = "zhushu", defaultValue = "") String zhushu,
			@RequestParam(value = "addNumber", defaultValue = "") String addNumber,
			@RequestParam(value = "token", defaultValue = "") String token,
			@RequestParam(value = "buyways", defaultValue = "") String buyways,// 投注类型// daigou// 普通投注// hemai// 合买投注// presented
			Model model, HttpServletRequest request) throws JSONException,
			ParseException {
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
				.getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("T01013");
		String channel = WapUtil.getChannelId(request);
		logger.info("userno:" + userno + " zhushu:" + zhushu + " beishu:"
				+ beishu + " betCode:" + betCode + " addNumber:" + addNumber
				+ " amount:" + amount + " term:" + term);
		
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if ("false".equals(isExecute)) {
			request.getSession().setAttribute(token, "true");

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
				request.setAttribute("newbetcode", betCode);
				request.setAttribute("lotno", "T01013");
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
				request.setAttribute("newbetcode", betCode);
				request.setAttribute("lotno", "T01013");
				return "wap/zengcai/zengcai";
			} else {
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				String ttssBet = JsonToJrtLotUtil.sendToBet(userno, "T01013",
						term, betCode, beishu, "2", "", amount, addNumber,
						channel);
				model.addAttribute("err_msg", ttssBet);

			}
		} else {
			model.addAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

	/**
	 * 
	 * @param model
	 */
	public void getDeadlineAndWincode(Model model) {
		String deadline = CommonUtil.getDeadline("T01013", 0);
		String term = CommonUtil.getTerm("T01013");
		List<String> list = CommonUtil.getBetCode("T01013", "5");
		model.addAttribute("deadline", deadline);
		model.addAttribute("list", list);
		model.addAttribute("term", term);
	}

	/**
	 * @param request
	 * @param model
	 */
	public void getRandom(HttpServletRequest request, Model model) {
		Random rdm = new Random();
		int transctionId = rdm.nextInt();
		model.addAttribute("token", transctionId + "");
		request.getSession().setAttribute(transctionId + "", "false");
	}
}
