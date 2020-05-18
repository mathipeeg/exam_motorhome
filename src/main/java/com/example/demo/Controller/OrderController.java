package com.example.demo.Controller;

import com.example.demo.Model.CustomerOrder;
import com.example.demo.Service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    OrderService orderService = new OrderService();

    @GetMapping("/create-order")
    public String createOrder(){
        return "create-order";
    }

    @PostMapping("/submit-order")
    public String submitOrder(@ModelAttribute CustomerOrder customerOrder){
        orderService.submitOrder(customerOrder);
        return "order-submitted";
    }
}
