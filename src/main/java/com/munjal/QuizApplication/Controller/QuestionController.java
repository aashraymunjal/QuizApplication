package com.munjal.QuizApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munjal.QuizApplication.Model.Question;
import com.munjal.QuizApplication.Service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("category") String category) {
		return questionService.getQuestionsByCategory(category);
	}

	@PostMapping("/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		return questionService.delete(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Question> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
		return questionService.updateQuestion(id, question);

	}
}
