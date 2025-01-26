package com.JavaTech.Service;

import com.JavaTech.Dto.UserRequest;
import com.JavaTech.Entity.UserDetail;

public interface UserDetailService {
  
	public String add(UserDetail userDetail);
	public UserDetail findById(Long id);
	public String partialUpdateUser(Long id,UserDetail userDetail);
	public String fullUpdate(Long id,UserDetail userDetail);
}
