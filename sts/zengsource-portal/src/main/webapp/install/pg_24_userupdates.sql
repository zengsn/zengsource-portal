CREATE SEQUENCE seria_uuid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
CREATE TABLE userupdates
(
  uuid_ bigint NOT NULL DEFAULT nextval('seria_uuid'::regclass),
  text_ text,
  picture_ character varying(1500),
  status_ integer DEFAULT 0,
  ownerid_ bigint,
  createdtime_ timestamp with time zone,
  CONSTRAINT pk_uuid PRIMARY KEY (uuid_)
);