package com.ruyicai.wap.controller;

/**
 * BetingSelectAction 用于获取服务器端数据 反回给用户查看
 * @author 鞠牧
 * (C)版权由北京金软通科技发展有限公司所有
 * 网址：www.ruyicai.com
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruyicai.wap.bean.Present;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.controller.vo.BetBean;
import com.ruyicai.wap.controller.vo.WiningBean;
import com.ruyicai.wap.util.BetingSelect;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.JsonToJrtLotUtil;
import com.ruyicai.wap.util.MessageUtil;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;
@RequestMapping("/BetingSelectAction")
@Controller
public class BetingSelectAction {

	private static final Logger logger = Logger.getLogger(BetingSelectAction.class);

	/**
	 * 投注查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	@RequestMapping(value="/bettingSelect.jspa")
	public String bettingSelect(HttpServletRequest request, HttpServletResponse response) {
		try {
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();
			String begintime = request.getParameter("startDate") == null ? ""
					: request.getParameter("startDate"); // 开始时间
			String endtime = request.getParameter("stopDate") == null ? ""
					: request.getParameter("stopDate"); // 结束时间
			String beginId = request.getParameter("beginId") == null ? "0"
					: request.getParameter("beginId");
			String endId = request.getParameter("endId") == null ? "5"
					: request.getParameter("endId");
			String nowPage = request.getParameter("nowPage") == null ? "1"
					: request.getParameter("nowPage");
			String type = request.getParameter("type");
			String message = VerificationAlgorithmUtil.getDateByString(
					begintime, endtime);
		
			logger.info("userno:"
					+ userno + ";begintime:" + begintime + ";endtime:"
					+ endtime + ";beginId:" + beginId + ";endId:" + endId
					+ ";nowPage:" + nowPage + ";验证开始日期与结束日期message:" + message);

			if (message != null && !begintime.equals("") && !endtime.equals("")) {
				request.setAttribute("messageDate", message);
				return "wap/query/betingSelect";
			}
			//近期查询
			if("JQ".equals(type)){
			if( ("".equals(begintime)||"".equals(endtime))){
				 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				 Date date = new Date();
				 endtime = format.format(date);
				 begintime = (Integer.parseInt(endtime)-7)+"";
			}
			}
			request.setAttribute("startDate", begintime);
			request.setAttribute("endDate", endtime);
			request.setAttribute("nowPage", nowPage);

			// 3.将查询条件存入map中传给后台
			logger.info("============投注查询开始===========");
			Map map = new HashMap();
			map.put("userno", userno);
			map.put("batchCode", "");
			map.put("lotNo", "");
			map.put("startDate", begintime);
			map.put("stopDate", endtime);
			map.put("startLine", beginId);
			map.put("stopLine", endId);
            
			JSONObject obj = BetingSelect.getBetSelect(map);
			JSONObject objvalue = obj.getJSONObject("value");
			JSONArray error_code_list = objvalue.getJSONArray("list");
			JSONObject torder = obj.getJSONObject("torder");
			// 4.得到返回的查询记录
			int count = 0;
			List list = new ArrayList();
			BetBean bb = null;
			String orderBetType = "";
			orderBetType = torder.getString("bettype");
			if("0".equals(orderBetType)){
				orderBetType = "追号";
			}else if("1".equals(orderBetType)){
				orderBetType = "套餐";
			}else if("2".equals(orderBetType)){
				orderBetType = "普通投注";
			}else if("3".equals(orderBetType)){
				orderBetType = "合买";
			}else if("4".equals(orderBetType)||"5".equals(orderBetType)){
				orderBetType = "赠送";
			}
			if (error_code_list != null && error_code_list.size() > 0) {
				for (int i = 0; i < error_code_list.size(); i++) {
					bb = new BetBean();
					JSONObject json = error_code_list.getJSONObject(i);
					// 4.2从map中
					Map jsonMap = CommonUtil.getBetCodeContentToModel(json);
					bb.setPlay_name(json.getString("lotno")); // 彩种名称
					bb.setBatchcode(json.getString("batchcode")); // 期号
					
					bb.setSell_cate(CommonUtil.setBetTimeByFormat(json.getString("ordertime"))); // 投注时间
					bb.setAmt(torder.getLong("orderamt")); // 投注金额
					bb.setBetcodeAll(json.getString("betcode")); // 注码
					bb.setBetcode(jsonMap);
					bb.setMaxLine(Integer.parseInt(objvalue
							.getString("totalResult")));
					bb.setCash_date_time(json.getString("validcode"));// 期结日期
					bb.setState(json.containsKey("state") != true ? "" : json
							.getString("state"));// 彩票记录的状态
					bb.setBetType((String) jsonMap.get("bet_code_type"));
					bb.setMulNo((String) json.getString("lotmulti")); // 倍数
					bb.setOrderBetType(orderBetType); // 订单投注类型
					list.add(bb);
				}
			}
			request.setAttribute("error_code_list", list);
			request.setAttribute("count", objvalue.getString("totalResult"));
			request.setAttribute("maxResult", objvalue.getString("maxResult"));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message",
					MessageUtil.BetingSelectAction_ExceptinMsg);
			logger.error("获取投注信息时出现异常:" + e.getMessage());
			return "wap/wapindex";
		}
		logger.info("============投注查询结束===========");
		return "wap/query/betingSelect";
	}
	/**
	 * 订单投注列表查询(现用)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	@RequestMapping(value="/bettingOrdersSelect.jspa")
	public String bettingOrdersSelect(HttpServletRequest request, HttpServletResponse response) {
		try {
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();
			String begintime = request.getParameter("startDate") == null ? ""
					: request.getParameter("startDate"); // 开始时间
			String endtime = request.getParameter("stopDate") == null ? ""
					: request.getParameter("stopDate"); // 结束时间
			String beginId = request.getParameter("beginId") == null ? "0"
					: request.getParameter("beginId");
			String endId = request.getParameter("endId") == null ? "5"
					: request.getParameter("endId");
			String nowPage = request.getParameter("nowPage") == null ? "1"
					: request.getParameter("nowPage");
			String type = request.getParameter("type");
			String message = VerificationAlgorithmUtil.getDateByString(
					begintime, endtime);
		
			logger.info("userno:"
					+ userno + ";begintime:" + begintime + ";endtime:"
					+ endtime + ";beginId:" + beginId + ";endId:" + endId
					+ ";nowPage:" + nowPage + ";验证开始日期与结束日期message:" + message);

			if (message != null && !begintime.equals("") && !endtime.equals("")) {
				request.setAttribute("messageDate", message);
				return "wap/query/betingOrdersSelect";
			}
			//近期查询
			if("JQ".equals(type)){
			if( ("".equals(begintime)||"".equals(endtime))){
				 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				 Date date = new Date();
				 endtime = format.format(date);
				 begintime = format.format(new Date(new Date().getTime() - 7 * 24
										* 60 * 60 * 1000));
			}
			}
			request.setAttribute("startDate", begintime);
			request.setAttribute("endDate", endtime);
			request.setAttribute("nowPage", nowPage);

			// 3.将查询条件存入map中传给后台
			logger.info("============投注查询开始===========");
			Map map = new HashMap();
			map.put("userno", userno);
			map.put("batchCode", "");
			map.put("lotNo", "");
			map.put("startDate", begintime);
			map.put("stopDate", endtime);
			map.put("startLine", beginId);
			map.put("stopLine", endId);
            
			JSONObject obj = BetingSelect.getTorders(map);
			JSONObject objvalue = obj.getJSONObject(("value"));

			JSONArray error_code_list = objvalue.getJSONArray("list");
			// 4.得到返回的查询记录
			int count = 0;
			List list = new ArrayList();
			BetBean bb = null;
			String orderBetType = "";
			if (error_code_list != null && error_code_list.size() > 0) {
				for (int i = 0; i < error_code_list.size(); i++) {
					bb = new BetBean();
					JSONObject json = error_code_list.getJSONObject(i);
					JSONObject json1 = json.getJSONObject("torder");
					JSONArray array = json.getJSONArray("tuseraction");
					JSONObject json2 = array.getJSONObject(0);
					orderBetType = json1.getString("bettype");
					if("0".equals(orderBetType)){
						orderBetType = "追号";
					}else if("1".equals(orderBetType)){
						orderBetType = "套餐";
					}else if("2".equals(orderBetType)){
						orderBetType = "普通投注";
					}else if("3".equals(orderBetType)){
						orderBetType = "合买";
					}else if("4".equals(orderBetType)||"5".equals(orderBetType)){
						orderBetType = "赠送";
					}
					// 4.2从map中
					Map jsonMap = SelectAllUtil.getBetcodeFormat(json1);
					if("J00005".equals(json1.getString("lotno"))||"J00006".equals(json1.getString("lotno"))||"J00007".equals(json1.getString("lotno"))||"J00008".equals(json1.getString("lotno"))){
						bb.setPlay_name(CommonUtil.getLotnameByLotno(json1.getString("lotno")));
						bb.setSell_cate(CommonUtil.setBetTimeByFormat(json1.getString("modifytime"))); // 投注时间
						bb.setAmt(json1.getLong("orderamt")); // 投注金额
						bb.setMulNo((String) json1.getString("lotmulti")); // 倍数
						String displayTlots = json.getString("displayTlots");
						if("false".equals(displayTlots)){
							bb.setBetcodeAll("保密!");
						}else{
							bb.setBetcodeAll(jsonMap.get("betCodeView")+"");
						}
//						bb.setBetcodeAll(jsonMap.get("betCodeView")+"");
						bb.setBetType((String) jsonMap.get("betType"));
						bb.setPrizeamt(json2.getString("prizeamt"));
						bb.setOrderBetType(orderBetType);
						bb.setType("JC");

					}else if("J00001".equals(json1.getString("lotno"))||"J00002".equals(json1.getString("lotno"))||"J00003".equals(json1.getString("lotno"))||"J00004".equals(json1.getString("lotno"))){
						bb.setPlay_name(CommonUtil.getLotnameByLotno(json1.getString("lotno")));
						bb.setSell_cate(CommonUtil.setBetTimeByFormat(json1.getString("modifytime"))); // 投注时间
						bb.setAmt(json1.getLong("orderamt")); // 投注金额
						bb.setMulNo((String) json1.getString("lotmulti")); // 倍数
						String displayTlots = json.getString("displayTlots");
						if("false".equals(displayTlots)){
							bb.setBetcodeAll("保密!");
						}else{
							bb.setBetcodeAll(jsonMap.get("betCodeView")+"");
						}
//						bb.setBetcodeAll(jsonMap.get("betCodeView")+"");
						bb.setBetType((String) jsonMap.get("betType"));
						bb.setPrizeamt(json2.getString("prizeamt"));
						bb.setOrderBetType(orderBetType);
						bb.setType("JCzq");
					}else if("T01003".equals(json1.getString("lotno"))||"T01004".equals(json1.getString("lotno"))||"T01005".equals(json1.getString("lotno"))||"T01006".equals(json1.getString("lotno"))){
						bb.setPlay_name(CommonUtil.getLotnameByLotno(json1.getString("lotno")));
						bb.setSell_cate(CommonUtil.setBetTimeByFormat(json1.getString("modifytime"))); // 投注时间
						bb.setAmt(json1.getLong("orderamt")); // 投注金额
						bb.setMulNo((String) json1.getString("lotmulti")); // 倍数
						String displayTlots = json.getString("displayTlots");
						if("false".equals(displayTlots)){
							bb.setBetcodeAll("保密!");
						}else{
							bb.setBetcodeAll(jsonMap.get("betCodeView")+"");
						}
//						bb.setBetcodeAll(jsonMap.get("betCodeView")+"");
						bb.setPrizeamt(json2.getString("prizeamt"));
						bb.setBatchcode(json1.getString("batchcode")); // 期号
						bb.setOrderBetType(orderBetType);
						bb.setType("ZC");
					}else{
						bb.setPlay_name(json1.getString("lotno")); // 彩种名称
						bb.setBatchcode(json1.getString("batchcode")); // 期号
						
						bb.setSell_cate(CommonUtil.setBetTimeByFormat(json1.getString("modifytime"))); // 投注时间
						bb.setAmt(json1.getLong("orderamt")); // 投注金额
						String displayTlots = json.getString("displayTlots");
						if("false".equals(displayTlots)){
							bb.setBetcodeAll("保密!");
						}else{
							bb.setBetcodeAll(jsonMap.get("betCodeView")+"");
						}
//						bb.setBetcodeAll(jsonMap.get("betCodeView")+"");
						bb.setBetcode(jsonMap);
						// count = objvalue.getString("totalResult"); //返回最大记录数
						bb.setMaxLine(Integer.parseInt(objvalue
								.getString("totalResult")));
						bb.setCash_date_time(json1.getString("endtime"));// 期结日期
						bb.setState(json.containsKey("state") != true ? "" : json
								.getString("state"));// 彩票记录的状态
						bb.setBetType((String) jsonMap.get("memo"));
						bb.setMulNo((String) json1.getString("lotmulti")); // 倍数
						bb.setPrizeamt(json2.getString("prizeamt"));
						bb.setOrderBetType(orderBetType); // 订单投注类型

					}
					list.add(bb);
				}
			}
			request.setAttribute("error_code_list", list);
			request.setAttribute("count", objvalue.getString("totalResult"));
			request.setAttribute("maxResult", objvalue.getString("maxResult"));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message",
					MessageUtil.BetingSelectAction_ExceptinMsg);
			logger.error("获取投注信息时出现异常:" + e.getMessage());
			return "wap/userinfo/login";
		}
		logger.info("============投注查询结束===========");
		return "wap/query/betingOrdersSelect";
	}
	/**
	 * 获取中奖信息(新lottery)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	@SuppressWarnings( { "unchecked", "rawtypes" })
	@RequestMapping(value="/getWiningSelectTorders.jspa")
	public String getWiningSelectTorders(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();

			String beginId = request.getParameter("beginId") == null ? "0"
					: request.getParameter("beginId");
			String endId = request.getParameter("endId") == null ? "5"
					: request.getParameter("endId");
			String nowPage = request.getParameter("nowPage") == null ? "1"
					: request.getParameter("nowPage");
			request.setAttribute("nowPage", nowPage);

			// 取得投注的返回码
			Map map = new HashMap();
			map.put("userno", userno);
			map.put("lotNo", "");
			map.put("stopDate", sdf.format(new Date()));
			map.put("startDate", sdf.format(new Date(new Date().getTime()
					- (120*30 * 24 * 60 * 60 * 1000l))));
			map.put("startLine", beginId);
			map.put("stopLine", endId);

			JSONObject error_code_list = BetingSelect.getWinByTorders(map);
			List list = new ArrayList();
			if(!"null".equals(error_code_list.getString("value")) && !"400004".equals(error_code_list.getString("errorCode"))){
				JSONObject objectList = error_code_list.getJSONObject("value");
				JSONArray arrayList = objectList.getJSONArray("list");
				int count = 0;
				if (error_code_list != null && arrayList.size() > 0) {
					for (int i = 0; i < arrayList.size(); i++) {
						WiningBean wb = new WiningBean();
						JSONObject json = arrayList.getJSONObject(i);
						JSONObject object = json.getJSONObject("torder");
//						Map jsonMap = CommonUtil.getBetCodeContentToModel(object);
						Map jsonMap =SelectAllUtil.getBetcodeFormat(object);
						if("J00005".equals(object.getString("lotno"))||"J00006".equals(object.getString("lotno"))||"J00007".equals(object.getString("lotno"))||"J00008".equals(object.getString("lotno"))){
							wb.setPlay_name(CommonUtil.getLotnameByLotno(object.getString("lotno")));
							wb.setSell_cate(CommonUtil.setBetTimeByFormat(object.getString("modifytime"))); // 投注时间
							wb.setPrizeamt(object.getLong("orderprize")); // 
							wb.setMulNo((String) object.getString("lotmulti")); // 倍数
							wb.setBetcodeAll(jsonMap.get("betCodeView")+"");
							wb.setBetType((String) jsonMap.get("betType"));
							wb.setType("JC");

						}else if("J00001".equals(object.getString("lotno"))||"J00002".equals(object.getString("lotno"))||"J00003".equals(object.getString("lotno"))||"J00004".equals(object.getString("lotno"))){
							wb.setPlay_name(CommonUtil.getLotnameByLotno(object.getString("lotno")));
							wb.setSell_cate(CommonUtil.setBetTimeByFormat(object.getString("modifytime"))); // 投注时间
							wb.setPrizeamt(object.getLong("orderprize")); // 
							wb.setMulNo((String) object.getString("lotmulti")); // 倍数
							wb.setBetcodeAll(jsonMap.get("betCodeView")+"");
							wb.setBetType((String) jsonMap.get("betType"));
							wb.setType("JCzq");
						}else{
							wb.setPlay_name(object.getString("lotno"));
							wb.setBatchcode(object.getString("batchcode"));
							wb.setPrizeinfo(object.containsKey("prizeinfo") != true ? ""
									: object.getString("prizeinfo"));// prizeinfo
							wb.setPrizeamt(object.getLong("orderprize"));
							wb.setEncash_flag(object.getString("prizestate"));
							wb.setSell_cate(CommonUtil.setBetTimeByFormat(object.getString("modifytime")));
							wb.setBetcodeAll(object.getString("betcode"));
							wb.setBetcode(jsonMap);
							
						}
						count = objectList.getInt("totalResult");
						wb.setMaxLine(count);
						list.add(wb);
					}
				}
				request.setAttribute("error_code_list", list);
				request.setAttribute("count", count);
			}else{
				request.setAttribute("error_code_list", list);
				request.setAttribute("count", 0);
				
			}
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message",
					MessageUtil.BetingSelectAction_ExceptinMsg);
			logger.error("获取中奖信息时出现错误:" + e.getMessage());
			return "wap/wapindex";
		}
		return "wap/query/winingSelect";
	}

	/**
	 * 赠送查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectSenderPresentDetails1.jspa")
	public String selectSenderPresentDetails1(Model model, HttpServletRequest request, HttpServletResponse response) {
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String startLine = request.getParameter("startLine") == null ? "0"
				: request.getParameter("startLine");
		String endLine = request.getParameter("endLine") == null ? "10"
				: request.getParameter("endLine");
		// 取得投注的返回码
		logger.info("赠送查询开始");
		String result = SelectAllUtil.selectSenderPresentDetails(userno, startLine, endLine);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			Present present = null;
			List<Present> presents = new ArrayList<Present>();
			for(int i=0;i<jsonArray.size()&&i<10;i++){
				present = new Present();
				JSONObject object = jsonArray.getJSONObject(i);
				present.setMobile(object.getString("reciverMobile"));
				present.setAmt((Double.parseDouble(object.getString("amt"))/100)+"");
				String lotName = SelectAllUtil.getLotName(object.getString("lotno"));
				present.setLotName(lotName);
//				Map<String, String> map = SelectAllUtil.getBetcodeFormatForPresent(object.getString("betcode"), object.getString("lotno"));
				Map<String, String> map = SelectAllUtil.getBetcodeFormat(object);
				present.setBetType(map.get("betType"));
				if(object.getString("lotno").contains("J")){
					present.setBatchCode(map.get("batchcode"));
				}else{
					present.setBatchCode(object.getString("batchcode"));
				}
				present.setBeishu(object.getString("lotmulti"));
				present.setBetCode(map.get("betCodeView"));
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String time = format.format(new Date(Long.parseLong(object.getString("createTime"))));
				time = time.substring(0,4)+"年"+time.substring(4,6)+"月"+time.substring(6)+"日<br/><br/>";
				present.setCreateTime(time);
				presents.add(present);
			}
			Boolean nextPage = false;
			if(Integer.parseInt(totalPage)>Integer.parseInt(currentPageNo)){
				nextPage = true;
			}
			Boolean upPage = false;
			if(Integer.parseInt(currentPageNo)>1){
				upPage = true;
			}
			model.addAttribute("startLine",startLine);
			model.addAttribute("endLine",Integer.parseInt(endLine));
			model.addAttribute("nowPage",currentPageNo);
			model.addAttribute("nextPage",nextPage);
			model.addAttribute("upPage",upPage);
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("presents",presents);
			logger.info("赠送查询结束");
		}else{
			model.addAttribute("messageError","服务器忙，请稍后再试！");
		}
		return "wap/query/selectSenderPresentDetails";
	}
	/**
	 * 赠送查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectSenderPresentDetails.jspa")
	public String selectSenderPresentDetails(Model model, HttpServletRequest request, HttpServletResponse response) {
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String startLine = request.getParameter("startLine") == null ? "0"
				: request.getParameter("startLine");
		String endLine = request.getParameter("endLine") == null ? "10"
				: request.getParameter("endLine");
		// 取得投注的返回码
		logger.info("赠送查询开始");
		String result = SelectAllUtil.selectSenderPresentDetails(userno, startLine, endLine);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			Present present = null;
			List<Present> presents = new ArrayList<Present>();
			for(int i=0;i<jsonArray.size()&&i<10;i++){
				present = new Present();
				JSONObject jsObject = jsonArray.getJSONObject(i);
				JSONObject jsonObjectOrder = jsObject.getJSONObject("torder");
				JSONObject jsonObjectPresentDetails = jsObject.getJSONObject("presentDetails");
				present.setMobile(jsonObjectPresentDetails.getString("reciverMobile"));
				present.setAmt((Double.parseDouble(jsonObjectOrder.getString("amt"))/100)+"");
				String lotName = SelectAllUtil.getLotName(jsonObjectOrder.getString("lotno"));
				present.setLotName(lotName);
//				Map<String, String> map = SelectAllUtil.getBetcodeFormatForPresent(object.getString("betcode"), object.getString("lotno"));
				Map<String, String> map = SelectAllUtil.getBetcodeFormat(jsonObjectOrder);
				present.setBetType(map.get("betType"));
				if(jsonObjectOrder.getString("lotno").contains("J")){
					present.setBatchCode(map.get("batchcode"));
				}else{
					present.setBatchCode(jsonObjectOrder.getString("batchcode"));
				}
				present.setBeishu(jsonObjectOrder.getString("lotmulti"));
				present.setBetCode(map.get("betCodeView"));
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String time = format.format(new Date(Long.parseLong(jsonObjectPresentDetails.getString("createTime"))));
				time = time.substring(0,4)+"年"+time.substring(4,6)+"月"+time.substring(6)+"日<br/><br/>";
				present.setCreateTime(time);
				presents.add(present);
			}
			Boolean nextPage = false;
			if(Integer.parseInt(totalPage)>Integer.parseInt(currentPageNo)){
				nextPage = true;
			}
			Boolean upPage = false;
			if(Integer.parseInt(currentPageNo)>1){
				upPage = true;
			}
			model.addAttribute("startLine",startLine);
			model.addAttribute("endLine",Integer.parseInt(endLine));
			model.addAttribute("nowPage",currentPageNo);
			model.addAttribute("nextPage",nextPage);
			model.addAttribute("upPage",upPage);
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("presents",presents);
			logger.info("赠送查询结束");
		}else{
			model.addAttribute("messageError","服务器忙，请稍后再试！");
		}
		return "wap/query/selectSenderPresentDetails";
	}
	/**
	 * 被赠送查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectReciverPresentDetails1.jspa")
	public String selectReciverPresentDetails1(Model model, HttpServletRequest request, HttpServletResponse response) {
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String userName = tuserInfoBean.getUserName();
		String startLine = request.getParameter("startLine") == null ? "0"
				: request.getParameter("startLine");
		String endLine = request.getParameter("endLine") == null ? "10"
				: request.getParameter("endLine");;
		logger.info("查询被赠送彩票用户是否绑定手机号");
		JSONObject userObject = CommonUtil.getUserinfoByUserno(userno);
		String code = userObject.getString("errorCode");
		String mobileid = "";
		if("0".equals(code)){
			JSONObject jsonObjectValue = userObject.getJSONObject("value");
			mobileid = jsonObjectValue.get("mobileid")== null ? "" :jsonObjectValue.getString("mobileid");
		}
		if("".equals(mobileid)){
			return "wap/query/selectReciverPresentDetails";
		}
		logger.info("用户已绑定手机号，查询被赠送彩票");
		logger.info("被赠送查询开始");
		// 取得投注的返回码
		String result = SelectAllUtil.selectReciverPresentDetails(userno, startLine, endLine);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			Present present = null;
			List<Present> presents = new ArrayList<Present>();
			for(int i=0;i<jsonArray.size()&&i<10;i++){
				present = new Present();
				JSONObject object = jsonArray.getJSONObject(i);
				JSONObject presentDetails = object.getJSONObject("presentDetails");
				JSONObject userinfo = object.getJSONObject("tuserinfo");
				present.setId(presentDetails.getString("id"));
				present.setUserName(userinfo.getString("userName"));
				present.setAmt((Double.parseDouble(presentDetails.getString("amt"))/100)+"");
				String lotName = SelectAllUtil.getLotName(presentDetails.getString("lotno"));
				present.setLotName(lotName);
//				Map<String, String> map = SelectAllUtil.getBetcodeFormatForPresent(presentDetails.getString("betcode"), presentDetails.getString("lotno"));
				Map<String, String> map = SelectAllUtil.getBetcodeFormat(presentDetails);
				present.setBetType(map.get("betType"));
				present.setBeishu(presentDetails.getString("lotmulti"));
				present.setBetCode(map.get("betCodeView"));
				if(presentDetails.getString("lotno").contains("J")){
					present.setBatchCode(map.get("batchcode"));
				}else{
					present.setBatchCode(presentDetails.getString("batchcode"));
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String time = format.format(new Date(Long.parseLong(presentDetails.getString("createTime"))));
				time = time.substring(0,4)+"年"+time.substring(4,6)+"月"+time.substring(6)+"日";
				present.setCreateTime(time);
				present.setReciveState(presentDetails.getString("reciveState"));
				presents.add(present);
			}
			Boolean nextPage = false;
			if(Integer.parseInt(totalPage)>Integer.parseInt(currentPageNo)){
				nextPage = true;
			}
			Boolean upPage = false;
			if(Integer.parseInt(currentPageNo)>1){
				upPage = true;
			}
			model.addAttribute("startLine",startLine);
			model.addAttribute("endLine",Integer.parseInt(endLine));
			model.addAttribute("nowPage",currentPageNo);
			model.addAttribute("nextPage",nextPage);
			model.addAttribute("upPage",upPage);
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("presents",presents);
			logger.info("被赠送查询结束");
		}else{
			model.addAttribute("messageError","服务器忙，请稍后再试！");
		}
		return "wap/query/selectReciverPresentDetails";
	}
	/**
	 * 被赠送查询
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/selectReciverPresentDetails.jspa")
	public String selectReciverPresentDetails(Model model, HttpServletRequest request, HttpServletResponse response) {
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
//		String userName = tuserInfoBean.getUserName();
		String startLine = request.getParameter("startLine") == null ? "0"
				: request.getParameter("startLine");
		String endLine = request.getParameter("endLine") == null ? "10"
				: request.getParameter("endLine");;
		logger.info("被赠送查询开始");
		// 取得投注的返回码
		String result = SelectAllUtil.selectReciverPresentDetails(userno, startLine, endLine);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		
		if("0".equals(errorCode)){
			JSONObject jsonObjectValue = jsonObject.getJSONObject("value");
			String totalPage = jsonObjectValue.getString("totalPage");
			String currentPageNo = jsonObjectValue.getString("currentPageNo");
			JSONArray jsonArray = jsonObjectValue.getJSONArray("list");
			Present present = null;
			List<Present> presents = new ArrayList<Present>();
			for(int i=0;i<jsonArray.size()&&i<10;i++){
				present = new Present();
				JSONObject object = jsonArray.getJSONObject(i);
				JSONObject presentDetails = object.getJSONObject("presentDetails");
				JSONObject jsonObjectOrder = object.getJSONObject("torder");
				JSONObject userinfo = object.getJSONObject("tuserinfo");
				present.setId(presentDetails.getString("id"));
				present.setUserName(userinfo.getString("userName"));
				present.setAmt((Double.parseDouble(jsonObjectOrder.getString("amt"))/100)+"");
				String lotName = SelectAllUtil.getLotName(jsonObjectOrder.getString("lotno"));
				present.setLotName(lotName);
//				Map<String, String> map = SelectAllUtil.getBetcodeFormatForPresent(presentDetails.getString("betcode"), presentDetails.getString("lotno"));
				Map<String, String> map = SelectAllUtil.getBetcodeFormat(jsonObjectOrder);
				present.setBetType(map.get("betType"));
				present.setBeishu(jsonObjectOrder.getString("lotmulti"));
				present.setBetCode(map.get("betCodeView"));
				if(presentDetails.getString("lotno").contains("J")){
					present.setBatchCode(map.get("batchcode"));
				}else{
					present.setBatchCode(jsonObjectOrder.getString("batchcode"));
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
				String time = format.format(new Date(Long.parseLong(presentDetails.getString("createTime"))));
				time = time.substring(0,4)+"年"+time.substring(4,6)+"月"+time.substring(6)+"日";
				present.setCreateTime(time);
//				present.setReciveState(presentDetails.getString("reciveState"));
				presents.add(present);
			}
			Boolean nextPage = false;
			if(Integer.parseInt(totalPage)>Integer.parseInt(currentPageNo)){
				nextPage = true;
			}
			Boolean upPage = false;
			if(Integer.parseInt(currentPageNo)>1){
				upPage = true;
			}
			model.addAttribute("startLine",startLine);
			model.addAttribute("endLine",Integer.parseInt(endLine));
			model.addAttribute("nowPage",currentPageNo);
			model.addAttribute("nextPage",nextPage);
			model.addAttribute("upPage",upPage);
			model.addAttribute("totalPage",totalPage);
			model.addAttribute("presents",presents);
			logger.info("被赠送查询结束");
		}else{
			model.addAttribute("messageError","服务器忙，请稍后再试！");
		}
		return "wap/query/selectReciverPresentDetails";
	}
	/**
	 * 赠送获得领取验证码
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sendRandomSMS.jspa")
	public String sendRandomSMS(Model model, HttpServletRequest request, HttpServletResponse response) {
		TuserInfoBean tuserInfoBean = (TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String presentid = request.getParameter("presentid")==null ? "" : request.getParameter("presentid");
		logger.info("赠送领取获取验证码");
		String result = SelectAllUtil.sendRandomSMS(presentid, userno);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			model.addAttribute("presentid",presentid);
			model.addAttribute("messageError","验证码已发送到您的手机!");
			logger.info("赠送领取获取验证码成功errorCode="+errorCode);
		}else{
			model.addAttribute("messageError","服务器忙，请稍后再试!");
			logger.info("赠送领取获取验证码失败errorCode="+errorCode);
		}
		return "wap/query/sendRandomSMS";
	}
	/**
	 * 领取彩票
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/recivePresent.jspa")
	public String recivePresent(Model model, HttpServletRequest request, HttpServletResponse response) {
		String randomCode = request.getParameter("randomCode")==null ? "" : request.getParameter("randomCode");
		String presentid = request.getParameter("presentid")==null ? "" : request.getParameter("presentid");
		logger.info("领取彩票");
		String result = SelectAllUtil.recivePresent(presentid, randomCode);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String errorCode = jsonObject.getString("errorCode");
		if("0".equals(errorCode)){
			model.addAttribute("messageError","恭喜，领取彩票成功!");
			logger.info("领取彩票成功errorCode="+errorCode);
		}else{
			model.addAttribute("messageError","领取失败，请稍后再试!");
			logger.info("领取彩票失败errorCode="+errorCode);
		}
		return "wap/query/recivePresent";
	}

		
		
	/**
	 * 用户追号查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	@RequestMapping(value="/addNumberSelect.jspa")
	public String addNumberSelect(HttpServletRequest request,
			HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();

			String ordertime = request.getParameter("ordertime");
			String lotno = request.getParameter("lotno");
			String beginId = request.getParameter("beginId") == null ? "0"
					: request.getParameter("beginId");
			String endId = request.getParameter("endId") == null ? "10"
					: request.getParameter("endId");
			String nowPage = request.getParameter("nowPage") == null ? "1"
					: request.getParameter("nowPage");
			logger.info("ordertime:" + ordertime + ",lotno:" + lotno
					+ ",beginId:" + beginId + ",endId:" + endId + ",nowPage:"
					+ nowPage);
			request.setAttribute("nowPage", nowPage);
			request.setAttribute("ordertime", ordertime);
			request.setAttribute("lotno", lotno);

			// 设置参数
			Map map = new HashMap();
			// 查询条件
			map.put("userno", userno);
			System.out.println("lotno==="+lotno);
			if (lotno != null && !lotno.trim().equals("")
					&& !lotno.trim().equals("all")) {
				map.put("lotNo", lotno);
			}
			if (ordertime != null && !ordertime.trim().equals("")
					&& ordertime.trim().equals("seven")
					&& !ordertime.trim().equals("all")) {
				map.put("stopDate", sdf.format(new Date()));
				map.put("startDate", sdf.format(new Date(new Date().getTime()
						- (7 * 24 * 60 * 60 * 1000l))));
			}
			else if (ordertime != null && !ordertime.trim().equals("")
					&& ordertime.trim().equals("fifteenth")
					&& !ordertime.trim().equals("all")) {
				map.put("stopDate", sdf.format(new Date()));
				map.put("startDate", sdf.format(new Date(new Date().getTime()
						- (15 * 24 * 60 * 60 * 1000l))));
			}
			else if (ordertime != null && !ordertime.trim().equals("")
					&& ordertime.trim().equals("thirty")
					&& !ordertime.trim().equals("all")) {
				map.put("stopDate", sdf.format(new Date()));
				map.put("startDate", sdf.format(new Date(new Date().getTime()
						- (30 * 24 * 60 * 60 * 1000l))));
			}
			else{
				map.put("stopDate", sdf.format(new Date()));
				map.put("startDate", sdf.format(new Date(new Date().getTime()
						- (120*30 * 24 * 60 * 60 * 1000l))));
			}
			map.put("startLine", beginId);
			map.put("stopLine", endId);
			JSONObject jsonObject = JsonToJrtLotUtil
					.getJsonToLotteryRequestJSONArray(map);
			request.setAttribute("jsonObject", jsonObject);
			return "wap/query/addNumberSelect";
		} catch (Exception e) {
			logger.error("用户追号查询时发生异常:" + e.toString());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 追号详细信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	@RequestMapping(value="/addNumberDetail.jspa")
	public String addNumberDetail(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String flowNo = request.getParameter("flowNo") == null ? ""
					: request.getParameter("flowNo");

			JSONObject jsonObject = JsonToJrtLotUtil
					.getJsonToLotteryRequestDetailJSONArray(flowNo);

			request.setAttribute("jsonObject", jsonObject);
			return "wap/query/addNumberDetail";
		} catch (Exception e) {
			logger.error("用户追号明细查询时发生异常:" + e.toString());
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 停止追号(lottery)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	@RequestMapping(value="/stopAddNumber.jspa")
	public String stopAddNumber(HttpServletRequest request, HttpServletResponse response) {

		try {
			String flowNo = request.getParameter("flowNo") == null ? ""
					: request.getParameter("flowNo");

			String json =JsonToJrtLotUtil.giveupSubscribe(flowNo);
			request.setAttribute("json", json);
			return "wap/query/stopAddNumberResult";
		} catch (Exception e) {
			logger.error("用户追号明细查询时发生异常:" + e.toString());
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 追号详细信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "rawtypes" })
	@RequestMapping(value="/addNumberDetailByTorders.jspa")
	public String addNumberDetailByTorders(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String flowNo = request.getParameter("flowNo") == null ? ""
					: request.getParameter("flowNo");
			String endLine = request.getParameter("flowNo");
			String beginTime = "20100101";
			Date date = new Date();
			SimpleDateFormat dateformat=new SimpleDateFormat("yyyyMMdd");
			String endTime = dateformat.format(date) ;
			JSONObject jsonObject = JsonToJrtLotUtil
					.getAddNumberDetailByTorders(flowNo, beginTime, endTime, "0",endLine);

			request.setAttribute("jsonObject", jsonObject);
			return "wap/query/addNumberDetailByOrders";
		} catch (Exception e) {
			logger.error("用户追号明细查询时发生异常:" + e.toString());
			e.printStackTrace();
		}
		return null;
	}
}
