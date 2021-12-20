package com.ksm981126.fitness.controller;

import java.util.Map;

import com.ksm981126.fitness.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired MemberService service;
    @GetMapping("/main")
    public String getMain(Model model, @RequestParam @Nullable Integer offset,@RequestParam@Nullable String keyword){
        Map<String,Object> resultMap = service.getMemberList(offset,keyword);
        model.addAttribute("data", resultMap);
        return"/main/index";
    }
}
