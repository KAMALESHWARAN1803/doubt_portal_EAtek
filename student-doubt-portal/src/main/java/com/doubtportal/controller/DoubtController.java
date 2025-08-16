package com.doubtportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoubtController {

    @GetMapping("/submit-doubt")
    public String showForm() {
        return "submit-doubt"; // show form
    }

    @PostMapping("/submit-doubt")
    public String handleForm(@RequestParam String studentName,
                             @RequestParam String email,
                             @RequestParam String doubt,
                             Model model) {
        // You can save to DB here

        model.addAttribute("message", "✅ Doubt submitted successfully!");
        return "submit-doubt";
    }
}
