CREATE SEQUENCE seria_rid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE roles
(
  rid_ bigint NOT NULL DEFAULT nextval('seria_rid'::regclass),
  name_ character varying(128),
  parentid_ bigint,
  description_ character varying(1500),
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT roles_pkey PRIMARY KEY (rid_)
)
WITH (
  OIDS=FALSE
);