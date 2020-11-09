package com.project.management.domainDAO;


import com.project.management.database.HibernateDataBaseConnector;
import com.project.management.domain.Company;
import com.project.management.domain.Customer;
import com.project.management.domain.Developer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;


public class DeveloperDAO extends DataCRUD<Developer> {

    private static final Logger LOGGER = LogManager.getLogger(DeveloperDAO.class);
    private SessionFactory sessionFactory;
    public DeveloperDAO() {
        sessionFactory = HibernateDataBaseConnector.getSessionFactory();
    }

    @Override
    public void create(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(developer);
        transaction.commit();
        session.close();
        LOGGER.debug("developer  " + developer.getName() + "  was created");
        System.out.println("developer  " + developer.toString() + "  was created");
    }

    public List<Developer> read() {
        Session session = sessionFactory.openSession();
        LOGGER.debug(" Print list of developers  ");
        return ( session.createQuery(" FROM Developer ").getResultList());
    }

    @Override
    public void update(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(developer);
        transaction.commit();
        session.close();
        LOGGER.debug("Developer  " +developer.getName() + "  was updated");
        System.out.println("Customer  " + developer.toString() + "  was updated");
    }

    @Override
    public void delete(Developer developer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(developer);
        transaction.commit();
        session.close();
        LOGGER.debug("Developer  " +developer.getName() + "  was deleted");
        System.out.println("developer  " + developer.toString() + "  was deleted");
    }

    public Developer findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" FROM Developer as d WHERE d.name =  :name ");
        Developer result;
        try {
            result = (Developer) query.setParameter("name", name).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            throw new NullPointerException("Developer with that name noy found in database");
        }
        session.close();
        LOGGER.debug("Developer  " + result.getName() + "  was found");
        return result;
    }
}

