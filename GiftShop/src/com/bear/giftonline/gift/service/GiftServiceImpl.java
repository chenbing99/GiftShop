package com.bear.giftonline.gift.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bear.giftonline.entity.Gift;
import com.bear.giftonline.gift.dao.GiftDaoImpl;

@Service
@Transactional(readOnly=true)
public class GiftServiceImpl {
	@Resource
	private GiftDaoImpl giftDaoImpl;//注入dao
	public List<Gift> listAll(){
		return this.giftDaoImpl.findAll();
	}
}
