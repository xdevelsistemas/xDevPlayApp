| Code_ | Bugs_ | Forum_ | License_ | Contact_

.. _Code : http://github.com/frgomes/ssdemojpa
.. _Bugs : http://github.com/frgomes/ssdemojpa/issues
.. _Forum : http://github.com/frgomes/ssdemojpa/wiki
.. _License : http://opensource.org/licenses/Apache-2.0
.. _Contact : http://github.com/~frgomes
.. _`SecureSocial`: http://securesocial.ws


SecureSocial Demo with JPA (or ``ssdemojpa`` for short) provides a ready-to-use
base implementation which employs `SecureSocial`_ backed by an JPA layer for
*userpass* authentication.


For the impatient
=================

::

    $ git clone http://github.com/frgomes/ssdemojpa
    $ play run

Then point your browser to ``http://localhost:9000/``

::

    email:    admin@example.com
    password: 12345678

You can also try these pages:

* http://localhost:9000/credentials
* http://localhost:9000/identities
* http://localhost:9000/app


Features is a Nutshell
======================

* *userpass* (i.e: username/password) authentication is backed by JPA, which can be backed by any SQL database supported
  by Avaje Ebeans or another JPA implementation you choose.

* JPA is available only from Java sources, bacause *enhancement* is only available on Java sources. Due to this reason,
  the data model is implemented in Java whilst everything else is implemented in Scala.

* In order to fill in the gap between Java and Scala, there are DAOs responsible for talking to the data model. The DAO
  layer is implemented in Scala and it is responsible for converting data structures from Java to Scala.

* The service layer is responsible for calling methods provided by the DAO layer in order to persist information
  onto the database.

* The web layer is basically provided entirely by SecureSocial, plus one route provided as an example about how the code
  is organized. More about this below.


Organization of Source Code
===========================

Play! requires a project organization more or less like this:

::

    /
    +-- app
    +-- conf
    +-- project
        -- Build.scala
    +-- test

We have added modules to this structure. Below we show some of the most relevant classes you will find:

::

    /
    +-- app
        +-- controllers
            +-- Application.scala
            +-- Authentication.scala
    +-- conf
        +-- application.conf
        +-- messages
        +-- securesocial.conf
    +-- modules
        +-- controllers
            +-- app
                +-- Waitress.scala
            +-- test
        +-- models
            +-- app
                +-- models
                    +-- AbstractModel.scala
                    +-- Identity.scala
                    +-- User.scala
            +-- test
        +-- services
            +-- app
                +-- dao
                    +-- AbstractDAO.scala
                    +-- IdentityDAO.scala
                    +-- UserDAO.scala
                +-- services
                    +-- AuthenticationListener
                    +-- AuthenticationService
            +-- test
    +-- project
        -- Build.scala
    +-- test

The idea is:

1. app/controllers/Application.scala is intended to serve your home page and unprotected pages.

2. app/controllers/Authentication.scala provides pages related to SecureSocial.

3. modules/controllers/Waitress.scala is intended to serve your protected pages.

4. modules/models contains Entity classes used by JPA.

5. modules/services/app/dao contains DAOs which works in conjunction with the data model.

6. modules/services/app/services contains all services exposed by your application, including services related to
   authentication.


Configuration
=============

1. You are certainly interested on changing the title presented during the authentication. Just edit conf/messages.

2. The file conf/securesocial.conf allows you to configure keys for accessing external authentication providers.

3. If you have turned on *userpass*, you will need an SMTP server for development. Instead of installing a full SMTP
server, you can simply relay messages to your preferred SMTP server. A quick guide about this is available at
http://rgomes-info.blogspot.co.uk/2014/03/configuring-postfix-for-relaying-on.html

4. I you prefer EclipseLink or any other JPA implementation, please read this article:
http://pbaris.wordpress.com/2013/07/29/play-framework-2-jpa-eclipselink-setup/


Known issues
============

Authentication tokens should be shared in a cluster environment. The current implementation is not doing that yet, which
is equivalent to say that this code is not ready yet for a production server in cluster.


Why JPA and why SQL?
====================

I suppose that, if you are trying this software, you must already have at least 50% of the answer.

In case you are considering NoSQL and you are concerned about mixing SQL and NoSQL databases in your solution, I'd like
to share with you a couple of interesting articles:

https://interlinked.org/tutorials/postgresql.html

http://sourceforge.net/apps/mediawiki/postgres-xc


Support
=======

Please find links on the top of this page.
