package com.test.task.api.demo.web;

import com.test.task.api.demo.entity.AppointmentEntity;
import com.test.task.api.demo.entity.DoctorEntity;
import com.test.task.api.demo.entity.PatientEntity;
import com.test.task.api.demo.model.request.ScheduleRequest;
import com.test.task.api.demo.model.response.ScheduleResponse;
import com.test.task.api.demo.sevice.AppointmentService;
import com.test.task.api.demo.sevice.DoctorService;
import com.test.task.api.demo.sevice.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class ScheduleEndpoint {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;


    @PayloadRoot(namespace = "http://example.org/appointment", localPart = "ScheduleRequest")
    @ResponsePayload
    public ScheduleResponse createSchedule(@RequestPayload ScheduleRequest request) {
        LocalDateTime startTime = request.getStartTime();
        Duration appointmentDuration = request.getAppointmentDuration();
        int numberOfAppointments = request.getNumberOfAppointments();

        DoctorEntity doctor = doctorService.getDoctorById(request.getDoctorId());

        PatientEntity patient = patientService.getPatientById(request.getPatientId());

        // Создание расписания на основе параметров
        List<AppointmentEntity> appointments = generateAppointments(doctor, patient, startTime, appointmentDuration, numberOfAppointments);

        appointmentService.saveAppointments(appointments);

        ScheduleResponse response = new ScheduleResponse();
        response.setStatus("SUCCESS");
        response.setMessage("Schedule created successfully");

        return response;
    }

    private List<AppointmentEntity> generateAppointments(DoctorEntity doctor, PatientEntity patient, LocalDateTime startTime, Duration appointmentDuration, int numberOfAppointments) {
        List<AppointmentEntity> appointments = new ArrayList<>();

        for (int i = 0; i < numberOfAppointments; i++) {
            // Создаем талон с учетом переданных параметров
            AppointmentEntity appointment = new AppointmentEntity();
            appointment.setDoctor(doctor);
            appointment.setPatient(patient);
            appointment.setStartTime(startTime.plus(appointmentDuration.multipliedBy(i))); // Увеличиваем время начала для каждого следующего талона

            appointments.add(appointment);
        }

        return appointments;
    }
}
