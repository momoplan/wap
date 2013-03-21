package com.ruyicai.wap.controller;

import static com.ruyicai.wap.Global.rbint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buybal.lot.lottype.ShishicaiUtil;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/shishicai")
@Controller
public class CQShiShiCaiAction{
	
	private static final Logger logger = Logger.getLogger(CQShiShiCaiAction.class);
 
	/**
	 * 时时彩一星投注详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 投注页面
	 * @throws Exception
	 */
	@RequestMapping(value="/SSCOneStarBetDetail.jspx",method=RequestMethod.POST)
	public String SSCOneStarBetDetail(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取参数
		 String beishu,amount,unit,type,addNumber="";
		 unit = request.getParameter("unit"); //个位
	     beishu = request.getParameter("beishu"); //倍数
	     type = request.getParameter("type");
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber"); // 追号
			if (addNumber == null) {
				addNumber = "1";
			}
		}
		String parameter = "unit="+unit+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" unit:"+unit+" addNumber:"+addNumber+
				" beishu:"+beishu);
		//获取当前期号
		String term = CommonUtil.getTerm("T01007");
		// 验证是否是全角，如果是全角。 那么转换为半角
		addNumber = CommonUtil.QJToBJChange(addNumber);
        unit = CommonUtil.QJToBJChange(unit);
        beishu = CommonUtil.QJToBJChange(beishu);
        if("".equals(unit)||unit == null){
        	request.setAttribute("err_msg", "个位不可以为空");
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscOneStar";
        }else if("".equals(beishu)||beishu == null){
        	request.setAttribute("err_msg", "倍数不可以为空");
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscOneStar";
		}else if("".equals(addNumber)||addNumber == null){
			request.setAttribute("err_msg", "追号不可以为空");
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscOneStar";
		}
        //验证追号， 注码，倍数是否是特殊字符
        logger.info("Numbers:"+unit+" beishu:"+beishu);
        //检查注码的合法性
        if(!ShishicaiUtil.checkSing(unit,beishu,addNumber).equals("pass")){
        	request.setAttribute("err_msg", ShishicaiUtil.checkSing(unit,beishu,addNumber));
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber", addNumber);
        	return "wap/cqshishicai/sscOneStar";
        }
        //转换注码格式
         String zhuma = ShishicaiUtil.zhumaSingle(unit);
         String newzhuma = ShishicaiUtil.getNewZhuma(zhuma);
        //计算注数
        long zhushu = ShishicaiUtil.zhushuSingle(unit);
        if(zhushu <= 0){
        	logger.info("注数不正确，zhushu:"+zhushu+"unit"+unit);
        	request.setAttribute("err_msg", "您输入的注码不正确，请您重新输入");
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber", addNumber);
        	return "wap/cqshishicai/sscOneStar";
        }
       //计算金额
        long amt = zhushu*2*Integer.parseInt(beishu);
        //单次金额不能超过2万元
        if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber", addNumber);
        	return "wap/cqshishicai/sscOneStar";
        }
        amount = amt+"";
        //计算新的注数 ，新注数= 注数*倍数
        zhushu = zhushu*Integer.parseInt(beishu);
        logger.info("投注的金额和新的注数"+amt+" "+zhushu);
        //把所有获取到的值  都放到request
        request.setAttribute("zhuma", zhuma);
        request.setAttribute("newzhuma", newzhuma);
    	request.setAttribute("beishu", beishu);
    	request.setAttribute("addNumber", addNumber);
    	request.setAttribute("zhushu", zhushu);
    	request.setAttribute("amt", amount);
    	request.setAttribute("term", term);
    	request.setAttribute("type", type);
    	request.getSession().setAttribute("unit", unit);
		return "wap/cqshishicai/sscBetDetail";
	}
	
	/**
	 * 时时彩普通二星玩法业务操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/generalTwoStar.jspx",method=RequestMethod.POST)
	public String generalTwoStar(HttpServletRequest request, HttpServletResponse response) {

		// 获取参数
		String unit = request.getParameter("unit"); // 个位
		String tens = request.getParameter("tens"); // 十位
		String beishu = request.getParameter("beishu"); // 倍数
		String addNumber =""; // 追号
		String type = request.getParameter("type"); // 类型
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null) {
				addNumber = "1";
			}
		}
		String parameter = "unit="+unit+"&tens="+tens+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("tens:" + tens + "unit:"
				+ unit + "addNumber:" + addNumber + "beishu:" + beishu
				+ "type:" + type);
		// 获取当前期号
		String term = CommonUtil.getTerm("T01007");
		// 验证是否是全角，如果是全角。 那么转换为半角

		unit = CommonUtil.QJToBJChange(unit);
		tens = CommonUtil.QJToBJChange(tens);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		if("".equals(tens)||tens == null){
			request.setAttribute("err_msg", "十位不可以为空");
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscTowStar";
		}else if("".equals(unit)||unit == null){
	        	request.setAttribute("err_msg", "个位不可以为空");
	        	request.setAttribute("tens", tens);
				request.setAttribute("unit", unit);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/sscTowStar";
	        }else if("".equals(beishu)||beishu == null){
	        	request.setAttribute("err_msg", "倍数不可以为空");
	        	request.setAttribute("tens", tens);
	        	request.setAttribute("unit", unit);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/sscTowStar";
			}else if("".equals(addNumber)||addNumber == null){
				request.setAttribute("err_msg", "追号不可以为空");
				request.setAttribute("tens", tens);
				request.setAttribute("unit", unit);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/sscTowStar";
			}
		
		// 检查注码和倍数的合法性
		String[] zhuma = { unit, tens };
		if (!ShishicaiUtil.checkDouble(zhuma, beishu,addNumber).equals("pass")) {
			request.setAttribute("err_msg", ShishicaiUtil.checkDouble(zhuma,
					beishu,addNumber));
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/sscTowStar";
		}
		// 转换格式
		String zhumaStr = ShishicaiUtil.zhumaDouble(zhuma);
		String newzhuma = ShishicaiUtil.getNewZhuma(zhumaStr);
		// 计算注数
		long zhushuInt = ShishicaiUtil.zhushuDouble(zhuma);
		String zhushu = zhushuInt + "";
		if (zhushuInt <= 0) {
			logger.info("注数不正确，zhushu:" + zhushu + "unit" + unit + "tens"
					+ tens);
			request.setAttribute("err_msg", "您输入的注码不正确，请您重新输入");
			request.setAttribute("unit", unit);
			request.setAttribute("tens", tens);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/sscTowStar";
		}
		// 计算金额
		long amt = zhushuInt * 2 * Integer.parseInt(beishu);
		//单次金额不能超过2万元
        if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("unit", unit);
			request.setAttribute("tens", tens);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/sscTowStar";
        }
		String amount = amt + ""; 
		// 计算新的注数 ，新注数= 注数*倍数
		zhushuInt = zhushuInt * Integer.parseInt(beishu);
		request.setAttribute("zhuma", zhumaStr);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("type", type);
		request.setAttribute("amt", amount);
		request.setAttribute("zhushu", zhushuInt);
		return "wap/cqshishicai/sscBetDetail";
	}

	/**
	 * 时时彩二星组选玩法业务操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sgTwoStar.jspx",method=RequestMethod.POST)
	public String sgTwoStar(HttpServletRequest request, HttpServletResponse response) {

		// 获取参数
		String number = request.getParameter("number");
		String beishu = request.getParameter("beishu"); // 倍数
		String addNumber = ""; // 追号
		String type = request.getParameter("type"); // 类型
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null) {
				addNumber = "1";
			}
		}
		String parameter = "number="+number+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("addNumber:" + addNumber
				+ "beishu:" + beishu + "type:" + type);
		// 获取当前期号
		String term = CommonUtil.getTerm("T01007");
		// 验证是否是全角，如果是全角。 那么转换为半角
		number = CommonUtil.QJToBJChange(number);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		//验证是否为空
		if("".equals(number)||number == null){
			request.setAttribute("err_msg", "号码不可以为空");
			request.setAttribute("number", number);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sgTowStar";
	     }else if("".equals(beishu)||beishu == null){
	        	request.setAttribute("err_msg", "倍数不可以为空");
	        	request.setAttribute("number", number);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/sgTowStar";
		 }else if("".equals(addNumber)||addNumber == null){
				request.setAttribute("err_msg", "追号不可以为空");
				request.setAttribute("number", number);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/sgTowStar";
			}
		
		// 验证追号， 注码，倍数是否是特殊字符
		logger.info("number:" + number
				+ "beishu:" + beishu + "addNumber:" + addNumber);
		// 检查注码和倍数的合法性(如果是两位的注码就过不去)
		if (!ShishicaiUtil.checkDoubleGroup(number, beishu,addNumber).equals("pass")) {
			request.setAttribute("err_msg", ShishicaiUtil.checkDoubleGroup(
					number, beishu,addNumber));
			request.setAttribute("number", number);
			request.setAttribute("beishu", beishu);
			// request.setAttribute("type", type);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/sgTowStar";
		}
		// 转换格式
		String zhumaStr = ShishicaiUtil.zhumaDoubleGroup(number);
		String newzhuma = ShishicaiUtil.getNewZhuma(zhumaStr);
		// 计算注数
		long zhushuInt = ShishicaiUtil.zhushuDoubleGroup(number);
		String zhushu = zhushuInt + "";
		if (zhushuInt <= 0) {
			logger.info("注数不正确，zhushu:" + zhushu + "number" + number);
			request.setAttribute("err_msg", "您输入的注码不正确，请您重新输入");
			request.setAttribute("number", number);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/sgTowStar";
		}
		// 计算金额
		long amt = zhushuInt * 2 * Integer.parseInt(beishu);
		//单次金额不能超过2万元
        if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("number", number);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/sgTowStar";
        }
		String amount = amt + ""; 
		// 计算新的注数 ，新注数= 注数*倍数
		zhushuInt = zhushuInt * Integer.parseInt(beishu);

		request.setAttribute("zhuma", zhumaStr);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("type", type);
		request.setAttribute("amt", amount);
		request.setAttribute("zhushu", zhushuInt);
		return "wap/cqshishicai/sscBetDetail";
	}

	/**
	 * 时时彩二星和值玩法业务操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/hzTwoStar.jspx",method=RequestMethod.POST)
	public String hzTwoStar(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		String hezhi = request.getParameter("hezhi");
		String beishu = request.getParameter("beishu"); // 倍数
		String addNumber = ""; // 追号
		String type = request.getParameter("type"); // 类型
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null) {
				addNumber = "1";
			}
		}
		String parameter = "hezhi="+hezhi+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("hezhi:" + hezhi
				+ "addNumber:" + addNumber + "beishu:" + beishu + "type:"
				+ type);
		// 获取当前期号
		String term = CommonUtil.getTerm("T01007");
		// 验证是否是全角，如果是全角。 那么转换为半角

		hezhi = CommonUtil.QJToBJChange(hezhi);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		if("".equals(hezhi)||hezhi == null){
			request.setAttribute("err_msg", "号码不可以为空");
			request.setAttribute("hezhi", hezhi);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/hezhiTowStar";
	     }else if("".equals(beishu)||beishu == null){
	        	request.setAttribute("err_msg", "倍数不可以为空");
	        	request.setAttribute("hezhi", hezhi);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/hezhiTowStar";
		 }else if("".equals(addNumber)||addNumber == null){
				request.setAttribute("err_msg", "追号不可以为空");
				request.setAttribute("hezhi", hezhi);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/hezhiTowStar";
			}
		// 验证追号， 注码，倍数是否是特殊字符(输入19的时候就报错了)
		logger.info("hezhi:" + hezhi + "beishu:"
				+ beishu + "addNumber:" + addNumber);
		// 检查注码和倍数的合法性
		if (!ShishicaiUtil.checkDoubleHezhi(hezhi, beishu,addNumber).equals("pass")) {
			request.setAttribute("err_msg", ShishicaiUtil.checkDoubleHezhi(
					hezhi, beishu,addNumber));
			request.setAttribute("hezhi", hezhi);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/hezhiTowStar";
		}
		// 转换格式
		String zhumaStr = ShishicaiUtil.zhumaDoubleHezhi(hezhi);
		String newzhuma = ShishicaiUtil.getNewZhuma(zhumaStr);
		// 计算注数
		long zhushuInt = ShishicaiUtil.zhushuDoubleHezhi(hezhi);
		String zhushu = zhushuInt + "";
		if (zhushuInt <= 0) {
			logger.info("注数不正确，zhushu:" + zhushu + "hezhi" + hezhi);
			request.setAttribute("err_msg", "您输入的注码不正确，请您重新输入");
			request.setAttribute("hezhi", hezhi);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/hezhiTowStar";
		}
		// 计算金额
		long amt = zhushuInt * 2 * Integer.parseInt(beishu);
		String amount = amt + ""; 
		//单次金额不能超过2万元
        if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("hezhi", hezhi);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/hezhiTowStar";
        }
	
		// 计算新的注数 ，新注数= 注数*倍数
		zhushuInt = zhushuInt * Integer.parseInt(beishu);

		request.setAttribute("zhuma", zhumaStr);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("type", type);
		request.setAttribute("amt", amount);
		request.setAttribute("zhushu", zhushuInt);
		return "wap/cqshishicai/sscBetDetail";
	}

	/**
	 * 时时彩五星小大单双玩法业务操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/xddsFive.jspx",method=RequestMethod.POST)
	public String xddsFive(HttpServletRequest request, HttpServletResponse response) {
	
		// 获取参数
		String unit = request.getParameter("unit"); // 个位
		String tens = request.getParameter("tens"); // 十位
		String beishu = request.getParameter("beishu"); // 倍数
		String addNumber = ""; // 追号
		String type = request.getParameter("type"); // 类型
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null) {
				addNumber = "1";
			}
		}
		String parameter = "unit="+unit+"&tens="+tens+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("tens:" + tens + "unit:"
				+ unit + "addNumber:" + addNumber + "beishu:" + beishu
				+ "type:" + type);
		// 获取当前期号
		String term = CommonUtil.getTerm("T01007");
		// 验证是否是全角，如果是全角。 那么转换为半角

		unit = CommonUtil.QJToBJChange(unit);
		tens = CommonUtil.QJToBJChange(tens);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
        if(beishu == null || "".equals(beishu)){
        	request.setAttribute("err_msg", "倍数不能为空");
        	request.setAttribute("beishu", beishu);
    		request.setAttribute("addNumber", addNumber);
    		return "wap/cqshishicai/xddsFiveStar";
        }else if(addNumber == null ||"".equals(addNumber)){
        	request.setAttribute("err_msg", "追号不能为空");
	    	request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/xddsFiveStar";
        }
		// 验证追号， 注码，倍数是否是特殊字符
		logger.info("tens:" + tens + "unit:"
				+ unit + "beishu:" + beishu + "addNumber:" + addNumber);
		
		// 检查注码和倍数的合法性
		String[] zhuma = { unit, tens };
		if (!ShishicaiUtil.checkDouble(zhuma, beishu,addNumber).equals("pass")) {
			request.setAttribute("err_msg", ShishicaiUtil.checkDouble(zhuma,
					beishu,addNumber));
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/xddsFiveStar";
		}
		// 转换格式
		String zhumaStr = ShishicaiUtil.zhumaDxds(zhuma);
		String newzhuma = ShishicaiUtil.getNewZhuma(zhumaStr);
		newzhuma = ShishicaiUtil.getSplitZhuma(zhumaStr, "ON");
		// 计算注数
		int zhushuInt = 1;
		String zhushu = zhushuInt + "";
		if (zhushuInt <= 0) {
			logger.info("注数不正确，zhushu:" + zhushu + "unit" + unit + "tens"
					+ tens);
			request.setAttribute("err_msg", "您输入的注码不正确，请您重新输入");
			request.setAttribute("unit", unit);
			request.setAttribute("tens", tens);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/xddsFiveStar";
		}
		// 计算金额
		long amt = zhushuInt * 2 * Integer.parseInt(beishu);
		String amount = amt + ""; 
		//单次金额不能超过2万元
		if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("unit", unit);
			request.setAttribute("tens", tens);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber",addNumber);
			return "wap/cqshishicai/xddsFiveStar";
        }
		
		// 计算新的注数 ，新注数= 注数*倍数
		zhushuInt = zhushuInt * Integer.parseInt(beishu);

		request.setAttribute("zhuma", zhumaStr);
		request.setAttribute("newzhuma", newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("type", type);
		request.setAttribute("amt", amount);
		request.setAttribute("zhushu", zhushuInt);
		return "wap/cqshishicai/sscBetDetail";
	}
	
	
	
	/**
	 * CQShiShiCaiAction 时时彩三星业务操作
	 * @author  
	 * (C)版权由北京金软瑞彩科技发展有限公司所有
	 * 网址：www.ruyicai.com
	 * 创建者：张博
	 * 创建日期：2011-2-25
	 * 修改记录：无
	 */
	@RequestMapping(value="/sscThreeStar.jspx",method=RequestMethod.POST)
	public String sscThreeStar(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//获取参数
		String hundred = request.getParameter("hundred");    //百位
		String tens = request.getParameter("tens");			 //十位
		String unit = request.getParameter("unit");			 //个位
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type"); // 类型
		String addNumber="";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null) {
				addNumber = "1";
			}
			logger.info(" addNumber:" + addNumber);
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "hundred="+hundred+"&tens="+tens+"&unit="+unit+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("hundred:"+hundred+" tens:"+tens+"unit:"+unit+"beishu:"+beishu);
		//获取当前期号
		String term = CommonUtil.getTerm("T01007");
		//验证是否全角，如果是全角转换成半角
		hundred = CommonUtil.QJToBJChange(hundred);
		tens = CommonUtil.QJToBJChange(tens);
	    unit = CommonUtil.QJToBJChange(unit);
	    beishu = CommonUtil.QJToBJChange(beishu);
	  if("".equals(hundred)||hundred == null){
		request.setAttribute("err_msg", "百位不可以为空");
		request.setAttribute("hundred", hundred);
		request.setAttribute("tens", tens);
		request.setAttribute("unit", unit);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		return "wap/cqshishicai/sscThreeStar";
	}else  if("".equals(tens)||tens == null){
			request.setAttribute("err_msg", "十位不可以为空");
			request.setAttribute("hundred", hundred);
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscThreeStar";
		}else if("".equals(unit)||unit == null){
	        	request.setAttribute("err_msg", "个位不可以为空");
	        	request.setAttribute("hundred", hundred);
	        	request.setAttribute("tens", tens);
				request.setAttribute("unit", unit);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/sscThreeStar";
	        }else if("".equals(beishu)||beishu == null){
	        	request.setAttribute("err_msg", "倍数不可以为空");
	        	request.setAttribute("hundred", hundred);
	        	request.setAttribute("tens", tens);
	        	request.setAttribute("unit", unit);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/sscThreeStar";
			}else if("".equals(addNumber)||addNumber == null){
				request.setAttribute("err_msg", "追号不可以为空");
				request.setAttribute("hundred", hundred);
				request.setAttribute("tens", tens);
				request.setAttribute("unit", unit);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/sscThreeStar";
			}
	    //检查注码的合法性
	    String[] s = {unit,tens,hundred};
        if(!ShishicaiUtil.checkThree(s,beishu,addNumber).equals("pass")){
        	request.setAttribute("err_msg", ShishicaiUtil.checkThree(s,beishu,addNumber));
        	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber",addNumber);
        	return "wap/cqshishicai/sscThreeStar";
        }
        //计算注码
        String zhuma = ShishicaiUtil.zhumaThree(s);
        String newzhuma = ShishicaiUtil.getNewZhuma(zhuma);

        //计算注数
        long zhushu = ShishicaiUtil.zhushuThree(s);
        if(zhushu <= 0){
        	logger.info("注数不正确，zhushu:"+zhushu+"hundred"+hundred+"tens"+tens+"unit"+unit);
        	request.setAttribute("err_msg", "您输入的注码不正确，请您重新输入");
        	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber",addNumber);
        	return "wap/cqshishicai/sscThreeStar";
        } 
        //计算金额
        long amt = zhushu*2*Integer.parseInt(beishu);
      //单次金额不能超过2万元
		if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber",addNumber);
        	return "wap/cqshishicai/sscThreeStar";
        }
        //计算新注数
        zhushu = zhushu*Integer.parseInt(beishu);
      
		request.setAttribute("beishu",beishu);
		request.setAttribute("amt", amt);
		request.setAttribute("term", term);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("zhuma",zhuma);
		request.setAttribute("newzhuma",newzhuma);
		request.setAttribute("type",type);
		request.setAttribute("addNumber",addNumber);
		return "wap/cqshishicai/sscBetDetail";
	}
	
	/**
	 * CQShiShiCaiAction 时时彩五星业务操作
	 * @author  
	 * (C)版权由北京金软瑞彩科技发展有限公司所有
	 * 网址：www.ruyicai.com
	 * 创建者：张博
	 * 创建日期：2011-2-25
	 * 修改记录：无
	 */
	@RequestMapping(value="/sscFiveStar.jspx",method=RequestMethod.POST)
	public String sscFiveStar(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//获取参数
		String myria = request.getParameter("myria");		 //万位
		String thousand = request.getParameter("thousand");	 //千位
		String hundred = request.getParameter("hundred");    //百位
		String tens = request.getParameter("tens");			 //十位
		String unit = request.getParameter("unit");			 //个位
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type"); // 类型
		String addNumber="";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber == null) {
				addNumber = "1";
			}
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "myria="+myria+"&thousand="+thousand+"&hundred="+hundred+"&tens="+tens+"&unit="+unit+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("myria"+myria+"thousand"+thousand+"hundred:"+hundred+" tens:"+tens+"unit:"+unit+"beishu:"+beishu);
		//获取当前期号
		String term = CommonUtil.getTerm("T01007");
		//验证是否全角，如果是全角转换成半角
		myria = CommonUtil.QJToBJChange(myria);
		thousand = CommonUtil.QJToBJChange(thousand);
		hundred = CommonUtil.QJToBJChange(hundred);
		tens = CommonUtil.QJToBJChange(tens);
	    unit = CommonUtil.QJToBJChange(unit);
	    beishu = CommonUtil.QJToBJChange(beishu);
	    if("".equals(myria) || myria== null){
	    	request.setAttribute("err_msg", "万位不可以为空");
	    	request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscFiveStar";
	    }else  if("".equals(thousand) || thousand== null){
	    	request.setAttribute("err_msg", "千位不可以为空");
	    	request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscFiveStar";
	    }
	    else if("".equals(hundred)||hundred == null){
			request.setAttribute("err_msg", "百位不可以为空");
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscFiveStar";
		}else  if("".equals(tens)||tens == null){
				request.setAttribute("err_msg", "十位不可以为空");
				request.setAttribute("myria", myria);
				request.setAttribute("thousand", thousand);
				request.setAttribute("hundred", hundred);
				request.setAttribute("tens", tens);
				request.setAttribute("unit", unit);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/sscFiveStar";
			}else if("".equals(unit)||unit == null){
		        	request.setAttribute("err_msg", "个位不可以为空");
		        	request.setAttribute("myria", myria);
					request.setAttribute("thousand", thousand);
		        	request.setAttribute("hundred", hundred);
		        	request.setAttribute("tens", tens);
					request.setAttribute("unit", unit);
					request.setAttribute("beishu", beishu);
					request.setAttribute("addNumber", addNumber);
					return "wap/cqshishicai/sscFiveStar";
		        }else if("".equals(beishu)||beishu == null){
		        	request.setAttribute("err_msg", "倍数不可以为空");
		        	request.setAttribute("myria", myria);
					request.setAttribute("thousand", thousand);
		        	request.setAttribute("hundred", hundred);
		        	request.setAttribute("tens", tens);
		        	request.setAttribute("unit", unit);
					request.setAttribute("beishu", beishu);
					request.setAttribute("addNumber", addNumber);
					return "wap/cqshishicai/sscFiveStar";
				}else if("".equals(addNumber)||addNumber == null){
					request.setAttribute("err_msg", "追号不可以为空");
					request.setAttribute("myria", myria);
					request.setAttribute("thousand", thousand);
					request.setAttribute("hundred", hundred);
					request.setAttribute("tens", tens);
					request.setAttribute("unit", unit);
					request.setAttribute("beishu", beishu);
					request.setAttribute("addNumber", addNumber);
					return "wap/cqshishicai/sscFiveStar";
				}
	    //检查注码的合法性
	    String[] s = {unit,tens,hundred,thousand,myria};
        if(!ShishicaiUtil.checkFive(s,beishu,addNumber).equals("pass")){
        	request.setAttribute("err_msg", ShishicaiUtil.checkFive(s, beishu,addNumber));
        	request.setAttribute("myria",myria);
	    	request.setAttribute("thousand",thousand);
        	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber",addNumber);
        	return "wap/cqshishicai/sscFiveStar";
        }
        //计算注码
        String zhuma = ShishicaiUtil.zhumaFive(s);
        String newzhuma = ShishicaiUtil.getNewZhuma(zhuma);

        //计算注数
        long zhushu = ShishicaiUtil.zhushuFive(s);
        if(zhushu <= 0){
        	logger.info("注数不正确，zhushu:"+zhushu+"myria"+myria+"thousand"+thousand+"hundred"+hundred+"tens"+tens+"unit"+unit);
        	request.setAttribute("err_msg", "您输入的注码不正确，请您重新输入");
        	request.setAttribute("myria",myria);
	    	request.setAttribute("thousand",thousand);
        	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber",addNumber);
        	return "wap/cqshishicai/sscFiveStar";
        } 
        //计算金额
        long amt = zhushu*2*Integer.parseInt(beishu);
        //单次金额不能超过2万元
		if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("myria",myria);
	    	request.setAttribute("thousand",thousand);
        	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber",addNumber);
        	return "wap/cqshishicai/sscFiveStar";
        }
        //计算新注数
        zhushu = zhushu*Integer.parseInt(beishu);
      
		request.setAttribute("beishu",beishu);
		request.setAttribute("amt", amt);
		request.setAttribute("term", term);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("zhuma",zhuma);
		request.setAttribute("newzhuma",newzhuma);
		request.setAttribute("type",type);
		request.setAttribute("addNumber",addNumber);
		return "wap/cqshishicai/sscBetDetail";
	}
	
	/**
	 * CQShiShiCaiAction 时时彩五星通选业务操作
	 * @author  
	 * (C)版权由北京金软瑞彩科技发展有限公司所有
	 * 网址：www.ruyicai.com
	 * 创建者：张博
	 * 创建日期：2011-2-25
	 * 修改记录：无
	 */
	@RequestMapping(value="/GeneralFiveStar.jspx",method=RequestMethod.POST)
	public String GeneralFiveStar(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//获取参数
		String myria = request.getParameter("myria");		 //万位
		String thousand = request.getParameter("thousand");	 //千位
		String hundred = request.getParameter("hundred");    //百位
		String tens = request.getParameter("tens");			 //十位
		String unit = request.getParameter("unit");			 //个位
		String beishu = request.getParameter("beishu");
		String type = request.getParameter("type"); // 类型
		String addNumber="";
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber");
			if (addNumber==null) {
 				addNumber = "1";
 			 }
			logger.info(" addNumber:"+addNumber);
			addNumber = CommonUtil.QJToBJChange(addNumber);
		}
		String parameter = "myria="+myria+"&thousand="+thousand+"&hundred="+hundred+"&tens="+tens+"&unit="+unit+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);

		logger.info("myria"+myria+"thousand"+thousand+"hundred:"+hundred+" tens:"+tens+"unit:"+unit+"beishu:"+beishu);
		//获取当前期号
		String term = CommonUtil.getTerm("T01007");
		//验证是否全角，如果是全角转换成半角
		myria = CommonUtil.QJToBJChange(myria);
		thousand = CommonUtil.QJToBJChange(thousand);
		hundred = CommonUtil.QJToBJChange(hundred);
		tens = CommonUtil.QJToBJChange(tens);
	    unit = CommonUtil.QJToBJChange(unit);
	    beishu = CommonUtil.QJToBJChange(beishu);
	    if("".equals(myria) || myria== null){
	    	request.setAttribute("err_msg", "万位不可以为空");
	    	request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/GeneralFiveStar";
	    }else  if("".equals(thousand) || thousand== null){
	    	request.setAttribute("err_msg", "千位不可以为空");
	    	request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/GeneralFiveStar";
	    }
	    else if("".equals(hundred)||hundred == null){
			request.setAttribute("err_msg", "百位不可以为空");
			request.setAttribute("myria", myria);
			request.setAttribute("thousand", thousand);
			request.setAttribute("hundred", hundred);
			request.setAttribute("tens", tens);
			request.setAttribute("unit", unit);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/GeneralFiveStar";
		}else  if("".equals(tens)||tens == null){
				request.setAttribute("err_msg", "十位不可以为空");
				request.setAttribute("myria", myria);
				request.setAttribute("thousand", thousand);
				request.setAttribute("hundred", hundred);
				request.setAttribute("tens", tens);
				request.setAttribute("unit", unit);
				request.setAttribute("beishu", beishu);
				request.setAttribute("addNumber", addNumber);
				return "wap/cqshishicai/GeneralFiveStar";
			}else if("".equals(unit)||unit == null){
		        	request.setAttribute("err_msg", "个位不可以为空");
		        	request.setAttribute("myria", myria);
					request.setAttribute("thousand", thousand);
		        	request.setAttribute("hundred", hundred);
		        	request.setAttribute("tens", tens);
					request.setAttribute("unit", unit);
					request.setAttribute("beishu", beishu);
					request.setAttribute("addNumber", addNumber);
					return "wap/cqshishicai/GeneralFiveStar";
		        }else if("".equals(beishu)||beishu == null){
		        	request.setAttribute("err_msg", "倍数不可以为空");
		        	request.setAttribute("myria", myria);
					request.setAttribute("thousand", thousand);
		        	request.setAttribute("hundred", hundred);
		        	request.setAttribute("tens", tens);
		        	request.setAttribute("unit", unit);
					request.setAttribute("beishu", beishu);
					request.setAttribute("addNumber", addNumber);
					return "wap/cqshishicai/GeneralFiveStar";
				}else if("".equals(addNumber)||addNumber == null){
					request.setAttribute("err_msg", "追号不可以为空");
					request.setAttribute("myria", myria);
					request.setAttribute("thousand", thousand);
					request.setAttribute("hundred", hundred);
					request.setAttribute("tens", tens);
					request.setAttribute("unit", unit);
					request.setAttribute("beishu", beishu);
					request.setAttribute("addNumber", addNumber);
					return "wap/cqshishicai/GeneralFiveStar";
				}
	    //验证注码，追号 ，倍数是否是特殊字符
	    if(VerificationAlgorithmUtil.isStringFilter(myria)||VerificationAlgorithmUtil.isStringFilter(thousand)||VerificationAlgorithmUtil.isStringFilter(hundred)||VerificationAlgorithmUtil.isStringFilter(tens)||VerificationAlgorithmUtil.isStringFilter(unit)||
	    		VerificationAlgorithmUtil.isStringFilter(beishu)||VerificationAlgorithmUtil.isStringFilter(addNumber)){
	    	request.setAttribute("err_msg", "输入框中不能输入特殊字符");
	    	request.setAttribute("myria",myria);
	    	request.setAttribute("thousand",thousand);
	    	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
	    	request.setAttribute("unit",unit);
	    	request.setAttribute("beishu",beishu);
	    	request.setAttribute("addNumber",addNumber);
	    	return "wap/cqshishicai/GeneralFiveStar";
	    }
	    //检查注码的合法性
	    String[] s = {unit,tens,hundred,thousand,myria};
	    
        if(!ShishicaiUtil.checkFiveAll(s,beishu,addNumber).equals("pass")){
        	request.setAttribute("err_msg", ShishicaiUtil.checkFiveAll(s, beishu,addNumber));
        	request.setAttribute("myria",myria);
	    	request.setAttribute("thousand",thousand);
        	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber",addNumber);
        	return "wap/cqshishicai/GeneralFiveStar";
        }
        //计算注码
        String zhuma = ShishicaiUtil.zhumaFiveAll(s);
      String   newzhuma = ShishicaiUtil.getNewZhuma(zhuma);
        
        //计算注数
        long zhushu = ShishicaiUtil.zhushuFiveAll(s);
        
        if(zhushu <= 0){
        	logger.info("注数不正确，zhushu:"+zhushu+"myria"+myria+"thousand"+thousand+"hundred"+hundred+"tens"+tens+"unit"+unit);
        	request.setAttribute("err_msg", "您输入的注码不正确，请您重新输入");
        	request.setAttribute("myria",myria);
	    	request.setAttribute("thousand",thousand);
        	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber",addNumber);
        	return "wap/cqshishicai/GeneralFiveStar";
        } 
        
        //计算金额
        long amt = zhushu*2*Integer.parseInt(beishu);
      //单次金额不能超过2万元
		if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("myria",myria);
	    	request.setAttribute("thousand",thousand);
        	request.setAttribute("hundred",hundred);
	    	request.setAttribute("tens",tens);
        	request.setAttribute("unit", unit);
        	request.setAttribute("beishu", beishu);
        	request.setAttribute("addNumber",addNumber);
        	return "wap/cqshishicai/GeneralFiveStar";
        }
        //计算新注数
        zhushu = zhushu*Integer.parseInt(beishu);
        
        request.setAttribute("beishu",beishu);
		request.setAttribute("amt", amt);
		request.setAttribute("term", term);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("zhuma",zhuma);
		request.setAttribute("newzhuma",newzhuma);
		request.setAttribute("type",type);
		request.setAttribute("addNumber",addNumber);
		return "wap/cqshishicai/sscBetDetail";
	}
	/**
	 * 时时彩投注
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/SSCBet.jspa")
	public String SSCBet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String beishu,amount="",zhuma,addNumber="",token,ttssBet="";
		String type ="";
		String term = CommonUtil.getTerm("T01007");
		String channel = WapUtil.getChannelId(request);
		String zhushu = request.getParameter("zhushu")==null?"":request.getParameter("zhushu");
		//用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则 从request.getAttribute 中获取 与表单同名同类型的obj
		if(request.getAttribute("outFormData")==null||request.getAttribute("outFormData").equals("")){
			 zhuma = request.getParameter("zhuma"); //个位
	         beishu = request.getParameter("beishu"); //倍数
			 token = request.getParameter("token");//判断是否重复提交
			 type = request.getParameter("type");//类型
			 amount = request.getParameter("amount");//彩种标号
			 if (rbint.getString("addNumberSwitch").equals("1")) { 
		        	 addNumber = request.getParameter("addNumber"); //追号
		        }
		}else{//获取request.getAttribute中的存储
			String[] beishus = request.getAttribute("beishu")==null?null:(String[])request.getAttribute("beishu");
			String[] zhumas = request.getAttribute("zhuma")==null?null:(String[])request.getAttribute("zhuma");
			String[] tokens = request.getAttribute("token")==null?null:(String[])request.getAttribute("token");
			String[] amounts = request.getAttribute("amount")==null?null:(String[])request.getAttribute("amount");
			String[] types = request.getAttribute("type")==null?null:(String[])request.getAttribute("type");
			String[] addNumbers = null;
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumbers = request.getAttribute("addNumber")==null?null:(String[])request.getAttribute("addNumber");
				addNumber = CommonUtil.QJToBJChange(addNumber);
			}
			//获取输入页面的参数
			beishu = beishus==null||beishus[0].equals("")?"0":beishus[0]; //倍数
			zhuma = zhumas==null||zhumas[0].equals("")?"0":zhumas[0];//不带","的注码
			token = tokens==null||tokens[0].equals("")?"0":tokens[0];
			type = types==null||types[0].equals("")?"0":types[0];
			amount = amounts==null||amounts[0].equals("")?"0":amounts[0];
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = addNumbers==null||addNumbers[0].equals("")?"0":addNumbers[0];
			}
		}
		logger.info("userno:"+userno+" zhuma:"+zhuma+" addNumber:"+addNumber+
				" beishu:"+beishu);
		boolean flag = CommonUtil.getBalanceResult(userno, amount);
		if(flag){
			request.setAttribute("message","您的余额不足，请先充值！");
			return "wap/charge/chargeIndex";
		}
		//判断是否重复提交
		String isExecute = (String)request.getSession().getAttribute(token);
		if (isExecute.trim().equals("false")) {
			request.getSession().setAttribute(token, "true");
			
			String wanfa = "";
			if("1D".equals(type)){
				wanfa="SSC_1X";
			}
			if("2D".equals(type)){
				wanfa="SSC_2X";
			}
			if("3D".equals(type)){
				wanfa="SSC_3X";
			}
			if("5D".equals(type)){
				wanfa="SSC_5X";
			}
			if("F2".equals(type)){
				wanfa="SSC_2XZX";
			}
			if("S2".equals(type)){
				wanfa="SSC_2XHZ";
			}
			if("5T".equals(type)){
				wanfa="SSC_5XT";
			}
			if("DD".equals(type)){
				wanfa="SSC_DXDS";
			}
		
	    	//调用投注接口
			ttssBet= JsonToJrtLotUtil.sendToBet(userno, "T01007", term, zhuma, beishu, "2", wanfa, amount, addNumber, channel);
		    request.setAttribute("err_msg", ttssBet);
	     }	else {
	    	 request.setAttribute("err_msg", "请勿重复提交");
		 }
		return "wap/BetSuccess";
		
		
	}
	
	}
