package com.JavaTech.Service;

import java.util.List;

import com.JavaTech.Entity.UserDetail;

public interface UserDetailService {
  
	public String add(UserDetail userDetail);
	public UserDetail findById(Long id);
	public String partialUpdateUser(Long id,UserDetail userDetail);
	public String fullUpdate(Long id,UserDetail userDetail);
	public List<UserDetail> findUserList();
}
