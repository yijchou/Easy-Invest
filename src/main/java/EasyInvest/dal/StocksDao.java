package EasyInvest.dal;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import EasyInvest.model.Stocks;



public class StocksDao {

	protected ConnectionManager connectionManager;

	private static StocksDao instance = null;

	protected StocksDao() {
		connectionManager = new ConnectionManager();
	}

	public static StocksDao getInstance() {
		if (instance == null) {
			instance = new StocksDao();
		}
		return instance;
	}
	
	/**
	 * Create StocksDao instance
	 */
	public Stocks create(Stocks stock) throws SQLException {
		String insertStock = "INSERT INTO Stocks(Date,Open,High,Low,Close,AdjClose,Volume,StockCapRank,TickerName) " + "VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertStock);

			insertStmt.setTimestamp(1, new Timestamp(stock.getDate().getTime()));
			insertStmt.setDouble(2, stock.getOpen());
			insertStmt.setDouble(3, stock.getHigh());
			insertStmt.setDouble(4, stock.getLow());
			insertStmt.setDouble(5, stock.getClose());
			insertStmt.setDouble(6, stock.getAdjClose());
			insertStmt.setLong(7, stock.getVolume());
			insertStmt.setInt(8, stock.getStockCapRank());
			insertStmt.setString(9, stock.getTickerName());
			
			insertStmt.executeUpdate();
			return stock;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	
	/**
	 * Get the very beginning row and the last row based on given ticker
	 */
	public List<Stocks> getStartAndEndForTicker(String TickerName) throws SQLException {
		List<Stocks> stockList = new ArrayList<Stocks>();
		String selectStockList =
			"(SELECT * FROM "
			+ "(SELECT * FROM Stocks "
			+ "WHERE TickerName=?) AS TICKER "
			+ "ORDER BY Date "
			+ "LIMIT 1) "
			+ "UNION "
			+ "(SELECT * FROM "
			+ "(SELECT * FROM Stocks "
			+ "WHERE TickerName=?) AS TICKER "
			+ "ORDER BY Date DESC "
			+ "LIMIT 1);";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStockList);
			selectStmt.setString(1, TickerName);
			selectStmt.setString(2, TickerName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Date date =  new Date(results.getTimestamp("Date").getTime());
				Double open = results.getDouble("Open");
				Double high = results.getDouble("High");
				Double low = results.getDouble("Low");
				Double close = results.getDouble("Close");
				Double adjClose = results.getDouble("AdjClose");
				long volume = results.getLong("Volume");
				int stockCapRank = results.getInt("StockCapRank");
				String tickerName = results.getString("TickerName");
				
				Stocks stock = new Stocks(date, open, high, low, close, adjClose, volume,
								stockCapRank, tickerName);
				stockList.add(stock);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return stockList;
	}

	/**
	 * Get a list of distinct ticker names that in the whole stock table 
	 */
	public List<String> getDistinctTickerNames() throws SQLException {
		List<String> tickerNames = new ArrayList<String>();
		String selectDistinct =  "SELECT DISTINCT TickerName FROM Stocks;";
			
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
	
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDistinct);
			
			results = selectStmt.executeQuery();
			while (results.next()) {
				String tickerName = results.getString("TickerName");
				
				tickerNames.add(tickerName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return tickerNames;
	}


	/**
	 * Get a return percent based on given ticker
	 */
	public Double getReturnPercentForTicker(String TickerName) throws SQLException {
		double returnPercent = 0.0;
		String selectReturn =  "SELECT  (CurrentPrice - StartPrice)/StartPrice AS ReturnPercent FROM "
				+ "(SELECT AdjClose AS StartPrice FROM "
				+ "(SELECT * FROM Stocks "
				+ "	WHERE TickerName=?) AS TICKER "
				+ "	ORDER"
				+ "		BY Date "
				+ "	LIMIT 1) AS Start "
				+ "CROSS JOIN "
				+ "	(SELECT AdjClose AS CurrentPrice FROM "
				+ "	(SELECT * FROM Stocks "
				+ "	WHERE TickerName =?) AS TICKER "
				+ "	ORDER "
				+ "		BY Date DESC "
				+ "	LIMIT 1) AS Current;";
		
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
	
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReturn);
			selectStmt.setString(1, TickerName);
			selectStmt.setString(2, TickerName);
			results = selectStmt.executeQuery();
			if (results.next()) {
				returnPercent = results.getDouble("ReturnPercent");
				return returnPercent;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}
	

}