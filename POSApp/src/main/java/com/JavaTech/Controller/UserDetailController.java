package com.JavaTech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaTech.Dto.ResponseModel;
import com.JavaTech.Dto.UserRequest;
import com.JavaTech.Entity.CustomerInfo;
import com.JavaTech.Entity.UserDetail;
import com.JavaTech.Repository.UserDetailRepo;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserDetailController {
    @Autowired
	private UserDetailRepo userDetailRepo;
	
	@PostMapping("/add")
	public ResponseEntity<UserDetail> save(@RequestBody @Valid UserRequest userRequest) {
		UserDetail userDetail= UserDetail.build(null, userRequest.getUserName(), userRequest.getEmail(), userRequest.getMobile(), userRequest.getNationality());
		return new ResponseEntity<>(userDetailRepo.save(userDetail),HttpStatus.CREATED) ;
	}
}
