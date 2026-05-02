package com.sga.assignment.repository;

import com.sga.assignment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query method that performs an inner join between Student and Course
    @Query("SELECT s FROM Student s JOIN FETCH s.course")
    List<Student> findAllStudentsWithCourses();
}
