package com.buybal.lot.lottype;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ruyicai.wap.bean.AddNumberAmtBean;

public class AddNumberUtil {

		private static final Logger logger = Logger.getLogger(AddNumberUtil.class);

		   /**
	     *  计算特殊追号的赔赚
	     */
		
		private Map<String, Integer> map = new HashMap<String,Integer >();
	    {
			map.put("rx1" ,13);
			map.put("rx2" ,6);
			map.put("rx3" ,19);
			map.put("rx4" ,78);
			map.put("rx5" ,540);
			map.put("rx6" ,90);
			map.put("rx7" ,26);
			map.put("rx8" ,9);
			map.put("gx2" ,65);
			map.put("gx3" ,195);
			map.put("zx2" ,130);
			map.put("zx3" ,1170);
		}

	
		/**
		 * 特殊追号 投入 累计 和 赚的利润 计算方法
		 * @param beishuarr
		 * @param zhushu
		 * @param addnumber
		 * @param flag
		 * 投入 和 累计 还有赔赚的  计算公式给你， 
		 * 投入 = 注数 * 倍数 * 2 
                            累计 = 投入 + 上一期累计（当前期累计为0）
                            赔赚  =  玩法中奖金额  * 注数  * 倍数 - 累计
		 * @return  返回list数组
		 */
		public  List<AddNumberAmtBean>  getAddAmtByParam (List<String> beishuarr,String zhushu,String addnumber,String flag){
            int betAmt = 0;//touru
            int addAmt = 0;//leiji
            int profAmt = 0;//利润
            List<AddNumberAmtBean> list = new LinkedList<AddNumberAmtBean>();
//            if(Integer.parseInt(addnumber)>20){
//            	addnumber = "5";
//            }
            for(int i=0 ; i< Integer.valueOf(addnumber) ; i++){
               AddNumberAmtBean     amtBean = new AddNumberAmtBean(); 
               String  beishu =beishuarr.get(i);
               if("".equals(beishu)||beishu==null){
            	   beishu="1";
               }
               if("".equals(zhushu)||zhushu==null){
            	   zhushu="1";
               }
               betAmt = Integer.valueOf(beishu) * Integer.valueOf(zhushu) * 2;//touru
               addAmt = betAmt + addAmt; //leiji 
               profAmt =new AddNumberUtil().map.get(flag)* Integer.valueOf(zhushu)* Integer.valueOf(beishu) - addAmt;
               amtBean.setAddAmt(addAmt);
               amtBean.setBetAmt(betAmt);
               amtBean.setProfAmt(profAmt);
               amtBean.setBeishu(Integer.valueOf(beishu));
               list.add(amtBean);
               }
            return list;
		}
	
}

