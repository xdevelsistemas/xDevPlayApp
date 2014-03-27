package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames={"username", "provider"}),
        @UniqueConstraint(columnNames={"email"})
})
public class Identity extends AbstractModel {

    @ManyToOne public User user;

    public String  username;
    public String  provider;

    public String  firstName;
    public String  lastName;
    public String  fullName;

    public String  email;

    public boolean hasAvatarUrl;
    public String  avatarUrl;

    public String  authMethod;

    public boolean hasOAuth1Info;
    public String  oAuth1InfoToken;
    public String  oAuth1InfoSecret;

    public boolean hasOAuth2Info;
    public String  oAuth2InfoAccessToken;
    public String  oAuth2InfoTokenType;
    public int     oAuth2InfoExpiresIn;
    public String  oAuth2InfoRefreshToken;

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
