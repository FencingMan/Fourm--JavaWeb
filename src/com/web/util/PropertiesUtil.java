package com.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	public static final String DRIVER="driver";
	public static final String URL="url";
	public static final String USERNAME="username";
	public static final String PASSWORD="password";
	
	
	public static String getProperty(String key){
		//读取properties配置文件
		InputStream inStream=PropertiesUtil.class.getClassLoader().getResourceAsStream("db.properties");
		//创建properties对象
		Properties p=new Properties();
		try {
			//加载配置文件
			p.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}
}
