package org.example.dao;


import org.example.domain.Phone;
import org.example.repository.Repository;
import org.example.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.*;

public class PhoneDAO implements Repository<Phone> {
    @Override
    public Phone get(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            Phone phone = session.find(Phone.class, id);
            session.getTransaction().commit();
            return phone;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    @Override
    public boolean add(Phone item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            session.close();

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
            Phone phone = session.find(Phone.class, id);
            session.delete(phone);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Phone item) {
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
    public boolean update(Phone item) {
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
    public List<Phone> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            List<Phone> phoneList = session.createQuery("FROM Phone", Phone.class).list();
            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public ArrayList<Phone>getHighestPricePhone(){
        List<Phone> lst = getAll();
        int max = 0;
        ArrayList<Phone> maxList = new ArrayList<Phone>();
        for(Phone a: lst){
            if (a.getPrice()>max){
                max = a.getPrice();
            }
        }
        for(Phone a:lst){
            if(a.getPrice() == max){
                maxList.add(a);
            }
        }
        return maxList;
    }
    public List<Phone> sortByCountry() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            CriteriaBuilder cb = (CriteriaBuilder) session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.orderBy(cb.asc(root.get("country")), cb.desc(root.get("price")));
            Query<Phone> query = session.createQuery(cr);
            List<Phone> phoneList = query.getResultList();
            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public List<Phone> getPhonesHigher(int price) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
            Root<Phone> root = cr.from(Phone.class);
            cr.select(root).where(cb.gt(root.get("price"), price));
            Query<Phone> query = session.createQuery(cr);
            List<Phone> phoneList = query.getResultList();
            session.getTransaction().commit();
            return phoneList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
}
