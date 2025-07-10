package com.student_api.studentapi.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "Date of birth is required")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Status is required") // Accepts: "ACTIVE", "INACTIVE"
    @Column(nullable = false)
    private String status;

    @NotNull(message = "Enrollment date is required")
    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    @DecimalMin(value = "0.0", inclusive = true, message = "GPA must be at least 0.0")
    @DecimalMax(value = "10.0", inclusive = true, message = "GPA must not exceed 10.0")
    @Column(nullable = false)
    private Double gpa;
}
