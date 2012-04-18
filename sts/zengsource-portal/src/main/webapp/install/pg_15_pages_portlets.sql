CREATE SEQUENCE seria_ppid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE pages_portlets
(
  ppid_ bigint NOT NULL DEFAULT nextval('seria_ppid'::regclass),
  pageid_ bigint,
  portletinstanceid_ bigint,
  createdtime_ timestamp with time zone DEFAULT now(),
  CONSTRAINT pk_ppid PRIMARY KEY (ppid_)
);