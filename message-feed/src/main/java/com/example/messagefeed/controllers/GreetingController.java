package com.example.messagefeed.controllers;

import com.example.messagefeed.domain.Massage;
import com.example.messagefeed.repos.MassageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private MassageRepo massageRepo;
    @GetMapping("/greeting")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }
    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Massage> massages = massageRepo.findAll();
        model.put("massages", massages);
        return "main";
    }
    @PostMapping
    public String add(@RequestParam String text,@RequestParam String tag, Map<String, Object> model) {
        Massage massage = new Massage(text, tag);
        massageRepo.save(massage);

        Iterable<Massage> massages = massageRepo.findAll();
        model.put("massages", massages);

        return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model){
        Iterable<Massage> massages;
        if(filter != null && !filter.isEmpty()){
            massages = massageRepo.findByTag(filter);
        } else {
            massages = massageRepo.findAll();
        }

        model.put("massages", massages);
        return "main";
    }

}
