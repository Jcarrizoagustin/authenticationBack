package com.example.authenticationBack.repositories;

import com.example.authenticationBack.entities.ModelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ModelImage, Long> {

}
