package com.example.demo;

import com.example.demo.DBManager.CustomException;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.LoginService;
import com.example.demo.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExamMotorhomeApplicationTests {

    UserRepository userRepository = new UserRepository();
    OrderRepository orderRepository = new OrderRepository();
//    LoginService loginService = new LoginService();
    OrderService orderService = new OrderService();

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        String expected = "Ulla";
        assertEquals(expected, userRepository.getStaff("Ulla@NordicRental.dk", "$2a$10$K3YQCZ9vAJdfpdUEUgOpy.Kfh4BVxCI73ORupYTQo5plgOH5sZ06K").getFirstName());
    }

//    @Test
//    public void testLogin(HttpServletRequest request){
//        String expected = "login";
//        assertEquals(expected, loginService.checkCurrentUser(request, "create-order"));
//    }

    @Test
    public void testSeason(){
        String expected = "middle";
        assertEquals(expected, orderService.getSeason("10/04-2020"));
    }

    @Test
    public void testOrder() throws CustomException {
        String expected = null;
        assertEquals(expected, orderRepository.getOrder(1));
    }
}
