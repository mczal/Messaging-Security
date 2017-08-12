package com.test.dana.dto.base;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mczal on 8/10/17.
 */
public class BaseResponse implements Serializable {

  private Date createdDate;

  private String id;

  private Boolean markForDelete;

  private Date updatedDate;

  private Long version;

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Boolean getMarkForDelete() {
    return markForDelete;
  }

  public void setMarkForDelete(Boolean markForDelete) {
    this.markForDelete = markForDelete;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }
}
