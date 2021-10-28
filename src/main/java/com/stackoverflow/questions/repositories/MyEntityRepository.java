package com.stackoverflow.questions.repositories;

import java.util.Collection;
import com.stackoverflow.questions.entities.MyEntity;
import com.stackoverflow.questions.entities.MyIdClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MyEntityRepository extends JpaRepository<MyEntity, MyIdClass> {
  @Modifying
  @Query("DELETE FROM MyEntity me WHERE me.id in (:ids)")
  void deleteByIdInWithQuery(@Param("ids") Collection<MyIdClass> ids);

  void deleteByIdIn(Collection<MyIdClass> ids);
}
