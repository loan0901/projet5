package com.cleanup.todoc.repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.DAO.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private final TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) { this.taskDao = taskDao; }

    public LiveData<List<Task>> getAllTasks()
    { return this.taskDao.getAllTask(); }

    public void createTask(Task task)
    { taskDao.insertTask(task); }

    public void deleteTask(Task task)
    { taskDao.deleteTask(task); }
}
