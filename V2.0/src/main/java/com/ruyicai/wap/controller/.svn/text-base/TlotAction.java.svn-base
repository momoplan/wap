package com.ruyicai.wap.controller;
/**
 * TlotAction 彩票系统Action类 
 * 实现用户投注、赠彩、追号套餐定制、投注查询、中奖查询、开奖查询等功能功能
 * @author 
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * 网址：www.ruyicai.com
* 创建者：
* 创建日期：
* 修改记录：
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;

/**
 * 彩票系统Action类
 * 实现用户投注、赠彩、追号套餐定制、投注查询、中奖查询、开奖查询等功能功能
 */
@RequestMapping("/touzhu")
@Controller
public class TlotAction{
	private static final Logger logger = Logger.getLogger(TlotAction.class);
	/**
	 * 用户账户明细查询
	 * 根据用户编号、开始日期、结束日期查询
	 * @author 
	 */
	@RequestMapping(value="/accountDetailSelect.jspa")
	public String accountDetailSelect(HttpServletRequest request, HttpServletResponse response) {
		
		String begintime,endtime,startPage,endPage;
		//用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData
		//不存在 那么就正常运行获取表单 否则 从request.getAttribute 中获取 与表单同名同类型的obj
		if(request.getAttribute("outFormData")==null||request.getAttribute("outFormData").equals("")){
			//获取页面参数
			begintime =request.getParameter("begintime");	//开始时间
			endtime = request.getParameter("endtime");	//结束时间
			startPage=request.getParameter("beginId")==null?"0":request.getParameter("beginId");
			endPage = request.getParameter("endId")==null?"10":request.getParameter("endId");
		}else{//获取request.getAttribute中的存储
			String[] begintimes = request.getAttribute("begintime")==null?null:(String[])request.getAttribute("begintime");
			String[] endtimes = request.getAttribute("endtime")==null?null:(String[])request.getAttribute("endtime");
			String[] startPages =request.getAttribute("beginId")==null?null:(String[])request.getAttribute("beginId");
			String[] endPages = request.getAttribute("endId")==null?null:(String[])request.getAttribute("endId");
			//获取输入页面的参数
			begintime = begintimes==null||begintimes[0].equals("")?"0":begintimes[0]; //注数
			endtime = endtimes==null||endtimes[0].equals("")?"0":endtimes[0]; //倍数
			startPage = startPages==null||startPages[0].equals("")?"0":startPages[0]; //
			endPage = endPages==null||endPages[0].equals("")?"0":endPages[0]; //
		}
		String nowPage = request.getParameter("nowPage")==null?"0":request.getParameter("nowPage");
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String message = VerificationAlgorithmUtil.getDateByString(begintime, endtime);
		String maxResult="15";
		if(message!=null)
		{
			request.setAttribute("message", message);
			return "wap/accountDetail/accountDetail";
		}

		JSONObject jsonObject= CommonUtil.getTtransaction(userno, begintime, endtime, nowPage,maxResult);
		logger.info("startPage:"+startPage);
		logger.info("endPage:"+endPage);
		request.setAttribute("jsonObject", jsonObject);
		request.setAttribute("nowpage", nowPage);
		return "wap/accountDetail/accountDetailQueryed";
	} 
	
}
