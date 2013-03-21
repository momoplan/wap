package com.ruyicai.wap.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.bean.Account;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.WapUtil;

@Controller
@RequestMapping("/lottery")
public class LotteryController {
	private static final Logger logger = Logger.getLogger(LotteryController.class);
	@RequestMapping("/betDetail.jspa")
	public ModelAndView betDetail(
			@RequestParam(value = "lotNo", defaultValue = "") String lotNo,
			@RequestParam(value = "beiShu", defaultValue = "") String beiShu,
			@RequestParam(value = "betCode", defaultValue = "") String betCode,
			@RequestParam(value = "amount", defaultValue = "") String amount,
			@RequestParam(value = "oneAmount", defaultValue = "") String oneAmount,
			@RequestParam(value = "zhuShu", defaultValue = "") String zhuShu,
			@RequestParam(value = "addNumber", defaultValue = "") String addNumber,
			@RequestParam(value = "batchCode", defaultValue = "") String batchCode,
			@RequestParam(value = "token", defaultValue = "") String token,
			@RequestParam(value = "buyWays", defaultValue = "") String buyWays,// 投注类型// daigou// 普通投注// hemai// 合买投注// presented
			HttpServletRequest request) throws JSONException, ParseException{
		ModelAndView modelAndView = new ModelAndView();
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession().getAttribute("user");
		String userNo = tuserInfoBean.getUserno();
		if(batchCode==null||"".equals(batchCode)){
			batchCode = CommonUtil.getTerm(lotNo);
		}
		String channel = WapUtil.getChannelId(request);
		logger.info("userNo:" + userNo + " zhuShu:" + zhuShu + " beiShu:"
				+ beiShu + " betCode:" + betCode + " addNumber:" + addNumber
				+ " amount:" + amount + " batchCode:" + batchCode+ " oneAmount:" + oneAmount);
		
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if ("false".equals(isExecute)) {
			request.getSession().setAttribute(token, "true");
			Account account = SelectAllUtil.selectUserAccount(userNo);
			if (buyWays.equals("heMai")) {
				boolean flag = CommonUtil.getValidateBalanceResult(account, amount);
				if(flag){
					modelAndView.addObject("message", "您的余额不足，请先充值！");
					modelAndView.setViewName("wap/charge/chargeIndex");
					return modelAndView;
				}
				CommonUtil.getRandom(request);
				request.setAttribute("zhushu", zhuShu);
				request.setAttribute("beishu", beiShu);
				request.setAttribute("amt", amount);
				request.setAttribute("newbetcode", betCode);
				request.setAttribute("lotno", lotNo);
				modelAndView.setViewName("wap/hemai/buyhemai");
				return modelAndView;
			} else if (buyWays.equals("present")) {
				boolean flag = CommonUtil.getValidateBalanceResult(account, amount);
				if(flag){
					modelAndView.addObject("message", "您的余额不足，请先充值！");
					modelAndView.setViewName("wap/charge/chargeIndex");
					return modelAndView;
				}
				CommonUtil.getRandom(request);
				request.setAttribute("zhushu", zhuShu);
				request.setAttribute("beishu", beiShu);
				request.setAttribute("amt", amount);
				request.setAttribute("newbetcode", betCode);
				request.setAttribute("lotno", lotNo);
				modelAndView.setViewName("wap/zengcai/zengcai");
				return modelAndView;
			} else {
				boolean flag = CommonUtil.getValidateBalanceResult(account, amount);
				if(flag){
					modelAndView.addObject("message", "您的余额不足，请先充值！");
					modelAndView.setViewName("wap/charge/chargeIndex");
					return modelAndView;
				}
				String betResult = JsonToJrtLotUtil.sendToBet(userNo, lotNo,
						batchCode, betCode, beiShu, oneAmount, "", amount, addNumber,
						channel);
				account = SelectAllUtil.selectUserAccount(userNo);
				modelAndView.addObject("betResult", betResult);
				modelAndView.addObject("account", account);
			}
		} else {
			modelAndView.addObject("betResult", "请勿重复提交！");
		}
		modelAndView.setViewName("wap/betResult");
		return modelAndView;
	}
}
