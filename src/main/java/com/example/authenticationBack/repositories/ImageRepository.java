package com.example.authenticationBack.repositories;

import com.example.authenticationBack.entities.ModelImage;
import com.example.authenticationBack.entities.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ModelImage, Long> {
    List<ModelImage> findAllByUser(ModelUser modelUser);

}
