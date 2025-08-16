package com.doubtportal.controller;

import com.doubtportal.model.Doubt;
import com.doubtportal.model.User;
import com.doubtportal.repository.DoubtRepository;
import com.doubtportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private DoubtRepository doubtRepo;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userRepo.save(user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userRepo.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            if (user.getRole().equals("admin")) {
                List<Doubt> doubts = doubtRepo.findAll();
                model.addAttribute("doubts", doubts);
                return "view-doubts";
            } else {
                List<Doubt> doubts = doubtRepo.findByAskedBy(user.getEmail());
                model.addAttribute("doubts", doubts);
                return "ask-doubt";
            }
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    @PostMapping("/ask-doubt")
    public String askDoubt(@RequestParam String email, @RequestParam String question, Model model) {
        Doubt doubt = new Doubt();
        doubt.setAskedBy(email);
        doubt.setQuestion(question);
        doubtRepo.save(doubt);

        List<Doubt> doubts = doubtRepo.findByAskedBy(email);
        model.addAttribute("doubts", doubts);
        model.addAttribute("user", userRepo.findByEmail(email));
        return "ask-doubt";
    }

    @PostMapping("/answer-doubt")
    public String answerDoubt(@RequestParam int id, @RequestParam String answer, @RequestParam String admin, Model model) {
        Doubt doubt = doubtRepo.findById(id).orElse(null);
        if (doubt != null) {
            doubt.setAnswer(answer);
            doubt.setAnsweredBy(admin);
            doubtRepo.save(doubt);
        }
        List<Doubt> doubts = doubtRepo.findAll();
        model.addAttribute("doubts", doubts);
        return "view-doubts";
    }
}
