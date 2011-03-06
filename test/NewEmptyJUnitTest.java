/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import EntityDB.*;
import static org.junit.Assert.*;

/**
 *
 * @author Rick Shaub
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    //@Test
    public void testPerson()
    {
        Person p = new Person();
        p.setEntityAccessStatus("1");
        p.setEntityId(EntityBase.generateNewID());
        p.setTypeId(0);
        p.setEmail("someone@somewhere.wut");
        p.setFirstName("Ricky");
        p.setLastName("Sprungfield");
        p.setPhone("800-587-6309");
        p.save();

        
    }

    @Test
    public void testUser()
            throws java.security.NoSuchAlgorithmException,
            java.io.UnsupportedEncodingException,
            java.sql.SQLException
    {
        
        //Create unique random usernames and passwords:
        String email1 = EntityBase.generateNewID();
        String pwd1 = EntityBase.generateNewID();

        String email2 = EntityBase.generateNewID();
        String pwd2 = EntityBase.generateNewID();

        String email3 = EntityBase.generateNewID();
        String pwd3 = EntityBase.generateNewID();

        //Create the first User
        User u = new User();
        u.setEntityAccessStatus("1");
        u.setEntityId(EntityBase.generateNewID());
        u.setTypeId(0);        
        u.setEmail(email1);
        u.setFirstName("Ricky");
        u.setLastName("Sprungfield");
        u.setPhone("800-587-6309");
        u.setPassword(pwd1);
        
        //Create the second User
        User u0 = new User();
        u0.setEntityAccessStatus("1");
        u0.setEntityId(EntityBase.generateNewID());
        u0.setTypeId(0);        
        u0.setEmail(email3);
        u0.setFirstName("Joe");
        u0.setLastName("Namath");
        u0.setPhone("800-667-1235");
        u0.setPassword(pwd3);


        //Create the third user
        User u1 = new User();
        u1.setEntityAccessStatus("1");
        u1.setEntityId(EntityBase.generateNewID());
        u1.setTypeId(0);
        u1.setEmail(email2);
        u1.setFirstName("Johnny");
        u1.setLastName("knoxville");
        u1.setPhone("800-251-1112");
        u1.setPassword(pwd2);
        u1.setOwner(u1);
        //Save the third user
        u1.save();

        //Test ownership property
        u.setOwner(u1);
        u.save();

        u0.setOwner(u1);
        u0.save();
        
        User u2 = User.getUserByPassword(email1,pwd1);

        //Assert we can lookup first user
        assert(u2 != null);
        User u3 = u2.getOwner();

        String s=u3.getLastName();
        //Assert this is the owner we set
        assert(u3.equals(u1));

        //Delete the second user to verify the third user is not deleted.
        u0.delete(true);
       
        //Lookup third user and assert user is not null
        User u4 = User.getUserByPassword(email2,pwd2);
        assert(u4 != null);
        u4.delete(true);

        //Verify deletion of the owner cascades
        User u5 = User.getUserByPassword(email1,pwd1);
        assert(u5==null);
        


    }

    @Test
    public void TestDate()
    {
        Event e = new Event();
        e.setEntityId(EntityBase.generateNewID());
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 4, 5, 11, 0);

        Date date1 = new Date(cal.getTimeInMillis());

        cal.set(2011, 4, 5, 17, 0);

        Date date2 = new Date(cal.getTimeInMillis());


        e.setStartDate(date1);
        e.setEndDate(date2);
        e.save();
    }
}