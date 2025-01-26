package com.JavaTech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaTech.Dto.UserRequest;
import com.JavaTech.Entity.UserDetail;
import com.JavaTech.Service.UserDetailServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserDetailController {
	 @Autowired
	 private UserDetailServiceImpl userDetailService;
	
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody @Valid UserRequest userRequest) {
		UserDetail userDetail= UserDetail.build(null, userRequest.getUserName(), userRequest.getEmail(), userRequest.getMobile(), userRequest.getNationality());
		return new ResponseEntity<>(userDetailService.add(userDetail),HttpStatus.CREATED) ;	
	}
	
	@GetMapping("/findUser/{id}")
	public ResponseEntity<UserDetail> findUser(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userDetailService.findById(id),HttpStatus.FOUND) ;	
	}
}
