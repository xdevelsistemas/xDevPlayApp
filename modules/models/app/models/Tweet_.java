package models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.TweetTag;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-04-16T03:14:26")
@StaticMetamodel(Tweet.class)
public class Tweet_ extends AbstractModel_ {

    public static volatile SingularAttribute<Tweet, Date> date;
    public static volatile SingularAttribute<Tweet, String> json;
    public static volatile SingularAttribute<Tweet, Long> id;
    public static volatile SingularAttribute<Tweet, Integer> retweets;
    public static volatile CollectionAttribute<Tweet, TweetTag> tags;

}