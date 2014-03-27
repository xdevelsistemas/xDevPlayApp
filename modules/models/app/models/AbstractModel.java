package models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import play.db.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;


@MappedSuperclass
public class AbstractModel extends Model {

    @Id
    @GeneratedValue
    public UUID uuid;

    @CreatedTimestamp
    public Timestamp created;

    @Version
    public Timestamp updated;

}
