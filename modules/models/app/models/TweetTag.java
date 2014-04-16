package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"tag"})
})
public class TweetTag extends AbstractModel {

    @NotNull(message="field 'tag' cannot be NULL")
    public String tag;

    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.MERGE})
    public Collection<Tweet> tweets = new HashSet<>();

}
