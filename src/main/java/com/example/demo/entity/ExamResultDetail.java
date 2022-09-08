package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "exam_result_details")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseEntityListener.class)
public class ExamResultDetail extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "exam_result_id", referencedColumnName = "id")
  ExamResult examResult;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_id", referencedColumnName = "id")
  Question question;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "answer_id", referencedColumnName = "id")
  Answer answer;
}
