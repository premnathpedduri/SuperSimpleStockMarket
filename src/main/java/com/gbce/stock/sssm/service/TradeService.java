package com.gbce.stock.sssm.service;

import java.util.List;

import com.gbce.stock.sssm.entities.Trade;

public interface TradeService {

	Trade saveTrade(Trade trade);

	List<Trade> findTradeAll();

	Double getVolumeWeightedStockPrice();

}
