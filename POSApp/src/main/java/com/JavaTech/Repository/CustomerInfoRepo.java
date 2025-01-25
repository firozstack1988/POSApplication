package com.JavaTech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.JavaTech.Entity.CustomerInfo;

@Repository
public interface CustomerInfoRepo extends JpaRepository<CustomerInfo,Long>{

}
