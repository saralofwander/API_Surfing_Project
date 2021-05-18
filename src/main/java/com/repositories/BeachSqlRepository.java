package com.repositories;


import com.entities.Beach;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeachSqlRepository extends JpaRepository<Beach, Long> {
    
}