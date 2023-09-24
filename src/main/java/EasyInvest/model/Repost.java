package EasyInvest.model;

public class Repost{
	protected int repostId;
	protected String description;
	protected Users user;
	protected StrategyPost strategyPost;
	/**
	 * @param repostId
	 * @param description
	 * @param user
	 * @param strategyPost
	 */
	public Repost(int repostId, String description, Users user, StrategyPost strategyPost) {
		super();
		this.repostId = repostId;
		this.description = description;
		this.user = user;
		this.strategyPost = strategyPost;
	}
	
	
	/**
	 * @param description
	 * @param user
	 * @param strategyPost
	 */
	public Repost(String description, Users user, StrategyPost strategyPost) {
		super();
		this.description = description;
		this.user = user;
		this.strategyPost = strategyPost;
	}


	public int getRepostId() {
		return repostId;
	}
	public void setRepostId(int repostId) {
		this.repostId = repostId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public StrategyPost getStrategyPost() {
		return strategyPost;
	}
	public void setStrategyPost(StrategyPost strategyPost) {
		this.strategyPost = strategyPost;
	}
	
}