/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityDB;
import java.util.*;
import javax.persistence.*;
import java.io.Serializable;




/**
 *
 * @author Rick Shaub
 */
@Entity
@Table(name="people"
    ,catalog="entitydb")
public class Person extends EntityBase
{
    @Column(name = "PeopleFirstName", length = 50)
    private String peopleFirstName;
    @Column(name = "PeopleLastName", length = 50)
    private String peopleLastName;
    @Column(name = "PeopleEmail", length = 100)
    private String peopleEmail;
    @Column(name="PeoplePhone", length=20)
    private String peoplePhone;
    //private String userID = "0";
    public Person(){}
    
    public String getFirstName() {
        return this.peopleFirstName;
    }
    
    public void setFirstName(String peopleFirstName) {
        this.peopleFirstName = peopleFirstName;
    }
    
    public String getLastName() {
        return this.peopleLastName;
    }
    
    public void setLastName(String peopleLastName) {
        this.peopleLastName = peopleLastName;
    }
    
    public String getEmail() {
        return this.peopleEmail;
    }
    
    public void setEmail(String peopleEmail) {
        this.peopleEmail = peopleEmail;
    }
    
    
    public String getPhone() {
        return this.peoplePhone;
    }
    
    public void setPhone(String peoplePhone) {
        this.peoplePhone = peoplePhone;
    }

   

    


}
