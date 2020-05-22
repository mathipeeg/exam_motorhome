package com.example.demo.Controller;
//import com.example.demo.DBManager.OrderException;
import com.example.demo.Model.*;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.OrderService;
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
    public String orderSubmitted(HttpServletRequest request, Model model) throws CustomException
    {
        Order order = orderRepository.getOrder(orderRepository.getLastOrderId());
        Customer customer = orderRepository.getCustomerInfo(orderRepository.getLastOrderId());
        Motorhome motorhome = orderRepository.getMotorhomeInfo(orderRepository.getLastOrderId());

        model.addAttribute("totalprice", orderService.totalPrice(order, "totalPriceAll"));
        model.addAttribute("nightstotalprice", orderService.totalPrice(order, "hej"));
        model.addAttribute("orderextra", orderRepository.getExtraInfo(orderRepository.getLastOrderId()));
        model.addAttribute("order", order);
        model.addAttribute("customer", customer);
        model.addAttribute("motorhome", motorhome);


        return loginService.checkCurrentUser(request, "order-submitted");

    }
}