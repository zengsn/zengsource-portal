CREATE SEQUENCE seria_mnid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE menus
(
  mnid_ bigint NOT NULL DEFAULT nextval('seria_mnid'::regclass),
  url_ character varying(1500),
  name_ character varying(128),
  type_ character varying(128),
  title_ character varying(1500),
  icon_ character varying(1500),
  widget_ character varying(1500),
  controller_ character varying(1500),
  index_ integer DEFAULT 1,
  moduleid_ bigint,
  parentid_ bigint,
  description_ character varying(1500),
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT pk_mnid PRIMARY KEY (mnid_)
);