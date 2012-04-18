CREATE SEQUENCE seria_tnid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE tasknotes
(
  tnid_ bigint NOT NULL DEFAULT nextval('seria_tnid'::regclass),
  executorid_ bigint,
  executionid_ bigint,
  text_ text,
  progress_ real DEFAULT 0.0,
  status_ integer DEFAULT 0,
  attachment_ character varying(1500),
  createdtime_ timestamp with time zone,
  CONSTRAINT pk_tnid PRIMARY KEY (tnid_)
);