package org.jsp.hibernate.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.hibernate.dao.Productdao;
import org.jsp.hibernate.dao.userdao;
import org.jsp.hibernate.dto.Product;
import org.jsp.hibernate.dto.User;

public class UserNProductController {
	public static Scanner sc=new Scanner(System.in);
	private static userdao udao=new userdao();
	private static Productdao pdao=new Productdao();
	
	public static void main(String[] args) {
		System.out.println("1.save user");
		System.out.println("2.update user");
		System.out.println("3. find user by id");
		System.out.println("4. verify user by phone and password");
		System.out.println("5. verify user by email and password");
		System.out.println("6. add product");
		System.out.println(" 7.update product");
		System.out.println("8. find product by user id");
		System.out.println("9.find product by user phone and password");
		System.out.println("10. find product by brand and category");
		
		switch (sc.nextInt()) {
		case 1:{
			saveuser();
			break;
		}
		case 2:{
			updateuser();
			break;
		}
		
		case 3:{
			finduser() ;
			break;
		}
		case 4:{
			verifyuserPNP();
			break;
		}
		case 5:{
			verifyuserENP() ;
			break;
		}
		
		case 6:{
			addproduct() ;
			break;
		}
		case 7:{
			updateproduct();
			break;
		}
		case 8:{
			findproductByUId() ;
			break;
		}
		case 9:{
			findproByUPNP();
			break;
		}
		case 10:{
			findProByBNC();
			break;
		}

		default:{
			System.out.println("invalid option");
		}
		}
	}
	
	public static void saveuser() {
		User u=new User();
		System.out.println("enter name,phone,email,age,gender, and password");
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setAge(sc.nextInt());
		u.setGender(sc.next());
		u.setPassword(sc.next());
		u=udao.saveUser(u);
		if(u!=null) {
			System.out.println("user saved");
		}else {
			System.out.println("user saved");
		}
		}
	
	public static void updateuser() {
		User u=new User();
		System.out.println("enter id to update");
		int id=sc.nextInt();
		System.out.println("enter name.phone,email,age,gender to update  user");
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setAge(sc.nextInt());
		u.setGender(sc.next());
		u.setPassword(sc.next());
		u=udao.updateUser(u);
		if(u!=null) {
			System.out.println("user updated");
		}else {
			System.out.println("invalid");
		}
	}
	
	public static void finduser() {
		System.out.println("enter user id to find");
		int id=sc.nextInt();
		User u=udao.findUserByid(id);
		if(u!=null) {
			System.out.println(u.getId());
			System.out.println(u.getName());
			System.out.println(u.getPhone());
			System.out.println(u.getEmail());
			System.out.println(u.getAge());
			System.out.println(u.getGender());
			System.out.println("-----------");
		}else {
			System.out.println("invalid");
		}
	}
	
	public static void verifyuserPNP() {
		System.out.println("enter phone and password to verify user");
		long phone=sc.nextLong();
		String password=sc.next();
		User u=udao.verifyPhNPass(phone, password);
		if(u!=null) {
			System.out.println(u.getId());
			System.out.println(u.getName());
			System.out.println(u.getPhone());
			System.out.println(u.getEmail());
			System.out.println(u.getAge());
			System.out.println(u.getGender());
			System.out.println("-----------");
		}else {
			System.out.println("invalid");
		}
	
	}
	
	public static void verifyuserENP() {
		System.out.println("enter email and password to verify user");
		String email=sc.next();
		String password=sc.next();
		User u=udao.verifyEmailNPass(email, password);
		if(u!=null) {
			System.out.println(u.getId());
			System.out.println(u.getName());
			System.out.println(u.getPhone());
			System.out.println(u.getEmail());
			System.out.println(u.getAge());
			System.out.println(u.getGender());
			System.out.println("-----------");
		}else {
			System.out.println("invalid");
		}
	}
	
	public static void addproduct() {
		System.out.println("enter user id to add product");
		int user_id=sc.nextInt();
		System.out.println("enter product name,brand,category,decription and cost");
		Product p=new Product();
		
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setDescription(sc.next());
		p.setCost(sc.nextDouble());
		p=pdao.saveProduct(p, user_id);
		System.out.println("=------");
	}
	
	public static void updateproduct() {
		System.out.println("enter  id to update product");
		int user_id=sc.nextInt();
		System.out.println("enter product name,brand,category,decription and cost");
		Product p=new Product();
		p.setName(sc.next());
		p.setBrand(sc.next());
		p.setCategory(sc.next());
		p.setDescription(sc.next());
		p.setCost(sc.nextDouble());
		p=pdao.updateProduct(p);
		if(p!=null) {
			System.out.println("prduct updated");
		}else {
			System.out.println("invalid");
		}
	}
	
	public static void findproductByUId() {
		System.out.println("enter user id to find product");
		int user_id=sc.nextInt();
		List<Product> p=pdao.findProductByuserid(user_id);
		for(Product p1:p) {
		System.out.println(p1.getName());
		System.out.println(p1.getBrand());
		System.out.println(p1.getCategory());
		System.out.println(p1.getDescription());
		System.out.println(p1.getCost());
		System.out.println("-------");
	}}
	
	public static void findproByUPNP() {
		System.out.println("enter user phone n password to find product");
		long phone=sc.nextLong();
		String password=sc.next();
		List<Product> p=pdao.findProByUserPhNPass(phone, password);
		for(Product p1:p) {
		System.out.println(p1.getName());
		System.out.println(p1.getBrand());
		System.out.println(p1.getCategory());
		System.out.println(p1.getDescription());
		System.out.println(p1.getCost());
		System.out.println("-------");
	}}
	
	public static void findProByBNC() {
		System.out.println("enter product brand and category to find product");
		String brand=sc.next();
		String category=sc.next();
		List<Product> p=pdao.findProByBrandNcatogory(brand, category);
		for(Product p1:p) {
		System.out.println(p1.getName());
		System.out.println(p1.getBrand());
		System.out.println(p1.getCategory());
		System.out.println(p1.getDescription());
		System.out.println(p1.getCost());
		System.out.println("-------");
	}
	}

}
