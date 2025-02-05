package com.JavaTech.Entity.Inventory;

import java.util.Date;
import java.util.List;

import com.JavaTech.Entity.CustomerInfo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ITEM_TRANSFER")
public class ItemTransfer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String fromInventory;
	private String toInventory;
	private String transactionCode;
	private Date tranDate;
	@OneToMany(targetEntity=TransferItemList.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="transactionCode",referencedColumnName="transactionCode")
	private List<TransferItemList> items;
	
}
