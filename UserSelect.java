package  hibernatetest.test;
import common.NewHibernateUtil;
import resource.Users;
import java.util.HashMap;
import  java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*
 * Author Stuart Fleming
 * usage:  returns List object of query passed to it.
 */
public class UserSelect {
public static List UsersSQL (HashMap mapList) {
    // returns one or more records based on a field and value
    // example where userID = '123456'
    List usersList = null;
        try {
            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            String hql = util.buildHQL ("Users" , mapList );
            System.out.println("hql: " + hql );

            Query query = session.createQuery(hql);
            usersList = query.list();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    return usersList;
}
      public static void main(String[] args){
        HashMap  hMap = new HashMap();
	//hMap.put("UserID","123456");
       // hMap.put("UserEmail","ssfleming@hotmail.com");
 	//hMap.put("UserPasswordHash","7890");
        List usersList = UsersSQL(hMap) ;
        Iterator it = usersList.iterator();


        while(it.hasNext()) {
            Users u = (Users) it.next();
            System.out.println( u.getUserEmail());
        }

    }
}
