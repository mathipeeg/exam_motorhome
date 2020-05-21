package com.example.demo.Controller;
import com.example.demo.DBManager.*;
import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    OrderService orderService = new OrderService();
    LoginService loginService = new LoginService();
    OrderRepository orderRepository = new OrderRepository();

    @GetMapping("/create-order")
    public String createOrder(HttpServletRequest request, Model model){
        model.addAttribute("motorhomes", orderRepository.getAllMotorhomes());
        return loginService.checkCurrentUser(request, "create-order");
    }

    @PostMapping("/submit-order")
    public String submitOrder(@ModelAttribute CustomerOrder customerOrder) throws CustomException {
        orderService.submitOrder(customerOrder);
        return "redirect:/add-extras";
    }
    @GetMapping("add-extras")
    public String addExtras(Model model){
        model.addAttribute("extras", orderRepository.getAllExtras());
        return "add-extras";
    }
    @PostMapping("/addExtra")
    public String addExtra(@RequestParam("extraId") int extraId) throws CustomException {
        orderService.addExtra(extraId);
        return "order-submitted";
    }
    @GetMapping("/order-submitted")
    public String orderSubmitted(HttpServletRequest request, Model model) throws CustomException {
        Order order = orderService.getOrder(orderRepository.getLastOrderId());
        model.addAttribute("order", order);
        return loginService.checkCurrentUser(request, "order-submitted");
    }
}