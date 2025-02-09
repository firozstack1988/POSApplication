package com.JavaTech.Service.purchase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.JavaTech.Entity.accounts.ChartOfAccounts;
import com.JavaTech.Entity.accounts.PaymentVoucher;
import com.JavaTech.Entity.purchase.PurchaseOrder;
import com.JavaTech.ExceptionHandle.ElementAlreadyExistsException;
import com.JavaTech.ExceptionHandle.NoSuchElementFoundException;
import com.JavaTech.Repository.account.ChartOfAccountsRepo;
import com.JavaTech.Repository.account.PaymentVoucherRepo;
import com.JavaTech.Repository.purchase.PurchaseOrderRepo;
import com.JavaTech.Utils.ExceptionMessage;

@Component
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
    
	@Autowired
	private PurchaseOrderRepo purchaseOrderRepo;
	@Autowired
	private PaymentVoucherRepo paymentVoucherRepo;
	 
	@Autowired
	private ChartOfAccountsRepo chartOfAccountsRepo;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public String add(PurchaseOrder purchaseOrder) throws ParseException {
		PurchaseOrder existOrder=purchaseOrderRepo.findByOrder(purchaseOrder.getOrderNo());
		if(existOrder==null) {
			purchaseOrderRepo.save(purchaseOrder);
			
			payment(purchaseOrder);
			
			return ExceptionMessage.MSG_SUCCESS;
		}
		else
			throw new ElementAlreadyExistsException("Duplicate purchase order");	
	}
			
	private String payment(PurchaseOrder purchaseOrder) {
		 PaymentVoucher paymentVoucher1=new PaymentVoucher();
		 PaymentVoucher paymentVoucher2=new PaymentVoucher();

		 Optional<ChartOfAccounts> chartOfAccounts=chartOfAccountsRepo.findByIsAccountForPurchaseAndIsAccountActive(true, true);
		if(chartOfAccounts.isPresent()) 
			paymentVoucher1.setAccountGlCode(chartOfAccounts.get().getAccountGLCode());	
		else
			throw new NoSuchElementFoundException("GL Code not found");
		
		//Credit block
		paymentVoucher1.setTranSerialNum(genSerialNo(purchaseOrder));
		paymentVoucher1.setOrderNo(purchaseOrder.getOrderNo());
		paymentVoucher1.setTranDate(purchaseOrder.getOrderDate());
		paymentVoucher1.setNarration("Payment for purchase");
		paymentVoucher1.setCreditAmt(purchaseOrder.getTotalAmount());
		paymentVoucher1.setDebitAmt(0);
		paymentVoucherRepo.save(paymentVoucher1);
		
		 Optional<ChartOfAccounts> chartOfAccounts2=chartOfAccountsRepo.findByIsAccountForCashInHandAndIsAccountActive(true, true);
			if(chartOfAccounts2.isPresent()) 
				paymentVoucher2.setAccountGlCode(chartOfAccounts2.get().getAccountGLCode());	
			else
			  throw new NoSuchElementFoundException("GL Code not found");
		
		//Debit block
		paymentVoucher2.setTranSerialNum(genSerialNo(purchaseOrder));
		paymentVoucher2.setOrderNo(purchaseOrder.getOrderNo());
		paymentVoucher2.setTranDate(purchaseOrder.getOrderDate());
		paymentVoucher2.setNarration("Payment for purchase");
		paymentVoucher2.setCreditAmt(0);
		paymentVoucher2.setDebitAmt(purchaseOrder.getTotalAmount());
		paymentVoucherRepo.save(paymentVoucher2);
		return "";
	}
	
	private String genSerialNo(PurchaseOrder purchaseOrder) {
		long paymentVoucherNo=0;
		Optional<Long> voucher=paymentVoucherRepo.findMaxVoucherNo(purchaseOrder.getOrderDate());
		if(voucher.isPresent()) {
			long maxVoucher=voucher.get();
			paymentVoucherNo=maxVoucher+1;
			return String.valueOf(paymentVoucherNo);
		}
		else
			throw new NoSuchElementFoundException("No not found");
		
	}

}
