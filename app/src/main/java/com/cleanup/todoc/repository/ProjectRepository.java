package com.cleanup.todoc.repository;

import com.cleanup.todoc.DAO.ProjectDao;
import com.cleanup.todoc.model.Project;

public class ProjectRepository {

    private final ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao) { this.projectDao = projectDao; }

    public void createProject(Project project)
    { projectDao.insertProject(project); }

    public void deleteProject(Project project)
    { projectDao.deleteProject(project); }
}
