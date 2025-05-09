package com.scaler.productservicemay25.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {
    @GetMapping("/sayhello")
    public String Sample(){
        return "Hellow World";
    }

    @GetMapping("/sayhello2")
    public String Sample2(){
        return "Hellow Pooja";
    }
}
