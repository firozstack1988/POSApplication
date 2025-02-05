package com.JavaTech.Entity.Inventory;

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
@Table(name="ITEM_TRANSFER_LIST")
public class TransferItemList {
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String itemCode;
	private String itemDesc;
	private long quantity;
	private Double price;
}
