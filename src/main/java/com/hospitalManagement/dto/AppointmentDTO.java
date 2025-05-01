package com.hospitalManagement.dto;

import java.time.LocalDate;


public class AppointmentDTO{
    public String patientName;
    public String nurseName;
    public LocalDate appointmentDate;

    public AppointmentDTO(String patientName, String nurseName, LocalDate appointmentDate){
        this.patientName = patientName;
        this.nurseName = nurseName;
        this.appointmentDate = appointmentDate;
    }
}