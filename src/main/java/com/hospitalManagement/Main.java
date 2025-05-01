package com.hospitalManagement;

import com.hospitalManagement.api.HospitalManagementApi;

public class Main {
    public static void main(String[] args) {
        HospitalManagementApi.start();
        System.out.println("Server has started on port 4567");
    }
}
