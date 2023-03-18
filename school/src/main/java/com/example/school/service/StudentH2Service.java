package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.school.repository.StudentRepository;
import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
@Service
public class StudentH2Service implements StudentRepository{
    
	@Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Student> getStudents(){
        List<Student> studentsall = db.query("select * from Student" , new StudentRowMapper());
        ArrayList<Student> allStudents = new ArrayList<>(studentsall);
        return allStudents;
    }
    @Override
    // public Student addStudent(Student student){
    //     String sql = "INSERT INTO Student (studentName ,gender , standard) VALUES (? , ? , ?)";
    //     db.update(sql ,student.getStudentName() , student.getGender() , student.getStandard());
    //     Student savedBook = db.queryForObject("select * from student where studentName = ? and gender = ? and standard = ?",new StudentRowMapper(),student.getStudentName(),student.getGender(),student.getStandard());
    //     return savedBook;
    // }
    public Student addStudent(Student student){
        // db.update("insert into team(playerName , jerseyNumber,role) values(player.getPlayerName(),player.getJerseyNumber(),player.getRole())");
        // return db.queryForObject("select * from team where playerName = ? and jerseyNumber = ? and role = ?",new PlayerRowMapper(),player.getPlayerName(),player.getJerseyNumber(),player.getRole());
        String sql = "INSERT INTO STUDENT (studentName, gender , standard) VALUES (?, ? , ?)";  
        db.update(sql, student.getStudentName(), student.getGender(),student.getStandard());
        Student movieadd = db.queryForObject("select * from STUDENT where studentName = ? and gender = ? and standard = ?",new StudentRowMapper(),student.getStudentName(),student.getGender(),student.getStandard());
        return movieadd;
        
    }
    @Override
    public String addBulkStudents(ArrayList<Student> bulkStudents){
        int len =  bulkStudents.size();
        for(int i = 0; i<len;i++){
            Student student = bulkStudents.get(i);
            String sql = "INSERT INTO Student (studentName ,gender , standard) VALUES (? , ? , ?)";
            db.update(sql ,student.getStudentName() , student.getGender() , student.getStandard());
        }
        String str = "Successfully added "+ len + " students";
        return str;
    }
    @Override
    public Student getStudentById(int studentId){
        try{
            String sql = "select * from Student where studentId = ?";
            Student s = db.queryForObject(sql, new StudentRowMapper(),studentId);
            return s;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Student updateStudent(int studentId ,Student student){
        if(student.getStudentName() != null){
            db.update("update Student set studentName = ? where studentId = ?",student.getStudentName() , studentId);
        }
        if(student.getGender() != null){
            db.update("update Student set gender = ? where studentId = ?" , student.getGender() , studentId);
        }
        if(student.getStandard() != 0){
            db.update("update Student set standard = ? where studentId = ?" , student.getStandard() , studentId);
        }
        return getStudentById(studentId);
    }
    @Override 
    public void deleteStudent(int studentId){
        db.update("delete from Student where studentId = ?" , studentId);
    }

}