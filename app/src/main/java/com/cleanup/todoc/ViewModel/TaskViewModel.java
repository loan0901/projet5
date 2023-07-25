package com.cleanup.todoc.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final Executor executor;


    public TaskViewModel(TaskRepository taskRepository, ProjectRepository projectRepository, Executor executor) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.executor = executor;
    }

    // FOR TASKS
    public LiveData<List<Task>> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public void createTask(Task task) {
        executor.execute(() -> {
            taskRepository.createTask(task);
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> taskRepository.deleteTask(task));
    }

    //FOR PROJECT
    public void createProject(long id, String name, int color){
        executor.execute(() -> projectRepository.createProject(new Project(id,name,color)));
    }

    public void deleteProject(Project project){
        executor.execute(() -> projectRepository.deleteProject(project));
    }
}
