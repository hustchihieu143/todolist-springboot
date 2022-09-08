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
@Table(name = "exam_results")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseEntityListener.class)
public class ExamResult extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exam_application_id", referencedColumnName = "id")
  private ExamApplication examApplication;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "examResult")
  List<ExamResultDetail> examApplicationDetails;

  @Column(name = "num_correct_answers")
  private int numCorrectAnswers;

  @Column(name = "time_taken")
  private long timeTaken;
}
