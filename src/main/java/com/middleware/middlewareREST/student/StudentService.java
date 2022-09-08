package com.middleware.middlewareREST.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail= studentRepository.findStudentByEmail(student.getEmail());
        System.out.println(student);
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Email has already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id);
        boolean exists=studentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("student "+id+"does not exists");
        }else{
            studentRepository.deleteById(id);
        }
    }
    @Transactional
    public void updateStudent(Long id,String name) {
        Student student=studentRepository.findById(id).orElseThrow(()->new IllegalStateException("Student with id doesn't exist"));
        if(name!=null &&
                name.length()>0 &&
                !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
    }
}
