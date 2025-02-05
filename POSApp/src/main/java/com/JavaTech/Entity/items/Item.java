package com.JavaTech.Entity.items;

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
@Table(name="ITEM")
public class Item {
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String itemCategory;
	private String subCategory;
	private String model;
	private String itemDesc;
	private String itemCode;
	private Double itemPrice;
}
