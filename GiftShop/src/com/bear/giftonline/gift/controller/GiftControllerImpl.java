package com.bear.giftonline.gift.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bear.giftonline.entity.Gift;
import com.bear.giftonline.entity.GiftDetail;
import com.bear.giftonline.entity.GiftType;
import com.bear.giftonline.entity.Order;
import com.bear.giftonline.entity.OrderDetail;
import com.bear.giftonline.entity.Page;
import com.bear.giftonline.entity.User;
import com.bear.giftonline.gift.service.GiftServiceImpl;

@Controller
@RequestMapping("/gift")
public class GiftControllerImpl {
		
	
	
	    @Resource
		private GiftServiceImpl giftServiceImpl;
		
		
		
		//产品页  读取出首页产品信息
		@RequestMapping("/list")
		public String list(Model model) {
			List<Gift> list=this.giftServiceImpl.listAll();
			model.addAttribute("list", list);
			return "product";
		}
		
		

		
		//分页根据产品id分成四个页面
		@RequestMapping("/list2")
		public String findAll(HttpServletRequest request,HttpServletResponse response) { 
				try {
					request.setCharacterEncoding("UTF-8");
					response.setContentType("charset=UTF-8;text/html");
					response.setCharacterEncoding("UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        String pageNo = request.getParameter("pageNo");
		        if (pageNo == null) {
		            pageNo = "1";
		        }
		        Page<Gift> page = giftServiceImpl.queryForPage(Integer.valueOf(pageNo), 4);
		        request.setAttribute("page", page);
		        List<Gift> list2 = page.getList();
		        for(Gift g:list2) {
		        	System.out.println(g.getName());
		        }
		        System.out.println(list2);
		        request.setAttribute("list2", list2);
		        return "product";
		}
		
		
		//点击图片进入详情页	
		@RequestMapping("/findAllGiftDetail")
		public String findAllGiftDetail(Model model,@RequestParam("giftid") int giftid) {
			List<GiftDetail> detailList= this.giftServiceImpl.findAllGiftDetail(giftid);
			model.addAttribute("detailList", detailList);
			return "detail";
		}
		
		//搜索  根据关键字搜索产品
		@RequestMapping("/findByGiftName")
		public String findByGiftName(Model model,@RequestParam("giftname") String giftname) {
			List<GiftDetail> giftList= this.giftServiceImpl.findAllGift(giftname);
			model.addAttribute("giftList", giftList);
			return "detail";
		}
		
		//分类 点击一个类型 会出来相对应产品的信息
		@RequestMapping("/findByGiftTypeid")
		public String findByTypeid(Model model,@RequestParam("gifttypeid") int gifttypeid) {
			//获取到某一种类型的图书信息
			List<Gift> sublist = this.giftServiceImpl. QueryByTypeid(gifttypeid);
			//获取到所有的图书类型
			List<GiftType> GifttypeList = this.giftServiceImpl.findAllType(gifttypeid);
			model.addAttribute("sublist", sublist);
			model.addAttribute("gifttypeList", GifttypeList);
			return "liebiao";
		}
		
		
		

		//购物车       
		
		//增加购物车里的东西
		@RequestMapping("/addBought")
		public String findByGiftid(Model model,@RequestParam(value="giftid") int id,HttpSession session) {
			
			//获取到登录时保存的用户对象
			User user = (User)session.getAttribute("user");
			//调用giftServiceImpl中的保存图书进入购物车的方法
			this.giftServiceImpl.saveShopping(user, id);
			//得到订单中的信息
			Set<Order> shoppingCartSet = (Set<Order>)session.getAttribute("shoppingcart");
			session.setAttribute("shoppingCartSet",shoppingCartSet);
			//计算订单中的总价格
			for(Order order : user.getOrderSet()) {
				double sum = 0;
				for(OrderDetail oderdetail : order.getOrderDetail()) {
					sum = sum + oderdetail.getTotalprice();
					session.setAttribute("totalPrice",sum);
				}
			}
			return"shop_car";
		}
		
		
		
		
		//删除购物车里的东西
		@RequestMapping("/delete")
		public String findByGiftId(@RequestParam("orderdetailid") int orderdetailid,HttpSession session) {
			User user = (User)session.getAttribute("user");
			Set<Order> orderSet = user.getOrderSet();
			//匹配成功，删除这个订单
			for(Order order:orderSet) {
				for(OrderDetail oderDetail : order.getOrderDetail()) {
					if(oderDetail.getId() == orderdetailid) {
						order.getOrderDetail().remove(oderDetail);
					}
				}
			}
			OrderDetail orderDetail = this.giftServiceImpl.findByOrderDetailid(orderdetailid);
			this.giftServiceImpl.deleteByOrderDetail(orderDetail);
			//重新存入订单
			Set<Order>shoppingCartSet = user.getOrderSet();
			session.setAttribute("shoppingCartSet", shoppingCartSet);
			//删除之后再次进行总价格的计算
			for(Order order : user.getOrderSet()) {
				double sum = 0;
				for(OrderDetail oderdetail : order.getOrderDetail()) {
					sum = sum + oderdetail.getTotalprice();
					session.setAttribute("totalPrice",sum);
				}
			}
			return "bought";
		}
		
		
		//管理员登录成功后执行控制器  跳转到index2页面
			@RequestMapping("/list3")
				public String findAllDetail(HttpSession session) {
					List<GiftDetail> detailList= this.giftServiceImpl.findAllDetail();
					session.setAttribute("detailList", detailList);
					return "index2";
			}
			
			
		//在后台添加商品
			@RequestMapping("/addgift")
			public String addGift(@RequestParam("giftname") String name,@RequestParam("gifttype") String typename,@RequestParam("giftprice") int price,
					HttpSession session) {
				//new一个GiftDetail对象，设置其中的属性值
				GiftDetail giftdetail = new GiftDetail();
				giftdetail.setName(name);
				giftdetail.setPrice(price);
				//调用giftServiceImplh中的方法保存数据
				this.giftServiceImpl.saveGift(giftdetail,typename);
				//遍历giftdetail表，得到图书信息列表
				List<GiftDetail> detailList =this.giftServiceImpl.findAllDetail();
				session.setAttribute("detailList", detailList);
				return "Index2";
			}
			
			
		//更新商品
			@RequestMapping("/updateGifts")
			public String updateGift(@RequestParam("giftname") String name, @RequestParam("giftprice") int price,@RequestParam("giftType") int typeid,HttpSession session ) {
				GiftDetail giftdetail = (GiftDetail) session.getAttribute("giftdetail");
				giftdetail.setName(name);
				giftdetail.setPrice(price);
				this.giftServiceImpl.updateGift(giftdetail,typeid);
				List<GiftDetail> detailList =this.giftServiceImpl.findAllDetail();
				session.setAttribute("detailList", detailList);
				return "Index2";				
			}
			
		//删除商品
			 @RequestMapping("/deletegift")
				public String deleteGift(@RequestParam("giftid") int giftid,HttpSession session) {
					 this.giftServiceImpl.deleteGift(giftid);
					 List<GiftDetail> detailList =this.giftServiceImpl.findAllDetail();
					 session.setAttribute("detailList", detailList);
					 return "Index2";
				}


			
}