package com.example.taskmanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.model.Task;

//public class TaskService {
//
//}
@Service
public class TaskService {
	private final TaskRepository repo;

	public TaskService(TaskRepository repo) {
		this.repo = repo;
	}

	public List<Task> getAllTasks() {
		return repo.findAll();
	}

	public List<Task> getTasksByCompleted(boolean completed) {
		return repo.findByCompleted(completed);
	}

	public Task saveTask(Task task) {
		return repo.save(task);
	}

	public Task getTask(Long id) {
		return repo.findById(id).orElse(null);
	}

	public void deleteTask(Long id) {
		repo.deleteById(id);
	}
}