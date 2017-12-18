package com.bear.giftonline.gift.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bear.giftonline.entity.Gift;
import com.bear.giftonline.entity.GiftDetail;
import com.bear.giftonline.entity.GiftType;
import com.bear.giftonline.entity.Order;
import com.bear.giftonline.entity.OrderDetail;
import com.bear.giftonline.entity.Page;
import com.bear.giftonline.entity.User;
import com.bear.giftonline.gift.dao.GiftDaoImpl;


@Service
@Transactional(readOnly=false)
public class GiftServiceImpl {
		@Resource
		private GiftDaoImpl giftDaoImpl;//注入dao
		
		
		
		public List<Gift> listAll(){
			return this.giftDaoImpl.findAll();
		}
		
		
		
				
		/**
		 * 分页
		 */

		    public Page<Gift> queryForPage(int currentPage,int pageSize) {
		        Page<Gift> page = new Page<Gift>();       
		        //总记录数
		        int allRow = giftDaoImpl.getAllRowCount();
		        System.out.println(allRow);
		        //当前页开始记录
		        int offset = page.countOffset(currentPage,pageSize);  
		        //分页查询结果集
		        List<Gift> list = giftDaoImpl.queryForPage(offset, pageSize); 
		        page.setPageNo(currentPage);
		        page.setPageSize(pageSize);
		        page.setTotalRecords(allRow);
		        page.setList(list);  
		      
		        return page;
		    }
			public void Servicesave(Gift gift){
		 	  giftDaoImpl.save(gift);
		    }
		   public void Serviceupdate(Gift gift){
		 	  giftDaoImpl.update(gift);
		   }
		   public void Servicedelete(Gift gift){
		 	  giftDaoImpl.delete(gift);
		   }
		   public int ServicegetCount(){
		       return giftDaoImpl.getAllRowCount();
		   }
		   
		   
		 //详情页
			 public List <GiftDetail> findAllGiftDetail(int giftid){
			        return this.giftDaoImpl.findAllGiftDetail(giftid);
			    }




			//根据礼物类型的id来将礼物分类
		      public List <Gift> QueryByTypeid(int gifttypeid){
		          return giftDaoImpl.findAllType1(gifttypeid);
		      }
		      //根据礼物类型的id来查询礼物类型的信息
		      public List<GiftType> findAllType(int gifttypeid){
		      	return this.giftDaoImpl.findAllType(gifttypeid);
		      }
		     
		      
		      //搜索
		      public List <GiftDetail> findAllGift(String name){
			        return this.giftDaoImpl.findAllGiftDetail(name);
			    }
	    
			      
			  //购物车
				public void saveShopping(User user, int id) {
					 this.giftDaoImpl.saveShopping(user,id);
					
				}




				public List<GiftDetail> findAllDetail() {
					 return this.giftDaoImpl.findAllDetail();

				}
				
				//调用dao层deleteByOrderDetail方法  删除购物车的订单
			     public void deleteByOrderDetail(OrderDetail od) {
			    	 this.giftDaoImpl.deleteByOrderDetail(od);
			     }
			     
			   //调用dao层findByOrderDetailid方法
			      public OrderDetail findByOrderDetailid(int id) {
			    	  return this.giftDaoImpl.findByOrderDetailid(id);
			      }
			      
			      
			      
			   //后台添加图书
			      //调用dao层saveGift方法
			      public void saveGift(GiftDetail giftdetail,String typename) {
			     	 this.giftDaoImpl.save(giftdetail,typename);
			      }
			      
			      
			      //更新商品
			    //调用dao层updateGift方法
			      public void updateGift(GiftDetail giftdetail,int typeid) {
			     	 this.giftDaoImpl.updateGift(giftdetail,typeid);
			      }
			      
			      //删除商品
			      //调用dao层deleteGift方法
			      public void deleteGift(int giftid) {
			     	 this.giftDaoImpl.deleteByGiftDetailid(giftid);
			      }




}
