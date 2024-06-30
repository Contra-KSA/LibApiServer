create table if not exists auhtors
(
    id         bigint not null,
    firstName  varchar(255),
    lastName   varchar(255),
    middleName varchar(255),
    primary key (id)
) engine=InnoDB