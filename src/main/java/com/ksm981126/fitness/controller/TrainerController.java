package com.ksm981126.fitness.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainerController {
    @GetMapping("/trainer")
    public String getTrainer(){
        return "trainer/trainer";
    }
}
