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
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/shishicaiauto")
@Controller
public class CQShiShiCaiAutoAction{

	private static final Logger logger = Logger.getLogger(CQShiShiCaiAction.class);
	 
	
	/**
	 * 时时彩一星机选
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/SSCOneStarAuto.jspx",method=RequestMethod.POST)
	public String SSCOneStarAuto(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//获取参数
		String zhushu = request.getParameter("jixuan");
		String beishu = request.getParameter("beishu");
		String addNumber = request.getParameter("addNumber");
		if(zhushu == null || "".equals(zhushu)){
			request.setAttribute("err_msg", "注数不可为空");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscOneStarAuto";
		}
		if( ShishicaiUtil.checkByRegex("[1-9]|10",zhushu) ==false  ){
			request.setAttribute("err_msg", "机选注数不合法，一星最高机选注数为10注");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscOneStarAuto";
		}
		String checkResult = ShishicaiUtil.checkBeiShuAndAddNumber(beishu, addNumber);
		if(!"".equals(checkResult)&&checkResult!=null){
			request.setAttribute("err_msg", checkResult);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscOneStarAuto";			
		}
		String parameter = "jixuan="+zhushu+"&beishu="+beishu+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		// 获取当前期号
		String term = CommonUtil.getTerm("T01007");
		zhushu = CommonUtil.QJToBJChange(zhushu);
		//生成页面显示的注码
		String newzhuma = ShishicaiUtil.zhumaAutoSingle(Integer.parseInt(zhushu));
		//生成投注格式的注码
		String newzhumaStr = ShishicaiUtil.getAutoBetCode(newzhuma, "1D");
		//计算机选注数
		long zhushuInt = ShishicaiUtil.getAutoZhushu(newzhumaStr);
		
        if(zhushuInt != Integer.parseInt(zhushu) ){
        	request.setAttribute("err_msg", "系统正在维护 ，请稍后再试");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscOneStarAuto";
        }
        String zhushuStr = zhushuInt+"";
        //计算金额
        long amt = zhushuInt*2*Integer.parseInt(beishu);
      //单次金额不能超过2万元
		if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("beishu", beishu);
    		request.setAttribute("addNumber", addNumber);
        	request.setAttribute("zhushu", zhushu);
	    	return "wap/cqshishicai/sscOneStarAuto";
        }
        String amount = amt+"";
        
        request.setAttribute("zhuma", newzhumaStr);
		request.setAttribute("newzhuma",newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("amt",amount);
		request.setAttribute("zhushu",zhushuStr);
		request.setAttribute("type", "1D");
		request.setAttribute("autoMethod", "SSCOneStarAuto");
	return "wap/cqshishicai/sscAutoBetDetail";
	}
	
	/**
	 * 时时彩二星机选
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/SSCTowStarAuto.jspx",method=RequestMethod.POST)
	public String SSCTowStarAuto(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		//获取参数
		String zhushu = request.getParameter("jixuan");
		String beishu = request.getParameter("beishu");
		String addNumber = request.getParameter("addNumber");
		if(zhushu == null || "".equals(zhushu)){
			request.setAttribute("err_msg", "注数不可为空");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);

			return "cqshishicai/sscTowStarAuto";
		}
		if( ShishicaiUtil.checkByRegex("[1-9][0-9]{0,1}", zhushu) == false){
			request.setAttribute("err_msg", "机选注数不合法，二星机选最大99注");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
	    	return "cqshishicai/sscTowStarAuto";
		}
		String checkResult = ShishicaiUtil.checkBeiShuAndAddNumber(beishu, addNumber);
		if(!"".equals(checkResult)&&checkResult!=null){
			request.setAttribute("err_msg", checkResult);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
	    	return "cqshishicai/sscTowStarAuto";			
		}
		String parameter = "jixuan="+zhushu+"&beishu="+beishu+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		// 获取当前期号
		String term = CommonUtil.getTerm("T01007");
		zhushu = CommonUtil.QJToBJChange(zhushu);
        
		//页面显示注码
		String newzhuma = ShishicaiUtil.zhumaAutoDouble(Integer.parseInt(zhushu));
		//投注格式注码
		String newzhumaStr = ShishicaiUtil.getAutoBetCode(newzhuma, "2D");
		//计算注数
		long zhushuInt = ShishicaiUtil.getAutoZhushu(newzhumaStr);
        
        
        if(zhushuInt != Integer.parseInt(zhushu) ){
        	request.setAttribute("err_msg", "系统正在维护 ，请稍后再试");
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscTowStarAuto";
        }
        String zhushuStr = zhushuInt+"";
        //计算金额
        long amt = zhushuInt*2*Integer.parseInt(beishu);
      //单次金额不能超过2万元
		if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("zhushu", zhushu);
    		request.setAttribute("beishu", beishu);
    		request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscTowStarAuto";
        }
        String amount = amt+"";
        
        request.setAttribute("zhuma", newzhumaStr);
		request.setAttribute("newzhuma",newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("amt",amount);
		request.setAttribute("zhushu",zhushuStr);
		request.setAttribute("type", "2D");
		request.setAttribute("autoMethod", "SSCTowStarAuto");
		return "wap/cqshishicai/sscAutoBetDetail";
	}
	
	/**
	 * 时时彩机选投注
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/SSCAutoBet.jspa")
	public String SSCAutoBet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String term = CommonUtil.getTerm("T01007");
		String channel = WapUtil.getChannelId(request);

		String zhushu = request.getParameter("zhushu")==null?"":request.getParameter("zhushu");

		String beishu,amount="",zhuma,addNumber="",token,ttssBet="";
		String type ="";
		//用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则 从request.getAttribute 中获取 与表单同名同类型的obj
		if(request.getAttribute("outFormData")==null||request.getAttribute("outFormData").equals("")){
			 zhuma = request.getParameter("zhuma"); //投注注码
	         beishu = request.getParameter("beishu"); //倍数
			 token = request.getParameter("token");//判断是否重复提交
			 type = request.getParameter("type");//类型
			 amount = request.getParameter("amount");//金额
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
			
	    	//调用投注接口
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
		
			ttssBet= JsonToJrtLotUtil.sendToBet(userno, "T01007", term, zhuma, beishu, "2", wanfa, amount, addNumber, channel);
		    request.setAttribute("err_msg", ttssBet);
	     }	else {
	    	 request.setAttribute("err_msg", "请勿重复提交");
		 }
		return "wap/BetSuccess";
		
	}

	/**
	 * 时时彩三星机选业务操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/autoThree.jspx",method=RequestMethod.POST)
	public String autoThree(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		//获取参数
		String jixuan = request.getParameter("jixuan");
		String beishu = request.getParameter("beishu");
		String addNumber = request.getParameter("addNumber");
		String parameter = "jixuan="+jixuan+"&beishu="+beishu+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		if( jixuan == null || "".equals(jixuan)){
			request.setAttribute("err_msg", "机选注数不能为空");
			request.setAttribute("zhushu", jixuan);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscThreeStarAuto";
		}
		
		if( ShishicaiUtil.checkByRegex("[1-9][0-9]{0,1}", jixuan) == false){
			request.setAttribute("err_msg", "机选注数不合法，三星机选最多99注");
			request.setAttribute("zhushu", jixuan);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscThreeStarAuto";
		}
		String checkResult = ShishicaiUtil.checkBeiShuAndAddNumber(beishu, addNumber);
		if(!"".equals(checkResult)&&checkResult!=null){
			request.setAttribute("err_msg", checkResult);
			request.setAttribute("zhushu", jixuan);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscThreeStarAuto";
		}
		// 获取当前期号
		String term = CommonUtil.getTerm("T01007");
		jixuan = CommonUtil.QJToBJChange(jixuan);
		//将注数生成随机字符串注码
        
        //页面显示注码
		String newzhuma = ShishicaiUtil.zhumaAutoThree(Integer.parseInt(jixuan));
		//投注格式注码
		String newzhumaStr = ShishicaiUtil.getAutoBetCode(newzhuma, "3D");
		//计算注数
		long zhushuInt = ShishicaiUtil.getAutoZhushu(newzhumaStr);
        
        if(zhushuInt != Integer.parseInt(jixuan) ){
        	request.setAttribute("err_msg", "系统正在维护 ，请稍后再试");
			request.setAttribute("jixuan", jixuan);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscThreeStarAuto";
        }
        String zhushuStr = zhushuInt+"";
        //计算金额
        long amt = zhushuInt*2*Integer.parseInt(beishu);
        //单次金额不能超过2万元
		if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("jixuan", jixuan);
    		request.setAttribute("beishu", beishu);
    		request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscThreeStarAuto";
        }
        String amount = amt+"";
        request.setAttribute("zhuma", newzhumaStr);
		request.setAttribute("newzhuma",newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("beishu", beishu);
		request.setAttribute("addNumber", addNumber);
		request.setAttribute("amt",amount);
		request.setAttribute("zhushu",zhushuStr);
		request.setAttribute("type", "3D");
		request.setAttribute("autoMethod", "autoThree");
	return "wap/cqshishicai/sscAutoBetDetail";
	}
	
	/**
	 * 时时彩五星机选业务操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/autoFive.jspx",method=RequestMethod.POST)
	public String autoFive(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		//获取参数
		String jixuan = request.getParameter("jixuan");
		String beishu = request.getParameter("beishu");
		String addNumber = request.getParameter("addNumber");
		if( jixuan == null || "".equals(jixuan)){
			request.setAttribute("err_msg", "机选注数不能为空");
			request.setAttribute("zhushu", jixuan);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
			return "wap/cqshishicai/sscFiveStarAuto";
		}
		if( ShishicaiUtil.checkByRegex("[1-9][0-9]{0,1}", jixuan) == false){
			request.setAttribute("err_msg", "机选注数不合法，五星机选最多99注");
			request.setAttribute("zhushu", jixuan);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscFiveStarAuto";
		}
		String checkResult = ShishicaiUtil.checkBeiShuAndAddNumber(beishu, addNumber);
		if(!"".equals(checkResult)&&checkResult!=null){
			request.setAttribute("err_msg", checkResult);
			request.setAttribute("zhushu", jixuan);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscFiveStarAuto";
		}
		String parameter = "jixuan="+jixuan+"&beishu="+beishu+"&addNumber="+addNumber;
		request.getSession().setAttribute("parameter", parameter);
		// 获取当前期号
		String term = CommonUtil.getTerm("T01007");
		jixuan = CommonUtil.QJToBJChange(jixuan);
		//页面显示注码
		String newzhuma = ShishicaiUtil.zhumaAutoFive(Integer.parseInt(jixuan));
		//投注格式注码
		String newzhumaStr = ShishicaiUtil.getAutoBetCode(newzhuma, "5D");
		//计算注数
		long zhushuInt = ShishicaiUtil.getAutoZhushu(newzhumaStr);
		
        if(zhushuInt != Integer.parseInt(jixuan) ){
        	request.setAttribute("err_msg", "系统正在维护 ，请稍后再试");
			request.setAttribute("jixuan", jixuan);
			request.setAttribute("beishu", beishu);
			request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscFiveStarAuto";
        }
        String zhushuStr = zhushuInt+"";
        //计算金额
        long amt = zhushuInt*2*Integer.parseInt(beishu);
      //单次金额不能超过2万元
		if(amt>20000) {
        	logger.info("单次投注金额不能超过两万元，amt"+amt);
        	request.setAttribute("err_msg", "单次投注金额不能超过两万元，您本次金额为"+amt+"元");
        	request.setAttribute("jixuan", jixuan);
        	request.setAttribute("beishu", beishu);
    		request.setAttribute("addNumber", addNumber);
	    	return "wap/cqshishicai/sscFiveStarAuto";
        }
        String amount = amt+"";
        request.setAttribute("zhuma", newzhumaStr);
		request.setAttribute("newzhuma",newzhuma);
		request.setAttribute("term", term);
		request.setAttribute("beishu", "1");
		request.setAttribute("addNumber", "1");
		request.setAttribute("amt",amount);
		request.setAttribute("zhushu",zhushuStr);
		request.setAttribute("type", "5D");
		request.setAttribute("autoMethod", "autoFive");
		return "wap/cqshishicai/sscAutoBetDetail";
	}

}
