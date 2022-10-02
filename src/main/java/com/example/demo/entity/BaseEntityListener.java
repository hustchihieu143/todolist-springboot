package com.example.demo.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import java.sql.Timestamp;

public class BaseEntityListener {
  /**
   * Prepare before create.
   *
   * @param baseModel the base id entity
   */
  @PrePersist
  public void prepareBeforeCreate(BaseEntity baseModel) {
    baseModel.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    baseModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
  }

  /**
   * Prepare before update.
   *
   * @param baseModel the base id entity
   */
  @PreUpdate
  public void prepareBeforeUpdate(BaseEntity baseModel) {
    baseModel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
  }
}
