package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.ExceptionHandler.StudentRegistrationException;
import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Student;
import com.miniproject.Quizziz.repository.ClassroomRepository;
import com.miniproject.Quizziz.repository.StudentRepository;
import com.miniproject.Quizziz.service.StudentService;

@Service
public class StudentServiceImplementation implements StudentService {

	@Autowired
	StudentRepository studentRepo;
	@Autowired
	ClassroomRepository classroomRepo;

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	public void addStudent(Student student) {
		if (studentRepo.existsByUsername(student.getUsername())) {
			throw new StudentRegistrationException("Username already exists. Please choose a different username.");
		}

		if (studentRepo.existsByEmail(student.getEmail())) {
			throw new StudentRegistrationException("Email already exists. Please use a different email address.");
		} else {
			studentRepo.save(student);
		}
	}

	@Override
	public Student editStudentDetails(Integer id, Student student) {
		Student updatedStudent = studentRepo.findById(id).orElse(null);
		updatedStudent.setPassword(student.getPassword());
		updatedStudent.setUsername(student.getUsername());
		studentRepo.save(updatedStudent);
		return updatedStudent;
	}

	@Override
	public String deleteStudent(Integer id) {
		studentRepo.deleteById(id);
		return "Student has been deleted";
	}

	@Override
	public Optional<Student> login(Student student) {
		return studentRepo.findByUsernameAndPassword(student.getUsername(), student.getPassword());
	}

	@Override
	public void joinClassroom(Integer studentId, Long classroomId) {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

		Classroom classroom = classroomRepo.findById(classroomId)
				.orElseThrow(() -> new RuntimeException("Classroom not found with id: " + classroomId));

		if (student.getClassrooms().contains(classroom)) {
			throw new RuntimeException("Student is already in the classroom");
		}

		student.getClassrooms().add(classroom);
		studentRepo.save(student);
	}

	@Override
	public void removeStudentFromClassroom(Integer studentId, Long classroomId) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));;

		Classroom classroom = classroomRepo.findById(classroomId)
				.orElseThrow(() -> new RuntimeException("Classroom not found with id: " + classroomId));

		// Check if the student is in the classroom
		if (!student.getClassrooms().contains(classroom)) {
			throw new RuntimeException("Student is not in the classroom");
		}

		// Remove the student from the classroom
		student.getClassrooms().remove(classroom);
		studentRepo.save(student);
	}

	@Override
	public List<Classroom> getAllClassroomsForStudent(Integer studentId) {
		Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        return student.getClassrooms();
	}

	@Override
	public List<Classroom> getClassroomsNotJoinedByStudent(Integer studentId) {
		Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));

        List<Classroom> allClassrooms = classroomRepo.findAll();
        List<Classroom> joinedClassrooms = student.getClassrooms();

        // Use Java 8 streams to filter out the classrooms that the student has already joined
        return allClassrooms.stream()
                .filter(classroom -> !joinedClassrooms.contains(classroom))
                .collect(Collectors.toList());
	}

}
