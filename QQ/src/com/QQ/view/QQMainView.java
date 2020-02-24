package com.QQ.view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class QQMainView extends JFrame {

	private JPanel contentPane;
	private JDesktopPane Table=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QQMainView frame = new QQMainView();
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
	public QQMainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 508, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 10, 494, 477);
		contentPane.add(tabbedPane);
		
		JTabbedPane FriendTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("好友列表", null, FriendTabbedPane, null);
		
		JTabbedPane MessageTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("消息", null, MessageTabbedPane, null);
		
	}
}
