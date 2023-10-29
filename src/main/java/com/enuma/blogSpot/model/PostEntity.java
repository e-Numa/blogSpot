package com.enuma.blogSpot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "post", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
    public class PostEntity extends BaseModel {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        @Column(name = "title", nullable = false)
        String title;

        @Column(name = "description", nullable = false)
        String description;

        @Column(name = "body", columnDefinition = "TEXT", nullable = false)
        String body;

        @Column(updatable = false)
        LocalDateTime createdAt;

        LocalDateTime updatedAt;

        @NotNull
        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<CommentEntity> comments = new HashSet<>();
}


