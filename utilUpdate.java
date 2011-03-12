package hibernatetest.test;
import java.util.HashMap;
import common.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 *
 * Author Stuart Fleming
 * updates values where to values passed based on where clause
 */
public class utilUpdate {
    public static String UpdateScript (String Table , HashMap WhereClause, HashMap FieldsNewValue){
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session sess = sf.openSession();
        Transaction tx =  sess.beginTransaction();

        String UpdateHql = util.buildUpdate(Table, FieldsNewValue);
        System.out.println("UpdateHql: " + UpdateHql);
        
        String WhereHql  = util.buildWhere(WhereClause);
        System.out.println("WhereHql: " + WhereHql);

        String hql = UpdateHql + WhereHql ;
        System.out.println("hql: " + hql);
        Query query = sess.createQuery(hql);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);

        tx.commit();
        sess.close();

        return rowCount + "Records updated";
    }
        public static void main(String[] args){
        HashMap  WhereMap = new HashMap();
	WhereMap.put("UserEmail","myemail@eat.com");
        HashMap  FieldAndUpdate = new HashMap();
	FieldAndUpdate.put("UserEmail","ssfleming@hotmail.com");
	FieldAndUpdate.put("UserSalt","salty");
	FieldAndUpdate.put("UserOauthToken","01234567") ;
        String s = UpdateScript("User",WhereMap,FieldAndUpdate);
    }


}
