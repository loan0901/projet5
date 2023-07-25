package com.cleanup.todoc.Factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.ViewModel.TaskViewModel;
import com.cleanup.todoc.dataBase.MyDataBase;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final TaskRepository taskDataRepository;
    private final ProjectRepository projectDataRepository;
    private final Executor executor;

    private static ViewModelFactory factory;

    //get instance of ViewModel
    public static ViewModelFactory getInstance(Context context) {

                if (factory == null) {
                    factory = new ViewModelFactory(context);
                }
        return factory;
    }

    private ViewModelFactory(Context context) {

        MyDataBase database = MyDataBase.getInstance(context);

        this.taskDataRepository = new TaskRepository(database.taskDao());
        this.projectDataRepository = new ProjectRepository(database.projectDao());
        this.executor = Executors.newSingleThreadExecutor();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)){
            return (T) new TaskViewModel(taskDataRepository, projectDataRepository, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
