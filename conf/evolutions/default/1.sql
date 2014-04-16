# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table identity (
  uuid                      varchar(40) not null,
  user_uuid                 varchar(40),
  username                  varchar(255),
  provider                  varchar(255),
  first_name                varchar(255),
  last_name                 varchar(255),
  full_name                 varchar(255),
  email                     varchar(255),
  has_avatar_url            boolean,
  avatar_url                varchar(255),
  auth_method               varchar(255),
  has_oauth1info            boolean,
  o_auth1info_token         varchar(255),
  o_auth1info_secret        varchar(255),
  has_oauth2info            boolean,
  o_auth2info_access_token  varchar(255),
  o_auth2info_token_type    varchar(255),
  o_auth2info_expires_in    integer,
  o_auth2info_refresh_token varchar(255),
  has_password_info         boolean,
  password_info_hasher      varchar(255),
  password_info_password    varchar(255),
  password_info_salt        varchar(255),
  created                   timestamp not null,
  updated                   timestamp not null,
  constraint uq_identity_1 unique (username,provider),
  constraint uq_identity_2 unique (email),
  constraint pk_identity primary key (uuid))
;

create table tweet (
  uuid                      varchar(40) not null,
  tweet_tag_uuid            varchar(40) not null,
  id                        bigint,
  date                      timestamp,
  retweets                  integer,
  json                      TEXT,
  created                   timestamp not null,
  updated                   timestamp not null,
  constraint uq_tweet_1 unique (date,id),
  constraint pk_tweet primary key (uuid))
;

create table tweet_tag (
  uuid                      varchar(40) not null,
  tag                       varchar(255),
  created                   timestamp not null,
  updated                   timestamp not null,
  constraint uq_tweet_tag_1 unique (tag),
  constraint pk_tweet_tag primary key (uuid))
;

create table user (
  uuid                      varchar(40) not null,
  email                     varchar(255),
  real_name                 varchar(255),
  created                   timestamp not null,
  updated                   timestamp not null,
  constraint uq_user_1 unique (email),
  constraint pk_user primary key (uuid))
;

alter table identity add constraint fk_identity_user_1 foreign key (user_uuid) references user (uuid) on delete restrict on update restrict;
create index ix_identity_user_1 on identity (user_uuid);
alter table tweet add constraint fk_tweet_tweet_tag_2 foreign key (tweet_tag_uuid) references tweet_tag (uuid) on delete restrict on update restrict;
create index ix_tweet_tweet_tag_2 on tweet (tweet_tag_uuid);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists identity;

drop table if exists tweet;

drop table if exists tweet_tag;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

