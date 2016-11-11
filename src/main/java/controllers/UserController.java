package controllers;

import java.io.Serializable;
import java.util.Map;

import beans.UserManager;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Vergir on 04/11/2016.
 */
@Controller
@Scope("session")
public class UserController implements Serializable{
    @Autowired
    UserManager userManager;

    User currentUser = null;

    public UserController() {}

    @RequestMapping({"/","/login","/show_users"})
    public String showLoginPage(Map<String, Object> model) {
        return (currentUser != null) ? "show_users" : "login";
    }

    @RequestMapping({"/check_user"})
    public @ResponseBody
    String showHomePage(@RequestParam String username, @RequestParam String password) {
        if (userManager.userExists(username, password)) {
            currentUser = new User(username, password);
            return "yes";
        } else
            return "no";
    }
}
