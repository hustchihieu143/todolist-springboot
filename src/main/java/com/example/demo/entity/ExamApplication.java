package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "exam_applications")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseEntityListener.class)
public class ExamApplication extends BaseEntity {
  @Column(name = "type")
  private int type;

  @Column(name = "status")
  private int status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exam_id", referencedColumnName = "id")
  private Exam exam;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "examApplication")
  List<ExamResult> examResults;
}
