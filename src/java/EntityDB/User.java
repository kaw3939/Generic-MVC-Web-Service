/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityDB;

/**
 *
 * @author Rick Shaub
 */

import java.util.*;
import java.security.*;
import javax.persistence.*;
import org.hibernate.*;





/**
 *
 * @author Rick Shaub
 */
@Entity
@Table(name="users",catalog="entitydb")
public class User extends Person
{
     
    @Column(name = "UserEmail", length = 100)
    private String userEmail;
    @Column(name = "UserSalt", length = 20)
    private String userSalt;
    @Column(name = "UserPasswordHash", length = 64)
    private String userPasswordHash;
    @Column(name="UserOauthToken", length=20)
    private String userOauthToken;
    private int saltMin = 3;
    private int saltMax = 15;

    @OneToMany
    Set<EntityBase> children;


    public User()
    {

    }
    @Override
    public String getEmail()
    {
        return this.userEmail;
    }

    @Override
    public void setEmail(String userEmail)
    {
        this.userEmail = userEmail;
        super.setEmail(userEmail);

    }    
    
    protected String getOauthToken() {
        return this.userOauthToken;
    }

    public void setOauthToken(String userOauthToken) {
        this.userOauthToken = userOauthToken;
    }

    public static User GetUserByPassword(String userName,String password)
            throws java.security.NoSuchAlgorithmException,
            java.io.UnsupportedEncodingException
    {

        User[] userRecord = selectByUsername(userName);
        if(userRecord.length < 1)
        {
            return null;
        }
        String hash = AeSimpleMD5.MD5(password + userRecord[0].userSalt);
        if (hash.equalsIgnoreCase(userRecord[0].userPasswordHash))
        {
            return userRecord[0];
        }

        return null;

    }

    public static User GetUserByOauthToken(String userName,String token)
            throws java.security.NoSuchAlgorithmException,
            java.io.UnsupportedEncodingException
    {

        User[] userRecord = selectByUsername(userName);
        if(userRecord.length < 1)
        {
            return null;
        }
        if (token.equalsIgnoreCase(userRecord[0].userPasswordHash))
        {
            return userRecord[0];
        }

        return null;

    }
    

    public void setPassword(String password)
            throws NoSuchAlgorithmException,
            java.io.UnsupportedEncodingException
    {

        Random rand = new Random();
        int length = rand.nextInt(saltMax - saltMin) + saltMin;

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<length;i++)
        {
           sb.append((char)(rand.nextInt(94) + 32));
        }
        this.userSalt = sb.toString();
        this.userPasswordHash =  AeSimpleMD5.MD5(password + userSalt);


    }

    protected static User[] selectByUsername(String name)
    {
        SessionFactory sessionFactory =SessionFactoryUtil.getInstance();
        // new AnnotationConfiguration().configure().buildSessionFactory();
        Session session =sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List l = session.createQuery("from User u where u.userEmail=:userName")
                .setString("userName", name).list();

        User[] user = new User[l.size()];

        for(int i = 0;i<l.size();i++)
        {
            user[i] = (User)l.get(i);
        }

        tx.commit();
        return user;


    }



}
