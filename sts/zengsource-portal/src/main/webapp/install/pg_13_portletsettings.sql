CREATE SEQUENCE seria_psid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE portletsettings
(
  psid_ bigint NOT NULL DEFAULT nextval('seria_psid'::regclass),
  key_ character varying(128),
  name_ character varying(128),
  value_ character varying(1500),
  usage_ character varying(1500),
  portletid_ bigint,
  instanceid_ bigint,
  updatedtime_ timestamp with time zone,
  createdtime_ timestamp with time zone,
  CONSTRAINT pk_psid PRIMARY KEY (psid_)
);