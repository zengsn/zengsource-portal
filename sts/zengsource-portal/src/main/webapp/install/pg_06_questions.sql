CREATE SEQUENCE seria_qid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
 
CREATE TABLE questions
(
  qid_ bigint NOT NULL DEFAULT nextval('seria_qid'::regclass),
  text_ text,
  email_ text,
  authorid_ bigint,
  status_ smallint DEFAULT 0,
  updatedtime_ timestamp with time zone DEFAULT now(),
  createdtime_ timestamp with time zone DEFAULT now(),
  CONSTRAINT pk_qid PRIMARY KEY (qid_)
)
WITH (
  OIDS=FALSE
);