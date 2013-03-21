package com.buybal.lot.lottype.test;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.buybal.lot.lottype.ElevenDuoJin;
public class ElevenDuoJinTest {
	 @Test
     public void autoBetCodetest(){
	 com.buybal.lot.lottype.ElevenDuoJin ed = new com.buybal.lot.lottype.ElevenDuoJin();
     String u = ed.autoBetCode(10, 1);
     System.out.println(u);
     }
	 
	 @Test
	 public void RandomCountsTest(){
		 com.buybal.lot.lottype.ElevenDuoJin ed = new com.buybal.lot.lottype.ElevenDuoJin();
		 for (int i = 0; i < 10; i++) {
			 Set<Integer>  set = ed.RandomCounts(1);
			 System.out.print(set);
 		}
	 }
	 @Test
	 public void tobetcodeTest() {
		 ElevenDuoJin duoJin = new ElevenDuoJin();
		 //任选一
		 Assert.assertEquals("101@*01^", duoJin.tobetcode("R1", "01").getString("toBetcode"));
		 Assert.assertEquals("101@*010405^", duoJin.tobetcode("R1", "010405").getString("toBetcode"));
		 //任选2-7 复试
		 Assert.assertEquals("102@*010204^", duoJin.tobetcode("R2", "010204").getString("toBetcode"));
		 Assert.assertEquals("103@*01020405^", duoJin.tobetcode("R3", "01020405").getString("toBetcode"));
		 Assert.assertEquals("104@*0102040506^", duoJin.tobetcode("R4", "0102040506").getString("toBetcode"));
		 Assert.assertEquals("105@*010204050607^", duoJin.tobetcode("R5", "010204050607").getString("toBetcode"));
		 Assert.assertEquals("106@*01020405060708^", duoJin.tobetcode("R6", "01020405060708").getString("toBetcode"));
		 Assert.assertEquals("107@*0102040506070809^", duoJin.tobetcode("R7", "0102040506070809").getString("toBetcode"));
		 
		 //任选2-8 单式
		 Assert.assertEquals("111@0102^", duoJin.tobetcode("R2", "0102").getString("toBetcode"));
		 Assert.assertEquals("112@010204^", duoJin.tobetcode("R3", "010204").getString("toBetcode"));
		 Assert.assertEquals("113@01020405^", duoJin.tobetcode("R4", "01020405").getString("toBetcode"));
		 Assert.assertEquals("114@0102040506^", duoJin.tobetcode("R5", "0102040506").getString("toBetcode"));
		 Assert.assertEquals("115@010204050607^", duoJin.tobetcode("R6", "010204050607").getString("toBetcode"));
		 Assert.assertEquals("116@01020405060708^", duoJin.tobetcode("R7", "01020405060708").getString("toBetcode"));
		 Assert.assertEquals("117@0102040506070809^", duoJin.tobetcode("R8", "0102040506070809").getString("toBetcode"));
		 
		 //任选胆拖2-7
		 Assert.assertEquals("121@0102*030405^", duoJin.tobetcode("DR2", "0102|030405").getString("toBetcode"));
		 Assert.assertEquals("122@0102*030405^", duoJin.tobetcode("DR3", "0102|030405").getString("toBetcode"));
		 Assert.assertEquals("123@0102*030405^", duoJin.tobetcode("DR4", "0102|030405").getString("toBetcode"));
		 Assert.assertEquals("124@0102*030405^", duoJin.tobetcode("DR5", "0102|030405").getString("toBetcode"));
		 Assert.assertEquals("125@0102*030405^", duoJin.tobetcode("DR6", "0102|030405").getString("toBetcode"));
		 Assert.assertEquals("126@0102*030405^", duoJin.tobetcode("DR7", "0102|030405").getString("toBetcode"));
		 //选前二组选 单式
		 Assert.assertEquals("131@0102^", duoJin.tobetcode("G2", "0102").getString("toBetcode"));
		 //选前二组选 复式
		 Assert.assertEquals("108@*01020405^", duoJin.tobetcode("G2", "01020405").getString("toBetcode"));
		 //选前3组选 单式
		 Assert.assertEquals("151@010204^", duoJin.tobetcode("G3", "010204").getString("toBetcode"));
		 //选前3组选 复式
		 Assert.assertEquals("109@*01020405^", duoJin.tobetcode("G3", "01020405").getString("toBetcode"));
		
		 //选前2直选 单式
		 Assert.assertEquals("141@0102^", duoJin.tobetcode("z2", "01|02").getString("toBetcode"));
		 //选前2直选 复式
		 Assert.assertEquals("144@*01020405^", duoJin.tobetcode("z2", "0102|0405").getString("toBetcode"));
		
		 //选前3直选 单式
		 System.out.println(duoJin.tobetcode("z3", "01|04|09").getString("toBetcode"));
		 Assert.assertEquals("161@010409^", duoJin.tobetcode("z3", "01|04|09").getString("toBetcode"));
		 //选前3直选 复式
		 Assert.assertEquals("164@*01020405^", duoJin.tobetcode("z3", "01|0204|05").getString("toBetcode"));
		 
		 
		 
	 }
}
