CREATE SEQUENCE seria_pid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE permissions
(
  pid_ bigint NOT NULL DEFAULT nextval('seria_pid'::regclass),
  roleid_ bigint,
  name_ character varying(1500),
  createdtime_ timestamp with time zone,
  CONSTRAINT pk_pid PRIMARY KEY (pid_)
)
WITH (
  OIDS=FALSE
);