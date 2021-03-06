package com.example.examMicroservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examMicroservice.bean.QuestionBean;

@Repository
public interface ServiceDAO extends JpaRepository<QuestionBean, Integer> {

}
