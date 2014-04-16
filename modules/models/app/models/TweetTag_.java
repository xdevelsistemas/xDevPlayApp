package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Tweet;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-04-16T03:14:26")
@StaticMetamodel(TweetTag.class)
public class TweetTag_ extends AbstractModel_ {

    public static volatile SingularAttribute<TweetTag, String> tag;
    public static volatile CollectionAttribute<TweetTag, Tweet> tweets;

}