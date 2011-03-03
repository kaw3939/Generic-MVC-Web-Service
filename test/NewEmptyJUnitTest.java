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
        p.setEntityId(EntityBase.GenerateNewID());
        p.setTypeId(0);
        p.setEmail("someone@somewhere.wut");
        p.setFirstName("Ricky");
        p.setLastName("Sprungfield");
        p.setPhone("800-587-6309");
        p.Save();

        
    }

    @Test
    public void testUser()
            throws java.security.NoSuchAlgorithmException,
            java.io.UnsupportedEncodingException,
            java.sql.SQLException
    {
        

        for(int i =0;i<2;i++)
        {
        User u = new User();
        u.setEntityAccessStatus("1");
        u.setEntityId(EntityBase.GenerateNewID());
        u.setTypeId(0);        
        u.setEmail("someone@somewhere.biz");
        u.setFirstName("Ricky");
        u.setLastName("Sprungfield");
        u.setPhone("800-587-6309");
        u.setPassword("deadbeef");

        User u1 = new User();
        u1.setEntityAccessStatus("1");
        u1.setEntityId(EntityBase.GenerateNewID());
        u1.setTypeId(0);
        u1.setEmail("jknox@somewhere.ru");
        u1.setFirstName("Johnny");
        u1.setLastName("knoxville");
        u1.setPhone("800-251-1112");
        u1.setPassword("dogmeat");
        u1.Save();
        u.setOwner(u1);
        u.Save();
        User u2 = User.GetUserByPassword("someone@somewhere.biz","deadbeef");
        assert(u2 != null);
        User u3 = u2.getOwner();
        u.Delete();
        }


    }

    @Test
    public void TestDate()
    {
        Event e = new Event();
        e.setEntityId(EntityBase.GenerateNewID());
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 4, 5, 11, 0);

        Date date1 = new Date(cal.getTimeInMillis());

        cal.set(2011, 4, 5, 17, 0);

        Date date2 = new Date(cal.getTimeInMillis());


        e.setStartDate(date1);
        e.setEndDate(date2);
        e.Save();
    }
}