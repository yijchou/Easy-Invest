package EasyInvest.model;

import java.util.Date;


public class EducationalMaterials {
  protected int materialId;
  protected String title;
  protected String content;
  protected Date created;
  protected boolean published;
//  protected Admin user; // update to string -- Yuhan Dec 07
  protected String UserName;


  public EducationalMaterials(String title, String content, Date created, boolean published, String userName) {
	super();
	this.title = title;
	this.content = content;
	this.created = created;
	this.published = published;
	UserName = userName;
}

public EducationalMaterials(int materialId, String title, String content, Date created, boolean published,
		String userName) {
	super();
	this.materialId = materialId;
	this.title = title;
	this.content = content;
	this.created = created;
	this.published = published;
	UserName = userName;
}

public EducationalMaterials(int materialId) {
	super();
	this.materialId = materialId;
}



public int getMaterialId() {
    return materialId;
  }

  public void setMaterialId(int materialId) {
    this.materialId = materialId;
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

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public String getUserName() {
    return UserName;
  }

  public void setUserName(String userName) {
    UserName = userName;
  }
}