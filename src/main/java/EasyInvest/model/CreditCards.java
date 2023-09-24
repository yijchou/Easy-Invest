package EasyInvest.model;

import java.util.Date;

public class CreditCards {
	protected long CreditcardId;
	protected MembershipUsers UserName;
	protected Date Expiration;
	
	public CreditCards(long cardNumberId, MembershipUsers username, Date expiration) {
		this.CreditcardId = cardNumberId;
		this.UserName = username;
		this.Expiration = expiration;
	}
	
	/** Getters and setters. */
	public long getCreditcardId() {
		return CreditcardId;
	}

	public void setCreditcardId(long creditcardId) {
		this.CreditcardId = creditcardId;
	}

	public MembershipUsers getUserName() {
		return UserName;
	}

	public void setUserName(MembershipUsers username) {
		this.UserName = username;
	}
	
	public Date getExpiration() {
		return Expiration;
	}

	public void setExpiration(Date expiration) {
		this.Expiration = expiration;
	}
}
