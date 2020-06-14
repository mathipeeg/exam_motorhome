package com.example.demo.Controller;
import com.example.demo.Model.*;
import com.example.demo.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
    OrderService orderService;
    LoginService loginService;
    FleetService fleetService;
    UserService userService;

    @GetMapping("/create-order")
    public String createOrder(HttpServletRequest request, Model model) {
        model.addAttribute("motorhomes", fleetService.getAllMotorhomes());
        model.addAttribute("customers", userService.getAllCustomers());
        return loginService.checkCurrentUser(request, "create-order");
    }

    @PostMapping("/submit-order")
    public String submitOrder(@ModelAttribute CustomerOrder customerOrder) {
        orderService.submitOrder(customerOrder);
        return "redirect:/add-extras";
    }

    @GetMapping("add-extras")
    public String addExtras(Model model, HttpServletRequest request) {
        model.addAttribute("extras", orderService.getAllExtras());
        return loginService.checkCurrentUser(request, "add-extras");
    }

    @PostMapping("/addExtra")
    public String addExtra(@RequestParam("extraId") int extraId) {
        orderService.addExtra(extraId);
        return "order-submitted";
    }

    @GetMapping("/order-submitted")
    public String orderSubmitted(HttpServletRequest request, Model model) {
        Order order = orderService.getOrder();

        model.addAttribute("totalprice", orderService.totalPrice(order, "totalPrice"));
        model.addAttribute("nightstotalprice", orderService.totalPrice(order, ""));
        model.addAttribute("orderextra", orderService.getOrderExtra());
        model.addAttribute("order", order);
        model.addAttribute("customer", orderService.getCustomer(order));
        model.addAttribute("motorhome", fleetService.getMotorhome(order));
        return loginService.checkCurrentUser(request, "order-submitted");
    }
}