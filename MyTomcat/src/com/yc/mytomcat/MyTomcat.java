package com.yc.mytomcat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyTomcat {
	public static void main(String[] args) throws IOException {
		ServerSocket ssk=new ServerSocket(8888);
		System.out.println("服务器已启动...");
		Socket sk=null;
		
		try {
			new WebxmlObject(); //读取配置文件
			
			while(true){
				sk=ssk.accept();
				new Thread( new HttpSession(sk) ).start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
