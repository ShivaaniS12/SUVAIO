package com.th.test;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.th.Utility.DBConnection;
import com.th.daoimpl.UserDAOImpl;
import com.th.model.User;

//import java.sql.Connection;
//
//import org.eclipse.jdt.internal.compiler.parser.Scanner;
//
//import com.th.Utility.DBConnection;



public class Testing {
	public static void main(String[] args) {
		
//		Connection connection = DBConnection.getConnection();
//		System.out.println("Connection created");
		
		
//		ADD USER: 
//		
		Scanner scan =new Scanner(System.in);
		System.out.println("Enter the UserName");
		String userName = scan.next();
		System.out.println("Enter the Email");
		String email = scan.next();
		System.out.println("Enter the Password");
		String password = scan.next();
		System.out.println("Enter the Address");
		String address = scan.next();
		System.out.println("Enter the Role");
		scan.nextLine(); // consume leftover enter
		String role = scan.nextLine();
       
		
		User u = new User(0, userName, password, email, address, role, null, null);
		UserDAOImpl udao = new UserDAOImpl();
		udao.addUser(u);
		System.out.println("USER ADDED");
		
		
		
//		GET USER:
		
//		UserDAOImpl udao = new UserDAOImpl();
//		User u = udao.getUser(1);
//		System.out.println(u);
		
		
		
//		GET ALL USER:
		
//		UserDAOImpl udao = new UserDAOImpl();
//		List<User> alluser = udao.getAllUsers();
//		
//		for(User user : alluser) {
//			System.out.println(user);
//		}
		
		
//		UPDATE USER:
		
		
//		UserDAOImpl udao = new UserDAOImpl();
//		System.out.println("Enter the userId");
//		int id = scan.nextInt();
//		User u = udao.getUser(id);
//		System.out.println(u);
//		
//		u.setAddress("JP Nagar");
//		udao.updateUser(u);
//		System.out.println(u);

		
		
		
		
		
	}

}
