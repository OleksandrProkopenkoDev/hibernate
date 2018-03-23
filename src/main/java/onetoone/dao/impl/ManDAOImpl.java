package onetoone.dao.impl;

import onetoone.dao.ManDAO;
import onetoone.entity.Man;
import onetoone.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ManDAOImpl implements ManDAO {

    private SessionFactory factory;

    public ManDAOImpl(){
        factory = HibernateUtil.getFactory();
    }

    public Long create(Man man) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Long id = (Long) session.save(man);
            session.getTransaction().commit();
            return id;

        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

        return null;
    }

    public Man read(Long id) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            Man man = session.get(Man.class, id);
            return man;
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

        return null;
    }

    public boolean update(Man man) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(man);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }


        return false;
    }

    public boolean delete(Man man) {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(man);
            session.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }


        return false;
    }

    public List<Man> getAll() {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            List<Man> men = session.createCriteria(Man.class).list();
            return men;
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

        return null;
    }
}
