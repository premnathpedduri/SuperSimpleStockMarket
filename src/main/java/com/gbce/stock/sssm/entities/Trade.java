package com.gbce.stock.sssm.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.gbce.stock.sssm.enumeration.Side;

import lombok.Data;

@Entity
@Data
@NamedQuery(name = "Trade.findLast15MinutesTrades", query = "SELECT t FROM Trade t where t.timestamp > :sysdate")
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tradeId;
	private Double quantity;
	private Double tradedPrice;
	private Side side;
	private Timestamp timestamp;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "symbol", nullable = false)
	private Stock stock;
}
