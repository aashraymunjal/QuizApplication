package com.munjal.QuizApplication.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.munjal.QuizApplication.Controller.QuestionController;
import com.munjal.QuizApplication.Dao.QuestionDao;
import com.munjal.QuizApplication.Dao.QuizDao;
import com.munjal.QuizApplication.Model.Question;
import com.munjal.QuizApplication.Model.QuestionWrapper;
import com.munjal.QuizApplication.Model.Quiz;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions = questionDao.FindRandomQuestionsByCategory(category, numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);

		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questionsfromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionforuser = new ArrayList();
		for (Question q : questionsfromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
					q.getOption3(), q.getOption4(), q.getCategory());
			questionforuser.add(qw);

		}
		return new ResponseEntity<>(questionforuser, HttpStatus.OK);

	}

}
