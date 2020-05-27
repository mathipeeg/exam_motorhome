package com.example.demo.Controller;

import com.example.demo.Repository.FleetRepository;
import com.example.demo.Service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FleetController {

    LoginService loginService = new LoginService();
    FleetRepository fleetRepository = new FleetRepository();

    @GetMapping("/get-fleet")
    public String getFleets(Model model, HttpServletRequest request){
        model.addAttribute("motorhomes", fleetRepository.getAllMotorhomes());
        return loginService.checkCurrentUser(request, "get-fleet");
    }
}
