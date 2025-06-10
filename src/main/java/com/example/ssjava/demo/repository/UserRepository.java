package com.example.ssjava.demo.repository;

import com.example.ssjava.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Long> {
    @Query(value = "select * from users where email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @Query(value = "select count(*) from users", nativeQuery = true)
    Long countTotalUsers();

}
