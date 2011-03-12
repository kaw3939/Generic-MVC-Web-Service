package  hibernatetest.test;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/*
 * Author Stuart Fleming
 * usage:  builds a hibernate sql statement based on Hashmap containing new values is sent
 * ex:  updates email where userID = 123
 */
public class util {
    public static String buildUpdate (String Table, HashMap FieldsNewValueHsh) {
       int i = 0;
       String hql = "update " + Table  ;
       if( FieldsNewValueHsh.size() > 0) hql = hql + " set " ;
       Set entries = FieldsNewValueHsh.entrySet();
       Iterator it = entries.iterator();
       while (it.hasNext()) {
          Map.Entry entry = (Map.Entry) it.next();
          //System.out.println(entry.getKey() + " " + entry.getValue());
            if( i!=0)  hql = hql + " , "  ;
             hql = hql + " " + entry.getKey()+ "=" +  "'" + entry.getValue() +  "'";
             i++;
        }
        return hql ;
    }
    public static String buildWhere (HashMap mapList) {
       int i = 0;
       String hql = ""  ;
       if( mapList.size() > 0) hql = hql + " where " ;
       Set entries = mapList.entrySet();
       Iterator it = entries.iterator();
       while (it.hasNext()) {
          Map.Entry entry = (Map.Entry) it.next();
          //System.out.println(entry.getKey() + " " + entry.getValue());
            if( i!=0)  hql = hql + " and "  ;
             hql = hql + " " + entry.getKey()+ "=" +  "'" + entry.getValue() +  "'";
             i++;
        }
       return hql;
    }
    public static String buildHQL (String Table, HashMap mapList) {
       int i = 0;
       String hql = "from " + Table + buildWhere(mapList)  ;
       return hql;
    }
}
