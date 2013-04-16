package com.ruyicai.wap.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.buybal.lot.lottype.ElevenDuoJin;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.WapUtil;
@RequestMapping("/elevenduojin")
@Controller
public class ElevenDuoJinAction{
	private static final Logger logger = Logger.getLogger(ElevenDuoJinAction.class);
	
	/**
	 * ren 1 - ren 8 投注详细 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DetailBet.jspx",method=RequestMethod.POST)
	public String DetailBet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		//获取参数
		String betcode = request.getParameter("betcode");
		String beishu = request.getParameter("beishu");
		if(beishu == null)beishu="1";
		String addnumber = request.getParameter("addnumber");
		if(addnumber == null) addnumber="1";
		String type = request.getParameter("type"); //  ren1-ren8 自选标示
		String term = CommonUtil.getTerm("T01012");
		String parameter = "betcode="+betcode+"&beishu="+beishu+"&type="+type+"&addnumber="+addnumber;
		request.getSession().setAttribute("parameter", parameter);
		//对参数进行校验
	    String checkResult = new ElevenDuoJin().checkBetCode(betcode, beishu, addnumber,type);
	    logger.info("注码验证结果：返回pass为通过"+checkResult);
	    if(!checkResult.equals("pass")){
	    	request.setAttribute("message",checkResult );
	    	request.setAttribute("beishu", beishu);
	    	request.setAttribute("betcode", betcode);
	    	request.setAttribute("addnumber", addnumber);
	    	if(type.equals("R1"))return "wap/ElevenDuoJin/optionOne";
			if(type.equals("R2"))return "wap/ElevenDuoJin/optionTwo";
			if(type.equals("R3"))return "wap/ElevenDuoJin/optionThree";
			if(type.equals("R4"))return "wap/ElevenDuoJin/optionFour";
			if(type.equals("R5"))return "wap/ElevenDuoJin/optionFive";
			if(type.equals("R6"))return "wap/ElevenDuoJin/optionSix";
			if(type.equals("R7"))return "wap/ElevenDuoJin/optionSeven";
			if(type.equals("R8"))return "wap/ElevenDuoJin/optionEight";
	    }
	    
	    logger.info("投注详细信息验证通过：betcode="+betcode +"beishu"+ beishu + "addnumber" + addnumber);
	    
	    //注码处理  将生成两种注码格式      viewBetcode(展示注码)  toBetcode (用于投注的注码)
	    //根据type值,获取投注的注码
	     JSONObject json =new ElevenDuoJin().tobetcode(type, betcode);
	     String toBetcode = json.getString("toBetcode");
	     String viewBetcode = json.getString("viewBetcode");
	    //金额验证java通用版
		int  zhushu = json.getInt("zhushu");
		//金额算法   注数* 倍数 * 单注2元 * 追期期数
		int amount  = zhushu * Integer.parseInt(beishu) * 2;
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("message", MessageUtil.TC_AmountError);
	    	request.setAttribute("beishu", beishu);
	    	request.setAttribute("betcode", betcode);
	    	request.setAttribute("addnumber", addnumber);
	    	request.setAttribute("zhushu", zhushu+"");
	    	if(type.equals("R1"))return "ElevenDuoJin/optionOne";
			if(type.equals("R2"))return "ElevenDuoJin/optionTwo";
			if(type.equals("R3"))return "ElevenDuoJin/optionThree";
			if(type.equals("R4"))return "ElevenDuoJin/optionFour";
			if(type.equals("R5"))return "ElevenDuoJin/optionFive";
			if(type.equals("R6"))return "ElevenDuoJin/optionSix";
			if(type.equals("R7"))return "ElevenDuoJin/optionSeven";
			if(type.equals("R8"))return "ElevenDuoJin/optionEight";
		}
		logger.info("投注注码:"+toBetcode+"注数："+zhushu+"投注金额，  注数* 倍数 * 单注2元 ："+amount);
		request.setAttribute("zhushu", zhushu+"");
    	request.setAttribute("beishu", beishu);
    	request.setAttribute("type", type);
    	request.setAttribute("addnumber", addnumber);
    	request.setAttribute("term", term);
    	request.setAttribute("amount", String.valueOf(amount));
    	request.setAttribute("toBetcode", toBetcode);
    	request.setAttribute("viewBetcode", viewBetcode);
    	
    	
		return "wap/ElevenDuoJin/generalBetDetail";
		
	}
	/**
	 * 组选2投注详细 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DetailBetG2.jspx",method=RequestMethod.POST)
	public String DetailBetG2(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	
		//获取参数
		String betcode = request.getParameter("betcode");
		String beishu = request.getParameter("beishu");
		if(beishu == null)beishu="1";
		String addnumber = request.getParameter("addnumber");
		if(addnumber == null) addnumber="1";
		String type = request.getParameter("type"); //  ren1-ren8 自选标示
		String term = CommonUtil.getTerm("T01012");
		String parameter = "betcode="+betcode+"&beishu="+beishu+"&type="+type+"&addnumber="+addnumber;
		request.getSession().setAttribute("parameter", parameter);
		//对参数进行校验
		String checkResult = new ElevenDuoJin().checkBetCode(betcode, beishu, addnumber,type);
		logger.info("注码验证结果：返回pass为通过"+checkResult);
		if(!checkResult.equals("pass")){
			request.setAttribute("message",checkResult );
			request.setAttribute("beishu", beishu);
			request.setAttribute("betcode", betcode);
			request.setAttribute("addnumber", addnumber);
			return "wap/ElevenDuoJin/ForwardTwoGroup";
		}
		
		logger.info("投注详细信息验证通过：betcode="+betcode +"beishu"+ beishu + "addnumber" + addnumber);
		
		//注码处理  将生成两种注码格式      viewBetcode(展示注码)  toBetcode (用于投注的注码)
		//根据type值,获取投注的注码
		JSONObject json =new ElevenDuoJin().tobetcode(type, betcode);
		String toBetcode = json.getString("toBetcode");
		String viewBetcode = json.getString("viewBetcode");
		//金额验证
		int  zhushu = json.getInt("zhushu");
		//金额算法   注数* 倍数 * 单注2元 * 追期期数
		int amount  = zhushu * Integer.parseInt(beishu) * 2;
		if (!CommonUtil.TCverifyAmount(amount)) {
			logger.info("amount 投注金额大于2万"+amount);
			request.setAttribute("message", MessageUtil.TC_AmountError);
			request.setAttribute("beishu", beishu);
			request.setAttribute("betcode", betcode);
			request.setAttribute("addnumber", addnumber);
			return "wap/ElevenDuoJin/ForwardTwoGroup";
		}
		logger.info("投注注码:"+toBetcode+"注数："+zhushu+"投注金额，  注数* 倍数 * 单注2元 ："+amount);
		request.setAttribute("zhushu", zhushu+"");
		request.setAttribute("beishu", beishu);
		request.setAttribute("type", type);
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("term", term);
		request.setAttribute("amount", String.valueOf(amount));
		request.setAttribute("toBetcode", toBetcode);
		request.setAttribute("viewBetcode", viewBetcode);
		return "wap/ElevenDuoJin/generalBetDetail";
		
	}
	
	/**
	 * 组选3投注详细 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DetailBetG3.jspx",method=RequestMethod.POST)
	public String DetailBetG3(HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		//获取参数
		String betcode = request.getParameter("betcode");
		String beishu = request.getParameter("beishu");
		if(beishu == null)beishu="1";
		String addnumber = request.getParameter("addnumber");
		if(addnumber == null) addnumber="1";
		String type = request.getParameter("type"); //  ren1-ren8 自选标示
		String term = CommonUtil.getTerm("T01012");
		String parameter = "betcode="+betcode+"&beishu="+beishu+"&type="+type+"&addnumber="+addnumber;
		request.getSession().setAttribute("parameter", parameter);
		//对参数进行校验
		String checkResult = new ElevenDuoJin().checkBetCode(betcode, beishu, addnumber,type);
		logger.info("注码验证结果：返回pass为通过"+checkResult);
		if(!checkResult.equals("pass")){
			request.setAttribute("message",checkResult );
			request.setAttribute("beishu", beishu);
			request.setAttribute("betcode", betcode);
			request.setAttribute("addnumber", addnumber);
			return "wap/ElevenDuoJin/ForwardThreeGroup";
		}
		
		logger.info("投注详细信息验证通过：betcode="+betcode +"beishu"+ beishu + "addnumber" + addnumber);
		
		//注码处理  将生成两种注码格式      viewBetcode(展示注码)  toBetcode (用于投注的注码)
		//根据type值,获取投注的注码
		JSONObject json =new ElevenDuoJin().tobetcode(type, betcode);
		String toBetcode = json.getString("toBetcode");
		String viewBetcode = json.getString("viewBetcode");
		//金额验证
		int  zhushu = json.getInt("zhushu");
		//金额算法   注数* 倍数 * 单注2元 * 追期期数
		int amount  = zhushu * Integer.parseInt(beishu) * 2;
		if (!CommonUtil.TCverifyAmount(amount)) {
			logger.info("amount 投注金额大于2万"+amount);
			request.setAttribute("message", MessageUtil.TC_AmountError);
			request.setAttribute("beishu", beishu);
			request.setAttribute("betcode", betcode);
			request.setAttribute("addnumber", addnumber);
			return "wap/ElevenDuoJin/ForwardThreeGroup";
		}
		logger.info("投注注码:"+toBetcode+"注数："+zhushu+"投注金额，  注数* 倍数 * 单注2元 ："+amount);
		request.setAttribute("zhushu", zhushu+"");
		request.setAttribute("beishu", beishu);
		request.setAttribute("type", type);
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("term", term);
		request.setAttribute("amount", String.valueOf(amount));
		request.setAttribute("toBetcode", toBetcode);
		request.setAttribute("viewBetcode", viewBetcode);
		return "wap/ElevenDuoJin/generalBetDetail";
		
	}
	
	/**
	 * 投注
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	@RequestMapping(value="/bet.jspa")
	public String bet(HttpServletRequest request, HttpServletResponse response) throws JSONException, ParseException{
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String zhuma, amount, beishu, zhushu, type, addnumber = "", token;
		// 获取当前期号
		String term = CommonUtil.getTerm("T01012");
		String ttssBet = "";
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
//			type = request.getParameter("type");     //标签
			token = request.getParameter("token");// 判断是否重复提交
			addnumber = request.getParameter("addnumber");
			if("".equals(addnumber) || addnumber==null){
				addnumber = "1";
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
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");
			String[] addnumbers = null;
			addnumbers = request.getAttribute("addnumber") == null ? null
						: (String[]) request.getAttribute("addnumber");
			// 获取输入页面的参数
			zhushu = zhushus == null || zhushus[0].equals("") ? "0"
					: zhushus[0]; // 注数
			beishu = beishus == null || beishus[0].equals("") ? "0"
					: beishus[0]; // 倍数
			zhuma = zhumas == null || zhumas[0].equals("") ? "0" : zhumas[0]; // 注码
			amount = amounts == null || amounts[0].equals("") ? "0"
					: amounts[0]; // 金额
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];
			addnumber = addnumbers == null || addnumbers[0].equals("") ? "0"
						: addnumbers[0];
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
			ttssBet = JsonToJrtLotUtil.sendToBet(userno, "T01012", term, zhuma, beishu, "2", "", amount, addnumber, channel);
			request.setAttribute("err_msg", ttssBet);
		}
		else{
			request.setAttribute("err_msg", "请勿重复提交");			
		}
		return "wap/BetSuccess";
	}
	/**
	 * 选前二直选单式
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/groupDetail.jspx",method=RequestMethod.POST)
	public String groupDetail(HttpServletRequest request, HttpServletResponse response) throws JSONException{
	
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		String beishu = request.getParameter("beishu");
		String addnumber = request.getParameter("addnumber");
		String type = request.getParameter("type");
		String term = CommonUtil.getTerm("T01012");
		String parameter = "first="+first+"&beishu="+beishu+"&type="+type+"&addnumber="+addnumber+"&second="+second;
		request.getSession().setAttribute("parameter", parameter);
		//对参数进行校验
	    String checkResult = new ElevenDuoJin().checkBetCodeZX(first,second, beishu, addnumber,type);
	    logger.info("注码验证结果：返回pass为通过"+checkResult);
	    if(!checkResult.equals("pass")){
	    	request.setAttribute("message",checkResult );
	    	request.setAttribute("beishu", beishu);
	    	request.setAttribute("first", first);
	    	request.setAttribute("second", second);
	    	request.setAttribute("addnumber", addnumber);
	    	if("z2".equals(type))return "wap/ElevenDuoJin/ForwardTwoZX";
			if("z2dwfs".equals(type))return "wap/ElevenDuoJin/DWTwoZXFS";
	    }
	    
	    logger.info("投注详细信息验证通过：第一位first="+first+"第二位second"+second +"beishu"+ beishu + "addnumber" + addnumber);
	    
	    //注码处理  将生成两种注码格式      viewBetcode(展示注码)  toBetcode (用于投注的注码)
	    //根据type值,获取投注的注码
	     String betcode = first+"|"+second;
	     JSONObject json =new ElevenDuoJin().tobetcode(type, betcode);
	     String toBetcode = json.getString("toBetcode");
	     String viewBetcode = json.getString("viewBetcode");
	    //金额验证
		int  zhushu = json.getInt("zhushu");
		//金额算法   注数* 倍数 * 单注2元 * 追期期数
		int amount  = zhushu * Integer.parseInt(beishu) * 2;
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("message", MessageUtil.TC_AmountError);
		   	request.setAttribute("beishu", beishu);
	    	request.setAttribute("first", first);
	    	request.setAttribute("second", second);
	    	request.setAttribute("addnumber", addnumber);
	    	if("z2".equals(type))return "wap/ElevenDuoJin/ForwardTwoZX";
			if("z2dwfs".equals(type))return "wap/ElevenDuoJin/DWTwoZXFS";
		}
		logger.info("投注注码:"+toBetcode+"注数："+zhushu+"投注金额,  注数* 倍数 * 单注2元 ："+amount);
		request.setAttribute("zhushu", zhushu+"");
    	request.setAttribute("beishu", beishu);
    	request.setAttribute("addnumber", addnumber);
    	request.setAttribute("term", term);
    	request.setAttribute("type", type);
    	request.setAttribute("amount", String.valueOf(amount));
    	request.setAttribute("toBetcode", toBetcode);
    	request.setAttribute("viewBetcode", viewBetcode);
		return "wap/ElevenDuoJin/generalBetDetail";
	}
	/**
	 * 选前二直选复式
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/groupFSDetail.jspx",method=RequestMethod.POST)
	public String groupFSDetail(HttpServletRequest request, HttpServletResponse response) throws JSONException{
	
		String betcode = request.getParameter("betcode");
		String beishu = request.getParameter("beishu");
		String addnumber = request.getParameter("addnumber");
		String type = request.getParameter("type");
		String term = CommonUtil.getTerm("T01012");
		String parameter = "betcode="+betcode+"&beishu="+beishu+"&type="+type+"&addnumber="+addnumber;
		request.getSession().setAttribute("parameter", parameter);
		//对参数进行校验
		String checkResult = new ElevenDuoJin().checkBetCode(betcode, beishu, addnumber,type);
		logger.info("注码验证结果：返回pass为通过"+checkResult);
		if(!checkResult.equals("pass")){
			request.setAttribute("message",checkResult );
			request.setAttribute("beishu", beishu);
			request.setAttribute("betcode", betcode);
			request.setAttribute("addnumber", addnumber);
			return "wap/ElevenDuoJin/ForwardTwoZXFS";
		}
		
		//注码处理  将生成两种注码格式      viewBetcode(展示注码)  toBetcode (用于投注的注码)
		//根据type值,获取投注的注码
		JSONObject json =new ElevenDuoJin().tobetcode(type, betcode);
		String toBetcode = json.getString("toBetcode");
		String viewBetcode = json.getString("viewBetcode");
		//金额验证
		int  zhushu = json.getInt("zhushu");
		//金额算法   注数* 倍数 * 单注2元 * 追期期数
		int amount  = zhushu * Integer.parseInt(beishu) * 2;
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("message", MessageUtil.TC_AmountError);
			request.setAttribute("beishu", beishu);
			request.setAttribute("betcode", betcode);
			request.setAttribute("addnumber", addnumber);
			return "wap/ElevenDuoJin/ForwardTwoZXFS";
		}
		logger.info("投注注码:"+toBetcode+"注数："+zhushu+"投注金额，  注数* 倍数 * 单注2元 ："+amount);
		request.setAttribute("zhushu", zhushu+"");
		request.setAttribute("beishu", beishu);
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("term", term);
		request.setAttribute("type", type);
		request.setAttribute("amount", String.valueOf(amount));
		request.setAttribute("toBetcode", toBetcode);
		request.setAttribute("viewBetcode", viewBetcode);
		return "wap/ElevenDuoJin/generalBetDetail";
	}
	/**
	 * 选前3直选
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/groupDetailZ3.jspx",method=RequestMethod.POST)
	public String groupDetailZ3(HttpServletRequest request, HttpServletResponse response) throws JSONException{
		
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		String third = request.getParameter("third");
		String beishu = request.getParameter("beishu");
		String addnumber = request.getParameter("addnumber");
		String type = request.getParameter("type");
		String term = CommonUtil.getTerm("T01012");
		String parameter = "first="+first+"&beishu="+beishu+"&type="+type+"&addnumber="+addnumber+"&second="+second+"&third="+third;
		request.getSession().setAttribute("parameter", parameter);
		//对参数进行校验
		String checkResult = new ElevenDuoJin().checkBetCodeZX3(first,second,third, beishu, addnumber,type);
		logger.info("注码验证结果：返回pass为通过"+checkResult);
		if(!checkResult.equals("pass")){
			request.setAttribute("message",checkResult );
			request.setAttribute("beishu", beishu);
			request.setAttribute("first", first);
			request.setAttribute("second", second);
			request.setAttribute("third", third);
			request.setAttribute("addnumber", addnumber);
			if("z3".equals(type))return "wap/ElevenDuoJin/ForwardThreeZX";
			if("z3dwfs".equals(type))return "wap/ElevenDuoJin/DWThreeZXFS";
		}
		
		logger.info("投注详细信息验证通过：第一位first="+first+"第二位second"+second+"第三位third"+third +"beishu"+ beishu + "addnumber" + addnumber);
		
		//注码处理  将生成两种注码格式      viewBetcode(展示注码)  toBetcode (用于投注的注码)
		//根据type值,获取投注的注码
		String betcode = first+"|"+second+"|"+third;
		JSONObject json =new ElevenDuoJin().tobetcode(type, betcode);
		String toBetcode = json.getString("toBetcode");
		String viewBetcode = json.getString("viewBetcode");
		//金额验证
		int  zhushu = json.getInt("zhushu");
		//金额算法   注数* 倍数 * 单注2元 * 追期期数
		int amount  = zhushu * Integer.parseInt(beishu) * 2;
		if (!CommonUtil.TCverifyAmount(amount)) {
			logger.info("amount 投注金额大于2万"+amount);
			request.setAttribute("message", MessageUtil.TC_AmountError);
			request.setAttribute("beishu", beishu);
			request.setAttribute("first", first);
			request.setAttribute("second", second);
			request.setAttribute("third", third);
			request.setAttribute("addnumber", addnumber);
			if("z3".equals(type))return "wap/ElevenDuoJin/ForwardThreeZX";
			if("z3dwfs".equals(type))return "wap/ElevenDuoJin/DWThreeZXFS";
		}
		logger.info("投注注码:"+toBetcode+"注数："+zhushu+"投注金额，  注数* 倍数 * 单注2元 ："+amount);
		request.setAttribute("zhushu", zhushu+"");
		request.setAttribute("beishu", beishu);
		request.setAttribute("type", type);
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("term", term);
		request.setAttribute("amount", String.valueOf(amount));
		request.setAttribute("toBetcode", toBetcode);
		request.setAttribute("viewBetcode", viewBetcode);
		return "wap/ElevenDuoJin/generalBetDetail";
	}
	
	/**
	 * 选前3直选复式
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/groupFSDetailZ3.jspx",method=RequestMethod.POST)
	public String groupFSDetailZ3(HttpServletRequest request, HttpServletResponse response) throws JSONException{
		
		String betcode = request.getParameter("betcode");
		String beishu = request.getParameter("beishu");
		String addnumber = request.getParameter("addnumber");
		String type = request.getParameter("type");
		String term = CommonUtil.getTerm("T01012");
		String parameter = "betcode="+betcode+"&beishu="+beishu+"&type="+type+"&addnumber="+addnumber;
		request.getSession().setAttribute("parameter", parameter);
		//对参数进行校验
		String checkResult = new ElevenDuoJin().checkBetCode(betcode, beishu, addnumber,type);
		logger.info("注码验证结果：返回pass为通过"+checkResult);
		if(!checkResult.equals("pass")){
			request.setAttribute("message",checkResult );
			request.setAttribute("beishu", beishu);
			request.setAttribute("betcode", betcode);
			request.setAttribute("addnumber", addnumber);
			return "wap/ElevenDuoJin/ForwardThreeZXFS";
		}
		
		logger.info("投注详细信息验证通过"+betcode +"beishu"+ beishu + "addnumber" + addnumber);
		
		//注码处理  将生成两种注码格式      viewBetcode(展示注码)  toBetcode (用于投注的注码)
		//根据type值,获取投注的注码
		
		JSONObject json =new ElevenDuoJin().tobetcode(type, betcode);
		String toBetcode = json.getString("toBetcode");
		String viewBetcode = json.getString("viewBetcode");
		//金额验证
		int  zhushu = json.getInt("zhushu");
		//金额算法   注数* 倍数 * 单注2元 * 追期期数
		int amount  = zhushu * Integer.parseInt(beishu) * 2;
		if (!CommonUtil.TCverifyAmount(amount)) {
			logger.info("amount 投注金额大于2万"+amount);
			request.setAttribute("message", MessageUtil.TC_AmountError);
			request.setAttribute("beishu", beishu);
			request.setAttribute("betcode", betcode);
			request.setAttribute("addnumber", addnumber);
			return "wap/ElevenDuoJin/ForwardThreeZXFS";
		}
		logger.info("投注注码:"+toBetcode+"注数："+zhushu+"投注金额，  注数* 倍数 * 单注2元 ："+amount);
		request.setAttribute("zhushu", zhushu+"");
		request.setAttribute("beishu", beishu);
		request.setAttribute("type", type);
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("term", term);
		request.setAttribute("amount", String.valueOf(amount));
		request.setAttribute("toBetcode", toBetcode);
		request.setAttribute("viewBetcode", viewBetcode);
		return "wap/ElevenDuoJin/generalBetDetail";
	}
	
	/**
	 * 胆拖 详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/todantuoDetail.jspx",method=RequestMethod.POST)
	public String todantuoDetail(HttpServletRequest request, HttpServletResponse response) throws JSONException{
	
		//获取参数
		String danma = request.getParameter("danma");
		String tuoma = request.getParameter("tuoma");
		String beishu = request.getParameter("beishu");
		String addnumber = request.getParameter("addnumber");
		String type = request.getParameter("type");
		String term = CommonUtil.getTerm("T01012");
		String parameter = "danma="+danma+"&tuoma="+tuoma+"&beishu="+beishu+"&type="+type+"&addnumber="+addnumber;
		request.getSession().setAttribute("parameter", parameter);
		//对参数进行校验
		String checkResult = new ElevenDuoJin().checkBetCodeDR(danma,tuoma, beishu, addnumber,type);
		logger.info("注码验证结果：返回pass为通过"+checkResult);
		if(!checkResult.equals("pass")){
			request.setAttribute("message",checkResult );
			request.setAttribute("beishu", beishu);
			request.setAttribute("danma", danma);
			request.setAttribute("tuoma", tuoma);
			request.setAttribute("addnumber", addnumber);
			if(type.equals("DR2"))return "wap/ElevenDuoJin/dantuoR2";
			if(type.equals("DR3"))return "wap/ElevenDuoJin/dantuoR3";
			if(type.equals("DR4"))return "wap/ElevenDuoJin/dantuoR4";
			if(type.equals("DR5"))return "wap/ElevenDuoJin/dantuoR5";
			if(type.equals("DR6"))return "wap/ElevenDuoJin/dantuoR6";
			if(type.equals("DR7"))return "wap/ElevenDuoJin/dantuoR7";
			if(type.equals("DZX2"))return "wap/ElevenDuoJin/dantuoGroup2";
			if(type.equals("DZX3"))return "wap/ElevenDuoJin/dantuoGroup3";
		}
		
		logger.info("投注详细信息验证通过:胆码="+danma+"拖码="+tuoma+"beishu"+ beishu + "addnumber" + addnumber);
		
		//注码处理  将生成两种注码格式      viewBetcode(展示注码)  toBetcode (用于投注的注码)
		//根据type值,获取投注的注码
		String betcode = danma+"|"+tuoma;
		JSONObject json =new ElevenDuoJin().tobetcode(type, betcode);
		String toBetcode = json.getString("toBetcode");
		String viewBetcode = json.getString("viewBetcode");
		//金额验证
		int  zhushu = json.getInt("zhushu");
		//金额算法   注数* 倍数 * 单注2元 * 追期期数
		int amount  = zhushu * Integer.parseInt(beishu) * 2;
		if (!CommonUtil.TCverifyAmount(amount)) {
			logger.info("amount 投注金额大于2万"+amount);
			request.setAttribute("message", MessageUtil.TC_AmountError);
			request.setAttribute("beishu", beishu);
			request.setAttribute("danma", danma);
			request.setAttribute("tuoma", tuoma);
			request.setAttribute("addnumber", addnumber);
			if(type.equals("DR2"))return "wap/ElevenDuoJin/dantuoR2";
			if(type.equals("DR3"))return "wap/ElevenDuoJin/dantuoR3";
			if(type.equals("DR4"))return "wap/ElevenDuoJin/dantuoR4";
			if(type.equals("DR5"))return "wap/ElevenDuoJin/dantuoR5";
			if(type.equals("DR6"))return "wap/ElevenDuoJin/dantuoR6";
			if(type.equals("DR7"))return "wap/ElevenDuoJin/dantuoR7";
			if(type.equals("DZX2"))return "wap/ElevenDuoJin/dantuoGroup2";
			if(type.equals("DZX3"))return "wap/ElevenDuoJin/dantuoGroup3";
		}
		logger.info("投注注码:"+toBetcode+"注数："+zhushu+"  投注金额： 注数* 倍数 * 单注2元 ="+amount);
		request.setAttribute("zhushu", zhushu+"");
		request.setAttribute("beishu", beishu);
		request.setAttribute("type", type);
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("term", term);
		request.setAttribute("amount", String.valueOf(amount));
		request.setAttribute("toBetcode", toBetcode);
		request.setAttribute("viewBetcode", viewBetcode);
		return "wap/ElevenDuoJin/DantuoBetDetail";
	}
	/**
	 * 单式机选详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/autoBetDetail.jspx",method=RequestMethod.POST)
    public String autoBetDetail(HttpServletRequest request, HttpServletResponse response) throws JSONException{
    
		// 传来的参数
		String autocode = request.getParameter("autocode");
		String type = request.getParameter("type");
		String term = CommonUtil.getTerm("T01012");
		String beishu = request.getParameter("beishu");
		String addnumber = request.getParameter("addnumber");
		String parameter = "autocode="+autocode+"&type="+type+"&beishu="+beishu+"&addnumber="+addnumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" autocode :" + autocode + " type :" +type+" term:" +term+"&beishu="+beishu+"&addnumber="+addnumber);
		//检查注数的合法性
		String resultMassage =ElevenDuoJin.autoCheckZhushu(autocode,beishu,addnumber);
		  logger.info("注码验证结果：返回pass为通过"+resultMassage);
		    if(!resultMassage.equals("pass")){
		    	request.setAttribute("message",resultMassage);
		    	request.setAttribute("autocode", autocode);
		    	request.setAttribute("beishu", beishu);
		    	request.setAttribute("addnumber", addnumber);
		    	request.setAttribute("type", type);
		    	if(type.equals("autoR1"))return "wap/ElevenDuoJin/optionOneAutoDS";
				if(type.equals("autoR2"))return "wap/ElevenDuoJin/optionTwoAutoDS";
				if(type.equals("autoR3"))return "wap/ElevenDuoJin/optionThreeAutoDS";
				if(type.equals("autoR4"))return "wap/ElevenDuoJin/optionFourAutoDS";
				if(type.equals("autoR5"))return "wap/ElevenDuoJin/optionFiveAutoDS";
				if(type.equals("autoR6"))return "wap/ElevenDuoJin/optionSixAutoDS";
				if(type.equals("autoR7"))return "wap/ElevenDuoJin/optionSevenAutoDS";
				if(type.equals("autoR8"))return "wap/ElevenDuoJin/optionEightAutoDS";
				if(type.equals("autoG2"))return "wap/ElevenDuoJin/ForwardTwoGroupAutoDS";
				if(type.equals("autoG3"))return "wap/ElevenDuoJin/ForwardThreeGroupAutoDS";
		    }
		    logger.info("投注详细信息验证通过：autocode="+autocode +"type="+ type);
			// 转换半角
		    autocode = CommonUtil.QJToBJChange(autocode);
			type = CommonUtil.QJToBJChange(type);
		 // 根据type的类型生成对应的zhuma
			String zhuma = "";
			JSONObject json = null;;
		 	if(type.equals("autoR1")){
		 		zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 1);
		 		json =ElevenDuoJin.toAutoDSbetcode("R1",zhuma,autocode);
		 	}
			if(type.equals("autoR2")){
				zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 2);
				json =ElevenDuoJin.toAutoDSbetcode("R2", zhuma,autocode);
			}
			if(type.equals("autoR3")){
				zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 3);
				json =ElevenDuoJin.toAutoDSbetcode("R3", zhuma,autocode);
			}
			if(type.equals("autoR4")){
				zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 4);
				json =ElevenDuoJin.toAutoDSbetcode("R4", zhuma,autocode);
			}
			if(type.equals("autoR5")){
				zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 5);
				json =ElevenDuoJin.toAutoDSbetcode("R5", zhuma,autocode);
			}
			if(type.equals("autoR6")){
				zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 6);
				json =ElevenDuoJin.toAutoDSbetcode("R6", zhuma,autocode);
			}
			if(type.equals("autoR7")){
				zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 7);
				json = ElevenDuoJin.toAutoDSbetcode("R7", zhuma,autocode);
			}
			if(type.equals("autoR8")){
				zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 8);
				json = ElevenDuoJin.toAutoDSbetcode("R8", zhuma,autocode);
			}
			if(type.equals("autoG2")){
				zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 2);
				json =ElevenDuoJin.toAutoDSbetcode("G2", zhuma,autocode);
			}
			if(type.equals("autoG3")){
				zhuma = ElevenDuoJin.autoBetCode(Integer.valueOf(autocode), 3);
				json = ElevenDuoJin.toAutoDSbetcode("G3", zhuma,autocode);
			}
			String toBetcode = json.getString("toBetcode");
			String viewBetcode = json.getString("viewBetcode");
			//金额验证
			int zhushu = json.getInt("zhushu");
			//金额算法   注数* 倍数 * 单注2元 * 追期期数    (追期和倍数都是1)
			int amount  = zhushu * 2*Integer.parseInt(beishu);
			if (!CommonUtil.TCverifyAmount(amount)) {
				logger.info("amount 投注金额大于2万"+amount);
				request.setAttribute("beishu", beishu);
		    	request.setAttribute("addnumber", addnumber);
				request.setAttribute("message", MessageUtil.TC_AmountError);
		    	request.setAttribute("autocode", autocode);
		    	request.setAttribute("type", type);
			  	if(type.equals("autoR1"))return "wap/ElevenDuoJin/optionOneAutoDS";
				if(type.equals("autoR2"))return "wap/ElevenDuoJin/optionTwoAutoDS";
				if(type.equals("autoR3"))return "wap/ElevenDuoJin/optionThreeAutoDS";
				if(type.equals("autoR4"))return "wap/ElevenDuoJin/optionFourAutoDS";
				if(type.equals("autoR5"))return "wap/ElevenDuoJin/optionFiveAutoDS";
				if(type.equals("autoR6"))return "wap/ElevenDuoJin/optionSixAutoDS";
				if(type.equals("autoR7"))return "wap/ElevenDuoJin/optionSevenAutoDS";
				if(type.equals("autoR8"))return "wap/ElevenDuoJin/optionEightAutoDS";
				if(type.equals("autoG2"))return "wap/ElevenDuoJin/ForwardTwoGroupAutoDS";
				if(type.equals("autoG3"))return "wap/ElevenDuoJin/ForwardThreeGroupAutoDS";
			}
			logger.info("投注注码:"+toBetcode+"注数："+zhushu+"投注金额，  注数* 单注2元 ："+amount);
			request.setAttribute("zhushu", zhushu+"");
			request.setAttribute("beishu", beishu);
			request.setAttribute("type", type);
			request.setAttribute("addnumber", addnumber);
			request.setAttribute("term", term);
			request.setAttribute("amount", String.valueOf(amount));
			request.setAttribute("autocode", autocode);
			request.setAttribute("toBetcode", toBetcode);
			request.setAttribute("viewBetcode", viewBetcode);
			return "wap/ElevenDuoJin/generalDSJixuanBetDetail";
    }
	/**
	 * 复式机选详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws JSONException 
	 */
	@RequestMapping(value="/autoFSBetDetail.jspx",method=RequestMethod.POST)
    public String autoFSBetDetail(HttpServletRequest request, HttpServletResponse response) throws JSONException{
    
		// 传来的参数
		String autoFScode = request.getParameter("autoFScode");
		String type = request.getParameter("type");
		String term = CommonUtil.getTerm("T01012");
		String beishu = request.getParameter("beishu");
		String addnumber = request.getParameter("addnumber");
		String parameter = "autoFScode="+autoFScode+"&type="+type+"&beishu="+beishu+"&addnumber="+addnumber;
		request.getSession().setAttribute("parameter", parameter);
		logger.info(" autoFScode :" + autoFScode + " type :" +type+" term:" +term+"&beishu="+beishu+"&addnumber="+addnumber);
		//检查注数的合法性
		String resultMassage =ElevenDuoJin.autoCheckGeShu(autoFScode,type,beishu,addnumber);
		  logger.info("注码验证：返回pass为通过"+resultMassage);
		    if(!resultMassage.equals("pass")){
		    	request.setAttribute("message",resultMassage);
		    	request.setAttribute("autoFScode", autoFScode);
		    	request.setAttribute("beishu", beishu);
		    	request.setAttribute("addnumber", addnumber);
		    	request.setAttribute("type", type);
		    	if(type.equals("autoR1"))return "wap/ElevenDuoJin/optionOneAutoFS";
				if(type.equals("autoR2"))return "wap/ElevenDuoJin/optionTwoAutoFS";
				if(type.equals("autoR3"))return "wap/ElevenDuoJin/optionThreeAutoFS";
				if(type.equals("autoR4"))return "wap/ElevenDuoJin/optionFourAutoFS";
				if(type.equals("autoR5"))return "wap/ElevenDuoJin/optionFiveAutoFS";
				if(type.equals("autoR6"))return "wap/ElevenDuoJin/optionSixAutoFS";
				if(type.equals("autoR7"))return "wap/ElevenDuoJin/optionSevenAutoFS";
				if(type.equals("autoG2"))return "wap/ElevenDuoJin/ForwardTwoGroupAutoFS";
				if(type.equals("autoG3"))return "wap/ElevenDuoJin/ForwardThreeGroupAutoFS";
		    }
		    logger.info("投注详细信息验证通过：autoFScode="+autoFScode +"type"+ type);
			// 转换半角
		    autoFScode = CommonUtil.QJToBJChange(autoFScode);
			type = CommonUtil.QJToBJChange(type);
		 // 根据type的类型生成对应的zhuma
			String zhuma = "";
			JSONObject json = null;;
		 	if(type.equals("autoR1")){
		 		zhuma = ElevenDuoJin.RMultipleAutoNum(Integer.valueOf(autoFScode));
		 		json =new ElevenDuoJin().tobetcode("R1", zhuma);
		 	}
			if(type.equals("autoR2")){
				zhuma = ElevenDuoJin.RMultipleAutoNum(Integer.valueOf(autoFScode));
				json =new ElevenDuoJin().tobetcode("R2", zhuma);
			}
			if(type.equals("autoR3")){
				zhuma = ElevenDuoJin.RMultipleAutoNum(Integer.valueOf(autoFScode));
				json =new ElevenDuoJin().tobetcode("R3", zhuma);
			}
			if(type.equals("autoR4")){
				zhuma = ElevenDuoJin.RMultipleAutoNum(Integer.valueOf(autoFScode));
				json =new ElevenDuoJin().tobetcode("R4", zhuma);
			}
			if(type.equals("autoR5")){
				zhuma = ElevenDuoJin.RMultipleAutoNum(Integer.valueOf(autoFScode));
				json =new ElevenDuoJin().tobetcode("R5", zhuma);
			}
			if(type.equals("autoR6")){
				zhuma = ElevenDuoJin.RMultipleAutoNum(Integer.valueOf(autoFScode));
				json =new ElevenDuoJin().tobetcode("R6", zhuma);
			}
			if(type.equals("autoR7")){
				zhuma = ElevenDuoJin.RMultipleAutoNum(Integer.valueOf(autoFScode));
				json =new ElevenDuoJin().tobetcode("R7", zhuma);
			}
			if(type.equals("autoG2")){
				zhuma = ElevenDuoJin.RMultipleAutoNum(Integer.valueOf(autoFScode));
				json =new ElevenDuoJin().tobetcode("G2", zhuma);
			}
			if(type.equals("autoG3")){
				zhuma = ElevenDuoJin.RMultipleAutoNum(Integer.valueOf(autoFScode));
				json =new ElevenDuoJin().tobetcode("G3", zhuma);
			}
			String toBetcode = json.getString("toBetcode");
			String viewBetcode = json.getString("viewBetcode");
			//金额验证
			int zhushu = json.getInt("zhushu");
			//金额算法   注数* 倍数 * 单注2元 * 追期期数    (追期和倍数都是1)
			int amount  = zhushu * 2*Integer.parseInt(beishu);
			if (!CommonUtil.TCverifyAmount(amount)) {
				logger.info("amount 投注金额大于2万"+amount);
				request.setAttribute("beishu", beishu);
		    	request.setAttribute("addnumber", addnumber);
				request.setAttribute("message", MessageUtil.TC_AmountError);
		    	request.setAttribute("autoFScode", autoFScode);
		    	request.setAttribute("type", type);
			  	if(type.equals("autoR1"))return "wap/ElevenDuoJin/optionOneAutoFS";
				if(type.equals("autoR2"))return "wap/ElevenDuoJin/optionTwoAutoFS";
				if(type.equals("autoR3"))return "wap/ElevenDuoJin/optionThreeAutoFS";
				if(type.equals("autoR4"))return "wap/ElevenDuoJin/optionFourAutoFS";
				if(type.equals("autoR5"))return "wap/ElevenDuoJin/optionFiveAutoFS";
				if(type.equals("autoR6"))return "wap/ElevenDuoJin/optionSixAutoFS";
				if(type.equals("autoR7"))return "wap/ElevenDuoJin/optionSevenAutoFS";
				if(type.equals("autoG2"))return "wap/ElevenDuoJin/ForwardTwoGroupAutoFS";
				if(type.equals("autoG3"))return "wap/ElevenDuoJin/ForwardThreeGroupAutoFS";
			}
			logger.info("投注注码:"+toBetcode+"注数："+zhushu+"投注金额，  注数* 单注2元 ："+amount);
			request.setAttribute("zhushu", zhushu+"");
			request.setAttribute("beishu", beishu);
			request.setAttribute("type", type);
			request.setAttribute("addnumber", addnumber);
			request.setAttribute("term", term);
			request.setAttribute("amount", String.valueOf(amount));
			request.setAttribute("autoFScode", autoFScode);
			request.setAttribute("toBetcode", toBetcode);
			request.setAttribute("viewBetcode", viewBetcode);
			return "wap/ElevenDuoJin/generalFSJixuanBetDetail";
    }

}
