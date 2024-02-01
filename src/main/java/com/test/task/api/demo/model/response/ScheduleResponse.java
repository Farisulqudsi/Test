package com.test.task.api.demo.model.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScheduleResponse", propOrder = {
        "status",
        "message"
})
public class ScheduleResponse {

    @XmlElement(required = true)
    private String status;

    @XmlElement(required = true)
    private String message;
}