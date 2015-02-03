CREATE OR REPLACE FUNCTION sp_insert_into_activeusers
  (
    p_username  VARCHAR(64),
    p_attribute VARCHAR(64),
    p_op        CHAR(2),
    p_value     VARCHAR(253)

  )
  RETURNS VOID AS $$
BEGIN
  INSERT INTO public.radcheck
  (
    username,
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
    ); END;$$
LANGUAGE 'plpgsql';
