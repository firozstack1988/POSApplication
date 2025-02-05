package com.JavaTech.Repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.JavaTech.Entity.accounts.ChartOfAccounts;

@Repository
public interface ChartOfAccountsRepo extends JpaRepository<ChartOfAccounts,Long>{
	
	public ChartOfAccounts findByAccountGLCode(@Param("accountGLCode") String accountGLCode);

}
