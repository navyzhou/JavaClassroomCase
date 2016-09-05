package com.yc.wowo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.wowo.utils.MailConnect;

public class SendMailServlet extends BasicServlet{
	private static final long serialVersionUID = 1L;
	private HttpSession session;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname=request.getParameter("uname");
		String mail=request.getParameter("mail");
		
		MailConnect mailConnect=new MailConnect();
		List<String> list=new ArrayList<String>();
		Collections.addAll(list,"1","2","3","4","5","6","7","8","9");
		for(int i=97;i<123;i++){  //97+26
			list.add( String.valueOf((char)i) );
		}
		
		Collections.shuffle(list);
		
		String code="";
		for(int i=0;i<6;i++){
			code+=list.get(i);
		}
		
		session=request.getSession();
		session.setAttribute("sendCode",code);
		
		boolean bl=mailConnect.sendQQmail("1293580602@qq.com","mwdiuiqtdrfnbaeh",mail,code,uname);
		//启用定时器开始计时
		startTimer(request);
		
		if(bl){
			this.out(response,1);
		}else{
			this.out(response,0);
		}
	}
	
	public void startTimer(final HttpServletRequest request){
		final Timer timer=new Timer();
		//timer.schedule(task, delay);//多少时间后开始执行方法一次
		//timer.scheduleAtFixedRate(task, delay, period); delay时间后开始执行task，以后每隔period执行task一次
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				session.removeAttribute("sendCode");
				timer.cancel();
			}
			
		},10000);
	}
}
