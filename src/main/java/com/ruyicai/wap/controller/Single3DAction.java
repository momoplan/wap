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

import com.buybal.lot.lottype.Array5Util;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/single3D")
@Controller
public class Single3DAction{

	private static final Logger logger = Logger.getLogger(Single3DAction.class);
	 
	
	/**
	 * 3D机选投注信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="/autoSelectionDetail.jspx",method=RequestMethod.POST)
	public String autoSelectionDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取当前期号
		String term = CommonUtil.getTerm("F47103");
		String zhushu = request.getParameter("zhushu");
		String beishu = request.getParameter("beishu");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber==null) {
				addNumber = "1";
			}
			logger.info(" addNumber:"+addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		logger.info(" zhushu:"+zhushu+" beishu:"+beishu+" term:"+term);
		if (VerificationAlgorithmUtil.isStringFilter(zhushu)||VerificationAlgorithmUtil.isStringFilter(beishu)) {
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
			//随机生成3D注码
			Vector vector = LotteryAlgorithmUtil.getRandomIntArrayWithinRange3D(10);
			//Collections.sort(vector);
			//将数组中的注码前加"0"
			vector = LotteryAlgorithmUtil.getStringArrayFromIntArray(vector);
			//转成赠送明细里的数组
			newzhuma += LotteryAlgorithmUtil.joinStringArrayWithComma(vector) + "<br/>";
			zhuma += "00" + CommonUtil.getNewString(beishu) + LotteryAlgorithmUtil.joinStringArray(vector) + "^";
		}
		logger.info(" zhuma:"+zhuma);
		zhuma = zhuma.substring(4);
		if(zhuma.endsWith("^")) zhuma=zhuma.substring(0,zhuma.length()-1);
		// 金额
		Integer amount = zhushuInt*beishuInt*LotteryAlgorithmUtil.priceOfCaipiao;
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("zhushu", zhushuInt);
		request.setAttribute("beishu", beishu);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		request.setAttribute("term", term);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", String.valueOf(Integer.parseInt(addNumber)));
		}
		logger.info(" zhuma:"+zhuma+" zhushu:"+(zhushuInt * beishuInt) +
				" beishu:"+beishu+" newzhuma"+newzhuma+" amount:"+amount+" term:"+term);
		request.setAttribute("pageType", "autoSelection");
		return "wap/3D/3DSingleDetail";
	}
	/**
	 * 随即投注  注码已经生成
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/autoDetail.jspx",method=RequestMethod.POST)
	public String autoDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取当前期号
		String newzhuma = "";
		String zhuma = "";
		String term = CommonUtil.getTerm("F47103");
		String zhushu = "1";
		String beishu = "1";
		String addNumber = "";
	    newzhuma = request.getParameter("newzhuma");
	    zhuma = request.getParameter("betNo");
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber==null) {
				addNumber = "1";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		zhushu = CommonUtil.QJToBJChange(zhushu);
		beishu = CommonUtil.QJToBJChange(beishu);
		int zhushuInt = Integer.parseInt(zhushu);
		int beishuInt = Integer.parseInt(beishu);
		// 金额
		Integer amount = zhushuInt*beishuInt*LotteryAlgorithmUtil.priceOfCaipiao;
		request.setAttribute("zhuma", zhuma);
		request.setAttribute("zhushu", zhushuInt * beishuInt);
		request.setAttribute("beishu", beishu);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("amount", amount);
		request.setAttribute("term", term);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			request.setAttribute("addNumber", String.valueOf(Integer.parseInt(addNumber)));
		}
		logger.info(" zhuma:"+zhuma+" zhushu:"+(zhushuInt * beishuInt) +
				" beishu:"+beishu+" newzhuma"+newzhuma+" amount:"+amount+" term:"+term);
		request.setAttribute("pageType", "autoSelection");
		return "wap/3D/3DSingleDetail";
	}
	/**
	 * 3D直选单式投注信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/directSelectionSingleDetail.jspx",method=RequestMethod.POST)
	public String directSelectionSingleDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		//获取当前期号
		String term = CommonUtil.getTerm("F47103");
		// 获取请求参数
		String hundreds_No = request.getParameter("hundreds_No");// 百位数
		String tens_No = request.getParameter("tens_No");// 十位数
		String units_No = request.getParameter("units_No");// 个位数
		String beishu_No = request.getParameter("beishu_No");// 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = request.getParameter("addNumber");
		String parameter = "hundreds_No="+hundreds_No+"&tens_No="+tens_No+"&units_No="+units_No+"&addNumber="+addNumber
				+"&beishu_No="+beishu_No+"&pageType="+pageType;
		request.getSession().setAttribute("parameter", parameter);
		hundreds_No = CommonUtil.QJToBJChange(hundreds_No);
		tens_No = CommonUtil.QJToBJChange(tens_No);
		units_No = CommonUtil.QJToBJChange(units_No);
		beishu_No = CommonUtil.QJToBJChange(beishu_No);
		//将注码前加"0"
		String hundreds_No1 = LotteryAlgorithmUtil.addZero3D(hundreds_No);
		String tens_No1 = LotteryAlgorithmUtil.addZero3D(tens_No);
		String units_No1 = LotteryAlgorithmUtil.addZero3D(units_No);
		logger.info(" hundreds_No:"+hundreds_No1 + 
				" tens_No:"+tens_No1+" units_No:"+units_No1);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			logger.info(" addNumber:"+addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/3D/3DDirectSingle";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		logger.info(" hundreds_No:"+hundreds_No+" tens_No:"+tens_No+" units_No:"+units_No+" beishu_No:"+beishu_No+" pageType:"+pageType);
		if (VerificationAlgorithmUtil.isStringFilter(hundreds_No)||VerificationAlgorithmUtil.isStringFilter(tens_No)||VerificationAlgorithmUtil.isStringFilter(units_No)||VerificationAlgorithmUtil.isStringFilter(beishu_No)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/3D/3DDirectSingle";
		}
		Map map = new HashMap();
		map.put("hundreds_No", hundreds_No1);
		map.put("tens_No", tens_No1);
		map.put("units_No", units_No1);
		map.put("beishu", beishu_No);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil.verify3DDirectSelectionSingle(map);
		logger.info(" message:"+errorMessage);
		if (errorMessage!=null) {
			request.setAttribute("hundreds_No", hundreds_No);
			request.setAttribute("tens_No", tens_No);
			request.setAttribute("units_No", units_No);
			request.setAttribute("beishu_No", beishu_No);
			request.setAttribute("err_msg", errorMessage);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/3D/3DDirectSingle";
		} else {
			String beishuStr = "";
			if (beishu_No.trim().equals("")) {
				beishuStr = "1";
			} else {
				beishuStr =beishu_No;
			}
			// 合成字符串
			String newzhuma="百位:"+hundreds_No+"<br/>十位:"+tens_No+"<br/>个位:"+units_No;
			String zhuma="";
			if(pageType=="ZXFS"||pageType.equals("ZXFS"))
			{
				Vector hundreds_NoVector = LotteryAlgorithmUtil.getStringArrayFromString(hundreds_No1);
				Collections.sort(hundreds_NoVector);
				Vector tens_NoVector = LotteryAlgorithmUtil.getStringArrayFromString(tens_No1);
				Collections.sort(tens_NoVector);
				Vector units_NoVector = LotteryAlgorithmUtil.getStringArrayFromString(units_No1);
				Collections.sort(units_NoVector);
				zhuma = "20" + CommonUtil.getNewString(beishuStr)+ CommonUtil.getNewString(String.valueOf(hundreds_No.length()))
				         + LotteryAlgorithmUtil.joinStringArray(hundreds_NoVector) + "^"
				         + CommonUtil.getNewString(String.valueOf(tens_No.length())) + LotteryAlgorithmUtil.joinStringArray(tens_NoVector) + "^"
				         + CommonUtil.getNewString(String.valueOf(units_No.length())) + LotteryAlgorithmUtil.joinStringArray(units_NoVector) + "^";
			}
			else if(pageType=="directSelectionSingle"||pageType.equals("directSelectionSingle"))
			{
				//1512-F47103-00-01-0001030308^
				zhuma = "00"+CommonUtil.getNewString(beishuStr)
				+LotteryAlgorithmUtil.getStringFromString3D(hundreds_No)+LotteryAlgorithmUtil.getStringFromString3D(tens_No)
				+LotteryAlgorithmUtil.getStringFromString3D(units_No)+"^";
			}
			zhuma = zhuma.substring(4);
			if(zhuma.endsWith("^")) zhuma=zhuma.substring(0,zhuma.length()-1);
			// 注数
			Integer zhushu = hundreds_No.length()*tens_No.length()*units_No.length();
			// 倍数
			int beishu = 1;
			if(!beishu_No.equals(""))
				{beishu = Integer.parseInt(beishu_No);}
			else{
				beishu=1;
			}
			// 金额
			Integer amount = zhushu*beishu*LotteryAlgorithmUtil.priceOfCaipiao;
			//将参数设置在request中
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("zhushu", zhushu * beishu);
			request.setAttribute("beishu", beishu_No);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("amount", amount);
			request.setAttribute("term", term);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			
			logger.info(" zhushu:"+(zhushu * beishu)+" pageType:"+pageType+
					" beishu:"+beishu_No+" addNumber:"+addNumber+" amount:"+amount+" newzhuma:"+newzhuma+"zhuma:"+zhuma+" term"+term);
			return "wap/3D/3DSingleDetail";
		}

	}

	/**
	 * 3D组3单式投注信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/group3SingleDetail.jspx",method=RequestMethod.POST)
	public String group3SingleDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获取当前期号
		String term = CommonUtil.getTerm("F47103");
		// 获取请求参数
		String zhuma1 = request.getParameter("zhuma1");// 出现1次的注码
		String zhuma2 = request.getParameter("zhuma2");// 出现2次的注码
		String beishuStr = request.getParameter("beishu");// 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:"+addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhuma1="+zhuma1+"&zhuma2="+zhuma2+"&beishu="+beishuStr+"&addNumber="+addNumber+"&pageType="+pageType;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" zhuma1:"+zhuma1+" zhuma2:"+zhuma2+" beishuStr:"+beishuStr+" pageType:"+pageType);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma1)||VerificationAlgorithmUtil.isStringFilter(zhuma2)||VerificationAlgorithmUtil.isStringFilter(beishuStr)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhuma1 = CommonUtil.QJToBJChange(zhuma1);
		zhuma2 =  CommonUtil.QJToBJChange(zhuma2);
		beishuStr =  CommonUtil.QJToBJChange(beishuStr);
		//将注码加"0"
		String zhuma11 = LotteryAlgorithmUtil.addZero3D(zhuma1);
		String zhuma22 = LotteryAlgorithmUtil.addZero3D(zhuma2);
		logger.info(" zhuma11:"+zhuma11+" zhuma22:"+zhuma22);
		//验证数据的合法性
		Map map = new HashMap();
		map.put("zhuma1", zhuma11);
		map.put("zhuma2", zhuma22);
		map.put("beishu", beishuStr);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil.verify3DGroup3Single(map);
		logger.info(" message:"+errorMessage);
		if (errorMessage!=null) {
			request.setAttribute("err_msg", errorMessage);
			request.setAttribute("zhuma1", zhuma1);
			request.setAttribute("zhuma2", zhuma2);
			request.setAttribute("beishuStr", beishuStr);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/3D/3DGroup3Single";
		} else {
			// 将注码1,注码2合成字符串
			String zhumaStr = zhuma11 + zhuma22 + zhuma22;
			zhumaStr = CommonUtil.getSortString(zhumaStr);
			// 将注码转成数组
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhumaStr);
			//对注码数组进行排序
			Collections.sort(vector);
			//将不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString3D(zhuma1 + zhuma2 + zhuma2);
			//对数组进行排序
			Collections.sort(vector2);
			// 将数组转成带","的注码
			String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector2);
			// 注数
			Integer zhushu = 1;
			// 倍数
			Integer beishu = 0;
			if (beishuStr.trim().equals("")) {
				beishu = 1;
			} else {
				beishu = Integer.parseInt(beishuStr); 
			}
			// 金额
			Integer amount = zhushu*beishu*LotteryAlgorithmUtil.priceOfCaipiao;
			//将参数设置在request中
			request.setAttribute("zhuma", zhumaStr);
			request.setAttribute("zhushu", zhushu * beishu);
			request.setAttribute("beishu", beishuStr);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("amount", amount);
			request.setAttribute("term", term);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			
			logger.info(" zhushu:"+(zhushu * beishu)+" pageType:"+pageType+
					" beishu:"+beishuStr+" addNumber:"+addNumber+" amount:"+amount+" zhuma:"+zhumaStr+"newzhuma:"+newzhuma+" term"+term);
			return "wap/3D/3DSingleDetail";
		}

	}

	/**
	 * 3D组6投注信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/group6SingleDetail.jspx",method=RequestMethod.POST)
	public String group6SingleDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//获取当前期号
		String term = CommonUtil.getTerm("F47103");
		String zhuma = request.getParameter("zhuma"); // 不带","注码
		String beishuStr = request.getParameter("beishu"); // 倍数
		String pageType = request.getParameter("pageType");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			logger.info(" addNumber:"+addNumber);
			if (VerificationAlgorithmUtil.isStringFilter(addNumber)) {
				request.setAttribute("err_msg", "输入框中不能输入特殊字符");
				return "wap/BetSuccess";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "zhuma="+zhuma+"&beishu="+beishuStr+"&pageType="+pageType+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" zhuma:"+zhuma+" beishuStr:"+beishuStr+" term:"+term+" pageType:"+pageType);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma)||VerificationAlgorithmUtil.isStringFilter(beishuStr)) {
			request.setAttribute("err_msg", "输入框中不能输入特殊字符");
			return "wap/BetSuccess";
		}
		zhuma = CommonUtil.QJToBJChange(zhuma);
		beishuStr =  CommonUtil.QJToBJChange(beishuStr);
		//将注码加"0"
		String zhuma1 = LotteryAlgorithmUtil.addZero3D(zhuma);
		//验证注码的合法性
		Map map = new HashMap();
		map.put("zhuma", zhuma1);
		map.put("beishu", beishuStr);
		if (rbint.getString("addNumberSwitch").equals("1")) {
			map.put("addNumber", addNumber);
		}
		String errorMessage = VerificationAlgorithmUtil.verify3DGroup6Single(map);
		logger.info(" message:"+errorMessage);
		if (errorMessage!=null) {
			request.setAttribute("err_msg", errorMessage);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("beishuStr", beishuStr);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/3D/3DGroup6Single";
		} else {
			zhuma1 = CommonUtil.getSortString(zhuma1);
			// 将注码转成数组
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
			//对注码数组进行排序
			Collections.sort(vector);
			//将不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString3D(zhuma);
			//对数码进行排序
			Collections.sort(vector2);
			// 将注码数组转成带","的新注码
			String newzhuma = LotteryAlgorithmUtil.joinStringArrayWithComma(vector2);
			// 组6单式的注数
			Integer zhushu = 1;
			// 将倍数字符串转成Integer
			Integer beishu = 0;
			if (beishuStr.trim().equals("")) {
				beishu = 1;
			} else {
				beishu = Integer.parseInt(beishuStr); 
			}
			// 计算金额
			Integer amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
			//将参数设置在request中
			request.setAttribute("beishu", beishuStr);
			request.setAttribute("zhushu", zhushu * beishu);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma1);
			request.setAttribute("term", term);
			request.setAttribute("pageType", pageType);
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			
			logger.info(" zhushu:"+(zhushu * beishu)+
					" beishu:"+beishuStr+" addNumber:"+addNumber+" amount:"+amount+" newzhuma:"+newzhuma+"zhuma:"+zhuma1+" term"+term);
			return "wap/3D/3DSingleDetail";
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
	public String bet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("F47103");
		String zhushu,certID,beishu,amount,zhuma,pageType,addNumber="",token,sellWay="",channel="",lotNo="";
		lotNo="F47103";
		channel = WapUtil.getChannelId(request);
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		//用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则 从request.getAttribute 中获取 与表单同名同类型的obj
		if(request.getAttribute("outFormData")==null||request.getAttribute("outFormData").equals("")){
			//获取页面参数
			zhushu = request.getParameter("zhushu");// 注数
			zhuma = request.getParameter("zhuma");// 注码,不带","
			beishu = request.getParameter("beishu"); // 倍数
			amount = request.getParameter("amount"); // 金额
			pageType = request.getParameter("pageType");
			token = request.getParameter("token");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber");
			}
		}else{//获取request.getAttribute中的存储
			String[] zhushus = request.getAttribute("zhushu")==null?null:(String[])request.getAttribute("zhushu");
			String[] beishus = request.getAttribute("beishu")==null?null:(String[])request.getAttribute("beishu");
			String[] zhumas = request.getAttribute("zhuma")==null?null:(String[])request.getAttribute("zhuma");
			String[] amounts = request.getAttribute("amount")==null?null:(String[])request.getAttribute("amount");
			String[] pageTypes = request.getAttribute("pageType")==null?null:(String[])request.getAttribute("pageType");
			String[] tokens = request.getAttribute("token")==null?null:(String[])request.getAttribute("token");
			String[] addNumbers=null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber")==null?null:(String[])request.getAttribute("addNumber");
			}
			//获取输入页面的参数
			zhushu = zhushus==null||zhushus[0].equals("")?"0":zhushus[0]; //注数
			beishu = beishus==null||beishus[0].equals("")?"0":beishus[0]; //倍数
			zhuma = zhumas==null||zhumas[0].equals("")?"0":zhumas[0];   //注码
			amount = amounts==null||amounts[0].equals("")?"0":amounts[0];	//金额 
			pageType = pageTypes==null||pageTypes[0].equals("")?"0":pageTypes[0];
			token = tokens==null||tokens[0].equals("")?"0":tokens[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers==null||addNumbers[0].equals("")?"0":addNumbers[0];
			}
		}
	
		//判断是否重复提交
		String isExecute = (String)request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			//执行正常的投注

		logger.info("userno:"+userno+" zhushu:"+zhushu+" beishu:"+beishu+" zhuma:"+zhuma+
				" addNumber:"+addNumber+" amount:"+amount+" pageType:"+pageType);
		//取得组3单式的注码
		String bet_code = "";
		String wanfa="";
		logger.info("userno:"+userno+" zhuma:"+zhuma);
		if (pageType.trim().equals("directSelectionSingle")||pageType.trim().equals("autoSelection")) {
			bet_code = CommonUtil.generate3DZhuma("F47103", CommonUtil.M_ZXDS, beishu,zhuma,zhushu);
			wanfa="M_ZXDS";
		}
		if (pageType.trim().equals("ZXFS")||pageType.trim().equals("ZXFS")) {
			wanfa="M_WXTZ";
			bet_code = CommonUtil.generate3DZhuma("F47103", CommonUtil.M_WXTZ, beishu,zhuma,zhushu);
		}
		if (pageType.trim().equals("group3Single")) {
			wanfa="M_Z3DS";
			bet_code = CommonUtil.generate3DZhuma("F47103", CommonUtil.M_Z3DS, beishu,zhuma, zhushu);
		}
		if (pageType.trim().equals("group6Single")) {
			wanfa="M_Z6DS";
			bet_code = CommonUtil.generate3DZhuma("F47103", CommonUtil.M_Z6DS, beishu,zhuma, zhushu);
		}
		logger.info("userno"+userno+" bet_code"+bet_code);
		//取得投注的返回码
		if (buyways.equals("hemai")) {
//			boolean flag = CommonUtil.getBalanceResult(userno, amount);
//			if(flag){
//				request.setAttribute("message","您的余额不足，请先充值！");
//				return "wap/charge/chargeIndex";
//			}
			CommonUtil.getRandom(request);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amt", amount);
			request.setAttribute("newbetcode", bet_code);
			request.setAttribute("lotno", lotNo);
			return "wap/hemai/buyhemai";
		} else if (buyways.equals("presented")) {
//			boolean flag = CommonUtil.getBalanceResult(userno, amount);
//			if(flag){
//				request.setAttribute("message","您的余额不足，请先充值！");
//				return "wap/charge/chargeIndex";
//			}
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
			String ttssBet=JsonToJrtLotUtil.sendToBet(userno, lotNo, term, bet_code, beishu, "2", wanfa, amount, addNumber, channel);
			request.setAttribute("err_msg", ttssBet);
		}
		
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
		String type = request.getParameter("pageType");
		String term = request.getParameter("term");
		String beishu = request.getParameter("beishu");
		String autoMethod = request.getParameter("autoMethod");
		String zhuma = request.getParameter("zhuma");
		String addNumber = "";
		if (rbint.getString("addNumberSwitch").equals("1")) {
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
			if (rbint.getString("addNumberSwitch").equals("1")) {
				request.setAttribute("addNumber", addNumber);
			}
			return "wap/3D/3DmodifyAutoSelection";
		}
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
			return "wap/3D/3DmodifyAutoSelection";
		}
		// 验证注码格式
			for (int i = 0; i < zhushuStr.length; i++) {
				String err_msg = VerificationAlgorithmUtil.check3DCode(zhushuStr[i]);
				if (!"".equals(err_msg)){
					request.setAttribute("err_msg", err_msg);
					request.setAttribute("zhuma", zhuma);
					request.setAttribute("beishu", beishu);
					if (rbint.getString("addNumberSwitch").equals("1")) {
						request.setAttribute("addNumber", addNumber);
					}
					return "wap/3D/3DmodifyAutoSelection";
				}
			}
			if (amt > 100000) {
				logger.info("单次投注金额不能超过10万元，amt" + amt);
				request.setAttribute("err_msg", MessageUtil.DBMA_showSelfBetDetails_AmountError);
				request.setAttribute("zhuma", zhuma);
				request.setAttribute("beishu", beishu);
				if (rbint.getString("addNumberSwitch").equals("1")) {
					request.setAttribute("addNumber", "1");
				}
				return "wap/3D/3DmodifyAutoSelection";
			}

		// 生成显示格式注码
		newzhuma = zhuma;
		String zhumaStr = "";
		for (int i = 0; i < zhushuStr.length; i++) {
			if(i == 0){
				zhumaStr = zhushuStr[i].replace(",", "")+"^";
			}else if(i==zhushuStr.length-1){
				zhumaStr = zhumaStr+"0001"+zhushuStr[i].replace(",", "");
			}else{
				zhumaStr = zhumaStr+"0001"+zhushuStr[i].replace(",", "")+"^";
			}
			
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
		return "wap/3D/3DSingleDetail";
	}
}

