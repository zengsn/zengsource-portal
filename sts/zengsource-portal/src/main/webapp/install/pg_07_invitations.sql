CREATE SEQUENCE seria_ivid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE invitations
(
  ivid_ bigint NOT NULL DEFAULT nextval('seria_ivid'::regclass),
  inviterid_ bigint,
  inviteeid_ bigint,
  email_ character varying(1500),
  method_ character varying(128),
  introduction_ text,
  code_ character varying(1500),
  status_ smallint DEFAULT 0,
  createdtime_ timestamp with time zone,
  updatedtime_ timestamp with time zone,
  CONSTRAINT pk_ivid PRIMARY KEY (ivid_)
);
