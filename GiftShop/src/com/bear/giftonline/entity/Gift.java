
package com.bear.giftonline.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="gift")
public class Gift {
	private int id;
	private String name;
	private String author;
	private String img;
	private double price;
	private int discountprice;//打折商品
	//关联关系映射 
	private GiftType gifttype;
	private GiftDetail giftdetail;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getDiscountprice() {
		return discountprice;
	}
	public void setDiscountprice(int discountprice) {
		this.discountprice = discountprice;
	}
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	//一个书对应一个详情
	@OneToOne
	@Cascade(value=CascadeType.ALL)
	@PrimaryKeyJoinColumn(name="id")
	public GiftDetail getGiftdetail() {
		return giftdetail;
	}
	public void setGiftdetail(GiftDetail giftdetail) {
		this.giftdetail = giftdetail;
	}

	
	//多本书对应一个类型
	@ManyToOne
	@JoinColumn(name="gifttypeid")

	public GiftType getGifttype() {
		return gifttype;
	}
	public void setGifttype(GiftType gifttype) {
		this.gifttype = gifttype;
	}
	
	
	
}
