package com.example.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.crud.model.Teacher;
import com.example.crud.repository.TeacherRepo;

@RestController
public class TeacherController {
	
	@Autowired
	private TeacherRepo teacherRepo;
	
	@GetMapping("/teachers")
	public List<Teacher> getAllStudents() {
		return teacherRepo.findAll();
	}
	
	@PostMapping("/saveTeachers")
	public int saveTeachers(@RequestBody List<Teacher> teacher) {
		return teacherRepo.saveAll(teacher).size();
	}
	
	@PostMapping("/saveTeacher")
	public Teacher saveTeacher(@RequestBody Teacher teacher) {
		return teacherRepo.save(teacher);
	}
	
	

}
