package com.ruyicai.wap.controller;

import static com.ruyicai.wap.Global.rbint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buybal.lot.lottype.QixingcaiUtil;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/sevenStar")
@Controller
public class SevenStarAutoAction{
	private static final Logger logger = Logger.getLogger(DaLeTouAction.class);
 

	// 定胆机选
	@RequestMapping(value="/DDSevenStar.jspx",method=RequestMethod.POST)
	public String DDSevenStar(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取参数
		String dm1 = request.getParameter("dm1");
		String dm2 = request.getParameter("dm2");
		String dm3 = request.getParameter("dm3");
		String dm4 = request.getParameter("dm4");
		String dm5 = request.getParameter("dm5");
		String dm6 = request.getParameter("dm6");
		String dm7 = request.getParameter("dm7");
		String zhushu = request.getParameter("zhushu");
		logger.info( "dm1:" + dm1 + "dm2:"
				+ dm2 + "dm3:" + dm3 + "dm4:" + dm4 + "dm5:" + dm5 + "dm6:"
				+ dm6 + "dm7:" + dm7 + "zhushu:" + zhushu);
		String parameter = "dm1="+dm1+"&zhushu="+zhushu
				+"&dm2="+dm2+"&dm3="+dm3+"&dm4="+dm4+"&dm5="+dm5+"&dm6="+dm6+"&dm7="+dm7;
		request.getSession().setAttribute("parameter", parameter);
		// 获取当前期号
		String term = CommonUtil.getTerm("T01009");
		// 验证全角半角,如果是全角,转换为半角
		dm1 = CommonUtil.QJToBJChange(dm1);
		dm2 = CommonUtil.QJToBJChange(dm2);
		dm3 = CommonUtil.QJToBJChange(dm3);
		dm4 = CommonUtil.QJToBJChange(dm4);
		dm5 = CommonUtil.QJToBJChange(dm5);
		dm6 = CommonUtil.QJToBJChange(dm6);
		dm7 = CommonUtil.QJToBJChange(dm7);
		zhushu = CommonUtil.QJToBJChange(zhushu);

		// 验证注数
		if ("".equals(zhushu) || zhushu == null) {
			request.setAttribute("err_msg", "注数不能为空");
			request.setAttribute("dm1", dm1);
			request.setAttribute("dm2", dm2);
			request.setAttribute("dm3", dm3);
			request.setAttribute("dm4", dm4);
			request.setAttribute("dm5", dm5);
			request.setAttribute("dm6", dm6);
			request.setAttribute("dm7", dm7);
			request.setAttribute("zhushu", zhushu);
			return "wap/sevenStar/ddSevenStar";
		}
		if (QixingcaiUtil.checkZhushu(zhushu) == false) {
			request.setAttribute("err_msg", "注数不合法");
			request.setAttribute("dm1", dm1);
			request.setAttribute("dm2", dm2);
			request.setAttribute("dm3", dm3);
			request.setAttribute("dm4", dm4);
			request.setAttribute("dm5", dm5);
			request.setAttribute("dm6", dm6);
			request.setAttribute("dm7", dm7);
			request.setAttribute("zhushu", zhushu);
			return "wap/sevenStar/ddSevenStar";
		}
		if (dm1.equals("-1") && dm2.equals("-1") && dm3.equals("-1")
				&& dm4.equals("-1") && dm5.equals("-1") && dm6.equals("-1")
				&& dm7.equals("-1")) {
			request.setAttribute("err_msg", "至少选择一个胆码");
			request.setAttribute("dm1", dm1);
			request.setAttribute("dm2", dm2);
			request.setAttribute("dm3", dm3);
			request.setAttribute("dm4", dm4);
			request.setAttribute("dm5", dm5);
			request.setAttribute("dm6", dm6);
			request.setAttribute("dm7", dm7);
			request.setAttribute("zhushu", zhushu);
			return "wap/sevenStar/ddSevenStar";
		}
		int[] select = { Integer.parseInt(dm1), Integer.parseInt(dm2),
				Integer.parseInt(dm3), Integer.parseInt(dm4),
				Integer.parseInt(dm5), Integer.parseInt(dm6),
				Integer.parseInt(dm7) };
		// 生成注码
		String zhuma = QixingcaiUtil.autoZhumaDan(select, Integer
				.parseInt(zhushu));
		// 生成注码格式
		String newzhuma = QixingcaiUtil.getZhumaReplace(zhuma);
		long zhushuInt = QixingcaiUtil.zhuShuAutoDan(zhuma);

		if (zhushuInt != Integer.parseInt(zhushu)) {
			request.setAttribute("err_msg", "系统正在维护 ，请稍后再试");
			request.setAttribute("zhushu", zhushu);
			return "wap/sevenStar/ddSevenStar";
		}
		// 计算金额
		long amt = Integer.parseInt(zhushu) * 2;
		String amount = amt + "";
		if(amt>20000){
			request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为" + amount + "元");
			request.setAttribute("dm1", dm1);
			request.setAttribute("dm2", dm2);
			request.setAttribute("dm3", dm3);
			request.setAttribute("dm4", dm4);
			request.setAttribute("dm5", dm5);
			request.setAttribute("dm6", dm6);
			request.setAttribute("dm7", dm7);
			request.setAttribute("zhushu", zhushu);
			return "wap/sevenStar/ddSevenStar";
		}
		// 传递重新机选参数
		request.setAttribute("dm1", dm1);
		request.setAttribute("dm2", dm2);
		request.setAttribute("dm3", dm3);
		request.setAttribute("dm4", dm4);
		request.setAttribute("dm5", dm5);
		request.setAttribute("dm6", dm6);
		request.setAttribute("dm7", dm7);

		request.setAttribute("zhushu", zhushu);
		request.setAttribute("term", term);
		request.setAttribute("amount", amount);
		request.setAttribute("addNumber", "1");
		request.setAttribute("beishu", "1");
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("type", "DDAU");
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("autoMethod", "DDSevenStar");
		return "wap/sevenStar/sevenstarAutoDetail";
	}

	/**
	 * 七星彩定胆机选投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/SSAutoBet.jspa")
	public String SSAutoBet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("T01009");
		String channel = WapUtil.getChannelId(request);
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		String autoMethod = "", zhushu = "", beishu, certID, amount = "", zhuma, addNumber = "", token, ttssBet = "", oneMoney = "", lotno = "", batchCode;
		String type = "";
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			zhuma = request.getParameter("zhuma"); // 投注注码
			beishu = request.getParameter("beishu"); // 倍数
			token = request.getParameter("token");// 判断是否重复提交
			type = request.getParameter("type");// 类型
			amount = request.getParameter("amount");// 金额
			batchCode = request.getParameter("term");// 当前期号
			// newzhuma = request.getParameter("newzhuma");//当前期号
			autoMethod = request.getParameter("autoMethod");// 机选方法
			zhushu = request.getParameter("zhushu");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber"); // 追号
			}
		} else {// 获取request.getAttribute中的存储
			String[] beishus = request.getAttribute("beishu") == null ? null
					: (String[]) request.getAttribute("beishu");
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
			String[] autoMethods = request.getAttribute("autoMethod") == null ? null
					: (String[]) request.getAttribute("autoMethod");
			String[] addNumbers = null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber") == null ? null
						: (String[]) request.getAttribute("addNumber");
				addNumber = CommonUtil.QJToBJChange(addNumber);
			}
			// 获取输入页面的参数
			beishu = beishus == null || beishus[0].equals("") ? "0"
					: beishus[0]; // 倍数
			zhuma = zhumas == null || zhumas[0].equals("") ? "0" : zhumas[0];// 不带","的注码
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];
			type = types == null || types[0].equals("") ? "0" : types[0];
			amount = amounts == null || amounts[0].equals("") ? "0"
					: amounts[0];
			autoMethod = autoMethods == null || autoMethods[0].equals("") ? "0"
					: autoMethods[0];
			batchCode = batchCodes == null || batchCodes[0].equals("") ? "0"
					: batchCodes[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers == null || addNumbers[0].equals("") ? "0"
						: addNumbers[0];
			}
		}
		logger.info("userno:" + userno + " zhuma:" + zhuma
				+ " addNumber:" + addNumber + " beishu:" + beishu);
	
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			// 调用投注接口
			String wanfa="QXC_DT";

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
						request.setAttribute("lotno", "T01009");
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
						request.setAttribute("lotno", "T01009");
						return "wap/zengcai/zengcai";
					} else {
						boolean flag = CommonUtil.getBalanceResult(userno, amount);
						if(flag){
							request.setAttribute("message","您的余额不足，请先充值！");
							return "wap/charge/chargeIndex";
						}
						ttssBet=JsonToJrtLotUtil.sendToBet(userno, "T01009", batchCode, zhuma, beishu, "2", wanfa, amount, addNumber, channel);

					}

			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";

	}

	// 单式机选
	@RequestMapping(value="/showAutoBetDetails.jspx",method=RequestMethod.POST)
	public String showAutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	

		// 获取当前期号
		String term = CommonUtil.getTerm("T01009");
		// 获取输入页面的参数
		String multNo = request.getParameter("beishu"); // 倍数
		String zhushuStr = request.getParameter("zhushu"); // 注数
		String addNumber = request.getParameter("addNumber");
		String parameter = "beishu="+multNo+"&zhushu="+zhushuStr+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		// 验证全角半角,如果是全角,转换为半角
		multNo = CommonUtil.QJToBJChange(multNo);
		zhushuStr = CommonUtil.QJToBJChange(zhushuStr);

		// 验证输入参数合法性
		String ret = "类型错误";
		if (QixingcaiUtil.checkZhushu(zhushuStr)) {
			ret = null;
			if (QixingcaiUtil.checkBeishu(multNo)) {
				ret = null;
				if (rbint.getString("addNumberSwitch").equals("1")) {
					addNumber = request.getParameter("addNumber");
					if (addNumber == null || "0".equals(addNumber)) {
						addNumber = "1";
					}
					addNumber = CommonUtil.QJToBJChange(addNumber);
					logger.info(" addNumber"
							+ addNumber);
					if (QixingcaiUtil.checkAddNumber(addNumber)) {
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
			return "wap/sevenStar/SingleAutoSelection";
		} else {
			String beishu = "";
			if (multNo.trim().equals("")) {
				beishu = "1";
			} else {
				beishu = multNo;
			}
			// 计算注码,注数,金额,倍数
			String newzhuma = "";
			String betNo = "";
			int zhushu = Integer.parseInt(zhushuStr);
			betNo = QixingcaiUtil.autoZhumaSingle(zhushu);
			newzhuma = betNo.replaceAll(";", "<br />");

			long amount = zhushu * Integer.parseInt(beishu)
					* LotteryAlgorithmUtil.priceOfCaipiao;
			if (amount > 20000) {
				logger.info("单次投注金额不能超过两万元，amt" + amount);
				request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为" + amount
						+ "元");
				return "wap/sevenStar/SingleAutoSelection";
			}
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", multNo);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("betNo", betNo);
			request.setAttribute("type", "DS");
			request.setAttribute("term", term);
			request.setAttribute("autoMethod", "showAutoBetDetails");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			// 转到确认投注页面
			return "wap/sevenStar/7starAutoDetail";
		}
	}

	// 首页单式机选
	@RequestMapping(value="/autoBetDetails.jspx",method=RequestMethod.POST)
	public String autoBetDetails(HttpServletRequest request, HttpServletResponse response)
			throws Exception {


		// 获取当前期号
		String term = CommonUtil.getTerm("T01009");
		// 获取输入页面的参数
		String beishu = "1"; // 倍数
		String zhushuStr = "1"; // 注数
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null || "0".equals(addNumber)) {
				addNumber = "1";
			}
			logger.info(" addNumber" + addNumber);
		}
		logger.info(" beishu" + beishu
				+ " zhushuStr:" + zhushuStr);

		if (beishu.trim().equals("")) {
			beishu = "1";
		}
		// 计算注码,注数,金额,倍数
		String newzhuma = "";
		String betNo = "";
		int zhushu = Integer.parseInt(zhushuStr);

		newzhuma = request.getParameter("newzhuma");
		betNo = newzhuma;
		long amount = zhushu * Integer.parseInt(beishu)
				* LotteryAlgorithmUtil.priceOfCaipiao;
		if (amount > 20000) {
			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为" + amount
					+ "元");
			return "wap/sevenStar/SingleAutoSelection";
		}
		// 把计算出来的结果设置到request
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("amount", amount);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("betNo", betNo);
		request.setAttribute("type", "DS");
		request.setAttribute("term", term);
		request.setAttribute("autoMethod", "showAutoBetDetails");
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		// 转到确认投注页面
		return "wap/BetSuccess";
	}

	// 复式机选
	@RequestMapping(value="/showFSAutoBetDetails.jspx",method=RequestMethod.POST)
	public String showFSAutoBetDetails(HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		// 获取当前期号
		String term = CommonUtil.getTerm("T01009");
		// 获取输入页面的参数
		String beishu = request.getParameter("beishu"); // 倍数
		String type = request.getParameter("type"); // 类型
		String num1 = request.getParameter("num1"); // 第1位注数
		String num2 = request.getParameter("num2"); // 第2位注数
		String num3 = request.getParameter("num3"); // 第3位注数
		String num4 = request.getParameter("num4"); // 第4位注数
		String num5 = request.getParameter("num5"); // 第5位注数
		String num6 = request.getParameter("num6"); // 第6位注数
		String num7 = request.getParameter("num7"); // 第7位注数
		String addNumber = request.getParameter("addNumber");
		// 验证全角半角,如果是全角,转换为半角
		beishu = CommonUtil.QJToBJChange(beishu);
		
		// 验证输入参数合法性
		String ret = "类型错误";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			addNumber = CommonUtil.QJToBJChange(addNumber);
			logger.info(" addNumber"
					+ addNumber);
			if (QixingcaiUtil.checkAddNumber(addNumber)) {
				ret = null;
			} else {
				ret = "追期不合法";
			}
		}
		String parameter = "num1="+num1+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber
				+"&num2="+num2+"&num3="+num3+"&num4="+num4+"&num5="+num5+"&num6="+num6+"&num7="+num7;
		request.getSession().setAttribute("parameter", parameter);
		if (QixingcaiUtil.checkBeishu(beishu)) {
			ret = null;
			
		} else {
			ret = "倍数不合法";
		}
		logger.info("beishu:" + beishu
				+ " addNumber:" + addNumber);

		logger.info(" ret" + ret);
		if (ret != null) {
			request.setAttribute("err_msg", ret);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/sevenStar/MulipleAutoSelection";
		} else {
			long amount = (Integer.parseInt(num1) * Integer.parseInt(num2)
					* Integer.parseInt(num3) * Integer.parseInt(num4)
					* Integer.parseInt(num5) * Integer.parseInt(num6) * Integer
					.parseInt(num7))
					* Integer.parseInt(beishu)
					* LotteryAlgorithmUtil.priceOfCaipiao;
			if (amount > 20000) {
				logger.info("单次投注金额不能超过两万元，amt" + amount);
				request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为" + amount
						+ "元");
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/sevenStar/MulipleAutoSelection";
			}
			// 计算注码,注数,金额,倍数
			String newzhuma = "";
			String betNoStr = "";
			betNoStr = QixingcaiUtil.autoZhumaComplex(new int[] {
					Integer.parseInt(num1), Integer.parseInt(num2),
					Integer.parseInt(num3), Integer.parseInt(num4),
					Integer.parseInt(num5), Integer.parseInt(num6),
					Integer.parseInt(num7) });
			String zhushuStr = String.valueOf(QixingcaiUtil
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
			request.setAttribute("num6", num6);
			request.setAttribute("num7", num7);
			request.setAttribute("autoMethod", "showFSAutoBetDetails");

			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}

			logger.info( " zhushu:" + zhushu
					* Integer.parseInt(beishu) + " beishu:" + beishu
					+ " addNumber:" + addNumber + " amount:" + amount
					+ " newzhuma:" + newzhuma + " bet_No:" + betNoStr
					+ " type:" + type + " term:" + term);

			// 转到确认投注页面
			return "wap/sevenStar/7starAutoDetail";
		}
	}
	@RequestMapping(value="/placeBet.jspa")
	public String placeBet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String autoMethod = "", newzhuma = "", zhushu = "", beishu, certID, amount = "", betNo = "", addNumber = "", token, ttssBet = "", oneMoney = "", lotno = "", batchCode;
		String type = "";
		String term = CommonUtil.getTerm("T01009");
		String channel = WapUtil.getChannelId(request);

		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
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
			// autoMethod = autoMethods == null || autoMethods[0].equals("") ?
			// "0"
			// : autoMethods[0];
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
			String wanfa = "";
			if("DS".equals(type)){
				wanfa="QXC_DS";
			}else{
				wanfa="QXC_FS";
			}
			
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
				request.setAttribute("lotno", "T01009");
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
				request.setAttribute("lotno", "T01009");
				return "wap/zengcai/zengcai";
			} else {
				boolean flag = CommonUtil.getBalanceResult(userno, amount);
				if(flag){
					request.setAttribute("message","您的余额不足，请先充值！");
					return "wap/charge/chargeIndex";
				}
				ttssBet = JsonToJrtLotUtil.sendToBet(userno, "T01009", term, betNo, beishu, "2", wanfa, amount, addNumber, channel);

			}

			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

	
	
	//定胆修改的方法
	@RequestMapping(value="/xiugaiDd.jspx",method=RequestMethod.POST)
	public String xiugaiDd(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		//获取重新机选参数
		String dm1 = request.getParameter("dm1");
		String dm2 = request.getParameter("dm2");
		String dm3 = request.getParameter("dm3");
		String dm4 = request.getParameter("dm4");
		String dm5 = request.getParameter("dm5");
		String dm6 = request.getParameter("dm6");
		String dm7 = request.getParameter("dm7");
		//获取页面跳转参数
		String zhushu = request.getParameter("zhushu");
		String amount = request.getParameter("amount");
		String type = request.getParameter("type");
		String term = request.getParameter("term");
		String autoMethod = request.getParameter("autoMethod");
		StringBuffer s1 = new StringBuffer();
		String s2 = "";
		String zhuma="";
		//从前台页面接受显示格式的注码，并生成投注格式的注码
		for(int i=0;i<Integer.parseInt(zhushu);i++){
			String s = request.getParameter("i"+i);
			if("".equals(s)||s==null){
				continue;
			}
			s2=s1.append(s+";").toString();
		}
		zhuma =s2;
		String[] zhushuStr = s2.split(";");
		//计算新注数
		int newzhushu = zhushuStr.length;
		String newzhushuStr = newzhushu+"";
		//计算新金额
		int amt = newzhushu*2;
		String newamount = amt + "";
		//验证注码格式
		for(int i=0;i<zhushuStr.length;i++){
			if(QixingcaiUtil.checkXgNumber(zhushuStr[i])==false){
				request.setAttribute("err_msg","注码格式错误");
				request.setAttribute("zhuma",zhuma);
				request.setAttribute("amount",newamount);
				request.setAttribute("zhushu",newzhushuStr);
				return "wap/sevenStar/xiugaidd";
			}
		}
		if (amt > 20000) {
			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为" + newamount
					+ "元");
			request.setAttribute("zhuma",zhuma);
			return "wap/sevenStar/xiugaidd";
		}
		//生成显示格式注码
		String newzhuma = QixingcaiUtil.getZhumaReplace(zhuma);
		
		// 传递重新机选参数
		request.setAttribute("dm1", dm1);
		request.setAttribute("dm2", dm2);
		request.setAttribute("dm3", dm3);
		request.setAttribute("dm4", dm4);
		request.setAttribute("dm5", dm5);
		request.setAttribute("dm6", dm6);
		request.setAttribute("dm7", dm7);
		
		request.setAttribute("zhuma",zhuma);
		request.setAttribute("newzhuma",newzhuma);
		request.setAttribute("zhushu",newzhushuStr);
		request.setAttribute("amount",newamount);
		request.setAttribute("beishu","");
		request.setAttribute("addNumber","");
		request.setAttribute("type",type);
		request.setAttribute("term",term);
		request.setAttribute("autoMethod",autoMethod);
		return "wap/sevenStar/sevenstarAutoDetail";
	}
	
	//机选单式修改方法
	@RequestMapping(value="/singleAutoModify.jspx",method=RequestMethod.POST)
	public String singleAutoModify(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		String zhushu = request.getParameter("zhushu");
		String amount = request.getParameter("amount");
		String type = request.getParameter("type");
		String term = request.getParameter("term");
		String beishu = request.getParameter("beishu");
		String autoMethod = request.getParameter("autoMethod");
		String addNumber="";
		if (rbint.getString("addNumberSwitch").equals("1")) {
		 addNumber = request.getParameter("addNumber");
		}
		
		StringBuffer s1 = new StringBuffer();
		String s2 = "";
		String zhuma="";
		//从前台页面接受显示格式的注码，并生成投注格式的注码
		for(int i=0;i<Integer.parseInt(zhushu);i++){
			String s = request.getParameter("i"+i);
			if("".equals(s)||s==null){
				continue;
			}
			s2=s1.append(s+";").toString();
		}
		zhuma =s2;
		String[] zhushuStr = s2.split(";");
		if(QixingcaiUtil.checkBeishu(beishu)==false){
			request.setAttribute("err_msg","倍数格式错误");
			request.setAttribute("zhuma",zhuma);
			request.setAttribute("amount",zhushuStr.length*2+"");
			request.setAttribute("beishu", beishu);
			request.setAttribute("zhushu",zhushuStr.length+"");
			if (rbint.getString("addNumberSwitch").equals("1")){
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/sevenStar/7starAutoModify";
		}
		//计算新注数
		int newzhushu = zhushuStr.length;
		String newzhushuStr = newzhushu+"";
		//计算新金额
		int amt = newzhushu*Integer.parseInt(beishu)*2;
		String newamount = amt+"";
		if(QixingcaiUtil.checkAddNumber(addNumber)==false){
			request.setAttribute("err_msg","追期格式错误");
			request.setAttribute("zhuma",zhuma);
			request.setAttribute("beishu", beishu);
		    if (rbint.getString("addNumberSwitch").equals("1")){
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/sevenStar/7starAutoModify";
		}
		//验证注码格式
		for(int i=0;i<zhushuStr.length;i++){
			if(QixingcaiUtil.checkXgNumber(zhushuStr[i])==false){
				request.setAttribute("err_msg","注码格式错误");
				request.setAttribute("zhuma",zhuma);
				request.setAttribute("beishu", beishu);
				if (rbint.getString("addNumberSwitch").equals("1")){
					request.setAttribute("addNumber", addNumber);
				}
				return "wap/sevenStar/7starAutoModify";
			}
		}
		
		if (amt > 20000) {
			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为" + newamount + "元");
			request.setAttribute("zhuma",zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")){
				request.setAttribute("addNumber", "1");
			}
			return "wap/sevenStar/7starAutoModify";
		}
		
		//生成显示格式注码
		String newzhuma = QixingcaiUtil.getZhumaReplace(zhuma);
		
		request.setAttribute("betNo",zhuma);
		request.setAttribute("newzhuma",newzhuma);
		request.setAttribute("zhushu",newzhushu);
		request.setAttribute("amount",newamount);
		request.setAttribute("beishu",beishu);
		request.setAttribute("addNumber",addNumber);
		request.setAttribute("type",type);
		request.setAttribute("term",term);
		request.setAttribute("autoMethod",autoMethod);
		return "wap/sevenStar/7starAutoDetail";
	}
	
	//机选复式修改方法
	@RequestMapping(value="/mulipleAutoModify.jspx",method=RequestMethod.POST)
	public String mulipleAutoModify(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		//获取页面跳转参数
		String zhushu = request.getParameter("zhushu");
		String amount = request.getParameter("amount");
		String type = request.getParameter("type");
		String term = request.getParameter("term");
		String beishu = request.getParameter("beishu");
		String autoMethod = request.getParameter("autoMethod");
		String addNumber="";
		if (rbint.getString("addNumberSwitch").equals("1")) {
		 addNumber = request.getParameter("addNumber");
		}		
		String zhuma = request.getParameter("i0");
		String[] s = zhuma.split(",");
		if(QixingcaiUtil.checkBeishu(beishu)==false){
			request.setAttribute("err_msg","倍数格式错误");
			request.setAttribute("zhuma",zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")){
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/sevenStar/7starMulipleAutoModify";
		}
		if(QixingcaiUtil.checkAddNumber(addNumber)==false){
			request.setAttribute("err_msg","追期格式错误");
			request.setAttribute("zhuma",zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")){
				request.setAttribute("addNumber",addNumber);
			}
			return "wap/sevenStar/7starMulipleAutoModify";
		}
		for(int i=0;i<s.length;i++){
			if(!(QixingcaiUtil.checkDuplicate(s[i])&&(s[i].length()>9?false:true)&&QixingcaiUtil.checkMulipleNumber(s[i])&&(s.length!=7?false:true))){
				request.setAttribute("err_msg","注码格式错误");
				request.setAttribute("zhuma",zhuma);
				request.setAttribute("beishu", beishu);
				if (rbint.getString("addNumberSwitch").equals("1")){
					request.setAttribute("addNumber", addNumber);
				}
				return "wap/sevenStar/7starMulipleAutoModify";
			}
		}
		if(zhuma.endsWith(",")){
			request.setAttribute("err_msg","注码格式错误");
			request.setAttribute("zhuma",zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")){
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/sevenStar/7starMulipleAutoModify";
		}
		
		//计算新注数
		int zhushuStr = QixingcaiUtil.zhuShuAutoComplex(zhuma);
		String newzhushu = zhushuStr+"";
		//计算新金额
		int amt = zhushuStr*Integer.parseInt(beishu)*2;
		String newamount = amt+"";
		if (amt > 20000) {
			logger.info("单次投注金额不能超过两万元，amt" + amount);
			request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为" + newamount
					+ "元");
			request.setAttribute("zhuma",zhuma);
			request.setAttribute("beishu", beishu);
			if (rbint.getString("addNumberSwitch").equals("1")){
				request.setAttribute("addNumber", "1");
			}
			return "wap/sevenStar/7starMulipleAutoModify";
		}
		//传递重新机选参数
		request.setAttribute("num1", s[0].length()+"");
		request.setAttribute("num2", s[1].length()+"");
		request.setAttribute("num3", s[2].length()+"");
		request.setAttribute("num4", s[3].length()+"");
		request.setAttribute("num5", s[4].length()+"");
		request.setAttribute("num6", s[5].length()+"");
		request.setAttribute("num7", s[6].length()+"");
		//传递页面参数
		request.setAttribute("betNo",zhuma);
		request.setAttribute("newzhuma",zhuma);
		request.setAttribute("zhushu",newzhushu);
		request.setAttribute("amount",newamount);
		request.setAttribute("beishu",beishu);
		request.setAttribute("addNumber",addNumber);
		request.setAttribute("type",type);
		request.setAttribute("term",term);
		request.setAttribute("autoMethod",autoMethod);
		return "wap/sevenStar/7starAutoDetail";
		
	}

}