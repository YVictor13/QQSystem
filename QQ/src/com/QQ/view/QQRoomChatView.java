package com.QQ.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.QQ.DbUtil.DbUtil;
import com.QQ.DbUtil.QQClient;
import com.QQ.dao.UserDao;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class QQRoomChatView extends JFrame {

	private JPanel contentPane;
	private JTextField MessageTxt;
	private String username;
	private QQClient client;
	private JTextArea MessageShowArea;
	private DbUtil dbUtil =new DbUtil();
	private UserDao  userdao=new UserDao();
	private JLabel label;

	/**
	 * Create the frame.
	 * 
	 *  	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QQChatView frame = new QQChatView("127.0.0.1","杨洋","hh");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QQRoomChatView frame = new QQRoomChatView("127.0.0.1","杨洋","hh","1079079202");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	


	

	
	public QQRoomChatView(String ip,final String username,String friednName,final String AccountID) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(QQRoomChatView.class.getResource("/image/QQ.png")));
		this.username=username;
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent atg0) {
				int op=JOptionPane.showConfirmDialog(null, "确定要退出群聊吗？", "确定", JOptionPane.YES_OPTION);
				if(op==1) {
					client.sendMessage("%EXIR%:" + username);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}
					Connection con = null;
					client.close();
					dispose();
					new QQFriendsListView(username, AccountID).setVisible(true);
					try {
						con=dbUtil.getCon();
						userdao.ExitState(con);
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					
				}else {
					return;
				}
			}
		});
		
		
		setTitle("你正在"+friednName+"聊天");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MessageTxt = new JTextField();
		MessageTxt.setBounds(84, 350, 270, 24);
		MessageTxt.setColumns(10);
		contentPane.add(MessageTxt);
		
		JButton button = new JButton("\u53D1\u9001");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
				client.sendMessage(username+"   "+df.format(date)+ "\n" + MessageTxt.getText());
				MessageTxt.setText("");
			}
		});
		
		button.setBounds(384, 349, 113, 27);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 521, 337);
		contentPane.add(scrollPane);
		
		MessageShowArea = new JTextArea();
		MessageShowArea.setEditable(false);
		scrollPane.setViewportView(MessageShowArea);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(QQRoomChatView.class.getResource("/image/3.gif")));
		label.setBounds(-2, 353, 72, 18);
		contentPane.add(label);
		
		try {
			client = new QQClient(ip, 11112);
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ReadMessageThread messageThread = new ReadMessageThread();
		messageThread.start();
		
	}
	private class ReadMessageThread extends Thread{
		public void run() {
			while(true) {
				
				String str=client.reciveMessage();
				MessageShowArea.append(str+"\n");
			}
			
		}
	}
			
}

