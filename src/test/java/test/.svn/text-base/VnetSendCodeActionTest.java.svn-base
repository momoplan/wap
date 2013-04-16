package test;

import java.util.ResourceBundle;

import org.junit.Test;

import com.ruyicai.wap.util.Cryptogram;

public class VnetSendCodeActionTest {
	private static ResourceBundle rbint = ResourceBundle.getBundle("jrtWAPSite");

	@Test
	public void testname() throws Exception {
		Cryptogram cryptogram = new Cryptogram();
		String str = "";
		String Key =rbint.getString("vnetKey") ;
//		String vnet_user_encry_info = "sd8nUD0XtqHOIEHBZO9h6LLsnoWlm31a3zQz6VzQgCuQSFDl2PBt5S+V7oQ3+ktxSraLw3fc5bY=";
		String vnet_user_encry_info = "sd8nUD0XtqF98Khtw8IaMqMgaTAaL6azUg9vhqC4ZYg1uRyi6yInRb2szOU0XZO/v5+7heGAegY=";
		str = cryptogram.decryptByKey(vnet_user_encry_info,Key);
		System.out.println("str==\n"+str);
	}
	@Test
	public void test1name() throws Exception {
		
	}
}
