package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"date","id"})
})
public class Tweet extends AbstractModel {

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message="field 'date' cannot be NULL")
    public Date date;

    @NotNull(message="field 'id' cannot be NULL")
    public long id;

    @NotNull(message="field 'retweets' cannot be NULL")
    public int  retweets;

    @Column(columnDefinition="TEXT")
    @NotNull(message="field 'json' cannot be NULL")
    public String json;

    @ManyToMany(mappedBy="tweets")
    public Collection<TweetTag> tags = new HashSet<>();

    public static class ChronologicalOrder implements java.util.Comparator<Tweet> {
        @Override
        public int compare (Tweet o1, Tweet o2) {
            int compDate = o1.date.compareTo(o2.date);
            if (compDate != 0) return compDate;
            long compId = o1.id - o2.id;
            return (compId == 0L) ? 0 : (compId < 0L) ? -1 : 1;
        }
    }

}
