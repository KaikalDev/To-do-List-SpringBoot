package kaique.luan.dev.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kaique.luan.dev.domain.User;
import kaique.luan.dev.service.CookiesService;
import kaique.luan.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserService service;

    @Autowired
    private LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @PostMapping("/login/")
    public String loginPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpServletResponse response) {

        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            model.addAttribute("errorEmpty", "Username and password are required");
            return "Login";
        }

        if (!service.UserExist(username)) {
            model.addAttribute("errorUser", "User does not exist.");
            return "Login";
        }
        Optional<User> user = service.findByUserName(username);
        if(user.isEmpty() || !service.checkPassword(user.get(), password)) {
            model.addAttribute("errorPassword", "Incorrect password.");
            return "Login";
        }

        CookiesService.setCookie(response, user.get().getId().toString());

        return "redirect:/listTask?filter=&title=";
    }
}
