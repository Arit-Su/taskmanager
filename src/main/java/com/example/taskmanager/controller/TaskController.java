package com.example.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;

//import ch.qos.logback.core.model.Model;

//public class TaskController {
//
//}
@Controller
public class TaskController {
	private final TaskService service;

	public TaskController(TaskService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String index(@RequestParam(defaultValue = "all") String filter, Model model) {
		if (filter.equals("completed")) {
			model.addAttribute("tasks", service.getTasksByCompleted(true));
		} else if (filter.equals("pending")) {
			model.addAttribute("tasks", service.getTasksByCompleted(false));
		} else {
			model.addAttribute("tasks", service.getAllTasks());
		}
		model.addAttribute("filter", filter);
		model.addAttribute("task", new Task());
		return "index";
	}

	@PostMapping("/add")
	public String addTask(@ModelAttribute Task task) {
		service.saveTask(task);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String editTask(@PathVariable Long id, Model model) {
		model.addAttribute("task", service.getTask(id));
		return "edit";
	}

	@PostMapping("/update")
	public String updateTask(@ModelAttribute Task task) {
		service.saveTask(task);
		return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id) {
		service.deleteTask(id);
		return "redirect:/";
	}

	@PostMapping("/complete/{id}")
	public String completeTask(@PathVariable Long id) {
		Task task = service.getTask(id);
		if (task != null) {
			task.setCompleted(true);
			service.saveTask(task);
		}
		return "redirect:/";
	}
}