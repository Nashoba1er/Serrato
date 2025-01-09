package com.emse.spring.serrato.model;


import jakarta.persistence.*;

@Entity // .
@Table(name = "SP_USER") // .
public class UserEntity {

    @Id // .
    @GeneratedValue
    private Long id;

    @Column(nullable=false,name = "username", length=255)  // .
    private String username;

    @Column(name = "password") //
    private String password;


    public UserEntity() { // .
    }

    public UserEntity(String name, String password) { // .
        this.username = name;
        this.password = password;
    }

    public Long getId() { // .
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
