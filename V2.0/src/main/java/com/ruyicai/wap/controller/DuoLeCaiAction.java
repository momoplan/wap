package com.ruyicai.wap.controller;


import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buybal.lot.lottype.DuolecaiUtil;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/11select5")
@Controller
public class DuoLeCaiAction{

	private static final Logger logger = Logger
			.getLogger(DuoLeCaiAction.class);
	private static final ResourceBundle rbint = ResourceBundle
			.getBundle("jrtWAPSite");

	/**
	 * 11选5 胆拖提交投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/dantuoBetDetail.jspx",method=RequestMethod.POST)
	public String dantuoBetDetail(HttpServletRequest request,
			HttpServletResponse response) {

		String danma = request.getParameter("danma").trim();
		String tuoma = request.getParameter("tuoma").trim();
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String newzhuma = "";
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if("".equals(addNumber) || addNumber==null){
				addNumber = "1";
			}
		}
		String parameter = "danma="+danma+"&tuoma="+tuoma+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		beishu = CommonUtil.QJToBJChange(beishu);
		String zhuma = "";
		// 获取当前期号
		String term = CommonUtil.getTerm("T01010");
		String message = null;
		long zhushu = 0;
		if ("2".equals(type)) {
			message = DuolecaiUtil.checkDantuo(danma, tuoma, beishu, addNumber);
			zhushu = DuolecaiUtil.DanTuoR2zhushu(danma, tuoma);
			zhuma = DuolecaiUtil.betdantuoR2Zhuma(danma, tuoma);
		} 
		if ("3".equals(type)) {
			message = DuolecaiUtil.checkR3Dantuo(danma, tuoma, beishu,
					addNumber);
			zhushu = DuolecaiUtil.DanTuoR3zhushu(danma, tuoma);
			zhuma = DuolecaiUtil.betdantuoR3Zhuma(danma, tuoma);
		} 
		if ("4".equals(type)) {
			message = DuolecaiUtil.checkR4Dantuo(danma, tuoma, beishu,
					addNumber);
			zhushu = DuolecaiUtil.DanTuoR4zhushu(danma, tuoma);
			zhuma = DuolecaiUtil.betdantuoR4Zhuma(danma, tuoma);
		} 
		if ("5".equals(type)) {
					message = DuolecaiUtil.checkR5Dantuo(danma, tuoma, beishu,
							addNumber);
					zhushu = DuolecaiUtil.DanTuoR5zhushu(danma, tuoma);
					zhuma = DuolecaiUtil.betdantuoR5Zhuma(danma, tuoma);
				}
		if ("6".equals(type)) {
					message = DuolecaiUtil.checkR6Dantuo(danma, tuoma, beishu,
							addNumber);
					zhushu = DuolecaiUtil.DanTuoR6zhushu(danma, tuoma);
					zhuma = DuolecaiUtil.betdantuoR6Zhuma(danma, tuoma);
				} 
		if ("7".equals(type)) {
					message = DuolecaiUtil.checkR7Dantuo(danma, tuoma, beishu,
							addNumber);
					zhushu = DuolecaiUtil.DanTuoR7zhushu(danma, tuoma);
					zhuma = DuolecaiUtil.betdantuoR7Zhuma(danma, tuoma);
				} 
		 if ("8".equals(type)) {
					message = DuolecaiUtil.checkR8Dantuo(danma, tuoma, beishu,
							addNumber);
					zhushu = DuolecaiUtil.DanTuoR8zhushu(danma, tuoma);
					zhuma = DuolecaiUtil.betdantuoR8Zhuma(danma, tuoma);
				} 
		 if ("9".equals(type)) {
					message = DuolecaiUtil.checkZ2Dantuo(danma, tuoma, beishu,
							addNumber);
					zhushu = DuolecaiUtil.DanTuoZ2zhushu(danma, tuoma);
					zhuma = DuolecaiUtil.betdantuoZ2Zhuma(danma, tuoma);
				} 
		 if ("10".equals(type)) {
					message = DuolecaiUtil.checkZ3Dantuo(danma, tuoma, beishu,
							addNumber);
					zhushu = DuolecaiUtil.DanTuoZ3zhushu(danma, tuoma);
					zhuma = DuolecaiUtil.betdantuoZ3Zhuma(danma, tuoma);
				}

		logger.info( " message:" + message);
		if (message != null && !"pass".equals(message)) {
			request.setAttribute("danma", danma);
			request.setAttribute("tuoma", tuoma);
			request.setAttribute("beishu", beishu);
			request.setAttribute("message", message);
			request.setAttribute("type", type);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/11select5/dantuoselection";
		}
		int beishuInt = 0;
		if (beishu.trim().equals("")) {
			beishuInt = 1;
		} else {
			beishuInt = Integer.parseInt(beishu);
		}
		int amount = (int) zhushu * beishuInt
				* LotteryAlgorithmUtil.priceOfCaipiao;
		
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/11select5/dantuoselection";
		}
		newzhuma = DuolecaiUtil.getViewZhuma(danma+ tuoma);
		danma = DuolecaiUtil.getViewZhuma(danma);
		tuoma = DuolecaiUtil.getViewZhuma(tuoma);
		request.setAttribute("type", type);
		request.setAttribute("zhushu", zhushu * beishuInt);
		request.setAttribute("amount", amount);
		request.setAttribute("danma", danma);
		request.setAttribute("tuoma", tuoma);
		request.setAttribute("beishu", beishu);
		request.setAttribute("term", term);
		request.setAttribute("type", type);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("zhuma", zhuma);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", addNumber);
		}

		logger.info( " zhushu:"
				+ (zhushu * beishuInt) + " beishu:" + beishu + " addNumber:"
				+ addNumber + " amount:" + amount + " zhuma:" + zhuma
				+ "newzhuma" + newzhuma + " type:" + type + " term" + term);
		return "wap/11select5/DantuoBetDetail";

	}

	/**
	 * 11选5 胆拖确认提交投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/dantuoBet.jspa")
	public String dantuoBet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String zhuma, amount, beishu, zhushu, certID, type, addNumber = "", token;
		// 获取当前期号
		String term = CommonUtil.getTerm("T01010");
		String channel = WapUtil.getChannelId(request);
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
				if("".equals(addNumber) || addNumber==null){
					addNumber = "1";
				}
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
		boolean flag = CommonUtil.getBalanceResult(userno, amount);
		if(flag){
			request.setAttribute("message","您的余额不足，请先充值！");
			return "wap/charge/chargeIndex";
		}
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			// 执行正常的投注
			String ttssBet = "";
			String wanfa="";
			if("9".equals(type)){
				wanfa="DLC_DTQEZX";
			}else if("10".equals(type)){
				wanfa="DLC_DTQSZX";
			}else{
				wanfa="DLC_DT";
			}
			logger.info("userno:" + userno + " zhuma:" + zhuma);
			ttssBet=JsonToJrtLotUtil.sendToBet(userno, "T01010", term, zhuma, beishu, "2", wanfa, amount, addNumber, channel);
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

	/**
	 * 11选5 任选1-8 手选投注
	 * 
	 * @author 张步云 
	 * 网址：www.ruyicai.com
	 */
	@RequestMapping(value="/optionByHand.jspx",method=RequestMethod.POST)
	public String optionByHand(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// 接受界面传过来的参数
		String zhuma = request.getParameter("zhuma").trim();
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if("".equals(addNumber) || addNumber==null){
				addNumber="1";
			}
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" zhuma :" + zhuma + " beishu :" + beishu + " type :"
				+ type + " addNumber :" + addNumber);
		// 转换半角
			zhuma = CommonUtil.QJToBJChange(zhuma);
		    beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		// 对接受的参数进行验证
		 if (type.equals("R1") || type == "R1") {
			if (!DuolecaiUtil.checkR1(zhuma, beishu, addNumber).equals("pass")
				) {
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("err_msg", DuolecaiUtil.checkR1(zhuma,
						beishu, addNumber));
				return "wap/11select5/optionOne";
			}
		} else if (type.equals("R2") || type == "R2") {
			if (!DuolecaiUtil.checkR2(zhuma, beishu, addNumber).equals("pass")
				) {
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("err_msg", DuolecaiUtil.checkR2(zhuma,
						beishu, addNumber));
				return "wap/11select5/optionTwo";
			}
		} else if (type.equals("R3") || type == "R3") {
			if (!DuolecaiUtil.checkR3(zhuma, beishu, addNumber).equals("pass")
				) {
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("err_msg", DuolecaiUtil.checkR3(zhuma,
						beishu, addNumber));
				return "wap/11select5/optionThree";
			}
		} else if (type.equals("R4") || type == "R4") {
			if (!DuolecaiUtil.checkR4(zhuma, beishu, addNumber).equals("pass")
				) {
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("err_msg", DuolecaiUtil.checkR4(zhuma,
						beishu, addNumber));
				return "wap/11select5/optionFour";
			}
		} else if (type.equals("R5") || type == "R5") {
			if (!DuolecaiUtil.checkR5(zhuma, beishu, addNumber).equals("pass")
				) {
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("err_msg", DuolecaiUtil.checkR5(zhuma,
						beishu, addNumber));
				return "wap/11select5/optionFive";
			}
		} else if (type.equals("R6") || type == "R6") {
			if (!DuolecaiUtil.checkR6(zhuma, beishu, addNumber).equals("pass")
					) {
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("err_msg", DuolecaiUtil.checkR6(zhuma,
						beishu, addNumber));
				return "wap/11select5/optionSix";
			}
		} else if (type.equals("R7") || type == "R7") {
			if (!DuolecaiUtil.checkR7(zhuma, beishu, addNumber).equals("pass")
					) {
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("err_msg", DuolecaiUtil.checkR7(zhuma,
						beishu, addNumber));
				return "wap/11select5/optionSeven";
			}
		} else if (type.equals("R8") || type == "R8") {
			if (!DuolecaiUtil.checkR8(zhuma, beishu, addNumber).equals("pass") ){
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				request.setAttribute("err_msg", DuolecaiUtil.checkR8(zhuma,
						beishu, addNumber));
				return "wap/11select5/optionEight";
			}
		}
		
		// 生成新注码newzhuma
		String newzhuma = DuolecaiUtil.getViewZhuma(zhuma);
		logger.info(" newzhuma :" + newzhuma + " zhuma :" + zhuma + " beishu :"
				+ beishu + " type :" + type + " addNumber :" + addNumber);
		// 计算注数
		long zhushuLong = 0;
		if (type.equals("R1") || type == "R1") {
			zhushuLong = DuolecaiUtil.zhushuSingle(zhuma);
		} else if (type.equals("R2") || type == "R2") {
			zhushuLong = DuolecaiUtil.zhushuR(zhuma, 2);
		} else if (type.equals("R3") || type == "R3") {
			zhushuLong = DuolecaiUtil.zhushuR(zhuma, 3);
		} else if (type.equals("R4") || type == "R4") {
			zhushuLong = DuolecaiUtil.zhushuR(zhuma, 4);
		} else if (type.equals("R5") || type == "R5") {
			zhushuLong = DuolecaiUtil.zhushuR(zhuma, 5);
		} else if (type.equals("R6") || type == "R6") {
			zhushuLong = DuolecaiUtil.zhushuR(zhuma, 6);
		} else if (type.equals("R7") || type == "R7") {
			zhushuLong = DuolecaiUtil.zhushuR(zhuma, 7);
		} else if (type.equals("R8") || type == "R8") {
			zhushuLong = DuolecaiUtil.zhushuR(zhuma, 8);
		}
		String zhushu = zhushuLong + "";
		logger.info(" zhushu :" + zhushu + " newzhuma :" + newzhuma
				+ " zhuma :" + zhuma + " beishu :" + beishu + " type :" + type
				+ " addNumber :" + addNumber);
		// 计算金额
		int amountInt = LotteryAlgorithmUtil.priceOfCaipiao
				* Integer.parseInt(beishu) * Integer.parseInt(zhushu);
		String amount = amountInt + "";
		logger.info(" amount :" + amount + " zhushu :" + zhushu + " newzhuma :"
				+ newzhuma + " zhuma :" + zhuma + " beishu :" + beishu
				+ " type :" + type + " addNumber :" + addNumber);
		
		// 验证单次投注金额
		if (!CommonUtil.TCverifyAmount(amountInt)) {
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			if (type.equals("R1") || type == "R1") {
				return "wap/11select5/optionOne";
			} else if (type.equals("R2") || type == "R2") {
				return "wap/11select5/optionTwo";
			} else if (type.equals("R3") || type == "R3") {
				return "wap/11select5/optionThree";
			} else if (type.equals("R4") || type == "R4") {
				return "wap/11select5/optionFour";
			} else if (type.equals("R5") || type == "R5") {
				return "wap/11select5/optionFive";
			} else if (type.equals("R6") || type == "R6") {
				return "wap/11select5/optionSix";
			} else if (type.equals("R7") || type == "R7") {
				return "wap/11select5/optionSeven";
			} else if (type.equals("R8") || type == "R8") {
				return "wap/11select5/optionEight";
			}
		}
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		request.setAttribute("type", type);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		return "wap/11select5/generalBetDetail";
	}

	/**
	 * 复式机选
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/optionFSAuto.jspx",method=RequestMethod.POST)
	public String optionFSAuto(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// 接受界面传过来的参数
		String optionFS = request.getParameter("optionFS");
		String R = request.getParameter("R");
		String addNumber = request.getParameter("addNumber");
		String beishu = request.getParameter("beishu");
		String parameter = "optionFS="+optionFS+"&R="+R+"&addNumber="+addNumber+"&beishu="+beishu;
		request.getSession().setAttribute("parameter", parameter);
		String type = "R" + R;
		String zhuma = DuolecaiUtil
				.RMultipleAutoNum(Integer.parseInt(optionFS));
		int zhushu = 0;
		// 转换半角
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		// 生成新注码newzhuma
		String newzhuma = DuolecaiUtil.getRMultipleAutoZhuma(zhuma);
		// 对接受的参数进行验证
		String checkResult = DuolecaiUtil.checkBeiShuAndAddNumber(beishu, addNumber);
		if(!"".equals(checkResult)&&checkResult!=null){
			request.setAttribute("err_msg", checkResult);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			if (type.equals("R1") || type == "R1") {
				return "wap/11select5/optionOneAutoFS";
			} else if (type.equals("R2") || type == "R2") {
				return "wap/11select5/optionTwoAutoFS";
			} else if (type.equals("R3") || type == "R3") {
				return "wap/11select5/optionThreeAutoFS";
			} else if (type.equals("R4") || type == "R4") {
				return "wap/11select5/optionFourAutoFS";
			} else if (type.equals("R5") || type == "R5") {
				return "wap/11select5/optionFiveAutoFS";
			} else if (type.equals("R6") || type == "R6") {
				return "wap/11select5/optionSixAutoFS";
			} else if (type.equals("R7") || type == "R7") {
				return "wap/11select5/optionSevenAutoFS";
			} else if (type.equals("R8") || type == "R8") {
				return "wap/11select5/optionEightAutoFS";
			}
		}
		if (type.equals("R1") || type == "R1") {
			zhushu = (int) DuolecaiUtil.zhushuR1Auto(zhuma);
		} else if (type.equals("R2") || type == "R2") {
			zhushu = (int) DuolecaiUtil.zhushuR2Auto(zhuma);
		} else if (type.equals("R3") || type == "R3") {
			zhushu = (int) DuolecaiUtil.zhushuR3Auto(zhuma);
		} else if (type.equals("R4") || type == "R4") {
			zhushu = (int) DuolecaiUtil.zhushuR4Auto(zhuma);
		} else if (type.equals("R5") || type == "R5") {
			zhushu = (int) DuolecaiUtil.zhushuR5Auto(zhuma);
		} else if (type.equals("R6") || type == "R6") {
			zhushu = (int) DuolecaiUtil.zhushuR6Auto(zhuma);
		} else if (type.equals("R7") || type == "R7") {
			zhushu = (int) DuolecaiUtil.zhushuR7Auto(zhuma);
		} else if (type.equals("R8") || type == "R8") {
			zhushu = (int) DuolecaiUtil.zhushuR8Auto(zhuma);
		}
		// 计算金额
		int amountInt = LotteryAlgorithmUtil.priceOfCaipiao
				* Integer.parseInt(beishu) * zhushu;
		String amount = amountInt + "";
		logger.info(" amount :" + amount + " zhushu :" + zhushu + " newzhuma :"
				+ newzhuma + " zhuma :" + zhuma + " beishu :" + beishu
				+ " type :" + type + " addNumber :" + addNumber);
		// 验证单次投注金额
		if (!CommonUtil.TCverifyAmount(amountInt)) {
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			if (type.equals("R1") || type == "R1") {
				return "wap/11select5/optionOneAutoFS";
			} else if (type.equals("R2") || type == "R2") {
				return "wap/11select5/optionTwoAutoFS";
			} else if (type.equals("R3") || type == "R3") {
				return "wap/11select5/optionThreeAutoFS";
			} else if (type.equals("R4") || type == "R4") {
				return "wap/11select5/optionFourAutoFS";
			} else if (type.equals("R5") || type == "R5") {
				return "wap/11select5/optionFiveAutoFS";
			} else if (type.equals("R6") || type == "R6") {
				return "wap/11select5/optionSixAutoFS";
			} else if (type.equals("R7") || type == "R7") {
				return "wap/11select5/optionSevenAutoFS";
			} else if (type.equals("R8") || type == "R8") {
				return "wap/11select5/optionEightAutoFS";
			}
		}
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("optionFS", optionFS);
		request.setAttribute("R", R);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount + "");
		request.setAttribute("type", type);
		request.setAttribute("zhushu", zhushu + "");
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		return "wap/11select5/generalFSJixuanBetDetail";
	}

	/**
	 * 11选5 任选1-8 danshijixuan投注
	 * 
	 * @author 张步云 网址：www.ruyicai.com
	 */
	@RequestMapping(value="/optionDSAuto.jspx",method=RequestMethod.POST)
	public String optionDSAuto(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// 接受界面传来的参数
		String optionDS = request.getParameter("optionDS");
		String R = request.getParameter("R");
		String addNumber = request.getParameter("addNumber");
		String beishu = request.getParameter("beishu");
		String parameter = "optionDS="+optionDS+"&R="+R+"&addNumber="+addNumber+"&beishu="+beishu;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" optionDS :" + optionDS + " R :" + R);
		// 对注数进行验证
		if (R.equals("1") || R == "1") {
			if (!CommonUtil.checkAutozhushu(optionDS,beishu,addNumber).equals("pass")
					|| CommonUtil.checkAutozhushu(optionDS,beishu,addNumber) != "pass") {
				request.setAttribute("optionDS", optionDS);
				request.setAttribute("err_msg", CommonUtil
						.checkAutozhushu(optionDS,beishu,addNumber));
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/11select5/optionOneAutoDS";
			}
		} else if (R.equals("2") || R == "2") {
			if (!CommonUtil.checkAutozhushu(optionDS,beishu,addNumber).equals("pass")
					|| CommonUtil.checkAutozhushu(optionDS,beishu,addNumber) != "pass") {
				request.setAttribute("optionDS", optionDS);
				request.setAttribute("err_msg", CommonUtil
						.checkAutozhushu(optionDS,beishu,addNumber));
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/11select5/optionTwoAutoDS";
			}
		} else if (R.equals("3") || R == "3") {
			if (!CommonUtil.checkAutozhushu(optionDS,beishu,addNumber).equals("pass")
					|| CommonUtil.checkAutozhushu(optionDS,beishu,addNumber) != "pass") {
				request.setAttribute("optionDS", optionDS);
				request.setAttribute("err_msg", CommonUtil
						.checkAutozhushu(optionDS,beishu,addNumber));
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/11select5/optionThreeAutoDS";
			}
		} else if (R.equals("4") || R == "4") {
			if (!CommonUtil.checkAutozhushu(optionDS,beishu,addNumber).equals("pass")
					|| CommonUtil.checkAutozhushu(optionDS,beishu,addNumber) != "pass") {
				request.setAttribute("optionDS", optionDS);
				request.setAttribute("err_msg", CommonUtil
						.checkAutozhushu(optionDS,beishu,addNumber));
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/11select5/optionFourAutoDS";
			}
		} else if (R.equals("5") || R == "5") {
			if (!CommonUtil.checkAutozhushu(optionDS,beishu,addNumber).equals("pass")
					|| CommonUtil.checkAutozhushu(optionDS,beishu,addNumber) != "pass") {
				request.setAttribute("optionDS", optionDS);
				request.setAttribute("err_msg", CommonUtil
						.checkAutozhushu(optionDS,beishu,addNumber));
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/11select5/optionFiveAutoDS";
			}
		} else if (R.equals("6") || R == "6") {
			if (!CommonUtil.checkAutozhushu(optionDS,beishu,addNumber).equals("pass")
					|| CommonUtil.checkAutozhushu(optionDS,beishu,addNumber) != "pass") {
				request.setAttribute("optionDS", optionDS);
				request.setAttribute("err_msg", CommonUtil
						.checkAutozhushu(optionDS,beishu,addNumber));
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/11select5/optionSixAutoDS";
			}
		} else if (R.equals("7") || R == "7") {
			if (!CommonUtil.checkAutozhushu(optionDS,beishu,addNumber).equals("pass")
					|| CommonUtil.checkAutozhushu(optionDS,beishu,addNumber) != "pass") {
				request.setAttribute("optionDS", optionDS);
				request.setAttribute("err_msg", CommonUtil
						.checkAutozhushu(optionDS,beishu,addNumber));
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/11select5/optionSevenAutoDS";
			}
		} else if (R.equals("8") || R == "8") {
			if (!CommonUtil.checkAutozhushu(optionDS,beishu,addNumber).equals("pass")
					|| CommonUtil.checkAutozhushu(optionDS,beishu,addNumber) != "pass") {
				request.setAttribute("optionDS", optionDS);
				request.setAttribute("err_msg", CommonUtil
						.checkAutozhushu(optionDS,beishu,addNumber));
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/11select5/optionEightAutoDS";
			}
		}
		// 转换半角
		optionDS = CommonUtil.QJToBJChange(optionDS);
		R = CommonUtil.QJToBJChange(R);
		String zhushu = optionDS;
		// 根据R的类型生成对应的zhuma
		String zhuma = "";
		if (R.equals("1") || R == "1") {
			zhuma = DuolecaiUtil.R1AutoNumber(Integer.parseInt(zhushu));
		} else if (R.equals("2") || R == "2") {
			zhuma = DuolecaiUtil.R2AutoNumber(Integer.parseInt(zhushu));
		} else if (R.equals("3") || R == "3") {
			zhuma = DuolecaiUtil.R3AutoNumber(Integer.parseInt(zhushu));
		} else if (R.equals("4") || R == "4") {
			zhuma = DuolecaiUtil.R4AutoNumber(Integer.parseInt(zhushu));
		} else if (R.equals("5") || R == "5") {
			zhuma = DuolecaiUtil.R5AutoNumber(Integer.parseInt(zhushu));
		} else if (R.equals("6") || R == "6") {
			zhuma = DuolecaiUtil.R6AutoNumber(Integer.parseInt(zhushu));
		} else if (R.equals("7") || R == "7") {
			zhuma = DuolecaiUtil.R7AutoNumber(Integer.parseInt(zhushu));
		} else if (R.equals("8") || R == "8") {
			zhuma = DuolecaiUtil.R8AutoNumber(Integer.parseInt(zhushu));
		}
		// 新注码
		String newzhuma = DuolecaiUtil.getRAutoZhuma(zhuma);
		// 根据R来计算金额
		int amountInt = LotteryAlgorithmUtil.priceOfCaipiao
				* Integer.parseInt(zhushu)*Integer.parseInt(beishu);
		String amount = amountInt + "";
		// 判断单次投注金额
		if (!CommonUtil.TCverifyAmount(Integer.parseInt(amount))) {
			request.setAttribute("optionDS", optionDS);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			if (R.equals("1") || R == "1") {
				return "wap/11select5/optionOneAutoDS";
			} else if (R.equals("2") || R == "2") {
				return "wap/11select5/optionTwoAutoDS";
			} else if (R.equals("3") || R == "3") {
				return "wap/11select5/optionThreeAutoDS";
			} else if (R.equals("4") || R == "4") {
				return "wap/11select5/optionFourAutoDS";
			} else if (R.equals("5") || R == "5") {
				return "wap/11select5/optionFiveAutoDS";
			} else if (R.equals("6") || R == "6") {
				return "wap/11select5/optionSixAutoDS";
			} else if (R.equals("7") || R == "7") {
				return "wap/11select5/optionSevenAutoDS";
			} else if (R.equals("8") || R == "8") {
				return "wap/11select5/optionEightAutoDS";
			}
		}
		logger.info(" zhushu :" + zhushu + " amount :" + amount + " newzhuma :"
				+ newzhuma + " zhuma :" + zhuma + " R :" + R);
		if (R.equals("1") || R == "1") {
			request.setAttribute("type", "R1");
		} else if (R.equals("2") || R == "2") {
			request.setAttribute("type", "R2");
		} else if (R.equals("3") || R == "3") {
			request.setAttribute("type", "R3");
		} else if (R.equals("4") || R == "4") {
			request.setAttribute("type", "R4");
		} else if (R.equals("5") || R == "5") {
			request.setAttribute("type", "R5");
		} else if (R.equals("6") || R == "6") {
			request.setAttribute("type", "R6");
		} else if (R.equals("7") || R == "7") {
			request.setAttribute("type", "R7");
		} else if (R.equals("8") || R == "8") {
			request.setAttribute("type", "R8");
		}
		request.setAttribute("R", R);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("optionDS", optionDS);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		return "wap/11select5/generalDSJixuanBetDetail";
	}

	/**
	 * 11选5 前二直选 手选 投注
	 * 
	 * @author 张步云 网址：www.ruyicai.com
	 */
	@RequestMapping(value="/qTwoByHand.jspx",method=RequestMethod.POST)
	public String qTwoByHand(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// 接受页面传来的参数
		String myria = request.getParameter("myria");
		String thousand = request.getParameter("thousand");
		String beishu = request.getParameter("beishu") == null?"1":request.getParameter("beishu");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if("".equals(addNumber) || addNumber==null){
				addNumber = "1";
			}
		}
		String parameter = "myria="+myria+"&thousand="+thousand+"&beishu="+beishu+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		// 转换半角
		myria = CommonUtil.QJToBJChange(myria);
		thousand = CommonUtil.QJToBJChange(thousand);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		// 验证页面传来的参数
		if (!DuolecaiUtil.checkQ2(myria, thousand, beishu, addNumber).equals(
				"pass")
				|| DuolecaiUtil.checkQ2(myria, thousand, beishu, addNumber) != "pass") {
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			request.setAttribute("err_msg", DuolecaiUtil.checkQ2(myria,
					thousand, beishu, addNumber));
			return "wap/11select5/ForwardTwoZX";
		}
	
		String zhuma = myria + "," + thousand;
		// 新注码
		String newzhuma = DuolecaiUtil.getQ2viewZhuMa(zhuma);
		// 计算注数
		long zhushuLong = DuolecaiUtil.getQ2Zhushu(myria, thousand);
		String zhushu = zhushuLong + "";
		// 计算金额
		String amount = LotteryAlgorithmUtil.priceOfCaipiao
				* Integer.parseInt(zhushu) * Integer.parseInt(beishu) + "";
		// 判断单次投注金额
		if (!CommonUtil.TCverifyAmount(Integer.parseInt(amount))) {
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/11select5/ForwardTwoZX";
		}
		logger.info(" zhuma :" + zhuma + " newzhuma :" + newzhuma + " zhushu :"
				+ zhushu + " amount :" + amount);
		request.setAttribute("beishu", beishu);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", "Q2");
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		return "wap/11select5/generalBetDetail";
	}

	/**
	 * 11选5 前二组选 手选 投注
	 * 
	 * @author 张步云 网址：www.ruyicai.com
	 */
	@RequestMapping(value="/zTwoByHand.jspx",method=RequestMethod.POST)
	public String zTwoByHand(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 接收界面传来的参数
		String zhuma = request.getParameter("zhuma");
		String beishu = request.getParameter("beishu");
		String addNumber = "";	
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if("".equals(addNumber) || addNumber==null){
				addNumber = "1";
			}
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishu+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" zhuma :" + zhuma + " beishu :" + beishu + " addNumber :"
				+ addNumber);
		// 对接收的参数进行验证
		if (!DuolecaiUtil.checkZ2(zhuma, beishu, addNumber).equals("pass")
				|| DuolecaiUtil.checkZ2(zhuma, beishu, addNumber) != "pass") {
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			request.setAttribute("err_msg", DuolecaiUtil.checkZ2(zhuma, beishu,
					addNumber));
			return "wap/11select5/ForwardTwoGroup";
		}
		// 转换半角
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		// 新注码
		String newzhuma = DuolecaiUtil.getViewZhuma(zhuma);
		// 计算注数
		String zhushu = DuolecaiUtil.zhushuZ2(zhuma) + "";
		// 计算金额
		int amountInt = LotteryAlgorithmUtil.priceOfCaipiao
				* Integer.parseInt(beishu) * Integer.parseInt(zhushu);
		String amount = amountInt + "";
		// 对投注金额进行验证
		if (!CommonUtil.TCverifyAmount(Integer.parseInt(amount))) {
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/11select5/ForwardTwoGroup";
		}
		logger.info(" zhushu :" + zhushu + " amount :" + amount);
		request.setAttribute("beishu", beishu);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", "Z2");
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		return "wap/11select5/generalBetDetail";
	}

	/**
	 * 11选5 前SAN组选 手选 投注
	 * 
	 * @author 张步云 网址：www.ruyicai.com
	 */
	@RequestMapping(value="/zThreeByHand.jspx",method=RequestMethod.POST)
	public String zThreeByHand(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		// 接收界面传来的参数
		String zhuma = request.getParameter("zhuma");
		String beishu = request.getParameter("beishu");
		String addNumber = "";	
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if("".equals(addNumber) || addNumber==null){
				addNumber = "1";
			}
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishu+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" zhuma :" + zhuma + " beishu :" + beishu + " addNumber :"
				+ addNumber);
		// 对接收的参数进行验证
		if (!DuolecaiUtil.checkZ3(zhuma, beishu, addNumber).equals("pass")
				|| DuolecaiUtil.checkZ3(zhuma, beishu, addNumber) != "pass") {
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			request.setAttribute("err_msg", DuolecaiUtil.checkZ3(zhuma, beishu,
					addNumber));
			return "wap/11select5/ForwardThreeGroup";
		}
		// 转换半角
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		// 新注码
		String newzhuma = DuolecaiUtil.getViewZhuma(zhuma);
		// 计算注数
		String zhushu = DuolecaiUtil.zhushuZ3(zhuma) + "";
		// 计算金额
		int amountInt = LotteryAlgorithmUtil.priceOfCaipiao
				* Integer.parseInt(beishu) * Integer.parseInt(zhushu);
		String amount = amountInt + "";
		// 对投注金额进行验证
		if (!CommonUtil.TCverifyAmount(Integer.parseInt(amount))) {
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/11select5/ForwardThreeGroup";
		}
		logger.info(" zhushu :" + zhushu + " amount :" + amount);
		request.setAttribute("beishu", beishu);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", "Z3");
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		return "wap/11select5/generalBetDetail";
	}

	/**
	 * 11选5 前三直选 手选 投注
	 * 
	 * @author 张步云 网址：www.ruyicai.com
	 */
	@RequestMapping(value="/qThreeByHand.jspx",method=RequestMethod.POST)
	public String qThreeByHand(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 接受页面传来的参数
		String myria = request.getParameter("myria");
		String thousand = request.getParameter("thousand");
		String hundred = request.getParameter("hundred");
		String beishu = request.getParameter("beishu");
		String addNumber = "";	
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if("".equals(addNumber) || addNumber==null){
				addNumber = "1";
			}
		}
		String parameter = "myria="+myria+"&beishu="+beishu+"&thousand="+thousand+"&hundred="+hundred	+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" myria :" + myria + " thousand :" + thousand + " beishu :"
				+ beishu + " addNumber :" + addNumber + " hundred :" + hundred);
		// 验证页面传来的参数
		if (!DuolecaiUtil.checkQ3(myria, thousand, hundred, beishu, addNumber)
				.equals("pass")
				|| DuolecaiUtil.checkQ3(myria, thousand, hundred, beishu,
						addNumber) != "pass") {
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			request.setAttribute("err_msg", DuolecaiUtil.checkQ3(myria,
					thousand, hundred, beishu, addNumber));
			return "wap/11select5/ForwardThreeZX";
		}
		// 转换半角
		myria = CommonUtil.QJToBJChange(myria);
		thousand = CommonUtil.QJToBJChange(thousand);
		hundred = CommonUtil.QJToBJChange(hundred);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		String zhuma = myria + "," + thousand + "," + hundred;
		// 新注码
		String newzhuma = DuolecaiUtil.getQ3viewZhuMa(zhuma);
		// 计算注数
		long zhushuLong = DuolecaiUtil.getQ3Zhushu(myria, thousand, hundred);
		String zhushu = zhushuLong + "";
		// 计算金额
		String amount = LotteryAlgorithmUtil.priceOfCaipiao
				* Integer.parseInt(zhushu) * Integer.parseInt(beishu) + "";
		// 判断单次投注金额
		if (!CommonUtil.TCverifyAmount(Integer.parseInt(amount))) {
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			request.setAttribute("err_msg", MessageUtil.TC_AmountError);
			return "wap/11select5/ForwardThreeZX";
		}
		logger.info(" zhuma :" + zhuma + " newzhuma :" + newzhuma + " zhushu :"
				+ zhushu + " amount :" + amount + " hundred :" + hundred);
		request.setAttribute("beishu", beishu);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("type", "Q3");
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		return "wap/11select5/generalBetDetail";
	}

	/**
	 * 普通玩法投注
	 * 
	 * @author 张步云 网址：www.ruyicai.com
	 */
	@RequestMapping(value="/BetConfirm.jspa")
	public String BetConfirm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("T01010");
		String channel = WapUtil.getChannelId(request);
		String newzhuma = "", zhushu = "", beishu, certID, amount = "", zhuma, addNumber = "", token, ttssBet = "", oneMoney = "", lotno = "", batchCode;
		String type = request.getParameter("type");
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			zhuma = request.getParameter("zhuma"); // 个位
			beishu = request.getParameter("beishu"); // 倍数
			token = request.getParameter("token");// 判断是否重复提交
			type = request.getParameter("type");// 类型
			if (type.equals("R1") || type == "R1") {
				zhuma = DuolecaiUtil.betR1Zhuma(zhuma);
			} else if (type.equals("R2") || type == "R2") {
				zhuma = DuolecaiUtil.betR2Zhuma(zhuma);
			} else if (type.equals("R3") || type == "R3") {
				zhuma = DuolecaiUtil.betR3Zhuma(zhuma);
			} else if (type.equals("R4") || type == "R4") {
				zhuma = DuolecaiUtil.betR4Zhuma(zhuma);
			} else if (type.equals("R5") || type == "R5") {
				zhuma = DuolecaiUtil.betR5Zhuma(zhuma);
			} else if (type.equals("R6") || type == "R6") {
				zhuma = DuolecaiUtil.betR6Zhuma(zhuma);
			} else if (type.equals("R7") || type == "R7") {
				zhuma = DuolecaiUtil.betR7Zhuma(zhuma);
			} else if (type.equals("R8") || type == "R8") {
				zhuma = DuolecaiUtil.betR8Zhuma(zhuma);
			} else if (type.equals("Q2") || type == "Q2") {
				zhuma = DuolecaiUtil.betQ2Zhuma(zhuma);
			} else if (type.equals("Z2") || type == "Z2") {
				zhuma = DuolecaiUtil.betZ2Zhuma(zhuma);
			} else if (type.equals("Q3") || type == "Q3") {
				zhuma = DuolecaiUtil.betQ3Zhuma(zhuma);
			} else if (type.equals("Z3") || type == "Z3") {
				zhuma = DuolecaiUtil.betZ3Zhuma(zhuma);
			}
			amount = request.getParameter("amount");// 彩种标号
			batchCode = request.getParameter("term");// 当前期号
			newzhuma = request.getParameter("newzhuma");// 当前期号
			zhushu = request.getParameter("zhushu");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber");
				if("".equals(addNumber) || addNumber==null){
					addNumber = "1";
				}
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
			if (type.equals("R1") || type == "R1") {
				zhuma = DuolecaiUtil.betR1Zhuma(zhuma);
			} else if (type.equals("R2") || type == "R2") {
				zhuma = DuolecaiUtil.betR2Zhuma(zhuma);
			} else if (type.equals("R3") || type == "R3") {
				zhuma = DuolecaiUtil.betR3Zhuma(zhuma);
			} else if (type.equals("R4") || type == "R4") {
				zhuma = DuolecaiUtil.betR4Zhuma(zhuma);
			} else if (type.equals("R5") || type == "R5") {
				zhuma = DuolecaiUtil.betR5Zhuma(zhuma);
			} else if (type.equals("R6") || type == "R6") {
				zhuma = DuolecaiUtil.betR6Zhuma(zhuma);
			} else if (type.equals("R7") || type == "R7") {
				zhuma = DuolecaiUtil.betR7Zhuma(zhuma);
			} else if (type.equals("R8") || type == "R8") {
				zhuma = DuolecaiUtil.betR8Zhuma(zhuma);
			} else if (type.equals("Q2") || type == "Q2") {
				zhuma = DuolecaiUtil.betQ2Zhuma(zhuma);
			} else if (type.equals("Z2") || type == "Z2") {
				zhuma = DuolecaiUtil.betZ2Zhuma(zhuma);
			} else if (type.equals("Q3") || type == "Q3") {
				zhuma = DuolecaiUtil.betQ3Zhuma(zhuma);
			} else if (type.equals("Z3") || type == "Z3") {
				zhuma = DuolecaiUtil.betZ3Zhuma(zhuma);
			}
			amount = amounts == null || amounts[0].equals("") ? "0"
					: amounts[0];
			batchCode = batchCodes == null || batchCodes[0].equals("") ? "0"
					: batchCodes[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers == null || addNumbers[0].equals("") ? "0"
						: addNumbers[0];
			}
		}
		logger.info("userno:" + userno + " zhuma:" + zhuma
				+ " addNumber:" + addNumber + " beishu:" + beishu);
		boolean flag = CommonUtil.getBalanceResult(userno, amount);
		if(flag){
			request.setAttribute("message","您的余额不足，请先充值！");
			return "wap/charge/chargeIndex";
		}
		// 判断是否重复提交
		String isExecute = (String) request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");

			String wanfa = "";
			ttssBet = JsonToJrtLotUtil.sendToBet(userno, "T01010", term, zhuma, beishu, "2", wanfa, amount, addNumber, channel);

			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}

}
