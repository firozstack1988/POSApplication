package com.JavaTech.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.JavaTech.Repository.UserDetailRepo;

@Component
public class UserInfoService implements UserDetailsService{
  
	@Autowired
	private UserDetailRepo userDetailRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetail userDetail=userDetailRepo.findByUserName(username);
		UserDetail user=new UserDetail();
		if(userDetail==null) {
			throw new UsernameNotFoundException("User not Exits");
		}
		user.setPassword(userDetail.getPassword());
		user.setUserRole(userDetail.getUserRole());
		user.setUserName(userDetail.getUserName());
		return new UserInfoDetails(user);
		
	}

	 

}
