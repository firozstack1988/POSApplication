package com.JavaTech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.JavaTech.Entity.UserDetail;

public interface UserDetailRepo extends JpaRepository<UserDetail,Long>{
	
	public UserDetail findByUserNameAndEmail(@Param("userName") String userName,@Param("email") String email);

}
