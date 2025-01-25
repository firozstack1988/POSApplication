package com.JavaTech.Controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JavaTech.Dto.CustomerWrapper;
import com.JavaTech.Entity.CustomerInfo;
import com.JavaTech.Repository.CustomerInfoRepo;

@RestController
@RequestMapping("/customer")
public class CustomerInfoController {

	@Autowired
	private CustomerInfoRepo customerInfoRepo;
	@Autowired
	private ModelMapper modelMapper;
		
	@PutMapping("modify/{id}")
	public CustomerInfo updateCustomer(@PathVariable("id") Long id,@RequestBody CustomerWrapper customerWrapper) {
		CustomerInfo customerInfo=modelMapper.map(customerWrapper, CustomerInfo.class);
		return customerInfoRepo.save(customerInfo);
		
	}
	
	@PatchMapping("/modify/{id}")
	public CustomerInfo update(@PathVariable("id") Long id,@RequestBody CustomerWrapper customerWrapper) {
		Optional<CustomerInfo> customer=customerInfoRepo.findById(id);
	    if(customer.isPresent()) {
	    	customer.get().setCustName(customerWrapper.getCustName());
	    	return customerInfoRepo.save(customer.get());
	     }
	    else
	       return null;
			
	}
}
