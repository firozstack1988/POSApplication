package com.JavaTech.Entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoDetails implements UserDetails{

	/*private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public UserInfoDetails(UserDetail userDetail) {
		this.userName=userDetail.getUserName();
		this.password=userDetail.getPassword();
		this.authorities=Arrays.stream(userDetail.getUserRole().split(","))
		 .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}*/
	
	private static final long serialVersionUID = 1L;
	private UserDetail userDetail;
	public UserInfoDetails(UserDetail userDetail) {
		super();
		this.userDetail=userDetail;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(userDetail.getUserRole()));
	}

	@Override
	public String getPassword() {
		return userDetail.getPassword();
	}

	@Override
	public String getUsername() {
		return userDetail.getUserName();
	}

}
