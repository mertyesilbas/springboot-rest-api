package com.example.springbootrestapi.controller;

import com.example.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController
    {
    @GetMapping("student")
    public ResponseEntity<Student> getStudent()
        {
        return new ResponseEntity<>(
                new Student(
                        1,
                        "Mert",
                        "Yeşilbaş"
                ),
                HttpStatus.OK
        );
        }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents()
        {
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                1,
                "Mehmet",
                "Taşçı"
        ));
        students.add(new Student(
                2,
                "Mert",
                "Yeşilbaş"
        ));
        students.add(new Student(
                3,
                "Ali",
                "Karabaş"
        ));
        students.add(new Student(
                4,
                "Su",
                "Ateş"
        ));
        return new ResponseEntity<>(
                students,
                HttpStatus.OK
        );
        }

    // Spring boot REST API with Path Variable
    // {id} - URI template var
    // htttp://localhost:8080/students/1
    @GetMapping(("{id}/{first-name}/{last-name}"))
    public ResponseEntity<Student> studentPathVariable(
            @PathVariable("id") int studentId,
            @PathVariable("first-name") String firstName,
            @PathVariable("last-name") String lastName
                                                      )
        {
        return ResponseEntity.ok(new Student(
                studentId,
                firstName,
                lastName
        ));
        }

    // Spring Boot REST API Request Param
    // http://localhost:8080/students/query?id=1&firstName=Mert&lastName=Yeşilbaş -> Request Parameter
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestvariable(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam String lastName
                                                         )
        {
        return ResponseEntity.ok().header(
                "custom-header",
                "Mert"
                                         ).body(new Student(
                id,
                firstName,
                lastName
        ));
        }

    // Spring Boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student)
        {
        return new ResponseEntity<>(
                student,
                HttpStatus.CREATED
        );
        }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resource
    @PutMapping("{id}/update")
//    @ResponseStatus(HTTPStatus)
    public ResponseEntity<Student> updateStudent(
            @RequestBody Student student,
            @PathVariable("id") int studentId
                                                )
        {
        student.setId(studentId);
        return ResponseEntity.ok(student);
        }

    // Spring Boot REST API that handles HTTP DELETE Request - deleting existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") int studentId)
        {
        System.out.println("Deleted " + studentId);
        return ResponseEntity.ok(new Student(studentId,"Mert","Y"));
        }

    }
