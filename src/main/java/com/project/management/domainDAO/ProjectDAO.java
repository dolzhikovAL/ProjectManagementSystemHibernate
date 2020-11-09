package com.project.management.domainDAO;

import com.project.management.database.HibernateDataBaseConnector;
import com.project.management.domain.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProjectDAO extends DataCRUD<Project> {

    private static final Logger LOGGER = LogManager.getLogger(ProjectDAO.class);
    private SessionFactory sessionFactory;

    public ProjectDAO() {
        sessionFactory = HibernateDataBaseConnector.getSessionFactory();
    }


    @Override
    public void create(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(project);
        transaction.commit();
        session.close();
        LOGGER.debug("project  " + project.getName() + "  was created");
        System.out.println("project " + project.toString() + "  was created");
    }

    public List<Project> read() {
        Session session = sessionFactory.openSession();
        LOGGER.debug(" Print list of projects  ");
        return (session.createQuery(" FROM Project  ").getResultList());
    }

    @Override
    public void update(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(project);
        transaction.commit();
        session.close();
        LOGGER.debug("project  " + project.getName() + "  was updated");
        System.out.println("project " + project.toString() + "  was updated");
    }

    @Override
    public void delete(Project project) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(project);
        transaction.commit();
        session.close();
        LOGGER.debug("project  " + project.getName() + "  was deleted");
        System.out.println("project " + project.toString() + "  was deleted");
    }

    public Project findByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" FROM Project as p WHERE p.name =  :name ");
        Project result;
        try {
            result = (Project) query.setParameter("name", name).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            throw new NullPointerException("Company with that name noy found in database");
        }
        session.close();
        LOGGER.debug("Company  " + result.getName() + "  was found");
        return result;
    }
}


