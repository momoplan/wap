package com.ruyicai.wap.controller;

import java.io.UnsupportedEncodingException;
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

import com.ruyicai.wap.bean.Account;
import com.ruyicai.wap.bean.TuserInfoBean;
import com.ruyicai.wap.bean.WinSelectInfoBean;
import com.ruyicai.wap.controller.vo.BetBean;
import com.ruyicai.wap.controller.vo.WiningBean;
import com.ruyicai.wap.util.BetingSelect;
import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.StringUtils;
import com.ruyicai.wap.util.TuserinfoUtil;
import com.ruyicai.wap.util.VerificationAlgorithmUtil;

@Controller
public class UserInfoController {
	private static final Logger logger = Logger
			.getLogger(UserInfoController.class);

	/**
	 * 查询账户余额
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/handpay/userBalance.jspa")
	public String getUserBalance(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
				.getAttribute("user");
		String userno = tuserInfoBean.getUserno(); 
		TuserinfoUtil.getBalance(model, userno);
		return "handpay/query/accountBalance";
	}

	@RequestMapping("/handpay/userInfo.jspa")
	public String getUserinfo(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
				.getAttribute("user");
		String userName = tuserInfoBean.getUserName();
		String userno = tuserInfoBean.getUserno();
		JSONObject userinfo = CommonUtil.getUserinfoByUserno(userno);
		String nickName = "";
		String name = "";
		String certID = "";
		if ("0".equals(userinfo.get("errorCode"))) {
			JSONObject jsonObject = userinfo.getJSONObject("value");
			nickName = (String) (jsonObject.get("nickname") == null ? ""
					: jsonObject.get("nickname"));
			name = (String) (jsonObject.get("name") == null ? "" : jsonObject
					.get("name"));
			if (jsonObject.get("certid") == null
					|| "111111111111111111".equals(jsonObject.get("certid"))) {
				certID = "";
			} else {
				certID = (String) jsonObject.get("certid");
			}
			if (!"".equals(nickName) &&!"".equals(name)&!"".equals(certID)) {
				model.addAttribute("nickName", nickName);
				model.addAttribute("name", name);
				model.addAttribute("certID", certID);
				model.addAttribute("messageError", "您的用户信息已完善！");
				return "/handpay/userinfo/perfectUserInfoDetail";
			} else {
				model.addAttribute("nickName", nickName);
				model.addAttribute("name", name);
				model.addAttribute("certID", certID);
				model.addAttribute("messageError", "请您完善用户信息！");
				return "/handpay/userinfo/perfectUserInfo";
			}
		} else {
			model.addAttribute("messageError", "用户不存在");
		}
		return "/handpay/userinfo/perfectUserInfo";
	}

	@RequestMapping("/handpay/modifyUserInfo.jspa")
	public String updateUserinfo(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
				.getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		// 得到参数
		String nickName = (String) (request.getParameter("nickName") == null ? ""
				: request.getAttribute("nickName"));
		String name = (String) (request.getParameter("name") == null ? ""
				: request.getAttribute("name"));
		String certID = (String) (request.getParameter("certid") == null ? ""
				: request.getAttribute("certid"));
		// 验证参数
		String result = TuserinfoUtil.validateUserInfo(nickName, name, certID);
		if (!"".equals(result)) {
			model.addAttribute("nickName", nickName);
			model.addAttribute("name", name);
			model.addAttribute("certID", certID);
			model.addAttribute("messageError", result);
			return "/handpay/userinfo/perfectUserInfo";
		}
		String errorCode = TuserinfoUtil.updateUserinfo(userno,
				StringUtils.encodeUrlString(nickName),
				StringUtils.encodeUrlString(name), certID);
		if ("0".equals(errorCode)) {
			model.addAttribute("nickName", nickName);
			model.addAttribute("name", name);
			model.addAttribute("certID", certID);
			model.addAttribute("messageError", "完善信息成功！");
			return "/handpay/userinfo/perfectUserInfoDetail";
		} else if ("100105".equals(errorCode)) {
			model.addAttribute("nickName", nickName);
			model.addAttribute("name", name);
			model.addAttribute("certID", certID);
			model.addAttribute("messageError", "昵称已经存在！");
			return "/handpay/userinfo/perfectUserInfo";
		} else {
			model.addAttribute("nickName", nickName);
			model.addAttribute("name", name);
			model.addAttribute("certID", certID);
			model.addAttribute("messageError", "完善信息失败，请稍后重试！");
			return "/handpay/userinfo/perfectUserInfo";
		}

	}

	@RequestMapping("/handpay/accountIndex.jspa")
	public String getAccount() {
		return "handpay/query/accountIndex";
	}

	@RequestMapping("/handpay/accountDetail.jspa")
	public String getAccountDetail(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String beginTime = request.getParameter("beginTime") == null ? ""
				: request.getParameter("beginTime");
		String endTime = request.getParameter("endTime") == null ? "" : request
				.getParameter("endTime");
		String startLine = request.getParameter("startLine") == null ? "0"
				: request.getParameter("startLine");
		String endLine = request.getParameter("endLine") == null ? "10"
				: request.getParameter("endLine");
		TuserInfoBean tuserInfoBean = (TuserInfoBean) request.getSession()
				.getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		logger.info("beginTime:" + beginTime + "endTime:" + endTime);
		String messageError = VerificationAlgorithmUtil.getDateByString(
				beginTime, endTime);
		if (messageError != null) {
			request.setAttribute("messageError", messageError);
			return "handpay/query/accountIndex";
		}
		Account account;
		List<Account> accountList = new ArrayList<Account>();
		JSONArray jsonArray = null;
		JSONObject jsonObjectDetail = null;
		JSONObject jsonObject = CommonUtil.getTtransaction(userno, beginTime,
				endTime, startLine, endLine);
		JSONObject jsonObjectValue = jsonObject.getJSONObject("value") == null ? new JSONObject()
				: jsonObject.getJSONObject("value");
		if (jsonObjectValue != null) {
			jsonArray = jsonObjectValue.getJSONArray("list");
			String newamt = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < jsonArray.size() && i < 10; i++) {
				jsonObjectDetail = (JSONObject) jsonArray.get(i);
				int blsign = jsonObjectDetail.getInt("blsign");
				double amt = Double.valueOf("" + jsonObjectDetail.get("amt"));
				if (blsign == -1) {
					if(amt==0) newamt = "0";
					else
					newamt = "-" + (amt / 100);
				} else {
					newamt = "" + (amt / 100);
				}
				String memo = jsonObjectDetail.get("memo").toString();
				if (!"".equals(memo) && memo != null) {
					if (memo.indexOf("gyj001") != -1) {
						memo = memo.substring(6);
					}
				}
				try {
					memo = java.net.URLDecoder.decode(memo, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				String plattime = sdf.format(jsonObjectDetail.get("plattime"));
				account = new Account();
				account.setAmt(newamt);
				account.setMemo(memo);
				account.setPlattime(plattime);
				accountList.add(account);
			}
		}
		Boolean nextPage = false;
		if(Integer.parseInt(jsonObjectValue.getString("totalPage"))>Integer.parseInt(startLine)){
			nextPage = true;
		}
		Boolean upPage = false;
		if(Integer.parseInt(startLine)>0){
			upPage = true;
		}
		model.addAttribute("beginTime",beginTime);
		model.addAttribute("endTime",endTime);
		model.addAttribute("startLine",startLine);
		model.addAttribute("endLine",Integer.parseInt(endLine));
		model.addAttribute("accountList",accountList);
		model.addAttribute("nowPage",Integer.parseInt(jsonObjectValue.getString("currentPageNo")));
		model.addAttribute("nextPage",nextPage);
		model.addAttribute("upPage",upPage);
		return "handpay/query/accountDetail";
	}
	
	@RequestMapping("/handpay/drawLottery.jspx")
	public String getDrawLottery(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<WinSelectInfoBean>  listDoubleball = WinSelectAction.getWinfo("F47104","1");
		List<WinSelectInfoBean>  list3D = WinSelectAction.getWinfo("F47103","1");
		List<WinSelectInfoBean>  listQiLeCai = WinSelectAction.getWinfo("F47102","1");
		String openTimeDoubleball = dateFormat.format(Long.parseLong(listDoubleball.get(0).getOpentime()));
		String batchcodeDoubleball = listDoubleball.get(0).getBatchcode();
		String red1Doubleball = listDoubleball.get(0).getBetCode().substring(0,2);
		String red2Doubleball = listDoubleball.get(0).getBetCode().substring(3,5);
		String red3Doubleball = listDoubleball.get(0).getBetCode().substring(6,8);
		String red4Doubleball = listDoubleball.get(0).getBetCode().substring(9,11);
		String red5Doubleball = listDoubleball.get(0).getBetCode().substring(12,14);
		String red6Doubleball = listDoubleball.get(0).getBetCode().substring(15,17);
		String blueDoubleball = listDoubleball.get(0).getBetCode().substring(18,20);
		
		String openTime3D = dateFormat.format(Long.parseLong(list3D.get(0).getOpentime()));
		String batchcode3D = list3D.get(0).getBatchcode();
		String red13D = list3D.get(0).getBetCode().substring(0,1);
		String red23D = list3D.get(0).getBetCode().substring(2,3);
		String red33D = list3D.get(0).getBetCode().substring(4,5);
		
		String openTimeQiLeCai = dateFormat.format(Long.parseLong(listQiLeCai.get(0).getOpentime()));
		String batchcodeQiLeCai = listQiLeCai.get(0).getBatchcode();
		String red1QiLeCai = listQiLeCai.get(0).getBetCode().substring(0,2);
		String red2QiLeCai = listQiLeCai.get(0).getBetCode().substring(3,5);
		String red3QiLeCai = listQiLeCai.get(0).getBetCode().substring(6,8);
		String red4QiLeCai = listQiLeCai.get(0).getBetCode().substring(9,11);
		String red5QiLeCai = listQiLeCai.get(0).getBetCode().substring(12,14);
		String red6QiLeCai = listQiLeCai.get(0).getBetCode().substring(15,17);
		String red7QiLeCai = listQiLeCai.get(0).getBetCode().substring(18,20);
		model.addAttribute("openTimeDoubleball",openTimeDoubleball);
		model.addAttribute("batchcodeDoubleball",batchcodeDoubleball);
		model.addAttribute("red1Doubleball",red1Doubleball);
		model.addAttribute("red2Doubleball",red2Doubleball);
		model.addAttribute("red3Doubleball",red3Doubleball);
		model.addAttribute("red4Doubleball",red4Doubleball);
		model.addAttribute("red5Doubleball",red5Doubleball);
		model.addAttribute("red6Doubleball",red6Doubleball);
		model.addAttribute("blueDoubleball",blueDoubleball);
		model.addAttribute("openTime3D",openTime3D);
		model.addAttribute("batchcode3D",batchcode3D);
		model.addAttribute("red13D",red13D);
		model.addAttribute("red23D",red23D);
		model.addAttribute("red33D",red33D);
		model.addAttribute("openTimeQiLeCai",openTimeQiLeCai);
		model.addAttribute("batchcodeQiLeCai",batchcodeQiLeCai);
		model.addAttribute("red1QiLeCai",red1QiLeCai);
		model.addAttribute("red2QiLeCai",red2QiLeCai);
		model.addAttribute("red3QiLeCai",red3QiLeCai);
		model.addAttribute("red4QiLeCai",red4QiLeCai);
		model.addAttribute("red5QiLeCai",red5QiLeCai);
		model.addAttribute("red6QiLeCai",red6QiLeCai);
		model.addAttribute("red7QiLeCai",red7QiLeCai);
		
		model.addAttribute("listDoubleball",listDoubleball);
		model.addAttribute("list3D",list3D);
		model.addAttribute("listQiLeCai",listQiLeCai);
		return "handpay/query/drawLottery";
	}
	@RequestMapping("/handpay/bettingSelectIndex.jspa")
	public String getBettingSelectIndex() {
		return "handpay/query/bettingSelectIndex";
	}
	
	/**
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/handpay/bettingSelect.jspa")
	public String getBettingSelect(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();
			String beginTime = request.getParameter("beginTime") == null ? ""
					: request.getParameter("beginTime"); // 开始时间
			String endTime = request.getParameter("endTime") == null ? ""
					: request.getParameter("endTime"); // 结束时间
			String startLine = request.getParameter("startLine") == null ? "0"
					: request.getParameter("startLine");
			String endLine = request.getParameter("endLine") == null ? "10"
					: request.getParameter("endLine");
			String type = request.getParameter("type");
			String messageError = VerificationAlgorithmUtil.getDateByString(
					beginTime, endTime);
		
			logger.info("userno:"
					+ userno + ";beginTime:" + beginTime + ";endTime:"
					+ endTime + ";startLine:" + startLine + ";endLine:" + endLine
					 + ";验证开始日期与结束日期messageError:" + messageError);

			if (messageError != null && !"".equals(beginTime) && !"".equals(endTime)) {
				request.setAttribute("messageError", messageError);
				return "handpay/query/bettingSelectIndex";
			}
			//近期查询
			if("JQ".equals(type)){
				if("".equals(beginTime)||"".equals(beginTime)){
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
					Date date = new Date();
					endTime = format.format(date);
					beginTime = (Integer.parseInt(endTime)-7)+"";
				}
			}

			logger.info("============投注查询开始===========");
			Map<String,String> map = new HashMap<String,String>();
			map.put("userno", userno);
			map.put("batchCode", "");
			map.put("lotNo", "");
			map.put("startDate", beginTime);
			map.put("stopDate", endTime);
			map.put("startLine", startLine);
			map.put("stopLine", endLine);
            
			JSONObject obj = BetingSelect.getTorders(map);
			JSONObject objvalue = obj.getJSONObject(("value"));
			JSONArray jsonArray = objvalue.getJSONArray("list");
			logger.info("投注查询返回："+obj);
			// 4.得到返回的查询记录
			List betList = new ArrayList();
			BetBean bb = null;
			WinSelectInfoBean winSelectInfoBean = null;
			String orderBetType = "";
			if (jsonArray != null && jsonArray.size() > 0) {
				for (int i = 0; i < jsonArray.size(); i++) {
					bb = new BetBean();
					JSONObject json = jsonArray.getJSONObject(i);
					JSONObject json1 = json.getJSONObject("torder");
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
					Map jsonMap = SelectAllUtil.getBetcodeFormat(json1);
					bb.setPlay_name(CommonUtil.getLotnameByLotno(json1.getString("lotno"))); // 彩种名称
					bb.setBatchcode(json1.getString("batchcode")); // 期号
					
					bb.setSell_cate(CommonUtil.setBetTimeByFormat(json1.getString("modifytime"))); // 投注时间
					bb.setAmt(json1.getLong("orderamt")); // 投注金额
					bb.setBetcodeAll((String)jsonMap.get("betCodeView")); // 注码
					bb.setBetcode(jsonMap);
					bb.setMaxLine(Integer.parseInt(objvalue
							.getString("totalResult")));
					bb.setCash_date_time(json1.getString("endtime"));// 期结日期
					bb.setState(json.containsKey("state") != true ? "" : json
							.getString("state"));// 彩票记录的状态
					bb.setBetType((String)jsonMap.get("betType"));
					bb.setMulNo((String)jsonMap.get("multiples")); // 倍数
					bb.setOrderBetType(orderBetType); // 订单投注类型
					winSelectInfoBean = WinSelectAction.getWinfoByIssue(json1.getString("lotno"),json1.getString("batchcode"));
					if(winSelectInfoBean!=null)
						bb.setWinCode(winSelectInfoBean.getBetCode());
					else
						bb.setWinCode("未开奖");
					betList.add(bb);
				}
			}
			Boolean nextPage = false;
			if(Integer.parseInt(objvalue.getString("totalPage"))>Integer.parseInt(objvalue.getString("currentPageNo"))){
				nextPage = true;
			}
			Boolean upPage = false;
			if(Integer.parseInt(objvalue.getString("currentPageNo"))>1){
				upPage = true;
			}
			model.addAttribute("beginTime",beginTime);
			model.addAttribute("endTime",endTime);
			model.addAttribute("startLine",startLine);
			model.addAttribute("endLine",Integer.parseInt(endLine));
			model.addAttribute("betList",betList);
			model.addAttribute("nowPage",Integer.parseInt(objvalue.getString("currentPageNo")));
			model.addAttribute("nextPage",nextPage);
			model.addAttribute("upPage",upPage);
			logger.info("============投注查询结束===========");
		} catch (Exception e) {
			logger.error("获取投注信息时出现异常:" + e.getMessage());
			return "handpay/home";
		}
	return "handpay/query/bettingSelect";
	}
	@RequestMapping("/handpay/winingSelect.jspa")
	public String getWiningSelect(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();

			String startLine = request.getParameter("startLine") == null ? "0"
					: request.getParameter("startLine");
			String endLine = request.getParameter("endLine") == null ? "10"
					: request.getParameter("endLine");

			// 取得投注的返回码
			Map<String,String> map = new HashMap<String,String>();
			map.put("userno", userno);
			map.put("lotNo", "");
			map.put("stopDate", sdf.format(new Date()));
			map.put("startDate", sdf.format(new Date(new Date().getTime()
					- (120*30 * 24 * 60 * 60 * 1000l))));
			map.put("startLine", startLine);
			map.put("stopLine", endLine);

			JSONObject jsonObject = BetingSelect.getWinByTorders(map);
			List winList = new ArrayList();
			if(!"null".equals(jsonObject.getString("value")) && !"400004".equals(jsonObject.getString("errorCode"))){
				JSONObject objectValue = jsonObject.getJSONObject("value");
				JSONArray arrayList = objectValue.getJSONArray("list");
				int count = 0;
				if (jsonObject != null && arrayList.size() > 0) {
					for (int i = 0; i < arrayList.size(); i++) {
						WiningBean wb = new WiningBean();
						JSONObject json = arrayList.getJSONObject(i);
						JSONObject object = json.getJSONObject("torder");
						Map jsonMap =SelectAllUtil.getBetcodeFormat(object);
						wb.setPlay_name(CommonUtil.getLotnameByLotno((String)object.getString("lotno")));
						wb.setBatchcode(object.getString("batchcode"));
						wb.setPrizeamt(object.getLong("orderprize")/100);
						String prizestate = object.getString("prizestate");
						if (prizestate.equals("0"))
							prizestate = "未兑奖";
						else if (prizestate.equals("1"))
							prizestate = "等待开奖";
						else if (prizestate.equals("2"))
							prizestate = "开奖处理中";
						else if (prizestate.equals("3"))
							prizestate = "已兑奖";
						else if (prizestate.equals("4"))
							prizestate = "大奖";
						else if (prizestate.equals("5"))
							prizestate = "小奖";
						wb.setEncash_flag(prizestate);
						wb.setSell_cate(CommonUtil.setBetTimeByFormat(object.getString("modifytime")));
						wb.setBetcodeAll((String)jsonMap.get("betCodeView"));
						wb.setMulNo((String)jsonMap.get("multiples"));
						wb.setBetType((String)jsonMap.get("betType"));
						count = objectValue.getInt("totalResult");
						wb.setMaxLine(count);
						winList.add(wb);
					}
				}
				Boolean nextPage = false;
				if(Integer.parseInt(objectValue.getString("totalPage"))>Integer.parseInt(objectValue.getString("currentPageNo"))){
					nextPage = true;
				}
				Boolean upPage = false;
				if(Integer.parseInt(objectValue.getString("currentPageNo"))>1){
					upPage = true;
				}
				model.addAttribute("startLine",startLine);
				model.addAttribute("endLine",Integer.parseInt(endLine));
				model.addAttribute("winList",winList);
				model.addAttribute("nowPage",Integer.parseInt(objectValue.getString("currentPageNo")));
				model.addAttribute("nextPage",nextPage);
				model.addAttribute("upPage",upPage);
			}
		} catch (Exception e) {
			return "handpay/home";
		}
		return "handpay/query/winingSelect";
	}
	@RequestMapping("/handpay/drawCashIndex.jspa")
	public String drawCashIndex(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();
				// 查询用户提现状态1.待审核、102.审核中、105成功、106取消
				JSONObject cashObject = CommonUtil.getTcashDetailByUserno(userno);
				JSONArray cashArray = cashObject.getJSONArray("value");
				JSONObject jsonObject = null;
				String errorCode = cashObject.getString("errorCode");
				if (cashArray.size() > 0) {
					jsonObject = cashArray.getJSONObject(0);
				}

				if (jsonObject != null && "0".equals(errorCode)) {
					String state = jsonObject.get("state")==null ? "" : jsonObject.getString("state");
					String drawAmount = jsonObject.get("amt")==null ? "" : jsonObject.getString("amt");
					String bankNo = jsonObject.get("bankaccount")==null ? "" : jsonObject.getString("bankaccount");
					String bankName = jsonObject.get("bankname")==null ? "" : jsonObject.getString("bankname");
					String name = jsonObject.get("name")==null ? "" : jsonObject.getString("name");
					String id = jsonObject.get("id")==null ? "" : jsonObject.getString("id");
					String messageError = "";
					String type = "";
					if ("".equals(state) || "1".equals(state)){
						messageError = "您的提现申请正在审核期，现在可以撤销体现";
						type = "CheXiao";
					}else if("102".equals(state) || "103".equals(state)){
						messageError = "您提现申请正在审核中！";
					}else if("105".equals(state)){
						messageError = "提现请求已通过审核，将于五个工作日到账，请耐心等候！";
						type = "JiXu";
					}else if("106".equals(state) || "104".equals(state)){
						messageError = "提现请求已经取消，请重新提交！";
						type = "ChongXin";
					}
					model.addAttribute("drawAmount",Double.parseDouble(drawAmount)/100);
					model.addAttribute("bankNo",bankNo);
					model.addAttribute("bankName",bankName);
					model.addAttribute("name",name);
					model.addAttribute("type",type);
					model.addAttribute("id",id);
					model.addAttribute("messageError",messageError);
					return "handpay/userinfo/drawCashResultDetail";

				} else {
					this.drawCashByDNA(model,request, response);
					return "handpay/userinfo/drawCashIndex";
				}
		} catch (Exception e) {
			logger.error("查询用户提现信息出异常Exception:");
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/handpay/drawCashByDNA.jspa")
	public String drawCashByDNA(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();
				//查询账号余额
				TuserinfoUtil.getBalance(model, userno);
				//查询是否绑定
				JSONObject object = CommonUtil.getDNABinding(userno);
				String errorCode = "";
				JSONObject obj = null;
				if (!"null".equals(object.getString("value"))) {
					obj = object.getJSONObject("value");
					errorCode = object.getString("errorCode");
				}

				logger.info("返回的JSONObject：" + object);
				// 得到传回来的值
				if ("0".equals(errorCode) && "1".equals(obj.getString("state"))) {
					// 状态为1证明已经绑定手机号码
					String name = obj.get("name") ==null ? "" : obj.getString("name");
					String bankNo = obj.get("bankcardno")==null ? "" : obj.getString("bankcardno");
					model.addAttribute("name",name);
					model.addAttribute("bankNo",bankNo);
					return "handpay/userinfo/drawCashIndex";
				} else {
					return "handpay/userinfo/drawCashIndex";
				}
		} catch (Exception e) {
			logger.error("查询是否是DNA绑定异常Exception:" + e.toString());
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/handpay/drawCashDetail.jspa")
	public String drawCashDetail(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try { 
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();
				String userName = tuserInfoBean.getUserName();
				JSONObject jsonObject = CommonUtil.getUserinfoByUserno(userno);
				JSONObject tuserinfo = null;
				tuserinfo = jsonObject.getJSONObject("value");
				String realName = tuserinfo.getString("name");
				String certid = tuserinfo.getString("certid");
				TuserinfoUtil.getBalance(model, userno);
				Map<String, String> map = new HashMap<String, String>();
				map = CommonUtil.getBalance(userno);
				String drawbalance = map.get("ableToTcash");
				String drawAmount = request.getParameter("drawAmount")==null ? "" :request.getParameter("drawAmount");
				String bankNo = request.getParameter("bankNo") == null ? "" :request.getParameter("bankNo");
				String bankName = request.getParameter("bankName") == null ? "" : request.getParameter("bankName");
				String name = (String)request.getParameter("name") == null ? "" :request.getParameter("name");
				bankName = CommonUtil.getBankName(bankName);
				model.addAttribute("drawAmount",drawAmount);
				model.addAttribute("bankNo",bankNo);
				model.addAttribute("bankName",bankName);
				model.addAttribute("name",name);
				if ("".equals(realName) || realName == null || "null".equals(realName)
						|| " ".equals(realName)) {
					model.addAttribute("messageError","您的账户还未填写真实姓名，请先完善用户信息再进行提现");
					return "handpay/userinfo/drawCashIndex";
				}
				if ("".equals(certid) || certid == null
						|| "null".equals(certid) || " ".equals(certid)
						|| "111111111111111111".trim().equals(certid)) {
					model.addAttribute("messageError","您的账户还未填写身份证号，请先完善用户信息再进行提现");
					return "handpay/userinfo/drawCashIndex";
				}
				if (!VerificationAlgorithmUtil.verifyParamEmpty(drawAmount)){// 验证提现金额是否是大于零的数字
					request.setAttribute("messageError", "请您输入提现金额");
					return "handpay/userinfo/drawCashIndex";
				}
				if (!VerificationAlgorithmUtil.verifyMoneyPattern(drawAmount)){
					model.addAttribute("messageError","提现金额必须是大于等于1的数字");
					return "handpay/userinfo/drawCashIndex";
				}
				if (Float.valueOf(drawbalance) < 1) {
					model.addAttribute("messageError","提现金额不足");
					return "handpay/userinfo/drawCashIndex";
				}
				if (Float.valueOf(drawbalance) >= 10&& Float.valueOf(drawAmount) < 10) {
					model.addAttribute("messageError","提现金额不能小于10元");
					return "handpay/userinfo/drawCashIndex";
				}
				if (Float.valueOf(drawbalance) < Float.valueOf(drawAmount)) {
					model.addAttribute("messageError","提现金额不能大于账户可提现余额");
					return "handpay/userinfo/drawCashIndex";
				}
				if (Float.valueOf(drawbalance) < 10&& Float.valueOf(drawAmount)/ Math.floor(Float.valueOf(drawbalance)) != 1) {
					model.addAttribute("messageError","提现余额不足10元必须一次提清");
					return "handpay/userinfo/drawCashIndex";
				}
				if (!VerificationAlgorithmUtil.verifyParamEmpty4(bankNo)){// 验证账号是否是16-21位的数字串
					model.addAttribute("messageError","银行账号格式不正确");
					return "handpay/userinfo/drawCashIndex";
				}
				if (!VerificationAlgorithmUtil.verifyParamEmpty(name)){// 验证持卡人姓名是否为空
					model.addAttribute("messageError","请输入持卡人姓名");
					return "handpay/userinfo/drawCashIndex";
				}
				if (bankName == null || "00".equals(bankName)||"".equals(bankName)) {
					model.addAttribute("messageError","请选择开卡银行");
					return "handpay/userinfo/drawCashIndex";
				}
				if (!VerificationAlgorithmUtil.verifyBankNumber(bankNo)) {
					request.setAttribute("message", "银行账号格式不正确");// 必须是16-21位的数字字符串
					return "handpay/userinfo/drawCashIndex";
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "handpay/userinfo/drawCashDetail";
	}
	@RequestMapping("/handpay/drawCash.jspa")
	public String drawCash(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
			String userno = tuserInfoBean.getUserno();
			String userName = tuserInfoBean.getUserName();

			// 获取页面参数
			String drawAmount = request.getParameter("drawAmount")==null ? "" :request.getParameter("drawAmount");
			String bankNo = request.getParameter("bankNo") == null ? "" :request.getParameter("bankNo");
			String bankName = request.getParameter("bankName") == null ? "" : request.getParameter("bankName");
			String name = request.getParameter("name") == null ? "" :request.getParameter("name");

			bankName = CommonUtil.getBankName(bankName);
			logger.info("Method:cash,userName:" + userName + ",drawAmount:"
					+ drawAmount + ",bankNo:" + bankNo
					+ ",bankName:" + bankName);

			String result = CommonUtil.tcashDetail(userno,Long.parseLong(drawAmount) * 100, bankName,
						bankNo, name, bankName);
				logger.info("userName:" + userName + ",用户提现时的返回码result=" + result);
				// 显示用户余额
				if ("0".equals(result)) {// 提现成功
					model.addAttribute("messageError","提现请求成功提交，请等待客服电话确认!");
				} else if ("230013".equals(result) || "230012".equals(result)|| "230093".equals(result)) {
					logger.error("userName:" + userName + ",提现失败,余额不足");
					model.addAttribute("messageError","提现失败!余额不足!");
				} else {
					model.addAttribute("messageError","提现失败!");
					logger.error("userName:" + userName+ ",提现失败,返回值result=" + result);
				}

		} catch (Exception e) {
			logger.error("Method:drawCash,异常:"
					+ e.getMessage());
			e.printStackTrace();
		}

		return "handpay/userinfo/drawCashResult";
	}
	@RequestMapping("/handpay/cancelDrawCash.jspa")
	public String cancelDrawCash(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 获取页面参数
			String id = request.getParameter("id")==null ? "" :request.getParameter("id");
			logger.info("撤销提现：id:" + id);
			// 调用接口执行撤销
			String errorCode = CommonUtil.cancelTcash(id);
			if ("0".equals(errorCode)) {
				model.addAttribute("messageError","提现请求成功撤销!");
				return "handpay/userinfo/drawCashResult";
			}

		} catch (Exception e) {
			logger.error("撤销提现出异常" + e.toString());
			e.printStackTrace();
			model.addAttribute("messageError","提现请求撤销失败!");
			return "handpay/userinfo/drawCashResult";
		}
		model.addAttribute("messageError","提现请求撤销失败!");
		return "handpay/userinfo/drawCashResult";
	}
	@RequestMapping("/handpay/bandingMobile.jspa")
	public String bandingMobile(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		return "";
	}
}
