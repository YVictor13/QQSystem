package com.QQ.DbUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class QQClient {

	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter pWriter;
	
	/**
	 * 聊天室客户端的构造方法
	 */
	
	
	public QQClient(String host,int port) throws UnknownHostException,IOException{
		socket = new Socket(host,port);
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pWriter = new PrintWriter(socket.getOutputStream());
	}
	
	
	/**
	 * 聊天室客户端发送消息的方法
	 */
	
	
	public void sendMessage(String str) {
		pWriter.println(str);
		pWriter.flush();
		
	}
	
	
	
	/**
	 * 聊天室客户端获取消息的方法
	 */
	
	public String reciveMessage() {
		
		
		try {
			return bufferedReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	/**
	 * 关闭套接字
	 */
	
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
