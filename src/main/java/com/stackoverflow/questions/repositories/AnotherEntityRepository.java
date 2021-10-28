package com.stackoverflow.questions.repositories;

import com.stackoverflow.questions.entities.AnotherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnotherEntityRepository extends JpaRepository<AnotherEntity, String> {

}
