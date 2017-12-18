package com.bear.giftonline.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")

//很多人的订单

public class Order {
	private int orderid;
	private String username;
	
	//关联关系映射 
	private User user;
	private Set<OrderDetail> orderDetail = new HashSet<OrderDetail>();
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	//多个订单对应一个用户
	@ManyToOne(cascade = CascadeType.MERGE,optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name="userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	//一个订单有多个订单详情
	@OneToMany(mappedBy="order", targetEntity=OrderDetail.class,cascade=CascadeType.ALL)
	public Set<OrderDetail> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(Set<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

}
