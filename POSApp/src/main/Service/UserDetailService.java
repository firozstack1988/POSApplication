package com.JavaTech.Service;

import com.JavaTech.Dto.UserRequest;
import com.JavaTech.Entity.UserDetail;

public interface UserDetailService {
  
	public String add(UserDetail userDetail);
	public UserDetail findById(Long id);
}
