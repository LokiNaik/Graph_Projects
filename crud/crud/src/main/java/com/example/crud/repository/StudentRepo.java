package com.example.crud.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crud.model.Student;

public interface StudentRepo extends MongoRepository<Student, ObjectId>{

}
