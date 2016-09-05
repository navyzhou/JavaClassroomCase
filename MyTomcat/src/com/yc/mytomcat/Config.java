package com.yc.mytomcat;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件的
 * @author navy
 */
public class Config extends Properties{
	private static final long serialVersionUID = 1L;
	private static Config config=new Config();
	
	private Config(){
		try {
			load(Config.class.getClassLoader().getResourceAsStream("web.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Config getConfig(){
		if(config==null){
			 config=new Config();
		}
		return config;
	}
}
