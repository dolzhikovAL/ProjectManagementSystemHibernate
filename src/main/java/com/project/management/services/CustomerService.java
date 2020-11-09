package com.project.management.services;

import com.project.management.console.View;
import com.project.management.domain.Company;
import com.project.management.domain.Customer;
import com.project.management.domainDAO.CompanyDAO;
import com.project.management.domainDAO.CustomerDAO;
import com.project.management.domainDAO.DataCRUD;

import java.sql.SQLException;
import java.util.List;

import static com.project.management.services.InputValidator.validateString;

public class CustomerService {

    private final View view;
    private CustomerDAO customerDAO;

    public CustomerService(View view) {
       customerDAO = new CustomerDAO()  ;
        this.view = view;
    }

    public void createCustomer() {
        Customer customer = enterPositionCustomer();
        customerDAO.create(customer);
    }

    public void readCustomer() {
        List<Customer> list= customerDAO.read();
        System.out.println(list.toString());
    }

    public Customer enterPositionCustomer() {
        view.write("Enter Customer name");
        String name = validateString(view);
        view.write("Enter Company email");
        String email = validateString(view);
        return (new Customer(name, email));
    }

    public void updateCustomer() {
       Customer customer = enterPositionCustomer();
       customer = customerDAO.findByName(customer.getName());
        if (customer != null) {
            customerDAO.update(customer);
        } else {
            view.write("Company with  not found");
        }
    }

    public void deleteCustomer () {
        view.write("EnterCustomer name");
        String name = validateString(view);
        Customer customer = customerDAO.findByName(name);
        if (customer != null) {
            customerDAO.delete(customer);
        } else {
            view.write("Customer with " + name + " not found");
        }
    }
}


