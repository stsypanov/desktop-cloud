CREATE TABLE radacct (
  RadAcctId            BIGSERIAL PRIMARY KEY,
  AcctSessionId        VARCHAR(64) NOT NULL,
  AcctUniqueId         VARCHAR(32) NOT NULL UNIQUE,
  UserName             VARCHAR(253),
  GroupName            VARCHAR(253),
  Realm                VARCHAR(64),
  NASIPAddress         INET        NOT NULL,
  NASPortId            VARCHAR(15),
  NASPortType          VARCHAR(32),
  AcctStartTime        TIMESTAMP WITH TIME ZONE,
  AcctStopTime         TIMESTAMP WITH TIME ZONE,
  AcctSessionTime      BIGINT,
  AcctAuthentic        VARCHAR(32),
  ConnectInfo_start    VARCHAR(50),
  ConnectInfo_stop     VARCHAR(50),
  AcctInputOctets      BIGINT,
  AcctOutputOctets     BIGINT,
  CalledStationId      VARCHAR(50),
  CallingStationId     VARCHAR(50),
  AcctTerminateCause   VARCHAR(32),
  ServiceType          VARCHAR(32),
  XAscendSessionSvrKey VARCHAR(10),
  FramedProtocol       VARCHAR(32),
  FramedIPAddress      INET,
  AcctStartDelay       INTEGER,
  AcctStopDelay        INTEGER
);
CREATE INDEX radacct_active_user_idx ON radacct (UserName, NASIPAddress, AcctSessionId)
  WHERE AcctStopTime IS NULL;
CREATE INDEX radacct_start_user_idx ON radacct (AcctStartTime, UserName);
CREATE TABLE radcheck (
  id        SERIAL PRIMARY KEY,
  UserName  VARCHAR(64)  NOT NULL DEFAULT '',
  Attribute VARCHAR(64)  NOT NULL DEFAULT '',
  op        CHAR(2)      NOT NULL DEFAULT '==',
  Value     VARCHAR(253) NOT NULL DEFAULT ''
);
CREATE INDEX radcheck_UserName ON radcheck (UserName, Attribute);
CREATE TABLE radgroupcheck (
  id        SERIAL PRIMARY KEY,
  GroupName VARCHAR(64)  NOT NULL DEFAULT '',
  Attribute VARCHAR(64)  NOT NULL DEFAULT '',
  op        CHAR(2)      NOT NULL DEFAULT '==',
  Value     VARCHAR(253) NOT NULL DEFAULT ''
);
CREATE INDEX radgroupcheck_GroupName ON radgroupcheck (GroupName, Attribute);
CREATE TABLE radgroupreply (
  id        SERIAL PRIMARY KEY,
  GroupName VARCHAR(64)  NOT NULL DEFAULT '',
  Attribute VARCHAR(64)  NOT NULL DEFAULT '',
  op        CHAR(2)      NOT NULL DEFAULT '=',
  Value     VARCHAR(253) NOT NULL DEFAULT ''
);
CREATE INDEX radgroupreply_GroupName ON radgroupreply (GroupName, Attribute);
CREATE TABLE radreply (
  id        SERIAL PRIMARY KEY,
  UserName  VARCHAR(64)  NOT NULL DEFAULT '',
  Attribute VARCHAR(64)  NOT NULL DEFAULT '',
  op        CHAR(2)      NOT NULL DEFAULT '=',
  Value     VARCHAR(253) NOT NULL DEFAULT ''
);
CREATE INDEX radreply_UserName ON radreply (UserName, Attribute);
CREATE TABLE radusergroup (
  UserName  VARCHAR(64) NOT NULL DEFAULT '',
  GroupName VARCHAR(64) NOT NULL DEFAULT '',
  priority  INTEGER     NOT NULL DEFAULT 0
);
CREATE INDEX radusergroup_UserName ON radusergroup (UserName);
CREATE TABLE radpostauth (
  id               BIGSERIAL PRIMARY KEY,
  username         VARCHAR(253)             NOT NULL,
  pass             VARCHAR(128),
  reply            VARCHAR(32),
  CalledStationId  VARCHAR(50),
  CallingStationId VARCHAR(50),
  authdate         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT 'now()'
);
CREATE TABLE activeusers (
  id       SERIAL PRIMARY KEY,
  username VARCHAR(32),
  password VARCHAR(32)
);
