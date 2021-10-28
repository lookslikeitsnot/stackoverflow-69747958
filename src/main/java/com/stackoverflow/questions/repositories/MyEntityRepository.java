package com.stackoverflow.questions.repositories;

import java.util.Collection;
import javax.persistence.criteria.Predicate;
import com.stackoverflow.questions.entities.MyEntity;
import com.stackoverflow.questions.entities.MyIdClass;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MyEntityRepository
    extends JpaRepository<MyEntity, MyIdClass>, JpaSpecificationExecutor<MyEntity> {
  @Modifying
  @Query("DELETE FROM MyEntity me WHERE me.id in (:ids)")
  void deleteByIdInWithQuery(@Param("ids") Collection<MyIdClass> ids);

  void deleteByIdIn(Collection<MyIdClass> ids);

  default void deleteByMyIdClassIn(Collection<MyIdClass> ids) {
    if (ids == null)
      throw new NullPointerException("Null ids in when trying to delete by ids !");
    Specification<MyEntity> spec =
        Specification
            .where(
                (root, query,
                    builder) -> builder.or(ids.stream()
                        .map(id -> builder.and(builder.equal(root.get("bar"), id.getBar()),
                            builder.equal(root.get("foo"), id.getFoo())))
                        .toArray(Predicate[]::new)));

    deleteAll(findAll(spec));
  }
}
