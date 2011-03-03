/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityDB;
import java.util.*;
import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.*;
import org.hibernate.cfg.*;
/**
 *
 * @author Rick Shaub
 */
public abstract class PersistenceObject implements Serializable
{

    public void Save()
    {

        SessionFactory sessionFactory =SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session =sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(this);
        session.flush();
        tx.commit();
        session.close();


    }

    public void Delete()
    {
        SessionFactory sessionFactory =SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session =sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(this);
        session.flush();
        tx.commit();
        session.close();
    }

    /**
     *
     * @param objects
     */
    public static void DeleteMany(PersistenceObject[] objects)
    {
        SessionFactory sessionFactory =SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session =sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for(int i = 0; i< objects.length;i++)
        {
            session.delete(objects[i]);
        }

        session.flush();
        tx.commit();
        session.close();

    }

    public static void SaveMany(PersistenceObject[] objects)
    {
        SessionFactory sessionFactory =SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session =sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for(int i = 0; i< objects.length;i++)
        {
            session.save(objects[i]);
        }

        session.flush();
        tx.commit();
        session.close();

    }

//    protected PersistanceObject UniqueSelectQuery(String Query)
//    {
//
//
//
//
//    }


}
