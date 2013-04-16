package com.ruyicai.wap.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PagerReader {
	// 连接对象
	private static HttpURLConnection conn;

	/** */
	/**
	 * 根据url连接某地址，并返回返回码. 返回码说明： 0~200为正常情况，其中200为OK 其余都为错误的情况，具体请参见w3
	 * 
	 * @param urlStr
	 *            需连接的url字符串
	 */
	private int connect(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		conn = (HttpURLConnection) url.openConnection();
		System.out.println("返回码: " + conn.getResponseCode());
		// 如果定向的地址经过重定向，
		// 那么conn.getURL().toString()显示的是重定向后的地址
		System.out.println(conn.getURL().toString());
		return conn.getResponseCode();
	}

	/** */
	/**
	 * 读取网页的内容.
	 * 
	 * @return 返回网页的内容
	 */
	private String readContents() throws Exception {
		BufferedReader in = null;
		StringBuffer sb = new StringBuffer();
		in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
			sb.append("\n");
		}
		return sb.toString();
	}

	/** */
	/**
	 * 中断连接.
	 */
	private void disconnect() {
		conn.disconnect();
	}
	
	/** *//**
	* 测试方法
	* @param args
	* @throws Exception
	*/
  public static void main(String[] args) throws Exception {
	PagerReader reader = new PagerReader();
	String url = "http://192.168.1.109:8888/jrtWAPSite1.0/wap/userinfo/recommendRegister.jsp/13436602479";
	reader.connect(url);
	String content = reader.readContents();
	System.out.println("网页内容：" + content);
	reader.disconnect();
	}

}
