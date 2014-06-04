package dao;

import scala.None;
import scala.Option;
import scala.Option$;
import securesocial.core.AuthenticationMethod;
import securesocial.core.IdentityId;
import securesocial.core.OAuth1Info;
import securesocial.core.OAuth2Info;
import securesocial.core.PasswordInfo;


/**
 * This trait provides access to the User associated to a certain Identity
 */
public class UserIdentity implements securesocial.core.Identity {

    private final models.Identity identity;
    private final models.User     user;


    public UserIdentity(models.Identity identity) {
        this.identity = identity;
        this.user = identity.user;
    }

    public models.User user() {
        return this.user;
    }

    //
    // implements securesocial.core.Identity
    //

    @Override
    public IdentityId identityId() {
        return new IdentityId(identity.username, identity.provider);
    }

    @Override
    public String firstName() {
        return identity.firstName;
    }

    @Override
    public String lastName() {
        return identity.lastName;
    }

    @Override
    public String fullName() {
        return identity.fullName;
    }

    @Override
    public scala.Option<String> email() {
        return scala.Option$.MODULE$.apply(identity.email);
    }

    @Override
    public scala.Option<String> avatarUrl() {
        return (!identity.hasAvatarUrl) ? null: scala.Option$.MODULE$.apply(identity.avatarUrl);
    }

    @Override
    public AuthenticationMethod authMethod() {
        return new AuthenticationMethod(identity.authMethod);
    }

    @Override
    public Option<OAuth1Info> oAuth1Info() {
        return (identity.hasOAuth1Info)
                ? scala.Option$.MODULE$.apply(
                        new OAuth1Info(
                                identity.oAuth1InfoToken,
                                identity.oAuth1InfoSecret))
                : null;
    }

    @Override
    public Option<OAuth2Info> oAuth2Info() {
        return (identity.hasOAuth1Info)
                ? scala.Option$.MODULE$.apply(
                        new OAuth2Info(
                                identity.oAuth2InfoAccessToken,
                                scala.Option$.MODULE$.apply(identity.oAuth2InfoTokenType),
                                scala.Option$.MODULE$.apply((Object)identity.oAuth2InfoExpiresIn),
                                scala.Option$.MODULE$.apply(identity.oAuth2InfoRefreshToken)
                        )
                )
                : null;
    }

    @Override
    public Option<PasswordInfo> passwordInfo() {
        return (identity.hasPasswordInfo)
                ? scala.Option$.MODULE$.apply(
                        new PasswordInfo(
                                identity.passwordInfoHasher,
                                identity.passwordInfoPassword,
                                scala.Option$.MODULE$.apply(identity.passwordInfoSalt)))
                : null;
    }

}