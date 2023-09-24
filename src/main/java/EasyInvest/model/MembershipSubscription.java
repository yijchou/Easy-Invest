package EasyInvest.model;

import java.util.Date;

public class MembershipSubscription {
	protected int transactionId;
	protected MembershipUsers user;
	protected double subscriptionPrice;
	protected Date startDate;
	/**
	 * @param transactionId
	 * @param user
	 * @param subscriptionPrice
	 * @param startDate
	 */
	public MembershipSubscription(int transactionId, MembershipUsers user, double subscriptionPrice,
			Date startDate) {
		super();
		this.transactionId = transactionId;
		this.user = user;
		this.subscriptionPrice = subscriptionPrice;
		this.startDate = startDate;
	}
	
	
	/**
	 * @param user
	 * @param subscriptionPrice
	 * @param startDate
	 */
	public MembershipSubscription(MembershipUsers user, double subscriptionPrice, Date startDate) {
		super();
		this.user = user;
		this.subscriptionPrice = subscriptionPrice;
		this.startDate = startDate;
	}


	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public MembershipUsers getUser() {
		return user;
	}
	public void setUser(MembershipUsers user) {
		this.user = user;
	}
	public double getSubscriptionPrice() {
		return subscriptionPrice;
	}
	public void setSubscriptionPrice(double subscriptionPrice) {
		this.subscriptionPrice = subscriptionPrice;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
}
	
	