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

    @OneToMany(mappedBy="owner")
    private Set<EntityBase> children;


    @SuppressWarnings("LeakingThisInConstructor")
    public User()
    {
        setOwner(this);
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

    public static User getUserByPassword(String userName,String password)
            throws java.security.NoSuchAlgorithmException,
            java.io.UnsupportedEncodingException
    {

        User userRecord = selectByUsername(userName);
        if(userRecord == null)
        {
            return null;
        }
        String hash = AeSimpleMD5.MD5(password + userRecord.userSalt);
        if (hash.equalsIgnoreCase(userRecord.userPasswordHash))
        {
            return userRecord;
        }

        return null;

    }

    public static User getUserByOauthToken(String userName,String token)
            throws java.security.NoSuchAlgorithmException,
            java.io.UnsupportedEncodingException
    {

        User userRecord = selectByUsername(userName);
        if(userRecord == null)
        {
            return null;
        }
        if (token.equalsIgnoreCase(userRecord.userPasswordHash))
        {
            return userRecord;
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

    /***
     * Selects a user by username (email)
     * @param name User's email address
     * @return The user with the matching username or null if no user exists.
     */
    protected static User selectByUsername(String name)
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
        if(user.length==0)
        {
            return null;
        }
        return user[0];


    }

    /***
     * Deletes the user and all entities owned by the user.
     */
    @Override
    public void delete(boolean load)
    {
        
        
        Set<EntityBase> childrenSet = getChildren();
        super.delete(load);
        if(childrenSet !=null)
        {
            for(EntityBase entity:childrenSet)
            {
                if(!entity.equals(this))
                {
                    EntityBase e = selectByID(entity.getEntityId());
                    if(e!=null)
                    {
                        e.delete(load);
                    }
                }
            }
        }
        

        
    }

    /**
     * This method returns all of the entities owned by this user.
     * note you cannot add children through the User object.  instead you must
     * call addUser() through the entity.
     * @return a Set of the entities owned by this user.
     */
    public Set<EntityBase> getChildren()
    {
        return children;
    }



}
