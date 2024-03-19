package org.jsp.hibernate.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.hibernate.dto.User;

import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

public class userdao {
	static EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	
	public static User saveUser(User u) {
		EntityTransaction t=manager.getTransaction();
		manager.persist(u);
		t.begin();
		t.commit();
		return u;
		
	}
	
	public static User updateUser(User u) {
		User userdb=findUserByid(u.getId());
		if(userdb!=null) {
			userdb.setName(u.getName());
			userdb.setPhone(u.getPhone());
			userdb.setEmail(u.getEmail());
			userdb.setAge(u.getAge());
			userdb.setGender(u.getGender());
			EntityTransaction t=manager.getTransaction();
			t.begin();
		    t.commit();
			return userdb;
		}else {
			return null;
		}
	}
	
	public static User findUserByid(int id) {
		return manager.find(User.class, id);
	}
	  
	public static User verifyPhNPass(long phone,String password) {
		Query q=manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
		return (User)q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	
	public static User verifyEmailNPass(String email,String password) {
		Query q=manager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
		return (User)q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}

}
