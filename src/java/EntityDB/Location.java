/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityDB;
import java.util.*;
import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.Session;
import org.hibernate.*;
import org.hibernate.cfg.*;

/**
 *
 * @author Rick Shaub
 */
@Entity
@Table(name="location"
    ,catalog="entitydb")
public class Location extends EntityBase
{
    @Column(name="LocationName", length=50)
    private String locationName;
    @Column(name = "LocationStreetAddress", length = 50)
    private String locationStreetAddress;
    @Column(name = "LocationZipCode", length = 10)
    private String locationZipCode;


    public String getLocationName()
    {
        return this.locationName;
    }

    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }

    public String getLocationStreetAddress()
    {
        return this.locationStreetAddress;
    }

    public void setLocationStreetAddress(String locationStreetAddress)
    {
        this.locationStreetAddress = locationStreetAddress;
    }

    public String getLocationZipCode()
    {
        return this.locationZipCode;
    }

    public void setLocationZipCode(String locationZipCode)
    {
        this.locationZipCode = locationZipCode;
    }


}
