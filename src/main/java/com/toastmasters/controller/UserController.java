package com.toastmasters.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toastmasters.bean.ResponseBean;
import com.toastmasters.entity.UserEntity;
import com.toastmasters.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseBean> register(@RequestBody UserEntity entity){
		
		if(userService.checkIfEmailIdExists(entity.getEmailId()))
			return new ResponseEntity<>(new ResponseBean("Email Id Already Exists"),HttpStatus.OK);
		
		userService.saveUserDetail(entity);
		return new ResponseEntity<>(new ResponseBean("Success"),HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserEntity>> listOfUsers(){
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String justTest() {
		return "Yes I am working";
	}
}
