package com.QQ.Model;

public class Friend {

	private String Myaccount;
	private String FriendAccount;
	
	public Friend() {
		
	}
	
	public Friend(String Myaccount,String FriendAccount) {
		this.Myaccount=Myaccount;
		this.FriendAccount=FriendAccount;
	}
	

	public String getMyaccount() {
		return Myaccount;
	}
	public void setMyaccount(String myaccount) {
		Myaccount = myaccount;
	}
	public String getFriendAccount() {
		return FriendAccount;
	}
	public void setFriendAccount(String friendAccount) {
		FriendAccount = friendAccount;
	}
	
	

}
