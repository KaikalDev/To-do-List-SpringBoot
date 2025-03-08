package kaique.luan.dev.controller;

import jakarta.servlet.http.HttpServletRequest;
import kaique.luan.dev.Enuns.TaskLevel;
import kaique.luan.dev.domain.Task;
import kaique.luan.dev.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Controller
public class EditTaskController {

    private final TaskService service;

    @Autowired
    private EditTaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/editTask")
    public String editTask(
            Model model,
            @RequestParam("id") String taskId) {
        Task task = service.consultar(Long.parseLong(taskId));
        Collection<TaskLevel> leves = TaskLevel.getValues();
        model.addAttribute("levels", leves);
        model.addAttribute("task", task);
        return "EditTask";
    }

    @PostMapping("/editTask/")
    public String postEditTask(
            Model model,
            HttpServletRequest request,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("options") Optional<String> options,
            @RequestParam("id") String taskId

    ) {
        Task task = service.consultar(Long.parseLong(taskId));

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Collection<TaskLevel> leves = TaskLevel.getValues();
            model.addAttribute("levels", leves);
            model.addAttribute("task", task);
            model.addAttribute("errorEmpty", "Title and description are required");
            return "EditTask";
        }

        if (options.isEmpty() || TaskLevel.getByName(options.get()) == null) {
            Collection<TaskLevel> leves = TaskLevel.getValues();
            model.addAttribute("levels", leves);
            model.addAttribute("task", task);
            model.addAttribute("errorOption", "Is required.");
            return "EditTask";
        }

        task.setTitle(title);
        task.setDescription(description);
        task.setLevel(TaskLevel.getByName(options.get()));
        service.alterar(task);

        return "redirect:/listTask?filter=&title=";
    }

    @GetMapping("/toggleStatus")
    public String toggleStatus(@RequestParam("id") String taskId) {
        Task task = service.consultar(Long.parseLong(taskId));
        service.toggleCompleted(task);
        return "redirect:/listTask?filter=&title=";
    }

    @GetMapping("/deleteTask")
    public String deleteTask(@RequestParam("id") String taskId) {
        Task task = service.consultar(Long.parseLong(taskId));
        service.excluir(task);
        return "redirect:/listTask?filter=&title=";
    }

}
