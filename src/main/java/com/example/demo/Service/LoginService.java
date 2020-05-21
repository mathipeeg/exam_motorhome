package com.example.demo.Service;

import com.example.demo.Model.Staff;
import com.example.demo.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    UserRepository userRepository = new UserRepository();

    public boolean checkLogin(Staff staff) {
        return userRepository.checkLogin(staff.getEmail(), staff.getPassword());
    }

    public void encryptPasswords(){
        //String hashed = BCrypt.hashpw(staff.getPassword(), BCrypt.gensalt());
        ArrayList<Staff> staffArray = userRepository.getAllStaff();
        for (Staff staff : staffArray) {
            staff.setPassword(passwordEncoder.encode(staff.getPassword()));
            userRepository.updatePassword(staff);
        }
    }
}
