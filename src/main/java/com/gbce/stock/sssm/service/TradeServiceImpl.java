package com.gbce.stock.sssm.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gbce.stock.sssm.entities.Trade;
import com.gbce.stock.sssm.entities.repository.StockRepository;
import com.gbce.stock.sssm.entities.repository.TradeRepository;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private StockRepository stockRepository;

	@Override
	public Trade saveTrade(final Trade trade) {
		final Date date = new Date();
		final Timestamp timestamp = new Timestamp(date.getTime());
		trade.setTimestamp(timestamp);
		return tradeRepository.save(trade);
	}

	@Override
	public List<Trade> findTradeAll() {
		return tradeRepository.findAll();
	}

	@Override
	public Double getVolumeWeightedStockPrice() {

		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -15);
		final Timestamp sysdate = new Timestamp(cal.getTime().getTime());
		final List<Trade> tradeList = tradeRepository.findLast15MinutesTrades(sysdate);
		Double sumOfTradePrice = Double.valueOf(0);
		Double sumOfQuantity = Double.valueOf(0);
		Integer quantity = 0;
		if (!CollectionUtils.isEmpty(tradeList)) {
			quantity = tradeList.size();
			for (final Trade trade : tradeList) {
				if (trade.getTradedPrice() != null) {
					sumOfTradePrice += trade.getTradedPrice();
				}

				if (trade.getQuantity() != null) {
					sumOfQuantity += trade.getQuantity();
				}
			}

			if (sumOfQuantity != 0) {
				return (sumOfTradePrice * quantity) / sumOfQuantity;

			}
		}
		return Double.valueOf(0);
	}

}
