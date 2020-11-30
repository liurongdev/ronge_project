package com.ronge.demo.utils;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpDoUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpDoUtils.class);

    public String post(String urlString, Object params, Map<String, String> requestProperties) {
        URL url;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        BufferedOutputStream bufferedOutputStream = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(8000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Charset", String.valueOf(StandardCharsets.UTF_8));
            connection.setRequestProperty("Content-Type","application/json");

            if(!CollectionUtils.isEmpty(requestProperties)){
                for(Map.Entry<String,String> entry:requestProperties.entrySet()){
                    connection.setRequestProperty(entry.getKey(),entry.getValue());
                }
            }

            bufferedOutputStream = new BufferedOutputStream(connection.getOutputStream());
            bufferedOutputStream.write(JackSonUtils.convertObjectToString(params).getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            if(connection.getResponseCode() != 200){
                throw new RuntimeException("POST请求失败....");
            }

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String content;
            while ((content = reader.readLine()) != null) {
                builder.append(content);
            }
            return builder.toString();
        } catch (Exception e) {
            logger.error("request error:{}",e);
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(bufferedOutputStream);
        }
        return null;
    }

    public String get(String urlString, Map<String, String> requestProperties) {
        URL url;
        ByteArrayOutputStream byteArrayOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(8000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", String.valueOf(StandardCharsets.UTF_8));
            connection.setRequestProperty("Content-Type","application/json");

            if(!CollectionUtils.isEmpty(requestProperties)){
                for(Map.Entry<String,String> entry:requestProperties.entrySet()){
                    connection.setRequestProperty(entry.getKey(),entry.getValue());
                }
            }

            if(connection.getResponseCode() != 200){
                throw new RuntimeException("GET请求失败....");
            }

            bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, len);
            }
            return byteArrayOutputStream.toString();
        } catch (Exception e) {
            logger.error("request error:{}",e);
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            IOUtils.closeQuietly(bufferedInputStream);
            IOUtils.closeQuietly(byteArrayOutputStream);
        }
        return null;
    }

}
