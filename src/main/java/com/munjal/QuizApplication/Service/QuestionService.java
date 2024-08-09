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
	QuestionDao dao;

	public List<Question> getAllQuestions() {
		return dao.findAll();
	}

	public Question adQuestion(Question question) {
		return dao.save(question);
	}

	public List<Question> getQuestionByCat(String category) {

		return dao.findByCategory(category);
	}

	public String deletebyid(int id) {

		if (dao.findById(id).isPresent()) {
			dao.deleteById(id);
			return "Deleted";
		} else {
			return "Failed to find id";
		}

	}

	public Question upadteQuestion(int id, Question question) {

		if (dao.findById(id).isPresent()) {
			Optional<Question> temp = dao.findById(id);
			Question saved = temp.get();
			saved.setCategory(question.getCategory());
			saved.setDifficulty_level(question.getDifficulty_level());
			saved.setOption1(question.getOption1());
			saved.setOption2(question.getOption2());
			saved.setOption3(question.getOption3());
			saved.setOption4(question.getOption4());

			dao.save(saved);
			return saved;
		} else {
			return null;
		}

	}

}

