CREATE SEQUENCE seria_mid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE modules
(
  mid_ bigint NOT NULL DEFAULT nextval('seria_mid'::regclass),
  name_ character varying(1500),
  title_ character varying(1500),
  description_ text,
  status_ integer DEFAULT 0,
  index_ integer DEFAULT 0,
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT pk_mid PRIMARY KEY (mid_),
  CONSTRAINT uq_md_name UNIQUE (name_)
);