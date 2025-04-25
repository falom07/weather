package org.example.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
@AllArgsConstructor

@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Session> sessions = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Location> locations = new ArrayList<>();

}
