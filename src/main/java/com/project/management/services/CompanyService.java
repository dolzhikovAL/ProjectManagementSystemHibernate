package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Company;
import com.project.management.domainDAO.CompanyDAO;
import com.project.management.domainDAO.DataCRUD;

import java.sql.SQLException;
import java.util.List;


import static com.project.management.services.InputValidator.validateString;

public class CompanyService {

    private final View view;
    private CompanyDAO companyDAO;

    public CompanyService(View view) {
        companyDAO = new CompanyDAO();
        this.view = view;
    }

    public void createCompany() {
        Company company = enterPositionCompany();
        companyDAO.create(company);
    }

    public void readCompany() {
        List<Company> list= companyDAO.read();
        System.out.println(list.toString());
    }

    public Company enterPositionCompany() {
        view.write("Enter Company name");
        String name = validateString(view);
        view.write("Enter Company country from");
        String country = validateString(view);
        return (new Company(name, country));
    }

    public void updateCompany() {
        Company company = enterPositionCompany();
        company = companyDAO.findByName(company.getName());
        if (company == null) {
            view.write("Company with   not found");
        } else {
            companyDAO.update(company);
        }
    }

        public void deleteCompany () {
            view.write("Enter Company name");
            String name = validateString(view);
            Company company = companyDAO.findByName(name);
            if (company == null) {
                view.write("Company with " + name + " not found");
            } else {
                companyDAO.delete(company);
            }
        }
    }
