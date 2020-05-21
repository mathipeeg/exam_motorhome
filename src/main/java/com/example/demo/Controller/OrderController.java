package com.example.demo.Controller;

import com.example.demo.DBManager.OrderException;
import com.example.demo.Model.CustomerOrder;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.engine.IterationStatusVar;

@Controller
public class OrderController {

    OrderService orderService = new OrderService();
    OrderRepository orderRepository = new OrderRepository();

    @GetMapping("/create-order")
    public String createOrder(Model model){
        model.addAttribute("motorhomes", orderRepository.getAllMotorhomes());
        model.addAttribute("brand", orderRepository.getBrand());
        return "create-order";
    }


    @PostMapping("/submit-order")
    public String submitOrder(@ModelAttribute CustomerOrder customerOrder) throws OrderException {
        orderService.submitOrder(customerOrder);
        return "redirect:/add-extras";
    }

    @GetMapping("add-extras")
    public String addExtras(Model model){
        model.addAttribute("extras", orderRepository.getAllExtras());
        return "add-extras";
    }

    @PostMapping("/addExtra")
    public String addExtra(@RequestParam("extraId") int extraId) throws OrderException {
        orderService.addExtra(extraId);
        return "order-submitted";
    }

    @GetMapping("/order-submitted")
    public String orderSubmitted(){
        return "order-submitted";
    }

}
