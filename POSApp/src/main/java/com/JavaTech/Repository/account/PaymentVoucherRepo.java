package com.JavaTech.Repository.account;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.JavaTech.Entity.accounts.PaymentVoucher;

@Repository
public interface PaymentVoucherRepo extends JpaRepository<PaymentVoucher,Long>{
 
	@Query("Select max(t.voucherNum) from PaymentVoucher t where t.tranDate=:currDate")
    public Optional<Long> findMaxVoucherNo(@Param("currDate") Date currDate);
	
	 public Optional<PaymentVoucher> findTopVoucherNoByTranDate(@Param("currDate") Date currDate);

}
