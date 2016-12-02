package controllers;

import beans.TaskManager;
import beans.UserManager;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.io.Serializable;

/**
 * Created by Vergir on 30/11/2016.
 */

@Controller
@Scope("session")
public class TaskController implements Serializable {
    @Autowired
    UserManager userManager;

    User currentUser = null;

    public TaskController() {}

    @RequestMapping({"/tasks"})
    public ModelAndView tasks(@RequestParam Long user) {
        ModelAndView mav = new ModelAndView("show_tasks");
        mav.addObject("user", userManager.getUser(user));
        mav.addObject("tasks", userManager.getUserTasks(userManager.getUser(user)));
        return mav;
    }


}
