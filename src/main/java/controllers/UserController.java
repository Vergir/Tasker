package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import beans.RoleManager;
import beans.TaskManager;
import beans.UserManager;
import com.sun.org.apache.xpath.internal.operations.Mod;
import entities.Role;
import entities.Task;
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
    @Autowired
    TaskManager taskManager;

    User currentUser = null;

    public UserController() {}

    @RequestMapping({"/","/login"})
    public String showIndexPage(Map<String, Object> model) {
        return (currentUser != null) ? "tasks" : "login";
    }

    @RequestMapping({"/check_user"})
    public @ResponseBody
    String checkUser(@RequestParam String username, @RequestParam String password) {
        User attempter = userManager.getUser(username, password);
        if (attempter != null) {
            currentUser = attempter;
            return currentUser.getId().toString();
        }
        else
            return "no";
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (currentUser == null || !currentUser.getRole().getCanCreateUsers())
            return new ModelAndView("login");
        for (Role r : roleManager.getRoles())
            if (r.toString().equals(role))
                userManager.addUser(username, password, r);
        return tasks(null, Integer.MAX_VALUE);
    }
    @RequestMapping(value = "/add_task", method = RequestMethod.POST)
    public ModelAndView addTask(@RequestParam String taskDesc, @RequestParam String assigneeName) {
        if (currentUser == null || !currentUser.getRole().getCanCreateUsers())
            return new ModelAndView("login");
        User assignee = null;
        for (User u : userManager.getUsers())
            if (u.getUsername().equals(assigneeName))
                assignee = u;
        taskManager.addTask(new Task(taskDesc, assignee.getId()));

        return tasks(assignee.getId(), null);
    }

    @RequestMapping({"/tasks"})
    public ModelAndView tasks(@RequestParam(required = false) Long user,
                              @RequestParam(required = false) Integer page) {
        final int USERS_PER_PAGE = 15;

        if (currentUser == null)
            return new ModelAndView("login");
        if (user == null)
            user = currentUser.getId();
        if (page == null)
            page = 1 + userManager.getUsers().indexOf(userManager.getUser(user)) / USERS_PER_PAGE;
        else if (page < 1)
            page = 1;
        else if (page*USERS_PER_PAGE > userManager.getUsers().size())
            page = userManager.getUsers().size() / USERS_PER_PAGE + ((userManager.getUsers().size() % USERS_PER_PAGE > 0) ? 1 : 0);

        List<User> allUsers = userManager.getUsers();
        List<User> usersOnPage = new ArrayList<User>();
        int upperLimit = page*USERS_PER_PAGE < allUsers.size() ? page*USERS_PER_PAGE : allUsers.size();
        for (int i = (page-1)*USERS_PER_PAGE; i < upperLimit; i++)
            usersOnPage.add(allUsers.get(i));

        ModelAndView mav = new ModelAndView("tasks");
        User u = userManager.getUser(user);
        mav.addObject("pageLowerLimit", page == 1);
        mav.addObject("pageUpperLimit", page*USERS_PER_PAGE >= allUsers.size());
        mav.addObject("currentUser", currentUser);
        mav.addObject("activeUserId", user);
        mav.addObject("page", page);
        mav.addObject("users", usersOnPage);
        mav.addObject("userTasks", u.getUsername()+"'s Tasks");
        mav.addObject("tasks", userManager.getUserTasks(u));
        return mav;
    }

    @RequestMapping({"/log_out"})
    public ModelAndView logOut() {
        currentUser = null;
        return new ModelAndView("login");
    }
}
