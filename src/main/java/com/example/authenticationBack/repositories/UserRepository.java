package com.example.authenticationBack.repositories;

import com.example.authenticationBack.entities.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ModelUser,Long> {
}
