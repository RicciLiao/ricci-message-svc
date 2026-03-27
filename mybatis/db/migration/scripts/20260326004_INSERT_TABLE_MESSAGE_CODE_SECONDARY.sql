INSERT INTO message_code_secondary (code, consumer, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (1, 'bsm', null, 'This Email has been registered, you can sign in directly or use another Email to register.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_secondary (code, consumer, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (2, 'bsm', null, 'Captcha does not match, please try again.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_secondary (code, consumer, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (3, 'bsm', null, 'Time out, please try again.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_secondary (code, consumer, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (4, 'bsm', null, 'Login failed, please check your input.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_secondary (code, consumer, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (1, 'mcp', null, 'Provider {}-{} already exists.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_secondary (code, consumer, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (2, 'mcp', null, 'ProviderFactory [{}_{}] does not exist.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_secondary (code, consumer, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (3, 'mcp', null, 'Cannot find the Provider [{}_{}].', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_secondary (code, consumer, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (4, 'mcp', null, 'ProviderFactory [{}_{}] already exist.', 0, sysdate(), 0, sysdate(), sysdate());

-- //@UNDO
DELETE
FROM message_code_secondary
WHERE code IN (1, 2, 3, 4)
  AND consumer = 'bsm';
DELETE
FROM message_code_secondary
WHERE code IN (1, 2, 3, 4)
  AND consumer = 'mcp';