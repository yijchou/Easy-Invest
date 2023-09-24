package EasyInvest.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import EasyInvest.model.*;


public class RepostDao {

	protected ConnectionManager connectionManager;

	private static RepostDao instance = null;

	protected RepostDao() {
		connectionManager = new ConnectionManager();
	}

	public static RepostDao getInstance() {
		if (instance == null) {
			instance = new RepostDao();
		}
		return instance;
	}
	
	/*
	 *  Create instance
	 */
	public Repost create(Repost repost) throws SQLException {

		String insertRepost = "INSERT INTO Repost(RepostId, Description, UserName, PostId) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Repost has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertRepost, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, repost.getRepostId());
			insertStmt.setString(2, repost.getDescription());
			insertStmt.setString(3, repost.getUser().getUserName());
			insertStmt.setInt(4,  repost.getStrategyPost().getPostId());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int recId = -1;
			if (resultKey.next()) {
				recId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			repost.setRepostId(recId);
			return repost;
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
	 * Delete the Repost instance.
	 * This runs a DELETE statement.
	 */
	public Repost delete(Repost repost) throws SQLException {
		String deleteLike = "DELETE FROM Repost WHERE RepostId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLike);
			deleteStmt.setInt(1, repost.getRepostId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}