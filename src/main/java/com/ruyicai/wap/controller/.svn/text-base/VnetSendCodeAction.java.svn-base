package com.ruyicai.wap.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Cryptogram;

@Controller
public class VnetSendCodeAction {
	private static final Logger logger = Logger
			.getLogger(VnetSendCodeAction.class);
	private static ResourceBundle rbint = ResourceBundle
			.getBundle("jrtWAPSite");

	/**
	 * 发送验证码
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping("/sendcode.do") 
	public String vnetcode(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("互联星空调用认证码开始····");
		String DecryptStr = "";
		try { // 捕获互联星空传过来的加密信息
			logger.info("捕获互联星空传过来的加密信息...");
			String vnet_user_encry_info = request
					.getParameter("vnet_user_encry_info");
			// logger.info("捕获互联星空传过来的加密信息..."+vnet_user_encry_info);
			vnet_user_encry_info = vnet_user_encry_info.replaceAll(" ", "+");
			logger.info("捕获互联星空调用接口的加密参数：" + vnet_user_encry_info);
			// 解密
			String Key = rbint.getString("vnetKey");
			// 解密示例
			DecryptStr = Cryptogram.decryptByKey(vnet_user_encry_info, Key);
			logger.info("互联星空调用接口的加密参数解密结果:" + DecryptStr);
			if (!"".equals(DecryptStr)) {
				// 时间戳$地区编码$11位手机号$用户平台ID$是否是手机用户登陆$是否从门户发起
				String[] DecryptArr = DecryptStr.split("\\$");
				if (DecryptArr.length == 6) {
					// 生成认证码
					String code = "";
					try {
						code = CommonUtil.getUuid();
						ServletContext servletContext = request.getSession()
								.getServletContext();
						String str = DecryptArr[2];
						if ("".equals(str) || str == null) {
							logger.info("互联星空加密信息手机号为空");
							code = "";
						} else if (!"133".equals(str.substring(0, 3))
								&& !"153".equals(str.substring(0, 3))
								&& !"180".equals(str.substring(0, 3))
								&& !"189".equals(str.substring(0, 3))) {
							logger.info("互联星空加密信息手机号非电信手机号：" + str);
							code = "";
						}
						if (!"".equals(str) && str != null) {
							servletContext.setAttribute(str, code);
							servletContext
									.setAttribute("mobile", DecryptArr[2]);
							servletContext.setAttribute("user_id",
									DecryptArr[3]);
						}
						if (code != null)
							request.getSession().setAttribute("code", code);
						StringBuffer buffer = new StringBuffer();
						buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
						buffer.append("<code>");
						buffer.append(code);
						buffer.append("</code>");
						logger.info("生成认证码:" + buffer);
						response.setCharacterEncoding("utf-8");
						response.getWriter().write(buffer.toString());
						response.getWriter().flush();
						response.getWriter().close();
					} catch (IOException e) {
						code = "";
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("互联星空调用认证码异常：" + e.getMessage());
					}

					request.getSession().setAttribute("date", DecryptArr[0]);
					request.getSession().setAttribute("mobile", DecryptArr[2]);
					request.getSession().setAttribute("user_id", DecryptArr[3]);
					request.getSession().setAttribute("isLoginByMObile",
							DecryptArr[4]);
					request.getSession().setAttribute("isFromVnet",
							DecryptArr[5]);
					logger.info("互联星空调用认证码接口成功");
				} else {
					logger.info("~~~~~~~~~~~~互联星空调用认证码接口失败：获取参数为空, 生成认证码失败");
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			DecryptStr = "";
			e1.printStackTrace();
			logger.debug("catch this exception" + e1.getMessage());
		}

		return null;

	}
}
