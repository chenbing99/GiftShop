package com.bear.giftonline.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
@Entity
@Table(name="order")

//很多人的订单

public class Order {
	private int id;
	private Double price;
	private String username;
	
	//关联关系映射 
	private User user;
	private OrderDetail ordertail;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	//多个订单对应一个用户
	@ManyToOne
    @JoinColumn(name="userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	//一个订单有一个订单详情
	 @OneToOne
	    @Cascade(value=CascadeType.ALL)
	    @PrimaryKeyJoinColumn(name="id")
	 public OrderDetail getOrdertail() {
			return ordertail;
		}
		public void setOrdertail(OrderDetail ordertail) {
			this.ordertail = ordertail;
		}
}
