package com.stackoverflow.questions.entities;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "myentity_embeddedid")
@Entity
public class MyEntityWithEmbeddedId {
  @EmbeddedId
  MyEmbeddedId id;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "my_foreign_key", referencedColumnName = "external_pk")
  private AnotherEntity anotherEntity;
}
