package com.munjal.QuizApplication.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.munjal.QuizApplication.Dao.QuestionDao;
import com.munjal.QuizApplication.Model.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			List<Question> questions = questionDao.findAll();
			if (questions.size() > 0) {
				return new ResponseEntity<>(questions, HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			List<Question> questions = questionDao.findBycategory(category);
			if (questions != null) {
				return new ResponseEntity<>(questions, HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
			if (question != null) {
				questionDao.save(question);
				return new ResponseEntity<>("Added! " + question, HttpStatus.CREATED);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Empty question passed by user!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong here!");
		}

	}

	public ResponseEntity<String> delete(int id) {
		try {
			if (id > 0 && questionDao.findById(id).isPresent()) {
				questionDao.deleteById(id);
				return new ResponseEntity<>("Deleted id: " +id, HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Question not present in DB!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong here!");
		}

	}

	public ResponseEntity<Question> updateQuestion(int id, Question question) {
		try {
			Optional<Question> q = questionDao.findById(id);
			Question old = new Question();
			if (questionDao.findById(id).isPresent()) {
				old = q.get();
				old.setQuestionTitle(question.getQuestionTitle());
				old.setOption1(question.getOption1());
				old.setOption2(question.getOption2());
				old.setOption3(question.getOption3());
				old.setOption4(question.getOption4());
				old.setRightAnswer(question.getRightAnswer());
				old.setDifficulty_level(question.getDifficulty_level());
				old.setCategory(question.getCategory());
				questionDao.save(old);
				return new ResponseEntity<>(old, HttpStatus.OK);

			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(question);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
