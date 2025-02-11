package com.JavaTech.Service.account;

import java.util.List;

import com.JavaTech.Entity.accounts.ChartOfAccounts;

public interface ChartOfAccountsService {

	public String add(ChartOfAccounts chartOfAccounts);
	public List<ChartOfAccounts> findAllChartOfAccount();
}
