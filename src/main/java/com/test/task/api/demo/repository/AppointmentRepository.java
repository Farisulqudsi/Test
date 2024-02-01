package com.test.task.api.demo.repository;

import com.test.task.api.demo.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query("SELECT a FROM AppointmentEntity a " +
            "WHERE a.doctor.id = :doctorId " +
            "AND a.startTime = :dateTime " +
            "AND a.patient IS NULL")
    List<AppointmentEntity> findFreeSlotsByDoctorAndDate(@Param("doctorId") Long doctorId, @Param("date") LocalDateTime dateTime);

    @Query("SELECT a FROM AppointmentEntity a " +
            "WHERE a.patient.id = :patientId")
    List<AppointmentEntity> findOccupiedSlotsByPatientId(@Param("patientId") Long patientId);
}
