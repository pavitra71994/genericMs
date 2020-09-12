package com.example.examMicroservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.examMicroservice.bean.QuestionBean;

@Component
public interface ServiceDAO extends JpaRepository<QuestionBean, Integer> {

}
