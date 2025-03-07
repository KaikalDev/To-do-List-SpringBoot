package kaique.luan.dev.controller;

import jakarta.servlet.http.HttpServletRequest;
import kaique.luan.dev.Enuns.TaskLevel;
import kaique.luan.dev.Enuns.TaskStatus;
import kaique.luan.dev.domain.Task;
import kaique.luan.dev.domain.User;
import kaique.luan.dev.service.CookiesService;
import kaique.luan.dev.service.TaskService;
import kaique.luan.dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Controller
public class AddTaskController {

    private final TaskService taskservice;
    private final UserService userService;

    @Autowired
    private AddTaskController(TaskService taskservice, UserService userService) {
        this.taskservice = taskservice;
        this.userService = userService;
    }

    @GetMapping("/addTask")
    public String addTask(Model model) {
        Collection<TaskLevel> leves = TaskLevel.getValues();
        model.addAttribute("levels", leves);

        model.addAttribute("NORMAL", TaskLevel.NORMAL);

        return "AddTask";
    }

    @PostMapping("/addTask/")
    public String postAddTask(
            Model model,
            HttpServletRequest request,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("options") Optional<String> options

    ) {
        User user = userService.consultar(CookiesService.getCookie(request));
        if (user == null) {
            return "redirect:/login";
        }

        if (options.isEmpty() || TaskLevel.getByName(options.get()) == null) {
            Collection<TaskLevel> leves = TaskLevel.getValues();
            model.addAttribute("levels", leves);
            model.addAttribute("NORMAL", TaskLevel.NORMAL);
            model.addAttribute("error", "Is required.");
            return "AddTask";
        }

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(TaskStatus.PENDENTE);
        task.setLevel(TaskLevel.getByName(options.get()));
        task.setUser(user);

        taskservice.cadastrar(task);

        return "redirect:/listTask?filter=&title=";
    }

}
