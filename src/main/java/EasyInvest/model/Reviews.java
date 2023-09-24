package EasyInvest.model;

public class Reviews {
	 protected int reviewId;
	 protected String review;
	 protected MembershipUsers user;
	 protected StrategyPost post;

	 public Reviews(int reviewId, String review, MembershipUsers user, StrategyPost post) {
	   this.reviewId = reviewId;
	   this.review = review;
	   this.user = user;
	   this.post = post;
	 }

	 public int getReviewId() {
	   return reviewId;
	 }

	 public void setReviewId(int reviewId) {
	   this.reviewId = reviewId;
	 }

	 public String getReview() {
	   return review;
	 }

	 public void setReview(String review) {
	   this.review = review;
	 }

	 public MembershipUsers getUser() {
	   return user;
	 }

	 public void setUser(MembershipUsers user) {
	   this.user = user;
	 }

	 public StrategyPost getStrategyPost() {
	   return post;
	 }

	 public void setPost(StrategyPost post) {
	   this.post = post;
	 }
	}
