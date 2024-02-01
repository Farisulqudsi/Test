package com.test.task.api.demo.sevice;

import com.test.task.api.demo.entity.PatientEntity;

import java.util.List;

public interface PatientService {

    List<PatientEntity> getAllPatients();

    PatientEntity getPatientById(Long id);

    Long savePatient(PatientEntity patient);

    void deletePatient(Long id);
}
