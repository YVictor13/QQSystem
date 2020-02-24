package com.QQ.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.QQ.DbUtil.DbUtil;
import com.QQ.DbUtil.IntUtil;
import com.QQ.DbUtil.StringUtil;
import com.QQ.Model.User;
import com.QQ.dao.UserDao;

public class LogOnFrm extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;

	private DbUtil dbUtil= new DbUtil();
	private UserDao userDao = new UserDao();
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogOnFrm frame = new LogOnFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogOnFrm() {
		setFont(new Font("Dialog", Font.BOLD, 20));
		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(LogOnFrm.class.getResource("/image/QQ.png")));
		setTitle("\u6B22\u8FCE\u8FDB\u5165QQ\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton Regedit = new JButton("\u6CE8\u518C");
		Regedit.setFont(new Font("宋体", Font.BOLD, 16));
		Regedit.setIcon(new ImageIcon(LogOnFrm.class.getResource("/image/edit.png")));
		
		/**
		 * 注册事件
		 */
		Regedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regeditActionPerformed(e);
			}
		});
		
		JButton LogOn = new JButton("\u767B\u5F55");
		/**
		 * 登录事件
		 */
		LogOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e);
			}
		});
		LogOn.setIcon(new ImageIcon(LogOnFrm.class.getResource("/image/login.png")));
		LogOn.setFont(new Font("宋体", Font.BOLD, 16));
		
		JLabel UserNameLabel = new JLabel("\u8D26\u53F7");
		UserNameLabel.setIcon(new ImageIcon(LogOnFrm.class.getResource("/image/userName.png")));
		UserNameLabel.setFont(new Font("宋体", Font.BOLD, 16));
		
		JLabel passwordLabel = new JLabel("\u5BC6\u7801");
		passwordLabel.setIcon(new ImageIcon(LogOnFrm.class.getResource("/image/password.png")));
		passwordLabel.setFont(new Font("宋体", Font.BOLD, 16));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		
		JLabel Log = new JLabel("");
		Log.setIcon(new ImageIcon(LogOnFrm.class.getResource("/image/QQ.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(63)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(UserNameLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(userNameTxt, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
					.addGap(102))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(97)
					.addComponent(Regedit, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addGap(79)
					.addComponent(LogOn, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(81, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(172)
					.addComponent(Log)
					.addContainerGap(195, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(Log)
					.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(UserNameLabel))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Regedit, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(LogOn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(40))
		);
		contentPane.setLayout(gl_contentPane);
	}
	/**
	 * 注册事件处理
	 * @param evt
	 */
	private void regeditActionPerformed(ActionEvent evt) {
		dispose();
		new RegeditFrm().setVisible(true);
	}

	/**
	 * 登录事件处理
	 * @param e
	 */
	private void loginActionPerformed(ActionEvent evt) {
		
		String id = this.userNameTxt.getText();
		String password = new String(this.passwordTxt.getPassword());
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！"); 
			return ;
		}
		if(StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return ;
		}
		if(!IntUtil.IsInt(id)) {
			JOptionPane.showMessageDialog(null, "用户名输入不正确！");
			return ;
		}
		User user = new User(id,null,password, null, null, null, null, null, null,null);
		Connection con = null;
		try {
			con=dbUtil.getCon();
			User currentUser = userDao.login(con, user);
			User username=userDao.loginToShowUserName(con, user);
			
			if(currentUser!=null)
			{
				//登录显示操作
				
				dispose();
				int temp = userDao.LoginState(con,user.getId());
				if(temp==1) {
					
					new QQFriendsListView(username.getUsername(),this.userNameTxt.getText()).setVisible(true);	
				}
			}else {
				
				JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
			}
			dbUtil.closeCon(con);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
}
