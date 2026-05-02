package com.sga.assignment;

import com.sga.assignment.entity.Course;
import com.sga.assignment.entity.Student;
import com.sga.assignment.repository.CourseRepository;
import com.sga.assignment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public DataLoader(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Load 10 Courses
        Course c1 = courseRepository.save(new Course("Computer Science 101", 3));
        Course c2 = courseRepository.save(new Course("Data Structures", 4));
        Course c3 = courseRepository.save(new Course("Algorithms", 4));
        Course c4 = courseRepository.save(new Course("Database Management", 3));
        Course c5 = courseRepository.save(new Course("Operating Systems", 4));
        Course c6 = courseRepository.save(new Course("Computer Networks", 3));
        Course c7 = courseRepository.save(new Course("Software Engineering", 3));
        Course c8 = courseRepository.save(new Course("Artificial Intelligence", 4));
        Course c9 = courseRepository.save(new Course("Machine Learning", 4));
        Course c10 = courseRepository.save(new Course("Web Development", 3));

        // Load 10 Students
        studentRepository.save(new Student("Alice Smith", "alice@example.com", c1));
        studentRepository.save(new Student("Bob Johnson", "bob@example.com", c1));
        studentRepository.save(new Student("Charlie Brown", "charlie@example.com", c2));
        studentRepository.save(new Student("Diana Prince", "diana@example.com", c3));
        studentRepository.save(new Student("Eve Davis", "eve@example.com", c4));
        studentRepository.save(new Student("Frank Miller", "frank@example.com", c5));
        studentRepository.save(new Student("Grace Lee", "grace@example.com", c6));
        studentRepository.save(new Student("Hank Green", "hank@example.com", c7));
        studentRepository.save(new Student("Ivy Chen", "ivy@example.com", c8));
        studentRepository.save(new Student("Jack White", "jack@example.com", c10));
    }
}
