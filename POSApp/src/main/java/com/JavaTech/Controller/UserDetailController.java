package com.JavaTech.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaTech.Dto.UserRequest;
import com.JavaTech.Entity.UserAuthenticate;
import com.JavaTech.Entity.UserDetail;
import com.JavaTech.ExceptionHandle.NoSuchElementFoundException;
import com.JavaTech.JwtToken.JwtUtils;
import com.JavaTech.Service.UserDetailServiceImpl;
import com.JavaTech.Utils.JwtResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserDetailController {
	 @Autowired
	 private UserDetailServiceImpl userDetailService;
	 @Autowired
	 private ModelMapper modelMapper;
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 @Autowired
	 private UserDetailsService userDetailsService;
	 @Autowired
	 AuthenticationManager authenticationManager; 
	 @Autowired
	 private JwtUtils jwtUtils;
	 
	 @PostMapping("/auth")
	 public ResponseEntity<JwtResponse> auth(@RequestBody UserAuthenticate userAuthenticate) {
		 JwtResponse res=new JwtResponse();
		final UserDetails loadedUser=userDetailsService.loadUserByUsername(userAuthenticate.getUserName());
		Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthenticate.getUserName(),userAuthenticate.getPassword()));
		if(authentication.isAuthenticated()) {	
			System.out.print("token="+jwtUtils.generateToken(loadedUser));
			return new ResponseEntity<>(new JwtResponse(jwtUtils.generateToken(loadedUser),"") ,HttpStatus.ACCEPTED);
		}
		else
			throw new NoSuchElementFoundException("Invalid User");
		
	 }
	 
	 @GetMapping("/welcome")
	 public String findUser() {
		 return "welcome page";	
		}
	
	@PostMapping("/add")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> add(@RequestBody @Valid UserRequest userRequest) {
		UserDetail userDetail= UserDetail.build(null, userRequest.getUserName(),passwordEncoder.encode(userRequest.getPassword()), 
		userRequest.getEmail(), userRequest.getMobile(), userRequest.getNationality(),userRequest.getUserRole());
		return new ResponseEntity<>(userDetailService.add(userDetail),HttpStatus.CREATED) ;	
	}
	
	@GetMapping("/findUser/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<UserDetail> findUser(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userDetailService.findById(id),HttpStatus.FOUND) ;	
	}
	
	@PatchMapping("modify/{id}")
	public ResponseEntity<String> partialUpdateUser(@PathVariable("id") Long id,@RequestBody UserRequest userRequest) {
		UserDetail userDetail=modelMapper.map(userRequest, UserDetail.class);
		return new ResponseEntity<>(userDetailService.partialUpdateUser(id,userDetail),HttpStatus.CREATED);		
	}
	
	@PutMapping("/modify/{id}")
	public ResponseEntity<String> fullUpdate(@PathVariable("id") Long id,@RequestBody UserRequest userRequest) {
		UserDetail userDetail=modelMapper.map(userRequest, UserDetail.class);
		return new ResponseEntity<>(userDetailService.fullUpdate(id,userDetail),HttpStatus.CREATED); 			
	}
}
