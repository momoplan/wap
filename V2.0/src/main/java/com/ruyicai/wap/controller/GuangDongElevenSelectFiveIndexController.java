package com.ruyicai.wap.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.SelectAllUtil;

@Controller
@RequestMapping("/guangDongElevenSelectFiveIndex")
public class GuangDongElevenSelectFiveIndexController {
	private static final Logger logger = Logger.getLogger(GuangDongElevenSelectFiveIndexController.class);
	/**
	 * 任选一自选
	 * @return
	 */
	@RequestMapping("/optionOneSelf.jspx")
	public ModelAndView optionOneSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R1");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionOneSelf");
		return modelAndView;
	}
	/**
	 * 任选二自选
	 * @return
	 */
	@RequestMapping("/optionTwoSelf.jspx")
	public ModelAndView optionTwoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R2");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionTwoSelf");
		return modelAndView;
	}
	/**
	 * 任选三自选
	 * @return
	 */
	@RequestMapping("/optionThreeSelf.jspx")
	public ModelAndView optionThreeSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R3");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionThreeSelf");
		return modelAndView;
	}
	/**任选四自选
	 * @return
	 */
	@RequestMapping("/optionFourSelf.jspx")
	public ModelAndView optionFourSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R4");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionFourSelf");
		return modelAndView;
	}
	/**
	 * 任选五自选
	 * @return
	 */
	@RequestMapping("/optionFiveSelf.jspx")
	public ModelAndView optionFiveSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R5");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionFiveSelf");
		return modelAndView;
	}
	/**
	 * 任选六自选
	 * @return
	 */
	@RequestMapping("/optionSixSelf.jspx")
	public ModelAndView optionSixSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R6");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionSixSelf");
		return modelAndView;
	}
	/**
	 * 任选七自选
	 * @return
	 */
	@RequestMapping("/optionSevenSelf.jspx")
	public ModelAndView optionSevenSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R7");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionSevenSelf");
		return modelAndView;
	}
	/**
	 * 任选八自选
	 * @return
	 */
	@RequestMapping("/optionEightSelf.jspx")
	public ModelAndView optionEightSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R8");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionEightSelf");
		return modelAndView;
	}
	public ModelAndView getSelfModelAndView(ModelAndView modelAndView){
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		String headLine = SelectAllUtil.selectLotteryTermAndEndtime(Constants.LOTNO_GDSYXW);
		String batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("beiShu", "1");
		modelAndView.addObject("addNumber", "1");
		modelAndView.addObject("headLine", headLine);
		modelAndView.addObject("lotNo", Constants.LOTNO_GDSYXW);
		modelAndView.addObject("winCodeList", winCodeList);
		return modelAndView;
	}
	/**
	 * 任选一单式机选
	 * @return
	 */
	@RequestMapping("/optionOneSingleAuto.jspx")
	public ModelAndView optionOneSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R1");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionOneSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选二单式机选
	 * @return
	 */
	@RequestMapping("/optionTwoSingleAuto.jspx")
	public ModelAndView optionTwoSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R2");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionTwoSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选三单式机选
	 * @return
	 */
	@RequestMapping("/optionThreeSingleAuto.jspx")
	public ModelAndView optionThreeSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R3");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionThreeSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选四单式机选
	 * @return
	 */
	@RequestMapping("/optionFourSingleAuto.jspx")
	public ModelAndView optionFourSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R4");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionFourSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选五单式机选
	 * @return
	 */
	@RequestMapping("/optionFiveSingleAuto.jspx")
	public ModelAndView optionFiveSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R5");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionFiveSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选六单式机选
	 * @return
	 */
	@RequestMapping("/optionSixSingleAuto.jspx")
	public ModelAndView optionSixSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R6");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionSixSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选七单式机选
	 * @return
	 */
	@RequestMapping("/optionSevenSingleAuto.jspx")
	public ModelAndView optionSevenSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R7");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionSevenSingleAuto");
		return modelAndView;
	}
	/**
	 * 任选八单式机选
	 * @return
	 */
	@RequestMapping("/optionEightSingleAuto.jspx")
	public ModelAndView optionEightSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R8");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionEightSingleAuto");
		return modelAndView;
	}
	public ModelAndView getSingleAutoModelAndView(ModelAndView modelAndView){
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		String headLine = SelectAllUtil.selectLotteryTermAndEndtime(Constants.LOTNO_GDSYXW);
		String batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("beiShu", "1");
		modelAndView.addObject("addNumber", "1");
		modelAndView.addObject("zhuShu", "1");
		modelAndView.addObject("lotNo", Constants.LOTNO_GDSYXW);
		modelAndView.addObject("headLine", headLine);
		modelAndView.addObject("winCodeList", winCodeList);
		return modelAndView;
	}
	
	/**
	 * 任选一复式机选
	 * @return
	 */
	@RequestMapping("/optionOneMultipleAuto.jspx")
	public ModelAndView optionOneMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R1");
		modelAndView.addObject("geShu", "1");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionOneMultipleAuto");
		return modelAndView;
	}
	/**
	 * 任选二复式机选
	 * @return
	 */
	@RequestMapping("/optionTwoMultipleAuto.jspx")
	public ModelAndView optionTwoMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R2");
		modelAndView.addObject("geShu", "2");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionTwoMultipleAuto");
		return modelAndView;
	}
	/**
	 * 任选三复式机选
	 * @return
	 */
	@RequestMapping("/optionThreeMultipleAuto.jspx")
	public ModelAndView optionThreeMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R3");
		modelAndView.addObject("geShu", "3");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionThreeMultipleAuto");
		return modelAndView;
	}
	/**
	 * 任选四复式机选
	 * @return
	 */
	@RequestMapping("/optionFourMultipleAuto.jspx")
	public ModelAndView optionFourMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R4");
		modelAndView.addObject("geShu", "4");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionFourMultipleAuto");
		return modelAndView;
	}
	/**
	 * 任选五复式机选
	 * @return
	 */
	@RequestMapping("/optionFiveMultipleAuto.jspx")
	public ModelAndView optionFiveMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R5");
		modelAndView.addObject("geShu", "5");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionFiveMultipleAuto");
		return modelAndView;
	}
	/**
	 * 任选六复式机选
	 * @return
	 */
	@RequestMapping("/optionSixMultipleAuto.jspx")
	public ModelAndView optionSixMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R6");
		modelAndView.addObject("geShu", "6");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionSixMultipleAuto");
		return modelAndView;
	}
	/**
	 * 任选七复式机选
	 * @return
	 */
	@RequestMapping("/optionSevenMultipleAuto.jspx")
	public ModelAndView optionSevenMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R7");
		modelAndView.addObject("geShu", "7");
		getMultipleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionSevenMultipleAuto");
		return modelAndView;
	}

	public ModelAndView getMultipleAutoModelAndView(ModelAndView modelAndView){
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		String headLine = SelectAllUtil.selectLotteryTermAndEndtime(Constants.LOTNO_GDSYXW);
		String batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("beiShu", "1");
		modelAndView.addObject("addNumber", "1");
		modelAndView.addObject("lotNo", Constants.LOTNO_GDSYXW);
		modelAndView.addObject("headLine", headLine);
		modelAndView.addObject("winCodeList", winCodeList);
		return modelAndView;
	}
	
	/**
	 * 任选二胆拖自选
	 * @return
	 */
	@RequestMapping("/optionTwoDanTuoSelf.jspx")
	public ModelAndView optionTwoDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|R2");
		getDanTuoSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionTwoDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 任选三胆拖自选
	 * @return
	 */
	@RequestMapping("/optionThreeDanTuoSelf.jspx")
	public ModelAndView optionThreeDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|R3");
		getDanTuoSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionThreeDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 任选四胆拖自选
	 * @return
	 */
	@RequestMapping("/optionFourDanTuoSelf.jspx")
	public ModelAndView optionFourDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|R4");
		getDanTuoSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionFourDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 任选五胆拖自选
	 * @return
	 */
	@RequestMapping("/optionFiveDanTuoSelf.jspx")
	public ModelAndView optionFiveDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|R5");
		getDanTuoSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionFiveDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 任选六胆拖自选
	 * @return
	 */
	@RequestMapping("/optionSixDanTuoSelf.jspx")
	public ModelAndView optionSixDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|R6");
		getDanTuoSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionSixDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 任选七胆拖自选
	 * @return
	 */
	@RequestMapping("/optionSevenDanTuoSelf.jspx")
	public ModelAndView optionSevenDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|R7");
		getDanTuoSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/optionSevenDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 组选前二胆拖
	 * @return
	 */
	@RequestMapping("/groupSelectForwardTwoDanTuoSelf.jspx")
	public ModelAndView groupSelectForwardTwoDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|Z2");
		getDanTuoSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/groupSelectForwardTwoDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 组选前三胆拖
	 * @return
	 */
	@RequestMapping("/groupSelectForwardThreeDanTuoSelf.jspx")
	public ModelAndView groupSelectForwardThreeDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|Z3");
		getDanTuoSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/groupSelectForwardThreeDanTuoSelf");
		return modelAndView;
	}
	public ModelAndView getDanTuoSelfModelAndView(ModelAndView modelAndView){
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDSYXW, "5");
		String headLine = SelectAllUtil.selectLotteryTermAndEndtime(Constants.LOTNO_GDSYXW);
		String batchCode = CommonUtil.getTerm(Constants.LOTNO_GDSYXW);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("beiShu", "1");
		modelAndView.addObject("addNumber", "1");
		modelAndView.addObject("lotNo", Constants.LOTNO_GDSYXW);
		modelAndView.addObject("headLine", headLine);
		modelAndView.addObject("winCodeList", winCodeList);
		return modelAndView;
	}
	@RequestMapping("/groupSelectForwardTwoSelf.jspx")
	public ModelAndView groupSelectForwardTwoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z2");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/groupSelectForwardTwoSelf");
		return modelAndView;
	}
	@RequestMapping("/groupSelectForwardThreeSelf.jspx")
	public ModelAndView groupSelectForwardThreeSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z3");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/groupSelectForwardThreeSelf");
		return modelAndView;
	}
	@RequestMapping("/groupSelectForwardTwoSingleAuto.jspx")
	public ModelAndView groupSelectForwardTwoSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z2");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/groupSelectForwardTwoSingleAuto");
		return modelAndView;
	}
	@RequestMapping("/groupSelectForwardThreeSingleAuto.jspx")
	public ModelAndView groupSelectForwardThreeSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z3");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/groupSelectForwardThreeSingleAuto");
		return modelAndView;
	}
	@RequestMapping("/groupSelectForwardTwoMultipleAuto.jspx")
	public ModelAndView groupSelectForwardTwoMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z2");
		modelAndView.addObject("geShu", "5");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/groupSelectForwardTwoMultipleAuto");
		return modelAndView;
	}
	@RequestMapping("/groupSelectForwardThreeMultipleAuto.jspx")
	public ModelAndView groupSelectForwardThreeMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z3");
		modelAndView.addObject("geShu", "5");
		getSingleAutoModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/groupSelectForwardThreeMultipleAuto");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardTwoSingleSelf.jspx")
	public ModelAndView directSelectForwardTwoSingleSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q2");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/directSelectForwardTwoSingleSelf");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardThreeSingleSelf.jspx")
	public ModelAndView directSelectForwardThreeSingleSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q3");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/directSelectForwardThreeSingleSelf");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardTwoMultipleSelf.jspx")
	public ModelAndView directSelectForwardTwoMultipleSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q2");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/directSelectForwardTwoMultipleSelf");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardThreeMultipleSelf.jspx")
	public ModelAndView directSelectForwardThreeMultipleSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q3");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/directSelectForwardThreeMultipleSelf");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardTwoPositionMultipleSelf.jspx")
	public ModelAndView directSelectForwardTwoPositionMultipleSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "P|Q2");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/directSelectForwardTwoPositionMultipleSelf");
		return modelAndView;
	}
	@RequestMapping("/directSelectForwardThreePositionMultipleSelf.jspx")
	public ModelAndView directSelectForwardThreePositionMultipleSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "P|Q3");
		getSelfModelAndView(modelAndView);
		modelAndView.setViewName("wap/gd11x5/directSelectForwardThreePositionMultipleSelf");
		return modelAndView;
	}
	public ModelAndView optionOneSelfDetail(
			@RequestParam(value="zhuMa",defaultValue="") String zhuMa,
			@RequestParam(value="beiShu",defaultValue="") String beiShu,
			@RequestParam(value="addNumber",defaultValue="") String addNumber,
			@RequestParam(value="type",defaultValue="") String type){
		ModelAndView modelAndView = new ModelAndView();
		logger.info("GuangDongElevenSelectFiveController/optionOneSelfDetail参数:zhuMa:"+zhuMa+",beiShu:"+beiShu
				+",addNumber:"+addNumber+",type:"+type);
		return modelAndView;
	}
}
