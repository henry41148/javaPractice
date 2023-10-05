package org.example.dao;


import org.example.domain.Manufacturer;
import org.example.repository.Repository;
import org.example.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.*;
public class ManufacturerDAO implements Repository<Manufacturer>{
    @Override
    public Manufacturer get(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            Manufacturer manufacturer = session.find(Manufacturer.class, id);
            session.getTransaction().commit();
            return manufacturer;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Manufacturer> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            List<Manufacturer> manufacturerList = session.createQuery("FROM Manufacturer", Manufacturer.class).list();
            session.getTransaction().commit();
            return manufacturerList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Manufacturer item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Manufacturer item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            Manufacturer manufacturer = session.find(Manufacturer.class, id);
            session.delete(manufacturer);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean add(Manufacturer item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.getTransaction().commit();
            session.save(item);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public boolean isMoreThan100() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacturer> cr1 = cb.createQuery(Manufacturer.class);
            Root<Manufacturer> root1 = cr1.from(Manufacturer.class);
            cr1.select(root1).where(cb.gt(root1.get("employeeNumber"), 100));
            Query <Manufacturer> query1 = session.createQuery(cr1);
            List<Manufacturer> manufacturerList = query1.getResultList();
            CriteriaQuery<Long> cr2 = cb.createQuery(Long.class);
            Root<Manufacturer> root2 = cr2.from(Manufacturer.class);
            cr2.select(cb.count(root2.get("id")));
            Query<Long> query2 = session.createQuery(cr2);
            Long manufactureNumber = query2.getSingleResult();
            session.getTransaction().commit();
            return manufacturerList.size() == manufactureNumber;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public Integer getTotalEmployees() {
        List<Manufacturer> a = getAll();
        int sum = 0;
        for (Manufacturer each:a){
            sum+=each.getEmployee();
        }
        return sum;
    }

    public List<Manufacturer> getManuByLocation(String location) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacturer> cr = cb.createQuery(Manufacturer.class);
            Root<Manufacturer> root = cr.from(Manufacturer.class);
            cr.select(root).where(cb.equal(root.get("location"), location));
            Query<Manufacturer> query = session.createQuery(cr);
            List<Manufacturer> manufacturerList = query.getResultList();
            session.getTransaction().commit();
            return manufacturerList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }


}