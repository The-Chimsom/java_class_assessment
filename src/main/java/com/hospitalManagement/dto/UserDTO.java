package com.hospitalManagement.dto;

public class UserDTO {
    public String id;
    public String name;
    public String role;

    public UserDTO(String name, String role){
        this.name = name;
        this.role = role;
    }
}