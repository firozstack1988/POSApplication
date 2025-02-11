package com.JavaTech.Service.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaTech.Entity.accounts.ChartOfAccounts;
import com.JavaTech.ExceptionHandle.ElementAlreadyExistsException;
import com.JavaTech.Repository.account.ChartOfAccountsRepo;
import com.JavaTech.Utils.ExceptionMessage;

@Service
public class ChartOfAccountsServiceImpl implements ChartOfAccountsService{

	@Autowired
	private ChartOfAccountsRepo chartOfAccountsRepo;
	
	@Override
	public String add(ChartOfAccounts chartOfAccounts) {
		ChartOfAccounts existChartOfAccounts=chartOfAccountsRepo.findByAccountGLCode(chartOfAccounts.getAccountGLCode());
			if(existChartOfAccounts==null) {
				chartOfAccountsRepo.save(chartOfAccounts);
				 return ExceptionMessage.MSG_SUCCESS;
			}
			else
		      throw new ElementAlreadyExistsException("ChartOfAccounts already exist");	
	}

	@Override
	public List<ChartOfAccounts> findAllChartOfAccount() {		
		return chartOfAccountsRepo.findAll();
	}

}
