package com.slimechan.journal.server.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;

public class ErrController implements ErrorController {
    @Override
    public String getErrorPath() {
       return "error";
    }
}
