package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Project;
import com.project.management.domainDAO.ProjectDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.project.management.services.InputValidator.validateString;


public class ProjectService {

    private final View view;
    private final ProjectDAO projectDAO;

    public ProjectService(View view) {
        this.view = view;
        projectDAO = new ProjectDAO();
    }

    public void createProject() {
        Project project = enterPositionProject();
        projectDAO.create(project);
    }

    public void readProject() {
        List<Project> list = projectDAO.read();
        System.out.println(list.toString());
    }

    public Project enterPositionProject() {
        view.write("Enter Project name");
        String name = view.read();
        view.write("Enter project deadLIne in format YYYY-MM-DD");
        LocalDate deadLine = InputValidator.validateSLocalDate(view);
        return (new Project(name, deadLine));
    }

    public void updateProject() {
        Project project = enterPositionProject();
        project = projectDAO.findByName(project.getName());
        if (project != null) {
            projectDAO.update(project);
        } else {
            view.write("Project with   not found");
        }
    }

    public void deleteProject() {
        view.write("Enter project  name");
        String name = validateString(view);
        Project project = projectDAO.findByName(name);
        if (project != null) {
            projectDAO.delete(project);
        } else {
            view.write("Developer with " + name + " not found");
        }
    }
}



