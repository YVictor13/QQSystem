package com.QQ.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.QQ.DbUtil.StringUtil;
import com.QQ.Model.Friend;
import com.QQ.Model.User;

public class FriendDao {
	
	/**
	 * 加好友操作
	 * @param con
	 * @param User
	 * @return
	 * @throws Exception
	 */
	
	
		public int regedit(Connection con,Friend friend) throws Exception{
			String sql="insert into friends values(null,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, friend.getMyaccount());
			pstmt.setString(2, friend.getFriendAccount());
			return pstmt.executeUpdate();
		}
		
		/**
		 * 查看好友列表
		 * @param con
		 * @param User
		 * @return
		 * @throws Exception
		 */
		
		public  ResultSet ShowFriendList(Connection con,Friend friend) throws Exception{
			String sql="select account_id2 from friends where account_id1=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, friend.getMyaccount());
			return pstmt.executeQuery();
		}
		
		
		
		/**
		 * 展现用户的群
		 */
		
		public  ResultSet ShowGroupList(Connection con,Friend friend) throws Exception{
			String sql="select groupname from group where account=? ";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, friend.getMyaccount());
			return pstmt.executeQuery();		
		}
		

	
	public int update(Connection con,User User) throws Exception{
		String sql="update information set sex=?,brithday=?,address=?,Email=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, User.getSex());
		pstmt.setString(2, User.getYear()+"年"+User.getYear()+"月"+User.getDay()+"日");
		pstmt.setString(3, User.getAddress());
		pstmt.setString(4, User.getEmail());
		return pstmt.executeUpdate();
	}

	
	 public ResultSet IsLogin(Connection con,String Account)throws Exception{
			StringBuffer sb=new StringBuffer("select state from information where id = ?");
			PreparedStatement pstmt=con.prepareStatement(sb.toString());
			pstmt.setString(1, Account);
			return pstmt.executeQuery();
		}
	 
	 
	 public int AddFriend(Connection con,Friend friend) throws Exception{
			String sql="insert into friends values(?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, friend.getMyaccount());
			pstmt.setString(2, friend.getFriendAccount());
			return pstmt.executeUpdate();
		}
	 
	 
	 public int AddGroup(Connection con,String account,String groupid) throws Exception{
			String sql="insert into grouplist values(?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, groupid);
			return pstmt.executeUpdate();
		}
	 
}
