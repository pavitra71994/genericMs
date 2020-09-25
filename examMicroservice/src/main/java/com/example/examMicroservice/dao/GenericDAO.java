package com.example.examMicroservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.examMicroservice.bean.UserBean;

@Repository
public interface GenericDAO extends JpaRepository<UserBean, Integer> {

}
