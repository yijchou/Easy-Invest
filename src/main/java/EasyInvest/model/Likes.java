package EasyInvest.model;
import java.sql.Timestamp;
import java.util.Date;

public class Likes {
	protected int likeId;
	protected MembershipUsers user;
	protected StrategyPost post;
	protected Date created;
	/**
	 * @param likeId
	 * @param user
	 * @param post
	 * @param created
	 */
	public Likes(int likeId, MembershipUsers user, StrategyPost post, Date created) {
		super();
		this.likeId = likeId;
		this.user = user;
		this.post = post;
		this.created = created;
	}
	
	
	/**
	 * @param user
	 * @param post
	 * @param created
	 */
	public Likes(MembershipUsers user, StrategyPost post, Date created) {
		super();
		this.user = user;
		this.post = post;
		this.created = created;
	}


	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public MembershipUsers getUser() {
		return user;
	}
	public void setUser(MembershipUsers user) {
		this.user = user;
	}
	public StrategyPost getPost() {
		return post;
	}
	public void setPost(StrategyPost post) {
		this.post = post;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
	
	