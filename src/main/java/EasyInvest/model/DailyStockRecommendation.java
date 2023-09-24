package EasyInvest.model;

import java.util.Date;

public class DailyStockRecommendation{
	protected int dailyStockRecommendationID;
	protected String tickerName;
	protected Date date;
	protected PositionType position;
	public enum PositionType {
		Long, Short
	}
	
	public DailyStockRecommendation(int dailyStockRecommendationID, String tickerName, Date date,
			PositionType position) {
		this.dailyStockRecommendationID = dailyStockRecommendationID;
		this.tickerName = tickerName;
		this.date = date;
		this.position = position;
	}
	
	public int getDailyStockRecommendationID() {
		return dailyStockRecommendationID;
	}

	public void setDailyStockRecommendationID(int dailyStockRecommendationID) {
		this.dailyStockRecommendationID = dailyStockRecommendationID;
	}


	public String getTickerName() {
		return tickerName;
	}

	public void setTickerName(String tickerName) {
		this.tickerName = tickerName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PositionType getPosition() {
		return position;
	}

	public void setPosition(PositionType position) {
		this.position = position;
	}

}
