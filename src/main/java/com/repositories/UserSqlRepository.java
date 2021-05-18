package com.repositories;


import java.util.List;

import com.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSqlRepository extends JpaRepository<User, Long> {

    List<User> findByFirstName(String firstName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByFirstNameOrLastName(String firstName, String lastName);

    List<User> findByBeachLocation(String location);

}
