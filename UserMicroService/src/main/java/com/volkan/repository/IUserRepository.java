package com.volkan.repository;

import com.volkan.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    @Query(value = "select COUNT(a)>0 from User a where upper(a.email)=upper(?1)")
    boolean isEmail(String email);
    Optional<User> findOptionalByEmailIgnoreCaseAndPassword(String email, String password);
}
