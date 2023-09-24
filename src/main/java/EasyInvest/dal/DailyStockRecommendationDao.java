package EasyInvest.dal;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import EasyInvest.model.*;

public class DailyStockRecommendationDao {

	protected ConnectionManager connectionManager;

	private static DailyStockRecommendationDao instance = null;

	protected DailyStockRecommendationDao() {
		connectionManager = new ConnectionManager();
	}

	public static DailyStockRecommendationDao getInstance() {
		if (instance == null) {
			instance = new DailyStockRecommendationDao();
		}
		return instance;
	}
	
	// Create instance
	public DailyStockRecommendation create(DailyStockRecommendation recommendation) throws SQLException {

		String insertRecommendation = "INSERT INTO DailyStockRecommendation(TickerName,Date,PositionType) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Restaurants has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertRecommendation, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, recommendation.getTickerName());
			insertStmt.setTimestamp(2, new Timestamp(recommendation.getDate().getTime()));
			insertStmt.setString(3, recommendation.getPosition().toString());
			insertStmt.executeUpdate();

			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			// For more details, see:
			// http://dev.mysql.com/doc/connector-j/en/connector-j-usagenotes-last-insert-id.html
			resultKey = insertStmt.getGeneratedKeys();
			int recId = -1;
			if (resultKey.next()) {
				recId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setDailyStockRecommendationID(recId);
			return recommendation;
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
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	/**
	 * Get a list of Recommendations by ticker name
	 */
	public List<DailyStockRecommendation> getRecommendationByTicker(String tickerName) throws SQLException {
		List<DailyStockRecommendation> recommendations = new ArrayList<DailyStockRecommendation>();
		String selectRecommendation = 
				"SELECT DailyStockRecommendationId,TickerName,Date,PositionType "
				+ "FROM DailyStockRecommendation " 
				+ "WHERE TickerName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setString(1, tickerName);
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				int dailyStockRecommendationId = results.getInt("DailyStockRecommendationId");
				String name = results.getString("TickerName");
				Date date =  new Date(results.getTimestamp("Date").getTime());
				DailyStockRecommendation.PositionType posType = DailyStockRecommendation.PositionType.valueOf(results.getString("PositionType"));

				DailyStockRecommendation recommendation = new DailyStockRecommendation(dailyStockRecommendationId, name, date, posType);
				recommendations.add(recommendation);
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
		return recommendations;

	}
	
//	/**
//	 * Get a list of Recommendations by date
//	 */
	public List<DailyStockRecommendation> getRecommendationByDate(java.util.Date date2) throws SQLException {
		List<DailyStockRecommendation> recommendations = new ArrayList<DailyStockRecommendation>();
		String selectRecommendation = 
				"SELECT DailyStockRecommendationId,TickerName,Date,PositionType "
				+ "FROM DailyStockRecommendation " 
				+ "WHERE Date=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			
			selectStmt.setTimestamp(1, new Timestamp(date2.getTime()));
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				int dailyStockRecommendationId = results.getInt("DailyStockRecommendationId");
				String name = results.getString("TickerName");
				Date resDate =  new Date(results.getTimestamp("Date").getTime());
				DailyStockRecommendation.PositionType posType = DailyStockRecommendation.PositionType.valueOf(results.getString("PositionType"));

				DailyStockRecommendation recommendation = new DailyStockRecommendation(dailyStockRecommendationId, name, resDate, posType);
				recommendations.add(recommendation);
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
		return recommendations;

	}
}