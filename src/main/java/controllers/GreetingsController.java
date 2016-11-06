package controllers;

import java.util.Map;

import beans.User;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Vergir on 04/11/2016.
 */
@Controller
public class GreetingsController implements ApplicationContextAware{
    private ApplicationContext context;

    public GreetingsController() {}

    @RequestMapping({"/","/greetings"})
    public String showHomePage(Map<String, Object> model) {
        model.put("user", (User)context.getBean("user"));
        return "greetings";
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
