package com.JavaTech.Entity.accounts;

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
@Table(name="CHART_OF_ACCOUNTS")
public class ChartOfAccounts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String accountName;
	private String accountHead;
	private String accountGLCode;
	private Boolean isAccountActive;
	private Boolean isAccountForPurchase;
	private Boolean isAccountForSales;

}
