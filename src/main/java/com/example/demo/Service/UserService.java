package com.example.demo.Service;

import com.example.demo.DBManager.DatabaseException;
import com.example.demo.Model.Customer;
import com.example.demo.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    UserRepository userRepository = new UserRepository();

    public ArrayList<Customer> getAllCustomers() {
        return userRepository.getAllCustomers();
    }
}
