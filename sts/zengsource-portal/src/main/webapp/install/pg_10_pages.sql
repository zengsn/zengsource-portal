CREATE SEQUENCE seria_pgid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE pages
(
  pid_ bigint NOT NULL DEFAULT nextval('seria_pgid'::regclass),
  name_ character varying(1500),
  url_ character varying(1500),
  cls_ character varying(1500),
  style_ character varying(1500),
  columns_ integer DEFAULT 1,
  defaultportletid_ bigint,
  moduleid_ bigint,
  description_ text,
  updatedtime_ timestamp with time zone,
  createdtime_ timestamp with time zone,
  CONSTRAINT pk_pgid PRIMARY KEY (pid_),
  CONSTRAINT uq_pg_url UNIQUE (url_)
);