package com.jk.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	/**
	 * 解析 properties文件，properties数据类型 key=value ,根据key获取对应的value
	 */
	//1.获取properties文件路径
	private static final String CONFPATH = "/config.properties"; 
	
	public static String getProperties(String key){
		String value = null;
		//2.实例化properties对象
		//java.util.Properties
		Properties p = new Properties();
		try {
			//通过load()方法加载conf.properties文件
			InputStream inputStream = PropertiesUtil.class.getResourceAsStream(CONFPATH);
			//如果为空，表示config.properties未找到有可能是路径不对 
			if (null != inputStream) {
				p.load(inputStream);
				//通过getProperty()方法 根据key获取对应的value
				value = p.getProperty(key);
			}else{
				throw new RuntimeException("config.properties未找到该配置文件");
			}
		}catch (RuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	

}
