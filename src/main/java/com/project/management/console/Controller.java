package com.project.management.console;

import com.project.management.database.HibernateDataBaseConnector;
import com.project.management.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;


import static com.project.management.services.InputValidator.*;

public class Controller {

    private final View view;
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    public Controller(View view) {
        this.view = view;
        LOGGER.trace("Start application");
    }

    public void askMainOption() {
        view.write("Please tape one of the next command \n" +
                "for create function type (create) \n" +
                "for update function type (update) \n" +
                "for delete function type (delete) \n" +
                "for read function type (read) \n" +
                "or exit for exit");
        choseOfMainFunction(view.read());
    }

  /*  public void choseOfMainFunction(String input) {
        switch (input) {
            case "CRUD": {
                tableCrudAsk(view);
                choseCrudOption(validateString(view));
                askMainOption();
                break;
            }

            case "exit": {
                view.write("Goodbye!");
                System.exit(0);
                break;
            }
            default: {
                view.write("Command  was incorrect \n" +
                        "try one more time to chose MAIN command");
                askMainOption();
            }
        }
    }*/

    public void choseOfMainFunction(String input) {
        switch (input) {
            case "create": {
                InputValidator.tableChoseAsk(view);
                optionCreateObjectMenu(validateString(view));
                askMainOption();
                break;
            }
            case "update": {
                InputValidator.tableChoseAsk(view);
                optionUpdateObjectMenu(validateString(view));
                askMainOption();
                break;
            }
            case "read": {
                tableChoseAsk(view);
                optionReadObjectMenu(validateString(view));
                askMainOption();
                break;
            }
            case "delete": {
                tableChoseAsk(view);
                optionDeleteObjectMenu(validateString(view));
                askMainOption();
                break;
            }
            case "exit": {
                view.write("Goodbye!!!");
                HibernateDataBaseConnector.endWork();
                System.exit(0);
                break;
            }
            default: {
                view.write("Command was incorrect \n" +
                        "try one more time to chose CRUD command");
                InputValidator.tableCrudAsk(view);
                choseOfMainFunction(view.read());
                break;
            }
        }
    }



    public void optionCreateObjectMenu(String input) {
        switch (input) {
            case "company": {
                CompanyService companyService = new CompanyService(view);
                companyService.createCompany();
                askMainOption();
                break;
            }
            case "customer": {
                CustomerService customerService = new CustomerService(view);
                customerService.createCustomer();
                askMainOption();
                break;
            }
            case "developer": {
                DeveloperService developerService = new DeveloperService(view);
                developerService.createDeveloper();
                askMainOption();
                break;
            }
            case "project": {
                ProjectService projectService = new ProjectService(view);
                projectService.createProject();
                askMainOption();
                break;
            }
            case "exit": {
                view.write("Goodbye!!!!!!!!!!!");
                break;
            }
            default: {
                view.write("Wrong object-----");
                InputValidator.tableChoseAsk(view);
                optionCreateObjectMenu(validateString(view));
                break;
            }
        }
    }

    public void optionDeleteObjectMenu(String input) {
        switch (input) {
            case "company": {
                CompanyService companyService = new CompanyService(view);
                companyService.deleteCompany();
                askMainOption();
                break;
            }
            case "customer": {
                CustomerService customerService = new CustomerService(view);
                customerService.deleteCustomer();
                askMainOption();
                break;
            }
            case "developer": {
                DeveloperService developerService = new DeveloperService(view);
                developerService.deleteDeveloper();
                askMainOption();
                break;
            }
            case "project": {
                ProjectService projectService = new ProjectService(view);
                projectService.deleteProject();
                askMainOption();
                break;
            }
            case "exit": {
                view.write("Goodbye!!!!!!!!!!!!!!!!!!!!!!!");
                break;
            }
            default: {
                view.write("Wrong object-----");
                InputValidator.tableChoseAsk(view);
                optionCreateObjectMenu(validateString(view));
                break;
            }
        }
    }

    public void optionUpdateObjectMenu(String input) {
        switch (input) {
            case "company": {
                CompanyService companyService = new CompanyService(view);
                companyService.updateCompany();
                askMainOption();
                break;
            }
            case "customer": {
                CustomerService customerService = new CustomerService(view);
                customerService.updateCustomer();
                askMainOption();
                break;
            }
            case "developer": {
                DeveloperService developerService = new DeveloperService(view);
                developerService.updateDeveloper();
                askMainOption();
                break;
            }
            case "project": {
                ProjectService projectService = new ProjectService(view);
                projectService.updateProject();
                askMainOption();
                break;
            }
            case "exit": {
                view.write("Goodbye!!!!!!-!");
                break;
            }
            default: {
                view.write("Wrong object-----");
                InputValidator.tableChoseAsk(view);
                optionCreateObjectMenu(validateString(view));
                break;
            }
        }
    }

    public void optionReadObjectMenu(String input) {
        switch (input) {
            case "customer": {
                CustomerService customerService = new CustomerService(view);
                    customerService.readCustomer();
                askMainOption();
                break;
            }
            case "company": {
                CompanyService companyService = new CompanyService(view);
               companyService.readCompany();
                askMainOption();
                break;

            }
            case "project": {
                ProjectService projectService = new ProjectService(view);
                    projectService.readProject();
                askMainOption();
                break;
            }
            case "developer": {
                DeveloperService developerService = new DeveloperService(view);
                    developerService.readDeveloper();
                askMainOption();
                break;
            }
            case "exit": {
                view.write("Goodbye!!!!!!-!");
                break;
            }
            default: {
                view.write("Command was incorrect \n" +
                        "try one more time");
                tableChoseAsk(view);
                optionReadObjectMenu(validateString(view));
            }
        }
    }
}
