package com.example.school.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.school.service.StudentH2Service;
import com.example.school.model.Student;

@RestController
public class StudentController{
    @Autowired
    StudentH2Service ss;
    @GetMapping("/students")
    public ArrayList<Student> getStudents(){
        return ss.getStudents();
    }
    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable ("studentId") int studentId){
        return ss.getStudentById(studentId);
    }
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return ss.addStudent(student);
    }
    @PostMapping("/students/bulk")
    public String addBulkStudents(@RequestBody ArrayList<Student> bulkStudents){
        return ss.addBulkStudents(bulkStudents);
        
    }
    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable ("studentId") int studentId , @RequestBody Student student){
        return ss.updateStudent(studentId , student);
    }
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable ("studentId") int studentId){
        ss.deleteStudent(studentId);
    }

}