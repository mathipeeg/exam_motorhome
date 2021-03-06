package com.example.demo.Controller;
import com.example.demo.Model.Staff;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.Service.*;
import javax.servlet.http.*;

@Controller
public class HomeController {

    LoginService loginService = new LoginService();

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("role");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@ModelAttribute Staff staff, HttpServletRequest request){
        if(loginService.checkLogin(staff)){
            HttpSession session = request.getSession();
            session.setAttribute("user", staff);
            session.setAttribute("role", staff.getPosition());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}