package EasyInvest.model;
import java.sql.Timestamp;
import java.util.Date;

public class Admin extends Users{
	protected Date lastLogin;

	/**
	 * @param userName
	 * @param password
	 * @param created
	 * @param isMember
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param competencyLevel
	 * @param lastLogin
	 */
	public Admin(String userName, String password, Date created, boolean isMember, String firstName, String lastName,
			String email, String phone, CompetencyLevel competencyLevel, Date lastLogin) {
		super(userName, password, created, isMember, firstName, lastName, email, phone, competencyLevel);
		this.lastLogin = lastLogin;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}