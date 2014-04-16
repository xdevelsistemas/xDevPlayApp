package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import models.Tweet;
import models.TweetTag;
import twitter4j.Status;

public class TweetTagDAO extends AbstractDAO<models.TweetTag, models.TweetTag_> {

    public TweetTagDAO() {
        super(models.TweetTag.class, models.TweetTag_.class);
    }

    public TweetTag create(String tag, Collection<Status> tweets, boolean save) {
        TweetTag o = findOne("tag", tag);
        if (o == null) {
            o = new TweetTag();
            o.tag = tag;
        }
        final TweetDAO tweetDAO = new TweetDAO();
        o.tweets.addAll(tweetDAO.create(tweets, save));
        if (save) save(o);
        return o;
    }

    public List<Tweet> orderByDate(TweetTag o) {
        final TreeSet<Tweet> set = new TreeSet<Tweet>(new Tweet.ChronologicalOrder());
        for (final Tweet t : o.tweets) {
            set.add(t);
        }

        //FIXME: THIS SHOULD NOT BE NECESSARY
        // ----------------------------------------------------------------
        // Test cases show entries ordered chronologically as expected
        //     (honouring the TreeSet with the defined Comparator)
        // but in the browser it does not display ordered as expected :(
        // ----------------------------------------------------------------
        // --> For the time being, I'm creating a List from the TreeSet.
        //     I've tested with the web interface and it displays properly now.
        // ----------------------------------------------------------------

        final ArrayList<Tweet> list = new ArrayList<Tweet>();
        for (Tweet t : set) {
            list.add(t);
        }
        return list;
    }

    public int countRetweets(TweetTag o) {
        int retweets = 0;
        for (final Tweet t : o.tweets) {
            retweets += t.retweets;
        }
        return retweets;
    }

}
