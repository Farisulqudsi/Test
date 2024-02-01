package com.test.task.api.demo.model.request;

import com.test.task.api.demo.model.adapter.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScheduleRequest", propOrder = {
        "doctorId",
        "patientId",
        "startTime",
        "appointmentDuration",
        "numberOfAppointments"
})
public class ScheduleRequest {

    @XmlElement(required = true)
    private Long doctorId;

    @XmlElement(required = true)
    private Long patientId;

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime startTime;

    @XmlElement(required = true)
    private Duration appointmentDuration;

    @XmlElement(required = true)
    private int numberOfAppointments;
}
