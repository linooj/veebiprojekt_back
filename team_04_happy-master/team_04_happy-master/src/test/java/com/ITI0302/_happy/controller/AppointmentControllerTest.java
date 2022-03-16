package com.ITI0302._happy.controller;

import com.ITI0302._happy.models.Appointment;
import com.ITI0302._happy.remake.models.User;
import com.ITI0302._happy.service.AppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class AppointmentControllerTest {
    @Mock
    AppointmentService appointmentService;
    @InjectMocks
    AppointmentController appointmentController;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAllAppointments() {
        List<Appointment> list = List.of(new Appointment());
        when(appointmentService.findAll()).thenReturn(list);

        ResponseEntity<List<Appointment>> result = appointmentController.findAllAppointments();
        ResponseEntity<List<Appointment>> expected = new ResponseEntity<>(list, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testCreateAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentService.addAppointment(any())).thenReturn(appointment);

        ResponseEntity<Appointment> result = appointmentController.createAppointment(appointment);
        ResponseEntity<Appointment> expected = new ResponseEntity<>(HttpStatus.CREATED);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testUpdateAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentService.updateAppointment(any())).thenReturn(appointment);

        ResponseEntity<Appointment> result = appointmentController.updateAppointment(appointment);
        ResponseEntity<Appointment> expected = new ResponseEntity<>(appointment, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testDeleteAppointment() {
        ResponseEntity<Appointment> result = appointmentController.deleteAppointment("id");
        ResponseEntity<Appointment> expected = new ResponseEntity<>(HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }

    //ToDO  filteredByUser with userId
    @Test
    void testFindAppointment() {
        Appointment appointment = new Appointment();
        when(appointmentService.findById(anyString())).thenReturn(appointment);

        ResponseEntity<Appointment> result = appointmentController.findAppointment("id");
        ResponseEntity<Appointment> expected = new ResponseEntity<>(appointment, HttpStatus.OK);

        Assertions.assertEquals(expected, result);

    }

    @Test
    void testAppointmentsFilteredByUser() {
        Appointment appointment = new Appointment();
        List<Appointment> list = new ArrayList<>();
        User user = new User("username", "email", "password");
        appointment.setUser(user);

        ResponseEntity<List<Appointment>> result = appointmentController.appointmentsFilteredByUser("id");
        ResponseEntity<List<Appointment>> expected = new ResponseEntity<>(list, HttpStatus.OK);

        Assertions.assertEquals(expected, result);
    }
}
