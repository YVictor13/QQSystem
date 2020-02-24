package com.QQ.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.QQ.DbUtil.StringUtil;
import com.QQ.Model.User;

/**
 * 用户Dao类
 * @author dell
 *
 */
public class UserDao {
	public int regedit(Connection con,User User) throws Exception{
		String sql="insert into information values(?,?,?,null,null,null,null,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		int  num =(int)(100000000+Math.random()*1000000000);
		pstmt.setInt(1, num);
		pstmt.setString(2, User.getUsername());
		pstmt.setString(3, User.getPassword());
		pstmt.setString(4, "0");
		return pstmt.executeUpdate();
	}
	
	/**
	 * 显示账号
	 * @return 
	 * 
	 */
	public  ResultSet Disploy(Connection con,User User) throws Exception{
		StringBuffer sb=new StringBuffer("select id from information ");
		if(StringUtil.isNotEmpty(User.getUsername())){
			sb.append(" and username like '%"+User.getUsername()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();		
	}
	
	/**
	 * 登陆验证
	 * @param con
	 * @param User
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User User) throws Exception{
		User resultUser =null;
		String sql="select id ,password from information where id=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, User.getId());
		pstmt.setString(2, User.getPassword());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			resultUser=new User();
			
			//resultUser.setId(rs.getInt("id"));
			
			resultUser.setUsername(rs.getString("id"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
	}
	
	public int update(Connection con,User User) throws Exception{
		String sql="update information set sex=?,year=?,month=?,day=?,address=?,Email=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, User.getSex());
		pstmt.setString(2, User.getYear());
		pstmt.setString(3, User.getMonth());
		pstmt.setString(4, User.getDay());
		pstmt.setString(5, User.getAddress());
		pstmt.setString(6, User.getEmail());
		
		return pstmt.executeUpdate();
	}
	
	public User loginToShowUserName(Connection con,User User) throws Exception{
		User resultUser =null;
		String sql="select username from information where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, User.getId());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			resultUser=new User();
			resultUser.setUsername(rs.getString("username"));
		}
		return resultUser;
	}
	
	public int LoginState(Connection con,String account) throws Exception{
		String sql="update information set state=? where id =?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		
		pstmt.setString(1, "1");
		pstmt.setString(2, account);
		
		return pstmt.executeUpdate();
	}
	
	public int ExitState(Connection con) throws Exception{
		String sql="update information set state=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, "0");	
		return pstmt.executeUpdate();
	}
	
	
}
