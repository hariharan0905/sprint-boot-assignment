package com.student_api.studentapi.service.Implementation;

import com.student_api.studentapi.Entity.StudentEntity;
import com.student_api.studentapi.Exception.ResourceNotFoundException;
import com.student_api.studentapi.repository.StudentRepository;
import com.student_api.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

//It is class where the business logic or written what actullay want to do that maps form the controller class
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentEntity createStudent(StudentEntity student) {
        if (student.getStatus() == null || student.getStatus().isBlank()) {
            throw new IllegalArgumentException("Status is required (e.g., 'ACTIVE' or 'INACTIVE')");
        }
        return studentRepository.save(student);
    }

    @Override
    public StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
    }

    @Override
    public StudentEntity updateStudent(Long id, StudentEntity studentDetails) {
        StudentEntity student = getStudentById(id);
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setDateOfBirth(studentDetails.getDateOfBirth());
        student.setStatus(studentDetails.getStatus());
        student.setEnrollmentDate(studentDetails.getEnrollmentDate());
        student.setGpa(studentDetails.getGpa());
        return studentRepository.save(student);
    }

    @Override
    public void hardDeleteIfInactive(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        if (!"INACTIVE".equalsIgnoreCase(student.getStatus())) {
            throw new IllegalStateException("Cannot delete student unless status is INACTIVE");
        }

        studentRepository.delete(student);
    }

    @Override
    public Page<StudentEntity> getAllStudents(Pageable pageable,
                                              String status,
                                              Double minGpa,
                                              Double maxGpa,
                                              String name) {

        if (status == null || status.isBlank()) status = "ACTIVE";
        if (minGpa == null) minGpa = 0.0;
        if (maxGpa == null) maxGpa = 10.0;

        if (name != null && !name.isBlank()) {
            return studentRepository
                    .findByStatusIgnoreCaseAndGpaBetweenAndFirstNameContainingIgnoreCaseOrStatusIgnoreCaseAndGpaBetweenAndLastNameContainingIgnoreCase(
                            status, minGpa, maxGpa, name,
                            status, minGpa, maxGpa, name,
                            pageable);
        } else {
            return studentRepository.findByStatusIgnoreCaseAndGpaBetween(status, minGpa, maxGpa, pageable);
        }
    }
}
