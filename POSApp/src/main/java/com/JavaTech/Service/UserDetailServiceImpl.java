package com.JavaTech.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaTech.Entity.UserDetail;
import com.JavaTech.ExceptionHandle.ElementAlreadyExistsException;
import com.JavaTech.ExceptionHandle.NoSuchElementFoundException;
import com.JavaTech.Repository.UserDetailRepo;
import com.JavaTech.Utils.ExceptionMessage;

@Service
public class UserDetailServiceImpl implements UserDetailService{
	
	@Autowired
	private UserDetailRepo userDetailRepo;

	@Override
	public String add(UserDetail userDetail) {
		UserDetail existUser=userDetailRepo.findByUserNameAndEmail(userDetail.getUserName(), userDetail.getEmail());
		if(existUser==null) {
			userDetailRepo.save(userDetail);
			return ExceptionMessage.MSG_SUCCESS;
		}
		else
			throw new ElementAlreadyExistsException("User already exist");	
	}

	@Override
	public UserDetail findById(Long id) {
		Optional<UserDetail> userDetail=userDetailRepo.findById(id);
		if(userDetail.isPresent()) 
			return userDetail.get();
		else
			throw new NoSuchElementFoundException("User not found");		
	}

	@Override
	public String partialUpdateUser(Long id,UserDetail userDetail) {
		Optional<UserDetail> existUser=userDetailRepo.findById(id);
		if(existUser.isPresent()) {
			existUser.get().setMobile(userDetail.getMobile());
			userDetailRepo.save(existUser.get());
			return ExceptionMessage.MSG_UPDATE;
		}		 
		else
			throw new NoSuchElementFoundException("User not found");	
	}

	@Override
	public String fullUpdate(Long id, UserDetail userDetail) {
		Optional<UserDetail> existUser=userDetailRepo.findById(id);
		if(existUser.isPresent()) {
			userDetailRepo.save(existUser.get());
			return ExceptionMessage.MSG_UPDATE;
		}		 
		else
			throw new NoSuchElementFoundException("User not found");	
	}

	@Override
	public List<UserDetail> findUserList() {		 
		return userDetailRepo.findAll();
	}

}
