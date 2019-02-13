package com.sample.demo.vo.ticker;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class TickerVo {
	private Long opening_price;
	private Long closing_price;
	private Long min_price;
	private Long max_price;
	private BigDecimal average_price;
	private BigDecimal units_traded;
	private BigDecimal volume_1day;
	private BigDecimal volume_7day;
	private Long buy_price;
	private Long sell_price;
	@JsonProperty("24H_fluctate")
	private Long fluctate;
	@JsonProperty("24H_fluctate_rate")
	private BigDecimal fluctate_rate;
	private Date date;
}
