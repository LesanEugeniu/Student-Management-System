package com.SMSystem.SMSystem.Config;

import com.SMSystem.SMSystem.Entity.Student;
import com.SMSystem.SMSystem.Service.StudentService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {
    private final StudentService service;
    DemoApplication(StudentService service){
        this.service = service;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {

        Student student1 = new Student(1L,"Annet", "Marria", "ann.mar@gmail.com");
        Student student2 = new Student(2L,"Ruslan", "Sula", "sula.ruslan@mail.ru");
        Student student3 = new Student(3L,"Ludmila", "Cioric", "l.cioric@gmail.com");
        service.addStudent(student1);
        service.addStudent(student2);
        service.addStudent(student3);
    }
}
