package com.test.task.api.demo.sevice.impl;

import com.test.task.api.demo.entity.DoctorEntity;
import com.test.task.api.demo.exception.NotFoundException;
import com.test.task.api.demo.repository.DoctorRepository;
import com.test.task.api.demo.sevice.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public List<DoctorEntity> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public DoctorEntity getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Доктор с идентификатором %s не найден", id)));
    }

    public Long saveDoctor(DoctorEntity doctor) {
        return doctorRepository.save(doctor).getId();
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

}
