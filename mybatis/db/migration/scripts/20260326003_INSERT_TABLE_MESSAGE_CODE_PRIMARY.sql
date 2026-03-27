INSERT INTO message_code_primary (code, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (0, 'I', 'Success.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_primary (code, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (1, 'W', 'Data warning.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_primary (code, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (2, 'W', 'Parameter warning.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_primary (code, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (3, 'W', 'Rest Call warning.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_primary (code, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (8, 'E', 'Security error.', 0, sysdate(), 0, sysdate(), sysdate());
INSERT INTO message_code_primary (code, level, description, created_by, created_dtm, updated_by, updated_dtm, version)
VALUES (9, 'E', 'Unexpected error.', 0, sysdate(), 0, sysdate(), sysdate());

-- //@UNDO
DELETE
FROM message_code_primary
WHERE code IN (0, 1, 2, 3, 8, 9);