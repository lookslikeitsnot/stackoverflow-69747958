package com.stackoverflow.questions.repositories;

import java.util.Collection;
import com.stackoverflow.questions.entities.MyEmbeddedId;
import com.stackoverflow.questions.entities.MyEntityWithEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyEntityWithEmbeddedIdRepository
    extends JpaRepository<MyEntityWithEmbeddedId, MyEmbeddedId> {
      
  void deleteByIdIn(Collection<MyEmbeddedId> ids);
}
