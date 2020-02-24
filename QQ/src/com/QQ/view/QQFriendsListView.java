package com.QQ.view;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.QQ.DbUtil.DbUtil;
import com.QQ.DbUtil.StringUtil;
import com.QQ.Model.Friend;
import com.QQ.dao.FriendDao;
import com.QQ.dao.UserDao;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class QQFriendsListView extends JFrame implements ActionListener ,MouseListener{

	private JPanel contentPane;
	private JLabel []FriendList;
	private String []FList;
	private JLabel []GroupList;
	private String []GList;
	private String Owner,AccountID;
	private JTextField FriendIDTxt;
	private JTextField GroupIDTxt;
	private JPanel GroupPanel;
	private JPanel FriendListPanel;
	private JScrollPane scrollPane_1;
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPane;
	private JPanel FriendsPanel;
	private JPanel GroupListPanel;
	private FriendDao firendList = new FriendDao();
	private UserDao userdao=new UserDao();
	private DbUtil dbUtil= new DbUtil();
	private JButton button_2;
	private JButton button_3;
	
	
	/**
	 * 
	 * 	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QQFriendsListView frame = new QQFriendsListView("hh","874274302");
					frame.setVisible(true);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
	}
	 * @param args
	 */



	

	/**
	 * Create the frame.
	 */
	public QQFriendsListView(String Owner,String AccountID) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(QQFriendsListView.class.getResource("/image/QQ.png")));
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent atg0) {
				int op=JOptionPane.showConfirmDialog(QQFriendsListView.this, "确定要退出QQ吗？", "确定", JOptionPane.YES_OPTION);
				Connection con = null;
				if(op==1) {
					try {
						con=dbUtil.getCon();
						int temp=userdao.ExitState(con);
						if(temp==1) {
							dispose();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}else {
					return;
				}
			}
		});
		
		this.AccountID=AccountID;
		this.Owner=Owner;
		setTitle(Owner);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 444, 564);
		contentPane.add(tabbedPane);
		
		FriendListPanel = new JPanel();
		tabbedPane.addTab("好友列表", null, FriendListPanel, null);
		
		scrollPane = new JScrollPane();
		
		JLabel label = new JLabel(" \u8D26 \u53F7");
		label.setIcon(new ImageIcon(QQFriendsListView.class.getResource("/image/userName.png")));
		
		FriendIDTxt = new JTextField();
		FriendIDTxt.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.setIcon(new ImageIcon(QQFriendsListView.class.getResource("/image/add.png")));
		/**
		 * 添加好友
		 */
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFriendActionPerformed(e);
						
			}
		});
		
		
		
		button_3 = new JButton("\u5237\u65B0");
		button_3.setIcon(new ImageIcon(QQFriendsListView.class.getResource("/image/reset.png")));
		/**
		 * 刷新操作
		 */
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnActionPerformed(e);
			}
		});
		GroupLayout gl_FriendListPanel = new GroupLayout(FriendListPanel);
		gl_FriendListPanel.setHorizontalGroup(
			gl_FriendListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_FriendListPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(18)
					.addComponent(FriendIDTxt, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addGap(12)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_3)
					.addContainerGap())
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
		);
		gl_FriendListPanel.setVerticalGroup(
			gl_FriendListPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_FriendListPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_FriendListPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(FriendIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label)
						.addComponent(button_3)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 485, GroupLayout.PREFERRED_SIZE))
		);
		
		FriendsPanel = new JPanel();
		scrollPane.setViewportView(FriendsPanel);
		FriendsPanel.setLayout(new GridLayout(50, 1, 4, 4));
		FriendListPanel.setLayout(gl_FriendListPanel);
		
		GroupListPanel = new JPanel();
		tabbedPane.addTab("群聊管理", null, GroupListPanel, null);
		
		scrollPane_1 = new JScrollPane();
		
		JLabel label_1 = new JLabel("\u7FA4\u53F7");
		label_1.setIcon(new ImageIcon(QQFriendsListView.class.getResource("/image/about.png")));
		
		GroupIDTxt = new JTextField();
		GroupIDTxt.setColumns(10);
		
		JButton button_1 = new JButton("\u6DFB\u52A0");
		button_1.setIcon(new ImageIcon(QQFriendsListView.class.getResource("/image/add.png")));
		/**
		 * 添加群
		 */
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGroupActionPerformed(e);
			}
		});
		
		button_2 = new JButton("\u5237\u65B0");
		button_2.setIcon(new ImageIcon(QQFriendsListView.class.getResource("/image/reset.png")));
		/**
		 * 刷新群
		 */
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReturnGroupActionPerformed(e);
			}
		});
		GroupLayout gl_GroupListPanel = new GroupLayout(GroupListPanel);
		gl_GroupListPanel.setHorizontalGroup(
			gl_GroupListPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_GroupListPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_1)
					.addGap(18)
					.addComponent(GroupIDTxt, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1)
					.addGap(18)
					.addComponent(button_2)
					.addContainerGap())
				.addComponent(scrollPane_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
		);
		gl_GroupListPanel.setVerticalGroup(
			gl_GroupListPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_GroupListPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_GroupListPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(GroupIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(button_2)
						.addComponent(button_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 479, GroupLayout.PREFERRED_SIZE))
		);
		
		GroupPanel = new JPanel();
		scrollPane_1.setViewportView(GroupPanel);
		GroupPanel.setLayout(new GridLayout(20, 1, 4, 4));
		GroupListPanel.setLayout(gl_GroupListPanel);
		GroupListPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{scrollPane_1}));
		
		//加入50个群
		
		/**
		 * 展示好友列表
		 */
		//加入50个好友
		Connection con = null;
		try {
			con=dbUtil.getCon();
			String sql="select information.username from friends , information where information.id=friends.account_id2 and account_id1=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, AccountID);
			ResultSet rs  = pstmt.executeQuery();
			int i = 0;
			String temp=null;
			FriendList=new JLabel[100];
			while(rs.next()) {
				temp=rs.getString("username");
				if(temp!=null) {
					FriendList[i] = new JLabel("好友   "+temp+"",JLabel.LEFT);
					FriendList[i].addMouseListener(this);
					FriendsPanel.add(FriendList[i]);
					i++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		 * 显示群列表
		 */
			
				try {
					con=dbUtil.getCon();
					String sql="select groupname from grouplist as a,groupinformation as b where a.groupid = b.groupid and a.account=?";
					PreparedStatement pstmt=con.prepareStatement(sql);
					pstmt.setString(1, AccountID);
					ResultSet rs= pstmt.executeQuery();
					GroupList=new JLabel[20];
					int i = 0;
					String temp=null;
					while(rs.next()) {
						temp=rs.getString("groupname");
						if(temp!=null) {
							GroupList[i] = new JLabel("群   "+temp+"",JLabel.LEFT);
							System.out.println(temp);
							GroupList[i].addMouseListener(this);
							GroupPanel.add(GroupList[i]);
							i++;
						}
					}
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
	
	}

	public  void ReturnGroupActionPerformed(ActionEvent etv) {
		Connection con = null;
		try {
			con=dbUtil.getCon();
			String sql="select groupname from grouplist as a,groupinformation as b where a.groupid = b.groupid and a.account=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, AccountID);
			ResultSet rs= pstmt.executeQuery();
			GroupPanel.removeAll();
			GroupList=new JLabel[20];
			int i = 0;
			String temp=null;
			while(rs.next()) {
				temp=rs.getString("groupname");
				if(temp!=null) {
					GroupList[i] = new JLabel("群   "+temp+"",JLabel.LEFT);
					System.out.println(temp);
					GroupList[i].addMouseListener(this);
					GroupPanel.add(GroupList[i]);
					i++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}



	/**
	 * 添加群
	 * @param etv
	 */
	public void AddGroupActionPerformed(ActionEvent etv) {
		
		String groupid=this.GroupIDTxt.getText();
		
		if(StringUtil.isEmpty(groupid)) {
			JOptionPane.showMessageDialog(null, "群账号不能为空");
			return ;
		}
		Connection con = null;
		try {
			 con =dbUtil.getCon();
			int rs=firendList.AddGroup(con, AccountID,groupid);
			if(rs==1) {
				JOptionPane.showMessageDialog(null, "添加成功");
			}
			} catch (Exception e) {					
				JOptionPane.showMessageDialog(null, "操作错误\n请核实你添加的群账号是否正确\n或者是否进行了重复添加");
		}		
		
	}



	public void ReturnActionPerformed(ActionEvent evt) {
		
		
		Connection con = null;
		try {
			con=dbUtil.getCon();
			String sql="select information.username from friends , information where information.id=friends.account_id2 and account_id1=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, AccountID);
			ResultSet rs  = pstmt.executeQuery();
			FriendsPanel.removeAll();
			int i = 0;
			String temp=null;
			FriendList=new JLabel[100];
			
			while(rs.next()) {
				temp=rs.getString("username");
				if(temp!=null) {
					FriendList[i] = new JLabel("好友   "+temp+"",JLabel.LEFT);
					FriendList[i].addMouseListener(this);
					FriendsPanel.add(FriendList[i]);
					i++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	public void AddFriendActionPerformed(ActionEvent etv) {
		String friendaccount=this.FriendIDTxt.getText();
		Friend friend =new Friend(AccountID,friendaccount);
		if(StringUtil.isEmpty(friendaccount)) {
			JOptionPane.showMessageDialog(null, "好友账号不能为空");
			return ;
		}
		if(friendaccount.equals(AccountID)) {
			JOptionPane.showMessageDialog(null, "自己不可添加自己噢");
			return ;
		}
		Connection con = null;
		try {
			 con =dbUtil.getCon();
			int  rs= firendList.AddFriend(con, friend);	
			if(rs==1) {
				JOptionPane.showMessageDialog(null, "添加成功");
			}
			
			} catch (Exception e) {					
				JOptionPane.showMessageDialog(null, "操作错误\n请核实你添加的好友账号，或者是否进行了重复添加");
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//响应用户双击的事件，并得到好友的编号.
		if(e.getClickCount()==2)
			{
				String friendNo=((JLabel)e.getSource()).getText();
				String[] array1 = friendNo.split("好友");
				String[] array2 = friendNo.split("群");
				if(array1.length==1) {
					QQRoomChatView Chat = new QQRoomChatView("127.0.0.1",Owner,friendNo,AccountID);
					Chat.setVisible(true);	
					
				}else {
					
					QQChatView Chat = new QQChatView("127.0.0.1",Owner,friendNo);
					Chat.setVisible(true);
				}

			}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.black);
	}
}
