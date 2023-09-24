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

import EasyInvest.model.MembershipSubscription;
import EasyInvest.model.MembershipUsers;

public class MembershipSubscriptionDao extends MembershipUsersDao {
	// Single pattern: instantiation is limited to one object.
	private static MembershipSubscriptionDao instance = null;
	protected MembershipSubscriptionDao() {
		super();
	}
	public static MembershipSubscriptionDao getInstance() {
		if(instance == null) {
			instance = new MembershipSubscriptionDao();
		}
		return instance;
	}
	
	public MembershipSubscription create(MembershipSubscription subscription) throws SQLException {
		String insertSubscription = "INSERT INTO MembershipSubscription(UserName, SubscriptionPrice, StartDate) "
				+ "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSubscription,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1,  subscription.getUser().getUserName());
			insertStmt.setDouble(2, subscription.getSubscriptionPrice());
			if (subscription.getStartDate() != null) {
				insertStmt.setTimestamp(3, new Timestamp(subscription.getStartDate().getTime()));
			} else {
				insertStmt.setTimestamp(3, new Timestamp(new Date().getTime()));
			}
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int subscriptionId = -1;
			if(resultKey.next()) {
				subscriptionId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			subscription.setTransactionId(subscriptionId);
			return subscription;
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
	
	public List<MembershipSubscription> getMembershipSubscriptionByUserName(String userName) throws SQLException {
		List<MembershipSubscription> membershipSubscriptions = new ArrayList<MembershipSubscription>();
		String selectSubscription =
				"SELECT TransactionID, UserName, SubscriptionPrice, StartDate "+
				"FROM MembershipSubscription " +
				"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		MembershipUsersDao membershipUsersDao = MembershipUsersDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSubscription);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int transactionId = results.getInt("TransactionID");
				String resultUserName = results.getString("UserName");
				double subscriptionPrice = results.getDouble("SubscriptionPrice");
				Date startDate = new Date(results.getTimestamp("StartDate").getTime());
				MembershipUsers user = membershipUsersDao.getMembershipUserFromUserName(resultUserName);
				MembershipSubscription subscription = new MembershipSubscription(transactionId, user, subscriptionPrice, startDate);
				membershipSubscriptions.add(subscription);
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
		return membershipSubscriptions;
	}
	
	public MembershipSubscription updateSubscriptionPrice(MembershipSubscription subscription, double newPrice) throws SQLException {
		String updateSubscription = "UPDATE MembershipSubscription SET SubscriptionPrice=? WHERE TransactionID=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateSubscription);
			updateStmt.setDouble(1, newPrice);
			updateStmt.setLong(2, subscription.getTransactionId());
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			subscription.setSubscriptionPrice(newPrice);
			return subscription;
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
	
	public MembershipSubscription delete(MembershipSubscription subscription) throws SQLException {
		String deleteSubscription = "DELETE FROM MembershipSubscription WHERE TransactionID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSubscription);
			deleteStmt.setInt(1, subscription.getTransactionId());
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