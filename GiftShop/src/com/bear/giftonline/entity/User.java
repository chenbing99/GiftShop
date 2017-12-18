package com.bear.giftonline.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user")

public class User {
	private int userid;
	private String username;
	private String password;
	private int tel;
	private String email;
	
	
	//关联关系映射
	private Shopcart shopcart;
	private Set<Order> orderSet = new HashSet<Order>();
	private Set<Log> logSet = new HashSet<Log>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	//用户和购物车一对一
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="id")

	public Shopcart getShopcart() {
		return shopcart;
	}
	public void setShopcart(Shopcart shopcart) {
		this.shopcart = shopcart;
	}
	
	
	//用户和订单一对多  一个用户可以有多个订单
	@OneToMany(mappedBy="user", targetEntity=Order.class,cascade=CascadeType.ALL)
	public Set<Order> getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}
	
	//用户和日志文件一对多，一个用户多个日志文件
	@OneToMany(mappedBy="user", targetEntity=Log.class,cascade=CascadeType.ALL)
	public Set<Log> getLogSet() {
		return logSet;
	}
	public void setLogSet(Set<Log> logSet) {
		this.logSet = logSet;
	}
	

	
}
