package com.example.demo;

import com.example.demo.DBManager.DBManager;
import com.example.demo.DBManager.ErrorHandler;
import com.example.demo.Model.Order;
import com.example.demo.Model.Size;
import com.example.demo.Repository.*;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamMotorhomeApplicationTests {

    UserRepository userRepository = new UserRepository();
    OrderRepository orderRepository = new OrderRepository();
//    LoginService loginService = new LoginService();
    OrderService orderService = new OrderService();
    ErrorHandler errorHandler = new ErrorHandler();

    @Test
    void contextLoads() {
    }

    @Test
    public void testUser(){
        String expected = "Ulla";
        assertEquals(expected, userRepository.getStaff("Ulla@NordicRental.dk", "$2a$10$i5gODgRozIcullbsKeY0IOSO1xLQ1tcI4N0wC/ctfGxlIZq/s/Yci").getFirstName());
    }

    @Test
    public void testSeason(){
        assertEquals("middle", orderService.getSeason("10/04-2020"));
        assertEquals("high", orderService.getSeason("10/06-2020"));
        assertEquals("low", orderService.getSeason("10/11-2020"));
    }

    @Test
    public void searchNonExistingOrder() {
        assertNull(orderRepository.getOrder(-1));
        assertNotNull(orderRepository.getOrder(9));
    }

    @Test
    public void testError(){
        String path = "/error";
        assertEquals(path, errorHandler.getErrorPath());
    }

    @Test
    public void testLastId() {
        assertEquals(9, orderRepository.getLastOrderId());
    }
    //Skift funktionnavne
    //opret kunde ell. lign.
}
