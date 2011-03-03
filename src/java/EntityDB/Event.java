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
@Table(name="event"
    ,catalog="entitydb"
)
public class Event extends EntityBase
{
 
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "EventStartDate", nullable = false, length = 15)
    private Date eventStartDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EventEndDate", length=15)
    private Date eventEndDate;
    public Event()
    {

    }

    public Event(Date startDate, Date endDate)
    {
        eventStartDate = startDate;
        eventEndDate = endDate;
    }


    public Date getStartDate() {
        return this.eventStartDate;
    }

    public void setStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }
    
    public Date getEndDate() {
        return this.eventEndDate;
    }

    public void setEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }


}
