package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "exams")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseEntityListener.class)
public class ExamEntity extends BaseEntity {
  @Column(name = "level")
  private int level;

  @Column(name = "name")
  private String name;

  @Column(name = "register_start_at")
  private Date register_start_at;

  @Column(name = "register_start_end")
  private Date register_start_end;

  @Column(name = "time_start")
  private Date time_start;

  @Column(name = "duration_in_minutes")
  private long duration_in_minutes;

  @Column(name = "status")
  private int status;

  @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
  List<ExamDetailEntity> exam_details;

  @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL)
  List<ExamApplicationEntity> exam_applications;
}
