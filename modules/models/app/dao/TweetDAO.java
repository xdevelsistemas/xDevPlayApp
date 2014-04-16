package dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import models.Tweet;
import twitter4j.Status;

public class TweetDAO extends AbstractDAO<models.Tweet, models.Tweet_> {

    public TweetDAO() {
        super(models.Tweet.class, models.Tweet_.class);
    }

    public Collection<Tweet> create(Collection<Status> tweets, boolean save) {
        final ArrayList<Tweet> result = new ArrayList<Tweet>();
        for (Status t : tweets) {
            Tweet o = findOne("id", t.getId());
            if (o == null) {
                o = create(t, save);
                result.add(o);
            }
        }
        return result;
    }

    public Tweet create(Status tweet, boolean save) {
        final Tweet o = new Tweet();
        o.id = tweet.getId();
        o.date = tweet.getCreatedAt();
        o.retweets = tweet.getRetweetCount();
        o.json = tweet.getText();
        if (save) save(o);
        return o;
    }

}
