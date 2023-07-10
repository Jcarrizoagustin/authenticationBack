package com.example.authenticationBack.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "model_user")
public class ModelUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String bio;

    private String phone;

    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModelImage> profileImages = new ArrayList<>();

    public void addImage(ModelImage image){
        this.profileImages.add(image);
        image.setUser(this);
    }

    public void deleteImage(ModelImage image){
        this.profileImages.remove(image);
        image.setUser(null);
    }
}
