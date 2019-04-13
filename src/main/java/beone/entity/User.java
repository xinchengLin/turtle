package beone.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="user")
public class User {
	@Id
	@Column(name="userId",nullable=false, unique = true,length = 32)
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自增长
	private long userId;
	
	@Column(nullable=false,length = 32)
	private String userName;
	
	@Column(nullable=false,length = 32)
	private String password;
	
	private Date registerTime;
	
	private boolean isAdmin=true;
	
	public User() {
		super();
	}
	

	public User(String userName, String password, Date registerTime) {
		super();
		this.userName = userName;
		this.password = password;
		this.registerTime = registerTime;
	}


	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean admin) {
		this.isAdmin = admin;
	}
	

}
