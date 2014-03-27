package models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"email"})
})
public class User extends AbstractModel {

    public String email;
    public String realName;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
