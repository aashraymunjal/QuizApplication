package com.munjal.QuizApplication.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.munjal.QuizApplication.Dao.QuestionDao;
import com.munjal.QuizApplication.Model.Question;

@SpringBootTest
class QuestionServiceTest {

	@Autowired
	QuestionService service;
	@MockBean
	QuestionDao dao;

	@BeforeEach
	void setUp() throws Exception {
		Question question = new Question("Java sample question", "op1", "option2", "option3", "option4", "rightAnswer",
				"difficulty_level", "category");
		Mockito.when(dao.findBycategory("category")).thenReturn((List<Question>) question);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllQuestions() {

	}

	@Test
	void testGetQuestionsByCategory() {
		Question question1 = new Question("Java sample question", "op1", "option2", "option3", "option4", "rightAnswer",
				"difficulty_level", "category");
		ResponseEntity<List<Question>> result = service.getQuestionsByCategory(question1.getCategory());
		assertEquals("category", result.getBody().get(0).getCategory());
	}

	@Test
	void testAddQuestion() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateQuestion() {
		fail("Not yet implemented");
	}

}
