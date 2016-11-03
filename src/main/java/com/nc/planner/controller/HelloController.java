/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nc.planner.database.service.DomainUserRepo;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author AlexandraHP
 */

@RestController
public class HelloController {
    @Autowired
    DomainUserRepo userRepo;

    String loginPage;

    {
        byte[] encoded = null;
        try {
            encoded = Files.readAllBytes(Paths.get("src/main/web/loginPage.html"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginPage = new String(encoded, Charset.defaultCharset());
    }

    @RequestMapping("/")
    public String index() {
        /*
        DomainUser user = new DomainUser();
        user.setName("Sasha");
        user.setPassword("qwerty");
        userRepo.save(user);
        */
        return loginPage;
    }
}