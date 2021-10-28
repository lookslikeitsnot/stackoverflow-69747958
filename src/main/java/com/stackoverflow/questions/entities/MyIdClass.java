package com.stackoverflow.questions.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyIdClass implements Serializable {

  private String foo;
  private String bar;
}
