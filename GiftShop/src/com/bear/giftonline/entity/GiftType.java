package com.bear.giftonline.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name="gifttype")

public class GiftType {
	private int gifttypeid;
	private String name;
	
	//关联关系映射 和book
	private Set giftSet = new HashSet<Gift>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	public int getGifttypeid() {
		return gifttypeid;
	}

	public void setGifttypeid(int gifttypeid) {
		this.gifttypeid = gifttypeid;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	//一个类型对应多本书
	
	@OneToMany(mappedBy="gifttype", targetEntity=Gift.class, 
            cascade=CascadeType.ALL)
	public Set getGiftSet() {
		return giftSet;
	}

	public void setGiftSet(Set giftSet) {
		this.giftSet = giftSet;
	}
	

	
}
