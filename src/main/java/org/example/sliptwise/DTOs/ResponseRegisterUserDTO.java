package org.example.sliptwise.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseRegisterUserDTO {
    private Long userId;
    private String status;
    private String message;
}
