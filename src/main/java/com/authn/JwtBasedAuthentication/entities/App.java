package com.authn.JwtBasedAuthentication.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app")
public class App {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    private String enabled;

    private String callbackUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "app")
    @JsonManagedReference
    private List<User> users;
}
