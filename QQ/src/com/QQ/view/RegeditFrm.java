package com.QQ.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.QQ.DbUtil.DbUtil;
import com.QQ.DbUtil.StringUtil;
import com.QQ.Model.User;
import com.QQ.dao.UserDao;

public class RegeditFrm extends JFrame {

	private JPanel contentPane;
	private JTextField RegedituserNameTxt;
	private JTextField RegeditpasswordTxt;
	private JDesktopPane table =null;
	/**
	 * Launch the application.
	 */
	
	private DbUtil RegeditdbUtil= new DbUtil();
	private UserDao RegedituserDao = new UserDao();
	
	
	/**
	 * public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegeditFrm frame = new RegeditFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */

	/**
	 * Create the frame.
	 */
	public RegeditFrm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegeditFrm.class.getResource("/image/QQ.png")));
		setFont(new Font("Dialog", Font.BOLD, 14));
		setResizable(false);
		setTitle("QQ\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel RegeditLog = new JLabel("");
		RegeditLog.setIcon(new ImageIcon(RegeditFrm.class.getResource("/image/QQ.png")));
		
		JLabel RegedituserNameLabel = new JLabel("\u8D26\u53F7\u540D");
		RegedituserNameLabel.setIcon(new ImageIcon(RegeditFrm.class.getResource("/image/userName.png")));
		RegedituserNameLabel.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel RegeditpasswordLabel = new JLabel("\u5BC6  \u7801");
		RegeditpasswordLabel.setIcon(new ImageIcon(RegeditFrm.class.getResource("/image/password.png")));
		RegeditpasswordLabel.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel RegeditTitle_1 = new JLabel("\u6B22\u8FCE\u6CE8\u518CQQ");
		RegeditTitle_1.setFont(new Font("楷体", Font.BOLD, 16));
		
		JLabel RegeditTitle_2 = new JLabel("\u6BCF\u4E00\u5929\uFF0C\u4E50\u5728\u4EA4\u6D41");
		RegeditTitle_2.setFont(new Font("楷体", Font.BOLD, 18));
		
		JButton ExitButton = new JButton("\u9000    \u51FA");
		/**
		 * 退出事件控制
		 */
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		ExitButton.setFont(new Font("楷体", Font.BOLD, 16));
		
		JButton RegeditButton = new JButton("\u7ACB\u5373\u6CE8\u518C");
		/**
		 * 注册事件监听
		 */
		RegeditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegeditActionPerformed(e);
			}
		});
		RegeditButton.setFont(new Font("楷体", Font.BOLD, 16));
		
		RegedituserNameTxt = new JTextField();
		RegedituserNameTxt.setColumns(10);
		
		RegeditpasswordTxt = new JTextField();
		RegeditpasswordTxt.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(91)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(RegedituserNameLabel)
								.addComponent(RegeditpasswordLabel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(RegeditpasswordTxt)
								.addComponent(RegedituserNameTxt, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(165)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(RegeditTitle_2, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
								.addComponent(RegeditLog))))
					.addContainerGap(103, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(188, Short.MAX_VALUE)
					.addComponent(RegeditTitle_1, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addGap(155))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(81)
					.addComponent(ExitButton, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addGap(84)
					.addComponent(RegeditButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(105)
					.addComponent(RegeditLog)
					.addGap(18)
					.addComponent(RegeditTitle_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(RegeditTitle_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(RegedituserNameLabel)
						.addComponent(RegedituserNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(RegeditpasswordLabel)
						.addComponent(RegeditpasswordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(61)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ExitButton)
						.addComponent(RegeditButton))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		table = new JDesktopPane();
		contentPane.add(table, BorderLayout.CENTER);
	}

	
	private void RegeditActionPerformed(ActionEvent etv) {
		String Regeditusername = this.RegedituserNameTxt.getText();
		String Regeditpassword = this.RegeditpasswordTxt.getText();
		if(StringUtil.isEmpty(Regeditusername)) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！"); 
			return ;
		}
		if(StringUtil.isEmpty(Regeditpassword)) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return ;
		}
		
		User user = new User(null,Regeditusername,Regeditpassword,null, null, null, null, null, null,null);
		Connection con = null;
		try {
			con=RegeditdbUtil.getCon();
			int currentUser = RegedituserDao.regedit(con, user);
			String sql="select id from information where username=? and password=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Regeditusername);
			pstmt.setString(2, Regeditpassword);
			ResultSet rs=pstmt.executeQuery();
			JLabel Output = new JLabel();
			String id = " ";
			
			
			if(rs.next()) {
				id = rs.getString("id");
			}
			
			if(currentUser==1)
			{
				//加入成功显示操作
				JOptionPane.showMessageDialog(null, "注册成功\n账号： "+id+"\n密码："+Regeditpassword);
				new LogOnFrm().setVisible(true);
				dispose();
			}else {
				JOptionPane.showConfirmDialog(null, "注册失败！");
			}
			RegeditdbUtil.closeCon(con);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}
}
