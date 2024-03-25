package com.munjal.QuizApplication.Controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.munjal.QuizApplication.Model.Question;
import com.munjal.QuizApplication.Service.QuestionService;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

	@InjectMocks
	QuestionController controller;

	@Mock
	private QuestionService service;

	@Test
	@DisplayName("Test Getting All Questions")
	void testGetAllQuestions() {
		List<Question> savedList = new ArrayList<>();

		Question q1 = new Question();
		q1.setCategory("Java");
		Question q2 = new Question();
		q2.setCategory("Python");

		savedList.add(q1);
		savedList.add(q2);
		ResponseEntity<List<Question>> savedRs = new ResponseEntity<>(savedList, HttpStatus.OK);

		Mockito.when(service.getAllQuestions()).thenReturn(savedRs);

		ResponseEntity<List<Question>> list = controller.getAllQuestions();
		Assertions.assertEquals(list.getBody().get(0).getCategory(), q1.getCategory());
		Assertions.assertEquals(list.getBody().get(1).getCategory(), q2.getCategory());
	}

	@Test
	@DisplayName("Test Getting Questions by category")
	void testGetQuestionsByCategory() {
		List<Question> savedList = new ArrayList<>();
		String category = "Java";

		Question q1 = new Question();
		q1.setCategory("Java");
		Question q2 = new Question();
		q2.setCategory("Python");

		savedList.add(q1);
		savedList.add(q2);
		ResponseEntity<List<Question>> savedRs = new ResponseEntity<>(savedList, HttpStatus.OK);
		Mockito.when(service.getQuestionsByCategory(category)).thenReturn(savedRs);
		ResponseEntity<List<Question>> list = controller.getQuestionsByCategory(category);
		Assertions.assertNotNull(list);
		Assertions.assertEquals(list.getBody().size(), 2);
		Assertions.assertEquals(list.getBody().get(0).getCategory(), category);
	}

	@Test
	@DisplayName("Test to add question functionality")
	void testAddQuestion() {
		Question inputQ = new Question();
		inputQ.setCategory("Java");
		ResponseEntity<String> savedRs = new ResponseEntity<>("saved", HttpStatus.OK);
		Mockito.when(service.addQuestion(inputQ)).thenReturn(savedRs);
		ResponseEntity<String> rs = controller.addQuestion(inputQ);
		Assertions.assertNotNull(rs.getBody());
		Assertions.assertEquals(rs.getBody(), "saved");

	}

	@Test
	@DisplayName("Testing Delete functionality")
	void testDelete() {
		int id = 1;
		ResponseEntity<String> deletedrs = new ResponseEntity<>("Deleted", HttpStatus.OK);
		Mockito.when(service.delete(id)).thenReturn(deletedrs);
		ResponseEntity<String> rs = controller.delete(id);
		Assertions.assertEquals(rs.getBody(), "Deleted");
	}
	
	@Test
	@DisplayName("Testing update Question by id")
	void testUpdateQuestion() {
		int id=1;
		
		Question updatedQuestion = new Question();
		updatedQuestion.setId(1);
		updatedQuestion.setCategory("Java");
		ResponseEntity<Question> updatedrs = new ResponseEntity<>(updatedQuestion,HttpStatus.OK);
		Mockito.when(service.updateQuestion(id, updatedQuestion)).thenReturn(updatedrs);
		ResponseEntity<Question> rs = controller.updateQuestion(id, updatedQuestion);
		Assertions.assertNotNull(rs);
		Assertions.assertEquals(rs.getBody().getCategory(),"Java");
	}
	
}
