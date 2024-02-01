package com.test.task.api.demo.controller;

import com.test.task.api.demo.entity.AppointmentEntity;
import com.test.task.api.demo.entity.PatientEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/appointments")
public interface AppointmentController {

    // 2.3.1. Получение свободных слотов времени к указанному врачу на указанную дату
    @GetMapping("/freeSlots")
    ResponseEntity<List<AppointmentEntity>> getFreeSlots(@RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dateTime);

    // 2.3.2. Занятие слота времени по его id
    @PostMapping("/occupySlot/{appointmentId}")
    ResponseEntity<String> occupySlot(@PathVariable Long appointmentId, @RequestBody PatientEntity patient);

    // 2.3.3. Получение всех слотов времени, занятых одним пациентом по id/uuid
    @GetMapping("/occupiedSlots/{patientId}")
    ResponseEntity<List<AppointmentEntity>> getOccupiedSlots(@PathVariable Long patientId);
}
