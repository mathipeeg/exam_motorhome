package com.example.demo.Controller;
import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import com.example.demo.Service.*;
import com.example.demo.DBManager.*;
import com.example.demo.Model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    OrderService orderService = new OrderService();
    LoginService loginService = new LoginService();
    OrderRepository orderRepository = new OrderRepository();
    UserRepository userRepository = new UserRepository();

    @GetMapping("/create-order")
    public String createOrder(HttpServletRequest request, Model model){
        model.addAttribute("motorhomes", orderRepository.getAllMotorhomes());
<<<<<<< HEAD
        model.addAttribute("customers", userRepository.getAllCustomers());
        return loginService.checkCurrentUser(request, "create-order");
//        return "create-order";
=======

//        return loginService.checkCurrentUser(request, "create-order");
        return "create-order";

>>>>>>> 7e577d6210cc5d776f5ca9431c3b6ab236828a27
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
        Customer customer = orderRepository.getCustomerInfo(order.getCustomerId());
        Motorhome motorhome = orderRepository.getMotorhomeInfo(order.getMotorhomeId());

        model.addAttribute("totalprice", orderService.totalPrice(order, "totalPriceAll"));
        model.addAttribute("nightstotalprice", orderService.totalPrice(order, "hej"));
        model.addAttribute("orderextra", orderRepository.getExtraInfo(orderRepository.getLastOrderId()));
        model.addAttribute("order", order);
        model.addAttribute("customer", customer);
        model.addAttribute("motorhome", motorhome);

        return loginService.checkCurrentUser(request, "order-submitted");

    }
}