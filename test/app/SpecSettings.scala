package app

/**
 * Created by rgomes on 10/04/14.
 */
trait SpecSettings {
  val withoutPlugins = Seq(
    "com.typesafe.plugin.CommonsMailerPlugin",
    "securesocial.core.DefaultAuthenticatorStore",
    "securesocial.core.DefaultIdGenerator",
    "securesocial.core.providers.utils.DefaultPasswordValidator",
    "securesocial.controllers.DefaultTemplatesPlugin",
    "services.AuthenticationService",
    "securesocial.core.providers.utils.BCryptPasswordHasher",
    "securesocial.core.providers.TwitterProvider",
    "securesocial.core.providers.FacebookProvider",
    "securesocial.core.providers.GoogleProvider",
    "securesocial.core.providers.LinkedInProvider",
    "securesocial.core.providers.UsernamePasswordProvider",
    "securesocial.core.providers.GitHubProvider",
    "services.AuthenticationListener",
    "securesocial.core.providers.FoursquareProvider",
    "securesocial.core.providers.XingProvider",
    "securesocial.core.providers.VkProvider",
    "securesocial.core.providers.InstagramProvider")
}
