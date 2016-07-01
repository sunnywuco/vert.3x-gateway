package com.stone.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Component
public class SmsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

    @Value("${sms.account}")
    String account;
    @Value("${sms.pswd}")
    String password;
    @Value("${sms.url}")
    String url;
    @Autowired
    private ConfigLoader configLoader;

    public String sendSms(String account, String pswd, String mobile, String msg) {
        HttpResponse httpresponse = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(configLoader.getSmsNotifyUrl());
            String var = "cdkey=" + configLoader.getSmsAccount() + "&password=" + configLoader.getSmsPassword() + "&phone=" + mobile + "&message=【石头理财】" + msg;
            StringEntity reqEntity = new StringEntity(var, HTTP.UTF_8);
            reqEntity.setContentType("application/x-www-form-urlencoded");
            httppost.setEntity(reqEntity);
            httpresponse = httpclient.execute(httppost);
            HttpEntity entity = httpresponse.getEntity();
            // do something useful with the response body and ensure it is fully consumed
            EntityUtils.consume(entity);
        } catch (Exception e) {
            LOGGER.error("send message process is  failure,please check sms platform");
        }

        return "";
    }

    public String sendSms(String mobile, String msg) {
        return sendSms(account, password, mobile, msg);
    }

}
