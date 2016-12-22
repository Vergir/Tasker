package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beans.*;
import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by Vergir on 04/11/2016.
 */
@Controller
@Scope("session")
public class MainController implements Serializable{
    @Autowired
    UserManager userManager;
    @Autowired
    RoleManager roleManager;
    @Autowired
    TaskManager taskManager;
    @Autowired
    TaskStatusManager taskStatusManager;
    @Autowired
    PriorityManager priorityManager;

    User currentUser = null;


    @RequestMapping({"/","/login"})
    public String showIndexPage(Map<String, Object> model) {
        return (currentUser != null) ? "main" : "login";
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

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        if (currentUser == null || !currentUser.getRole().getCanCreateUsers())
            return new ModelAndView("login");
                userManager.addUser(username, password, roleManager.getRole(role));
        return main(null, Integer.MAX_VALUE);
    }
    @RequestMapping(value = "/add_task", method = RequestMethod.POST)
    public ModelAndView addTask(@RequestParam String taskDesc,
                                @RequestParam Long assignee,
                                @RequestParam String status,
                                @RequestParam String priority) {
        if (currentUser == null || !currentUser.getRole().getCanAssignDevelopers())
            return new ModelAndView("login");
        User user = userManager.getUser(assignee);
        TaskStatus taskStatus = taskStatusManager.getTaskStatus(status);
        Priority prio = priorityManager.getPriority(priority);
        taskManager.addTask(new Task(taskDesc, user, currentUser, taskStatus, prio));

        return main(assignee, null);
    }

    @RequestMapping({"/main"})
    public ModelAndView main(@RequestParam(required = false) Long user,
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

        ModelAndView mav = new ModelAndView("main");
        User u = userManager.getUser(user);
        mav.addObject("pageLowerLimit", page == 1);
        mav.addObject("pageUpperLimit", page*USERS_PER_PAGE >= allUsers.size());
        mav.addObject("currentUser", currentUser);
        mav.addObject("activeUser", u);
        mav.addObject("page", page);
        mav.addObject("users", usersOnPage);
        mav.addObject("tasks", userManager.getUserTasks(u));
        return mav;
    }


    @RequestMapping({"/edit_task", "/show_task"})
    public ModelAndView task(@RequestParam Long task) {
        if (currentUser == null)
            return new ModelAndView("login");


        ModelAndView mav = null;
        if (currentUser.getRole().getCanAssignDevelopers())
            mav = new ModelAndView("edit_task");
        else
            mav = new ModelAndView("view_task");
        Task taskToChange = taskManager.getTask(task);
        mav.addObject("task", taskToChange);
        mav.addObject("currentUser", currentUser);
        return mav;
    }

    @RequestMapping({"/update_task"})
    public ModelAndView updateTask(@RequestParam Long task,
                                   @RequestParam String taskDesc,
                                   @RequestParam Long assignee,
                                   @RequestParam String status,
                                   @RequestParam String priority) {
        if (currentUser == null || !currentUser.getRole().getCanAssignDevelopers())
            return new ModelAndView("login");
        TaskStatus taskStatus = taskStatusManager.getTaskStatus(status);
        Priority prio = priorityManager.getPriority(priority);

        Task taskToChange = taskManager.getTask(task);
        taskToChange.setDescription(taskDesc);
        taskToChange.setAssignee(userManager.getUser(assignee));
        taskToChange.setStatus(taskStatusManager.getTaskStatus(status));
        taskToChange.setPriority(priorityManager.getPriority(priority));

        return main(assignee, null);
    }

    @RequestMapping({"/log_out"})
    public ModelAndView logOut() {
        currentUser = null;
        return new ModelAndView("login");
    }
}
