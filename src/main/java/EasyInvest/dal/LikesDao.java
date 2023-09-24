package EasyInvest.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import EasyInvest.model.*;


public class LikesDao {

	protected ConnectionManager connectionManager;

	private static LikesDao instance = null;

	protected LikesDao() {
		connectionManager = new ConnectionManager();
	}

	public static LikesDao getInstance() {
		if (instance == null) {
			instance = new LikesDao();
		}
		return instance;
	}
	
	/*
	 *  Create instance
	 */
	public Likes create(Likes like) throws SQLException {

		String insertLike = "INSERT INTO Likes(UserName,PostId,Created) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// Likes has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertLike, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, like.getUser().getUserName());
			insertStmt.setInt(2, like.getPost().getPostId());
			insertStmt.setTimestamp(3, new Timestamp(like.getCreated().getTime()));
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
			like.setLikeId(recId);
			return like;
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
	 * Delete the Likes instance.
	 * This runs a DELETE statement.
	 */
	public Likes delete(Likes like) throws SQLException {
		String deleteLike = "DELETE FROM Reshares WHERE LikeId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLike);
			deleteStmt.setInt(1, like.getLikeId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
	
	/**
	 * Get the all the Likes for a user.
	 */
//	public List<Likes> getLikesForUser(MembershipUsers user) throws SQLException {
//		List<Likes> likes = new ArrayList<Likes>();
//		String selectLikes =
//			"SELECT LikeId,UserName,PostId,Created " +
//			"FROM Likes " + 
//			"WHERE UserName=?;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectLikes);
//			selectStmt.setString(1, user.getUserName());
//			results = selectStmt.executeQuery();
//			StrategyPostDao strategyPostDao = StrategyPostDao.getInstance();
//			while(results.next()) {
//				int likeId = results.getInt("LikeId");
//				int postId = results.getInt("PostId");
//				// need getStrategyPostById 
//				Timestamp created = results.getTimestamp("Created");
//				Likes like = new Likes(likeId, user, post, created);
//				likes.add(like);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(selectStmt != null) {
//				selectStmt.close();
//			}
//			if(results != null) {
//				results.close();
//			}
//		}
//		return likes;
//	}
	
}