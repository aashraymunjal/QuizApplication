package com.munjal.QuizApplication.Dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.munjal.QuizApplication.Model.Question;

@DataJpaTest
class QuestionDaoTest {

	@MockBean
	QuestionDao dao;


	@BeforeEach
	void setup() {
		Question question = new Question("Java sample question", "op1", "option2", "option3", "option4", "rightAnswer",
				"difficulty_level", "category");
		dao.save(question);
	}

	@Test
	void testFindBycategory_found() {
		List<Question> list = dao.findBycategory("category");
		System.out.println("hello");
		System.out.println(list);
	}

}
