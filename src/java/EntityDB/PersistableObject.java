/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityDB;
import java.util.*;
import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.*;
/**
 *
 * @author Rick Shaub
 */
public abstract class PersistableObject implements Serializable
{

    public static void close() {
        SessionFactory sessionFactory = SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.close();
    }
    abstract protected Serializable getID();

    public static void deleteMany(EntityBase[] objects) {
        SessionFactory sessionFactory = SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        for (int i = 0; i < objects.length; i++) {
            session.delete(objects[i]);
        }
        session.flush();
        tx.commit();
        //session.close();
    }

    public static void saveMany(EntityBase[] objects) {
        SessionFactory sessionFactory = SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        for (int i = 0; i < objects.length; i++) {
            session.save(objects[i]);
        }
        session.flush();
        tx.commit();
        //session.close();
    }

    public void delete(boolean load) {
        SessionFactory sessionFactory = SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Object deleted = this;
        if (load) {
            deleted = session.get(this.getClass(), getID(), LockMode.UPGRADE);
        }
        session.delete(deleted);
        session.flush();
        tx.commit();
        //session.close();
    }

    public void save() {
        save(false);
    }

    public void save(boolean load) {
        SessionFactory sessionFactory = SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Object saved = this;
        if (load) {
            saved = session.get(this.getClass(), getID(), LockMode.UPGRADE);
        }
        session.saveOrUpdate(saved);
        session.flush();
        tx.commit();
        //session.close();
    }

}
