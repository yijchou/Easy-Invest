package EasyInvest.model;

import java.util.Date;

public class Stocks {
	protected Date date;
	protected Double open;
	protected Double high;
	protected Double low;
	protected Double close;
	protected Double adjClose;
	protected Long volume;
	protected Integer stockCapRank;
	protected String tickerName;

	public Stocks(Date date, Double open, Double high, Double low, Double close, Double adjClose, Long volume,
			Integer stockCapRank, String tickerName) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjClose = adjClose;
		this.volume = volume;
		this.stockCapRank = stockCapRank;
		this.tickerName = tickerName;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(Double adjClose) {
		this.adjClose = adjClose;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Integer getStockCapRank() {
		return stockCapRank;
	}

	public void setStockCapRank(Integer stockCapRank) {
		this.stockCapRank = stockCapRank;
	}

	public String getTickerName() {
		return tickerName;
	}

	public void setTickerName(String tickerName) {
		this.tickerName = tickerName;
	}
}