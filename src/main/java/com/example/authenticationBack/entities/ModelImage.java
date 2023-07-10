package com.example.authenticationBack.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "image")
public class ModelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private byte[] bytes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private ModelUser user;


}
