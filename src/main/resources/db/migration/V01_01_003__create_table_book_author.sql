create table if not exists book_author
(
    author_id bigint not null,
    book_id   bigint not null
) engine=InnoDB