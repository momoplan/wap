package com.ruyicai.wap.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
/*
 * 访问http请求的工具类
   @company    北京金软瑞彩科技有限公司
 * @author     丁俊杰 
   @createDate 2011-3-4
 */
public class HttpUtil {
	private static  Logger logger = Logger.getLogger(HttpUtil.class);
	private static HttpClient httpClient = null;
	
	private static  HttpClient getHttpClient() {
		if(httpClient == null){
			httpClient = new HttpClient();
		}
		return httpClient;
	}
	
	/**
	 * get方法封装
	   @author       丁俊杰
	   @createDate   2011-7-7
	   @param url    
	   @return
	       默认的编码格式是utf-8
	 */
	public static synchronized String getMethod(String url) {
		HttpClient httpclient = getHttpClient();
		String resStr = "";
		logger.info("getMethod url="+url);
		GetMethod getMethod = new GetMethod(url);
		//InputStream stream = null;
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new  DefaultHttpMethodRetryHandler());
		try {
			int code = httpclient.executeMethod(getMethod);
			if (code == HttpStatus.SC_OK) {
				 byte[] responseBody = getMethod.getResponseBody();
				 resStr = new String(responseBody, "UTF-8");
				// 此response是明文
				/*stream = getMethod.getResponseBodyAsStream();
				StringBuffer resBuffter = new StringBuffer();
				byte[] buf = new byte[1024];
				int i = stream.read(buf);
				while (i != -1) {
					resBuffter.append(new String(buf, 0, i, "UTF-8"));
					i = stream.read(buf);
				}
				//stream.close();
				resStr = resBuffter.toString();*/
			} else {
				logger.error("不是200ok返回=code="+code+"url="+url);
			}
		} catch (Exception e) {
			logger.error("getMethod异常!", e);
		} finally {
			getMethod.releaseConnection();
			/*if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/
		}
		return resStr;
	}
	
	/**
	 * post方法封装
	   @author       丁俊杰
	   @createDate   2011-7-7
	   @param url
	   @param paramStr  封装为
	   @return
	 */
	public static synchronized String postMethod(String url, String paramStr) {
		HttpClient httpclient = getHttpClient();
		String resStr = "";
		PostMethod postMethod = new PostMethod(url);
		//InputStream stream = null;
		BufferedReader reader = null;
		StringBuffer resBuffter = new StringBuffer();
		try {
			if (paramStr.indexOf("&") != -1) {
				String[] strArr = paramStr.split("&");
				for (int i = 0; i < strArr.length; i++) {
					postMethod.addParameter(new NameValuePair(strArr[i]
							.split("=")[0], strArr[i].split("=")[1]));
				}
			} else {// 兼容参数为1个的时候
				postMethod.addParameter(new NameValuePair(
						paramStr.split("=")[0], paramStr.split("=")[1]));
			}
			int code = httpclient.executeMethod(postMethod);
			if (code == HttpStatus.SC_OK) {
				reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream(), "UTF-8"));
				String line; 
				while ((line = reader.readLine()) != null) {  
					resBuffter.append(line);
				}
				resStr = resBuffter.toString();
				reader.close();
				// 此response是明文
				/*stream = postMethod.getResponseBodyAsStream();
				StringBuffer resBuffter = new StringBuffer();
				byte[] buf = new byte[1024];
				int i = stream.read(buf);
				while (i != -1) {
					resBuffter.append(new String(buf, 0, i, "UTF-8"));
					i = stream.read(buf);
				}
				stream.close();
				resStr = resBuffter.toString();*/
			} else {
				logger.error("不是200ok返回");
			}
		} catch (Exception e) {
			logger.error("postMethod异常！！！！！", e);
		} finally {
			postMethod.releaseConnection();
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			/*if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}*/
		}
		return resStr;
	}
	
	/**
	 * 后台之间的通讯接口方法post
	   @author       丁俊杰
	   @createDate   2011-5-11
	   @param urlStr
	   @param paramStr
	   @return
	   @throws IOException
	 */
	public static String sendByPostUtF(String urlStr,String paramStr) {
        String  sTotalString = "";
        BufferedReader in = null;
        HttpURLConnection connection = null;
        OutputStream out = null;
        try {
        	logger.info("请求参数,urlStr="+urlStr+",paramStr="+paramStr);
            URL paostUrl = new URL(urlStr);
    		// 打开连接
    		connection = (HttpURLConnection) paostUrl
    				.openConnection();
    		connection.setRequestProperty("Content-Type",
    				"application/x-www-form-urlencoded");
    		connection.setDoOutput(true);
    		connection.setDoInput(true);
    		connection.setRequestMethod("POST");
    		connection.setUseCaches(false);
    		connection.setInstanceFollowRedirects(true);
    		connection.connect();
    		out = connection.getOutputStream();
    		out.write(paramStr.getBytes());
    		in = new BufferedReader(new InputStreamReader(
    				connection.getInputStream(), "UTF-8"), 1024 * 1024);
    		String line;
    		while ((line = in.readLine()) != null) {
    			sTotalString += line;
    		}
		} catch (Exception e) {
			logger.error("通讯接口发生异常,Exception:"+e.toString());
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (connection != null) {
					connection.disconnect();
					connection = null;
				}
			} catch (IOException e) {
				logger.info("关闭流异常！！！！！！", e);
			}
		}
		return sTotalString;
	}
}
