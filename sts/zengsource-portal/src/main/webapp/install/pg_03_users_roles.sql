
CREATE SEQUENCE seria_ur
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE users_roles
(
  id_ bigint NOT NULL DEFAULT nextval('seria_ur'::regclass),
  userid_ bigint,
  roleid_ bigint,
  description_ character varying(1500),
  createdtime_ timestamp with time zone,
  CONSTRAINT users_roles_pkey PRIMARY KEY (id_)
)
WITH (
  OIDS=FALSE
);