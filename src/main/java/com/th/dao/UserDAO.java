package com.th.dao;

import java.util.List;

import com.th.model.User;

public interface UserDAO {
	boolean addUser(User u);
	User getUser(int id);
	void updateUser(User u);
	void deleteUser(int userID);
	List<User> getAllUsers();
	User validateUser(String email,String password);
	void deactivateUser(int userId);
	boolean updateUserStatus(int userId, String status);
	int getTotalUsers();
	   List<User> getAllDeliveryAgents();
	   boolean isEmailExists(String email);
	   
	   User getUserById(int userId);
	   boolean updateProfile(User user);

}
