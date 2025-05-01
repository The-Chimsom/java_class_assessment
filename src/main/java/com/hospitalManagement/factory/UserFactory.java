package com.hospitalManagement.factory;

import com.hospitalManagement.dto.UserDTO;

public class UserFactory {
    public static UserDTO createUser(String name, String role){
        return new UserDTO(name, role);
    }
}
