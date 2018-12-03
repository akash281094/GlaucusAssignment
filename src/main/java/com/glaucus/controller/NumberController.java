package com.glaucus.controller;

import com.glaucus.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NumberController {
    @Autowired
    NumberService numberService;

    //Get method to get the current value of counter.
    @GetMapping("/value")
    public String getNumber(Model model) {
        String val=String.valueOf(numberService.getNumber(1)) ;
        model.addAttribute("number",val);
        return "value";
    }

    //Get method to increment the value of counter.
    @GetMapping("/increment_number")
    public String incrementNumber(Model model) {
        boolean bool = numberService.incrementNumber(1);
        if (bool) {
            model.addAttribute("status","Counter Incremented Successfully");
        }
        else{
            model.addAttribute("status","Counter Increment Failed");
        }
        return "status";
    }
}

