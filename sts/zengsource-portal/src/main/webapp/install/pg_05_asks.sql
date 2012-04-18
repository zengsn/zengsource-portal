CREATE SEQUENCE seria_aid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE asks
(
  aid_ bigint NOT NULL DEFAULT nextval('seria_aid'::regclass),
  email_ character varying(1500) NOT NULL,
  partneremail_ character varying(1500),
  introduction_ text,
  socialtype_ character varying(128),
  status_ smallint DEFAULT 0,
  ip_ character varying(128),
  createdtime_ timestamp with time zone DEFAULT now(),
  CONSTRAINT requests_pkey PRIMARY KEY (aid_)
)
WITH (
  OIDS=FALSE
);