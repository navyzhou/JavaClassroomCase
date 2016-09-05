package com.yc.chat.server;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Server {
	protected Shell shell;
	private Text text;
	private Table table;
	private Display display;

	private boolean isRun=false; //服务器的启停标识
	private ServerSocket ssk; //服务器
	private List<OnLineClient> clients=new ArrayList<OnLineClient>();
	private Button btnNewButton;
	private String port;
	private List<String> userList=new ArrayList<String>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Server window = new Server();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.MIN);
		shell.setImage(SWTResourceManager.getImage(Server.class, "/images/yc2.png"));
		shell.setSize(511, 379);
		shell.setText("网络聊天服务器");

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(33, 17, 51, 12);
		lblNewLabel.setText("端口号：");

		text = new Text(shell, SWT.BORDER);
		text.setText("6666");
		text.setBounds(90, 14, 128, 18);

		btnNewButton = new Button(shell, SWT.NONE);

		btnNewButton.setBounds(264, 12, 72, 22);
		btnNewButton.setText("启动服务器");

		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		
		btnNewButton_1.setBounds(362, 14, 72, 22);
		btnNewButton_1.setText("关闭服务器");

		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(11, 53, 482, 282);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn.setWidth(253);
		tblclmnNewColumn.setText("                 IP地址");

		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.CENTER);
		tblclmnNewColumn_1.setWidth(220);
		tblclmnNewColumn_1.setText("用户名");
		
		//关闭服务器
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isRun=false;
				btnNewButton.setEnabled(true);
			}
		});

		//启动服务器
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				port=text.getText().trim();
				new Thread(){
					@Override
					public void run() {
						startServer();
					}
				}.start();
				btnNewButton.setEnabled(false);
			}
		});
	}
	
	
	public void startServer(){
		try {
			ssk=new ServerSocket(Integer.parseInt(port));
			isRun=true;
		} catch (Exception e1) {
			isRun=false;
			System.out.println("服务器启动失败，请检查端口是否被占用....");
		}
		while(isRun){
			try {
				Socket sk=ssk.accept();//当用客户端连接上来时
				System.out.println(sk.getInetAddress().getHostAddress()+" 连上来了...");
				new Thread(new OnLineClient(sk)).start();
			} catch (IOException e1) {
				MessageDialog.openError(shell,"错误提示","服务器崩溃....");
				isRun=false;
				btnNewButton.setEnabled(true);
			} 
		}
	}

	/**
	 * 在线用户对象
	 * @author navy
	 */
	class OnLineClient implements Runnable{
		private Socket sk; //与当前登录用户保持连接的socket
		private String name; //当前在线用户的用户名
		private String ip; //当前在线用户使用的IP地址
		private boolean connected=false; //当前用户的在线状态
		private DataInputStream dis; //用于读取当前用户发送的信息
		private DataOutputStream dos; //用于向当前用户发送信息
		
		public String getIP(){
			return this.ip;
		}
		
		public String getName(){
			return this.name;
		}

		public OnLineClient(Socket sk){
			try {
				this.sk=sk;
				ip=sk.getInetAddress().getHostAddress();

				dis=new DataInputStream( sk.getInputStream() );
				dos=new DataOutputStream( sk.getOutputStream() );

				connected=true; //当前用户与服务器连接成功....
			} catch (IOException e) {
				connected=false;
				MessageDialog.openError(shell,"失败提示",name+" 与服务器连接失败...");
			}
		}

		/**
		 * 服务器向用户发送信息的方法
		 */
		public void send(String msg){
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				connected=false;
				clients.remove(this); 
				MessageDialog.openError(shell,"下线提示",name+" 已经下线...");
				//e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while(connected){ //如果当前用户还在线
					String info=dis.readUTF();//读取用户发送的信息,注意，服务器只负责转发数据
					
					boolean isExit=false;
					OnLineClient isRemoveObj = null;
					
					if(info.startsWith("login ")){//说明是用户的登录请求
						name=info.substring(info.indexOf(" ")+1);
						
						for(OnLineClient olc:clients){
							if(name.equals(olc.getName())){//说明该用户已经登录
								isExit=true;
								isRemoveObj=olc; //先将要移除的这个对象存起来
								//则先强制当前用户下线
								olc.send("again");
								System.out.println("发送了......");
								break;
							}
						}
						
						if(isExit){
							clients.remove(isRemoveObj);
							userList.remove(name);
							
							//当服务器表格中的当前用户移除
						}
						
						//将当前用户加入到在线聊天用户列表中
						clients.add(this);
						userList.add(name); //将当前用户添加到用户列表中

						//在服务器列表中显示当前用户
						display.asyncExec(new Runnable(){
							@Override
							public void run() {
								TableItem ti=new TableItem(table,SWT.NONE);
								ti.setText(new String[]{ip,name});
							}
						});
						
						StringBuffer sbf=new StringBuffer();
						for(String str:userList){
							sbf.append(str+",");
						}
						
						//将当前用户发送给所有在线用户
						for(OnLineClient ct:clients){
							ct.send("onLineUser "+sbf.toString());
						}
					}else if(info.startsWith("end ")){//说明是用户的下线请求
						final String outName=info.substring(info.indexOf(" ")+1);
						//把当前用户从在线用户列表中删除
						clients.remove(this);
						
						//把当前用户从userList中删除
						userList.remove(outName);
						
						//广播告诉其他在线用户，当前用户已经下线
						StringBuffer sbf=new StringBuffer();
						for(String str:userList){
							sbf.append(str+",");
						}
						
						//将当前用户发送给所有在线用户
						for(OnLineClient ct:clients){
							ct.send("onLineUser "+sbf.toString());
						}
						//通知用户下线
						this.send("end ");
						connected=false;
						//将当前用户从表格中移除
						display.asyncExec(new Runnable(){
							@Override
							public void run() {
								TableItem[] tis=table.getItems();
								for(int i=0,len=tis.length;i<len;i++){
									if(outName.equals(tis[i].getText(1).trim())){
										table.remove(i);
										break;
									}
								}
							}
						});
					}else if(info.startsWith("msg ")){//说明是用户的聊天记录请求
						//向当前所有的在线用户转发此用户发送的信息
						for(OnLineClient olc:clients){
							olc.send(info);
						}
					}
				}
			} catch (IOException e) {
				connected=false;
				clients.remove(this); //如果当前用户已经下线，则将当前用户从在线用户列表中删除
			} finally{
				if(sk!=null){
					try {
						sk.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
