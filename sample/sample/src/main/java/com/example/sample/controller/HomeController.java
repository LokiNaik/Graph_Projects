package com.example.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.model.Student;
import com.example.sample.repo.StudentRepo;
import com.example.sample.service.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@PostMapping("/save")
	public Student saveStudentController(@RequestBody Student student) {
		return studentService.saveStudent(student);
	}
	
	 @PostMapping("/addStudent")
	    public String saveStudent(@RequestBody Student student){
		 studentRepo.save(student);
	       
	        return "Added Successfully";
	    }
	
	@GetMapping("/students")
    public List<Student> getAllStudents() {
//		Document d = (Document) studentService.fetchStudents();
//        return d;
        return studentRepo.findAll();
        		
    }
	
	

}
