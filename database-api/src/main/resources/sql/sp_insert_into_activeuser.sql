# usage:
# mysql -u root -p  < C:\Users\STsypanov\Documents\its\diplom\desktop-cloud\database-api\src\main\resources\st_insert_user.sql

DELIMITER $$

USE desktop_cloud$$

DROP PROCEDURE IF EXISTS sp_insert_into_activeusers$$

CREATE PROCEDURE sp_insert_into_activeusers
  (
    IN p_username VARCHAR(16),
    IN p_password VARCHAR(16)
  )
  BEGIN
    INSERT INTO activeusers
    (
      username,
      password
    )
    VALUES
      (
        p_username,
        p_password
      );
  END$$

DELIMITER ;
