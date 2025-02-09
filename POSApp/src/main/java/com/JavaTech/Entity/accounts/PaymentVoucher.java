package com.JavaTech.Entity.accounts;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PAYMENT_VOUCHER")
public class PaymentVoucher {
  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String tranSerialNum;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD-MM-YYYY")
	private Date tranDate;
	private double creditAmt;
	private double debitAmt;
	private String orderNo;
	private String accountGlCode;
	private String narration;
	private long voucherNum;
	
	
}
