package com.project.management.domainDAO;

import com.project.management.database.HibernateDataBaseConnector;
import com.project.management.domain.Company;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class CompanyDAO extends DataCRUD<Company> {

    private SessionFactory sessionFactory;

    public CompanyDAO() {

        sessionFactory = HibernateDataBaseConnector.getSessionFactory();
    }

    private static final Logger LOGGER = LogManager.getLogger(Company.class);


    @Override
    public void create(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(company);
        transaction.commit();
        session.close();
        LOGGER.debug("Company  " + company.getName() + "  was created");
        System.out.println("Company  " + company.toString() + "  was created");
    }


    public List<Company> read() {
        Session session = sessionFactory.openSession();
        LOGGER.debug(" Print list of companies  ");
        return (session.createQuery(" FROM Company ").getResultList());
    }

    @Override
    public void update(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(company);
        transaction.commit();
        session.close();
        LOGGER.debug("Company  " + company.getName() + "  was updated");
        System.out.println("Company  " + company.toString() + "  was updated");
    }

    @Override
    public void delete(Company company) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(company);
        transaction.commit();
        session.close();
        LOGGER.debug("Company  " + company.getName() + "  was deleted");
        System.out.println("Company  " + company.toString() + "  was deleted");
    }


    public Company findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" FROM Company as c WHERE c.name =  :name ");
        Company result = null;
        try {
            result = (Company) query.setParameter("name", name).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            throw new NullPointerException("Company with that name noy found in database");
        }
        session.close();
        return result;
    }
}
