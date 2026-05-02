package com.sga.assignment.controller;

import com.sga.assignment.entity.Student;
import com.sga.assignment.service.CourseService;
import com.sga.assignment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "/students"})
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // READ operation - display list
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "list-students";
    }

    // SHOW Add Form
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseService.getAllCourses());
        return "add-student";
    }

    // CREATE operation - handle form submission
    @PostMapping
    public String addStudent(@ModelAttribute("student") Student student, Model model) {
        try {
            studentService.saveStudent(student);
            return "redirect:/students";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Integrity violation: A student with this email might already exist or data is invalid.");
            model.addAttribute("courses", courseService.getAllCourses());
            return "add-student";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred: " + e.getMessage());
            model.addAttribute("courses", courseService.getAllCourses());
            return "add-student";
        }
    }

    // SHOW Edit Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        try {
            Student student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            model.addAttribute("courses", courseService.getAllCourses());
            return "edit-student";
        } catch (IllegalArgumentException e) {
            return "redirect:/students";
        }
    }

    // UPDATE operation - handle form submission
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student studentDetails, Model model) {
        try {
            Student existingStudent = studentService.getStudentById(id);
            existingStudent.setName(studentDetails.getName());
            existingStudent.setEmail(studentDetails.getEmail());
            existingStudent.setCourse(studentDetails.getCourse());

            studentService.saveStudent(existingStudent);
            return "redirect:/students";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Integrity violation: A student with this email might already exist.");
            model.addAttribute("student", studentDetails);
            model.addAttribute("courses", courseService.getAllCourses());
            return "edit-student";
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred: " + e.getMessage());
            model.addAttribute("student", studentDetails);
            model.addAttribute("courses", courseService.getAllCourses());
            return "edit-student";
        }
    }
}
