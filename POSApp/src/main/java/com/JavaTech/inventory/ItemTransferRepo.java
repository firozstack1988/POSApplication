package com.JavaTech.Repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.JavaTech.Entity.Inventory.ItemTransfer;

@Repository
public interface ItemTransferRepo extends JpaRepository<ItemTransfer,Long>{

}
