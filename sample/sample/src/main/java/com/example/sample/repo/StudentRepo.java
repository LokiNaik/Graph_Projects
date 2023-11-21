package com.example.sample.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.sample.model.Student;

@Repository
public interface StudentRepo  extends MongoRepository<Student, Integer>{

}
