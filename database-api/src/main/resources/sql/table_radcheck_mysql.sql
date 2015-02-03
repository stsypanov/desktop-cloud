USE desktop_cloud;
DROP TABLE IF EXISTS radcheck;
CREATE TABLE radcheck (
  id        INT(11)      NOT NULL AUTO_INCREMENT,
  username  VARCHAR(64)  NOT NULL DEFAULT '',
  attribute VARCHAR(64)  NOT NULL DEFAULT '',
  op        VARCHAR(2)   NOT NULL DEFAULT '==',
  value     VARCHAR(256) NOT NULL DEFAULT '',
  PRIMARY KEY (id)
);
