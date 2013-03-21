package com.buybal.lot.util.test;

import org.junit.Assert;
import org.junit.Test;

import com.ruyicai.wap.util.LotteryAlgorithmUtil;
/**
 *   注码 0102030406 ===》 01,02,03,04,06 发现每个彩种的注码都不一样 添加处理 
	 *            双色球	 F47104 win_base_code 091521232830 winspecialcode 03 
	 *            七乐彩 F47102  win_base_code 06131520252730 winspecialcode 23 
	 *            3d F47103 win_base_code 010009 winspecialcode 03 
	 *            大乐透 T01001 win_base_code 10 17 19 32 33+01 12 winspecialcode null
	 *            排列三 T01002 win_base_code 806 winspecialcode null
	 *            "T01009";// 七星彩 win_base_code 7314869 winspecialcode null
	 *            "T01011";// 排列五 win_base_code 80656 winspecialcode null
	 *            "T01007";// 时时彩 win_base_code 78510 winspecialcode null
	 *            "T01010";// 多乐彩 win_base_code 01 09 10 05 02 winspecialcode    null 
	 *            "T01003"; // 足彩胜负14 win_base_code 33000033103313
	 *            "T01004"; // 足彩胜负9 win_base_code	  33000033103313 winspecialcode null 
	 *            "T01006"; // 足彩6场半全场	 win_base_code 331000001033 winspecialcode null 
	 *            "T01005"; // 足彩 四场进球彩 win_base_code 20011312 winspecialcode null
 * @author Administrator
 *
 */

public class LotteryAlgorithmUtilTest {
	/**
	 * 双色球
	 */
        @Test
        public void getbetCodeByF47104(){
        String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("F47104","091521232830","03");
        System.out.println(zhuma);
        Assert.assertEquals("09,15,21,23,28,30+03", zhuma);
        }
        /**
         * qilecai
         */
        @Test
        public void getbetCodeByF47102(){
        	String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("F47102","06131520252730","03");
        	System.out.println(zhuma);
        	Assert.assertEquals("06,13,15,20,25,27,30", zhuma);
        }
        /**
         * 3D
         */
        @Test
        public void getbetCodeByF47103(){
        	String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("F47103","010009",null);
        	System.out.println(zhuma);
        	Assert.assertEquals("01,00,09", zhuma);
        }
        /**
         * 大乐透
         */
        @Test
        public void getbetCodeByT01001(){
        	String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("T01001","10 17 19 32 33+01 12",null);
        	System.out.println(zhuma);
        	Assert.assertEquals("10,17,19,32,33+01,12", zhuma);
        }
        /**
         * 排列三
         */
        @Test
        public void getbetCodeByT01002(){
        	String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("T01002","806",null);
        	System.out.println(zhuma);
        	Assert.assertEquals("8,0,6", zhuma);
        }
        /**
         * 七星彩
         */
        @Test
        public void getbetCodeByT01009(){
        	String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("T01009","7314869",null);
        	System.out.println(zhuma);
        	Assert.assertEquals("7,3,1,4,8,6,9", zhuma);
        }
        /**
         *排列五
         */
        @Test
        public void getbetCodeByT01011(){
        	String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("T01011","80656",null);
        	System.out.println(zhuma);
        	Assert.assertEquals("8,0,6,5,6", zhuma);
        }
        
        
        /**
         *时时彩
         */
        @Test
        public void getbetCodeByT01007(){
        	String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("T01007","80656",null);
        	System.out.println(zhuma);
        	Assert.assertEquals("8,0,6,5,6", zhuma);
        }
        
        /**
         *duolecai 11xuan5
         */
        @Test
        public void getbetCodeByT01010(){
        	String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("T01010","01 09 10 05 02",null);
        	System.out.println(zhuma);
        	Assert.assertEquals("01,09,10,05,02", zhuma);
        }
        /**
         *22选5
         */
        //生成注码
        @Test
        public void getbetCodeByT01013(){
        	String zhuma = 	new LotteryAlgorithmUtil().getbetCodeByString("T01013","01,09,10,05,02",null);
        	System.out.println(zhuma);
//        	Assert.assertEquals("01,09,10,05,02", zhuma);
        }
        //计算注数
        @Test
        public void getbetCountByT01013(){
        	//复式
        	int zhushu = new LotteryAlgorithmUtil().get22select5Number(7);
        	System.out.println("复式："+zhushu);
        	//胆拖
        	zhushu = new LotteryAlgorithmUtil().get22select5DantuoNumber(3, 3);
        	System.out.println("胆拖："+zhushu);
        }
        
}
