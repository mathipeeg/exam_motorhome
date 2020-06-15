package com.example.demo.Service;

import com.example.demo.Model.Staff;
import com.example.demo.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.*;
import java.util.ArrayList;

@Service
public class LoginService {

    UserRepository userRepository = new UserRepository();

    public boolean checkLogin(Staff staff) {
        return userRepository.checkLogin(staff.getEmail(), staff.getPassword());
    }

    public String checkCurrentUser(HttpServletRequest request, String page){
        HttpSession session = request.getSession();
        Staff user = (Staff) session.getAttribute("user");
        if (user != null) {
            return page;
        }
        else
            return "login";
    }

    //Bruges én gang første gang, for at kryptere passwords,
    //da brugere ikke bliver oprettet via hjemmesiden, men manuelt i denne version af systemet
    public void encryptPasswords(){
        ArrayList<Staff> staffArray = userRepository.getAllStaff();
        for (Staff staff : staffArray) {
            staff.setPassword(BCrypt.hashpw(staff.getPassword(), BCrypt.gensalt()));
            userRepository.updatePassword(staff);
        }
    }
}
