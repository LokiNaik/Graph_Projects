package com.example.sample.service;

import java.util.List;

import com.example.sample.model.Student;

public interface StudentService {
	
	//Save Operation
	Student saveStudent(Student student);
	
	 // Read operation
    List <Student> fetchStudents();
 
    // Update operation
    Student updateStudent(Student student,
                                Integer id);
 
    // Delete operation
    void deleteById(int studentId);

}
