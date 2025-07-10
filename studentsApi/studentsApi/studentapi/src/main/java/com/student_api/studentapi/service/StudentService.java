package com.student_api.studentapi.service;

import com.student_api.studentapi.Entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//Interface where it actually hide the implementaion and not to show for the other users
public interface StudentService {

    StudentEntity createStudent(StudentEntity student);

    StudentEntity getStudentById(Long id);

    StudentEntity updateStudent(Long id, StudentEntity studentDetails);

    void hardDeleteIfInactive(Long id);

    Page<StudentEntity> getAllStudents(
        Pageable pageable,
        String status,
        Double minGpa,
        Double maxGpa,
        String name
        );
} 
