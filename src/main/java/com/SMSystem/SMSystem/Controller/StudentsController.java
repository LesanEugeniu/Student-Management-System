package com.SMSystem.SMSystem.Controller;

import com.SMSystem.SMSystem.Entity.Student;
import com.SMSystem.SMSystem.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/students")
public class StudentsController {
    private final StudentService studentService;

    StudentsController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public String studentsList(Model model){
        model.addAttribute("students", studentService.studentsList());
        return "index";
    }

    @GetMapping(path = "/new")
    public String addStudentView(Model model){
        model.addAttribute("student", new Student());
        return "add";
    }

    @PostMapping(path = "/new")
    public String addStudent(@Valid @ModelAttribute("student") Student student,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "add";
        }
        studentService.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping(path = "/edit/{id}")
    public String editStudentView(Model model,
                                  @PathVariable("id") Long id){
        model.addAttribute("student", studentService.studentById(id));
        return "edit";
    }

    @PutMapping(path = "/edit/{id}")
    public String editStudent(@PathVariable("id") Long id,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String email){
        studentService.updateStudent(id, firstName, lastName, email);
        return "redirect:/students";
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id){
        System.out.println("Number: " + id);
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}
