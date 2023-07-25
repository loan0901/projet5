package com.cleanup.todoc.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Dao
public interface ProjectDao {

    //add Project
    @Insert
    void insertProject(Project project);

    //Delete project
    @Delete
    void deleteProject(Project project);
}
