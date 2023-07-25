package com.cleanup.todoc.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    //get all Tasks
    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAllTask();

    //add task
    @Insert
    void insertTask(Task task);

    //Delete task
    @Delete
    void deleteTask(Task task);
}
