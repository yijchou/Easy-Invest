package EasyInvest.model;

import java.util.Date;


public class MembershipUsers extends Users {
	protected double revenue;

	public MembershipUsers(String userName, String password, Date created, Boolean isMember, String firstName,
			String lastName, String email, String phone, CompetencyLevel competencyLevel, double revenue) {
		super(userName, password, created, isMember, firstName, lastName, email, phone, competencyLevel);
		this.revenue = revenue;
	}
	
	
	public MembershipUsers(String userName) {
		super(userName);
	}



	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
}
	
	