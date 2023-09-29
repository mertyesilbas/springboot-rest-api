package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.bean.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @GetMapping("/student")
    public Student getStudent() {
        return new Student(1, "Mert", "Yeşilbaş");
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1,"Mehmet","Taşçı"));
        students.add(new Student(2,"Mert","Yeşilbaş"));
        students.add(new Student(3,"Ali","Karabaş"));
        students.add(new Student(4,"Su","Ateş"));
        return students;
    }

    // Spring boot REST API with Path Variable
    // {id} - URI template var
    // htttp://localhost:8080/students/1
    @GetMapping(("/students/{id}/{first-name}/{last-name}"))
    public Student studentPathVariable(
            @PathVariable("id") int studentId,
            @PathVariable("first-name") String firstName,
            @PathVariable("last-name") String lastName){
        return new Student(studentId,firstName,lastName);
    }

    // Spring Boot REST API Request Param
    // http://localhost:8080/students?id=1&firstName=Mert&lastName=Yeşilbaş -> Request Parameter
    @GetMapping("/students/query")
    public Student studentRequestvariable(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastName
    )
    {
        return new Student(id,firstName,lastName);
    }

}