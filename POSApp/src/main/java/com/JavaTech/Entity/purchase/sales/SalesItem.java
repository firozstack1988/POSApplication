package com.JavaTech.Entity.purchase.sales;

import java.util.List;

import com.JavaTech.Entity.items.Item;

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
@Table(name="SALES_ITEM")
public class SalesItem {
  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String inventoryCode;
	private String itemCode;
	private String customerName;
	@OneToMany(targetEntity=SalesItemList.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="itemCode", referencedColumnName="itemCode")
	private List<SalesItemList> saleItems; 
}
