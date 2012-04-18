CREATE SEQUENCE seria_uid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 10000
  CACHE 1;
  
  CREATE TABLE users
(
  uid_ bigint NOT NULL DEFAULT nextval('seria_uid'::regclass),
  username_ character varying(1500) NOT NULL,
  password_ character varying(1500) DEFAULT 123456,
  salt_ character varying(1500),
  status_ smallint DEFAULT 0,
  email_ character varying(1500) NOT NULL,
  emailconfirm_ character varying(256),
  emailshare_ character varying(64),
  nickname_ character varying(1500),
  realname_ character varying(1500),
  realnameshare_ character varying(64),
  location_ character varying(1500),
  locationshare_ character varying(64),
  avatar_ character varying(1500),
  sex_ character varying(64),
  sexshare_ character varying(64),
  birthday_ timestamp with time zone,
  birthdayshare_ character varying(64),
  blog_ character varying(1500),
  blogshare_ character varying(64),
  qq_ character varying(64),
  qqshare_ character varying(64),
  weibo_ character varying(1500),
  weiboshare_ character varying(64),
  msn_ character varying(1500),
  msnshare_ character varying(64),
  mobile_ character varying(64),
  mobileshare_ character varying(64),
  introduction_ character varying(1500),
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT users_pkey PRIMARY KEY (uid_),
  CONSTRAINT uq_username UNIQUE (username_)
)
WITH (
  OIDS=FALSE
);

