package com.ITI0302._happy.models;

import com.ITI0302._happy.remake.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "animal")

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Animal {

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    @Id
    private String id;
    private String name;
    // @ToDo
    // Date on deprecated to replace w/ Calendar or GeorgianCalendar
    private Date dateOfBirth;
    private String species;
    private String breed;
    private String chipNr;
    private Gender gender;
    private Integer weight;

//  Is this an issue?
    @DBRef
    private User user;

}
