CREATE SEQUENCE seria_uiid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 10000
  CACHE 1;
CREATE TABLE userinfo
(
  uid_ bigint NOT NULL,
  username_ character varying(1500),
  nickname_ character varying(1500),
  userid_ bigint,
  tags_ character varying(1500),
  shared_ boolean,
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT pk_uiid PRIMARY KEY (uid_)
);