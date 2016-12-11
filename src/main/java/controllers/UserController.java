package controllers;

import java.io.Serializable;
import java.util.Map;

import beans.RoleManager;
import beans.UserManager;
import entities.Role;
import entities.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by Vergir on 04/11/2016.
 */
@Controller
@Scope("session")
public class UserController implements Serializable{
    @Autowired
    UserManager userManager;
    @Autowired
    RoleManager roleManager;

    User currentUser = null;

    public UserController() {}

    @RequestMapping({"/","/login","/show_users"})
    public String showIndexPage(Map<String, Object> model) {
        return (currentUser != null) ? "show_users" : "login";
    }

    @RequestMapping({"/check_user"})
    public @ResponseBody
    String checkUser(@RequestParam String username, @RequestParam String password) {
        User attempter = userManager.getUser(username, password);
        if (attempter != null) {
            currentUser = attempter;
            return "yes";
        }
        else
            return "no";
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.GET)
    public String addUser() {
        if (currentUser == null || !currentUser.getRole().getCanCreateUsers())
            return "login";
        return "add_user";
    }
    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (currentUser == null || !currentUser.getRole().getCanCreateUsers())
            return "login";
        for (Role r : roleManager.getRoles())
            if (r.toString().equals(role)) {
                userManager.addUser(username, password, r);

            }
        return "show_users";
    }

    @RequestMapping({"/tasks"})
    public ModelAndView tasks(@RequestParam Long user) {
        if (currentUser == null)
            return new ModelAndView("login");

        ModelAndView mav = new ModelAndView("show_users");
        mav.addObject("activeUser", )
        mav.addObject("tasks", userManager.getUserTasks(userManager.getUser(user)));
        return mav;
    }
}
