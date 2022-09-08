package com.middleware.middlewareREST.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
      return args -> {
          Student Roshan=new Student(
                  "Rosha",
                  "Rosha@gmail.com",
                  LocalDate.of(2000, Month.JANUARY,5)
          );
          repository.save(
                  Roshan
          );
      };
    };
}
