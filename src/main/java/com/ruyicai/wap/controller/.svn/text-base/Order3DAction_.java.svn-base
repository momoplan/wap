package com.ruyicai.wap.controller;
/**
 * OrderAction 3dw合买 业务
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
* 创建者：tianqk
* 创建日期：
* 修改记录：
 */
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

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
@RequestMapping("/orderhm3D")
@Controller
public class Order3DAction_{
	private static final Logger logger = Logger.getLogger(Order3DAction_.class);
	 
	/*
	 * 3D直选合买 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/directSelection.jspx",method=RequestMethod.POST)
	public String directSelection(HttpServletRequest request, HttpServletResponse response) {

		String hundreds_No = request.getParameter("hundreds_No");// 百位数
		String tens_No = request.getParameter("tens_No");// 十位数
		String units_No = request.getParameter("units_No");// 个位数
		String beishu_No = request.getParameter("beishu");
		String type = "";
		if(hundreds_No.trim().length()<=1&&tens_No.trim().length()<=1&&units_No.trim().length()<=1){
			type ="HMSD_ZXDS";
		}else{
			type= "HMSD_ZXFS";
		}
		//String term = CommonUtil.getTerm("F47103");//获取当前期号
		hundreds_No = CommonUtil.QJToBJChange(hundreds_No);
		tens_No = CommonUtil.QJToBJChange(tens_No);
		units_No = CommonUtil.QJToBJChange(units_No);
		beishu_No = CommonUtil.QJToBJChange(beishu_No);
		if (VerificationAlgorithmUtil.isStringFilter(hundreds_No)||VerificationAlgorithmUtil.isStringFilter(tens_No)||VerificationAlgorithmUtil.isStringFilter(units_No)||VerificationAlgorithmUtil.isStringFilter(beishu_No)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/3D/issuehm3D/3DDirectSingle";
		}
		String hundreds_No1 = LotteryAlgorithmUtil.addZero3D(hundreds_No);
		String tens_No1 = LotteryAlgorithmUtil.addZero3D(tens_No);
		String units_No1 = LotteryAlgorithmUtil.addZero3D(units_No);
		Map map = new HashMap();
		map.put("hundreds_No", hundreds_No1);
		map.put("tens_No", tens_No1);
		map.put("units_No", units_No1);
		map.put("beishu", beishu_No);
		String message = VerificationAlgorithmUtil.verify3DDirectSelectionSingle(map);
		if(message!=null) {
			request.setAttribute("message", message);
			request.setAttribute("hundreds_No", hundreds_No);
			request.setAttribute("tens_No", tens_No);
			request.setAttribute("units_No", units_No);
			request.setAttribute("beishu", beishu_No);
			return "wap/3D/issuehm3D/3DDirectSingle";
		} else {
			String newzhuma="百位："+hundreds_No+"  十位："+tens_No+"  个位："+units_No;
			String zhuma="";//后台传送注码
			
			Vector hundreds_NoVector = LotteryAlgorithmUtil.getStringArrayFromString(hundreds_No1);
			Collections.sort(hundreds_NoVector);
			Vector tens_NoVector = LotteryAlgorithmUtil.getStringArrayFromString(tens_No1);
			Collections.sort(tens_NoVector);
			Vector units_NoVector = LotteryAlgorithmUtil.getStringArrayFromString(units_No1);
			Collections.sort(units_NoVector);
			zhuma = "2001"+ CommonUtil.getNewString(String.valueOf(hundreds_No.length()))
			         + LotteryAlgorithmUtil.joinStringArray(hundreds_NoVector) + "^"
			         + CommonUtil.getNewString(String.valueOf(tens_No.length())) + LotteryAlgorithmUtil.joinStringArray(tens_NoVector) + "^"
			         + CommonUtil.getNewString(String.valueOf(units_No.length())) + LotteryAlgorithmUtil.joinStringArray(units_NoVector) + "^";
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
			
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("amount", amount);
			request.setAttribute("type", type);
			return "wap/3D/issuehm3D/hm3DDetail";
		}
	}
	
	
	/*
	 * 3D组3合买 
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/group3Multiple.jspx",method=RequestMethod.POST)
	public String group3Multiple(HttpServletRequest request, HttpServletResponse response){
		
		String zhuma_No = request.getParameter("zhuma");// 注码
		String beishu_No = request.getParameter("beishu");
		//String term = CommonUtil.getTerm("F47103");//获取当前期号
		zhuma_No = CommonUtil.QJToBJChange(zhuma_No);
		beishu_No = CommonUtil.QJToBJChange(beishu_No);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma_No)||VerificationAlgorithmUtil.isStringFilter(beishu_No)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/3D/issuehm3D/Group3hm";
		}
		String zhuma0 = LotteryAlgorithmUtil.addZero3D(zhuma_No);
		
		Map map = new HashMap();
		map.put("zhuma", zhuma0);
		map.put("beishu", beishu_No);
		String errorMessage = VerificationAlgorithmUtil.verify3DGroup3Multiple(map);
		if(errorMessage!=null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("zhuma", zhuma_No);
			request.setAttribute("beishu", beishu_No);
			return "wap/3D/issuehm3D/Group3hm";
		} else {
			String zhuma="";//后台传送注码
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma0);
			//对不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString3D(zhuma_No);
			//对数组排序
			Collections.sort(vector2);
			//对注码数组进行排序
			Collections.sort(vector);
			int len = vector.size();
			zhuma = CommonUtil.M_Z3FS+ "01" + CommonUtil.getNewString(String.valueOf(zhuma_No.length()))
	         + LotteryAlgorithmUtil.joinStringArray(vector) ;//30120702030405060708^
			//获得带","的注码
			String newzhuma = "注码："+LotteryAlgorithmUtil.joinStringArrayWithComma(vector2);
			//获得组3复式的注数
			int zhushu = LotteryAlgorithmUtil.getGroup3Multiple3DNumber(len);
			//将倍数转成int
			int beishu = 1;
			if(!beishu_No.equals(""))
				{beishu = Integer.parseInt(beishu_No);}
			else{
				beishu=1;
			}
			//计算金额
			int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("type", "HMSD_Z3FS");
			return "wap/3D/issuehm3D/hm3DDetail";
		}
	}
	
	/*
	 * 3D组6合买 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/group6Multiple.jspx",method=RequestMethod.POST)
	public String group6Multiple(HttpServletRequest request, HttpServletResponse response) {
		
		String zhuma_No = request.getParameter("zhuma");// 注码
		String beishu_No = request.getParameter("beishu");
		//String term = CommonUtil.getTerm("F47103");//获取当前期号
		zhuma_No = CommonUtil.QJToBJChange(zhuma_No);
		beishu_No = CommonUtil.QJToBJChange(beishu_No);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma_No)||VerificationAlgorithmUtil.isStringFilter(beishu_No)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/3D/issuehm3D/Group6hm";
		}
		String zhuma0 = LotteryAlgorithmUtil.addZero3D(zhuma_No);
		
		Map map = new HashMap();
		map.put("zhuma", zhuma0);
		map.put("beishu", beishu_No);
		String errorMessage = null;
		String type = "";
		if (zhuma_No.trim().length() <= 3) {
			errorMessage = VerificationAlgorithmUtil.verify3DGroup6Single(map);
			type = "HMSD_Z6DS";
		} else {
			errorMessage = VerificationAlgorithmUtil.veritfy3DGroup6Multiple(map);
			type = "HMSD_Z6FS";
		}
		if(errorMessage != null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("zhuma", zhuma_No);
			request.setAttribute("beishu", beishu_No);
			return "wap/3D/issuehm3D/Group6hm";
		} else {
			String zhuma="";//后台传送注码
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma0);
			//对不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString3D(zhuma_No);
			//对数组排序
			Collections.sort(vector2);
			//对注码数组进行排序
			Collections.sort(vector);
			int zhushu = 1;
			if (zhuma_No.trim().length() == 3) {
				zhuma = CommonUtil.M_Z6DS +"01" + LotteryAlgorithmUtil.joinStringArray(vector);
				zhushu = 1;
			} else {
				int len = vector.size();
				zhuma = CommonUtil.M_Z6FS+ "01" + CommonUtil.getNewString(String.valueOf(zhuma_No.length())) + LotteryAlgorithmUtil.joinStringArray(vector) ;//30120702030405060708^
				//获得组6复式的注数
				zhushu = LotteryAlgorithmUtil.getGroup6Multiple3DNumber(len);
			}
			//获得带","的注码
			String newzhuma = "注码："+LotteryAlgorithmUtil.joinStringArrayWithComma(vector2);
			//将倍数转成int
			int beishu = 1;
			if(!beishu_No.equals(""))
				{beishu = Integer.parseInt(beishu_No);}
			else{
				beishu=1;
			}
			//计算金额
			int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
			
			
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("type", type);
			return "wap/3D/issuehm3D/hm3DDetail";
		}
	}
	
	/*
	 * 3D单选单复式合买 (直选包号)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/singleSelectSingleMultiple.jspx",method=RequestMethod.POST)
	public String singleSelectSingleMultiple(HttpServletRequest request, HttpServletResponse response){
		
		String zhuma_No = request.getParameter("zhuma");// 注码
		String beishu_No = request.getParameter("beishu");
		//String term = CommonUtil.getTerm("F47103");//获取当前期号
		zhuma_No = CommonUtil.QJToBJChange(zhuma_No);
		beishu_No = CommonUtil.QJToBJChange(beishu_No);
		if (VerificationAlgorithmUtil.isStringFilter(zhuma_No)||VerificationAlgorithmUtil.isStringFilter(beishu_No)) {
			request.setAttribute("message", "输入框中不能输入特殊字符");
			return "wap/3D/issuehm3D/Group3hm";
		}
		String zhuma0 = LotteryAlgorithmUtil.addZero3D(zhuma_No);
		Map map = new HashMap();
		map.put("zhuma", zhuma0);
		map.put("beishu", beishu_No);
		String errorMessage = VerificationAlgorithmUtil.verify3DSingleSelectSingleMultiple(map);
		if(errorMessage!=null) {
			request.setAttribute("message", errorMessage);
			request.setAttribute("zhuma", zhuma_No);
			request.setAttribute("beishu", beishu_No);
			return "wap/3D/issuehm3D/singleSelectSingleMultipleHm";
		} else {
			String zhuma="";//后台传送注码
			Vector vector = LotteryAlgorithmUtil.getStringArrayFromString(zhuma0);
			//对不带"0"的注码转成数组
			Vector vector2 = LotteryAlgorithmUtil.getStringArrayFromString3D(zhuma_No);
			//对数组排序
			Collections.sort(vector2);
			//对注码数组进行排序
			Collections.sort(vector);
			int len = vector.size();
			zhuma = CommonUtil.M_DXDFS+ "01" + CommonUtil.getNewString(String.valueOf(zhuma_No.length()))
	         + LotteryAlgorithmUtil.joinStringArray(vector) ;//30120702030405060708^
			//获得带","的注码
			String newzhuma = "注码："+LotteryAlgorithmUtil.joinStringArrayWithComma(vector2);
			//获得组3复式的注数
			int zhushu = (int)LotteryAlgorithmUtil.get3DSingleSelectSingleMultiple(3, len);
			//将倍数转成int
			int beishu = 1;
			if(!beishu_No.equals(""))
				{beishu = Integer.parseInt(beishu_No);}
			else{
				beishu=1;
			}
			//计算金额
			int amount = zhushu * beishu * LotteryAlgorithmUtil.priceOfCaipiao;
			// 把计算出来的结果设置到request
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("amount", amount);
			request.setAttribute("newzhuma", newzhuma);
			request.setAttribute("zhuma", zhuma);
			request.setAttribute("type", "HMSD_ZXBH");
			return "wap/3D/issuehm3D/hm3DDetail";
		}
	}
}
