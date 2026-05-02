package com.sga.assignment.repository;

import com.sga.assignment.entity.Course;
import com.sga.assignment.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindAllStudentsWithCourses() {
        // given
        Course course = new Course("Test Course", 3);
        entityManager.persist(course);

        Student student = new Student("Test Student", "test@example.com", course);
        entityManager.persist(student);
        entityManager.flush();

        // when
        List<Student> students = studentRepository.findAllStudentsWithCourses();

        // then
        assertThat(students).hasSize(11); // 10 from DataLoader + 1 we just added
        assertThat(students).extracting(Student::getEmail).contains("test@example.com");
        
        // Find our student
        Student foundStudent = students.stream()
                .filter(s -> s.getEmail().equals("test@example.com"))
                .findFirst().orElseThrow();
                
        assertThat(foundStudent.getCourse()).isNotNull();
        assertThat(foundStudent.getCourse().getTitle()).isEqualTo("Test Course");
    }
}
