--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.1
-- Dumped by pg_dump version 9.1.1
-- Started on 2012-01-15 21:49:20 CST

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 173 (class 3079 OID 11680)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1953 (class 0 OID 0)
-- Dependencies: 173
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 168 (class 1259 OID 16438)
-- Dependencies: 5
-- Name: seria_aid; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seria_aid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seria_aid OWNER TO postgres;

--
-- TOC entry 1954 (class 0 OID 0)
-- Dependencies: 168
-- Name: seria_aid; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seria_aid', 3, true);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 167 (class 1259 OID 16429)
-- Dependencies: 1922 1923 1924 5
-- Name: asks; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE asks (
    aid_ bigint DEFAULT nextval('seria_aid'::regclass) NOT NULL,
    email_ character varying(1500) NOT NULL,
    partneremail_ character varying(1500),
    introduction_ text,
    socialtype_ character varying(128),
    createdtime_ timestamp with time zone DEFAULT now(),
    status_ smallint DEFAULT 0,
    ip_ character varying(128)
);


ALTER TABLE public.asks OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16463)
-- Dependencies: 5
-- Name: seria_pid; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seria_pid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seria_pid OWNER TO postgres;

--
-- TOC entry 1955 (class 0 OID 0)
-- Dependencies: 170
-- Name: seria_pid; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seria_pid', 1, true);


--
-- TOC entry 169 (class 1259 OID 16455)
-- Dependencies: 1925 5
-- Name: permissions; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE permissions (
    pid_ bigint DEFAULT nextval('seria_pid'::regclass) NOT NULL,
    roleid_ bigint,
    name_ character varying(1500),
    createdtime_ timestamp with time zone
);


ALTER TABLE public.permissions OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16469)
-- Dependencies: 5
-- Name: seria_qid; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seria_qid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seria_qid OWNER TO postgres;

--
-- TOC entry 1956 (class 0 OID 0)
-- Dependencies: 171
-- Name: seria_qid; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seria_qid', 5, true);


--
-- TOC entry 172 (class 1259 OID 16471)
-- Dependencies: 1926 1927 1928 1929 5
-- Name: questions; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE questions (
    qid_ bigint DEFAULT nextval('seria_qid'::regclass) NOT NULL,
    text_ text,
    email_ text,
    authorid_ bigint,
    status_ smallint DEFAULT 0,
    updatedtime_ timestamp with time zone DEFAULT now(),
    createdtime_ timestamp with time zone DEFAULT now()
);


ALTER TABLE public.questions OWNER TO postgres;

--
-- TOC entry 165 (class 1259 OID 16423)
-- Dependencies: 5
-- Name: seria_rid; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seria_rid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seria_rid OWNER TO postgres;

--
-- TOC entry 1957 (class 0 OID 0)
-- Dependencies: 165
-- Name: seria_rid; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seria_rid', 1, true);


--
-- TOC entry 162 (class 1259 OID 16402)
-- Dependencies: 1920 5
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE roles (
    rid_ bigint DEFAULT nextval('seria_rid'::regclass) NOT NULL,
    name_ character varying(128),
    description_ character varying(1500),
    createdtime_ timestamp with time zone,
    updatedtime_ timestamp with time zone
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 164 (class 1259 OID 16420)
-- Dependencies: 5
-- Name: seria_uid; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seria_uid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seria_uid OWNER TO postgres;

--
-- TOC entry 1958 (class 0 OID 0)
-- Dependencies: 164
-- Name: seria_uid; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seria_uid', 1, true);


--
-- TOC entry 166 (class 1259 OID 16426)
-- Dependencies: 5
-- Name: seria_ur; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE seria_ur
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seria_ur OWNER TO postgres;

--
-- TOC entry 1959 (class 0 OID 0)
-- Dependencies: 166
-- Name: seria_ur; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('seria_ur', 1, true);


--
-- TOC entry 161 (class 1259 OID 16385)
-- Dependencies: 1918 1919 5
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    username_ character varying(64) NOT NULL,
    password_ character varying(150) DEFAULT 123456,
    salt_ character varying(1500),
    createdtime_ timestamp with time zone,
    updatedtime_ timestamp with time zone,
    uid_ bigint DEFAULT nextval('seria_uid'::regclass) NOT NULL,
    email_ character varying(1500)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 163 (class 1259 OID 16412)
-- Dependencies: 1921 5
-- Name: users_roles; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users_roles (
    id_ bigint DEFAULT nextval('seria_ur'::regclass) NOT NULL,
    userid_ bigint,
    roleid_ bigint,
    description_ character varying(1500),
    createdtime_ timestamp with time zone
);


ALTER TABLE public.users_roles OWNER TO postgres;

--
-- TOC entry 1945 (class 0 OID 16429)
-- Dependencies: 167
-- Data for Name: asks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY asks (aid_, email_, partneremail_, introduction_, socialtype_, createdtime_, status_, ip_) FROM stdin;
3	z.sn@foxmail.com	zxnwillow@163.com	dsdss	sina	\N	0	127.0.0.1
\.


--
-- TOC entry 1946 (class 0 OID 16455)
-- Dependencies: 169
-- Data for Name: permissions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY permissions (pid_, roleid_, name_, createdtime_) FROM stdin;
1	1	function:admin	2011-11-08 20:47:28.791+08
\.


--
-- TOC entry 1947 (class 0 OID 16471)
-- Dependencies: 172
-- Data for Name: questions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY questions (qid_, text_, email_, authorid_, status_, updatedtime_, createdtime_) FROM stdin;
2	您多久没有幸福感了？	\N	1	0	\N	\N
3	您多久陪另一半出去游玩？	\N	1	0	2011-11-12 00:13:03.909+08	2011-11-12 00:13:03.909+08
4	您多久陪另一半出去游玩？?	\N	1	0	2011-11-12 00:17:57.872+08	2011-11-12 00:17:57.872+08
5	另一半最喜欢您做什么事情？	\N	1	0	2011-11-12 00:23:52.699+08	2011-11-12 00:23:52.699+08
\.


--
-- TOC entry 1943 (class 0 OID 16402)
-- Dependencies: 162
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY roles (rid_, name_, description_, createdtime_, updatedtime_) FROM stdin;
1	admin	ok	2011-11-08 19:54:11.474+08	2011-11-08 19:54:11.474+08
\.


--
-- TOC entry 1942 (class 0 OID 16385)
-- Dependencies: 161
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (username_, password_, salt_, createdtime_, updatedtime_, uid_, email_) FROM stdin;
admin	308644ff9863aa76453ee17d12e8c5d8b5546b624425b4257a70a57bb33b3660	random_salt_value_admin	2011-11-09 23:56:56.065+08	2011-11-09 23:56:56.206+08	1	mail@zsn.cc
\.


--
-- TOC entry 1944 (class 0 OID 16412)
-- Dependencies: 163
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users_roles (id_, userid_, roleid_, description_, createdtime_) FROM stdin;
1	1	1	\N	\N
\.


--
-- TOC entry 1937 (class 2606 OID 16453)
-- Dependencies: 167 167
-- Name: ask_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY asks
    ADD CONSTRAINT ask_pkey PRIMARY KEY (aid_);


--
-- TOC entry 1939 (class 2606 OID 16467)
-- Dependencies: 169 169
-- Name: pk_pid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY permissions
    ADD CONSTRAINT pk_pid PRIMARY KEY (pid_);


--
-- TOC entry 1941 (class 2606 OID 16482)
-- Dependencies: 172 172
-- Name: pk_qid; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY questions
    ADD CONSTRAINT pk_qid PRIMARY KEY (qid_);


--
-- TOC entry 1933 (class 2606 OID 16409)
-- Dependencies: 162 162
-- Name: roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rid_);


--
-- TOC entry 1931 (class 2606 OID 16411)
-- Dependencies: 161 161
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (uid_);


--
-- TOC entry 1935 (class 2606 OID 16419)
-- Dependencies: 163 163
-- Name: users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (id_);


--
-- TOC entry 1952 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2012-01-15 21:49:21 CST

--
-- PostgreSQL database dump complete
--

