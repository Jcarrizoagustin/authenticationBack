package com.example.authenticationBack.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "model_user",uniqueConstraints = @UniqueConstraint(
        name = "uk_email",
        columnNames = {
                "email"
        }
))
public class ModelUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String bio;

    private String phone;


    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.REMOVE,CascadeType.PERSIST}, orphanRemoval = true)
    private List<ModelImage> profileImages = new ArrayList<>();

    public void addImage(ModelImage image){
        this.profileImages.add(image);
        image.setUser(this);
    }

    public void deleteImage(ModelImage image){
        this.profileImages.remove(image);
        image.setUser(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
