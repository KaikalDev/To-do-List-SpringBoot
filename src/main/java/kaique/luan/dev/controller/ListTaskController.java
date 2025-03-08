package kaique.luan.dev.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class ListTaskController {

    private final TaskService taskservice;
    private final UserService userService;

    @Autowired
    private ListTaskController(TaskService taskservice, UserService userService) {
        this.taskservice = taskservice;
        this.userService = userService;
    }

    @GetMapping("/listTask")
    public String listTask(Model model,
                           HttpServletRequest request,
                           @RequestParam("filter") String filter,
                           @RequestParam("title") String title) {
        User user = userService.consultar(CookiesService.getCookie(request));
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("filterParam", filter);
        model.addAttribute("titleParam", title);
        model.addAttribute("CONCLUIDA", TaskStatus.CONCLUIDA);

        Collection<Task> tasks = taskservice.findByUser(user.getId());
        Collection<Task> tasksFiltered = tasks;

        model.addAttribute("tasksSize", tasks.size());

        if (filter != null && !filter.trim().isEmpty()) {
            TaskStatus status = TaskStatus.getByName(filter);
            TaskLevel level = TaskLevel.getByName(filter);
            if (status != null) {
                tasksFiltered = taskservice.filter(status, tasksFiltered);
            } else if (level != null) {
                tasksFiltered = taskservice.filter(level, tasksFiltered);
            }
        }

        if (title != null && !title.trim().isEmpty()) {
            String titleLowerCase = title.toLowerCase();
            tasksFiltered = taskservice.filter(titleLowerCase, tasksFiltered);
        }

        model.addAttribute("tasks", tasksFiltered);

        Map<String, Long> filterCounts = new HashMap<>();

        filterCounts.putAll(
                Arrays.stream(TaskStatus.values())
                        .collect(Collectors.toMap(
                                e -> e.toString().toLowerCase(),
                                e -> (long) taskservice.filter(e, tasks).size()
                        ))
        );

        filterCounts.putAll(
                Arrays.stream(TaskLevel.values())
                        .collect(Collectors.toMap(
                                e -> e.toString().toLowerCase(),
                                e -> (long) taskservice.filter(e, tasks).size()
                        ))
        );

        model.addAttribute("filterCounts", filterCounts);

        return "ListTask";
    }

    @PostMapping("/listTask/")
    public String listTaskPost(
            @RequestParam("filter") String filter,
            @RequestParam("title") String title
    ) {
        return "redirect:/listTask?filter=" + filter + "&title=" + title;
    }
}
