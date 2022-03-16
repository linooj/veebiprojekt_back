package com.ITI0302._happy.service;

import com.ITI0302._happy.models.Appointment;
import com.ITI0302._happy.repositories.AppointmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AppointmentServiceTest {
    @Mock
    AppointmentRepository appointmentRepository;

    @InjectMocks
    AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        List<Appointment> list = List.of(new Appointment());
        when(appointmentService.findAll()).thenReturn(list);

        List<Appointment> result = appointmentService.findAll();
        Assertions.assertEquals(list, result);
    }


    @Test
    void testFindById() {
        Appointment appointment= new Appointment();
        when(appointmentRepository.findById(anyString())).thenReturn(Optional.of(appointment));
        Appointment result = appointmentService.findById("id");
        Assertions.assertEquals(appointment, result);
    }

    @Test
    void testAddAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment result = appointmentService.addAppointment(appointment);
        Assertions.assertEquals(appointment, result);
    }

    @Test
    void testUpdateAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        Appointment result = appointmentService.updateAppointment(appointment);
        Assertions.assertEquals(appointment, result);

    }
}
