package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"email"})
})
public class User extends AbstractModel {

    @NotNull(message="field 'email' cannot be NULL")
    public String email;

    @NotNull(message="field 'realName' cannot be NULL")
    public String realName;

}
