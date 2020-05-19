package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.Service.*;
// TODO: 18/05/2020 Encrypt password

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    //TEST / EXAMPLE
//    @PostMapping("/test-create")
//    public String testCreate(@ModelAttribute Test test) throws TestException {
//        testS.testCreate(test);
//        return "test-html";
//    }
//
//    @GetMapping("/test-html")
//    public String test(){
//        return "test-html";
//    }
}
