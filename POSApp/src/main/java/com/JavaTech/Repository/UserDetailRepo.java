package com.JavaTech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JavaTech.Entity.UserDetail;

public interface UserDetailRepo extends JpaRepository<UserDetail,Long>{

}
