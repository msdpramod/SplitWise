package org.example.sliptwise.Controllers;

import org.example.sliptwise.DTOs.RequestRegisterUserDTO;
import org.example.sliptwise.DTOs.ResponseRegisterUserDTO;
import org.example.sliptwise.Exceptions.UserExeception;
import org.example.sliptwise.Models.User;
import org.example.sliptwise.Service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    final private RegisterUserService registerUserService;
    @Autowired
    public UserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }
    public ResponseRegisterUserDTO registerUser(RequestRegisterUserDTO requestRegisterUserDTO) {

        User user;
        ResponseRegisterUserDTO response = new ResponseRegisterUserDTO();
        try {

            user = registerUserService.registerUser(
                    requestRegisterUserDTO.getUserName(),
                    requestRegisterUserDTO.getPhoneNumber(),
                    requestRegisterUserDTO.getPassword()
            );


            response.setUserId(user.getId());
            response.setStatus("SUCCESS");
        } catch (UserExeception userAlreadyExistsException) {
            response.setStatus("FAILURE");
            response.setMessage(userAlreadyExistsException.getMessage());
        }

        return response;

    }
}
