package com.example.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.model.Student;
import com.example.sample.repo.StudentRepo;

@Service
public class StudentServiceClass implements StudentService {

	@Autowired
	private StudentRepo studentRepo;

	@Override
	public Student saveStudent(Student student) {
		return studentRepo.save(student);
	}

	@Override
	public List<Student> fetchStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student updateStudent(Student student, Integer id) {

		return null;
	}

	@Override
	public void deleteById(int studentId) {
		studentRepo.deleteById(studentId);
	}

}
