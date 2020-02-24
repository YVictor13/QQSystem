package com.QQ.DbUtil;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
	 public static void main(String args[]) {
	        Connection conn=null;
	        try {
	          Class.forName("com.mysql.cj.jdbc.Driver");     
	          System.out.println("Success loading Mysql Driver!");
	        }
	        catch (Exception e) {
	          System.out.print("Error loading Mysql Driver!");
	          e.printStackTrace();
	        }

	        try {
	          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/qqdatebase?serverTimezone=UTC&characterEncoding=utf-8", "root", "yyvictory");
	          System.out.println("Success connect Mysql server!");
	          conn.close();

	        }catch (Exception e) {
	          System.out.print("get data error!");
	          e.printStackTrace();
	        }  
	    }
	
}
