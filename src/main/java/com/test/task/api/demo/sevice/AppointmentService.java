package com.test.task.api.demo.sevice;

import com.test.task.api.demo.entity.AppointmentEntity;
import com.test.task.api.demo.entity.PatientEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    List<AppointmentEntity> getAllAppointments();

    AppointmentEntity getAppointmentById(Long id);

    Long saveAppointment(AppointmentEntity appointment);

    void deleteAppointment(Long id);

    void saveAppointments(List<AppointmentEntity> appointments);

    List<AppointmentEntity> getFreeSlots(Long doctorId, LocalDateTime dateTime);

    void occupySlot(Long appointmentId, PatientEntity patient);

    List<AppointmentEntity> getOccupiedSlots(Long patientId);
}
