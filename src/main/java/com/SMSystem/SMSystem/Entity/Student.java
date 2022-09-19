package com.SMSystem.SMSystem.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    @Size(min = 1, max = 30, message = "First Name between 1 and 30")
    @NotEmpty(message = "First Name not empty")
    private String firstName;
    @Size(min = 1, max = 30, message = "Last Name between 1 and 30")
    @NotEmpty(message = "Last Name not empty")
    private String lastName;
    @Email(message = "example@gmail.com")
    @NotEmpty(message = "Email not empty")
    private String email;
}
