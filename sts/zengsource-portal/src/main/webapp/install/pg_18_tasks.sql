CREATE SEQUENCE seria_tsid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE tasks
(
  tsid_ bigint NOT NULL DEFAULT nextval('seria_tsid'::regclass),
  name_ character varying(1500),
  introduction_ text,
  status_ integer,
  progress_ real,
  feature_ character varying(1500),
  tags_ character varying(1500),
  requestorid_ bigint,
  /*mainexecutionid_ bigint,*/
  attachment_ character varying(1500),
  starttime_ timestamp with time zone,
  endtime_ timestamp with time zone,
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT pk_tsid PRIMARY KEY (tsid_)
);