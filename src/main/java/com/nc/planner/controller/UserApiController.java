/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nc.planner.controller;

import com.nc.planner.database.domain.DomainUser;
import com.nc.planner.database.service.DomainUserRepo;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.ParseState;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author AlexandraHP
 */

@RestController
public class UserApiController {
    @Autowired
    DomainUserRepo userRepo;

    //stub
    Map<String, String> users;
    {
        users = new HashMap<String, String>();
        users.put("Alexandra", "@l3x@nDr@");
        users.put("Viktor", "V1kT0r");
        users.put("Evgeniy", "3\\/631Y");
        users.put("Daniil", "D@n11L");
    }

    @RequestMapping("/stubCheckUser/{username}/{password}")
    public @ResponseBody String stubCheckUser(@PathVariable(value ="username") String username, @PathVariable(value = "password") String password) {
        String valid = "yes";
        String invalid = "no";


        if (username.equals(password))
            return valid;
        for (String name : users.keySet())
            if (username.equals(name) && password.equals(users.get(name)))
                return valid;

        return invalid;
    }

    @RequestMapping("/stubGetUsers")
    public @ResponseBody String stubGetUsers() {
        JSONObject json = new JSONObject();
        for (String name : users.keySet())
            json.put(name, users.get(name));

        return json.toString();
    }
     
    @RequestMapping("/addUser/{user_name}/{user_pass}")
    public @ResponseBody String addUser(@PathVariable(value="user_name") String userName, 
            @PathVariable(value="user_pass") String userPass){
        DomainUser user=new DomainUser();
        user.setName(userName);
        user.setPassword(userPass);
        userRepo.save(user);
        return "add " + userName + " pass " + userPass;
    }
    
    @RequestMapping("/getUser/{user_name}")
    public @ResponseBody String getUser(@PathVariable(value="user_name") String userName){
        DomainUser user=userRepo.findByName(userName);
        return "name " + user.getName() + " and pass " + user.getPassword();
    }
    
    @RequestMapping("/getUsers")
    public @ResponseBody String getUsers(){
        Iterable<DomainUser> users = userRepo.findAll();
        StringBuilder sb = new StringBuilder();
        for(DomainUser user: users) {
            sb.append(user.getName()).append("<br>");
        }
        
        return sb.toString();
    }
}
