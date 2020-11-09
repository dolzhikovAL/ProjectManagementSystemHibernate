package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Customer;
import com.project.management.domain.Developer;
import com.project.management.domainDAO.DeveloperDAO;

import java.util.List;

import static com.project.management.services.InputValidator.inputInteger;
import static com.project.management.services.InputValidator.validateString;

public class DeveloperService {

    private final View view;
    private  DeveloperDAO developerDAO;

    public DeveloperService(View view) {
        developerDAO = new DeveloperDAO();
        this.view = view;
    }


    public void createDeveloper() {
       Developer developer = enterPositionDeveloper();
        developerDAO.create(developer);
    }
    public void readDeveloper()  {
        List<Developer> list= developerDAO.read();
        System.out.println(list.toString());
    }

    public Developer enterPositionDeveloper() {
        view.write("Enter Developer name");
        String name = validateString(view);
        view.write("Enter  Developer salary only numbers available");
        int salary = inputInteger(view);
        view.write("Enter  Developer sex -- male or female");
        String sex = validateString(view);
        view.write("Enter Developer age only numbers available");
        int age = inputInteger(view);
        return (new Developer(name, salary,sex,age));
    }
    public void deleteDeveloper() {
        view.write("Enter Developer  name");
        String name = validateString(view);
        Developer developer = developerDAO.findByName(name);
        if (developer != null) {
            developerDAO.delete(developer);
        } else {
            view.write("Developer with " + name + " not found");
        }
    }

    public void updateDeveloper() {
       Developer developer = enterPositionDeveloper();
        developer = developerDAO.findByName(developer.getName());
        if (developer != null) {
            developerDAO.update(developer);
        } else {
            view.write("Developer with not found");
        }
    }
}
