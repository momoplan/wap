package com.ruyicai.wap.controller;

import static com.ruyicai.wap.Global.rbint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buybal.lot.lottype.Array5Util;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/array5")
@Controller
public class Array5Action{
	private static final Logger logger = Logger.getLogger(Array5Action.class);
 

	/**
	 * 排列五首页机选一注
	 */
	@RequestMapping(value="/oneBetDetail.jspx",method=RequestMethod.POST)
	public String oneBetDetail(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取当前期号
		String term = CommonUtil.getTerm("T01011");
		// 获取输入页面的参数
		String multNo = request.getParameter("beishu"); // 倍数
		String zhushuStr = request.getParameter("zhushu"); // 注数
		String addNumber = request.getParameter("addNumber");// 追期
		String zhuma = request.getParameter("zhuma");
		zhuma = zhuma + ";";
		String newzhuma = zhuma.replace(";", "<br/>");
		logger.info("注码：============" + zhuma);
		// 验证全角半角,如果是全角,转换为半角
		multNo = CommonUtil.QJToBJChange(multNo);
		zhushuStr = CommonUtil.QJToBJChange(zhushuStr);
		int amount = Integer.parseInt(multNo) * Integer.parseInt(zhushuStr)
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (!CommonUtil.TCverifyAmount(amount)) {

			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/BetSuccess";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", Integer.parseInt(zhushuStr));
		request.setAttribute("beishu", multNo);
		request.setAttribute("amount", amount + "");
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", "DSJX");
		request.setAttribute("term", term);
		request.setAttribute("autoMethod", "singleAutoBetDetail");
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		// 转到确认投注页面
		return "wap/array5/array5SingleDetail";
	}

	/**
	 * 排列五手选提交投注详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 确定投注页面
	 * @throws Exception
	 */
	@RequestMapping(value="/ByHandBetDetail.jspx",method=RequestMethod.POST)
	public String ByHandBetDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		// 接收界面传过来的信息
		String myria, thousand, hundred, ten, unit = "";
		String term = CommonUtil.getTerm("T01011");
		myria = request.getParameter("myria");// 万位
		thousand = request.getParameter("thousand");// 千位
		hundred = request.getParameter("hundred");// 百位
		ten = request.getParameter("ten");// 十位
		unit = request.getParameter("unit");// 个位
		String beishu = request.getParameter("beishu");// 倍数
		String addNumber = "";// 追号
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
		}
		String parameter = "myria="+myria+"&beishu="+beishu+"&thousand="+thousand+"&addNumber="+addNumber
				+"&hundred="+hundred+"&ten="+ten+"&unit="+unit;
		request.getSession().setAttribute("parameter", parameter);

		logger.info("万位：" + myria + "千位：" + thousand + "百位：" + hundred + "十位："
				+ ten + "个位：" + unit + "倍数：" + beishu + "追号：" + addNumber);
		// 判断注码是否为空
		if (!Array5Util.checkZhumaIsNull(myria)) {
			request.setAttribute("err_msg", "万位不能为空");
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("ten", ten);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/array5ByHand";
		}
		if (!Array5Util.checkZhumaIsNull(thousand)) {
			request.setAttribute("err_msg", "千位不能为空");
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("ten", ten);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/array5ByHand";
		}
		if (!Array5Util.checkZhumaIsNull(hundred)) {
			request.setAttribute("err_msg", "百位不能为空");
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("ten", ten);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/array5ByHand";
		}
		if (!Array5Util.checkZhumaIsNull(ten)) {
			request.setAttribute("err_msg", "十位不能为空");
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("ten", ten);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/array5ByHand";
		}
		if (!Array5Util.checkZhumaIsNull(unit)) {
			request.setAttribute("err_msg", "个位不能为空");
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("ten", ten);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/array5ByHand";
		}
		String zhuma = myria + "," + thousand + "," + hundred + "," + ten + ","
				+ unit;
		logger.info("注码zhuma：" + myria + "," + thousand + "," + hundred + ","
				+ ten + "," + unit);
		String[] code = zhuma.split(",");
		logger.info("注码数字code：" + code.length + code[0] + code[1] + code[2]
				+ code[3] + code[4]);

		// 验证注码格式 ，追期是否合法，倍数是否合法
		if (Array5Util.checkAll(code, beishu, addNumber) != "pass"
				|| !Array5Util.checkAll(code, beishu, addNumber)
						.equalsIgnoreCase("pass")) {
			request.setAttribute("err_msg", Array5Util.checkAll(code, beishu,
					addNumber));
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("ten", ten);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/array5ByHand";
		}
		myria = CommonUtil.QJToBJChange(myria);
		thousand = CommonUtil.QJToBJChange(thousand);
		hundred = CommonUtil.QJToBJChange(hundred);
		ten = CommonUtil.QJToBJChange(ten);
		unit = CommonUtil.QJToBJChange(unit);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		// 计算注数
		int zhushu = Array5Util.zhuShu(code);
		// 计算金额
		int amount = zhushu * LotteryAlgorithmUtil.priceOfCaipiao
				* Integer.parseInt(beishu);
		if (amount >= 20000) {
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("ten", ten);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "array5/array5ByHand";
		}
		String newzhuma = Array5Util.getZhumaSplit(zhuma, "ON");
		logger.info("注码：" + zhuma + "新注码：" + newzhuma + "倍数：" + beishu + "追号："
				+ addNumber + "注数：" + zhushu + "金额：" + amount);
		request.setAttribute("term", term);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhushu", zhushu + "");
		request.setAttribute("amount", amount + "");
		request.setAttribute("beishu", beishu);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}
		return "wap/array5/array5BetDetail";
	}

	// 手选投注确定
	@RequestMapping(value="/byHandBetConfirm.jspa")
	public String byHandBetConfirm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 登录
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String autoMethod = "", newzhuma = "", zhushu = "", beishu, certID, amount = "", betNo = "", addNumber = "", token, ttssBet = "", oneMoney = "", lotno = "", batchCode;
		String type = "";
		String lotNo="T01011";
		String term = CommonUtil.getTerm("T01011");
		String channel = WapUtil.getChannelId(request);

		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			betNo = request.getParameter("zhuma");
			newzhuma = Array5Util.getZhumaSplit(betNo, "ON");
			; // 投注注码
			beishu = request.getParameter("beishu"); // 倍数
			token = request.getParameter("token");// 判断是否重复提交
			amount = request.getParameter("amount");// 金额
			batchCode = request.getParameter("term");// 当前期号
			zhushu = request.getParameter("zhushu");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber"); // 追号
			}
		} else {// 获取request.getAttribute中的存储
			String[] beishus = request.getAttribute("beishu") == null ? null
					: (String[]) request.getAttribute("beishu");
			String[] betNos = request.getAttribute("zhuma") == null ? null
					: (String[]) request.getAttribute("zhuma");
			String[] zhumas = request.getAttribute("zhuma") == null ? null
					: (String[]) request.getAttribute("zhuma");
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");
			String[] amounts = request.getAttribute("amount") == null ? null
					: (String[]) request.getAttribute("amount");
			String[] batchCodes = request.getAttribute("term") == null ? null
					: (String[]) request.getAttribute("term");
			String[] addNumbers = null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber") == null ? null
						: (String[]) request.getAttribute("addNumber");
				addNumber = CommonUtil.QJToBJChange(addNumber);
			}
			// 获取输入页面的参数
			beishu = beishus == null || beishus[0].equals("") ? "0"
					: beishus[0]; // 倍数
			newzhuma = zhumas == null || zhumas[0].equals("") ? "0" : zhumas[0]
					.replaceAll(";", "<br />");// 不带","的注码
			betNo = betNos == null || betNos[0].equals("") ? "0" : betNos[0];
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];
			amount = amounts == null || amounts[0].equals("") ? "0"
					: amounts[0];
			batchCode = batchCodes == null || batchCodes[0].equals("") ? "0"
					: batchCodes[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers == null || addNumbers[0].equals("") ? "0"
						: addNumbers[0];
			}
		}
		logger.info("userno:" + userno + " zhuma:" + newzhuma
				+ " addNumber:" + addNumber + " beishu:" + beishu);
	
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");

			String wanfa = "A5_FS";
			// 调用投注接口
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
				request.setAttribute("newbetcode", betNo);
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
				request.setAttribute("newbetcode", betNo);
				request.setAttribute("lotno", lotNo);
				return "wap/zengcai/zengcai";
			} else {
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				ttssBet = JsonToJrtLotUtil.sendToBet(userno, lotNo, batchCode, betNo, beishu, "2", wanfa, amount, addNumber, channel);
			}

			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

	// 单机机选提交
	@RequestMapping(value="/singleAutoBetDetail.jspx",method=RequestMethod.POST)
	public String singleAutoBetDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		String term = CommonUtil.getTerm("T01011");
		// 获取输入页面的参数
		String multNo = request.getParameter("beishu"); // 倍数
		String zhushuStr = request.getParameter("zhushu"); // 注数
		String addNumber = request.getParameter("addNumber");
		String parameter = "zhushu="+zhushuStr+"&beishu="+multNo+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		// 验证全角半角,如果是全角,转换为半角
		multNo = CommonUtil.QJToBJChange(multNo);
		zhushuStr = CommonUtil.QJToBJChange(zhushuStr);

		// 验证输入参数合法性
		String ret = "类型错误";
		if (Array5Util.checkZhushu(zhushuStr)) {
			ret = null;
			if (Array5Util.checkBeishu(multNo)) {
				ret = null;
				if (rbint.getString("addNumberSwitch").equals("1")) {
					addNumber = request.getParameter("addNumber");
					if (addNumber == null || "0".equals(addNumber)) {
						addNumber = "1";
					}
					addNumber = CommonUtil.QJToBJChange(addNumber);
					logger.info(" addNumber"
							+ addNumber);
					if (Array5Util.checkAddNumber(addNumber)) {
						ret = null;
					} else {
						ret = "追期不合法";
					}
				}
			} else {
				ret = "倍数不合法";
			}

		} else {
			ret = "注数不合法";
		}

		logger.info(" ret" + ret);
		if (ret != null) {
			request.setAttribute("err_msg", ret);
			request.setAttribute("beishu", multNo);
			request.setAttribute("zhushu", zhushuStr);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/singleAutoSelection";
		} else {
			String beishu = "";
			if (multNo.trim().equals("")) {
				beishu = "1";
			} else {
				beishu = multNo;
			}
			// 计算注码,注数,金额,倍数
			String newzhuma = "";
			String zhuma = "";
			int zhushu = Integer.parseInt(zhushuStr);
			zhuma = Array5Util.autoZhumaSingle(zhushu);
			newzhuma = zhuma.replaceAll(";", "<br/>");

			int amount = zhushu * Integer.parseInt(beishu)
					* LotteryAlgorithmUtil.priceOfCaipiao;
			if (amount > 20000) {
				logger.info("单次投注金额不能超过两万元，amt" + amount);
				request.setAttribute("err_msg", MessageUtil.TC_AmountError);
				return "wap/array5/singleAutoSelection";
			}
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", multNo);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("type", "DSJX");
			request.setAttribute("term", term);
			request.setAttribute("autoMethod", "singleAutoBetDetail");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			// 转到确认投注页面
			return "wap/array5/array5SingleDetail";
		}
	}

	// 单式机选投注
	@RequestMapping(value="/bet.jspa")
	public String bet(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();

		String autoMethod = "", newzhuma = "", zhushu = "", beishu, certID, amount = "", zhuma = "", addNumber = "", token, ttssBet = "", oneMoney = "", lotno = "", batchCode;
		String lotNo="T01011";
		String term = CommonUtil.getTerm("T01011");
		String channel = WapUtil.getChannelId(request);
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			zhuma = request.getParameter("zhuma");
			newzhuma = zhuma.replaceAll(";", "<br/>"); // 投注注码
			beishu = request.getParameter("beishu"); // 倍数
			token = request.getParameter("token");// 判断是否重复提交
			amount = request.getParameter("amount");// 金额
			batchCode = request.getParameter("term");// 当前期号
			// newzhuma = request.getParameter("newzhuma");
			zhushu = request.getParameter("zhushu");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber"); // 追号
			}
		} else {// 获取request.getAttribute中的存储
			String[] beishus = request.getAttribute("beishu") == null ? null
					: (String[]) request.getAttribute("beishu");
			String[] betNos = request.getAttribute("zhuma") == null ? null
					: (String[]) request.getAttribute("zhuma");
			String[] zhumas = request.getAttribute("zhuma") == null ? null
					: (String[]) request.getAttribute("zhuma");
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");
			String[] amounts = request.getAttribute("amount") == null ? null
					: (String[]) request.getAttribute("amount");
			String[] batchCodes = request.getAttribute("term") == null ? null
					: (String[]) request.getAttribute("term");
			String[] types = request.getAttribute("type") == null ? null
					: (String[]) request.getAttribute("type");
		
			String[] addNumbers = null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber") == null ? null
						: (String[]) request.getAttribute("addNumber");
				addNumber = CommonUtil.QJToBJChange(addNumber);
			}
			// 获取输入页面的参数
			beishu = beishus == null || beishus[0].equals("") ? "0"
					: beishus[0]; // 倍数
			newzhuma = zhumas == null || zhumas[0].equals("") ? "0" : zhumas[0]
					.replaceAll(";", "<br/>");// 不带","的注码
			zhuma = betNos == null || betNos[0].equals("") ? "0" : betNos[0];
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];
			amount = amounts == null || amounts[0].equals("") ? "0"
					: amounts[0];
			batchCode = batchCodes == null || batchCodes[0].equals("") ? "0"
					: batchCodes[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers == null || addNumbers[0].equals("") ? "0"
						: addNumbers[0];
			}
		}
		logger.info("userno:" + userno + " zhuma:" + newzhuma
				+ " addNumber:" + addNumber + " beishu:" + beishu);
		
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");

			String wanfa = "A5_DS";
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
				request.setAttribute("newbetcode", zhuma);
				request.setAttribute("lotno", lotNo);
				return "wap/zengcai/zengcai";
			} else {
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				ttssBet = JsonToJrtLotUtil.sendToBet(userno, lotNo, batchCode, zhuma, beishu, "2", wanfa, amount, addNumber, channel);
			}
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

	// 单式机选修改
	@RequestMapping(value="/modifyBetDetail.jspx",method=RequestMethod.POST)
	public String modifyBetDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String zhushu = request.getParameter("zhushu");
		String amount = request.getParameter("amount");
		String type = request.getParameter("type");
		String term = request.getParameter("term");
		String beishu = request.getParameter("beishu");
		String autoMethod = request.getParameter("autoMethod");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
		}

		StringBuffer s1 = new StringBuffer();
		String s2 = "";
		String zhuma = "";
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
			request.setAttribute("amount", zhushuStr.length * 2 + "");
			request.setAttribute("beishu", beishu);
			request.setAttribute("zhushu", zhushuStr.length + "");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/modifyAutoSelection";
		}
		// 计算新注数
		int newzhushu = zhushuStr.length;
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
			return "wap/array5/modifyAutoSelection";
		}
		// 验证注码格式
		for (int i = 0; i < zhushuStr.length; i++) {
			if (Array5Util.checkXgNumber(zhushuStr[i]) == false) {
				request.setAttribute("err_msg", "注码格式错误");
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				if (rbint.getString("addNumberSwitch").equals("1")) {
					request.setAttribute("addNumber", addNumber);
				}
				return "wap/array5/modifyAutoSelection";
			}
		}
		if (amt > 20000) {
			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", "1");
			}
			return "wap/array5/modifyAutoSelection";
		}

		// 生成显示格式注码
		zhuma = zhuma.replace("<br/>", ";");
		zhuma = zhuma.substring(0, zhuma.length() - 1);
		newzhuma = Array5Util.getZhumaReplace(zhuma);

		request.setAttribute("zhuma", zhuma);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhushu", newzhushu);
		request.setAttribute("amount", newamount);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("autoMethod", autoMethod);
		return "wap/array5/array5SingleDetail";
	}

	// 复式机选
	@RequestMapping(value="/mulipleAutoBetdetail.jspx",method=RequestMethod.POST)
	public String mulipleAutoBetdetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取当前期号
		String term = CommonUtil.getTerm("T01011");
		// 获取输入页面的参数
		String beishu = request.getParameter("beishu"); // 倍数
		String type = request.getParameter("type"); // 类型
		String num1 = request.getParameter("num1"); // 第1位注数
		String num2 = request.getParameter("num2"); // 第2位注数
		String num3 = request.getParameter("num3"); // 第3位注数
		String num4 = request.getParameter("num4"); // 第4位注数
		String num5 = request.getParameter("num5"); // 第5位注数
		String addNumber = request.getParameter("addNumber");
		String parameter = "num1="+num1+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber
				+"&num2="+num2+"&num3="+num3+"&num4="+num4+"&num5="+num5;
		request.getSession().setAttribute("parameter", parameter);
		// 验证全角半角,如果是全角,转换为半角
		beishu = CommonUtil.QJToBJChange(beishu);

		// 验证输入参数合法性
		String ret = "类型错误";
		if (Array5Util.checkBeishu(beishu)) {
			ret = null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber");
				addNumber = CommonUtil.QJToBJChange(addNumber);
				logger.info(" addNumber"
						+ addNumber);
				if (Array5Util.checkAddNumber(addNumber)) {
					ret = null;
				} else {
					ret = "追期不合法";
				}
			}
		} else {
			ret = "倍数不合法";
		}
		logger.info("beishu:" + beishu
				+ " addNumber:" + addNumber + " ret" + ret);

		if (ret != null) {
			request.setAttribute("err_msg", ret);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/MulipleAutoSelection";
		} else {
			int amount = Integer.parseInt(num1) * Integer.parseInt(num2)
					* Integer.parseInt(num3) * Integer.parseInt(num4)
					* Integer.parseInt(num5) * Integer.parseInt(beishu)
					* LotteryAlgorithmUtil.priceOfCaipiao;
			if (!CommonUtil.TCverifyAmount(amount)) {

				logger.info("单次投注金额不能超过两万元，amt" + amount);
				request.setAttribute("err_msg", MessageUtil.TC_AmountError);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/array5/MulipleAutoSelection";
			}
			// 计算注码,注数,金额,倍数
			String newzhuma = "";
			String betNoStr = "";
			betNoStr = Array5Util.autoZhumaComplex(new int[] {
					Integer.parseInt(num1), Integer.parseInt(num2),
					Integer.parseInt(num3), Integer.parseInt(num4),
					Integer.parseInt(num5) });
			String zhushuStr = String.valueOf(Array5Util
					.zhuShuAutoComplex(betNoStr)); // 注数

			int zhushu = Integer.parseInt(zhushuStr);
			newzhuma = betNoStr.replaceAll(";", "<br />");

			logger.info(" beishu" + beishu
					+ " type:" + type + " zhushuStr:" + zhushuStr);
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushuStr);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("betNo", betNoStr);
			request.setAttribute("type", type);
			request.setAttribute("term", term);
			request.setAttribute("num1", num1);
			request.setAttribute("num2", num2);
			request.setAttribute("num3", num3);
			request.setAttribute("num4", num4);
			request.setAttribute("num5", num5);
			request.setAttribute("autoMethod", "mulipleAutoBetdetail");

			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info(" zhushu:" + zhushu
					* Integer.parseInt(beishu) + " beishu:" + beishu
					+ " addNumber:" + addNumber + " amount:" + amount
					+ " newzhuma:" + newzhuma + " bet_No:" + betNoStr
					+ " type:" + type + " term:" + term);

			// 转到确认投注页面
			return "wap/array5/MulipleBetDetail";
		}
	}

	// 机选复式修改方法
	@RequestMapping(value="/modifyMulipleDetail.jspx",method=RequestMethod.POST)
	public String modifyMulipleDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取页面跳转参数

		String zhushu = request.getParameter("zhushu");
		String amount = request.getParameter("amount");
		String type = request.getParameter("type");
		String term = request.getParameter("term");
		String beishu = request.getParameter("beishu");
		String autoMethod = request.getParameter("autoMethod");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
		}
		String zhuma = request.getParameter("zhuma");
		String[] s = zhuma.split(",");
		if (Array5Util.checkBeishu(beishu) == false) {
			request.setAttribute("err_msg", "倍数格式错误");
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/ModifyMulipleAuto";
		}
		if (Array5Util.checkAddNumber(addNumber) == false) {
			request.setAttribute("err_msg", "追期格式错误");
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/ModifyMulipleAuto";
		}
		for (int i = 0; i < s.length; i++) {
			if (!(Array5Util.checkDuplicate(s[i])
					&& (s[i].length() > 9 ? false : true)
					&& Array5Util.checkMulipleNumber(s[i]) && (s.length != 5 ? false
					: true))) {
				request.setAttribute("err_msg", "注码格式错误");
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				if (rbint.getString("addNumberSwitch").equals("1")) {
					request.setAttribute("addNumber", addNumber);
				}
				return "wap/array5/ModifyMulipleAuto";
			}
		}
		if (zhuma.endsWith(",")) {
			request.setAttribute("err_msg", "注码格式错误");
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/array5/ModifyMulipleAuto";
		}

		// 计算新注数
		int zhushuStr = Array5Util.zhuShuAutoComplex(zhuma);
		String newzhushu = zhushuStr + "";
		// 计算新金额
		int amt = zhushuStr * Integer.parseInt(beishu) * 2;
		String newamount = amt + "";
		if (!CommonUtil.TCverifyAmount(amt)) {

			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", "1");
			}
			return "wap/array5/ModifyMulipleAuto";
		}
		// 传递重新机选参数
		request.setAttribute("num1", s[0].length() + "");
		request.setAttribute("num2", s[1].length() + "");
		request.setAttribute("num3", s[2].length() + "");
		request.setAttribute("num4", s[3].length() + "");
		request.setAttribute("num5", s[4].length() + "");

		// 传递页面参数
		request.setAttribute("betNo", zhuma);
		request.setAttribute("newzhuma", zhuma);
		request.setAttribute("zhushu", newzhushu);
		request.setAttribute("amount", newamount);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("type", type);
		request.setAttribute("term", term);
		request.setAttribute("autoMethod", autoMethod);
		return "wap/array5/MulipleBetDetail";

	}

	// 确认投注方法
	@RequestMapping(value="/placeBet.jspa")
	public String placeBet(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();

		String lotNo="T01011";
		String term = CommonUtil.getTerm("T01011");
		String channel = WapUtil.getChannelId(request);

		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		String autoMethod = "", newzhuma = "", zhushu = "", beishu, certID, amount = "", betNo = "", addNumber = "", token, ttssBet = "", oneMoney = "", batchCode;
		String type = "";
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			betNo = request.getParameter("betNo");
			newzhuma = betNo.replaceAll(";", "<br />"); // 投注注码
			beishu = request.getParameter("beishu"); // 倍数
			token = request.getParameter("token");// 判断是否重复提交
			type = request.getParameter("type");// 类型
			amount = request.getParameter("amount");// 金额
			batchCode = request.getParameter("term");// 当前期号
			zhushu = request.getParameter("zhushu");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber"); // 追号
			}
		} else {// 获取request.getAttribute中的存储
			String[] beishus = request.getAttribute("beishu") == null ? null
					: (String[]) request.getAttribute("beishu");
			String[] betNos = request.getAttribute("betNo") == null ? null
					: (String[]) request.getAttribute("betNo");
			String[] zhumas = request.getAttribute("betNo") == null ? null
					: (String[]) request.getAttribute("betNo");
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");
			String[] amounts = request.getAttribute("amount") == null ? null
					: (String[]) request.getAttribute("amount");
			String[] batchCodes = request.getAttribute("term") == null ? null
					: (String[]) request.getAttribute("term");
			String[] types = request.getAttribute("type") == null ? null
					: (String[]) request.getAttribute("type");
			String[] addNumbers = null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber") == null ? null
						: (String[]) request.getAttribute("addNumber");
				addNumber = CommonUtil.QJToBJChange(addNumber);
			}
			// 获取输入页面的参数
			beishu = beishus == null || beishus[0].equals("") ? "0"
					: beishus[0]; // 倍数
			newzhuma = zhumas == null || zhumas[0].equals("") ? "0" : zhumas[0]
					.replaceAll(";", "<br />");// 不带","的注码
			betNo = betNos == null || betNos[0].equals("") ? "0" : betNos[0];
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];
			type = types == null || types[0].equals("") ? "0" : types[0];
			amount = amounts == null || amounts[0].equals("") ? "0"
					: amounts[0];
			batchCode = batchCodes == null || batchCodes[0].equals("") ? "0"
					: batchCodes[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers == null || addNumbers[0].equals("") ? "0"
						: addNumbers[0];
			}
		}
		logger.info("userno:" + userno + " zhuma:" + newzhuma
				+ " addNumber:" + addNumber + " beishu:" + beishu);
		
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");

			// 调用投注接口
			String wanfa = "A5_FS";
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
				request.setAttribute("newbetcode", betNo);
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
				request.setAttribute("newbetcode", betNo);
				request.setAttribute("lotno", lotNo);
				return "wap/zengcai/zengcai";
			} else {
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				ttssBet = JsonToJrtLotUtil.sendToBet(userno, lotNo, batchCode, betNo, beishu, "2", wanfa, amount, addNumber, channel);

			}
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}
}
