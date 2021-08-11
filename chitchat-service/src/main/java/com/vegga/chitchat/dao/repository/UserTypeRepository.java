package com.vegga.chitchat.dao.repository;

import com.vegga.chitchat.dao.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {}
