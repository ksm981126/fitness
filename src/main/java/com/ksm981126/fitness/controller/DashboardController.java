package com.ksm981126.fitness.controller;


import com.ksm981126.fitness.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @Autowired DashboardService service;
    @GetMapping("/")
    public String getDashboard(Model model){
        model.addAttribute("cnt",service.getCounts());
        model.addAttribute("update", service.getUpdateDate());
        return "/dashboard/dashboard";
    }
}
