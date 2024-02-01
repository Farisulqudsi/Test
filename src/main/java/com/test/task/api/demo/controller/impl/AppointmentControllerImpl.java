package com.test.task.api.demo.controller.impl;

import com.test.task.api.demo.controller.AppointmentController;
import com.test.task.api.demo.entity.AppointmentEntity;
import com.test.task.api.demo.entity.PatientEntity;
import com.test.task.api.demo.sevice.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentControllerImpl implements AppointmentController {

    private final AppointmentService appointmentService;

    @Override
    public ResponseEntity<List<AppointmentEntity>> getFreeSlots(Long doctorId, LocalDateTime dateTime) {
        return ResponseEntity.ok(appointmentService.getFreeSlots(doctorId, dateTime));
    }

    @Override
    public ResponseEntity<String> occupySlot(Long appointmentId, PatientEntity patient) {
        appointmentService.occupySlot(appointmentId, patient);
        return ResponseEntity.ok("Slot occupied successfully");
    }

    @Override
    public ResponseEntity<List<AppointmentEntity>> getOccupiedSlots(Long patientId) {
        return ResponseEntity.ok(appointmentService.getOccupiedSlots(patientId));
    }
}
