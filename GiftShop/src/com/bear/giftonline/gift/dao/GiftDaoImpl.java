package com.bear.giftonline.gift.dao;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.bear.giftonline.entity.Gift;
import com.bear.giftonline.entity.GiftDetail;
import com.bear.giftonline.entity.GiftType;
import com.bear.giftonline.entity.Order;
import com.bear.giftonline.entity.OrderDetail;
import com.bear.giftonline.entity.User;
@Repository
public class GiftDaoImpl {

		@Resource
		private SessionFactory sessionFactory; //注入sessionfactory
		
		public List<Gift> findAll(){
			Query q = this.sessionFactory.getCurrentSession().createQuery("from Gift");
			return q.list();
		}
		
		
		
		/*
		 * 分页
		 */
		@SuppressWarnings("unchecked")
		public List<Gift> queryForPage(int offset, int length) {
			Query query= (Query) sessionFactory.getCurrentSession().createQuery("from Gift");    
		    query.setFirstResult(offset);
		    query.setMaxResults(length);            
		    return query.list(); 
		  }
		 public void save(Gift gift){
		     sessionFactory.getCurrentSession().save(gift);
		 }
		 public void update(Gift gift) {   
		     sessionFactory.getCurrentSession().update(gift);
		 }
		     public void delete(Gift gift) {      
		     sessionFactory.getCurrentSession().delete(gift);
		 }
		 /*查询记录总数*/
		 public int getAllRowCount(){
		     int count=((Long) sessionFactory.getCurrentSession()
		                .createQuery( "select count(*) from Gift").iterate().next()).intValue();
		     return count;  
		     
		 }

		/*
		 * 详情页
		 */
		
			
		public List<GiftDetail> findAllGiftDetail(int giftid) {
				Query query= (Query) sessionFactory.getCurrentSession().createQuery("from GiftDetail where giftid=?");    
				query.setParameter(0, giftid);          
			    return query.list(); 
		}


		
		public List<GiftType> findAllType(int gifttypeid) {
			Query query= (Query) sessionFactory.getCurrentSession().createQuery("from GiftType where gifttypeid=?");    
			query.setParameter(0, gifttypeid);          
		    return query.list(); 
		}
		public List<Gift> findAllType1(int gifttypeid) {
			Query query= (Query) sessionFactory.getCurrentSession().createQuery("from Gift where gifttypeid=?");    
			query.setParameter(0, gifttypeid);          
		    return query.list(); 
		}
		
		
		//搜索
		public List<GiftDetail> findAllGiftDetail(String name) {
			Query query= (Query) sessionFactory.getCurrentSession().createQuery("from GiftDetail where name=?");    
			query.setParameter(0, name);          
		    return query.list(); 
	}
		
	
		
		//购物车
		 public GiftDetail findAllDetail(int giftid) {
			 return this.sessionFactory.getCurrentSession().get(GiftDetail.class,giftid);
		 }
		 public void saveShopping(User user,int id) {
		    	//得到一个session对象
		    	Session session = sessionFactory.getCurrentSession();
		    	/**
		    	 * 创建用户的订单，如果用户的订单为空，则创建一个订单
		    	 * 如果不为空的话，就得到用户的所有订单
		    	 */
				Order order = null;
				if(user.getOrderSet().size()<=0) {
					order = new Order();
				}else {
					Set<Order> orderset = user.getOrderSet();
					for(Order o :orderset) {
						order = o;
					}
				}
					//建立用户和订单之间的联系
					order.setUser(user);
					user.getOrderSet().add(order);
					//更新用户
					session.update(user);
					//保存用户的订单信息
					session.save(order);
					//将id传进findAllDetail1方法中，得到查到的giftdetail对象
					GiftDetail giftdetail = this.findAllDetail(id);
					//创建一个订单详情
					OrderDetail orderdetail = new OrderDetail();
					//在订单详情中设置用户的信息和礼物的详细信息
					orderdetail.setUsername(user.getUsername());
					orderdetail.setGiftname(giftdetail.getName());
					orderdetail.setGiftcount(giftdetail.getGiftcount());
					orderdetail.setPrice(giftdetail.getPrice());
					
					//建立order与orderdetail的关联
					orderdetail.setOrder(order);
					order.getOrderDetail().add(orderdetail);
					
					
					//保存订单详情更新订单信息
					session.save(orderdetail);
					session.update(order);
					
			}
		

		 
		
		 //删除订单详情中的订单信息
			public void deleteByOrderDetail(OrderDetail od) {
				Session session = this.sessionFactory.getCurrentSession();
				session.delete(od);
			}  
		 
		 public List<GiftDetail> findAllDetail(){
				Query q = this.sessionFactory.getCurrentSession().createQuery("from GiftDetail");
				return q.list();
			}

		//根据id得到OrderDetail对象
			public OrderDetail findByOrderDetailid(int id) {
				return this.sessionFactory.getCurrentSession().get(OrderDetail.class, id);
			}
			
			
			
			//通过后台添加商品
			
			//根据类型名字返回一个GiftType类型的对象
			public GiftType findAllType(String typename) {
				Session session = this.sessionFactory.getCurrentSession();
				Query q = session.createQuery("from GiftType where name=?");//gifttype数据库里的名字
				q.setParameter(0, typename);
				return (GiftType) q.uniqueResult();
			}

		
			public void save(GiftDetail giftdetail,String typename) {
				
				GiftType bt = this.findAllType(typename);
				
				Gift gift = new Gift();
				gift.setName(giftdetail.getName());
				gift.setPrice(giftdetail.getPrice());
				gift.setGifttype(bt);
				
				//gift  和giftDetail 建立关联
				gift.setGiftdetail(giftdetail);
				giftdetail.setGift(gift);
				//保存gift和giftdetail
				Session session = this.sessionFactory.getCurrentSession();
				session.save(gift);
				session.save(giftdetail);
			}
			
			
			
			//更新商品
			
			//查商品类型
			public GiftType findAllType2(int typeid) {
				// TODO Auto-generated method stub
				Session session = this.sessionFactory.getCurrentSession();
				Query q = session.createQuery("from GiftType where typeid=?");
				q.setParameter(0, typeid);
				return (GiftType) q.uniqueResult();
			}

			public void updateGift(GiftDetail giftdetail,int typeid) {
				//得到通过typeid得到GiftType类型的对象
				GiftType bt = this.findAllType2(typeid);
				//通过giftdeatail得到Gift对象  不能去new 一个Gift对象，那样是空的对象，里面没有数据
				Gift gift = giftdetail.getGift();
				gift.setName(giftdetail.getName());
				gift.setPrice(giftdetail.getPrice());
				gift.setImg(giftdetail.getImg1());
				gift.setGifttype(bt);
				//gift和giftdetail 建立 关联关系
				gift.setGiftdetail(giftdetail);
				giftdetail.setGift(gift);
				//更新gift和giftdetail 表中的数据
				Session session = this.sessionFactory.getCurrentSession();
				session.update(gift);
				session.update(giftdetail);
			}
			
			
			
			//删除商品
			 public void deleteByGiftDetailid(int giftid) {
					Session session = this.sessionFactory.getCurrentSession();
					Query q = session.createQuery("from GiftDetail where giftid=?");
					q.setParameter(0, giftid);
					GiftDetail bd = (GiftDetail) q.uniqueResult();
					
					session.delete(bd);
				}

}
