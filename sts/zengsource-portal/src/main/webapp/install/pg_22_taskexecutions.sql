CREATE SEQUENCE seria_tenid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE taskexecutions
(
  teid_ bigint NOT NULL DEFAULT nextval('seria_tenid'::regclass),
  executorid_ bigint,
  taskid_ bigint,
  status_ integer DEFAULT 0,
  progress_ real,
  ismain_ boolean,
  lastnoteid_ bigint,
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT pk_tenid PRIMARY KEY (teid_)
);