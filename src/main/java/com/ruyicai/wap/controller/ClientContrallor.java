package com.ruyicai.wap.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruyicai.wap.Global;
import com.ruyicai.wap.bean.Client;

@RequestMapping("/clientContrallor")
@Controller
public class ClientContrallor {
	
	private static final Logger logger = Logger.getLogger(ClientContrallor.class);
	@RequestMapping("/clientDown.jspx")
	public String clientDown(Model model){
		model.addAttribute("clientList", selectClient());
		System.out.println(selectClient());
		return "/client/download";
	}
	
	public static List<Client> selectClient(){
		logger.info("ClientContrallor/selectClient查询客户端下载列表");
		List<Client> clientList = new ArrayList<Client>();
		try {
			clientList = Global.clientDao.selectClient();
			logger.info("ClientContrallor/selectClient查询客户端下载列表结果："+clientList);
		} catch (Exception e) {
			logger.error("ClientContrallor/selectClient查询客户端下载列表异常");
			return null;
		}
		
		return clientList;
	}
}
