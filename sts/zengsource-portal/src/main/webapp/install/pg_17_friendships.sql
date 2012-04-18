CREATE SEQUENCE seria_fid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE friendships
(
  fid_ bigint NOT NULL DEFAULT nextval('seria_fid'::regclass),
  partaid_ bigint,
  partbid_ bigint,
  messagea_ character varying(1500),
  messageb_ character varying(1500),
  email_ character varying(1500),
  status_ integer DEFAULT 0,
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT pk_fid PRIMARY KEY (fid_)
);