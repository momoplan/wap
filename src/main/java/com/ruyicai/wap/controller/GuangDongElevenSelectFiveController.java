package com.ruyicai.wap.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.GuangDongElevenSelectFiveUtil;
import com.ruyicai.wap.util.LotteryUtil;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.ValidateLotteryUtil;

@Controller
@RequestMapping("/guangDongElevenSelectFive")
public class GuangDongElevenSelectFiveController {
	private static final Logger logger = Logger.getLogger(GuangDongElevenSelectFiveController.class);
	/**
	 * 任选自选
	 * @return
	 */
	@RequestMapping("/optionSelfDetail.jspx")
	public ModelAndView optionSelfDetail(
			@RequestParam(value="zhuMa",defaultValue="") String zhuMa,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "zhuMa="+zhuMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/optionOneSelfDetail页面参数zhuMa="
				+zhuMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveOptionSelf(zhuMa, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("zhuMa", zhuMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSelfViewName(type));
			logger.info("GuangDongElevenSelectFiveController/optionSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/optionSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getBetCodeAndZhuShu(zhuMa, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("zhuMa", zhuMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSelfViewName(type));
			logger.info("GuangDongElevenSelectFiveController/optionSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	/**
	 * 任选单式机选
	 * @return
	 */
	@RequestMapping("/optionSingleAutoDetail.jspx")
	public ModelAndView optionSingleAutoDetail(
			@RequestParam(value="zhuShu",defaultValue="") String zhuShu,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "zhuShu="+zhuShu+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/optionSingleAutoDetail页面参数zhuShu="
				+zhuShu+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveOptionSingleAuto(zhuShu, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("zhuShu", zhuShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSingleAutoViewName(type));
			logger.info("GuangDongElevenSelectFiveController/optionSingleAutoDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/optionSingleAutoDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getSingleAutoBetCode(zhuShu, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int amount = Integer.parseInt(zhuShu)*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("zhuShu", zhuShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSingleAutoViewName(type));
			logger.info("GuangDongElevenSelectFiveController/optionSingleAutoDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	/**
	 * 任选复式机选
	 * @return
	 */
	@RequestMapping("/optionMultipleAutoDetail.jspx")
	public ModelAndView optionMultipleAutoDetail(
			@RequestParam(value="geShu",defaultValue="") String geShu,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "geShu="+geShu+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/optionMultipleAutoDetail页面参数geShu="
				+geShu+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveOptionMultipleAuto(geShu, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("geShu", geShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionMultipleAutoViewName(type));
			logger.info("GuangDongElevenSelectFiveController/optionMultipleAutoDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/optionMultipleAutoDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getMultipleAutoBetCode(geShu, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("geShu", geShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionMultipleAutoViewName(type));
			logger.info("GuangDongElevenSelectFiveController/optionMultipleAutoDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	@RequestMapping("/danTuoSelfDetail.jspx")
	public ModelAndView danTuoSelfDetail(
			@RequestParam(value="danMa",defaultValue="") String danMa,
			@RequestParam(value="tuoMa",defaultValue="") String tuoMa,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "danMa="+danMa+"&tuoMa="+tuoMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/danTuoSelfDetail页面参数danMa="
				+danMa+"&tuoMa="+tuoMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveDanTuoSelf(danMa, tuoMa, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("danMa", danMa);
			modelAndView.addObject("tuoMa", tuoMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSelfViewName(type));
			logger.info("GuangDongElevenSelectFiveController/danTuoSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/danTuoSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getBetCodeAndZhuShu(danMa+"|"+tuoMa, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("danMa", danMa);
			modelAndView.addObject("tuoMa", tuoMa);
			modelAndView.addObject("beiShu", beiShu);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSelfViewName(type));
			logger.info("GuangDongElevenSelectFiveController/danTuoSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardTwoSingleSelfDetail.jspx")
	public ModelAndView directSelectForwardTwoSingleSelfDetail(
			@RequestParam(value="first",defaultValue="") String first,
			@RequestParam(value="second",defaultValue="") String second,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "first="+first+"&second="+second+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoSingleSelfDetail页面参数first="
				+first+"&second="+second+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveDirectSelectForwardTwoSingleSelf(first, second, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardTwoSingleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoSingleSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoSingleSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getBetCodeAndZhuShu(first+"|"+second, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardTwoSingleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoSingleSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardTwoMultipleSelfDetail.jspx")
	public ModelAndView directSelectForwardTwoMultipleSelfDetail(
			@RequestParam(value="zhuMa",defaultValue="") String zhuMa,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "zhuMa="+zhuMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoMultipleSelfDetail页面参数zhuMa="
				+zhuMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveDirectSelectForwardTwoMultipleSelf(zhuMa, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("zhuMa", zhuMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardTwoMultipleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoMultipleSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoMultipleSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getBetCodeAndZhuShu(zhuMa, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("zhuMa", zhuMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardTwoMultipleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoMultipleSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardTwoPositionMultipleSelfDetail.jspx")
	public ModelAndView directSelectForwardTwoPositionMultipleSelfDetail(
			@RequestParam(value="first",defaultValue="") String first,
			@RequestParam(value="second",defaultValue="") String second,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "first="+first+"&second="+second+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoPositionMultipleSelfDetail页面参数first="
				+first+"&second="+second+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveDirectSelectForwardTwoPositionMultipleSelf(first, second, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardTwoPositionMultipleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoPositionMultipleSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoPositionMultipleSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getBetCodeAndZhuShu(first+"|"+second, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardTwoPositionMultipleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardTwoPositionMultipleSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardThreeSingleSelfDetail.jspx")
	public ModelAndView directSelectForwardThreeSingleSelfDetail(
			@RequestParam(value="first",defaultValue="") String first,
			@RequestParam(value="second",defaultValue="") String second,
			@RequestParam(value="third",defaultValue="") String third,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "first="+first+"&second="+second+"&third="+third+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreeSingleSelfDetail页面参数first="
				+first+"&second="+second+"&third="+third+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveDirectSelectForwardThreeSingleSelf(first, second, third, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("third", third);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardThreeSingleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreeSingleSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreeSingleSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getBetCodeAndZhuShu(first+"|"+second+"|"+third, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("third", third);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardThreeSingleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreeSingleSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardThreeMultipleSelfDetail.jspx")
	public ModelAndView directSelectForwardThreeMultipleSelfDetail(
			@RequestParam(value="zhuMa",defaultValue="") String zhuMa,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "zhuMa="+zhuMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreeMultipleSelfDetail页面参数zhuMa="
				+zhuMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveDirectSelectForwardThreeMultipleSelf(zhuMa, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("zhuMa", zhuMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardThreeMultipleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreeMultipleSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreeMultipleSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getBetCodeAndZhuShu(zhuMa, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("zhuMa", zhuMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardThreeMultipleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreeMultipleSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardThreePositionMultipleSelfDetail.jspx")
	public ModelAndView directSelectForwardThreePositionMultipleSelfDetail(
			@RequestParam(value="first",defaultValue="") String first,
			@RequestParam(value="second",defaultValue="") String second,
			@RequestParam(value="third",defaultValue="") String third,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01014") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "first="+first+"&second="+second+"&third="+third+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreePositionMultipleSelfDetail页面参数first="
				+first+"&second="+second+"&third="+third+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongElevenSelectFiveDirectSelectForwardThreePositionMultipleSelf(first, second, third, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("third", third);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardThreePositionMultipleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreePositionMultipleSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreePositionMultipleSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongElevenSelectFiveUtil.getBetCodeAndZhuShu(first+"|"+second+"|"+third, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("third", third);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/gd11x5/directSelectForwardThreePositionMultipleSelf");
			logger.info("GuangDongElevenSelectFiveController/directSelectForwardThreePositionMultipleSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongElevenSelectFiveTypeName(type);
		String lotName = CommonUtil.getLotnameByLotno(lotNo);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("betCodeView", betCodeView);
		modelAndView.addObject("beiShu", beiShu);
		modelAndView.addObject("zhuShu", zhuShu);
		modelAndView.addObject("addNumber", addNumber);
		modelAndView.addObject("amount", amount);
		modelAndView.addObject("amountView", amountView);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("betCode", betCode);
		modelAndView.addObject("typeName", typeName);
		modelAndView.addObject("lotName", lotName);
		modelAndView.addObject("lotNo", lotNo);
		modelAndView.addObject("oneAmount", oneAmount);
		CommonUtil.setToken(request, modelAndView);
		modelAndView.setViewName("wap/gd11x5/betDetail");
		return modelAndView;
	}
	public ModelAndView getModelAndView(ModelAndView modelAndView){
		String headLine = SelectAllUtil.selectLotteryTermAndEndtime(Constants.LOTNO_GDSYXW);
		String batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("lotNo", Constants.LOTNO_GDSYXW);
		modelAndView.addObject("headLine", headLine);
		return modelAndView;
	}
	public String getOptionSelfViewName(String type){
		if("R1".equals(type)){
			return "wap/gd11x5/optionOneSelf";
		}else if("R2".equals(type)){
			return "wap/gd11x5/optionTwoSelf";
		}else if("R3".equals(type)){
			return "wap/gd11x5/optionThreeSelf";
		}else if("R4".equals(type)){
			return "wap/gd11x5/optionFourSelf";
		}else if("R5".equals(type)){
			return "wap/gd11x5/optionFiveSelf";
		}else if("R6".equals(type)){
			return "wap/gd11x5/optionSixSelf";
		}else if("R7".equals(type)){
			return "wap/gd11x5/optionSevenSelf";
		}else if("R8".equals(type)){
			return "wap/gd11x5/optionEightSelf";
		}else if("Z2".equals(type)){
			return "wap/gd11x5/groupSelectForwardTwoSelf";
		}else if("Z3".equals(type)){
			return "wap/gd11x5/groupSelectForwardThreeSelf";
		}else if("D|R2".equals(type)){
			return "wap/gd11x5/optionTwoDanTuoSelf";
		}else if("D|R3".equals(type)){
			return "wap/gd11x5/optionThreeDanTuoSelf";
		}else if("D|R4".equals(type)){
			return "wap/gd11x5/optionFourDanTuoSelf";
		}else if("D|R5".equals(type)){
			return "wap/gd11x5/optionFiveDanTuoSelf";
		}else if("D|R6".equals(type)){
			return "wap/gd11x5/optionSixDanTuoSelf";
		}else if("D|R7".equals(type)){
			return "wap/gd11x5/optionSevenDanTuoSelf";
		}else if("D|Z2".equals(type)){
			return "wap/gd11x5/groupSelectForwardTwoDanTuoSelf";
		}else if("D|Z3".equals(type)){
			return "wap/gd11x5/groupSelectForwardThreeDanTuoSelf";
		}else{
			return "";
		}
	}
	public String getOptionSingleAutoViewName(String type){
		if("R1".equals(type)){
			return "wap/gd11x5/optionOneSingleAuto";
		}else if("R2".equals(type)){
			return "wap/gd11x5/optionTwoSingleAuto";
		}else if("R3".equals(type)){
			return "wap/gd11x5/optionThreeSingleAuto";
		}else if("R4".equals(type)){
			return "wap/gd11x5/optionFourSingleAuto";
		}else if("R5".equals(type)){
			return "wap/gd11x5/optionFiveSingleAuto";
		}else if("R6".equals(type)){
			return "wap/gd11x5/optionSixSingleAuto";
		}else if("R7".equals(type)){
			return "wap/gd11x5/optionSevenSingleAuto";
		}else if("R8".equals(type)){
			return "wap/gd11x5/optionEightSingleAuto";
		}else if("Z2".equals(type)){
			return "wap/gd11x5/groupSelectForwardTwoSingleAuto";
		}else if("Z3".equals(type)){
			return "wap/gd11x5/groupSelectForwardThreeSingleAuto";
		}else{
			return "";
		}
	}
	public String getOptionMultipleAutoViewName(String type){
		if("R1".equals(type)){
			return "wap/gd11x5/optionOneMultipleAuto";
		}else if("R2".equals(type)){
			return "wap/gd11x5/optionTwoMultipleAuto";
		}else if("R3".equals(type)){
			return "wap/gd11x5/optionThreeMultipleAuto";
		}else if("R4".equals(type)){
			return "wap/gd11x5/optionFourMultipleAuto";
		}else if("R5".equals(type)){
			return "wap/gd11x5/optionFiveMultipleAuto";
		}else if("R6".equals(type)){
			return "wap/gd11x5/optionSixMultipleAuto";
		}else if("R7".equals(type)){
			return "wap/gd11x5/optionSevenMultipleAuto";
		}else if("R8".equals(type)){
			return "wap/gd11x5/optionEightMultipleAuto";
		}else if("Z2".equals(type)){
			return "wap/gd11x5/groupSelectForwardTwoMultipleAuto";
		}else if("Z3".equals(type)){
			return "wap/gd11x5/groupSelectForwardThreeMultipleAuto";
		}else{
			return "";
		}
	}
}
