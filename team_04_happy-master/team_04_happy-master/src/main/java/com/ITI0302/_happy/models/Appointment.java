package com.ITI0302._happy.models;

import com.ITI0302._happy.remake.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document(collection = "appointment")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Appointment {

    @Id
    private String id;
    @DBRef
    private Animal animal;
    @DBRef
    private User user;

//  ToDo uurida, mis asi see on -> @DateTimeFormat
    private LocalDateTime appointmentDate;

}
