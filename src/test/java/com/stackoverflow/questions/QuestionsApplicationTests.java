package com.stackoverflow.questions;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import com.stackoverflow.questions.entities.MyEmbeddedId;
import com.stackoverflow.questions.entities.MyEntity;
import com.stackoverflow.questions.entities.MyIdClass;
import com.stackoverflow.questions.repositories.AnotherEntityRepository;
import com.stackoverflow.questions.repositories.MyEntityRepository;
import com.stackoverflow.questions.repositories.MyEntityWithEmbeddedIdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuestionsApplicationTests {
	@Autowired
	private MyEntityRepository myEntityRepository;

	@Autowired
	private AnotherEntityRepository anotherEntityRepository;

	@Autowired
	private MyEntityWithEmbeddedIdRepository myEntityWithEmbeddedIdRepository;

	@Test
	void contextLoads() {}

	@Test
	@Transactional
	void deleteByInWithQuery_multipleIds_allDeleted() {
		assertEquals(4, ((Collection<MyEntity>) myEntityRepository.findAll()).size());
		MyIdClass id1 = new MyIdClass("foo1", "bar1");
		MyIdClass id2 = new MyIdClass("foo2", "bar2");

		assertDoesNotThrow(() -> myEntityRepository.deleteByIdInWithQuery(List.of(id1, id2)));
		assertEquals(2, ((Collection<MyEntity>) myEntityRepository.findAll()).size());
	}

	// With query, there's a single delete instruction but no cascading.
	@Test
	@Transactional
	void deleteByInWithQuery_multipleIds_allDeletedButNoCascades() {
		assertEquals(8, anotherEntityRepository.findAll().size());
		MyIdClass id1 = new MyIdClass("foo1", "bar1");
		MyIdClass id2 = new MyIdClass("foo2", "bar2");

		assertDoesNotThrow(() -> myEntityRepository.deleteByIdInWithQuery(List.of(id1, id2)));
		assertEquals(8, anotherEntityRepository.findAll().size());

	}


	// With derived query, there is cascading but multiple queries.
	@Test
	@Transactional
	void deleteByIn_multipleIds_allDeletedWithCascades() {
		assertEquals(8, anotherEntityRepository.findAll().size());
		assertEquals(4, ((Collection<MyEntity>) myEntityRepository.findAll()).size());
		MyIdClass id1 = new MyIdClass("foo1", "bar1");
		MyIdClass id2 = new MyIdClass("foo2", "bar2");

		assertDoesNotThrow(() -> myEntityRepository.deleteByIdIn(List.of(id1, id2)));
		assertEquals(2, ((Collection<MyEntity>) myEntityRepository.findAll()).size());
		assertEquals(6, anotherEntityRepository.findAll().size());
	}

	// Just for the giggles
	@Test
	@Transactional
	void deleteByMyIdClassIn_multipleIds_allDeletedWithCascades() {
		assertEquals(8, anotherEntityRepository.findAll().size());
		assertEquals(4, ((Collection<MyEntity>) myEntityRepository.findAll()).size());
		MyIdClass id1 = new MyIdClass("foo1", "bar1");
		MyIdClass id2 = new MyIdClass("foo2", "bar2");

		assertDoesNotThrow(() -> myEntityRepository.deleteByMyIdClassIn(List.of(id1, id2)));
		assertEquals(2, ((Collection<MyEntity>) myEntityRepository.findAll()).size());
		assertEquals(6, anotherEntityRepository.findAll().size());
	}

	@Test
	@Transactional
	void deleteByIdIn_multipleEmbeddedIds_allDeletedAndCascaded() {
		assertEquals(8, anotherEntityRepository.findAll().size());
		assertEquals(4, myEntityWithEmbeddedIdRepository.findAll().size());

		MyEmbeddedId id1 = new MyEmbeddedId("foo1", "bar1");
		MyEmbeddedId id2 = new MyEmbeddedId("foo2", "bar2");

		assertDoesNotThrow(() -> myEntityWithEmbeddedIdRepository.deleteByIdIn(List.of(id1, id2)));
		assertEquals(2, myEntityWithEmbeddedIdRepository.findAll().size());
		assertEquals(6, anotherEntityRepository.findAll().size());
	}
}
