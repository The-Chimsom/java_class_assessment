package com.hospitalManagement.dto;

public class VitalsDTO {
    public String patientId;
    public int bodyMassIndex;
    public int height;
    public int bloodPressure;
    public int pulseRate;
    public double temperature;


    public VitalsDTO(String patientId, int bodyMassIndex, int height, int bloodPressure, int pulseRate, double temperature){
        this.patientId = patientId;
        this.bodyMassIndex = bodyMassIndex;
        this.height = height;
        this.bloodPressure = bloodPressure;
        this.pulseRate = pulseRate;
        this.temperature = temperature;
    }
}