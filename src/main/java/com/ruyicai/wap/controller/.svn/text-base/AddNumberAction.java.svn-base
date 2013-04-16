package com.ruyicai.wap.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

@RequestMapping("/add")
@Controller
public class AddNumberAction {
	private static final Logger logger = Logger
			.getLogger(AddNumberAction.class);

	/**
	 * 重订方案
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toAddNumberBet.jspx",method=RequestMethod.GET)
	public String toAddNumberBet(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获取玩法标示
		String flag = request.getParameter("flag");
		String betcode = request.getParameter("betcode") == null ? "" : request
				.getParameter("betcode");
		String addnumber = (String) request.getParameter("addnumber") == null ? "5"
				: (String) request.getParameter("addnumber");
		// 获取倍数
		for (int i = 0; i < Integer.valueOf(addnumber); i++) {
			request.setAttribute("beishu" + i, (String) request
					.getParameter("beishu" + i) == null ? "1"
					: (String) request.getParameter("beishu" + i));
		}
		String checkResult = null;
		if (Integer.valueOf(addnumber) > 20)
			checkResult = "追号最高20期";
		request.setAttribute("message", checkResult);
		request.setAttribute("zhushu", "1");
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("betcode", betcode);
		request.setAttribute("flag", flag);
		if ("rx1".equals(flag)) {
			// 计算赔赚
			return "wap/ElevenDuoJin/addNumberR1";
		} else if ("rx2".equals(flag)) {
			return "wap/ElevenDuoJin/addNumberR2";
		} else if ("rx3".equals(flag)) {
			return "wap/ElevenDuoJin/addNumberR3";
		} else if ("rx4".equals(flag)) {
			return "wap/ElevenDuoJin/addNumberR4";
		} else if ("rx5".equals(flag)) {
			return "wap/ElevenDuoJin/addNumberR5";
		} else if ("rx6".equals(flag)) {
			return "wap/ElevenDuoJin/addNumberR6";
		} else if ("rx7".equals(flag)) {
			return "wap/ElevenDuoJin/addNumberR7";
		} else if ("rx8".equals(flag)) {
			return "wap/ElevenDuoJin/addNumberR8";
		}
		return null;
	}

	/**
	 * 生成追号计划
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toAddNumberPlan.jspx",method=RequestMethod.POST)
	public String toAddNumberPlan(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String betcode = request.getParameter("betcode");
		String flag = request.getParameter("flag");
		String addnumber = request.getParameter("addnumber");
		String zhushu = request.getParameter("zhushu") == null ? "1" : request
				.getParameter("zhushu");
		// 计算注数
		// 获取倍数
		String checkResult;
		String type = new CommonUtil().getTypeByflag(flag);
		checkResult = new ElevenDuoJin().checkBetCode(betcode, "1", addnumber,
				type);// 倍数默认为"1” ， 确保验证功能通过
		if ("pass".equals(checkResult)) {
			if (Integer.valueOf(addnumber) > 20) {
				checkResult = "追号最高20期";
				request.setAttribute("newaddnumbernull", "newaddnumbernull");
				for (int i = 0; i < 5; i++) {
					String beishu = (String) request.getParameter("beishu" + i) == null ? "1"
							: (String) request.getParameter("beishu" + i);
					if (!CommonUtil.checkBeishu(beishu)) {
						beishu = "1";
					}
					request.setAttribute("beishu" + i, beishu);
				}
			} else {
				for (int i = 0; i < Integer.valueOf(addnumber); i++) {
					String beishu = (String) request.getParameter("beishu" + i) == null ? "1"
							: (String) request.getParameter("beishu" + i);
					if (!CommonUtil.checkBeishu(beishu)) {
						beishu = "1";
						checkResult = "倍数为大于0的正整数";
					}
					request.setAttribute("beishu" + i, beishu);
				}
			}

			logger.info("注码验证结果：返回pass为通过" + checkResult);
		} else {
			for (int i = 0; i < 5; i++) {
				String beishu = (String) request.getParameter("beishu" + i) == null ? "1"
						: (String) request.getParameter("beishu" + i);
				if (!CommonUtil.checkBeishu(beishu)) {
					beishu = "1";
					checkResult = "倍数为大于0的正整数";
				}
				request.setAttribute("beishu" + i, beishu);
			}
		}

		if (!checkResult.equals("pass")) {
			request.setAttribute("message", checkResult);
			request.setAttribute("betcode", betcode);
			request.setAttribute("flag", flag);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("addnumber", addnumber);
			if (flag.equals("rx1"))
				return "wap/ElevenDuoJin/addNumberR1";
			if (flag.equals("rx2"))
				return "wap/ElevenDuoJin/addNumberR2";
			if (flag.equals("rx3"))
				return "wap/ElevenDuoJin/addNumberR3";
			if (flag.equals("rx4"))
				return "wap/ElevenDuoJin/addNumberR4";
			if (flag.equals("rx5"))
				return "wap/ElevenDuoJin/addNumberR5";
			if (flag.equals("rx6"))
				return "wap/ElevenDuoJin/addNumberR6";
			if (flag.equals("rx7"))
				return "wap/ElevenDuoJin/addNumberR7";
			if (flag.equals("rx8"))
				return "wap/ElevenDuoJin/addNumberR8";
		}

		JSONObject json = new ElevenDuoJin().tobetcode(type, betcode);
		zhushu = String.valueOf(json.getInt("zhushu")) == null ? "1" : String
				.valueOf(json.getInt("zhushu"));
		request.setAttribute("betcode", betcode);
		request.setAttribute("flag", flag);
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("zhushu", zhushu);
		request.getSession().setAttribute("newaddnumber", addnumber);
		if (flag.equals("rx1"))
			return "wap/ElevenDuoJin/addNumberR1";
		if (flag.equals("rx2"))
			return "wap/ElevenDuoJin/addNumberR2";
		if (flag.equals("rx3"))
			return "wap/ElevenDuoJin/addNumberR3";
		if (flag.equals("rx4"))
			return "wap/ElevenDuoJin/addNumberR4";
		if (flag.equals("rx5"))
			return "wap/ElevenDuoJin/addNumberR5";
		if (flag.equals("rx6"))
			return "wap/ElevenDuoJin/addNumberR6";
		if (flag.equals("rx7"))
			return "wap/ElevenDuoJin/addNumberR7";
		if (flag.equals("rx8"))
			return "wap/ElevenDuoJin/addNumberR8";

		return null;
	}

	/**
	 * 转到相对应的Action
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/ToActionByAddNUmber.jspx",method=RequestMethod.POST)
	public String ToActionByAddNUmber( HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String plan = request.getParameter("plan");
		String bet = request.getParameter("bet");
		String count = request.getParameter("count");
		if (plan != null) {
			return toAddNumberPlan(request, response);
		}
		if (bet != null) {
			return betDeatil(request, response);
		}
		if (count != null) {
			return toCountsAmt( request, response);
		}
		return null;

	}

	/**
	 * 计算投入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toCountsAmt.jspx",method=RequestMethod.POST)
	public String toCountsAmt(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String betcode = request.getParameter("betcode");
		String flag = request.getParameter("flag");
		String addnumber = request.getParameter("addnumber");
		String zhushu = request.getParameter("zhushu") == null ? "1" : request
				.getParameter("zhushu");
		String newaddnumber = (String) request.getSession().getAttribute(
				"newaddnumber");
		String random = request.getParameter("random");

		// 计算注数
		// 获取倍数
		int count = 0;
		String checkResult;
		String type = new CommonUtil().getTypeByflag(flag);
		checkResult = new ElevenDuoJin().checkBetCode(betcode, "1", addnumber,
				type);// 倍数默认为"1” ， 确保验证功能通过
		request.setAttribute("zhushu", zhushu);
		if ("pass".equals(checkResult)) {
			if (!"".equals(newaddnumber) && newaddnumber != null) {
				if (!newaddnumber.equals(addnumber)) {
					checkResult = "请先生成追号计划";
					request.setAttribute("count", "count");
					request.setAttribute("random", random);
					int add = 0;
					if (random != null && !"".equals(random)) {
						add = Integer.parseInt(random);
					} else if (Integer.parseInt(newaddnumber) > Integer
							.parseInt(addnumber)) {
						add = Integer.parseInt(addnumber);
					} else {
						add = Integer.parseInt(newaddnumber);
					}
					for (int i = 0; i < add; i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
							checkResult = "倍数为大于0的正整数";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
				} else if (Integer.parseInt(random) != Integer
						.parseInt(newaddnumber)) {
					checkResult = "请先生成追号计划";
					request.setAttribute("addnumberError", "addnumberError");
					request.setAttribute("random", random);
					for (int i = 0; i < Integer.parseInt(random); i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
							checkResult = "倍数为大于0的正整数";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
				} else {
					for (int i = 0; i < Integer.valueOf(addnumber); i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
							checkResult = "倍数为大于0的正整数";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
					if (Integer.valueOf(addnumber) > 20)
						checkResult = "追号最高20期";
				}
			} else {
				checkResult = "请先生成追号计划";
				request.setAttribute("newaddnumbernull", "newaddnumbernull");
				if (!"".equals(random)) {
					for (int i = 0; i < Integer.parseInt(random); i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
					request.setAttribute("random", random);
				} else {
					for (int i = 0; i < 5; i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
				}
			}
		} else {
			if (!"".equals(random)) {
				for (int i = 0; i < Integer.parseInt(random); i++) {
					String beishu = (String) request.getParameter("beishu" + i);
					if (!CommonUtil.checkBeishu(beishu)) {
						beishu = "1";
					}
					if (beishu != null) {
						count += Integer.valueOf(beishu) * 2;
					} else {
						count += 1 * 2;
						beishu = "1";
					}
					request.setAttribute("beishu" + i, beishu);
				}
				request.setAttribute("random", random);
			} else {
				for (int i = 0; i < 5; i++) {
					String beishu = (String) request.getParameter("beishu" + i);
					if (!CommonUtil.checkBeishu(beishu)) {
						beishu = "1";
					}
					if (beishu != null) {
						count += Integer.valueOf(beishu) * 2;
					} else {
						count += 1 * 2;
						beishu = "1";
					}
					request.setAttribute("beishu" + i, beishu);
				}
			}
		}

		logger.info("注码验证结果：返回pass为通过" + checkResult);
		if (!checkResult.equals("pass")) {
			request.setAttribute("message", checkResult);
			request.setAttribute("betcode", betcode);
			request.setAttribute("flag", flag);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("addnumber", addnumber);
			if (flag.equals("rx1"))
				return "wap/ElevenDuoJin/addNumberR1";
			if (flag.equals("rx2"))
				return "wap/ElevenDuoJin/addNumberR2";
			if (flag.equals("rx3"))
				return "wap/ElevenDuoJin/addNumberR3";
			if (flag.equals("rx4"))
				return "wap/ElevenDuoJin/addNumberR4";
			if (flag.equals("rx5"))
				return "wap/ElevenDuoJin/addNumberR5";
			if (flag.equals("rx6"))
				return "wap/ElevenDuoJin/addNumberR6";
			if (flag.equals("rx7"))
				return "wap/ElevenDuoJin/addNumberR7";
			if (flag.equals("rx8"))
				return "wap/ElevenDuoJin/addNumberR8";
		}
		String term = CommonUtil.getTerm("T01012");
		JSONObject json = new ElevenDuoJin().tobetcode(type, betcode);
		zhushu = String.valueOf(json.getInt("zhushu")) == null ? "1" : String
				.valueOf(json.getInt("zhushu"));
		String toBetCode = json.getString("toBetcode");
		String viewBetcode = json.getString("viewBetcode");
		int amount = Integer.valueOf(zhushu) * count;
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("message", MessageUtil.TC_AmountError);
			request.setAttribute("betcode", betcode);
			request.setAttribute("flag", flag);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("addnumber", addnumber);
			if (flag.equals("rx1"))
				return "wap/ElevenDuoJin/addNumberR1";
			if (flag.equals("rx2"))
				return "wap/ElevenDuoJin/addNumberR2";
			if (flag.equals("rx3"))
				return "wap/ElevenDuoJin/addNumberR3";
			if (flag.equals("rx4"))
				return "wap/ElevenDuoJin/addNumberR4";
			if (flag.equals("rx5"))
				return "wap/ElevenDuoJin/addNumberR5";
			if (flag.equals("rx6"))
				return "wap/ElevenDuoJin/addNumberR6";
			if (flag.equals("rx7"))
				return "wap/ElevenDuoJin/addNumberR7";
			if (flag.equals("rx8"))
				return "wap/ElevenDuoJin/addNumberR8";
		}
		request.getSession().removeAttribute("random");
		request.getSession().removeAttribute("newaddnumber");
		logger.info("投注注码:" + toBetCode + "注数：" + zhushu + "投注金额 ：" + amount);
		request.setAttribute("betcode", betcode);
		request.setAttribute("flag", flag);
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("term", term);
		request.setAttribute("toBetCode", toBetCode);
		request.setAttribute("amount", String.valueOf(amount));
		request.setAttribute("viewBetcode", viewBetcode);
		return "wap/ElevenDuoJin/countsToAmt";
	}

	/**
	 * 追号计划投注详细
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/betDeatil.jspx",method=RequestMethod.POST)
	public String betDeatil(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String betcode = request.getParameter("betcode");
		String flag = request.getParameter("flag");
		String addnumber = request.getParameter("addnumber");
		String zhushu = request.getParameter("zhushu") == null ? "1" : request
				.getParameter("zhushu");
		String random = request.getParameter("random");
		String parameter = "betcode="+betcode+"&flag="+flag+"&addnumber="+addnumber+"&zhushu="+zhushu+"&random="+random;
		request.getSession().setAttribute("parameter", parameter);
		// 计算注数
		// 获取倍数
		int count = 0;
		String checkResult;
		String type = new CommonUtil().getTypeByflag(flag);
		checkResult = new ElevenDuoJin().checkBetCode(betcode, "1", addnumber,
				type);// 倍数默认为"1” ， 确保验证功能通过
		String newaddnumber = (String) request.getSession().getAttribute(
				"newaddnumber");
		request.setAttribute("zhushu", zhushu);
		if ("pass".equals(checkResult)) {
			// 判断是否产生了追号计划
			if (!"".equals(newaddnumber) && newaddnumber != null) {
				// 判断生产追号计划时的期数和当前显示是否一致
				if (!newaddnumber.equals(addnumber)) {
					checkResult = "请先生成追号计划";
					request.setAttribute("count", "count");
					request.setAttribute("random", random);
					int add = 0;
					if (random != null && !"".equals(random)) {
						add = Integer.parseInt(random);
					} else if (Integer.parseInt(newaddnumber) > Integer
							.parseInt(addnumber)) {
						add = Integer.parseInt(addnumber);
					} else {
						add = Integer.parseInt(newaddnumber);
					}

					for (int i = 0; i < add; i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
							checkResult = "倍数为大于0的正整数";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
					// 判断生成追号计划的期数是否跟计划列表的期数（个数）一致
				} else if (Integer.parseInt(random) != Integer
						.parseInt(newaddnumber)) {
					checkResult = "请先生成追号计划";
					request.setAttribute("addnumberError", "addnumberError");
					request.setAttribute("random", random);
					for (int i = 0; i < Integer.parseInt(random); i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
							checkResult = "倍数为大于0的正整数";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
				} else {
					for (int i = 0; i < Integer.valueOf(addnumber); i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
							checkResult = "倍数为大于0的正整数";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
					if (Integer.valueOf(addnumber) > 20) {
						checkResult = "追号最高20期";
					}

				}
			} else {
				checkResult = "请先生成追号计划";
				request.setAttribute("newaddnumbernull", "newaddnumbernull");
				if (!"".equals(random)) {
					for (int i = 0; i < Integer.parseInt(random); i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
					request.setAttribute("random", random);
				} else {
					for (int i = 0; i < 5; i++) {
						String beishu = (String) request.getParameter("beishu"
								+ i);
						if (!CommonUtil.checkBeishu(beishu)) {
							beishu = "1";
						}
						if (beishu != null) {
							count += Integer.valueOf(beishu) * 2;
						} else {
							count += 1 * 2;
							beishu = "1";
						}
						request.setAttribute("beishu" + i, beishu);
					}
				}
			}
		} else {
			if (!"".equals(random)) {
				for (int i = 0; i < Integer.parseInt(random); i++) {
					String beishu = (String) request.getParameter("beishu" + i);
					if (!CommonUtil.checkBeishu(beishu)) {
						beishu = "1";
					}
					if (beishu != null) {
						count += Integer.valueOf(beishu) * 2;
					} else {
						count += 1 * 2;
						beishu = "1";
					}
					request.setAttribute("beishu" + i, beishu);
				}
				request.setAttribute("random", random);
			} else {
				for (int i = 0; i < 5; i++) {
					String beishu = (String) request.getParameter("beishu" + i);
					if (!CommonUtil.checkBeishu(beishu)) {
						beishu = "1";
					}
					if (beishu != null) {
						count += Integer.valueOf(beishu) * 2;
					} else {
						count += 1 * 2;
						beishu = "1";
					}
					request.setAttribute("beishu" + i, beishu);
				}
			}
		}
		logger.info("注码验证结果：返回pass为通过" + checkResult);

		if (!checkResult.equals("pass")) {
			request.setAttribute("message", checkResult);
			request.setAttribute("betcode", betcode);
			request.setAttribute("flag", flag);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("addnumber", addnumber);
			if (flag.equals("rx1"))
				return "wap/ElevenDuoJin/addNumberR1";
			if (flag.equals("rx2"))
				return "wap/ElevenDuoJin/addNumberR2";
			if (flag.equals("rx3"))
				return "wap/ElevenDuoJin/addNumberR3";
			if (flag.equals("rx4"))
				return "wap/ElevenDuoJin/addNumberR4";
			if (flag.equals("rx5"))
				return "wap/ElevenDuoJin/addNumberR5";
			if (flag.equals("rx6"))
				return "wap/ElevenDuoJin/addNumberR6";
			if (flag.equals("rx7"))
				return "wap/ElevenDuoJin/addNumberR7";
			if (flag.equals("rx8"))
				return "wap/ElevenDuoJin/addNumberR8";
		}
		String term = CommonUtil.getTerm("T01012");
		JSONObject json = new ElevenDuoJin().tobetcode(type, betcode);
		zhushu = String.valueOf(json.getInt("zhushu")) == null ? "1" : String
				.valueOf(json.getInt("zhushu"));
		String toBetCode = json.getString("toBetcode");
		String viewBetcode = json.getString("viewBetcode");

		int amount = Integer.valueOf(zhushu) * count;
		if (!CommonUtil.TCverifyAmount(amount)) {
			request.setAttribute("message", MessageUtil.TC_AmountError);
			request.setAttribute("betcode", betcode);
			request.setAttribute("flag", flag);
			request.setAttribute("zhushu", zhushu);
			request.setAttribute("addnumber", addnumber);
			if (flag.equals("rx1"))
				return "wap/ElevenDuoJin/addNumberR1";
			if (flag.equals("rx2"))
				return "wap/ElevenDuoJin/addNumberR2";
			if (flag.equals("rx3"))
				return "wap/ElevenDuoJin/addNumberR3";
			if (flag.equals("rx4"))
				return "wap/ElevenDuoJin/addNumberR4";
			if (flag.equals("rx5"))
				return "wap/ElevenDuoJin/addNumberR5";
			if (flag.equals("rx6"))
				return "wap/ElevenDuoJin/addNumberR6";
			if (flag.equals("rx7"))
				return "wap/ElevenDuoJin/addNumberR7";
			if (flag.equals("rx8"))
				return "wap/ElevenDuoJin/addNumberR8";
		}
		request.getSession().removeAttribute("random");
		request.getSession().removeAttribute("newaddnumber");
		logger.info("投注注码:" + toBetCode + "注数：" + zhushu + "投注金额 ：" + amount);
		request.setAttribute("betcode", betcode);
		request.setAttribute("flag", flag);
		request.setAttribute("addnumber", addnumber);
		request.setAttribute("zhushu", zhushu);
		request.setAttribute("term", term);
		request.setAttribute("toBetCode", toBetCode);
		request.setAttribute("amount", String.valueOf(amount));
		request.setAttribute("viewBetcode", viewBetcode);
		return "wap/ElevenDuoJin/addBetDeatil";
	}

	/**
	 * 投注
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws JSONException
	 */
	@RequestMapping(value="/bet.jspa")
	public String bet(HttpServletRequest request, HttpServletResponse response)
			throws ParseException {
		TuserInfoBean tuserInfoBean =(TuserInfoBean)request.getSession().getAttribute("user");
		String userno = tuserInfoBean.getUserno();
		String zhuma, amount, beishu, zhushu, addnumber = "", token;
		// 获取当前期号
		String term = CommonUtil.getTerm("T01012");
		String ttssBet = "";
		List<String> bsList = null;
		String channel = WapUtil.getChannelId(request);
		// 用于判断 是否因用户未登录中断操作 而去到登陆页面 如果outFormData 不存在 那么就正常运行获取表单 否则
		// 从request.getAttribute 中获取 与表单同名同类型的obj
		if (request.getAttribute("outFormData") == null
				|| request.getAttribute("outFormData").equals("")) {
			// 获取输入页面的参数
			zhushu = request.getParameter("zhushu"); // 注数
			zhuma = request.getParameter("zhuma"); // 注码
			amount = request.getParameter("amount"); // 金额
			token = request.getParameter("token");// 判断是否重复提交
			addnumber = request.getParameter("addnumber");
			if ("".equals(addnumber) || addnumber == null) {
				addnumber = "1";
			}
			bsList = new ArrayList<String>();
			for (int i = 0; i < Integer.valueOf(addnumber); i++) {
				beishu = (String) request.getParameter("beishu" + i);
				bsList.add(beishu);
			}
		} else {// 获取request.getAttribute中的存储
			bsList = new ArrayList<String>();
			String[] zhushus = request.getAttribute("zhushu") == null ? null
					: (String[]) request.getAttribute("zhushu");
			String[] addnumbers = request.getAttribute("addnumber") == null ? null
					: (String[]) request.getAttribute("addnumber");
			for (int i = 0; i < Integer.valueOf(addnumbers[0]); i++) {
				String[] beishus = request.getAttribute("beishu" + i) == null ? null
						: (String[]) request.getAttribute("beishu" + i);
				beishu = beishus == null || beishus[0].equals("") ? "0"
						: beishus[0]; // 倍数

				bsList.add(beishu);
			}
			String[] zhumas = request.getAttribute("zhuma") == null ? null
					: (String[]) request.getAttribute("zhuma");
			String[] amounts = request.getAttribute("amount") == null ? null
					: (String[]) request.getAttribute("amount");
			String[] tokens = request.getAttribute("token") == null ? null
					: (String[]) request.getAttribute("token");

			// 获取输入页面的参数
			zhushu = zhushus == null || zhushus[0].equals("") ? "0"
					: zhushus[0]; // 注数

			zhuma = zhumas == null || zhumas[0].equals("") ? "0" : zhumas[0]; // 注码
			amount = amounts == null || amounts[0].equals("") ? "0"
					: amounts[0]; // 金额
			addnumber = addnumbers == null || addnumbers[0].equals("") ? "0"
					: addnumbers[0]; // 金额
			token = tokens == null || tokens[0].equals("") ? "0" : tokens[0];

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
			ttssBet = JsonToJrtLotUtil.sendToADDBet(userno, "T01012", term,
					zhuma, bsList, "2", "", amount, addnumber, channel, zhushu);
			request.setAttribute("err_msg", ttssBet);
		} else {
			request.setAttribute("err_msg", "请勿重复提交");
		}
		return "wap/BetSuccess";
	}
}
