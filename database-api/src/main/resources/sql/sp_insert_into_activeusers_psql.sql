CREATE OR REPLACE FUNCTION sp_insert_into_radcheck
  (
    p_username  VARCHAR(16),
    p_attribute VARCHAR(16)
  )
  RETURNS VOID AS $$
BEGIN
  INSERT INTO activeusers
  (
    username,
    password
  )
  VALUES
    (
      p_username,
      p_attribute
    ); END;$$
LANGUAGE 'plpgsql';
