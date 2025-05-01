package com.hospitalManagement.roles;

import com.hospitalManagement.db.Database;
import com.hospitalManagement.dto.VitalsDTO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Nurse{

    public List<String> viewAppointments(){
        MongoCollection<Document> appointments = Database.getAppointmentsCollection();
        List<String> newArray = new ArrayList<>();
        appointments.find().forEach(doc -> newArray.add(doc.toJson()));
        return newArray;
    }

    public String recordVitals(String patientName,
                             double temperature,
                             int bloodPressure,
                             int height,
                             int bodyMassIndex,
                             int pulseRate) {
        MongoCollection<Document> users = Database.getUserCollection();
        Document patient = users.find(new Document("name", patientName).append("role", "Patient")).first();
        if (patient != null){
            VitalsDTO vitals = new VitalsDTO(patient.getObjectId("_id").toHexString(), bodyMassIndex, height, bloodPressure,pulseRate, temperature);
            Document doc = new Document("patientId", vitals.patientId)
                    .append("bodyMassIndex", vitals.bodyMassIndex)
                    .append("height", vitals.height)
                    .append("bloodPressure", vitals.bloodPressure)
                    .append("pulseRate", vitals.pulseRate)
                    .append("temperature", vitals.temperature);
            System.out.println("Vitals recorded successfully");
            InsertOneResult patientVitals = Database.getUserCollection().insertOne(doc);
            return patientVitals.getInsertedId().toString();

        }else{
            System.out.println("Patient not found");
            return null;
        }
    }
}