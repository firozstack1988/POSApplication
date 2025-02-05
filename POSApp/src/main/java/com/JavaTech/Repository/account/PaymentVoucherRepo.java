package com.JavaTech.Repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.JavaTech.Entity.accounts.PaymentVoucher;

@Repository
public interface PaymentVoucherRepo extends JpaRepository<PaymentVoucher,Long>{

}
