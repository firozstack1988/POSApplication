package com.JavaTech;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.JavaTech.Entity.accounts.ChartOfAccounts;
import com.JavaTech.Repository.account.ChartOfAccountsRepo;
import com.JavaTech.Service.account.ChartOfAccountsService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
@SpringBootTest
public class ChartOfAccountTest {
	
	@Autowired
	private ChartOfAccountsService chartOfAccountService;
	
	@MockBean
	private ChartOfAccountsRepo chartOfAccountsRepo;
	
	 @Test
	public void findAllChartOfAccount() {
		ChartOfAccounts chart1=ChartOfAccounts.build(null, "abc", "Asset", "101", true, true, false, false);
		ChartOfAccounts chart2=ChartOfAccounts.build(null, "xyz", "Current-Asset", "201", true, true, false, false);
		Mockito.when(chartOfAccountsRepo.findAll()).thenReturn(Stream.of(chart1,chart2).collect(Collectors.toList()));
		assertEquals(2,chartOfAccountService.findAllChartOfAccount().size());
	}

}
