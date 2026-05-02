package com.sga.assignment.service;

import com.sga.assignment.entity.Student;
import com.sga.assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // CREATE / UPDATE
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // READ using custom inner join query
    public List<Student> getAllStudents() {
        return studentRepository.findAllStudentsWithCourses();
    }

    // READ single student
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
    }
}
