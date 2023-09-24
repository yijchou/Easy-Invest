package EasyInvest.model;
import java.util.Date;

public class Users {
	protected String userName;
	protected String password;
	protected Date created; 
	protected boolean isMember;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String phone;
	protected CompetencyLevel competencyLevel;
	
	public enum CompetencyLevel {
		BEGINNER, INTERMEDIATE, ADVANCED, EXPERT
	}
	

	/*constructor with all attributes, the date is auto-generated */
	public Users(String userName, String password, Date created, boolean isMember, String firstName, String lastName,
			String email, String phone, CompetencyLevel competencyLevel) {
		super();
		this.userName = userName;
		this.password = password;
		this.created = created;
		this.isMember = isMember;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.competencyLevel = competencyLevel;
	}
	
	/*constructor with username only*/
	public Users(String userName) {
		this.userName = userName;
	}

	

	/* Getters and Setters */
	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public Date getCreated() {
		return created;
	}




	public void setCreated(Date created) {
		this.created = created;
	}




	public boolean isMember() {
		return isMember;
	}




	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public CompetencyLevel getCompetencyLevel() {
		return competencyLevel;
	}




	public void setCompetencyLevel(CompetencyLevel competencyLevel) {
		this.competencyLevel = competencyLevel;
	}

	
}