package com.enuma.blogSpot.model;

import com.enuma.blogSpot.enums.Gender;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity extends BaseModel{

     @Column(nullable = false)
     String name;
     String email;
     @Enumerated(EnumType.STRING)
     Gender gender;
     String password;

     @OneToMany (mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
     List<PostEntity> posts;

     public UserEntity(String name, String email, Gender gender, String password) {
     }
}
