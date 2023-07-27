package com.example.authenticationBack.repositories;

import com.example.authenticationBack.entities.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ModelUser,Long> {

    Optional<ModelUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
