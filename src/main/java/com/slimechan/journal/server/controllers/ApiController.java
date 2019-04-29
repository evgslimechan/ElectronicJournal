package com.slimechan.journal.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ApiController {

    @PostMapping(value = "/createnews")
    private String apiCreateNews(){
        return "{}";
    }

}
