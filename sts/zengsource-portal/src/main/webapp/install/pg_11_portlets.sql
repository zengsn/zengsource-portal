CREATE SEQUENCE seria_plid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE portlets
(
  plid_ bigint NOT NULL DEFAULT nextval('seria_plid'::regclass),
  name_ character varying(1500),
  type_ character varying(1500),
  template_ character varying(1500),
  html_ text,
  singleton_ boolean DEFAULT false,
  closable_ boolean DEFAULT true,
  collapsible_ boolean DEFAULT true,
  draggable_ boolean DEFAULT false,
  jssource_ character varying(1500),
  jsclass_ character varying(1500),
  jswidget_ character varying(1500),
  jscontroller_ character varying(1500),
  jsrequiredmodule_ character varying(1500),
  colspan_ integer DEFAULT 1,
  rowspan_ integer DEFAULT 1,
  width_ integer DEFAULT 330,
  height_ integer DEFAULT 330,
  moduleid_ bigint,
  description_ text,
  updatedtime_ timestamp with time zone,
  createdtime_ timestamp with time zone,
  CONSTRAINT pk_plid PRIMARY KEY (plid_),
  CONSTRAINT uq_pl_jsclass UNIQUE (jsclass_)
);