package com.example.demo.Controller;

import com.example.demo.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FleetController {

    LoginService loginService = new LoginService();
    FleetService fleetService = new FleetService();

    @GetMapping("/get-fleet")
    public String getFleets(Model model, HttpServletRequest request) {
        model.addAttribute("motorhomes", fleetService.getAllMotorhomes());
        return loginService.checkCurrentUser(request, "get-fleet");
    }
}
