package EasyInvest.model;
import java.util.Date;

public class StrategyPost {
	protected int postId;
	protected String title;
	protected String content;
	protected Date created;
	protected String UserName;
	protected boolean published;
	protected int likes;
	
	public StrategyPost(int postId, String title, String content, Date created, String userName, boolean published,
			int likes) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.created = created;
		UserName = userName;
		this.published = published;
		this.likes = likes;
	}

	public StrategyPost(String title, String content, Date created, String userName, boolean published, int likes) {
		this.title = title;
		this.content = content;
		this.created = created;
		UserName = userName;
		this.published = published;
		this.likes = likes;
	}

	public StrategyPost(int postId) {
		this.postId = postId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

}