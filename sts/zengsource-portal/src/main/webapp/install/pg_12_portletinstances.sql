CREATE SEQUENCE seria_piid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE portletinstances
(
  piid_ bigint NOT NULL,
  name_ character varying(1500),
  index_ integer DEFAULT 1,
  cls_ character varying(1500),
  style_ character varying(1500),
  colspan_ integer DEFAULT 1,
  rowspan_ integer DEFAULT 1,
  width_ integer DEFAULT 330,
  height_ integer DEFAULT 330,
  html_ text,
  portletid_ bigint,
  description_ text,
  updatedtime_ timestamp with time zone,
  createdtime_ timestamp with time zone,
  CONSTRAINT pk_piid PRIMARY KEY (piid_)
);