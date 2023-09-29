package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
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
    // http://localhost:8080/students/query?id=1&firstName=Mert&lastName=Yeşilbaş -> Request Parameter
    @GetMapping("/students/query")
    public Student studentRequestvariable(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastName
    )
    {
        return new Student(id,firstName,lastName);
    }

    // Spring Boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        return student;
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("students/{id}/update")
//    @ResponseStatus(HTTPStatus)
    public Student updateStudent(@RequestBody Student student,
                                 @PathVariable("id") int studentId){
        student.setId(studentId);
        return student;
    }

    // Spring Boot REST API that handles HTTP DELETE Request - deleting existing resource
    @DeleteMapping("students/{id}/delete")
    public void deleteStudent(@PathVariable("id") int studentId){
        System.out.println("Deleted "+studentId);
    }

}
