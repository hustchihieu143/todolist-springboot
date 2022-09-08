package com.example.demo.entity;

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
@Table(name = "questions")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseEntityListener.class)
public class Question extends BaseEntity {
  @Column(name = "type")
  private int type;

  @Column(name = "question_text")
  private String question_text;

  @Column(name = "difficulty")
  private int difficulty;

  @Column(name = "explanation")
  private int explanation;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
  List<QuestionListDetail> question_list_detail;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
  List<ExamDetail> exam_detail;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
  List<ExamResultDetail> exam_result_detail;
}
