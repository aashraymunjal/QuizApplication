package com.munjal.QuizApplication.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.munjal.QuizApplication.Dao.QuestionDao;
import com.munjal.QuizApplication.Model.Question;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

	@InjectMocks
	QuestionService service;

	@Mock
	QuestionDao dao;

	@Test
	@DisplayName("Testing get all questions Success")
	void testGetAllQuestionsSuccess() {

		Question q1 = new Question();
		q1.setCategory("java");
		List<Question> savedList = new ArrayList<>();
		savedList.add(q1);
		Mockito.when(dao.findAll()).thenReturn(savedList);
		ResponseEntity<List<Question>> list = service.getAllQuestions();
		Assertions.assertNotNull(list.getBody());
		Assertions.assertEquals(list.getBody().get(0).getCategory(), q1.getCategory());
		Assertions.assertEquals(list.getStatusCode(), HttpStatus.valueOf(200));

	}

	@Test
	@DisplayName("Testing get all questions Fails")
	void testGetAllQuestionsFails() {

		Question q1 = new Question();
		q1.setCategory("java");
		List<Question> savedList = new ArrayList<>();
		Mockito.when(dao.findAll()).thenReturn(savedList);
		ResponseEntity<List<Question>> list = service.getAllQuestions();
		Assertions.assertNull(list.getBody());
		Assertions.assertEquals(list.getStatusCode(), HttpStatus.valueOf(204));

	}

	@Test
	@DisplayName("Testing get Questions by category success")
	void testGetQyestuibsByCategorySuccess() {
		String category = "Java";
		Question q1 = new Question();
		q1.setCategory(category);
		List<Question> savedList = new ArrayList<>();
		savedList.add(q1);
		Mockito.when(dao.findBycategory(category)).thenReturn(savedList);
		ResponseEntity<List<Question>> list = service.getQuestionsByCategory(category);
		Assertions.assertNotNull(list.getBody());
		Assertions.assertEquals(list.getBody().get(0).getCategory(), q1.getCategory());
	}

	@Test
	@DisplayName("Testing get Questions by category fails")
	void testGetQyestuibsByCategoryFails() {
		String category = "Java";
		Question q1 = new Question();
		q1.setCategory(category);
		List<Question> savedList = new ArrayList<>();
		Mockito.when(dao.findBycategory(category)).thenReturn(savedList);
		ResponseEntity<List<Question>> list = service.getQuestionsByCategory(category);
		Assertions.assertEquals(list.getBody().size(), 0);
		Assertions.assertEquals(list.getStatusCode(), HttpStatus.valueOf(200));

	}

	@Test
	@DisplayName("Testing add Questions by category")
	void testAddQuestions() {
		Question question = new Question();
		question.setCategory("java");
		Mockito.when(dao.save(question)).thenReturn(null);
		ResponseEntity<String> rs = service.addQuestion(question);
		Assertions.assertEquals(rs.getBody(), "Added! " + question);
		Assertions.assertEquals(rs.getStatusCode(), HttpStatus.valueOf(201));

	}

	@Test
	@DisplayName("Testing add Questions by category Fails")
	void testAddQuestionsFails() {
		Question question = null;
		ResponseEntity<String> rs = service.addQuestion(question);
		Assertions.assertEquals(rs.getBody(), "Empty question passed by user!");
		Assertions.assertEquals(rs.getStatusCode(), HttpStatus.valueOf(204));

	}

	@Test
	@DisplayName("Testing delete Questions by id Passes")
	void testDeleteSuccess() {
		int id = 1;
		Question question = new Question();
		question.setCategory("Java");
		question.setId(id);
		Mockito.when(dao.findById(id)).thenReturn(Optional.ofNullable(question));
		doNothing().when(dao).deleteById(1);
		ResponseEntity<String> rs = service.delete(id);
		Assertions.assertEquals(rs.getBody(), "Deleted id: " + id);
		Assertions.assertEquals(rs.getStatusCode(), HttpStatusCode.valueOf(200));
	}

	@Test
	@DisplayName("Testing delete Questions by id Exception")
	void testDeleteFailCase() {
		int id = 0;
		Question question = new Question();
		question.setCategory("Java");
		question.setId(id);
		ResponseEntity<String> rs = service.delete(id);
		Assertions.assertEquals(rs.getBody(), "Question not present in DB!");
		Assertions.assertEquals(rs.getStatusCode(), HttpStatusCode.valueOf(204));
	}

	@Test
	@DisplayName("Testing updateQuestion")
	void testUpdateQuestion() {
		Question oldquestion = new Question();
		oldquestion.setCategory("Java");
		oldquestion.setId(1);
		Question newquestion = new Question();
		newquestion.setCategory("Python");
		newquestion.setId(1);
		
		Mockito.when(dao.findById(Mockito.any())).thenReturn(Optional.ofNullable(oldquestion));
		Mockito.when(dao.save(Mockito.any())).thenReturn(newquestion);
		ResponseEntity<Question> rs = service.updateQuestion(1, newquestion);
		Assertions.assertEquals(rs.getBody().getCategory(), newquestion.getCategory());
	}
	@Test
	@DisplayName("Testing updateQuestion")
	void testUpdateQuestionFail() {
		Question oldquestion = new Question();
		oldquestion.setCategory("Java");
		oldquestion.setId(1);
		Question newquestion = new Question();
		newquestion.setCategory("Python");
		newquestion.setId(1);
		ResponseEntity<Question> rs = service.updateQuestion(1, newquestion);
		System.out.println(rs.getBody());
		Assertions.assertEquals(rs.getStatusCode(),HttpStatusCode.valueOf(204));
	}

}
