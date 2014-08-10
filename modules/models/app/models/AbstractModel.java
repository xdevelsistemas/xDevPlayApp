package models;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
public class AbstractModel implements Serializable {

    @Id
    @UuidGenerator(name="UUID")
    @GeneratedValue(generator="UUID")
    @NotNull(message="Campo 'uuid' não pode ser nulo")
    //see: https://github.com/ancoron/pg-inet-maven/wiki/Support-custom-data-types-in-EclipseLink
    public String uuid;

    @Version
    @NotNull(message="Campo 'version' não pode ser nulo")
    public int version;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message="Campo 'created' não pode ser nulo")
    public Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message="Campo 'updated' não pode ser nulo")
    public Date updated;

    @PrePersist
    public void prePersist() {
        this.created = this.updated = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updated = new Date();
    }


    @Override
    public boolean equals(Object obj) {
        if  (obj == null){
            return false;
        }else{
            return (this.uuid.equals(((AbstractModel)obj).uuid))?true:false;
        }

    }




}





