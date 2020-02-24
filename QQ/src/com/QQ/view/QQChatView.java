package com.QQ.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.ResultSet;
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
import com.QQ.dao.FriendDao;
import com.QQ.dao.UserDao;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class QQChatView extends JFrame {

	private JPanel contentPane;
	private JTextField MessageTxt;
	private String username;
	private QQClient client;
	private JTextArea MessageShowArea;
	private FriendDao frienddao=new FriendDao();
	private UserDao userdao=new UserDao();
	private DbUtil dbUtil=new DbUtil();

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
					QQChatView frame = new QQChatView("127.0.0.1","杨洋","hh");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	 


	
	public QQChatView(String ip,final String username,String friednName) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(QQChatView.class.getResource("/image/QQ.png")));
		Connection con = null;
		try {
			ResultSet rs = frienddao.IsLogin(con, friednName);
			
			if(rs.next()) {
				
			}
		} catch (Exception e) {
			
		}

		this.username=username;
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent atg0) {
				int op=JOptionPane.showConfirmDialog(null, "确定要退出QQ吗？", "确定", JOptionPane.YES_NO_OPTION);
				if(op==JOptionPane.YES_OPTION) {
					client.sendMessage("%EXIT%:" + username);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					client.close();
					dispose();
					
					Connection con = null;
					
					
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
					
				}
			}
		});
		
		
		setTitle("你正在和"+friednName+"聊天");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MessageTxt = new JTextField();
		MessageTxt.setBounds(108, 389, 244, 24);
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
		
		button.setBounds(386, 388, 113, 27);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 521, 376);
		contentPane.add(scrollPane);
		
		MessageShowArea = new JTextArea();
		MessageShowArea.setEditable(false);
		scrollPane.setViewportView(MessageShowArea);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(QQChatView.class.getResource("/image/3.gif")));
		label.setBounds(32, 376, 62, 50);
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


