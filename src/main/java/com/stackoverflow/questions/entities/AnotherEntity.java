package com.stackoverflow.questions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "anotherentity")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AnotherEntity {

  @Id
  @Column(name = "external_pk", nullable = false, updatable = false)
  private String externalPk;
}
