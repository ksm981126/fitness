package com.ksm981126.fitness.controller;

import com.ksm981126.fitness.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TrainerController {
    @Autowired TrainerService service;
    @GetMapping("/trainer")
    public String getTrainer(Model model, @RequestParam@Nullable String type,@RequestParam@Nullable String keyword,@RequestParam@Nullable Integer offset){
        model.addAttribute("data", service.getTrainerList(type, keyword, offset));
        return "trainer/trainer";
    }
}
