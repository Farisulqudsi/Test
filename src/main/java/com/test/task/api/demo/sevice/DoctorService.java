package com.test.task.api.demo.sevice;

import com.test.task.api.demo.entity.DoctorEntity;

import java.util.List;

public interface DoctorService {

    List<DoctorEntity> getAllDoctors();

    DoctorEntity getDoctorById(Long id);

    Long saveDoctor(DoctorEntity entity);

    void deleteDoctor(Long id);
}
