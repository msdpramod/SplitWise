package org.example.sliptwise.Commands;

import org.example.sliptwise.Controllers.UserController;
import org.example.sliptwise.DTOs.RequestRegisterUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Component
public class RegisterUserCommand implements Command{
    private UserController userController;
    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean mathces(String input) {
        List<String> inputList = Arrays.stream(input.split(" ")).toList();
        if(inputList.size() ==4 || inputList.get(0).equalsIgnoreCase(CommandKeywords.REGISTER_USER)){
            return true;

        }
        return false;

    }

    @Override
    public void execute(String input) {
        List<String> inputList = Arrays.stream(input.split(" ")).toList();
        String password = inputList.get(1);
        String phoneNumber = inputList.get(2);
        String userName = inputList.get(3);
        RequestRegisterUserDTO request = new RequestRegisterUserDTO();
        request.setPassword(password);
        request.setPhoneNumber(phoneNumber);
        request.setUserName(userName);


        userController.registerUser(request);

    }
}
