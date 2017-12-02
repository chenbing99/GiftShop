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
	private String address;//地址
	private double telephone;
	private double email;
	private String postalcode;//邮政编码
	
	//关联关系映射
	private Shopcart shopcart;
	private Set orderSet = new HashSet<Order>();
	private Set logSet = new HashSet<Log>();
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getTelephone() {
		return telephone;
	}
	public void setTelephone(double telephone) {
		this.telephone = telephone;
	}
	public double getEmail() {
		return email;
	}
	public void setEmail(double email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	
	//用户和购物车一对一
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="ID")

	public Shopcart getShopcart() {
		return shopcart;
	}
	public void setShopcart(Shopcart shopcart) {
		this.shopcart = shopcart;
	}
	
	//用户和订单一对多  一个用户可以有多个订单
	@OneToMany(mappedBy="user", targetEntity=Order.class, 
            cascade=CascadeType.ALL)
	public Set getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set orderSet) {
		this.orderSet = orderSet;
	}
	
	
	//用户和日志文件一对多，一个用户多个日志文件
	@OneToMany(mappedBy="user", targetEntity=Log.class, 
            cascade=CascadeType.ALL)
	public Set getLogSet() {
		return logSet;
	}
	public void setLogSet(Set logSet) {
		this.logSet = logSet;
	}
	
}
