package hibernatetest.test;
import common.NewHibernateUtil;
import resource.*;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*
 * Author Stuart Fleming
 */
public class UserInsert {
    String  EntityID, userPWHash, userEmail , myReturn;

    public static String addNewUser (String userID,String userPWHash,String userEmail){
        try{
            SessionFactory sf = NewHibernateUtil.getSessionFactory();
            Session session = sf.openSession();
            Transaction tx =  session.beginTransaction();

             Users u = new Users();
           //  u.setEntityId(EntityID);
             u.setUserPasswordHash(userPWHash);
             u.setUserEmail(userEmail);

             session.save(u);
            tx.commit();

            session.close();
       }catch(Exception e) {
       }
    return "success";
    }

    public static void main(String[] args){
    String s = addNewUser("xx","xx","xx") ;
 
    }

}
