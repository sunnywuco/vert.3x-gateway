package com.stone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;

public class SmsTest {
	public static String sendSms(String account,String pswd,String mobile,String msg,String needstatus) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			// 目标地址
			HttpPost httppost = new HttpPost("http://localhost:18080/sms");
			// 构造最简单的字符串数据
			StringEntity reqEntity = new StringEntity("account=" + account + "&pswd=" + pswd + "&mobile=" + mobile + "&msg=" + msg + "&needstatus=" + needstatus,"UTF-8");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");
			reqEntity.setContentEncoding("UTF-8");
			// 设置请求的数据
			httppost.setEntity(reqEntity);
			// 执行
			HttpResponse httpresponse = httpclient.execute(httppost);
			HttpEntity entity = httpresponse.getEntity();
			String body = EntityUtils.toString(entity);
			String[] rs = body.split("\n");
//			String temp = null;
//			temp =  rs[0];
//			String[] ab = temp.split(",");
//			String status = ab[1];
//			if(!"0".equals(status)){
//				//System.out.println("短信又杭州接口发送");
//			}else{
//				//System.out.println("短信又上海接口发送");
//			}
			System.out.println(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String senSms(String mobile,String msg){
		return sendSms("stlc_sms","Txb123456",mobile,msg,"false");
	}

	
	public static void main(String args[]){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		//sendSms("stlc_sms","Txb123456","13868031625","登陆验证码：","true");
		senSms("18258838325","您申请的登陆验证码为：123456，有效期5分钟.");
		//System.out.println("dsfd");
	}
}
