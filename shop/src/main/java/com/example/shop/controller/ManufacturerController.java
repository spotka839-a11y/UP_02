package com.example.shop.controller;

import com.example.shop.Manufacturer;
import com.example.shop.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ManufacturerController {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("/manufacturers")
    @ResponseBody
    public List<Manufacturer> manufacturers() {
        return manufacturerRepository.findAll();
    }

    @GetMapping("/manufacturers-page")
    public String manufacturersPage(Model model) {
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        return "manufacturers";
    }
}
