package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, insertable = false, updatable = false)
  @EqualsAndHashCode.Include
  protected Long id;

  @Column(name = "created_at")
  protected Timestamp createdAt;

  @Column(name = "updated_at")
  protected Timestamp updatedAt;

  @Column(name = "is_deleted")
  protected Boolean isDeleted;
}
