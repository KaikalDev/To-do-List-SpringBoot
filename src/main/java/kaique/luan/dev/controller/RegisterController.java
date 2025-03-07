package kaique.luan.dev.controller;

import jakarta.servlet.http.HttpSession;
import kaique.luan.dev.domain.User;
import kaique.luan.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final UserService service;

    @Autowired
    private RegisterController(UserService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String Register() {
        return "Register";
    }

    @PostMapping("/register/")
    public String Register(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model,
            HttpSession session) {
        if (service.UserExist(username) || service.EmailExist(email) || !password.equals(confirmPassword)) {
            if (service.UserExist(username)) {
                model.addAttribute("errorUser", "Username already exists.");
            }
            if (service.EmailExist(email)) {
                model.addAttribute("errorEmail", "Email already exists.");
            }
            if (!password.equals(confirmPassword)) {
                model.addAttribute("errorPassword", "Passwords do not match.");
            }
            return "Register";
        }

        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password);

        user = service.cadastrar(user);
        session.setAttribute("user", user);
        return "redirect:/login";
    }

}
