package org.jsp.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hibernate.dto.Product;
import org.jsp.hibernate.dto.User;

public class Productdao {
	static EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public static Product saveProduct(Product products,int user_id) {
		User u=manager.find(User.class, user_id);
	
		u.getProducts().add(products);
		products.setUsers(u);
		
			EntityTransaction t=manager.getTransaction();
			manager.persist(products);
			t.begin();
			t.commit();
			return products;
		
		}
	
		
		public static Product updateProduct(Product products) {
			Product productdb=manager.find(Product.class, products.getId());
			if(productdb!=null) {
				productdb.setId(products.getId());
				productdb.setName(products.getName());
				productdb.setBrand(products.getBrand());
				productdb.setCategory(products.getCategory());
				productdb.setDescription(products.getDescription());
				EntityTransaction t=manager.getTransaction();
				t.begin();
			    t.commit();
				return productdb;
			}else {
				return null;
			}
		}
		
		public static List<Product> findProductByuserid(int user_id){
			Query q=manager.createQuery("select u.products from User u where u.id=?1");
			
			q.setParameter(1, user_id);
			return q.getResultList();
		}
		
		public static List<Product> findProByUserPhNPass(long phone,String password){
        Query q=manager.createQuery("select u.products from User u where u.phone=?1 and u.password=?2");
			
			q.setParameter(1, phone);
			q.setParameter(2, password);
			return q.getResultList();
		}
		
		public static List<Product> findProByBrandNcatogory(String brand,String category){
	        Query q=manager.createQuery("select p from Product p where p.brand=?1 and p.category=?2");
				
				q.setParameter(1, brand);
				q.setParameter(2, category);
				return q.getResultList();
			}
	}


