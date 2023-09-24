package EasyInvest.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import EasyInvest.model.Admin;
import EasyInvest.model.MembershipUsers;
import EasyInvest.model.Users;

public class AdminDao extends UsersDao {
	// Single pattern: instantiation is limited to one object.
	private static AdminDao instance = null;
	protected AdminDao() {
		super();
	}
	public static AdminDao getInstance() {
		if(instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
	/**
	 *  create the administer user instance. 
	 */
	
	public Admin create(Admin admin) throws SQLException {
		// Insert the User super class
		create(new Users(admin.getUserName(), admin.getPassword(),
				admin.getCreated(),admin.isMember(),
				admin.getFirstName(),admin.getLastName(),
				admin.getEmail(),admin.getPhone(),
				admin.getCompetencyLevel()));

		String insertAdmin = "INSERT INTO Admin(UserName, LastLogin) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAdmin);
			insertStmt.setString(1, admin.getUserName());
			insertStmt.setTimestamp(2, new Timestamp(admin.getLastLogin().getTime()));
			insertStmt.executeUpdate();
			return admin;
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
	 * Get admin user (all information included) by username
	 */
	public Admin getAdminFromUserName(String userName) throws SQLException {
		// build the membership user object and use the User super class
		String selectMembershipUser =
				"SELECT Admin.UserName AS UserName, Password,"
				+ "Created, isMember, FirstName, LastName,"
				+ "Email, Phone, CompetencyLevel, LastLogin "
				+ "FROM Admin INNER JOIN Users "
				+ "ON Admin.UserName = Users.UserName "
				+ "WHERE Admin.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMembershipUser);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				Date created = new Date(results.getTimestamp("Created").getTime());

				boolean isMember = results.getBoolean("isMember");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Date lastLogin = new Date(results.getTimestamp("LastLogin").getTime());
				String email = results.getString("Email");
				String phone = results.getString("Phone");
				MembershipUsers.CompetencyLevel competencyLevel = MembershipUsers.CompetencyLevel.valueOf(
						results.getString("CompetencyLevel"));
				Admin admin = new Admin(resultUserName,password,
						created, isMember, firstName, lastName,
						email, phone, competencyLevel, lastLogin);
				
				return admin;
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
	
	/**
	 * update the password of administer
	 * the update only in the super class table
	 */
	public Admin updatePassword(Admin admin, String newPassword) throws SQLException {
		super.updatePassword(admin, newPassword);
		return admin;
	}
	
	/**
	 * Delete the administer instance
	 */
	public Admin delete(Admin admin) throws SQLException {
		String deleteAdmin = "DELETE FROM Admin WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAdmin);
			deleteStmt.setString(1, admin.getUserName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for UserName=" + admin.getUserName());
			}
			super.delete(admin);

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
	
	
