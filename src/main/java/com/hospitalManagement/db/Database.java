package com.hospitalManagement.db;

import com.mongodb.client.*;
import org.bson.Document;

public  class Database{
    private static final MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/HOSPITAL_MANAGEMENT");
    private static final MongoDatabase database = mongoClient.getDatabase("HOSPITAL_MANAGEMENT");


    public static MongoCollection<Document> getUserCollection (){
        return  database.getCollection("USERS");
    }


    public static MongoCollection<Document> getAppointmentsCollection (){
        return  database.getCollection("APPOINTMENTS");
    }


    public static MongoCollection<Document> getVitalsCollection (){
        return  database.getCollection("VITALS");
    }
}