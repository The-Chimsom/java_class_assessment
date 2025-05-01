package com.hospitalManagement.api;

import com.hospitalManagement.roles.Clerk;
import com.hospitalManagement.roles.Nurse;


import static spark.Spark.*;

import org.bson.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class HospitalManagementApi {
    private static Clerk clerk = new Clerk();
    private static Nurse nurse = new Nurse();

    public static void start() {
        port(4567);

        post("/register", (req, res) -> {
            Document doc = Document.parse(req.body());
             String success = clerk.registerUser(doc.getString("name"), doc.getString("role"));
            res.type("application/json");
            return success != null ? "User registered" : "User already exists";
        });

        post("/schedule", (req, res) -> {
            Document doc = Document.parse(req.body());


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
            LocalDate formatDate = LocalDate.parse(doc.getString("Date"), formatter);

            String success = clerk.scheduleAppointment(doc.getString("patientName"), doc.getString("nurseName"), formatDate);
            System.out.println("I got here");
            res.type("application/json");
            return  success != null ? "Appointment scheduled" : "Patient not found, cannot schedule";
        });

        get("/appointments", (req, res) -> {
            List<String> appointments = nurse.viewAppointments();
            res.type("application/json");
            return appointments.toString();
        });

        post("/vitals", (req, res) -> {
            Document doc = Document.parse(req.body());
             String success = nurse.recordVitals(doc.getString("patientName"),
                    doc.getDouble("temperature"),
                    doc.getInteger("bloodPressure"),
                    doc.getInteger("height"),
                    doc.getInteger("bodyMassIndex"),
                    doc.getInteger("pulseRate") );
            res.type("application/json");
            return success != null ? "Vitals recorded" : "Patient not found, cannot record vitals";
        });

        get("/health", (req, res) -> {
            res.type("application/json");
            return new Document("message", "Hospital API is running 🚑").toJson();
        });
    }
}
