# usage:
# mysql -u root -p  < C:\Users\STsypanov\Documents\its\diplom\desktop-cloud\database-api\src\main\resources\st_insert_user.sql

DELIMITER $$

USE desktop_cloud$$

DROP PROCEDURE IF EXISTS sp_insert_into_radcheck$$

CREATE PROCEDURE sp_insert_into_radcheck(
  IN p_username  VARCHAR(64),
  IN p_attribute VARCHAR(64),
  IN p_op        VARCHAR(2),
  IN p_value     VARCHAR(256)
)
  BEGIN
    INSERT INTO radcheck
    (
      userName,
      attribute,
      op,
      value
    )
    VALUES
      (
        p_username,
        p_attribute,
        p_op,
        p_value
      );
  END$$
DELIMITER ;