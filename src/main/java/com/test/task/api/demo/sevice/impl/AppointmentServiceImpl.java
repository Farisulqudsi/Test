package com.test.task.api.demo.sevice.impl;

import com.test.task.api.demo.entity.AppointmentEntity;
import com.test.task.api.demo.entity.PatientEntity;
import com.test.task.api.demo.exception.NotFoundException;
import com.test.task.api.demo.exception.SlotOccupiedException;
import com.test.task.api.demo.repository.AppointmentRepository;
import com.test.task.api.demo.sevice.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<AppointmentEntity> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public AppointmentEntity getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Талон с идентификатором %s не найден", id)));
    }

    public Long saveAppointment(AppointmentEntity appointment) {
        return appointmentRepository.save(appointment).getId();
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public void saveAppointments(List<AppointmentEntity> appointments) {
        appointmentRepository.saveAll(appointments);
    }

    @Override
    public List<AppointmentEntity> getFreeSlots(Long doctorId, LocalDateTime dateTime) {
        List<AppointmentEntity> freeSlots = appointmentRepository.findFreeSlotsByDoctorAndDate(doctorId, dateTime);
        return freeSlots;
    }

    @Override
    public void occupySlot(Long appointmentId, PatientEntity patient) {
        AppointmentEntity appointment = getAppointmentById(appointmentId);

        if (appointment.getPatient() == null) {
            appointment.setPatient(patient);
            appointmentRepository.save(appointment);
        } else {
            throw new SlotOccupiedException("Slot is already occupied.");
        }
    }

    @Override
    public List<AppointmentEntity> getOccupiedSlots(Long patientId) {
        return appointmentRepository.findOccupiedSlotsByPatientId(patientId);
    }
}

