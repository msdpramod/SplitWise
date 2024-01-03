package org.example.sliptwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestRegisterUserDTO {
    private String password;
    private String phoneNumber;
    private String userName ;
}
