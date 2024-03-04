package com.munjal.QuizApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munjal.QuizApplication.Dao.QuestionDao;
import com.munjal.QuizApplication.Model.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionDao questionDao;

	public List<Question> getAllQuestions() {
		return questionDao.findAll();

	}

	public List<Question> getQuestionsByCategory(String category) {
		return questionDao.findBycategory(category);

	}

	public String addQuestion(Question question) {
		questionDao.save(question);
		return "Added: " + question.toString();
	}

}
