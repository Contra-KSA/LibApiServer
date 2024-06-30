create table if not exists user_roles
(
    role_id bigint not null references users,
    user_id bigint not null references roles,
        unique (user_id, role_id)
) engine=InnoDB;

-- alter table user_roles
--     drop key UK5q4rc4fh1on6567qk69uesvyf;

