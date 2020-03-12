package com.gbce.stock.sssm.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbce.stock.sssm.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

}
