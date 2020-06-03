package com.example.demo;

import com.example.demo.Controller.OrderController;
import com.example.demo.DBManager.ErrorHandler;
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
    OrderService orderService = new OrderService();
    ErrorHandler errorHandler = new ErrorHandler();
    OrderController orderController = new OrderController();

    @Test
    void contextLoads() {
    }

    @Test
    public void checkUser(){
        String expected = "Ulla";
        assertEquals(expected, userRepository.getStaff("Ulla@NordicRental.dk", "$2a$10$i5gODgRozIcullbsKeY0IOSO1xLQ1tcI4N0wC/ctfGxlIZq/s/Yci").getFirstName());
    }

    @Test
    public void assertReturnsArentNull(){
        assertNotNull(orderRepository.getCustomer("casperlm@outlook.dk"));
        assertNotNull(orderRepository.getOrder(1));
    }

    @Test
    public void assertLoginReturnsFalse(){
        assertTrue(userRepository.checkLogin("ulla@nordicrental.dk", "ULLA123"));
    }

    @Test
    public void seasonCheck(){
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
    public void errorPath(){
        String path = "/error";
        assertEquals(path, errorHandler.getErrorPath());
    }

    @Test
    public void lastOrderId() {
        assertEquals(9, orderRepository.getLastOrderId());
    }

    @Test
    public void assertAmountOfNights(){
        assertEquals(1, orderService.getNights("10/10-2020", "11/10-2020"));
    }

    @Test
    public void checkSeasonalPrice(){
        assertEquals(130, orderService.getSeasonalPrice("middle", 100));
    }

    @Test
    public void assertCorrectPageReturn(){
        assertEquals("order-submitted", orderController.addExtra(1));
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void throwTest(){
        orderRepository.getSize(100);
    }
}
