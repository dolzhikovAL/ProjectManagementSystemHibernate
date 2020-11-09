package com.project.management.domainDAO;

import com.project.management.database.HibernateDataBaseConnector;
import com.project.management.domain.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDAO extends DataCRUD<Customer> {

    private SessionFactory sessionFactory;

    public CustomerDAO() {
        sessionFactory = HibernateDataBaseConnector.getSessionFactory();
    }

    private static final Logger LOGGER = LogManager.getLogger(CustomerDAO.class);


    @Override
    public void create(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        LOGGER.debug("Create customer:  " + customer.getName());
        session.save(customer);
        transaction.commit();
        session.close();
        LOGGER.debug("customer  " + customer.getName() + "  was created");
        System.out.println("customer  " + customer.toString() + "  was created");

    }


    public List<Customer> read() {
        Session session = sessionFactory.openSession();
        LOGGER.debug(" Print list of customers  ");
        return (session.createQuery(" FROM Customer ").getResultList());
    }

    @Override
    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(customer);
        transaction.commit();
        session.close();
        LOGGER.debug("Customer  " + customer.getName() + "  was updated");
        System.out.println("Customer  " + customer.toString() + "  was updated");
    }

    @Override
    public void delete(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(customer);
        transaction.commit();
        session.close();
        LOGGER.debug("Customer  " + customer.getName() + "  was deleted");
        System.out.println("Customer  " + customer.toString() + "  was deleted");
    }

    public Customer findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" FROM Customer as c WHERE c.name =  :name ");
        Customer result;
        try {
            result = (Customer) query.setParameter("name", name).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            throw new NullPointerException("Customer with that name noy found in database");
        }
        session.close();
        LOGGER.debug("Customer  " + result.getName() + "  was found");
        return result;
    }
}

