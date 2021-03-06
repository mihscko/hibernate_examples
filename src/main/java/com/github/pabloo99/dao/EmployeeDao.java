package com.github.pabloo99.dao;

import com.github.pabloo99.connection.HibernateUtil;
import com.github.pabloo99.entity.Employee;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private final Logger logger = Logger.getLogger(EmployeeDao.class);

    public List<Employee> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;
        List<Employee> employees = new ArrayList<>();

        try {
            transaction = session.beginTransaction();

            employees =
                    session.createQuery("FROM Employee").
                            getResultList();

            transaction.commit();

            return employees;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }

        return employees;
    }

    public Employee findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, id);
            transaction.commit();

            return employee;
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }

        return null;
    }

    public void delete(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Employee employee = (Employee) session.get(Employee.class, id);
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public void update(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    public void saveEmployee(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null)
                transaction.rollback();

            logger.error(e.getMessage(), e);
        } finally {
            session.close();
        }
    }
}
