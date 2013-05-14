package com.ruyicai.wap.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.Constants;
import com.ruyicai.wap.util.SelectAllUtil;

@Controller
@RequestMapping("/guangDongHappyTenMinutesIndex")
public class GuangDongHappyTenMinutesIndexController {
	/**
	 * 选一数投
	 * @return
	 */
	@RequestMapping("/selectOneNumberSelf.jspx")
	public ModelAndView selectOneNumberSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "S1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectOneNumberSelf");
		return modelAndView;
	}
	/**
	 * 选一红投
	 * @return
	 */
	@RequestMapping("/selectOneRedSelf.jspx")
	public ModelAndView selectOneRedSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "H1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectOneRedSelf");
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
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionTwoSelf");
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
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionThreeSelf");
		return modelAndView;
	}
	/**
	 * 任选四自选
	 * @return
	 */
	@RequestMapping("/optionFourSelf.jspx")
	public ModelAndView optionFourSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R4");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionFourSelf");
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
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionFiveSelf");
		return modelAndView;
	}
	/**
	 * 选二连直
	 * @return
	 */
	@RequestMapping("/selectTwoLinkDirectSelf.jspx")
	public ModelAndView selectTwoLinkDirectSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q2");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectTwoLinkDirectSelf");
		return modelAndView;
	}
	/**
	 * 选二连组
	 * @return
	 */
	@RequestMapping("/selectTwoLinkGroupSelf.jspx")
	public ModelAndView selectTwoLinkGroupSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z2");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectTwoLinkGroupSelf");
		return modelAndView;
	}
	/**
	 * 直选前三
	 * @return
	 */
	@RequestMapping("/directSelectForwardThreeSelf.jspx")
	public ModelAndView directSelectForwardThreeSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q3");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/directSelectForwardThreeSelf");
		return modelAndView;
	}
	/**
	 * 组选前三
	 * @return
	 */
	@RequestMapping("/groupSelectForwardThreeSelf.jspx")
	public ModelAndView groupSelectForwardThreeSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z3");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/groupSelectForwardThreeSelf");
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
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionTwoDanTuoSelf");
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
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionThreeDanTuoSelf");
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
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionFourDanTuoSelf");
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
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionFiveDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 选二连直胆拖
	 * @return
	 */
	@RequestMapping("/selectTwoLinkDirectDanTuoSelf.jspx")
	public ModelAndView selectTwoLinkDirectDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|Q2");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectTwoLinkDirectDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 选二连组胆拖
	 * @return
	 */
	@RequestMapping("/selectTwoLinkGroupDanTuoSelf.jspx")
	public ModelAndView selectTwoLinkGroupDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|Z2");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectTwoLinkGroupDanTuoSelf");
		return modelAndView;
	}
	/**
	 * 直选前三胆拖
	 * @return
	 */
	@RequestMapping("/directSelectForwardThreeDanTuoSelf.jspx")
	public ModelAndView directSelectForwardThreeDanTuoSelf(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "D|Q3");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/directSelectForwardThreeDanTuoSelf");
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
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/groupSelectForwardThreeDanTuoSelf");
		return modelAndView;
	}
	/***************************机选**************************************/
	/**
	 * 选一数投单式机选
	 * @return
	 */
	@RequestMapping("/selectOneNumberSingleAuto.jspx")
	public ModelAndView selectOneNumberSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "S1");
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectOneNumberSingleAuto");
		return modelAndView;
	}
	/**
	 * 选一红投单式机选
	 * @return
	 */
	@RequestMapping("/selectOneRedSingleAuto.jspx")
	public ModelAndView selectOneRedSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "H1");
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectOneRedSingleAuto");
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
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionTwoSingleAuto");
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
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionThreeSingleAuto");
		return modelAndView;
	}
	/**任选四单式机选
	 * @return
	 */
	@RequestMapping("/optionFourSingleAuto.jspx")
	public ModelAndView optionFourSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R4");
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionFourSingleAuto");
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
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionFiveSingleAuto");
		return modelAndView;
	}
	/**
	 * 选二连直单式机选
	 * @return
	 */
	@RequestMapping("/selectTwoLinkDirectSingleAuto.jspx")
	public ModelAndView selectTwoLinkDirectSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q2");
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectTwoLinkDirectSingleAuto");
		return modelAndView;
	}
	/**
	 * 选二连组单式机选
	 * @return
	 */
	@RequestMapping("/selectTwoLinkGroupSingleAuto.jspx")
	public ModelAndView selectTwoLinkGroupSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z2");
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectTwoLinkGroupSingleAuto");
		return modelAndView;
	}
	/**
	 * 直选前三单式机选
	 * @return
	 */
	@RequestMapping("/directSelectForwardThreeSingleAuto.jspx")
	public ModelAndView directSelectForwardThreeSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q3");
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/directSelectForwardThreeSingleAuto");
		return modelAndView;
	}
	/**
	 * 组选前三单式机选
	 * @return
	 */
	@RequestMapping("/groupSelectForwardThreeSingleAuto.jspx")
	public ModelAndView groupSelectForwardThreeSingleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z3");
		modelAndView.addObject("zhuShu", "1");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/groupSelectForwardThreeSingleAuto");
		return modelAndView;
	}
	/**
	 * 选一数投复式机选
	 * @return
	 */
	@RequestMapping("/selectOneNumberMultipleAuto.jspx")
	public ModelAndView selectOneNumberMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "S1");
		modelAndView.addObject("geShu", "2");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectOneNumberMultipleAuto");
		return modelAndView;
	}
	/**
	 * 选一红投复式机选
	 * @return
	 */
	@RequestMapping("/selectOneRedMultipleAuto.jspx")
	public ModelAndView selectOneRedMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "H1");
		modelAndView.addObject("geShu", "2");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectOneRedMultipleAuto");
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
		modelAndView.addObject("geShu", "3");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionTwoMultipleAuto");
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
		modelAndView.addObject("geShu", "4");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionThreeMultipleAuto");
		return modelAndView;
	}
	/**任选四复式机选
	 * @return
	 */
	@RequestMapping("/optionFourMultipleAuto.jspx")
	public ModelAndView optionFourMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "R4");
		modelAndView.addObject("geShu", "5");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionFourMultipleAuto");
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
		modelAndView.addObject("geShu", "6");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/optionFiveMultipleAuto");
		return modelAndView;
	}
	/**
	 * 选二连直复式机选
	 * @return
	 */
	@RequestMapping("/selectTwoLinkDirectMultipleAuto.jspx")
	public ModelAndView selectTwoLinkDirectMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q2");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectTwoLinkDirectMultipleAuto");
		return modelAndView;
	}
	/**
	 * 选二连组复式机选
	 * @return
	 */
	@RequestMapping("/selectTwoLinkGroupMultipleAuto.jspx")
	public ModelAndView selectTwoLinkGroupMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z2");
		modelAndView.addObject("geShu", "3");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/selectTwoLinkGroupMultipleAuto");
		return modelAndView;
	}
	/**
	 * 直选前三复式机选
	 * @return
	 */
	@RequestMapping("/directSelectForwardThreeMultipleAuto.jspx")
	public ModelAndView directSelectForwardThreeMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Q3");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/directSelectForwardThreeMultipleAuto");
		return modelAndView;
	}
	/**
	 * 组选前三复式机选
	 * @return
	 */
	@RequestMapping("/groupSelectForwardThreeMultipleAuto.jspx")
	public ModelAndView groupSelectForwardThreeMultipleAuto(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("type", "Z3");
		modelAndView.addObject("geShu", "4");
		getModelAndView(modelAndView);
		modelAndView.setViewName("wap/KL10/groupSelectForwardThreeMultipleAuto");
		return modelAndView;
	}
	public ModelAndView getModelAndView(ModelAndView modelAndView){
		List<String> winCodeList = SelectAllUtil.selectWinCodeList(Constants.LOTNO_GDKLSF, "5");
		String headLine = SelectAllUtil.selectLotteryTermAndEndtime(Constants.LOTNO_GDKLSF);
		String batchCode = CommonUtil.getTerm(Constants.LOTNO_GDKLSF);
		modelAndView.addObject("batchCode", batchCode);
		modelAndView.addObject("beiShu", "1");
		modelAndView.addObject("addNumber", "1");
		modelAndView.addObject("headLine", headLine);
		modelAndView.addObject("lotNo", Constants.LOTNO_GDKLSF);
		modelAndView.addObject("winCodeList", winCodeList);
		return modelAndView;
	}
}
