package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Question;
import com.miniproject.Quizziz.model.Quiz;
import com.miniproject.Quizziz.model.Teacher;
import com.miniproject.Quizziz.repository.ClassroomRepository;
import com.miniproject.Quizziz.repository.QuestionRepository;
import com.miniproject.Quizziz.repository.QuizRepository;
import com.miniproject.Quizziz.repository.TeacherRepository;
import com.miniproject.Quizziz.service.QuizService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class QuizServiceImplementation implements QuizService {

	@Autowired
	QuizRepository quizRepo;
	@Autowired
	QuestionRepository questionRepo;

	@Autowired
	TeacherRepository teacherRepo;
	@Autowired
	ClassroomRepository classroomRepo;

	@Override
	public List<Quiz> getAllQuizzes() {
		return quizRepo.getAllQuizzes();
	}

	@Override
	public Quiz createQuiz(Quiz quiz) {
		return quizRepo.save(quiz);
	}

	@Override
	public String deleteQuiz(Integer id) {
		quizRepo.deleteById(id);
		return "Quiz has been deleted";
	}

	@Override
	public Quiz editQuiz(Integer id, Quiz quiz) {
		Quiz updatedQuiz = quizRepo.findById(id).orElse(null);
		updatedQuiz.setTitle(quiz.getTitle());
		updatedQuiz.setDescription(quiz.getDescription());
		updatedQuiz.setQuiz_date(quiz.getQuiz_date());
		updatedQuiz.setTimer(quiz.getTimer());
		updatedQuiz.setEnd_time(quiz.getEnd_time());
		updatedQuiz.setStart_time(quiz.getStart_time());
		quizRepo.save(updatedQuiz);
		return updatedQuiz;
	}

	@Override
	public Question addQuestionToQuiz(Integer quizId, Question question) {
		Quiz quiz = quizRepo.findById(quizId)
				.orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + quizId));

		question.setQuiz(quiz);
		quiz.getQuestions().add(question);
		return questionRepo.save(question);
	}

	@Override
	public Quiz addTeacherIdToQuiz(Integer teacherId, Quiz quiz) {
		Teacher teacher = teacherRepo.findById(teacherId)
				.orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));
		quiz.setTeacher(teacher);
		teacher.getQuizzes().add(quiz);
		return quizRepo.save(quiz);
	}

	@Override
	public Quiz addQuizToClassroom(Integer teacherId, Long classroomID, Quiz quiz) {
		Teacher teacher = teacherRepo.findById(teacherId)
				.orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));
		Classroom classroom = classroomRepo.findById(classroomID)
				.orElseThrow(() -> new EntityNotFoundException("Classroom not found with id: " + classroomID));
		quiz.setTeacher(teacher);
		quiz.setClassroom(classroom);
		return quizRepo.save(quiz);
	}

	@Override
	public List<Quiz> getQuizByClassroomID(Long classroomID) {
		return quizRepo.getQuizByClassroomID(classroomID);
	}

}
