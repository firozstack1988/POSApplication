package com.JavaTech.Service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JavaTech.Entity.UserDetail;
import com.JavaTech.Entity.purchase.PurchaseOrder;
import com.JavaTech.ExceptionHandle.ElementAlreadyExistsException;
import com.JavaTech.Repository.purchase.PurchaseOrderRepo;
import com.JavaTech.Utils.ExceptionMessage;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
    
	@Autowired
	private PurchaseOrderRepo purchaseOrderRepo;
	@Override
	public String add(PurchaseOrder purchaseOrder) {
		PurchaseOrder existOrder=purchaseOrderRepo.findByOrder(purchaseOrder.getOrderNo());
		if(existOrder==null) {
			purchaseOrderRepo.save(purchaseOrder);
			return ExceptionMessage.MSG_SUCCESS;
		}
		else
			throw new ElementAlreadyExistsException("Duplicate purchase order");	
	}

}
