package com.toastmasters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.toastmasters.entity.UserEntity;
import com.toastmasters.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String userEmailId) throws UsernameNotFoundException {
		List<UserEntity> list = userRepository.findByEmailId(userEmailId);
		if(CollectionUtils.isEmpty(list))
			throw new UsernameNotFoundException("MGS: No user found with this email id.");
		return list.get(0);
	}
	
	@Override
	public void saveUserDetail(UserEntity entity) {
		entity.setPassword(BCrypt.hashpw(entity.getPassword(),BCrypt.gensalt(4)));
		entity.setRoles("ROLE_USER");
		userRepository.save(entity);
		log.info("user details saved successfully.");
	}

	@Override
	public List<UserEntity> getAllUsers(){
		return userRepository.findAll();
	}
	
	@Override
	public boolean checkIfEmailIdExists(String emailId) {
		return !CollectionUtils.isEmpty(userRepository.findByEmailId(emailId));
	}
	
}
