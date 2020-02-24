package com.QQ.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.QQ.DbUtil.StringUtil;
import com.QQ.Model.User;

public class InformationDao {
	/**
	 * 个人信息查询信息查询
	 * 
	 * 
	 */
	public ResultSet list(Connection con,User user)throws Exception{
		StringBuffer sb=new StringBuffer("select * from information where id=?");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		pstmt.setString(1, user.getId());
		return pstmt.executeQuery();
	}
	
	/**
	 * 个人信息修改
	 *
	 */
	public int update(Connection con,User user)throws Exception{
		String sql="update information set sex=?,brithday=?,address=?,Emainl=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,user.getSex());
		pstmt.setString(2, user.getYear()+"-"+user.getMonth()+"-"+ user.getDay());
		pstmt.setString(3, user.getAddress());
		pstmt.setString(4, user.getEmail());
		pstmt.setString(5, user.getId());
		return pstmt.executeUpdate();
	}

}
