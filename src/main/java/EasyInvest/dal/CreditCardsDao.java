package EasyInvest.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import EasyInvest.model.CreditCards;
import EasyInvest.model.MembershipUsers;

public class CreditCardsDao {

	protected ConnectionManager connectionManager;

	private static CreditCardsDao instance = null;

	protected CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}

	public static CreditCardsDao getInstance() {
		if (instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}
	

	/**
	 * Create CreditCards instance
	 */
	public CreditCards create(CreditCards creditCard) throws SQLException {
		String insertCreditCard = "INSERT INTO CreditCards(CreditcardId,UserName,Expiration) " + "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;

		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);

			insertStmt.setLong(1, creditCard.getCreditcardId());
			insertStmt.setString(2, creditCard.getUserName().getUserName());
			insertStmt.setTimestamp(3, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.executeUpdate();
			return creditCard;
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
	 * Get a list of credit cards by user name
	 */
	public List<CreditCards> getCreditCardByUserName(String userName) throws SQLException {
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCreditCards = 
				"SELECT CreditcardId,UserName,Expiration " + 
				"FROM CreditCards " + 
				"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		MembershipUsersDao membershipUsersDao = MembershipUsersDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCards);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			while (results.next()) {
				long creditcardId = results.getLong("CreditcardId");
				MembershipUsers resultUser = membershipUsersDao.getMembershipUserFromUserName(userName);
				Date expiration = results.getDate("Expiration");
				CreditCards creditCard = new CreditCards(creditcardId, resultUser, expiration);
				creditCards.add(creditCard);
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
		return creditCards;
	}
		
	/**
	 * Get a credit card by card number
	 */
	public CreditCards getCreditCardByCardNumber(long cardNumber) throws SQLException {
		String selectCreditCard = 
				"SELECT CreditcardId,UserName,Expiration " + 
				"FROM CreditCards " +
				"WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setLong(1, cardNumber);
			results = selectStmt.executeQuery();
			MembershipUsersDao membershipUsersDao = MembershipUsersDao.getInstance();
			if (results.next()) {
				long resultCardNumber = results.getLong("CreditcardId");
				String userName = results.getString("UserName");
				Date expiration = results.getDate("Expiration");
				MembershipUsers users = membershipUsersDao.getMembershipUserFromUserName(userName);
				CreditCards creditCard = new CreditCards(resultCardNumber, users, expiration);
				return creditCard;
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


	/**
	 * Update the expiration of a credit card
	 */
	public CreditCards updateCreditCardExpiration(CreditCards creditCard, Date newExpiration) throws SQLException {
		String updateCreditCard = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCreditCard);
			updateStmt.setTimestamp(1, new Timestamp(newExpiration.getTime()));
			updateStmt.setLong(2, creditCard.getCreditcardId());
			updateStmt.executeUpdate();

			creditCard.setExpiration(newExpiration);
			return creditCard;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}

	}

}
