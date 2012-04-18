CREATE SEQUENCE seria_ftid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE filetasks
(
  ftid_ bigint NOT NULL DEFAULT nextval('seria_ftid'::regclass),
  nameregex_ character varying(1500),
  allowformat_ character varying(1500),
  template_ character varying(1500),
  maxfilesize_ double precision,
  taskid_ bigint,
  updatedtime_ timestamp with time zone,
  createdtime_ timestamp with time zone,
  CONSTRAINT pk_ftid PRIMARY KEY (ftid_)
);