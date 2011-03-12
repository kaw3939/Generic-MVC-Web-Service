/*
 * Author Stuart Fleming
 * usage:  runs sql of values passed to see if record exists.
 * returns  boolean Continue if no records are found
 */
package hibernatetest.test;
import common.NewHibernateUtil;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.HashMap;

public class utilValidateData {
public static Boolean StringValue (String Table,HashMap WhereMapList) {
// Boolean b = ValidateData.StringValue ("Users" , hmap ) ;
// returns true if record NOT exists (you can continue, false if record exist and you should not continue
   Boolean Continue =  false  ;
   String hql = util.buildHQL (Table , WhereMapList );
 
    try{
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        System.out.println ( "select * " + hql + ";" ) ;
        Query query = session.createQuery(hql);
        List users = query.list();
        if (users.isEmpty()) {
            Continue =  true  ;
        } else {
            Continue =  false  ;
        }
        session.close();
    }catch(Exception e) {
        e.printStackTrace();
    }

   return  Continue  ;
}
    public static void main(String[] args){
        HashMap  hMap = new HashMap();
	//hMap.put("UserID","123456");
        hMap.put("UserEmail","math@gmail.com");
 	//hMap.put("UserPasswordHash","7890");
        
        Boolean b = StringValue("User", hMap) ;
        System.out.println("b " + b);
    }
}
