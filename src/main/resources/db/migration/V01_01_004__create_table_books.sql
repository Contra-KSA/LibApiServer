create table if not exists books
(
    year  integer not null,
    id    bigint  not null auto_increment,
    isbn  varchar(255),
    title varchar(255),
    primary key (id)
) engine=InnoDB