package com.ITI0302._happy.service;

import com.ITI0302._happy.exception.NotFoundException;
import com.ITI0302._happy.models.Appointment;
import com.ITI0302._happy.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }
    
    public Appointment findById(String id) {
        return getById(id);
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(String id) {
        appointmentRepository.delete(getById(id));
    }

    private Appointment getById(String id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("No such appointment found. :("));
    }

}
