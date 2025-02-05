package com.JavaTech.Repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.JavaTech.Entity.purchase.PurchaseOrder;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder,Long>{
	@Query("select p from PurchaseOrder p where p.orderNo=:orderNo")
	public PurchaseOrder findByOrder(@Param("orderNo") String orderNo);

}
