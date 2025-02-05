package com.JavaTech.Repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JavaTech.Entity.items.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item,Long>{

}
