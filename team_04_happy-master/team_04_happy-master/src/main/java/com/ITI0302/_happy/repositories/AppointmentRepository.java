package com.ITI0302._happy.repositories;

import com.ITI0302._happy.models.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
//  ToDo come back here if you need a search algorithm
//    Appointment findAppointmentById(String id);
}
