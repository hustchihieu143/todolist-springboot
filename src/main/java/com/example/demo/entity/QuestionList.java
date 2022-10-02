package com.example.demo.entity;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "question_lists")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@EntityListeners(value = BaseEntityListener.class)
public class QuestionList extends BaseEntity {
  @Column(name = "code")
  private String code;

  @Column(name = "name")
  private String name;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionList")
  List<QuestionListDetail> details;

  @ManyToMany(targetEntity = Question.class, fetch = FetchType.LAZY)
  @JoinTable(
          name = "question_list_details",
          joinColumns = @JoinColumn(name = "question_list_id"),
          inverseJoinColumns = @JoinColumn(name = "question_id")
  )
  private List<Question> questions;
}
