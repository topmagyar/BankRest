package com.dev.bank.configs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {

    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }
}

// UI(react, js/ts, html, css) - Filter Node.js server - REST API - Backend(logic, endpoints) - DB

//config - config files
//controller - classes with endpoints (REST API)
//service - logic
//model - classes with entities
//db - connection between table database and entities(save/update/delete/get)