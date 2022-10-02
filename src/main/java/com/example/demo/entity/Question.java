package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.demo.common.Difficulty;
import com.example.demo.common.QuestionType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "questions")
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = BaseEntityListener.class)
public class Question extends BaseEntity {
  @Enumerated()
  private QuestionType type;

  @Column(name = "question_text")
  private String questionText;

  @Enumerated()
  private Difficulty difficulty;

  @Column(name = "explanation")
  private String explanation;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonManagedReference("question_answers")
  private List<Answer> answers = new ArrayList<>();

  @ManyToMany(targetEntity = QuestionList.class, fetch = FetchType.LAZY)
  @JoinTable(
      name = "question_list_details",
      joinColumns = @JoinColumn(name = "question_id"),
      inverseJoinColumns = @JoinColumn(name = "question_list_id")
  )
  private List<QuestionList> questionLists;

}
