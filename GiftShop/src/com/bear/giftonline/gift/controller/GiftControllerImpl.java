package com.bear.giftonline.gift.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bear.giftonline.entity.Gift;
import com.bear.giftonline.gift.service.GiftServiceImpl;


@Controller
@RequestMapping("gift")
public class GiftControllerImpl {
	@Resource
	private GiftServiceImpl giftServiceImpl;
	@RequestMapping("/list")
	public String list(Model model) {
		List<Gift> list=this.giftServiceImpl.listAll();
		model.addAttribute("list", list);
		return "list";
	}
}
