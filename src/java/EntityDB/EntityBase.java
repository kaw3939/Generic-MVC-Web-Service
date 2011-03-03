/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EntityDB;

import java.util.*;
import javax.persistence.*;
import java.io.Serializable;
import org.hibernate.*;
import org.hibernate.cfg.*;
/**
 *
 * @author Rick Shaub
 */
@Entity
@Table(name="entitybase"
    ,catalog="entitydb")
public class EntityBase extends PersistenceObject implements Serializable
{

    @Id
    @Column(name="EntityID", unique=true, nullable=false, length=40)
     private String entityId;
    
    @Column(name = "EntityTypeID")
     private Integer entityTypeId;

    @Column(name = "EntityAccessStatus", length = 20)
     private String entityAccessStatus;

    @Column(name = "EntityParentEdit", length = 20)
     private String entityParentEdit;

//     @Column(name="OwnerID", length=40)
//     private String ownerID;



    

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OwnerID")//, insertable=false, updatable=false)
     private User owner;



     public EntityBase(){}
     public EntityBase(String entityId)
     {
        this.entityId = entityId;
     }
    public EntityBase(String entityId, Integer entityTypeId,
            String entityAccessStatus, String entityParentEdit)
    {
       this.entityId = entityId;
       this.entityTypeId = entityTypeId;
       this.entityAccessStatus = entityAccessStatus;
       this.entityParentEdit = entityParentEdit;
    }

    private void selectOwner()
    {

    }

    public static String GenerateNewID()
    {
        return UUID.randomUUID().toString();
    }

    
    public String getEntityId()
    {
        return this.entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Integer getTypeId() {
        return this.entityTypeId;
    }

    public void setTypeId(Integer entityTypeId) {
        this.entityTypeId = entityTypeId;
    }

    public String getAccessStatus() 
    {
        return this.entityAccessStatus;
    }

    public void setEntityAccessStatus(String entityAccessStatus)
    {
        this.entityAccessStatus = entityAccessStatus;
    }

    public String getParentEdit()
    {
        return this.entityParentEdit;
    }

    public void setParentEdit(String entityParentEdit) {
        this.entityParentEdit = entityParentEdit;
    }

    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
        //ownerID = owner.getEntityId();
    }

    

    
}


