CREATE SEQUENCE seria_nid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE notes
(
  nid_ bigint NOT NULL DEFAULT nextval('seria_nid'::regclass),
  authorid_ bigint,
  title_ character varying(1500),
  text_ text,
  status_ integer DEFAULT 0,
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT pk_nid PRIMARY KEY (nid_)
);