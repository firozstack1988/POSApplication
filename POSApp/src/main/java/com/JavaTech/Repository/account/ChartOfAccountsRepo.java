package com.JavaTech.Repository.account;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.JavaTech.Entity.accounts.ChartOfAccounts;

@Repository
public interface ChartOfAccountsRepo extends JpaRepository<ChartOfAccounts,Long>{
	
	public ChartOfAccounts findByAccountGLCode(@Param("accountGLCode") String accountGLCode);
	
	@Query("SELECT t FROM ChartOfAccounts t where t.isAccountForPurchase=:isAccountForPurchase and t.isAccountActive=:isAccountActive ")
	public ChartOfAccounts findChartOfAccountByAccountForPurchase(@Param("isAccountForPurchase") boolean isAccountForPurchase,@Param("isAccountActive") boolean isAccountActive);
	
	public Optional<ChartOfAccounts> findByIsAccountForPurchaseAndIsAccountActive(@Param("isAccountForPurchase") boolean isAccountForPurchase,@Param("isAccountForPurchase") boolean isAccountActive);
	public Optional<ChartOfAccounts> findByIsAccountForCashInHandAndIsAccountActive(@Param("isAccountForCashInHand") boolean isAccountForCashInHand,@Param("isAccountActive") boolean isAccountActive);

}
