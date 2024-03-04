package com.munjal.QuizApplication.Service;

import java.util.List;
import java.util.Optional;
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

	public String delete(int id) {
		questionDao.deleteById(id);
		return "Deleted: " + id;
	}

	public Question updateQuestion(int id, Question question) {
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

		} else {
			return question;
		}

		return old;
	}

}
