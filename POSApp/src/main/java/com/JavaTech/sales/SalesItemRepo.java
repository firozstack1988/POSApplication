package com.JavaTech.Repository.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JavaTech.Entity.purchase.sales.SalesItem;

@Repository
public interface SalesItemRepo extends JpaRepository<SalesItem,Long>{

}
