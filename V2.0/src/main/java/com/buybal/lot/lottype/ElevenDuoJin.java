package com.buybal.lot.lottype;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ruyicai.wap.util.CommonUtil;
import com.ruyicai.wap.util.LotteryAlgorithmUtil;
import com.ruyicai.wap.util.MessageUtil;

/**
 * 山东 十一运夺金（山东11选5）
 * (C)版权由北京金软瑞彩科技发展有限公司所有
 * @author zhaokailong
 *
 */
public class ElevenDuoJin {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ElevenDuoJin.class);
	//彩种编号  T01012
	/**
	 * 从1-11中随机选出不重复的数
	 * @param n
	 * @return
	 */
	 public static Set<Integer> RandomCounts(int n){
		   int [] in = new int[] {1,2,3,4,5,6,7,8,9,10,11};
	 		Random r=new Random();
	 		int strarray[]=new int[n];
	 		int index=0;
	 		for(int i=0;i<n;i++)
	 		{
	 			//刚开始从数组中随机抽取一个
	 			//而后将抽取的元素后面的元素向前推进到随机的位置[index位置]
	 			//随着循环的继续,逐渐抛弃后面的元素
	 			index=r.nextInt(in.length-i);
	 			strarray[i]=in[index];
	 			//元素向前推进到随机[index]的位置
	 			for(int j=index;j<in.length-i-1;j++){
	 				in[j]=in[j+1];
	 			}
	 		}
	 		Set<Integer> set=new HashSet<Integer>();
	 		for (int ins : strarray) {
	 			set.add(ins);
	 		}
	 		return set;
	 }
	/**
	 * 计算从n中选出k个数的组合数
	 * @param n 样本总数
	 * @param k 选取样本数
	 * @return 组合数
	 */
	public static long nchoosek(int n,int k) {
		//验证传入参数，n不能小于等于0，k不能小于0，n不能小于k；k=0的时候，规定组合数为1
		if(n <= 0 || k < 0 || n < k) {
			return -1;
		}
		if(k == 0 || n==k) {
			return 1;
		}
		//按照组合数性质，在k大于n/2时，计算从n中选出n-k的值，减少计算量
		if(k > n/2) {
			k = n - k;
		}
		//通过组合数公式求组合数
		long result = multiplyByStep(n,k)/multiplyByStep(k,k);
		return result;
	}
	/**
	 * A(m,n)  算法
	 * @param a
	 * @param b
	 * @return
	 */
	public static long multiplyByStep(int a,int b) {
		
		if(b<=0||b>a) {
			return -1;
		}
		int m = a;
		int n = a-b+1;
		//定义默认返回值
		long result = 1l;
		
		//m大于等于n，从n以1为步长乘到m;m小于n，从m以1为步长乘到n
		if(m >= n) {
			for(int i = n;i <= m;i++) {
				result = result * i;
			}
		}else {
			for(int i = m;i <= n;i++) {
				result = result * i;
			}
		}
		return result;
	}
	//**********************************************机选*************************************************
	/**
	 * 机选单式
	 * @param m 选择几个数    n代表几注
	 * @return
	 */
	public static String  autoBetCode(int n,int m){
		   String s = "";
		   if(m>11 || m<1){
			   return s;
		   }
		   for(int i=0; i<n; i++){
			   Set<Integer> set = RandomCounts(m);
			   for(int b:set) {
				   s = s + (b>=10?b:("0"+b));
			   }
			   s += ";";
		   }
		   //去掉注码最后一位的分号
		   int j = s.toCharArray().length;
		   s = s.substring(0,j-1);
		   return s;
	}
	
	 /**
	   *任选 复式机选 
	   * @param  n  注码个数   
	   * 生成注码格式：0102030405
	   */
	  public  static String RMultipleAutoNum(int n){
		  String s = "";
		  Set<Integer> set = RandomCounts(n);
		  for(int b:set) {
			  s +=(b>=10?b:("0"+b));
		  }
		  return s;
	  }
	//**********************************************注码校验*************************************************
		/**
		 * 判断注码长度是否符合标准
		 * @param zhuma 页面传入注码
		 * @param m 为最小长度 n 最大长度限制
		 * @return 判断结果，true为符合，false为不符合
		 */
		
		public static String checkLengthAll(Vector<String> v,String type) {
			String errortext = "pass";
			if(type.equals("R2")){
				if(v.size()<2)
					errortext = MessageUtil.BETCODE_LEANTH_R2;
			}else if(type.equals("R3")){
				if(v.size()<3)
					errortext = MessageUtil.BETCODE_LEANTH_R3;
			}else if(type.equals("R4")){
				if(v.size()<4)
					errortext = MessageUtil.BETCODE_LEANTH_R4;
			}else if(type.equals("R5")){
				if(v.size()<5)
					errortext = MessageUtil.BETCODE_LEANTH_R5;
			}else if(type.equals("R6")){
				if(v.size()<6)
					errortext = MessageUtil.BETCODE_LEANTH_R6;
			}else if(type.equals("R7")){
				if(v.size()<7)
					errortext = MessageUtil.BETCODE_LEANTH_R7;
			}else if(type.equals("R8")){
				if(v.size()<8)
					errortext = MessageUtil.BETCODE_LEANTH_R8;
			}else if(type.equals("G2")){
				if(v.size()<2)
					errortext = MessageUtil.BETCODE_LEANTH_R2;
			}else if(type.equals("G3")){
				if(v.size()<3)
					errortext = MessageUtil.BETCODE_LEANTH_R3;
			}else if(type.equals("z2fs")){
				if(v.size()<3)
					errortext = MessageUtil.BETCODE_LEANTH_R3;
			}else if(type.equals("z3fs")){
				if(v.size()<4)
					errortext = MessageUtil.BETCODE_LEANTH_R4;
			}
			
			return errortext;
		}		
	  /**
	 * R1-R8 验证 注码  倍数  追号是否正确
	 */

	public String checkBetCode(String betcode ,String multiple ,String addnumber ,String type){
		  String errorcode = "pass";
		  if(betcode == null || "".equals(betcode)){
			 errorcode = MessageUtil.BETCODE_IS_NULL;
		  } else if(multiple == null || "".equals(multiple)){
			 errorcode = MessageUtil.MULTIPLE_IS_NULL;
		  }else if(addnumber == null || "".equals(addnumber)){
			  errorcode = MessageUtil.ADDNUMBER_IS_NULL;
		  }else{
			    Vector<String> v = new Vector<String>();
				v = LotteryAlgorithmUtil.getStringArrayFromString(betcode);
				//验证注码格式
			  if(!CommonUtil.check(betcode)){
				  errorcode = MessageUtil.BETCODE_IS_ERROR;
			  }else if(!checkLengthAll(v, type).equals("pass")){
				  errorcode =  checkLengthAll(v, type)  ;
			  }else if (!CommonUtil.verifyRepeat(v)) { //注码是否重复
				  errorcode =  MessageUtil.BETCODE_IS_RE;
			  }
			  //验证倍数
			  if(!CommonUtil.checkBeishu(multiple)){
				  errorcode = MessageUtil.MULTIPLE_IS_ERROR;
			  }
			  //验证追号
			  if(!CommonUtil.checkAddNumber(addnumber)){
				  errorcode = MessageUtil.ADDNUMBER_IS_ERROR;
			  }
			  if("0".equals(addnumber)){
				  errorcode = MessageUtil.ADDNUMBER_ISZERO_ERROR; 
			  }
		  }
		  
		  return errorcode;
	  }
	  /**
	   * 胆拖R2-R8 验证 注码  倍数  追号是否正确
	   */
	 
	  public String checkBetCodeDR(String danma,String tuoma ,String multiple ,String addnumber ,String type){
		  String errorcode = "pass";
		//验证胆码  拖码 倍数  追号 是否为空
		  if(danma == null || "".equals(danma)){
			  errorcode = MessageUtil.BETCODE_DANTUO_DANMA_NULL;
		  } else if(tuoma == null || "".equals(tuoma)){
			  errorcode = MessageUtil.BETCODE_DANTUO_TUOMA_NULL;
		  } else if(multiple == null || "".equals(multiple)){
			  errorcode = MessageUtil.MULTIPLE_IS_NULL;
		  }else if(addnumber == null || "".equals(addnumber)){
			  errorcode = MessageUtil.ADDNUMBER_IS_NULL;
		  }else{
			  String betcodes = danma+"|"+tuoma;
			  Vector<String> v = new Vector<String>();
			  Vector<String> v1 = new Vector<String>();
			  v = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
			  v1 = LotteryAlgorithmUtil.getStringArrayFromString(danma+tuoma);
			  //验证胆码格式
			  if(!CommonUtil.check(danma)){
				  errorcode = MessageUtil.BETCODE_IS_ERROR;
			  }else
			  //验证拖码格式
			  if(!CommonUtil.check(tuoma)){
				  errorcode = MessageUtil.BETCODE_IS_ERROR;
			  }else
			  //注码长度
			  if(!checkLengthDuotuo(betcodes, type).equals("pass")){
				  errorcode =  checkLengthDuotuo(betcodes, type)  ;
			  }else
			  
			  //注码是否重复
			  if (!CommonUtil.verifyRepeat(v)) {
				  errorcode =  MessageUtil.BETCODE_RE_TUOMA;
			  }else
			  if (!CommonUtil.verifyRepeat(v1)) {
				  errorcode =  MessageUtil.BETCODE_RE_DANTUO;
			  }
			  //验证倍数
			  if(!CommonUtil.checkBeishu(multiple)){
				  errorcode = MessageUtil.MULTIPLE_IS_ERROR;
			  }
			  //验证追号
			  if(!CommonUtil.checkAddNumber(addnumber)){
				  errorcode = MessageUtil.ADDNUMBER_IS_ERROR;
			  }
		  }
		  return errorcode;
	  }
	  /**
	   * 胆拖注码长度验证
	   * @return
	   */
	
	public String checkLengthDuotuo(String betcode,String type){
		 String errorCode = "pass";
		 String [] betcodes = betcode.split("\\|");//betcodes[0]=胆码  betcodes[1]=拖码
		 Vector<String> v = new Vector<String>();
		 v = LotteryAlgorithmUtil.getStringArrayFromString(betcodes[0]);//danma 
		 Vector<String> tuomaVe = new Vector<String>();
		 Vector<String> VeAll = new Vector<String>();
		 VeAll = LotteryAlgorithmUtil.getStringArrayFromString(betcodes[0]+betcodes[1]);
		 tuomaVe = LotteryAlgorithmUtil.getStringArrayFromString(betcodes[1]);
		 if("DR2".equals(type) || "DZX2".equals(type)){
			  if(v.size()>1){
				  errorCode = MessageUtil.BETCODE_LEANTH_DANMA;
				  return errorCode;
			  }
			  if( tuomaVe.size()<2){
				  errorCode = MessageUtil.BETCODE_LEANTH_TUOMA;
				  return errorCode;
			  }
		 }
		 if("DR3".equals(type) || "DZX3".equals(type)){
			 if(v.size()>2){
				  errorCode = MessageUtil.BETCODE_LEANTH_DR3_DANMA;
				  return errorCode;
			 }
			 if(VeAll.size() < 4){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR3_All;
				 return errorCode;
			 }
		 }
		 if("DR4".equals(type)){
			 if(v.size()>3){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR4_DANMA;
				 return errorCode;
			 }
			 if(VeAll.size() < 5){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR4_All;
				 return errorCode;
			 }
		 }
		 if("DR5".equals(type)){
			 if(v.size()>4){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR5_DANMA;
				 return errorCode;
			 }
			 if(VeAll.size() < 6){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR5_All;
				 return errorCode;
			 }
		 }
		 if("DR6".equals(type)){
			 if(v.size()>5){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR6_DANMA;
				 return errorCode;
			 }
			 if(VeAll.size() < 7){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR6_All;
				 return errorCode;
			 }
		 }
		 if("DR7".equals(type)){
			 if(v.size()>6){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR7_DANMA;
				 return errorCode;
			 }
			 if(VeAll.size() < 8){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR7_All;
				 return errorCode;
			 }
		 }
		 if("DR8".equals(type)){
			 if(v.size()>7){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR8_DANMA;
				 return errorCode;
			 }
			 if(VeAll.size() < 9){
				 errorCode = MessageUtil.BETCODE_LEANTH_DR8_All;
				 return errorCode;
			 }
		 }
		 
		 return errorCode;
	 }
	  /**
	   * 
	   * @param first
	   * @param second
	   * @param multiple
	   * @param addnumber
	   * @param type
	   * @return
	   */
	public String checkBetCodeZX(String first,String second ,String multiple ,String addnumber ,String type){
		  String errorcode = "pass";
		  if(first == null || "".equals(first)){
			  errorcode = MessageUtil.BETCODE_FIRST_IS_NULL;
		  } else if(second == null || "".equals(second)){
			  errorcode = MessageUtil.BETCODE_SECOND_IS_NULL;
		  } else if(multiple == null || "".equals(multiple)){
			  errorcode = MessageUtil.MULTIPLE_IS_NULL;
		  }else if(addnumber == null || "".equals(addnumber)){
			  errorcode = MessageUtil.ADDNUMBER_IS_NULL;
		  }else{
			  Vector<String> v = new Vector<String>();
			  Vector<String> v1 = new Vector<String>();
			  Vector<String> v2 = new Vector<String>();
			  v = LotteryAlgorithmUtil.getStringArrayFromString(first);
			  v1 = LotteryAlgorithmUtil.getStringArrayFromString(second);
			  v2 = LotteryAlgorithmUtil.getStringArrayFromString(first+second);
			  //验证注码格式
			  if(!CommonUtil.check(first)){
				  errorcode = MessageUtil.BETCODE_FIRST_ERROR;
				  return errorcode;
			  }  
			  if(!CommonUtil.check(second)){
				  errorcode = MessageUtil.BETCODE_SECOND_ERROR;
				  return errorcode;
			  }
			  if("z2".equals(type)){
				  
				  if(v.size()>1){
					  errorcode = MessageUtil.BETCODE_LEANTH_First;
					  return errorcode;
				  }
				  if(v1.size()>1){
					  errorcode = MessageUtil.BETCODE_LEANTH_SECOND;
					  return errorcode;
				  }
			  }
			  if("z2dwfs".equals(type)){
				  if(v2.size()<3){
					  errorcode = MessageUtil.BETCODE_LEANTH_SUM;
					  return errorcode;
				  }
			  }
			  //ALL注码是否重复
			  if (!CommonUtil.verifyRepeat(v2)) {
				  errorcode =  MessageUtil.BETCODE_IS_ALL;
				  return errorcode;
			  }
			  //验证倍数
			  if(!CommonUtil.checkBeishu(multiple)){
				  errorcode = MessageUtil.MULTIPLE_IS_ERROR;
			  }
			  //验证追号
			  if(!CommonUtil.checkAddNumber(addnumber)){
				  errorcode = MessageUtil.ADDNUMBER_IS_ERROR;
			  }
		  }
		  
		  return errorcode;
	  }
	  /**
	   * 
	   * @param first
	   * @param second
	   * @param multiple
	   * @param addnumber
	   * @param type
	   * @return
	   */
	 
	  public String checkBetCodeZX3(String first,String second ,String  third,String multiple ,String addnumber ,String type){
		  String errorcode = "pass";
		  if(first == null || "".equals(first)){
			  errorcode = MessageUtil.BETCODE_FIRST_IS_NULL;
		  } else if(second == null || "".equals(second)){
			  errorcode = MessageUtil.BETCODE_SECOND_IS_NULL;
		  } else if(third == null || "".equals(third)){
			  errorcode = MessageUtil.BETCODE_THIRD_IS_NULL;
		  } else if(multiple == null || "".equals(multiple)){
			  errorcode = MessageUtil.MULTIPLE_IS_NULL;
		  }else if(addnumber == null || "".equals(addnumber)){
			  errorcode = MessageUtil.ADDNUMBER_IS_NULL;
		  }else{
			  Vector<String> v = new Vector<String>();
			  Vector<String> v1 = new Vector<String>();
			  Vector<String> v2 = new Vector<String>();
			  Vector<String> v3 = new Vector<String>();
			  v = LotteryAlgorithmUtil.getStringArrayFromString(first);
			  v1 = LotteryAlgorithmUtil.getStringArrayFromString(second);
			  v2 = LotteryAlgorithmUtil.getStringArrayFromString(first+second+third);
			  v3 = LotteryAlgorithmUtil.getStringArrayFromString(third);
			  //验证注码格式
			  if(!CommonUtil.check(first)){
				  errorcode = MessageUtil.BETCODE_FIRST_ERROR;
			  }
			  if(!CommonUtil.check(second)){
				  errorcode = MessageUtil.BETCODE_SECOND_ERROR;
			  }
			  if(!CommonUtil.check(third)){
				  errorcode = MessageUtil.BETCODE_THIRD_ERROR;
			  }
			  if("z3".equals(type)){
				  if(v.size()>1){
					  errorcode = MessageUtil.BETCODE_LEANTH_First;
					  return errorcode;
				  }
				  if(v1.size()>1){
					  errorcode = MessageUtil.BETCODE_LEANTH_SECOND;
					  return errorcode;
				  }
				  if(v3.size()>1){
					  errorcode = MessageUtil.BETCODE_LEANTH_THIRD;
					  return errorcode;
				  }
			  }
			  
			  if("z3dwfs".equals(type)){
				  if(v2.size()<4){
					  errorcode = MessageUtil.BETCODE_LEANTH_SUMZ3;
					  return errorcode;
				  }
			  }
			  //注码是否重复
			  if (!CommonUtil.verifyRepeat(v)) {
				  errorcode =  MessageUtil.BETCODE_IS_FIRST;
				  return errorcode;
			  }
			  //注码是否重复
			  if (!CommonUtil.verifyRepeat(v1)) {
				  errorcode =  MessageUtil.BETCODE_IS_SECOND;
				  return errorcode;
			  }
			  //注码是否重复
			  if (!CommonUtil.verifyRepeat(v3)) {
				  errorcode =  MessageUtil.BETCODE_IS_THIRD;
				  return errorcode;
			  }
			 
			  //ALL注码是否重复
			  if (!CommonUtil.verifyRepeat(v2)) {
				  errorcode =  MessageUtil.BETCODE_IS_ZX3_ALL;
				  return errorcode;
			  }
			  //验证倍数
			  if(!CommonUtil.checkBeishu(multiple)){
				  errorcode = MessageUtil.MULTIPLE_IS_ERROR;
			  }
			  //验证追号
			  if(!CommonUtil.checkAddNumber(addnumber)){
				  errorcode = MessageUtil.ADDNUMBER_IS_ERROR;
			  }
		  }
		  
		  return errorcode;
	  }
	  /**
		 * R1复试机选注数计算
		 * @param zhuma 
		 */
		public static long zhushuR1Auto(String zhuma ){
	    		Vector<String> v = new Vector<String>();
				v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
				int c = v.size();
			return nchoosek(c, 1);
		 }
	  
	     /**
	      * R2复试机选注数计算
	      * @param zhuma 
	      */
	     public static long zhushuR2Auto(String zhuma ){
	    	 Vector<String> v = new Vector<String>();
	    	 v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
	    	 int c = v.size();
	    	 return nchoosek(c, 2);
	     }
	     
	     /**
	      * R3复试机选注数计算
	      * @param zhuma 
	      */
	     public static long zhushuR3Auto(String zhuma ){
	    	 Vector<String> v = new Vector<String>();
	    	 v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
	    	 int c = v.size();
	    	 return nchoosek(c, 3);
	     }
	     
	     /**
	      * R4复试机选注数计算
	      * @param zhuma 
	      */
	     public static long zhushuR4Auto(String zhuma ){
	    	 Vector<String> v = new Vector<String>();
	    	 v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
	    	 int c = v.size();
	    	 return nchoosek(c, 4);
	     }
	     
	     /**
	      * R5复试机选注数计算
	      * @param zhuma 
	      */
	     public static long zhushuR5Auto(String zhuma){
	    	 Vector<String> v = new Vector<String>();
	    	 v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
	    	 int c = v.size();
	    	 return nchoosek(c, 5);
	     }
	     
	     /**
	      * R复试机选注数计算
	      * @param zhuma 
	      */
	     public static long zhushuR6Auto(String zhuma ){
	    	 Vector<String> v = new Vector<String>();
	    	 v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
	    	 int c = v.size();
	    	 return nchoosek(c, 6);
	     }
	     
	     /**
	      * R7复试机选注数计算
	      * @param zhuma 
	      */
	     public static long zhushuR7Auto(String zhuma ){
	    	 Vector<String> v = new Vector<String>();
	    	 v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
	    	 int c = v.size();
	    	 return nchoosek(c, 7);
	     }
	     
	     /**
	      * R8复试机选注数计算
	      * @param zhuma 
	      */
	    
	     public static long zhushuR8Auto(String zhuma ){
	    	 Vector<String> v = new Vector<String>();
	    	 v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
	    	 int c = v.size();
	    	 return nchoosek(c, 8);
	     }
	     
	     
	     /**
	 	 * R1自选计算注数
	 	 * 参数 注码 格式： 010304060611
	 	 */
	 	
	 	
	 	public static long zhushuSingle(String zhuma) {
	 		Vector<String> ve = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
	 		int conter = ve.size();
	 		return conter;
	 	}
		/**
		 * 任选公用(不适合R1)   前二组选
		 * 计算注数 
		 * m 任1 m=1  任二m=2·······  以此类推
		 * 注:注数计算的调用应该放到在对用户输入的信息校验之后
		 */
		
		
	     public static long  zhushuR(String zhuma,int m){
			Vector<String> v = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
			int count = v.size();
			long l = nchoosek(count, m);
			return l;
		 }
	 	 /**
		   * 把注码加上","
		   * s 格式0103051011
		   */
		
	    public static  String getViewZhuma(String zhuma){
			Vector<String> ve = new Vector<String>();
			ve = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
			return  LotteryAlgorithmUtil.joinStringArrayWithComma(ve);
		}
		/**
		 * 对胆拖显示注码的处理
		 * @param danma
		 * @param tuoma
		 * @return
		 */
		public static String getViwecode(String danma,String tuoma){
			danma = getViewZhuma(danma);
			tuoma = getViewZhuma(tuoma);
			String code = "胆码:"+  danma+"<br/>拖码:"+  tuoma;
			return code;
		}
//+++++++++++++++++++=++++++++++展示注码格式+++++++++++++++++++++++++++++++++++
		
		/**
		    *机选单式 R1-R8注码显示
		    * @param zhuma 格式 R1|01;03;05;
		    */
		   public static String getRAutoZhuma(String zhuma){
			   String zhuma0 = "";
			   
			   String s[] = zhuma.split(";");
			   for(int i = 0;i<s.length;i++){
				   zhuma0 +=  getViewZhuma(s[i])+";";
			   }
			   int j = zhuma0.toCharArray().length;
			   zhuma0 = zhuma0.substring(0,j-1);
			   zhuma0 =  zhuma0.replaceAll(";", "<br/>");
			   return zhuma0;
		   }	 
//===============================注码投注格式=======================
	
		   
		   /**
		 * 任1 单复式 以及 任1-8复式   投注注码格式
		 * code : 010304
		 */
		public String betcodeAgain (String salesCode ,String code){
			String betcode = salesCode+"@"+"*"+code+"^";
			return betcode;
		}
		
		/**
		 * 单式(不包括 任1)
		 */
		
		public String betcodeSingle (String salesCode,String code){
			String betcode = salesCode+"@"+code+"^";
			return betcode;
		}
		/**
		 * 胆拖
		 * @param dancode
		 * @param tuocode
		 * @return
		 */
		public String betcodeTuodan(String dancode,String tuocode){
			String betcode = dancode+"*"+tuocode+"^";
			return betcode;
			
		}
	
		
		/**
		 * 根据注码和type判断单式或者复式的投注格式
		 * @param type
		 * @param code
		 * @return
		 * @throws JSONException
		 */
		public JSONObject tobetcode(String type ,String code) throws JSONException{
			JSONObject js = new JSONObject();
			ElevenDuoJin ej =  new ElevenDuoJin();
			String toBetcode="",viewBetcode="" ;
			long counts = 0;
			if(type.equals("R1")){
					toBetcode = ej.betcodeAgain("101",code);
				    viewBetcode = ElevenDuoJin.getViewZhuma(code);
				    counts =  zhushuSingle(code);
			}else{
				viewBetcode = ElevenDuoJin.getViewZhuma(code);
				if(type.equals("R2")){
					toBetcode = new ElevenDuoJin().betcodeSingle("111",code);
					if(code.length()>4){//判断复式
						toBetcode = new ElevenDuoJin().betcodeAgain("102",code);
				    }
					counts =  zhushuR(code,2);
				}else if(type.equals("G2")){
					toBetcode = new ElevenDuoJin().betcodeSingle("131",code);
					if(code.length()>4){//判断复式
						toBetcode = new ElevenDuoJin().betcodeAgain("108",code);
				    }
					counts =  zhushuR(code,2);
				}else if(type.equals("G3")){
					toBetcode = new ElevenDuoJin().betcodeSingle("151",code);
					if(code.length()>6){//判断复式
						toBetcode = new ElevenDuoJin().betcodeAgain("109",code);
				    }
					counts =  zhushuR(code,3);
				}else if(type.equals("R3")){
					counts =  zhushuR(code,3);
					toBetcode = new ElevenDuoJin().betcodeSingle("112",code);
					if(code.length()>6){//判断复式
						toBetcode = new ElevenDuoJin().betcodeAgain("103",code);
				    }
				}else if (type.equals("R4")) {
					toBetcode = new ElevenDuoJin().betcodeSingle("113",code);
					if(code.length()>8){//判断复式
						toBetcode = new ElevenDuoJin().betcodeAgain("104",code);
				    }
					counts =  zhushuR(code,4);
				}else if (type.equals("R5")) {
					toBetcode = new ElevenDuoJin().betcodeSingle("114",code);
					if(code.length()>10){//判断复式
						toBetcode = new ElevenDuoJin().betcodeAgain("105",code);
				    }
					counts =  zhushuR(code,5);
				}else if (type.equals("R6")) {
					toBetcode = new ElevenDuoJin().betcodeSingle("115",code);
					if(code.length()>12){//判断复式
						toBetcode = new ElevenDuoJin().betcodeAgain("106",code);
				    }
					counts =  zhushuR(code,6);
				}else if (type.equals("R7")) {
					toBetcode = new ElevenDuoJin().betcodeSingle("116",code);
					if(code.length()>14){//判断复式
						toBetcode = new ElevenDuoJin().betcodeAgain("107",code);
				    }
					counts =  zhushuR(code,7);
				}else if (type.equals("R8")) {
					//任选8  没有复式
					toBetcode = new ElevenDuoJin().betcodeSingle("117",code);
					counts =  zhushuR(code,8);
				}else if (type.equals("z2")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViewZhuma(i[0])+"|"+ElevenDuoJin.getViewZhuma(i[1]);
					toBetcode = new ElevenDuoJin().betcodeSingle("141",i[0]+i[1]);
					counts =  getQ2Zhushu(i[0],i[1]);//单式注数计算方法
				}else if (type.equals("z2dwfs")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViewZhuma(i[0])+"|"+ElevenDuoJin.getViewZhuma(i[1]);
					toBetcode = ElevenDuoJin.codeBetCodeDantuo("142",i[0],i[1]);
					counts =  getQ2Zhushu(i[0],i[1]);//单式注数计算方法
				}else if (type.equals("z2fs")) {
			
					int size=getCodeSize(code);
					toBetcode = new ElevenDuoJin().betcodeAgain("144",code);
					counts =  multiplyByStep(size, 2);
				}else if (type.equals("z3")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViewZhuma(i[0])+"|"+ElevenDuoJin.getViewZhuma(i[1])+"|"+ElevenDuoJin.getViewZhuma(i[2]);
					toBetcode = new ElevenDuoJin().betcodeSingle("161",i[0]+i[1]+i[2]);
					counts =  getZX3Zhushu(i[0],i[1],i[2]);
				}else if (type.equals("z3dwfs")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViewZhuma(i[0])+"|"+ElevenDuoJin.getViewZhuma(i[1])+"|"+ElevenDuoJin.getViewZhuma(i[2]);
					toBetcode = ElevenDuoJin.codeBetCodeDW("162",i[0],i[1],i[2]);
					counts =  getZX3Zhushu(i[0],i[1],i[2]);
				}else if (type.equals("z3fs")) {
					toBetcode = new ElevenDuoJin().betcodeAgain("164",code);
					int size=getCodeSize(code);
					counts =  multiplyByStep(size, 3);
				}else if (type.equals("DR2") ) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViwecode(i[0],i[1]);
					toBetcode = codeBetCodeDantuo("121",i[0],i[1]);
					counts =  dantuoCounts(i[0],i[1],type);
				}else if (type.equals("DZX2")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViwecode(i[0],i[1]);
					toBetcode = codeBetCodeDantuo("133",i[0],i[1]);
					counts =  dantuoCounts(i[0],i[1],type);
				}else if (type.equals("DR3")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViwecode(i[0],i[1]);
					toBetcode = codeBetCodeDantuo("122",i[0],i[1]);
					counts =  dantuoCounts(i[0],i[1],type);
				}else if ("DZX3".equals(type)) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViwecode(i[0],i[1]);
					toBetcode = codeBetCodeDantuo("153",i[0],i[1]);
					counts =  dantuoCounts(i[0],i[1],type);
				}else if (type.equals("DR4")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViwecode(i[0],i[1]);
					toBetcode = codeBetCodeDantuo("123",i[0],i[1]);
					counts =  dantuoCounts(i[0],i[1],type);
				}else if (type.equals("DR5")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViwecode(i[0],i[1]);
					toBetcode = codeBetCodeDantuo("124",i[0],i[1]);
					counts =  dantuoCounts(i[0],i[1],type);
				}else if (type.equals("DR6")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViwecode(i[0],i[1]);
					toBetcode = codeBetCodeDantuo("125",i[0],i[1]);
					counts =  dantuoCounts(i[0],i[1],type);
				}else if (type.equals("DR7")) {
					String [] i = code.split("\\|");
					viewBetcode = ElevenDuoJin.getViwecode(i[0],i[1]);
					toBetcode = codeBetCodeDantuo("126",i[0],i[1]);
					counts =  dantuoCounts(i[0],i[1],type);
				}
			}
			  js.put("toBetcode", toBetcode);
			  js.put("viewBetcode", viewBetcode);
			  js.put("zhushu", counts);
			return js;
			
		}
		
		public  static int  getCodeSize(String zhuma){
			Vector<String> ve = new Vector<String>();
			ve = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
			return ve.size();
		}
		/**
		 * 胆拖注数计算
		 */
		public static int dantuoCounts(String danma ,String tuoma ,String type){
			Vector<String> dnamaVe = new Vector<String>();
			dnamaVe = LotteryAlgorithmUtil.getStringArrayFromString(danma);
			Vector<String> tuomaVe = new Vector<String>();
			tuomaVe = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
			if(type.equals("DR3") || type.equals("DZX3")){
				if(dnamaVe.size()==1){
					return (int) nchoosek(tuomaVe.size(), 2);
				}else {
					return (int) nchoosek(tuomaVe.size(), 1);
				}
			}
			if(type.equals("DR4")){
				if(dnamaVe.size()==1){
					return (int) nchoosek(tuomaVe.size(), 3);
				}else if(dnamaVe.size()==2){
					return (int) nchoosek(tuomaVe.size(), 2);
				}else {
					return (int) nchoosek(tuomaVe.size(), 1);
				}
			}
			if(type.equals("DR5")){
				if(dnamaVe.size()==1){
					return (int) nchoosek(tuomaVe.size(), 4);
				}else if(dnamaVe.size()==2) {
					return (int) nchoosek(tuomaVe.size(), 3);
				}else if(dnamaVe.size()==3) {
					return (int) nchoosek(tuomaVe.size(), 2);
				}else {
					return (int) nchoosek(tuomaVe.size(), 1);
				}
			}
			if(type.equals("DR6")){
				if(dnamaVe.size()==1){
					return (int) nchoosek(tuomaVe.size(), 5);
				}else if(dnamaVe.size()==2) {
					return (int) nchoosek(tuomaVe.size(), 4);
				}else if(dnamaVe.size()==3) {
					return (int) nchoosek(tuomaVe.size(), 3);
				}else if(dnamaVe.size()==4) {
					return (int) nchoosek(tuomaVe.size(), 2);
				}else {
					return (int) nchoosek(tuomaVe.size(), 1);
				}
			}
			if(type.equals("DR7")){
				if(dnamaVe.size()==1){
					return (int) nchoosek(tuomaVe.size(), 6);
				}else if(dnamaVe.size()==2) {
					return (int) nchoosek(tuomaVe.size(), 5);
				}else if(dnamaVe.size()==3) {
					return (int) nchoosek(tuomaVe.size(), 4);
				}else if(dnamaVe.size()==4) {
					return (int) nchoosek(tuomaVe.size(), 3);
				}else if(dnamaVe.size()==5) {
					return (int) nchoosek(tuomaVe.size(), 2);
				}else {
					return (int) nchoosek(tuomaVe.size(), 1);
				}
			}
			return tuomaVe.size();
		}

		/**
		 * 胆拖注码返回投注格式
		 */
		
		private static String codeBetCodeDantuo(String salesCode,String danma,String tuoma){
		   StringBuffer buffer = new StringBuffer();
		   buffer.append(salesCode);
		   buffer.append("@");
		   buffer.append(new ElevenDuoJin().betcodeTuodan(danma,tuoma));
		   return buffer.toString();
		}
		
		
		private static String codeBetCodeDW(String salesCode,String zhuma1,String zhuma2,String zhuma3){
			StringBuffer buffer = new StringBuffer();
			buffer.append(salesCode);
			buffer.append("@");
			buffer.append(zhuma1);
			buffer.append("*");
			buffer.append(zhuma2);
			buffer.append("*");
			buffer.append(zhuma3);
			buffer.append("^");
			return buffer.toString();
		}
		/**
		 * 注数计算
		 * zhuma 为 030506,0408 样式
		 */
	public 	static long getQ2Zhushu(String zhuma1,String zhuma2){
			Vector<String> v = new Vector<String>();
			Vector<String> v1 = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
			v1 = LotteryAlgorithmUtil.getStringArrayFromString(zhuma2);
			long num1 = v.size();
			long num2 = v1.size();
			return num1*num2;
		}
		/**
		 * 注数计算
		 * zhuma 为 030506,0408 样式
		 */
		
		public 	static long getZX3Zhushu(String zhuma1,String zhuma2,String zhuma3){
			Vector<String> v = new Vector<String>();
			Vector<String> v1 = new Vector<String>();
			Vector<String> v2 = new Vector<String>();
			v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
			v1 = LotteryAlgorithmUtil.getStringArrayFromString(zhuma2);
			v2 = LotteryAlgorithmUtil.getStringArrayFromString(zhuma3);
			long num1 = v.size();
			long num2 = v1.size();
			long num3 = v2.size();
			return num1*num2*num3;
		}
		
		/**
		 * 机选验证
		 */
		
			public 	String 	autoCodeCheck(String code,String type){
				String massage = "pass";
				//验证NULL
				if(code == null|"".equals(code)) return massage = MessageUtil.Auto_BETCODE_IS_NULL;
				//验证是否正整数
				Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
				Matcher matcher = pattern.matcher(code);
				if(!matcher.matches()) return massage = MessageUtil.Auto_ZHUSHU_IS_ERROR;
				return massage;
				
			}
			/**
			 * 验证单式机选注数
			 * @param zhushu
			 * @return
			 * @author 安朋朋
			 */
			public static String autoCheckZhushu(String zhushu,String beishu,String addnumber) {
				String massage = "pass";
				//验证NULL
				if(zhushu == null|"".equals(zhushu)) return massage = MessageUtil.Auto_BETCODE_IS_NULL;
				//验证是否正整数
				Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
				Matcher matcher = pattern.matcher(zhushu);
				if(!matcher.matches()) return massage = MessageUtil.Auto_ZHUSHU_IS_ERROR;
				if(beishu==null||"".equals(beishu)){
					return "倍数不能为空！";
				}
				if(addnumber==null||"".equals(addnumber)){
					return "追期不能为空！";
				}
				matcher = pattern.matcher(beishu);
				if(!matcher.matches()){
					return "倍数不正确！";
				}
				matcher = pattern.matcher(addnumber);
				if(!matcher.matches()){
					return "追期不正确！";
				}
				return massage;
			}
			/**
			 * 验证复式机选注码个数
			 * @param geshu
			 * @param type
			 * @return
			 * @author 安朋朋
			 */
			public static String autoCheckGeShu(String geshu,String type,String beishu,String addnumber) {
				String massage = "pass";
				//验证NULL
				if(geshu == null|"".equals(geshu)) return massage = MessageUtil.Auto_BETCODE_LEANTH_IS_NULL;
				//验证是否正整数
				Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{0,1}");
				Matcher matcher = pattern.matcher(geshu);
				if(!matcher.matches()) return massage = MessageUtil.Auto_BETCODE_LEANTH_IS_ERROR;
				
				if("autoR1".equals(type)){
					if(Integer.valueOf(geshu)<1||Integer.valueOf(geshu)>11) 
						return massage = MessageUtil.Auto_BETCODE_LEANTH_ERROR;
				}
				if("autoR2".equals(type)){
					if(Integer.valueOf(geshu)<2||Integer.valueOf(geshu)>11) 
						return massage = MessageUtil.Auto_BETCODE_LEANTH_ERROR;
				}
				if("autoR3".equals(type)){
					if(Integer.valueOf(geshu)<3||Integer.valueOf(geshu)>11) 
						return massage = MessageUtil.Auto_BETCODE_LEANTH_ERROR;
				}
				if("autoR4".equals(type)){
					if(Integer.valueOf(geshu)<4||Integer.valueOf(geshu)>11) 
						return massage = MessageUtil.Auto_BETCODE_LEANTH_ERROR;
				}
				if("autoR5".equals(type)){
					if(Integer.valueOf(geshu)<5||Integer.valueOf(geshu)>11) 
						return massage = MessageUtil.Auto_BETCODE_LEANTH_ERROR;
				}
				if("autoR6".equals(type)){
					if(Integer.valueOf(geshu)<6||Integer.valueOf(geshu)>11) 
						return massage = MessageUtil.Auto_BETCODE_LEANTH_ERROR;
				}
				if("autoR7".equals(type)){
					if(Integer.valueOf(geshu)<7||Integer.valueOf(geshu)>11) 
						return massage = MessageUtil.Auto_BETCODE_LEANTH_ERROR;
				}
				if("autoG2".equals(type)){
					if(Integer.valueOf(geshu)<2||Integer.valueOf(geshu)>11) 
						return massage = MessageUtil.Auto_BETCODE_LEANTH_ERROR;
				}
				if("autoG3".equals(type)){
					if(Integer.valueOf(geshu)<3||Integer.valueOf(geshu)>11) 
						return massage = MessageUtil.Auto_BETCODE_LEANTH_ERROR;
				}
				Pattern pattern1 = Pattern.compile("[1-9]{1}[0-9]{0,1}");
				if(beishu==null||"".equals(beishu)){
					return "倍数不能为空！";
				}
				if(addnumber==null||"".equals(addnumber)){
					return "追期不能为空！";
				}
				matcher = pattern1.matcher(beishu);
				if(!matcher.matches()){
					return "倍数不正确！";
				}
				matcher = pattern1.matcher(addnumber);
				if(!matcher.matches()){
					return "追期不正确！";
				}
				return massage;
			}
		
			/**
			 * 机选投注注码和页面显示注码
			 * @param type
			 * @param code
			 * @return
			 * @throws JSONException
			 * @author 安朋朋
			 */
			public static JSONObject toAutobetcode(String type ,String code,String autocode) throws JSONException{
				JSONObject js = new JSONObject();
				
				String toBetcode="",viewBetcode="" ;
				long counts = 0;
				if(type.equals("R1")){
					toBetcode = ElevenDuoJin.betAutocodeAgain("101",code);
					viewBetcode = ElevenDuoJin.getRAutoZhuma(code);
					if(code.indexOf(";")==-1&&code.length()>2){
						 counts =  ElevenDuoJin.zhushuR1Auto(code);
					}else{
						 counts =  Long.valueOf(autocode);
					}
				}else{
					viewBetcode = ElevenDuoJin.getRAutoZhuma(code);
					if(type.equals("R2")){
						if(code.indexOf(";")==-1&&code.length()>4){//判断复式
							toBetcode = ElevenDuoJin.betAutocodeAgain("102",code);
							counts = ElevenDuoJin.zhushuR2Auto(code);
					    }else{
					    	toBetcode = ElevenDuoJin.betAutocodeSingle("111",code);
					    	 counts =  Long.valueOf(autocode);
					    }
					}else if(type.equals("G2")){
						
						if(code.indexOf(";")==-1&&code.length()>4){//判断复式
							toBetcode =  ElevenDuoJin.betAutocodeAgain("108",code);
							counts = ElevenDuoJin.zhushuR2Auto(code);
					    }else{
					    	toBetcode = ElevenDuoJin.betAutocodeSingle("131",code);
					    	counts =  Long.valueOf(autocode);
					    }
					}else if(type.equals("G3")){
						
						if(code.indexOf(";")==-1&&code.length()>6){//判断复式
							toBetcode =ElevenDuoJin.betAutocodeAgain("109",code);
							counts = ElevenDuoJin.zhushuR3Auto(code);
					    }else{
					    	toBetcode = ElevenDuoJin.betAutocodeSingle("151",code);
					    	counts =  Long.valueOf(autocode);
					    }
					}else if(type.equals("R3")){
						
						if(code.indexOf(";")==-1&&code.length()>6){//判断复式
							toBetcode =  ElevenDuoJin.betAutocodeAgain("103",code);
							counts = ElevenDuoJin.zhushuR3Auto(code);
					    }else{
					    	toBetcode = ElevenDuoJin.betAutocodeSingle("112",code);
					    	counts =  Long.valueOf(autocode);
					    }
					}else if (type.equals("R4")) {
						
						if(code.indexOf(";")==-1&&code.length()>8){//判断复式
							toBetcode = ElevenDuoJin.betAutocodeAgain("104",code);
							counts = ElevenDuoJin.zhushuR4Auto(code);
					    }else{
					    	toBetcode = ElevenDuoJin.betAutocodeSingle("113",code);
					    	counts =  Long.valueOf(autocode);
					    }
					}else if (type.equals("R5")) {
						
						if(code.indexOf(";")==-1&&code.length()>10){//判断复式
							toBetcode = ElevenDuoJin.betAutocodeAgain("105",code);
							counts = ElevenDuoJin.zhushuR5Auto(code);
					    }else{
					    	toBetcode = ElevenDuoJin.betAutocodeSingle("114",code);
					    	counts =  Long.valueOf(autocode);
					    }
					}else if (type.equals("R6")) {
						
						if(code.indexOf(";")==-1&&code.length()>12){//判断复式
							toBetcode = ElevenDuoJin.betAutocodeAgain("106",code);
							counts = ElevenDuoJin.zhushuR6Auto(code);
					    }else{
					    	toBetcode =ElevenDuoJin.betAutocodeSingle("115",code);
					    	counts =  Long.valueOf(autocode);
					    }
					}else if (type.equals("R7")) {
						
						if(code.indexOf(";")==-1&&code.length()>14){//判断复式
							toBetcode = ElevenDuoJin.betAutocodeAgain("107",code);
							counts = ElevenDuoJin.zhushuR7Auto(code);
					    }else{
					    	toBetcode =ElevenDuoJin.betAutocodeSingle("116",code);
					    	counts =  Long.valueOf(autocode);
					    }
					}else if (type.equals("R8")) {
						//任选8  没有复式
						toBetcode = ElevenDuoJin.betAutocodeSingle("117",code);
						counts =  Long.valueOf(autocode);
					}
				}
				  js.put("toBetcode", toBetcode);
				  js.put("viewBetcode", viewBetcode);
				  js.put("zhushu", counts);
				return js;
				
			}
			/**
			 * 机选投注注码和页面显示注码(单式)
			 * @param type
			 * @param code
			 * @return
			 * @throws JSONException
			 * @author 安朋朋
			 */
			public static JSONObject toAutoDSbetcode(String type ,String code,String autocode) throws JSONException{
				JSONObject js = new JSONObject();
				String toBetcode="",viewBetcode="" ;
				long counts = 0;
				if(type.equals("R1")){
					toBetcode = ElevenDuoJin.betAutocodeAgain("101",code);
					viewBetcode = ElevenDuoJin.getRAutoZhuma(code);
					counts =  Long.valueOf(autocode);
				}else{
					viewBetcode = ElevenDuoJin.getRAutoZhuma(code);
					if(type.equals("R2")){
					    toBetcode = ElevenDuoJin.betAutocodeSingle("111",code);
					    counts =  Long.valueOf(autocode);
					}else if(type.equals("G2")){
					    toBetcode = ElevenDuoJin.betAutocodeSingle("131",code);
					    counts =  Long.valueOf(autocode);
					}else if(type.equals("G3")){
					    toBetcode =  ElevenDuoJin.betAutocodeSingle("151",code);
					    counts =  Long.valueOf(autocode);
					}else if(type.equals("R3")){
					    toBetcode = ElevenDuoJin.betAutocodeSingle("112",code);
					    counts =  Long.valueOf(autocode);
					}else if (type.equals("R4")) {
					    toBetcode = ElevenDuoJin.betAutocodeSingle("113",code);
					    counts =  Long.valueOf(autocode);
					}else if (type.equals("R5")) {
					    toBetcode = ElevenDuoJin.betAutocodeSingle("114",code);
					    counts =  Long.valueOf(autocode);
					}else if (type.equals("R6")) {
					    toBetcode =ElevenDuoJin.betAutocodeSingle("115",code);
					    counts =  Long.valueOf(autocode);
					}else if (type.equals("R7")) {
					    toBetcode =ElevenDuoJin.betAutocodeSingle("116",code);
					    counts =  Long.valueOf(autocode);
					}else if (type.equals("R8")) {
						toBetcode = ElevenDuoJin.betAutocodeSingle("117",code);
						counts =  Long.valueOf(autocode);
					}
				}
				  js.put("toBetcode", toBetcode);
				  js.put("viewBetcode", viewBetcode);
				  js.put("zhushu", counts);
				return js;
				
			}
	/**
	 * 复式机选投注注码
	 * @param salesCode
	 * @param code
	 * @return
	 * @author 安朋朋
	 */
	public static String betAutocodeAgain(String salesCode,String code){
		String betcode = "";
		if(code.indexOf(";")!=-1){
			String str[] = code.split("\\;");
			for(int i=0;i<str.length;i++){
				String newbetcode = salesCode+"@"+"*"+str[i]+"^";
				betcode+=newbetcode;
			}
		}else{
			betcode = salesCode+"@"+"*"+code+"^";
		}
		return betcode;
	}
	/**
	 * 单式机选投注注码
	 * @param salesCode
	 * @param code
	 * @return
	 * @author 安朋朋
	 */
	public static String betAutocodeSingle(String salesCode,String code){
		String betcode = "";
		if(code.indexOf(";")!=-1){
			String str[] = code.split("\\;");
			for(int i=0;i<str.length;i++){
				String newbetcode= "";
				newbetcode = salesCode+"@"+str[i]+"^";
				betcode+=newbetcode;
			}
		}else{
			betcode = salesCode+"@"+code+"^";
		}
		return betcode;
	}
   }
