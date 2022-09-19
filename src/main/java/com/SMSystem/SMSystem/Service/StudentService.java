package com.SMSystem.SMSystem.Service;

import com.SMSystem.SMSystem.Entity.Student;
import com.SMSystem.SMSystem.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepo;

    StudentService (StudentRepository studentRepo){
        this.studentRepo = studentRepo;
    }

    public List<Student> studentsList(){
        return studentRepo.findAll();
    }

    public void addStudent(Student student){
        Optional<Student> studentOptional = studentRepo.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        studentRepo.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId,
                               String firstName,
                               String lastName,
                               String email){
        Student student = studentRepo.findById(studentId).orElseThrow(
                ()-> new IllegalStateException("student with id " + studentId + " does not exists"));
        if (firstName != null &&
                firstName.length() > 0 &&
                !Objects.equals(student.getFirstName(), firstName)){
            student.setFirstName(firstName);
        }

        if (lastName != null &&
                lastName.length() > 0 &&
                !Objects.equals(student.getLastName(), lastName)){
            student.setLastName(lastName);
        }

        if(email != null &&
            email.length() > 0 &&
            !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepo.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email already taken");
            }
            student.setEmail(email);
        }
    }

    public void deleteStudent(Long studentId){
        boolean exist = studentRepo.existsById(studentId);
        if (!exist){
            throw new IllegalStateException("student with id " + studentId + "does not exist");
        }
        studentRepo.deleteById(studentId);
    }

    public Student studentById(Long id){
        return studentsList().stream().filter(student -> student.getId().equals(id)).findAny().orElseThrow(IllegalStateException::new);
    }

}
