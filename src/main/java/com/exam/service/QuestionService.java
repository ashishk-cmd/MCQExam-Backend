package com.exam.service;

import java.util.Set;

import com.exam.modal.exam.Question;
import com.exam.modal.exam.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestion();
	
	public Question getQuestion(Long questionId);
	
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);

	public void deleteQuestion(Long questionId);
	
}
