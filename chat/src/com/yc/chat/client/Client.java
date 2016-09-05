package com.yc.chat.client;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Client implements Runnable{
	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Table table;
	private Text text_4;
	private Button btnNewButton; //登录按钮
	private Button btnNewButton_2; //发送按钮
	
	private Socket sk;  //连接对象
	private DataInputStream dis;
	private DataOutputStream dos;
	private boolean connected=false;
	private String msg;
	private String ip;
	private Integer port;
	private String name;
	private Display display;
	private boolean isForce=false;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Client window = new Client();
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(Client.class, "/images/yc2.png"));
		shell.setSize(840, 490);
		shell.setText("网络聊天室客户端");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(20, 13, 66, 12);
		lblNewLabel.setText("服务器地址：");
		
		text = new Text(composite_1, SWT.BORDER);
		text.setText("127.0.0.1");
		text.setBounds(92, 10, 108, 18);
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBounds(244, 13, 54, 12);
		lblNewLabel_1.setText("端口号：");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setText("6666");
		text_1.setBounds(301, 10, 93, 18);
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setBounds(439, 13, 48, 12);
		lblNewLabel_2.setText("用户名：");
		
		text_2 = new Text(composite_1, SWT.BORDER);
		text_2.setBounds(493, 10, 108, 18);
		
		btnNewButton = new Button(composite_1, SWT.NONE);
		
		btnNewButton.setBounds(637, 5, 52, 22);
		btnNewButton.setText("登录");
		
		Button btnNewButton_1 = new Button(composite_1, SWT.NONE);
		
		btnNewButton_1.setBounds(709, 5, 54, 22);
		btnNewButton_1.setText("退出");
		
		Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_2, SWT.NONE);
		
		Composite composite_4 = new Composite(sashForm_1, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group = new Group(composite_4, SWT.NONE);
		group.setText("聊天记录");
		group.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		text_3 = new Text(group, SWT.BORDER | SWT.MULTI);
		
		Composite composite_5 = new Composite(sashForm_1, SWT.NONE);
		composite_5.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Group group_1 = new Group(composite_5, SWT.NONE);
		group_1.setText("在线用户列表");
		group_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(group_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("  在线用户名");
		sashForm_1.setWeights(new int[] {710, 119});
		
		Composite composite_3 = new Composite(sashForm, SWT.NONE);
		
		Label lblNewLabel_3 = new Label(composite_3, SWT.NONE);
		lblNewLabel_3.setBounds(10, 25, 36, 12);
		lblNewLabel_3.setText("内容：");
		
		text_4 = new Text(composite_3, SWT.BORDER | SWT.MULTI);
		text_4.setBounds(52, 10, 713, 40);
		
		btnNewButton_2 = new Button(composite_3, SWT.NONE);
		
		btnNewButton_2.setBounds(771, 12, 51, 34);
		btnNewButton_2.setText("发送");
		btnNewButton_2.setEnabled(false);
		sashForm.setWeights(new int[] {41, 351, 58});
		
		//点击退出时
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//发一条信息到服务器，告诉服务器我要下线了
				try {
					dos.writeUTF("end "+name);
					dos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				connected=false;
			}
		});
		
		//点击发送
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String content=text_4.getText().trim();
					if(content==null || "".equals(content)){
						return;
					}else{
						String info="msg "+name+" "+content;//InetAddress.getLocalHost().getHostAddress()
						dos.writeUTF(info);
						dos.flush();
						
						text_4.setText("");
						text_4.setFocus();
					}
				} catch (IOException e1) {
					MessageDialog.openError(shell,"失败提示","信息发送失败...");
					e1.printStackTrace();
				}
			}
		});

		//登录
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ip=text.getText().trim();
				port=Integer.parseInt(text_1.getText().trim());
				name=text_2.getText().trim();
				
				connect();
				
				btnNewButton.setEnabled(false);
				btnNewButton_2.setEnabled(true);
				
				Thread t=new Thread(Client.this);
				t.start(); //run方法开始运行
			}
		});
	}
	
	
	/**
	 * 连接服务器的方法
	 */
	public void connect(){
		try {
			sk=new Socket(ip,port); //建立连接
			dis=new DataInputStream( sk.getInputStream() );
			dos=new DataOutputStream( sk.getOutputStream() );
			System.out.println(name+" 连接服务器成功...");
			connected=true;
			
			//当连接成功后，将当前用户的用户名发送到服务器
			dos.writeUTF("login "+name);
		} catch (Exception e1) {
			connected=false;
			MessageDialog.openError(shell,"错误提示","服务器连接失败....");
			e1.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				if(connected && sk.isConnected() && !sk.isClosed()){
					msg=dis.readUTF(); //获取服务器的回送信息
					if(msg.startsWith("onLineUser ")){
						display.asyncExec(new Runnable(){
							@Override
							public void run() {
								table.removeAll();
								msg=msg.substring(msg.indexOf(" ")+1);
								String[] users=msg.split(",");
								TableItem ti=null;
								for(int i=0,len=users.length;i<len;i++){
									ti=new TableItem(table,SWT.NONE);
									ti.setText(new String[]{users[i]});
								}
							}
						});
					}else if(msg.startsWith("msg ")){
						//用户发送的信息
						final String[] str=msg.split(" ");
						display.asyncExec(new Runnable(){
							@Override
							public void run() {
								text_3.append(str[1]+" 说：\n    "+str[2]+"\n\n");
							}
						});
					}else if(msg.startsWith("end ")){
						break;
					}else if(msg.startsWith("again")){ //说明被强制下线
						isForce=true;
						connected=false;
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		display.asyncExec(new Runnable(){
			@Override
			public void run() {
				if(isForce){
					MessageDialog.openWarning(shell,"下线通知","您的帐号已经在其它地方地方，若非本人操作，请修改密码....");
				}
				shell.dispose();// TODO Auto-generated method stub
			}
		});
	}
}
