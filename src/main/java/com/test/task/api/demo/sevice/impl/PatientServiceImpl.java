package com.test.task.api.demo.sevice.impl;

import com.test.task.api.demo.entity.PatientEntity;
import com.test.task.api.demo.exception.NotFoundException;
import com.test.task.api.demo.repository.PatientRepository;
import com.test.task.api.demo.sevice.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    public PatientEntity getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Пациент с идентификатором %s не найден", id)));
    }

    public Long savePatient(PatientEntity patient) {
        return patientRepository.save(patient).getId();
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

}
