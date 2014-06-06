package controllers.plugin;

import play.api.data.Form;
import play.api.mvc.Request;
import play.api.mvc.RequestHeader;
import play.api.templates.Html;
import play.api.templates.Txt;
import scala.Option;
import scala.Tuple2;
import securesocial.controllers.PasswordChange;
import securesocial.controllers.Registration;
import securesocial.controllers.TemplatesPlugin;
import securesocial.core.Identity;
import securesocial.core.SecuredRequest;

/**
 * Created by claytonsantosdasilva on 05/06/14.
 */
public class MyViews implements TemplatesPlugin {
    @Override
    public void onStart() {

    }

    @Override
    public <A> Html getLoginPage(Request<A> request, Form<Tuple2<String, String>> form, Option<String> msg) {
        return null;
    }

    @Override
    public <A> Html getSignUpPage(Request<A> request, Form<Registration.RegistrationInfo> form, String token) {
        return null;
    }

    @Override
    public void onStop() {

    }

    @Override
    public boolean enabled() {
        return false;
    }

    @Override
    public <A> Html getStartSignUpPage(Request<A> request, Form<String> form) {
        return null;
    }

    @Override
    public <A> Html getResetPasswordPage(Request<A> request, Form<Tuple2<String, String>> form, String token) {
        return null;
    }

    @Override
    public <A> Html getStartResetPasswordPage(Request<A> request, Form<String> form) {
        return null;
    }

    @Override
    public <A> Html getPasswordChangePage(SecuredRequest<A> request, Form<PasswordChange.ChangeInfo> form) {
        return null;
    }

    @Override
    public <A> Html getNotAuthorizedPage(Request<A> request) {
        return null;
    }

    @Override
    public Tuple2<Option<Txt>, Option<Html>> getSignUpEmail(String token, RequestHeader request) {
        return null;
    }

    @Override
    public Tuple2<Option<Txt>, Option<Html>> getAlreadyRegisteredEmail(Identity user, RequestHeader request) {
        return null;
    }

    @Override
    public Tuple2<Option<Txt>, Option<Html>> getWelcomeEmail(Identity user, RequestHeader request) {
        return null;
    }

    @Override
    public Tuple2<Option<Txt>, Option<Html>> getUnknownEmailNotice(RequestHeader request) {
        return null;
    }

    @Override
    public Tuple2<Option<Txt>, Option<Html>> getSendPasswordResetEmail(Identity user, String token, RequestHeader request) {
        return null;
    }

    @Override
    public Tuple2<Option<Txt>, Option<Html>> getPasswordChangedNoticeEmail(Identity user, RequestHeader request) {
        return null;
    }
}


