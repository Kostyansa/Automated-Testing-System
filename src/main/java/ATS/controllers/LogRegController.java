package ATS.controllers;

import ATS.DTO.User;
import ATS.Services.IUserService;
import ATS.Services.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.CommunicationException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LogRegController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login");

        request.getSession().invalidate();

        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(Long idUser, String password) {
        ModelAndView modelAndView = new ModelAndView();
        User currentUser = userService.checkUser(new User(idUser, null, null, password));
        if (currentUser != null) {
            userManager.setUser(currentUser);
            modelAndView.setViewName("redirect:/main");
            modelAndView.addObject("user", currentUser);
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("errorMsg", "Логин/Пароль неверен");
        }
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView registrationPage(User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registration(Long idUser, String name, String password, String email) {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User(idUser, name, email, password);

        if (userService.getById(user.getIdUser()) == null){
            userService.regUser(user);
        } else {
            modelAndView.addObject("errorMsg", "Пользователь с таким ID уже существует");
        }

        return modelAndView;
    }
}
