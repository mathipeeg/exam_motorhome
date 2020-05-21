package com.example.demo.Controller;
import com.example.demo.DBManager.OrderException;
import com.example.demo.Model.CustomerOrder;
import com.example.demo.Model.Extras;
import com.example.demo.Model.Order;
import com.example.demo.Model.OrderExtras;
import com.example.demo.Model.Staff;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.engine.IterationStatusVar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class OrderController {
    OrderService orderService = new OrderService();
    OrderRepository orderRepository = new OrderRepository();
    Order order = new Order();

    @GetMapping("/create-order")
    public String createOrder(HttpServletRequest request){
        HttpSession session = request.getSession();
        Staff user = (Staff) session.getAttribute("user");
        if (user != null) {
            return "create-order";
        }
        else
            return"login";
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
    public String orderSubmitted(Model model) throws OrderException
    {
        orderRepository.getOrder(orderRepository.getLastOrderId());
        System.out.println(orderRepository.getLastOrderId());

        model.addAttribute("order", order);

        return "order-submitted";
    }
}