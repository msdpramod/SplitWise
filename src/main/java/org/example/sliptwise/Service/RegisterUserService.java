package org.example.sliptwise.Service;

import org.example.sliptwise.Exceptions.UserExeception;
import org.example.sliptwise.Models.User;
import org.example.sliptwise.Models.UserStatus;
import org.example.sliptwise.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserService {
    private UserRepository userRepository;
    @Autowired
    public RegisterUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String userName, String password, String phoneNumber) throws UserExeception {
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (userOptional.isPresent()) {
            if (userOptional.get().getUserStatus().equals("ACTIVE")) {
                throw new UserExeception("User already exists");
            } else {
                User user = userOptional.get();
                user.setUserStatus(UserStatus.ACTIVE);
                user.setName(userName);
                user.setPassword(password);
                userRepository.save(user);

            }

        }
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setName(userName);
        user.setPassword(password);
        user.setUserStatus(UserStatus.ACTIVE);

        return userRepository.save(user);



    }
}
