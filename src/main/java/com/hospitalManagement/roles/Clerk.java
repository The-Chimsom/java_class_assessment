package com.hospitalManagement.roles;

import com.hospitalManagement.db.Database;
import com.hospitalManagement.dto.UserDTO;
import com.hospitalManagement.factory.UserFactory;
import com.mongodb.client.MongoCollection;
import com.hospitalManagement.dto.AppointmentDTO;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;

import java.time.LocalDate;
import java.util.Date;

public class Clerk{
    public String registerUser(String name, String role){
        MongoCollection<Document> collection = Database.getUserCollection();
        Document user = collection.find(new Document("name", name)).first();

        if (user == null){
            UserDTO userDTO = UserFactory.createUser(name,role);
            Document doc = new Document("name", userDTO.name).append("role", userDTO.role);
            InsertOneResult newUser = collection.insertOne(doc);
            System.out.println("New user successfully added");

            return newUser.getInsertedId().toString();
        }else {
            System.out.println("user already exists");
            return null;
        }
    }

    public String scheduleAppointment(String patientName, String nurseName, LocalDate date){
        System.out.println("I got here");

        MongoCollection<Document> collection = Database.getUserCollection();

        System.out.println(collection);

        Document patient = collection.find(new Document("name", patientName).append("role", "Patient")).first();
        System.out.println(patient);
        Document nurse = collection.find(new Document("name", nurseName).append("role", "Nurse")).first();
        System.out.println(nurse);


        if(patient !=null && nurse !=null){
            AppointmentDTO appointment = new AppointmentDTO(
                    patient.getObjectId("_id").toHexString(),
                    nurse.getObjectId("_id").toHexString(),
                    date
            );
            Document doc = new Document("patientName", appointment.patientName)
                    .append("nurseName", appointment.nurseName)
                    .append("appointmentId", appointment.appointmentDate);
            InsertOneResult newAppointment = Database.getAppointmentsCollection().insertOne(doc);
            System.out.println("Appointment booked sucsessfully");
            return newAppointment.getInsertedId().toString();
        } else {
            System.out.println("Patient or nurse not found");
            return null;
        }
    }
}