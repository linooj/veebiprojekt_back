package com.ITI0302._happy.controller;

import com.ITI0302._happy.models.Animal;
import com.ITI0302._happy.models.Appointment;
import com.ITI0302._happy.remake.models.User;
import com.ITI0302._happy.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("/api/appointments")
@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class  AppointmentController {

//   ToDo Fine tuning oleks asendada USER -> user

    private final AppointmentService appointmentService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Appointment>> findAllAppointments() {
        List<Appointment> appointments = appointmentService.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

//  ToDo välja mõelda, mis selle url võiks bnmgte
    @GetMapping("/userId")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Appointment>> appointmentsFilteredByUser(@RequestParam(name = "userId") String userId) {
        List<Appointment> usersAppointments = new ArrayList<>();
        for (Appointment appointment: appointmentService.findAll()) {
            User foundUser = appointment.getUser();
            if (foundUser != null && foundUser.getId().equals(userId)) {
                usersAppointments.add(appointment);
            }
        }
        return new ResponseEntity<>(usersAppointments, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment){
        this.appointmentService.addAppointment(appointment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Appointment> findAppointment(@PathVariable String id) {
        Appointment searchedAppointment = appointmentService.findById(id);
        return new ResponseEntity<>(searchedAppointment, HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment Appointment) {
        Appointment changed = this.appointmentService.updateAppointment(Appointment);
        return new ResponseEntity<>(changed, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
