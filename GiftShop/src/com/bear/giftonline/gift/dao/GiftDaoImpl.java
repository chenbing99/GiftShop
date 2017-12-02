package com.bear.giftonline.gift.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bear.giftonline.entity.Gift;



@Repository
public class GiftDaoImpl {
	@Resource
	private SessionFactory sessionFactory; //注入sessionfactory
	public List<Gift> findAll(){
		Query q = this.sessionFactory.getCurrentSession().createQuery("from Gift");
		return q.list();
	}
}
