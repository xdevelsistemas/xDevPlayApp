package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.User;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-04-16T03:14:26")
@StaticMetamodel(Identity.class)
public class Identity_ extends AbstractModel_ {

    public static volatile SingularAttribute<Identity, String> lastName;
    public static volatile SingularAttribute<Identity, String> avatarUrl;
    public static volatile SingularAttribute<Identity, String> fullName;
    public static volatile SingularAttribute<Identity, Boolean> hasAvatarUrl;
    public static volatile SingularAttribute<Identity, Boolean> hasOAuth2Info;
    public static volatile SingularAttribute<Identity, String> passwordInfoHasher;
    public static volatile SingularAttribute<Identity, String> passwordInfoSalt;
    public static volatile SingularAttribute<Identity, String> oAuth2InfoTokenType;
    public static volatile SingularAttribute<Identity, String> oAuth2InfoRefreshToken;
    public static volatile SingularAttribute<Identity, Integer> oAuth2InfoExpiresIn;
    public static volatile SingularAttribute<Identity, String> firstName;
    public static volatile SingularAttribute<Identity, String> oAuth2InfoAccessToken;
    public static volatile SingularAttribute<Identity, String> passwordInfoPassword;
    public static volatile SingularAttribute<Identity, String> provider;
    public static volatile SingularAttribute<Identity, Boolean> hasOAuth1Info;
    public static volatile SingularAttribute<Identity, String> oAuth1InfoToken;
    public static volatile SingularAttribute<Identity, Boolean> hasPasswordInfo;
    public static volatile SingularAttribute<Identity, String> authMethod;
    public static volatile SingularAttribute<Identity, User> user;
    public static volatile SingularAttribute<Identity, String> email;
    public static volatile SingularAttribute<Identity, String> username;
    public static volatile SingularAttribute<Identity, String> oAuth1InfoSecret;

}