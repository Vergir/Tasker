/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.planner.controller;

import com.nc.planner.database.domain.DomainUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nc.planner.database.service.DomainUserRepo;

/**
 *
 * @author AlexandraHP
 */

@RestController
public class HelloController {
    @Autowired
    DomainUserRepo userRepo;

    @RequestMapping("/")
    public String index() {
        DomainUser user = new DomainUser();
        user.setName("Sasha");
        user.setPassword("qwerty");
        userRepo.save(user);
        
        return "Greetings from Spring Boot!";
    }

}