package com.JavaTech.Entity.purchase;

import java.util.List;

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
@Table(name="PURCHASE_ORDER")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private String orderNo;
	private String orderDate;
	private String supplier;
	@OneToMany(targetEntity=OrderItem.class, fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="purchaseOrder_id",referencedColumnName="id")
	private List<OrderItem> orderItems;
}
