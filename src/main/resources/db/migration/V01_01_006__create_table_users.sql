create table if not exists users
(
    Id       bigint not null auto_increment,
    password varchar(255),
    status   varchar(255),
    username varchar(255),
    primary key (Id)
) engine=InnoDB