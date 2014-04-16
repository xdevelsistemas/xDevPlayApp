package models;

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
    @NotNull(message="field 'uuid' cannot be NULL")
    //see: https://github.com/ancoron/pg-inet-maven/wiki/Support-custom-data-types-in-EclipseLink
    public String uuid;

    @Version
    @NotNull(message="field 'version' cannot be NULL")
    public int version;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message="field 'created' cannot be NULL")
    public Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message="field 'updated' cannot be NULL")
    public Date updated;

    @PrePersist
    void prePersist() {
        this.created = this.updated = new Date();
    }

    @PreUpdate
    void preUpdate() {
        this.updated = new Date();
    }

}





