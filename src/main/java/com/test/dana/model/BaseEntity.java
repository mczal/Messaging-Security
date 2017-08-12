package com.test.dana.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by mczal on 8/10/17.
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity implements Serializable {
  private static final String CREATED_BY = "createdBy";

  private static final String CREATED_DATE = "CREATED_DATE";

  private static final String ID = "ID";

  private static final String MARK_FOR_DELETE = "MARK_FOR_DELETE";

  private static final String OPTLOCK = "OPTLOCK";

  private static final String UPDATED_BY = "updatedBy";

  private static final String UPDATED_DATE = "UPDATED_DATE";

  @CreatedBy
  @Column(name = CREATED_BY)
  private String createdBy;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = CREATED_DATE,
      nullable = false)
  private Date createdDate;

  @Id
  @Column(name = ID)
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid",
      strategy = "uuid2")
  private String id;

  @Column(name = MARK_FOR_DELETE)
  private Boolean markForDelete = false;

  @LastModifiedBy
  @Column(name = UPDATED_BY)
  private String updatedBy;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = UPDATED_DATE)
  private Date updatedDate;

  @Column(name = OPTLOCK)
  @Version
  private Long version;

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

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

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
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
