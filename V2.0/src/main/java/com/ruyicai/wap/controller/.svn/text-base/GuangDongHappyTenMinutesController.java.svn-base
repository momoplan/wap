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
import com.ruyicai.wap.util.GuangDongHappyTenMinutesUtil;
import com.ruyicai.wap.util.LotteryUtil;
import com.ruyicai.wap.util.SelectAllUtil;
import com.ruyicai.wap.util.ValidateLotteryUtil;

@Controller
@RequestMapping("/guangDongHappyTenMinutes")
public class GuangDongHappyTenMinutesController {
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 任选自选
	 * @return
	 */
	@RequestMapping("/optionSelfDetail.jspx")
	public ModelAndView optionSelfDetail(
			@RequestParam(value="zhuMa",defaultValue="") String zhuMa,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01015") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "zhuMa="+zhuMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongHappyTenMinutesController/optionOneSelfDetail页面参数zhuMa="
				+zhuMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesSelf(zhuMa, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("zhuMa", zhuMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSelfViewName(type));
			logger.info("GuangDongHappyTenMinutesController/optionSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongHappyTenMinutesController/optionSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getBetCodeAndZhuShu(zhuMa, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("zhuMa", zhuMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSelfViewName(type));
			logger.info("GuangDongHappyTenMinutesController/optionSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongHappyTenMinutesTypeName(type);
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
		modelAndView.setViewName("wap/KL10/betDetail");
		return modelAndView;
	}
	/**
	 * 任选组选直选单式机选
	 * @return
	 */
	@RequestMapping("/optionSingleAutoDetail.jspx")
	public ModelAndView optionSingleAutoDetail(
			@RequestParam(value="zhuShu",defaultValue="") String zhuShu,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01015") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "zhuShu="+zhuShu+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongHappyTenMinutesController/optionSingleAutoDetail页面参数zhuShu="
				+zhuShu+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesSingleAuto(zhuShu, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("zhuShu", zhuShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSingleAutoViewName(type));
			logger.info("GuangDongHappyTenMinutesController/optionSingleAutoDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongHappyTenMinutesController/optionSingleAutoDetail参数验证通过");
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getSingleAutoBetCode(zhuShu, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int amount = Integer.parseInt(zhuShu)*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("zhuShu", zhuShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSingleAutoViewName(type));
			logger.info("GuangDongHappyTenMinutesController/optionSingleAutoDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongHappyTenMinutesTypeName(type);
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
		modelAndView.setViewName("wap/KL10/betDetail");
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
			@RequestParam(value="lotNo",defaultValue="T01015") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "geShu="+geShu+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongHappyTenMinutesController/optionMultipleAutoDetail页面参数geShu="
				+geShu+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesMultipleAuto(geShu, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("geShu", geShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionMultipleAutoViewName(type));
			logger.info("GuangDongHappyTenMinutesController/optionMultipleAutoDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongHappyTenMinutesController/optionMultipleAutoDetail参数验证通过");
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getMultipleAutoBetCode(geShu, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("geShu", geShu);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionMultipleAutoViewName(type));
			logger.info("GuangDongHappyTenMinutesController/optionMultipleAutoDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongHappyTenMinutesTypeName(type);
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
		modelAndView.setViewName("wap/KL10/betDetail");
		return modelAndView;
	}
	/**
	 * 胆拖
	 * @param danMa
	 * @param tuoMa
	 * @param beiShu
	 * @param addNumber
	 * @param lotNo
	 * @param batchCode
	 * @param oneAmount
	 * @param type
	 * @param request
	 * @return
	 */
	@RequestMapping("/danTuoSelfDetail.jspx")
	public ModelAndView danTuoSelfDetail(
			@RequestParam(value="danMa",defaultValue="") String danMa,
			@RequestParam(value="tuoMa",defaultValue="") String tuoMa,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01015") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "danMa="+danMa+"&tuoMa="+tuoMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongHappyTenMinutesController/danTuoSelfDetail页面参数danMa="
				+danMa+"&tuoMa="+tuoMa+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesDanTuo(danMa, tuoMa, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("danMa", danMa);
			modelAndView.addObject("tuoMa", tuoMa);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSelfViewName(type));
			logger.info("GuangDongHappyTenMinutesController/danTuoSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongHappyTenMinutesController/danTuoSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getBetCodeAndZhuShu(danMa+"|"+tuoMa, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("danMa", danMa);
			modelAndView.addObject("tuoMa", tuoMa);
			modelAndView.addObject("beiShu", beiShu);
			getModelAndView(modelAndView);
			modelAndView.setViewName(getOptionSelfViewName(type));
			logger.info("GuangDongHappyTenMinutesController/danTuoSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongHappyTenMinutesTypeName(type);
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
		modelAndView.setViewName("wap/KL10/betDetail");
		return modelAndView;
	}
	@RequestMapping("/selectTwoLinkDirectSelfDetail.jspx")
	public ModelAndView selectTwoLinkDirectSelfDetail(
			@RequestParam(value="first",defaultValue="") String first,
			@RequestParam(value="second",defaultValue="") String second,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01015") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "first="+first+"&second="+second+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongHappyTenMinutesController/selectTwoLinkDirectSelfDetail页面参数first="
				+first+"&second="+second+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesSelectTwoLinkDirectSelf(first, second, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/KL10/selectTwoLinkDirectSelf");
			logger.info("GuangDongHappyTenMinutesController/selectTwoLinkDirectSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongHappyTenMinutesController/selectTwoLinkDirectSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getBetCodeAndZhuShu(first+"|"+second, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/KL10/selectTwoLinkDirectSelf");
			logger.info("GuangDongHappyTenMinutesController/selectTwoLinkDirectSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongHappyTenMinutesTypeName(type);
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
		modelAndView.setViewName("wap/KL10/betDetail");
		return modelAndView;
	}
	@RequestMapping("/selectTwoLinkDirectMultipleAutoDetail.jspx")
	public ModelAndView selectTwoLinkDirectMultipleAutoDetail(
			@RequestParam(value="first",defaultValue="") String first,
			@RequestParam(value="second",defaultValue="") String second,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01015") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "first="+first+"&second="+second+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongHappyTenMinutesController/selectTwoLinkDirectMultipleAutoDetail页面参数first="
				+first+"&second="+second+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesSelectTwoLinkDirectMultipleAuto(first, second, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/KL10/selectTwoLinkDirectMultipleAuto");
			logger.info("GuangDongHappyTenMinutesController/selectTwoLinkDirectMultipleAutoDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongHappyTenMinutesController/selectTwoLinkDirectMultipleAutoDetail参数验证通过");
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getMultipleAutoBetCode(first+"|"+second, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		while (zhuShu==0) {
			jsonObject = GuangDongHappyTenMinutesUtil.getMultipleAutoBetCode(first+"|"+second, type);
			betCode = jsonObject.getString("betCode");
			betCodeView =jsonObject.getString("betCodeView");
			zhuShu =jsonObject.getInt("zhuShu");
			
		}
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/KL10/selectTwoLinkDirectMultipleAuto");
			logger.info("GuangDongHappyTenMinutesController/selectTwoLinkDirectMultipleAutoDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongHappyTenMinutesTypeName(type);
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
		modelAndView.setViewName("wap/KL10/betDetail");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardThreeSelfDetail.jspx")
	public ModelAndView directSelectForwardThreeSelfDetail(
			@RequestParam(value="first",defaultValue="") String first,
			@RequestParam(value="second",defaultValue="") String second,
			@RequestParam(value="third",defaultValue="") String third,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01015") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "first="+first+"&second="+second+"&third="+third+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongHappyTenMinutesController/directSelectForwardThreeSelfDetail页面参数first="
				+first+"&second="+second+"&third="+third+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesDirectSelectForwardThreeSelf(first, second, third, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("third", third);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/KL10/directSelectForwardThreeSelf");
			logger.info("GuangDongHappyTenMinutesController/directSelectForwardThreeSelfDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongHappyTenMinutesController/directSelectForwardThreeSelfDetail参数验证通过");
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getBetCodeAndZhuShu(first+"|"+second+"|"+third, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("third", third);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/KL10/directSelectForwardThreeSelf");
			logger.info("GuangDongHappyTenMinutesController/directSelectForwardThreeSelfDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongHappyTenMinutesTypeName(type);
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
		modelAndView.setViewName("wap/KL10/betDetail");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardThreeMultipleAutoDetail.jspx")
	public ModelAndView directSelectForwardThreeMultipleAutoDetail(
			@RequestParam(value="first",defaultValue="") String first,
			@RequestParam(value="second",defaultValue="") String second,
			@RequestParam(value="third",defaultValue="") String third,
			@RequestParam(value="beiShu",defaultValue="1") String beiShu,
			@RequestParam(value="addNumber",defaultValue="1") String addNumber,
			@RequestParam(value="lotNo",defaultValue="T01015") String lotNo,
			@RequestParam(value="batchCode",defaultValue="") String batchCode,
			@RequestParam(value="oneAmount",defaultValue="2") String oneAmount,
			@RequestParam(value="type",defaultValue="") String type,
			HttpServletRequest request){
		String parameter = "first="+first+"&second="+second+"&third="+third+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode;
		request.getSession().setAttribute("parameter", parameter);
		logger.info("GuangDongHappyTenMinutesController/directSelectForwardThreeMultipleAutoDetail页面参数first="
				+first+"&second="+second+"&third="+third+"&beiShu="+beiShu+"&type="+type+"&addNumber="+addNumber
				+"&lotNo="+lotNo+"&oneAmount="+oneAmount+"&batchCode="+batchCode);		
		ModelAndView modelAndView = new ModelAndView();
		String validateResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesDirectSelectForwardThreeMultipleAuto(first, second, third, beiShu, addNumber, type);
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		modelAndView.addObject("winCodeList", winCodeList);
		if(validateResult!=null&&!"".equals(validateResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("third", third);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/KL10/directSelectForwardThreeMultipleAuto");
			logger.info("GuangDongHappyTenMinutesController/directSelectForwardThreeMultipleAutoDetail参数验证未通过validateResult:"+validateResult);
			return modelAndView;
		}
		logger.info("GuangDongHappyTenMinutesController/directSelectForwardThreeMultipleAutoDetail参数验证通过");
		JSONObject jsonObject = GuangDongHappyTenMinutesUtil.getMultipleAutoBetCode(first+"|"+second+"|"+third, type);
		String betCode = jsonObject.getString("betCode");
		String betCodeView =jsonObject.getString("betCodeView");
		int zhuShu =jsonObject.getInt("zhuShu");
		int amount = zhuShu*Integer.parseInt(beiShu)*2;
		String validateTotalAmoutResult = ValidateLotteryUtil.validateGuangDongHappyTenMinutesTotalAmount(amount);
		if(validateTotalAmoutResult!=null&&!"".equals(validateTotalAmoutResult)){
			batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
			modelAndView.addObject("messageError", validateTotalAmoutResult);
			modelAndView.addObject("first", first);
			modelAndView.addObject("second", second);
			modelAndView.addObject("third", third);
			modelAndView.addObject("beiShu", beiShu);
			modelAndView.addObject("addNumber", addNumber);
			modelAndView.addObject("lotNo", lotNo);
			modelAndView.addObject("type", type);
			getModelAndView(modelAndView);
			modelAndView.setViewName("wap/KL10/directSelectForwardThreeMultipleAuto");
			logger.info("GuangDongHappyTenMinutesController/directSelectForwardThreeMultipleAutoDetail金额验证未通过validateTotalAmoutResult:"+validateTotalAmoutResult);
			return modelAndView;
		}
		String amountView = CommonUtil.getBalanceFormat((amount*100)+"");
		String typeName = LotteryUtil.getGuangDongHappyTenMinutesTypeName(type);
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
		modelAndView.setViewName("wap/KL10/betDetail");
		return modelAndView;
	}
public ModelAndView getModelAndView(ModelAndView modelAndView){
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		String headLine = SelectAllUtil.selectLotteryTermAndEndtime(Constants.LOTNO_GDKLSF);
		String batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("headLine", headLine);
		modelAndView.addObject("lotNo", Constants.LOTNO_GDKLSF);
		modelAndView.addObject("winCodeList", winCodeList);
		return modelAndView;
	}
	public String getOptionSelfViewName(String type){
		if("S1".equals(type)){
			return "wap/KL10/selectOneNumberSelf";
		}else if("H1".equals(type)){
			return "wap/KL10/selectOneRedSelf";
		}else if("R2".equals(type)){
			return "wap/KL10/optionTwoSelf";
		}else if("R3".equals(type)){
			return "wap/KL10/optionThreeSelf";
		}else if("R4".equals(type)){
			return "wap/KL10/optionFourSelf";
		}else if("R5".equals(type)){
			return "wap/KL10/optionFiveSelf";
		}else if("Z2".equals(type)){
			return "wap/KL10/selectTwoLinkGroupSelf";
		}else if("Z3".equals(type)){
			return "wap/KL10/groupSelectForwardThreeSelf";
		}else if("D|R2".equals(type)){
			return "wap/KL10/optionTwoDanTuoSelf";
		}else if("D|R3".equals(type)){
			return "wap/KL10/optionThreeDanTuoSelf";
		}else if("D|R4".equals(type)){
			return "wap/KL10/optionFourDanTuoSelf";
		}else if("D|R5".equals(type)){
			return "wap/KL10/optionFiveDanTuoSelf";
		}else if("D|Z2".equals(type)){
			return "wap/KL10/selectTwoLinkGroupDanTuoSelf";
		}else if("D|Z3".equals(type)){
			return "wap/KL10/groupSelectForwardThreeDanTuoSelf";
		}else if("D|Q2".equals(type)){
			return "wap/KL10/selectTwoLinkDirectDanTuoSelf";
		}else if("D|Q3".equals(type)){
			return "wap/KL10/directSelectForwardThreeDanTuoSelf";
		}else{
			return "";
		}
	}
	public String getOptionSingleAutoViewName(String type){
		if("S1".equals(type)){
			return "wap/KL10/selectOneNumberSingleAuto";
		}else if("H1".equals(type)){
			return "wap/KL10/selectOneRedSingleAuto";
		}else if("R2".equals(type)){
			return "wap/KL10/optionTwoSingleAuto";
		}else if("R3".equals(type)){
			return "wap/KL10/optionThreeSingleAuto";
		}else if("R4".equals(type)){
			return "wap/KL10/optionFourSingleAuto";
		}else if("R5".equals(type)){
			return "wap/KL10/optionFiveSingleAuto";
		}else if("Q2".equals(type)){
			return "wap/KL10/selectTwoLinkDirectSingleAuto";
		}else if("Q3".equals(type)){
			return "wap/KL10/directSelectForwardThreeSingleAuto";
		}else if("Z2".equals(type)){
			return "wap/KL10/selectTwoLinkGroupSingleAuto";
		}else if("Z3".equals(type)){
			return "wap/KL10/groupSelectForwardThreeSingleAuto";
		}else{
			return "";
		}
	}
	public String getOptionMultipleAutoViewName(String type){
		if("S1".equals(type)){
			return "wap/KL10/selectOneNumberMultipleAuto";
		}else if("H1".equals(type)){
			return "wap/KL10/selectOneRedMultipleAuto";
		}else if("R2".equals(type)){
			return "wap/KL10/optionTwoMultipleAuto";
		}else if("R3".equals(type)){
			return "wap/KL10/optionThreeMultipleAuto";
		}else if("R4".equals(type)){
			return "wap/KL10/optionFourMultipleAuto";
		}else if("R5".equals(type)){
			return "wap/KL10/optionFiveMultipleAuto";
		}else if("Z2".equals(type)){
			return "wap/KL10/selectTwoLinkGroupMultipleAuto";
		}else if("Z3".equals(type)){
			return "wap/KL10/groupSelectForwardThreeMultipleAuto";
		}else{
			return "";
		}
	}
}
