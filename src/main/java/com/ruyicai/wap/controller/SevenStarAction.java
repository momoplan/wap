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
import com.ruyicai.wap.util.WapUtil;

/**
 * SevenStarAction 七星彩手选业务操作
 * @author  
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 *网址：www.ruyicai.com
* 创建者：张步云
* 创建日期：2011-3-14
* 修改记录：无
 */
@RequestMapping("/sevenstar")
@Controller
public class SevenStarAction{
	private static final Logger logger = Logger.getLogger(DaLeTouAction.class);
	 
	/**
	 * 七星彩提交投注详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 确定投注页面
	 * @throws Exception
	 */
	@RequestMapping(value="/sevenStarSubmit.jspx",method=RequestMethod.POST)
	public String sevenStarSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取参数
		String one, two, three, four, five, six, seven, beishu, amount, type, addNumber = "";
		one = request.getParameter("one"); // ①星注码
		two = request.getParameter("two"); // ②星注码
		three = request.getParameter("three"); // ③星注码
		four = request.getParameter("four"); // ④星注码
		five = request.getParameter("five"); // ⑤星注码
		six = request.getParameter("six"); // ⑥星注码
		seven = request.getParameter("seven"); // ⑦星注码
		beishu = request.getParameter("beishu"); // 倍数
		type = request.getParameter("type");
		if (rbint.getString("addNumberSwitch").equals("1")) {
			addNumber = request.getParameter("addNumber"); // 追号
		}
		String parameter = "one="+one+"&beishu="+beishu+"&type="+type+"&addNumber="+addNumber
				+"&two="+two+"&three="+three+"&four="+four+"&five="+five+"&six="+six+"&seven="+seven;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("one:" + one + "two:" + two
				+ "three:" + three + "four:" + four + "five:" + five + "six:"
				+ six + "seven:" + seven + "addNumber:" + addNumber
				+ " beishu:" + beishu);
		// 获取当前期号
		String term = CommonUtil.getTerm("T01009");
		// 验证是否是全角，如果是全角。 那么转换为半角
		one = CommonUtil.QJToBJChange(one);
		two = CommonUtil.QJToBJChange(two);
		three = CommonUtil.QJToBJChange(three);
		four = CommonUtil.QJToBJChange(four);
		five = CommonUtil.QJToBJChange(five);
		six = CommonUtil.QJToBJChange(six);
		seven = CommonUtil.QJToBJChange(seven);
		beishu = CommonUtil.QJToBJChange(beishu);
		addNumber = CommonUtil.QJToBJChange(addNumber);
		if ("".equals(one) || one == null) {
			request.setAttribute("err_msg", "第一星注码不可以为空");
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/sevenStar/sevenStarByHand";
		} else if ("".equals(two) || two == null) {
			request.setAttribute("err_msg", "第二星注码不可以为空");
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/sevenStar/sevenStarByHand";
		} else if ("".equals(three) || three == null) {
			request.setAttribute("err_msg", "第三星注码不可以为空");
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/sevenStar/sevenStarByHand";
		} else if ("".equals(four) || four == null) {
			request.setAttribute("err_msg", "第四星注码不可以为空");
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/sevenStar/sevenStarByHand";
		} else if ("".equals(five) || five == null) {
			request.setAttribute("err_msg", "第五星注码不可以为空");
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/sevenStar/sevenStarByHand";
		} else if ("".equals(six) || six == null) {
			request.setAttribute("err_msg", "第六星注码不可以为空");
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/sevenStar/sevenStarByHand";
		} else if ("".equals(seven) || seven == null) {
			request.setAttribute("err_msg", "第七星注码位不可以为空");
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/sevenStar/sevenStarByHand";
		} else if ("".equals(beishu) || beishu == null) {
			request.setAttribute("err_msg", "倍数不可以为空");
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/sevenStar/sevenStarByHand";
		} else if ("".equals(addNumber) || addNumber == null) {
			request.setAttribute("err_msg", "追号不可以为空");
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/sevenStar/sevenStarByHand";
		}
		logger.info("one:" + one + "two:" + two
				+ "three:" + three + "four:" + four + "five:" + five + "six:"
				+ six + "seven:" + seven + "beishu:" + beishu + "addNumber:"
				+ addNumber);
		//验证注码、倍数、追号是否合法
		String[] code = { one, two, three, four, five, six, seven };
		if(!QixingcaiUtil.checkAll(code, beishu, addNumber).equals("pass")){
			request.setAttribute("err_msg", QixingcaiUtil.checkAll(code, beishu, addNumber));
			request.setAttribute("one", one);
			request.setAttribute("two", two);
			request.setAttribute("three", three);
			request.setAttribute("four", four);
			request.setAttribute("five", five);
			request.setAttribute("six", six);
			request.setAttribute("seven", seven);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			logger.info("one:" + one + "two:"
					+ two + "three:" + three + "four:" + four + "five:" + five
					+ "six:" + six + "seven:" + seven + "beishu:" + beishu
					+ "addNumber:" + addNumber);
			return "wap/sevenStar/sevenStarByHand";
		}
		if(one.length()>1){
			int str[] = new int[one.length()];
			for(int i = 0;i<one.length();i++){
				str[i]=Integer.parseInt(one.charAt(i)+"");
			}
			one = QixingcaiUtil.bubbleSort(str);
		}
		if(two.length()>1){
			int str[] = new int[two.length()];
			for(int i = 0;i<two.length();i++){
				str[i]=Integer.parseInt(two.charAt(i)+"");
			}
			two = QixingcaiUtil.bubbleSort(str);
		}
		if(three.length()>1){
			int str[] =new int[three.length()];
			for(int i = 0;i<three.length();i++){
				str[i]=Integer.parseInt(three.charAt(i)+"");
			}
			three = QixingcaiUtil.bubbleSort(str);
		}
		if(four.length()>1){
			int str[] =new int[four.length()];
			for(int i = 0;i<four.length();i++){
				str[i]=Integer.parseInt(four.charAt(i)+"");
			}
			four = QixingcaiUtil.bubbleSort(str);
		}
		if(five.length()>1){
			int str[] =new int[five.length()];
			for(int i = 0;i<five.length();i++){
				str[i]=Integer.parseInt(five.charAt(i)+"");
			}
			five = QixingcaiUtil.bubbleSort(str);
		}
		if(six.length()>1){
			int str[] =new int[six.length()];
			for(int i = 0;i<six.length();i++){
				str[i]=Integer.parseInt(six.charAt(i)+"");
			}
			six = QixingcaiUtil.bubbleSort(str);
		}
		if(seven.length()>1){
			int str[] =new int[seven.length()];
			for(int i = 0;i<seven.length();i++){
				str[i]=Integer.parseInt(seven.charAt(i)+"");
			}
			seven = QixingcaiUtil.bubbleSort(str);
		}
		
		
		//转换注码格式
		String zhumaStr = one + "," + two + "," + three + "," + four + "," + five
				+ "," + six + "," + seven;
		String zhumaView = QixingcaiUtil.getZhumaSplit(zhumaStr, "ON");
		//计算注数
		long zhushu = QixingcaiUtil.zhuShu(code);
		 if(zhushu <= 0){
	        	logger.info("注数不正确，zhushu:" + zhushu + "one:" + one + "two:" + two
					+ "three:" + three + "four:" + four + "five:" + five
					+ "six:" + six + "seven:" + seven);
	        	request.setAttribute("message", "您输入的注码不正确，请您重新输入");	        	
	        	request.setAttribute("one", one);
				request.setAttribute("two", two);
				request.setAttribute("three", three);
				request.setAttribute("four", four);
				request.setAttribute("five", five);
				request.setAttribute("six", six);
				request.setAttribute("seven", seven);
	        	request.setAttribute("beishu", beishu);
	        	request.setAttribute("addNumber", addNumber);
	        	return "wap/sevenStar/sevenStarByHand";
	        }
		//计算金额
		long amt = zhushu*2*Integer.parseInt(beishu);
		amount = amt + "";
	
		//计算新的注数 ，新注数= 注数*倍数
		zhushu = zhushu*Integer.parseInt(beishu);
		//把所有获取到的值  都放到request
		request.setAttribute("zhumaStr", zhumaStr);
    	request.setAttribute("zhumaView", zhumaView);
    	request.setAttribute("zhushu", zhushu);
    	request.setAttribute("amt", amount);
    	request.setAttribute("term", term);
		request.setAttribute("one", one);
		request.setAttribute("two", two);
		request.setAttribute("three", three);
		request.setAttribute("four", four);
		request.setAttribute("five", five);
		request.setAttribute("six", six);
		request.setAttribute("seven", seven);
    	request.setAttribute("beishu", beishu);
    	request.setAttribute("addNumber", addNumber);
    	logger.info("one:" + one + "two:" + two
				+ "three:" + three + "four:" + four + "five:" + five + "six:"
				+ six + "seven:" + seven + "beishu:" + beishu + "addNumber:"
				+ addNumber + "amount:" + amount + "zhushu:" + zhushu);
		return "wap/sevenStar/7StarBetDetail";
	}
	
	
	/**
	 * 七星彩确定投注
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 成功页面
	 * @throws Exception
	 */
	@RequestMapping(value="/betConfirm.jspa")
	public String betConfirm(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("T01009");
		String channel = WapUtil.getChannelId(request);
		// 投注类型 daigou 普通投注 hemai 合买投注 presented
		String buyways = request.getParameter("buyways")==null?"":request.getParameter("buyways");
		String zhumaView = "", zhushu = "", beishu, certID, amount = "", zhumaStr, addNumber = "", token, ttssBet = "", oneMoney = "", lotno = "", batchCode;
		String type = "";
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			zhumaStr = request.getParameter("zhumaStr"); // 个位
			beishu = request.getParameter("beishu"); // 倍数
			token = request.getParameter("token");// 判断是否重复提交
			type = request.getParameter("type");// 类型
			amount = request.getParameter("amount");// 彩种标号
			batchCode = request.getParameter("term");// 当前期号
			zhumaView = request.getParameter("zhumaView");// 当前期号
			zhushu = request.getParameter("zhushu");
			if (rbint.getString("addNumberSwitch").equals("1")) {
				addNumber = request.getParameter("addNumber"); // 追号
			}
		} else {// 获取request.getAttribute中的存储
			String[] beishus = request.getAttribute("beishu") == null ? null
					: (String[]) request.getAttribute("beishu");
			String[] zhumas = request.getAttribute("zhumaStr") == null ? null
					: (String[]) request.getAttribute("zhumaStr");
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
			zhumaStr = zhumas == null || zhumas[0].equals("") ? "0" : zhumas[0];// 不带","的注码
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
		logger.info("userno:" + userno + " zhuma:" + zhumaStr
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
						request.setAttribute("newbetcode", zhumaStr);
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
						request.setAttribute("newbetcode", zhumaStr);
						request.setAttribute("lotno", "T01009");
						return "wap/zengcai/zengcai";
					} else {
						boolean flag = CommonUtil.getBalanceResult(userno, amount);
						if(flag){
							request.setAttribute("message","您的余额不足，请先充值！");
							return "wap/charge/chargeIndex";
						}
						ttssBet = JsonToJrtLotUtil.sendToBet(userno, "T01009", batchCode, zhumaStr, beishu, "2", wanfa, amount, addNumber, channel);

					}

			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}
	
}
