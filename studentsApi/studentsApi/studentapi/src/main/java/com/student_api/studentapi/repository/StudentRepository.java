package com.student_api.studentapi.repository;

import com.student_api.studentapi.Entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//Database connectivity class file which make the connection to the database

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    Page<StudentEntity> findByStatusIgnoreCaseAndGpaBetween(
        String status,
        Double minGpa,
        Double maxGpa,
        Pageable pageable
    );

    Page<StudentEntity> findByStatusIgnoreCaseAndGpaBetweenAndFirstNameContainingIgnoreCaseOrStatusIgnoreCaseAndGpaBetweenAndLastNameContainingIgnoreCase(
        String status1, Double minGpa1, Double maxGpa1, String firstName,
        String status2, Double minGpa2, Double maxGpa2, String lastName,
        Pageable pageable
    );
}
