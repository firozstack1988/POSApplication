package com.JavaTech.Controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaTech.Email.EmailDetail;
import com.JavaTech.Email.EmailDetailService;
import com.JavaTech.Entity.purchase.PurchaseOrder;
import com.JavaTech.Service.purchase.PurchaseOrderService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/purchase")
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private EmailDetailService emailDetailService;

	@PostMapping("/add")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> add(@RequestBody PurchaseOrder purchaseOrder) {		
		return new ResponseEntity<>(purchaseOrderService.add(purchaseOrder),HttpStatus.CREATED) ;	
	}
	
	@PostMapping("/email")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> email(@RequestBody EmailDetail emailDetail) {		
		return new ResponseEntity<>(emailDetailService.sendSimpleMail(emailDetail),HttpStatus.CREATED);	
	}
	
	@PostMapping("/emailAttach")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> emailAttach(@RequestBody EmailDetail emailDetail) throws MessagingException, FileNotFoundException {		
		return new ResponseEntity<>(emailDetailService.sendMailWithAttachment(emailDetail),HttpStatus.CREATED);	
	}
	
}
