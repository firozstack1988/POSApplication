package com.JavaTech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaTech.Dto.UserRequest;
import com.JavaTech.Entity.UserDetail;
import com.JavaTech.Entity.accounts.ChartOfAccounts;
import com.JavaTech.Service.account.ChartOfAccountsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/chart")
public class ChartOfAccountController {
     
	 @Autowired
     private ChartOfAccountsService chartOfAccountsService;
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> add(@RequestBody @Valid ChartOfAccounts chartOfAccounts) {			
		return new ResponseEntity<>(chartOfAccountsService.add(chartOfAccounts),HttpStatus.CREATED) ;	
	}
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<ChartOfAccounts>> chartOfAccountList() {			
		return new ResponseEntity<>(chartOfAccountsService.findAllChartOfAccount(),HttpStatus.CREATED) ;	
	}
}
