package com.miniproject.Quizziz.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.Quizziz.model.Classroom;
import com.miniproject.Quizziz.model.Student;
import com.miniproject.Quizziz.model.Teacher;
import com.miniproject.Quizziz.repository.ClassroomRepository;
import com.miniproject.Quizziz.repository.StudentRepository;
import com.miniproject.Quizziz.repository.TeacherRepository;
import com.miniproject.Quizziz.service.ClassroomService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassroomServiceImplementation implements ClassroomService{
	
	@Autowired ClassroomRepository classroomRepo;
	@Autowired TeacherRepository teacherRepo;
	@Autowired StudentRepository studentRepo;

	@Override
	public Classroom addClassroom(Integer teacherId, Classroom classroom) {
		Teacher teacher = teacherRepo.findById(teacherId)
				.orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + teacherId));
		classroom.setTeacher(teacher);
		teacher.getClassrooms().add(classroom);
		return classroomRepo.save(classroom);
	}

	@Override
	public List<Classroom> getAllClassrooms() {
		return classroomRepo.findAll();
	}
	
	@Override
	public Classroom editClassroom(Long id, Classroom classroom) {
		Classroom updatedClassroom = classroomRepo.findById(id).orElse(null);
		updatedClassroom.setTitle(classroom.getTitle());
		updatedClassroom.setDescription(classroom.getDescription());
		classroomRepo.save(updatedClassroom);
		return updatedClassroom;
	}

	@Override
	public String deleteClassroom(Long id) {
		classroomRepo.deleteById(id);
		return "Classrooom Deleted Successfully";
		
	}

	@Override
	public void addStudentToClassroom(Long classroom_id, Integer student_id) {
		// TODO Auto-generated method stub
		Classroom classroom = classroomRepo.findById(classroom_id)
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + classroom_id));

        Student student = studentRepo.findById(student_id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + student_id));

        // Add the student to the classroom
        classroom.getStudents().add(student);
        classroomRepo.save(classroom);
	}

	@Override
	public List<Student> getStudentsByClassroomId(Long classroomId) {
		Classroom classroom = classroomRepo.findById(classroomId)
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + classroomId));

        return classroom.getStudents();
	}
	
	

}
