package com.gbce.stock.sssm.entities.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbce.stock.sssm.entities.Stock;
import com.gbce.stock.sssm.entities.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

	public List<Trade> findLast15MinutesTrades(Timestamp sysdate);

	public Set<Trade> findAllByStock(Stock stock);
}
