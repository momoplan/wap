package com.ruyicai.wap.controller.test;

import org.junit.Test;

import com.ruyicai.wap.controller.WinSelectAction;

public class WinSelectActionTest {
	@Test
	public void getWinInfoTest(){
		String lotWinInfo = "";
		//双色球
		String lotNo = "F47104";
		String info = "33068233400_33068233400_70506941200,1_2_1000000000;2_75_33750800;3_1106_300000;4_64091_20000;5_1306549_1000;6_9691094_500;7_0_0;8_0_0;9_0_0;10_0_0";
		lotWinInfo = WinSelectAction.getWinInfo(lotNo, info);
		System.out.println("双色球："+lotWinInfo);
		
		//福彩3D
		lotNo = "F47103";
		info = "190519600_190519600_357669718,1_693_100000;2_0_32000;3_1924_16000;4_0_0;5_0_0;6_0_0;7_0_0;8_0_0;9_0_0;10_0_0";
		lotWinInfo = WinSelectAction.getWinInfo(lotNo, info);
		System.out.println("福彩3D："+lotWinInfo);
		
		//七乐彩
		lotNo = "F47102";
		info = "1006935000_1006935000_244471200,1_0_0;2_14_2494600;3_292_239200;4_1105_20000;5_9250_5000;6_21007_1000;7_109593_500;8_0_0;9_0_0;10_0_0";
		lotWinInfo = WinSelectAction.getWinInfo(lotNo, info);
		System.out.println("七乐彩："+lotWinInfo);

		//排列三
		lotNo = "T01002";
		info = "2116134200_1021780000_646849000,1_8013_100000;2_6890_32000;3_0_16000";
		lotWinInfo = WinSelectAction.getWinInfo(lotNo, info);
		System.out.println("排列三："+lotWinInfo);
		
		//大乐透
		lotNo = "T01001";
		info = "8703128900_2455151900_29407441000,1_0_0;2_26_16483000;3_138_806700;4_127_300000;5_4607_60000;6_19362_10000;7_184981_1000;8_2058096_500;"
				+"11_0_0;12_6_9889800;13_22_484000;14_25_150000;15_1447_30000;16_5670_5000;17_54731_500;18_3373_6000";
		lotWinInfo = WinSelectAction.getWinInfo(lotNo, info);
		System.out.println("大乐透："+lotWinInfo);
		
		//排列五
		lotNo = "T01011";
		info = "856048200_860000000_616898300,1_86_10000000";
		lotWinInfo = WinSelectAction.getWinInfo(lotNo, info);
		System.out.println("排列五："+lotWinInfo);
		
		//七星彩
		lotNo = "T01009";
		info = "1660922600_2390167500_500000000,1_5_387797900;2_12_3358000;3_172_180000;4_2584_30000;5_39282_2000;6_447676_500";
		lotWinInfo = WinSelectAction.getWinInfo(lotNo, info);
		System.out.println("七星彩："+lotWinInfo);
				
		//二十二选五
		lotNo = "T01013";
		info = "184355400_90330100_0,1_43_1054200;2_3536_5000;3_54639_500";
		lotWinInfo = WinSelectAction.getWinInfo(lotNo, info);
		System.out.println("二十二选五："+lotWinInfo);
				
	}
	
}
