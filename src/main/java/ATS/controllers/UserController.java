package ATS.controllers;

import ATS.DTO.User;
import ATS.Services.IUserService;
import ATS.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserManager userManager;

    @Autowired
    private IUserService userService;

    @RequestMapping("/main")
    private ModelAndView mainPage(){
        User currentUser = userManager.getUser();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", currentUser);
        modelAndView.setViewName("main");
        return modelAndView;
    }
}
