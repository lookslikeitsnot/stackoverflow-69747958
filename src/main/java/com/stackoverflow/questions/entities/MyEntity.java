package com.stackoverflow.questions.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "myentity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(MyIdClass.class)
public class MyEntity {

  @Id
  @Column(updatable = false)
  private String foo;

  @Id
  @Column(updatable = false)
  private String bar;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "my_foreign_key", referencedColumnName = "external_pk")
  private AnotherEntity anotherEntity;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "foo",
          column = @Column(name = "foo", insertable = false, updatable = false)),
      @AttributeOverride(name = "bar",
          column = @Column(name = "bar", insertable = false, updatable = false))})
  private MyIdClass id;
}
