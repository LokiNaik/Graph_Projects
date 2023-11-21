package com.example.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crud.model.Teacher;

public interface TeacherRepo extends MongoRepository<Teacher, Integer> {

}
