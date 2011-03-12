/*
 * Author Stuart Fleming
 * usage:  starts creation of new user. Validates data if userID or email already exist
 */
package hibernatetest.test;
import java.util.HashMap;

public class UserStart {
 
    public static  String startNewUser (String userID,String userPWHash,String userEmail){
        String myReturn = null;
        Boolean Continue =false ;
        HashMap  hMap = new HashMap();
	hMap.put("UserID",userID);
        Continue = utilValidateData.StringValue ("User" , hMap ) ;

        if ( Continue == false ) {
                myReturn ="UserID already Exists";
        } else {
            hMap = new HashMap();
            hMap.put("userEmail",userEmail);
            Continue = utilValidateData.StringValue ("User" , hMap ) ;
            if ( Continue == false ) {
                    myReturn ="Email already Exists";
            }
        }

        if (Continue == true ){
             myReturn = UserInsert.addNewUser (userID,userPWHash,userEmail) ;
         }
 
        return myReturn;
    }
    public static void main(String[] args) {
            String userID = "adfadsf" ;
            String userPWHash ="adfads"  ;
            String userEmail = "adfmade@gmail.com" ;
            String b = startNewUser (userID,userPWHash,userEmail) ;
            System.out.println(b);
    }
}
