alter table user_roles
    drop key UK5q4rc4fh1on6567qk69uesvyf;

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 1);

INSERT INTO user_roles (user_id, role_id)
VALUES (1, 2);

INSERT INTO user_roles (user_id, role_id)
VALUES (2, 2);
