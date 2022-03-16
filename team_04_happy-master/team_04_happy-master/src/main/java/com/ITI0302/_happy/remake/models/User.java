package com.ITI0302._happy.remake.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
@Getter @Setter @NoArgsConstructor
public class User {

//  Elemendid vajalikud, et sisse logida
    @Id
    private String id;
    private String username;
    private String password;
    private String email;

    @DBRef
    private Set<Role> roles = new HashSet<>();
//  End

    private String firstName;
    private String lastName;
    private String fullName;
    private String phone;
    private String street;
    private String house;
    private String apartment;
    private String city;
    private Integer postalIndex;
    private String county;
    private String country;


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}

