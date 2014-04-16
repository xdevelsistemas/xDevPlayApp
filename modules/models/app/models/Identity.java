package models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"username", "provider"}),
        @UniqueConstraint(columnNames={"email"})
})
public class Identity extends AbstractModel {

    @ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
    @NotNull(message="field 'user' cannot be NULL")
    public User user;

    @NotNull(message="field 'username' cannot be NULL")
    public String  username;

    @NotNull(message="field 'provider' cannot be NULL")
    public String  provider;

    @NotNull(message="field 'firstName' cannot be NULL")
    public String  firstName;

    @NotNull(message="field 'lastName' cannot be NULL")
    public String  lastName;

    @NotNull(message="field 'fullName' cannot be NULL")
    public String  fullName;

    @NotNull(message="field 'email' cannot be NULL")
    public String  email;

    @NotNull(message="field 'hasAvatarUrl' cannot be NULL")
    public boolean hasAvatarUrl;
    public String  avatarUrl;

    @NotNull(message="field 'authMethod' cannot be NULL")
    public String  authMethod;

    @NotNull(message="field 'hasOAuth1Info' cannot be NULL")
    public boolean hasOAuth1Info;
    public String  oAuth1InfoToken;
    public String  oAuth1InfoSecret;

    @NotNull(message="field 'hasOAuth2Info' cannot be NULL")
    public boolean hasOAuth2Info;
    public String  oAuth2InfoAccessToken;
    public String  oAuth2InfoTokenType;
    public Integer oAuth2InfoExpiresIn;
    public String  oAuth2InfoRefreshToken;

    @NotNull(message="field 'hasPasswordInfo' cannot be NULL")
    public boolean hasPasswordInfo;
    public String  passwordInfoHasher;
    public String  passwordInfoPassword;
    public String  passwordInfoSalt;

    @Override
    public String toString() {
        return "Identity{" +
                "user=" + user +
                ", username='" + username + '\'' +
                ", provider='" + provider + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", hasAvatarUrl=" + hasAvatarUrl +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", authMethod='" + authMethod + '\'' +
                ", hasOAuth1Info=" + hasOAuth1Info +
                ", oAuth1InfoToken='" + oAuth1InfoToken + '\'' +
                ", oAuth1InfoSecret='" + oAuth1InfoSecret + '\'' +
                ", hasOAuth2Info=" + hasOAuth2Info +
                ", oAuth2InfoAccessToken='" + oAuth2InfoAccessToken + '\'' +
                ", oAuth2InfoTokenType='" + oAuth2InfoTokenType + '\'' +
                ", oAuth2InfoExpiresIn=" + oAuth2InfoExpiresIn +
                ", oAuth2InfoRefreshToken='" + oAuth2InfoRefreshToken + '\'' +
                ", hasPasswordInfo=" + hasPasswordInfo +
                ", passwordInfoHasher='" + passwordInfoHasher + '\'' +
                ", passwordInfoPassword='" + passwordInfoPassword + '\'' +
                ", passwordInfoSalt='" + passwordInfoSalt + '\'' +
                '}';
    }
}
