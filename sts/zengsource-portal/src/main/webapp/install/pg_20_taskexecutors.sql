CREATE SEQUENCE seria_terid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE taskexecutors
(
  teid_ bigint NOT NULL DEFAULT nextval('seria_terid'::regclass),
  avatar character varying(1500),
  username_ character varying(1500),
  nickname_ character varying(1500),
  userid_ bigint,
  updatedtime_ timestamp with time zone,
  createdtime_ timestamp with time zone,
  CONSTRAINT pk_terid PRIMARY KEY (teid_)
);