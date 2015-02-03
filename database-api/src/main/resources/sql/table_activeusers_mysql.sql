DROP TABLE IF EXISTS desktop_cloud.activeusers;
CREATE TABLE desktop_cloud.activeusers (
  id       INT(11)     NOT NULL AUTO_INCREMENT,
  username VARCHAR(16) NOT NULL,
  password VARCHAR(16) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY username (username)
);
