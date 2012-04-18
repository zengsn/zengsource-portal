CREATE SEQUENCE seria_cfgid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE configs
(
  cfgid_ bigint NOT NULL DEFAULT nextval('seria_uid'::regclass),
  name_ character varying(1500),
  key_ character varying(1500),
  value_ character varying(1500),
  group_ character varying(64),
  description_ character varying(1500),
  createtime_ timestamp with time zone,
  CONSTRAINT pk_cfgid PRIMARY KEY (cfgid_),
  CONSTRAINT uq_cfg_key UNIQUE (key_)
);