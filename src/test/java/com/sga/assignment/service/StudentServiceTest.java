package com.sga.assignment.service;

import com.sga.assignment.entity.Course;
import com.sga.assignment.entity.Student;
import com.sga.assignment.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveStudent() {
        Course course = new Course("Math", 3);
        Student student = new Student("John Doe", "john@example.com", course);
        
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student savedStudent = studentService.saveStudent(student);

        assertThat(savedStudent.getName()).isEqualTo("John Doe");
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testGetAllStudents() {
        Course course = new Course("Math", 3);
        Student s1 = new Student("John", "john@example.com", course);
        Student s2 = new Student("Jane", "jane@example.com", course);
        
        when(studentRepository.findAllStudentsWithCourses()).thenReturn(Arrays.asList(s1, s2));

        List<Student> students = studentService.getAllStudents();

        assertThat(students).hasSize(2);
        verify(studentRepository, times(1)).findAllStudentsWithCourses();
    }

    @Test
    public void testGetStudentById() {
        Course course = new Course("Math", 3);
        Student student = new Student("John", "john@example.com", course);
        student.setId(1L);
        
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student found = studentService.getStudentById(1L);

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1L);
        verify(studentRepository, times(1)).findById(1L);
    }
}
