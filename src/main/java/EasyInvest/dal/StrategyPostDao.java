package EasyInvest.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import EasyInvest.model.*;


public class StrategyPostDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static StrategyPostDao instance = null;
	protected StrategyPostDao() {
		connectionManager = new ConnectionManager();
	}
	public static StrategyPostDao getInstance() {
		if(instance == null) {
			instance = new StrategyPostDao();
		}
		return instance;
	}
	
	public StrategyPost create(StrategyPost strategyPost) throws SQLException {
		String insertStrategyPost = "INSERT INTO StrategyPost(Title, Content, Created, UserName, Published, Likes) "
				+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertStrategyPost,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1,  strategyPost.getTitle());
			insertStmt.setString(2, strategyPost.getContent());
			if (strategyPost.getCreated() != null) {
				insertStmt.setTimestamp(3, new Timestamp(strategyPost.getCreated().getTime()));
			} else {
				insertStmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			}
			insertStmt.setString(4, strategyPost.getUserName());
			insertStmt.setBoolean(5, strategyPost.isPublished());
			insertStmt.setInt(6, strategyPost.getLikes());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int postId = -1;
			if(resultKey.next()) {
				postId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			strategyPost.setPostId(postId);
			return strategyPost;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	/**
	 * Get the StrategyPosts record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single BlogPosts instance.
	 */
	public StrategyPost getStrategyPostById(int postId) throws SQLException {
        String selectStrategyPost =
                "SELECT PostID, Title, Content, Created, UserName, Published, Likes "+
                "FROM StrategyPost " +
                "WHERE PostID=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectStrategyPost);
            selectStmt.setInt(1, postId);
            results = selectStmt.executeQuery();
            if(results.next()) {
                int resultPostId = results.getInt("PostID");
                String title = results.getString("Title");
                String content = results.getString("Content");
                Date created = new Date(results.getTimestamp("Created").getTime());
                String userName = results.getString("UserName");
                boolean published = results.getBoolean("Published");
                int likes = results.getInt("Likes");
                StrategyPost strategyPost = new StrategyPost(resultPostId, title, content, created, 
                        userName, published, likes);
                return strategyPost;
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
        return null;
    }
	
	
	public List<StrategyPost> getStrategyPostByTopLikes() throws SQLException {
		List<StrategyPost> strategyPosts = new ArrayList<StrategyPost>();
		String selectStrategyPost =
				"SELECT PostID, Title, Content, Created, UserName, Published, Likes "+
				"FROM StrategyPost " +
				"ORDER BY Likes DESC " +
				"LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStrategyPost);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int postId = results.getInt("PostID");
				String title = results.getString("Title");
				String content = results.getString("Content");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String userName = results.getString("UserName");
				boolean published = results.getBoolean("Published");
				int likes = results.getInt("Likes");
				StrategyPost strategyPost = new StrategyPost(postId, title, content, created, 
						userName, published, likes);
				
				strategyPosts.add(strategyPost);
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
		return strategyPosts;
	}
	
	public List<StrategyPost> getStrategyPostsByCreated(Date createdSearch) throws SQLException {
		List<StrategyPost> strategyPosts = new ArrayList<StrategyPost>();
		String selectStrategyPost =
				"SELECT PostID, Title, Content, Created, UserName, Published, Likes "+
				"FROM StrategyPost " +
				"WHERE Created=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStrategyPost);
			selectStmt.setTimestamp(1, new Timestamp(createdSearch.getTime()));
			results = selectStmt.executeQuery();
			while(results.next()) {
				int postId = results.getInt("PostID");
				String title = results.getString("Title");
				String content = results.getString("Content");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String userName = results.getString("UserName");
				boolean published = results.getBoolean("Published");
				int likes = results.getInt("Likes");
				StrategyPost strategyPost = new StrategyPost(postId, title, content, created, 
						userName, published, likes);
				
				strategyPosts.add(strategyPost);
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
		return strategyPosts;
	}
	
	
	
	public List<StrategyPost> getStrategyPostsByUserName(String userNameSearch) throws SQLException {
		List<StrategyPost> strategyPosts = new ArrayList<StrategyPost>();
		String selectStrategyPost =
				"SELECT PostID, Title, Content, Created, UserName, Published, Likes "+
				"FROM StrategyPost " +
				"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStrategyPost);
			selectStmt.setString(1, userNameSearch);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int postId = results.getInt("PostID");
				String title = results.getString("Title");
				String content = results.getString("Content");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String userName = results.getString("UserName");
				boolean published = results.getBoolean("Published");
				int likes = results.getInt("Likes");
				StrategyPost strategyPost = new StrategyPost(postId, title, content, created, 
						userName, published, likes);
				
				strategyPosts.add(strategyPost);
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
		return strategyPosts;
	}
	
	public List<StrategyPost> getStrategyPostsByCompetencyLevel(Users.CompetencyLevel compLevel) throws SQLException {
		List<StrategyPost> strategyPosts = new ArrayList<StrategyPost>();
		String selectStrategyPost =
				"SELECT PostID, Title, Content, StrategyPost.Created, StrategyPost.UserName, Published, Likes "+
				"FROM StrategyPost INNER JOIN Users " +
				"ON StrategyPost.UserName = Users.UserName " +
				"WHERE Users.CompetencyLevel=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectStrategyPost);
			selectStmt.setString(1, compLevel.name());
			results = selectStmt.executeQuery();
			while(results.next()) {
				int postId = results.getInt("PostID");
				String title = results.getString("Title");
				String content = results.getString("Content");
				Date created = new Date(results.getTimestamp("Created").getTime());
				String userName = results.getString("UserName");
				boolean published = results.getBoolean("Published");
				int likes = results.getInt("Likes");
				StrategyPost strategyPost = new StrategyPost(postId, title, content, created, 
						userName, published, likes);
				
				strategyPosts.add(strategyPost);
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
		return strategyPosts;
	}
	
	public StrategyPost updateContent(StrategyPost strategyPost, String newContent) throws SQLException {
		String updateContent = "UPDATE StrategyPost SET Content=? WHERE PostID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateContent);
			updateStmt.setString(1, newContent);
			updateStmt.setInt(2, strategyPost.getPostId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			strategyPost.setContent(newContent);
			return strategyPost;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public StrategyPost delete(StrategyPost strategyPost) throws SQLException {
		String deleteStrategyPost = "DELETE FROM StrategyPost WHERE PostID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteStrategyPost);
			deleteStmt.setInt(1, strategyPost.getPostId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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