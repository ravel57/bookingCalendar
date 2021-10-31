create table cabinet
(
    id   int auto_increment
        primary key,
    name varchar(10) null
);

create table reservations
(
    id         int auto_increment
        primary key,
    user_id    int         not null,
    cabinet_id int         not null,
    start_time datetime    not null,
    duration   int         not null,
    title      varchar(50) null
);

create table client
(
    id          int auto_increment
        primary key,
    telegram_id int         not null,
    name        varchar(30) null
);