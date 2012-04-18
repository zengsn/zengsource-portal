CREATE SEQUENCE seria_teid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE tasks_executors
(
  teid_ bigint NOT NULL DEFAULT nextval('seria_teid'::regclass),
  taskid_ bigint,
  executorid_ bigint,
  createdtime_ timestamp with time zone DEFAULT now(),
  CONSTRAINT pk_teid PRIMARY KEY (teid_)
);