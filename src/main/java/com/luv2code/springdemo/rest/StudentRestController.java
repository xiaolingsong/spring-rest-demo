package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    // define @Postconstruct to load the student data only once!
    @PostConstruct
    public void loadData(){
        students = new ArrayList<>();

        students.add(new Student("Mary","DeNova"));
        students.add(new Student("Trevor", "Noah"));
        students.add(new Student("Jake", "Tapper"));
    }

    // define endpoint for "/students" -- return list of students

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    // define endpoint for "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        // check the studentID against list size
        if(studentId<0 || studentId>=students.size())
            throw new StudentNotFoundException("Student Id Not found - " + studentId);
        // just index into the list ... keep it simple for now
        return students.get(studentId);
    }


}
