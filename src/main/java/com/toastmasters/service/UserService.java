package com.toastmasters.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.toastmasters.entity.UserEntity;

public interface UserService extends UserDetailsService {

	void saveUserDetail(UserEntity entity);
	boolean checkIfEmailIdExists(String emailId);
	List<UserEntity> getAllUsers();	
}
