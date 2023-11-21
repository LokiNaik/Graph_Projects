package com.example.crud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud.model.Student;
import com.example.crud.repository.StudentRepo;

@RestController
public class StudentController {

	@Autowired
	private StudentRepo studentRepo;

	@GetMapping("/students")
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@PostMapping("/saveStudents")
	public int saveStudents(@RequestBody List<Student> student) {
		return studentRepo.saveAll(student).size();
	}

}
