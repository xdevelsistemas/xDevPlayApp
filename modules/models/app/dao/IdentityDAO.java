package dao;

import java.util.Iterator;
import java.util.ListIterator;
import scala.Option;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import models.Identity;
import models.User;



public class IdentityDAO extends AbstractDAO<models.Identity> {

    public IdentityDAO() {
        super(models.Identity.class);
    }

    public UserIdentity create(securesocial.core.Identity i, boolean save) {
        final UserDAO userDAO = new UserDAO();
        final User u = userDAO.create(i, save);
        return create(u, i, save);
    }

    //TODO corrigir as persistencias utilizando classe anonima JPA.withTransaction
    public securesocial.core.Identity modify (securesocial.core.Identity i,boolean save) {

        Identity r     = findOne("email",i.email().get());

        if (i.passwordInfo().isDefined() && r.hasPasswordInfo) {

            r.hasPasswordInfo = i.passwordInfo().isDefined();
            r.passwordInfoHasher = i.passwordInfo().isDefined() ? i.passwordInfo().get().hasher() : null;
            r.passwordInfoPassword = i.passwordInfo().isDefined() ? i.passwordInfo().get().password() : null;
            r.passwordInfoSalt = i.passwordInfo().isDefined() ? toStr(i.passwordInfo().get().salt()) : null;
        }

        //if (save) save(r);
        em.persist(r);


        return i;
    }

    public UserIdentity create(User u, securesocial.core.Identity i, boolean save) {

        final models.Identity r = new models.Identity();
        r.user = u;

        r.username     = i.identityId().userId();
        r.provider     = i.identityId().providerId();

        r.firstName    = i.firstName();
        r.lastName     = i.lastName();
        r.fullName     = i.fullName();

        r.email        = i.email().get();

        r.hasAvatarUrl = (i.avatarUrl().isDefined());
        r.avatarUrl    = (i.avatarUrl().isDefined()) ? i.avatarUrl().get() : null;

        r.authMethod   = i.authMethod().method();

        r.hasOAuth1Info          = i.oAuth1Info().isDefined();
        r.oAuth1InfoToken        = i.oAuth1Info().isDefined() ? i.oAuth1Info().get().token()  : null;
        r.oAuth1InfoSecret       = i.oAuth1Info().isDefined() ? i.oAuth1Info().get().secret() : null;

        r.hasOAuth2Info          = i.oAuth2Info().isDefined();
        r.oAuth2InfoAccessToken  = i.oAuth2Info().isDefined() ? i.oAuth2Info().get().accessToken()         : null;
        r.oAuth2InfoTokenType    = i.oAuth2Info().isDefined() ? toStr(i.oAuth2Info().get().tokenType())    : null;
        r.oAuth2InfoExpiresIn    = i.oAuth2Info().isDefined() ? toInt(i.oAuth2Info().get().expiresIn())    : null;
        r.oAuth2InfoRefreshToken = i.oAuth2Info().isDefined() ? toStr(i.oAuth2Info().get().refreshToken()) : null;

        r.hasPasswordInfo        = i.passwordInfo().isDefined();
        r.passwordInfoHasher     = i.passwordInfo().isDefined() ? i.passwordInfo().get().hasher()      : null;
        r.passwordInfoPassword   = i.passwordInfo().isDefined() ? i.passwordInfo().get().password()    : null;
        r.passwordInfoSalt       = i.passwordInfo().isDefined() ? toStr(i.passwordInfo().get().salt()) : null;

        if (save) save(r);

        return new UserIdentity(r);
    }

    public UserIdentity findOneByIdentityId(final securesocial.core.IdentityId identityId) {
        CriteriaQuery<models.Identity> cq = cb.createQuery(models.Identity.class);
        Root<models.Identity> root = cq.from(models.Identity.class);
        final Predicate[] predicates = new Predicate[] {
                cb.equal(root.get("username"), identityId.userId()),
                cb.equal(root.get("provider"), identityId.providerId()) };
        cq.where(predicates);
        try {
            Identity result = em.createQuery(cq).getSingleResult();
            return wrap(result);
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

    public UserIdentity findOneByEmailAndProvider(final String email, final String provider) {
        CriteriaQuery<models.Identity> cq = cb.createQuery(models.Identity.class);
        Root<models.Identity> root = cq.from(models.Identity.class);
        final Predicate[] predicates = new Predicate[] {
                cb.equal(root.get("email"), email),
                cb.equal(root.get("provider"), provider) };
        cq.where(predicates);
        try {
            Identity result = em.createQuery(cq).getSingleResult();
            return wrap(result);
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }




    public Iterable<UserIdentity> findManyByEmail(final String email) {
        CriteriaQuery<models.Identity> cq = cb.createQuery(models.Identity.class);
        Root<models.Identity> root = cq.from(models.Identity.class);
        final Predicate[] predicates = new Predicate[] {
                cb.equal(root.get("email"), email) };
        cq.where(predicates);
        final ListIterator<models.Identity> it = em.createQuery(cq).getResultList().listIterator();

        //JDK8:: return em.createQuery(cq).getResultList().stream().map(p -> wrap(p)).collect(Collectors.toList());
        return new Iterable<UserIdentity>() {
            @Override
            public Iterator<UserIdentity> iterator() {
                return new InnerIterator(it);
            }

            class InnerIterator implements Iterator<UserIdentity> {
                private final ListIterator<models.Identity> it;

                private InnerIterator(ListIterator<models.Identity> it) {
                    this.it = it;
                }

                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public UserIdentity next() {
                    return wrap(it.next());
                }

                @Override
                public void remove() {

                }
            }
        };
    }

    public UserIdentity wrap(final models.Identity identity) {
        return (identity == null) ? null : new UserIdentity(identity);
    }

    private String toStr(Option <String> option) {
        return option.isDefined() ? option.get() : null;
    }

    private Integer toInt(Option <Object> option) {
        return option.isDefined() ? (Integer) option.get() : null;
    }

}