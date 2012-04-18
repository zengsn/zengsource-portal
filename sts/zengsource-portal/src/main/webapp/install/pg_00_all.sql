
CREATE SEQUENCE seria_aid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE asks (
    aid_ bigint DEFAULT nextval('seria_aid'::regclass) NOT NULL,
    email_ character varying(1500) NOT NULL,
    partneremail_ character varying(1500),
    introduction_ text,
    socialtype_ character varying(128),
    status_ smallint DEFAULT 0,
    ip_ character varying(128),
    createdtime_ timestamp with time zone DEFAULT now()
);

CREATE SEQUENCE seria_uid
    START WITH 9999
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE configs (
    cfgid_ bigint DEFAULT nextval('seria_uid'::regclass) NOT NULL,
    name_ character varying(1500),
    key_ character varying(1500),
    value_ character varying(1500),
    group_ character varying(64),
    description_ character varying(1500),
    createdtime_ timestamp with time zone
);

CREATE SEQUENCE seria_ftid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE filetasks (
    ftid_ bigint DEFAULT nextval('seria_ftid'::regclass) NOT NULL,
    nameregex_ character varying(1500),
    allowformat_ character varying(1500),
    template_ character varying(1500),
    maxfilesize_ double precision,
    taskid_ bigint,
    updatedtime_ timestamp with time zone,
    createdtime_ timestamp with time zone
);

CREATE SEQUENCE seria_fid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE friendships (
    fid_ bigint DEFAULT nextval('seria_fid'::regclass) NOT NULL,
    partaid_ bigint,
    partbid_ bigint,
    messagea_ character varying(1500),
    messageb_ character varying(1500),
    email_ character varying(1500),
    status_ integer DEFAULT 0,
    createdtime_ timestamp with time zone,
    updatedtime_ timestamp with time zone
);

CREATE SEQUENCE seria_ivid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE invitations (
    ivid_ bigint DEFAULT nextval('seria_ivid'::regclass) NOT NULL,
    inviterid_ bigint,
    inviteeid_ bigint,
    email_ character varying(1500),
    method_ character varying(128),
    introduction_ text,
    code_ character varying(1500),
    status_ smallint DEFAULT 0,
    updatedtime_ timestamp with time zone,
    createdtime_ timestamp with time zone
);

CREATE SEQUENCE seria_mnid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE menus (
    mnid_ bigint DEFAULT nextval('seria_mnid'::regclass) NOT NULL,
    url_ character varying(1500),
    name_ character varying(128),
    type_ character varying(128),
    icon_ character varying(1500),
    index_ integer DEFAULT 1,
    moduleid_ bigint,
    parentid_ bigint,
    description_ character varying(1500),
    createdtime_ timestamp with time zone,
    updatedtime_ timestamp with time zone,
    widget_ character varying(1500),
    title_ character varying(1500),
    controller_ character varying(1500)
);

CREATE SEQUENCE seria_mid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE modules (
    mid_ bigint DEFAULT nextval('seria_mid'::regclass) NOT NULL,
    name_ character varying(1500),
    description_ text,
    title_ character varying(1500),
    updatedtime_ timestamp with time zone,
    createdtime_ timestamp with time zone,
    status_ integer DEFAULT 0,
    index_ integer DEFAULT 0
);

CREATE SEQUENCE seria_pgid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE pages (
    pid_ bigint DEFAULT nextval('seria_pgid'::regclass) NOT NULL,
    name_ character varying(1500),
    url_ character varying(1500),
    cls_ character varying(1500),
    style_ character varying(1500),
    columns_ integer DEFAULT 1,
    defaultportletid_ bigint,
    moduleid_ bigint,
    description_ text,
    updatedtime_ timestamp with time zone,
    createdtime_ timestamp with time zone
);

CREATE SEQUENCE seria_ppid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE pages_portlets (
    ppid_ bigint DEFAULT nextval('seria_ppid'::regclass) NOT NULL,
    pageid_ bigint,
    portletinstanceid_ bigint,
    createdtime_ timestamp with time zone DEFAULT now()
);

CREATE SEQUENCE seria_pid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE permissions (
    pid_ bigint DEFAULT nextval('seria_pid'::regclass) NOT NULL,
    roleid_ bigint,
    name_ character varying(1500),
    createdtime_ timestamp with time zone
);

CREATE TABLE portletinstances (
    piid_ bigint NOT NULL,
    name_ character varying(1500),
    index_ integer DEFAULT 1,
    cls_ character varying(1500),
    style_ character varying(1500),
    colspan_ integer DEFAULT 1,
    rowspan_ integer DEFAULT 1,
    html_ text,
    portletid_ bigint,
    description_ text,
    updatedtime_ timestamp with time zone,
    createdtime_ timestamp with time zone,
    width_ integer DEFAULT 330,
    height_ integer DEFAULT 330
);

CREATE SEQUENCE seria_plid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE portlets (
    plid_ bigint DEFAULT nextval('seria_plid'::regclass) NOT NULL,
    name_ character varying(1500),
    type_ character varying(1500),
    template_ character varying(1500),
    html_ text,
    singleton_ boolean DEFAULT false,
    closable_ boolean DEFAULT true,
    collapsible_ boolean DEFAULT true,
    draggable_ boolean DEFAULT false,
    jssource_ character varying(1500),
    jsclass_ character varying(1500),
    jswidget_ character varying(1500),
    colspan_ integer DEFAULT 1,
    rowspan_ integer DEFAULT 1,
    width_ integer DEFAULT 330,
    height_ integer DEFAULT 330,
    moduleid_ bigint,
    description_ text,
    updatedtime_ timestamp with time zone,
    createdtime_ timestamp with time zone,
    jscontroller_ character varying(1500),
    jsrequiredmodule_ character varying(1500)
);

CREATE SEQUENCE seria_psid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE portletsettings (
    psid_ bigint DEFAULT nextval('seria_psid'::regclass) NOT NULL,
    key_ character varying(128),
    name_ character varying(128),
    value_ character varying(1500),
    usage_ character varying(1500),
    portletid_ bigint,
    instanceid_ bigint,
    updatedtime_ timestamp with time zone,
    createdtime_ timestamp with time zone
);

CREATE SEQUENCE seria_qid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE questions (
    qid_ bigint DEFAULT nextval('seria_qid'::regclass) NOT NULL,
    text_ text,
    email_ text,
    authorid_ bigint,
    status_ smallint DEFAULT 0,
    updatedtime_ timestamp with time zone DEFAULT now(),
    createdtime_ timestamp with time zone DEFAULT now()
);

CREATE SEQUENCE seria_rid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE roles (
    rid_ bigint DEFAULT nextval('seria_rid'::regclass) NOT NULL,
    name_ character varying(128),
    description_ character varying(1500),
    createdtime_ timestamp with time zone,
    updatedtime_ timestamp with time zone,
    parentid_ bigint
);

CREATE SEQUENCE seria_cfgid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seria_piid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seria_teid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seria_tenid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seria_terid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seria_tnid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seria_tsid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seria_uiid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seria_ur
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE seria_uuid
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE taskexecutions (
    teid_ bigint DEFAULT nextval('seria_tenid'::regclass) NOT NULL,
    executorid_ bigint,
    taskid_ bigint,
    status_ integer DEFAULT 0,
    progress_ real,
    ismain_ boolean,
    createdtime_ timestamp with time zone,
    updatedtime_ timestamp with time zone,
    lastnoteid_ bigint
);

CREATE TABLE taskexecutors (
    teid_ bigint DEFAULT nextval('seria_terid'::regclass) NOT NULL,
    username_ character varying(1500),
    userid_ bigint,
    nickname_ character varying(1500),
    updatedtime_ timestamp with time zone,
    createdtime_ timestamp with time zone,
    avatar_ character varying(1500)
);

CREATE TABLE tasknotes (
    tnid_ bigint DEFAULT nextval('seria_tnid'::regclass) NOT NULL,
    executorid_ bigint,
    executionid_ bigint,
    text_ text,
    progress_ real DEFAULT 0.0,
    attachment_ character varying(1500),
    createdtime_ timestamp with time zone,
    status_ integer DEFAULT 0
);

CREATE TABLE tasks (
    tsid_ bigint DEFAULT nextval('seria_tsid'::regclass) NOT NULL,
    name_ character varying(1500),
    introduction_ text,
    tags_ character varying(1500),
    status_ integer,
    progress_ real,
    starttime_ timestamp with time zone,
    endtime_ timestamp with time zone,
    createdtime_ timestamp with time zone,
    updatedtime_ timestamp with time zone,
    requestorid_ bigint,
    feature_ character varying(1500),
    mainexecutionid_ bigint,
    attachment_ character varying(1500)
);

CREATE TABLE tasks_executors (
    teid_ bigint DEFAULT nextval('seria_teid'::regclass) NOT NULL,
    taskid_ bigint,
    executorid_ bigint,
    createdtime_ timestamp with time zone DEFAULT now()
);

CREATE TABLE userinfo (
    uid_ bigint NOT NULL,
    nickname_ character varying(1500),
    userid_ bigint,
    tags_ character varying(1500),
    shared_ boolean,
    createdtime_ timestamp with time zone,
    updatedtime_ timestamp with time zone,
    username_ character varying(1500)
);

CREATE TABLE users (
    uid_ bigint DEFAULT nextval('seria_uid'::regclass) NOT NULL,
    username_ character varying(1500) NOT NULL,
    password_ character varying(1500) DEFAULT 123456,
    salt_ character varying(1500),
    createdtime_ timestamp with time zone,
    updatedtime_ timestamp with time zone,
    status_ smallint DEFAULT 0,
    email_ character varying(1500) NOT NULL,
    emailconfirm_ character varying(256),
    introduction_ character varying(1500),
    nickname_ character varying(1500),
    realname_ character varying(1500),
    realnameshare_ character varying(64),
    location_ character varying(1500),
    locationshare_ character varying(64),
    sex_ character varying(64),
    sexshare_ character varying(64),
    birthday_ timestamp with time zone,
    birthdayshare_ character varying(64),
    blog_ character varying(1500),
    blogshare_ character varying(64),
    qq_ character varying(64),
    qqshare_ character varying(64),
    weibo_ character varying(1500),
    weiboshare_ character varying(64),
    msn_ character varying(1500),
    msnshare_ character varying(64),
    mobile_ character varying(64),
    mobileshare_ character varying(64),
    emailshare_ character varying(64),
    avatar_ character varying(1500)
);

CREATE TABLE users_roles (
    id_ bigint DEFAULT nextval('seria_ur'::regclass) NOT NULL,
    userid_ bigint,
    roleid_ bigint,
    description_ character varying(1500),
    createdtime_ timestamp with time zone
);

CREATE TABLE userupdates (
    uuid_ bigint DEFAULT nextval('seria_uuid'::regclass) NOT NULL,
    text_ text,
    picture_ character varying(1500),
    status_ integer DEFAULT 0,
    ownerid_ bigint,
    createdtime_ timestamp with time zone
);

ALTER TABLE ONLY configs
    ADD CONSTRAINT pk_cfgid PRIMARY KEY (cfgid_);

ALTER TABLE ONLY friendships
    ADD CONSTRAINT pk_fid PRIMARY KEY (fid_);

ALTER TABLE ONLY filetasks
    ADD CONSTRAINT pk_ftid PRIMARY KEY (ftid_);

ALTER TABLE ONLY invitations
    ADD CONSTRAINT pk_ivid PRIMARY KEY (ivid_);

ALTER TABLE ONLY modules
    ADD CONSTRAINT pk_mid PRIMARY KEY (mid_);

ALTER TABLE ONLY menus
    ADD CONSTRAINT pk_mnid PRIMARY KEY (mnid_);

ALTER TABLE ONLY pages
    ADD CONSTRAINT pk_pgid PRIMARY KEY (pid_);

ALTER TABLE ONLY permissions
    ADD CONSTRAINT pk_pid PRIMARY KEY (pid_);

ALTER TABLE ONLY portletinstances
    ADD CONSTRAINT pk_piid PRIMARY KEY (piid_);

ALTER TABLE ONLY portlets
    ADD CONSTRAINT pk_plid PRIMARY KEY (plid_);

ALTER TABLE ONLY pages_portlets
    ADD CONSTRAINT pk_ppid PRIMARY KEY (ppid_);

ALTER TABLE ONLY portletsettings
    ADD CONSTRAINT pk_psid PRIMARY KEY (psid_);

ALTER TABLE ONLY questions
    ADD CONSTRAINT pk_qid PRIMARY KEY (qid_);

ALTER TABLE ONLY tasks_executors
    ADD CONSTRAINT pk_teid PRIMARY KEY (teid_);

ALTER TABLE ONLY taskexecutions
    ADD CONSTRAINT pk_tenid PRIMARY KEY (teid_);

ALTER TABLE ONLY taskexecutors
    ADD CONSTRAINT pk_terid PRIMARY KEY (teid_);

ALTER TABLE ONLY tasknotes
    ADD CONSTRAINT pk_tnid PRIMARY KEY (tnid_);

ALTER TABLE ONLY tasks
    ADD CONSTRAINT pk_tsid PRIMARY KEY (tsid_);

ALTER TABLE ONLY userinfo
    ADD CONSTRAINT pk_uiid PRIMARY KEY (uid_);

ALTER TABLE ONLY userupdates
    ADD CONSTRAINT pk_uuid PRIMARY KEY (uuid_);

ALTER TABLE ONLY asks
    ADD CONSTRAINT requests_pkey PRIMARY KEY (aid_);

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (rid_);

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (uid_);

ALTER TABLE ONLY users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (id_);

