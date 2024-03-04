package com.munjal.QuizApplication.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.munjal.QuizApplication.Model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
