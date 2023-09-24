package EasyInvest.dal;
import EasyInvest.model.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class EducationalMaterialsDao {
  protected ConnectionManager connectionManager;

  private static EducationalMaterialsDao instance = null;
  protected EducationalMaterialsDao() {
    connectionManager = new ConnectionManager();
  }
  public static EducationalMaterialsDao getInstance() {
    if(instance == null) {
      instance = new EducationalMaterialsDao();
    }
    return instance;
  }


  // GetMaterialByID -- updated by yuhan Dec06 -- do we need this? 


  // create
  public EducationalMaterials create(EducationalMaterials educationalMaterial) throws SQLException {
    String insertEducationalMaterial =
        "INSERT INTO EducationalMaterials(Title,Content,Created,Published,UserName) " +
        "VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertEducationalMaterial,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, educationalMaterial.getTitle());
      insertStmt.setString(2, educationalMaterial.getContent());
      insertStmt.setTimestamp(3, new Timestamp(educationalMaterial.getCreated().getTime()));
      insertStmt.setBoolean(4, educationalMaterial.isPublished());
//      insertStmt.setString(5, educationalMaterial.getUser().getUserName()); -- Yuhan Dec07
      insertStmt.setString(5, educationalMaterial.getUserName());
      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
      int materialId = -1;
      if(resultKey.next()) {
        materialId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      educationalMaterial.setMaterialId(materialId);
      return educationalMaterial;
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


  // updateContent
  public EducationalMaterials updateContent(EducationalMaterials educationalMaterial, String newContent) throws SQLException {
    String updateEducationalMaterial = "UPDATE EducationalMaterials SET Content=?,Created=? WHERE MaterialId=?;";
    Connection connection = null;
    PreparedStatement updateStmt = null;
    try {
      connection = connectionManager.getConnection();
      updateStmt = connection.prepareStatement(updateEducationalMaterial);
      updateStmt.setString(1, newContent);
      Date newCreatedTimestamp = new Date();
      updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
      updateStmt.setInt(3, educationalMaterial.getMaterialId());
      updateStmt.executeUpdate();

      educationalMaterial.setContent(newContent);
      educationalMaterial.setCreated(newCreatedTimestamp);
      return educationalMaterial;
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
  public EducationalMaterials delete(EducationalMaterials educationalMaterial) throws SQLException {
    String deleteEducationalMaterial = "DELETE FROM EducationalMaterials WHERE MaterialId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteEducationalMaterial);
      deleteStmt.setInt(1, educationalMaterial.getMaterialId());
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


  // getEducationalMaterialsByUserName
  // The username of the admin is set as Beginner, Intermediate, Advanced, Expert -- updated by Yuhan Dec 06
  public List<EducationalMaterials> getEducationalMaterialsByUserName (String userName) throws SQLException {
    List<EducationalMaterials> educationalMaterials = new ArrayList<>();
    String selectEducationalMaterial =
        "SELECT MaterialId, Title, EducationalMaterials.Content, EducationalMaterials.Created, Published, EducationalMaterials.UserName " +
        "FROM EducationalMaterials INNER JOIN Users ON EducationalMaterials.UserName=Users.UserName " +
        "WHERE EducationalMaterials.UserName=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;

    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectEducationalMaterial);
      selectStmt.setString(1, userName);
      results = selectStmt.executeQuery();
//      AdminDao adminDao = AdminDao.getInstance(); -- Yuhan Dec07

      while(results.next()) {
        int materialId = results.getInt("MaterialId");
        String title = results.getString("Title");
        String content = results.getString("Content");
        Date created =  new Date(results.getTimestamp("Created").getTime());
        Boolean published = results.getBoolean("Published");
//        Admin admin = adminDao.getAdminFromUserName(userName);
        String username = results.getString(userName); 
//        EducationalMaterials educationalMaterial = new EducationalMaterials(materialId,title,content,created,published,admin);
        EducationalMaterials educationalMaterial = new EducationalMaterials(materialId,title,content,created,published,username);
        educationalMaterials.add(educationalMaterial);
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
    return educationalMaterials;
  }


}