package EasyInvest.dal;
import EasyInvest.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class ReviewsDao {
  protected ConnectionManager connectionManager;

  private static ReviewsDao instance = null;
  protected ReviewsDao() {
    connectionManager = new ConnectionManager();
  }
  public static ReviewsDao getInstance() {
    if(instance == null) {
      instance = new ReviewsDao();
    }
    return instance;
  }


  // create
  public Reviews create(Reviews review) throws SQLException {
    String insertReview =
        "INSERT INTO Reviews(Review,UserName,PostId) " +
            "VALUES(?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertReview,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, review.getReview());
      insertStmt.setString(2, review.getUser().getUserName());
      insertStmt.setInt(3, review.getStrategyPost().getPostId());
      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
      int reviewId = -1;
      if(resultKey.next()) {
        reviewId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      review.setReviewId(reviewId);
      return review;
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
      if(resultKey != null) {
        resultKey.close();
      }
    }
  }


  // updateReview
  public Reviews updateReview(Reviews review, String newReview) throws SQLException {
    String updateReview = "UPDATE Reviews SET Review=? WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateReview);
      updateStmt.setString(1, newReview);
      updateStmt.setInt(2, review.getReviewId());
      updateStmt.executeUpdate();

      review.setReview(newReview);
      return review;
    } catch (SQLException e) {
      e.printStackTrace();
      throw e;
    } finally {
      if(connection != null) {
        connection.close();
      }
      if(updateStmt != null) {
        updateStmt.close();
      }
    }
  }


  // delete
  public Reviews delete(Reviews review) throws SQLException {
    String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteReview);
      deleteStmt.setInt(1, review.getReviewId());
      deleteStmt.executeUpdate();

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

  // getReviewById
  public Reviews getReviewById(int reviewId) throws SQLException {
    String selectReview =
        "SELECT ReviewID, Review, UserName, PostId " +
            "FROM Reviews " +
            "WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    MembershipUsersDao membershipUserDao = MembershipUsersDao.getInstance();
    StrategyPostDao strategyPostDao = StrategyPostDao.getInstance();

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReview);
      selectStmt.setInt(1, reviewId);
      results = selectStmt.executeQuery();

      if(results.next()) {
        int resultReviewId = results.getInt("ReviewId");
        String review = results.getString("Review");
        String userName = results.getString("UserName");
        int postId = results.getInt("PostId");
        MembershipUsers user = membershipUserDao.getMembershipUserFromUserName(userName);
        StrategyPost strategyPost = strategyPostDao.getStrategyPostById(postId);
        Reviews review1 = new Reviews(resultReviewId,review,user,strategyPost);
        return review1;
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


  // getReviewsByUserName
  public List<Reviews> getReviewsByUserName(String userName) throws SQLException {
    List<Reviews> reviews = new ArrayList<>();
    String selectReview =
        "SELECT ReviewID, Review, Reviews.UserName, Reviews.PostId " +
            "FROM Reviews INNER JOIN Users ON Reviews.UserName = Users.UserName " +
            "WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    MembershipUsersDao membershipUserDao = MembershipUsersDao.getInstance();
    StrategyPostDao strategyPostDao = StrategyPostDao.getInstance();

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReview);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
      
      while(results.next()) {
        int reviewId = results.getInt("ReviewId");
        String review = results.getString("Review");
        int postId = results.getInt("PostId");
        MembershipUsers user = membershipUserDao.getMembershipUserFromUserName(userName);
        StrategyPost strategyPost = strategyPostDao.getStrategyPostById(postId);
        Reviews review1 = new Reviews(reviewId,review,user,strategyPost);
        reviews.add(review1);
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
    return reviews;
  }


  // getReviewsByPostId
  public List<Reviews> getReviewsByPostId(int postId) throws SQLException {
    List<Reviews> reviews = new ArrayList<>();
    String selectReview =
        "SELECT ReviewID, Review, Reviews.UserName, Reviews.PostId " +
            "FROM Reviews INNER JOIN StrategyPost ON Reviews.PostId = StrategyPost.PostId " +
            "WHERE ReviewId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectReview);
      selectStmt.setInt(1, postId);
      results = selectStmt.executeQuery();
      MembershipUsersDao membershipUserDao = MembershipUsersDao.getInstance();
      StrategyPostDao strategyPostDao = StrategyPostDao.getInstance();

      while(results.next()) {
        int reviewId = results.getInt("ReviewId");
        String review = results.getString("Review");
        String userName = results.getString("UserName");
        MembershipUsers user = membershipUserDao.getMembershipUserFromUserName(userName);
        StrategyPost strategyPost = strategyPostDao.getStrategyPostById(postId);
        Reviews review1 = new Reviews(reviewId,review,user,strategyPost);
        reviews.add(review1);
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
    return reviews;
  }

}