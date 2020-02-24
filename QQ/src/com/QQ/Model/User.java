package com.QQ.Model;
/**
 * 用户实体
 * @author dell
 *
 */
public class User {
	
	
	private String id;
	private String username ;
	private String password;
	private String sex;
	private String year;
	private String month;
	private String day;
	private String address;
	private String Email;
	private String state;
	
	public User(String id,String username, String password,String sex,String year,String month,String day,String address,String Email,String state) {
		super();
		this.id =id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.year= year ;
		this.month = month;
		this.day = day;
		this.address = address;
		this.Email = Email;
		this.state=state;
	}
	


	public User(String regeditusername, String regeditpassword) {
		super();
		this.username=regeditusername;
		this.password=regeditpassword;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 快速建立get和set方法的快捷键 ： Atl +Shift + s
	 * @return
	 */
	
	
	public String getUsername() {
		return username;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDay() {
		return day;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
	

}
