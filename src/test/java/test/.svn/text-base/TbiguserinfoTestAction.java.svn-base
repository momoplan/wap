package test;

import net.sf.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;

import com.ruyicai.wap.controller.TbiguserInfoAction;
import com.ruyicai.wap.util.CommonUtil;


public class TbiguserinfoTestAction {
	@Test
	 public void bindBigUserTest() {//绑定用户功能
		TbiguserInfoAction action = new TbiguserInfoAction();
		String userno = CommonUtil.getUserno("15210900137");
		String  re  = action.bindBigUser(userno, "000000000", "000002");
		System.out.println(re);
     	Assert.assertEquals("0",re );
	}
	@Test
	public void getUserinfoByUserNoTest() {//查询绑定用户的信息
		JSONObject  re  = CommonUtil.getUserinfoByUserNo("000000000");
		String errorCode = re.getString("errorCode");
		Assert.assertEquals("0",errorCode );
	}
}
