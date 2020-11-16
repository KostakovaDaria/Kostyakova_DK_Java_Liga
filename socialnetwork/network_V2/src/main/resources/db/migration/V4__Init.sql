create sequence hibernate_sequence start 1 increment 1;

create table if not exists country
(
    id			bigint not null primary key,
    name		character varying(128) not null
);

create table if not exists region
(
    id			bigint not null primary key,
    country_id	bigint not null references country (id),
    name		varchar(128) not null
);

create table if not exists city
(
    id bigserial not null primary key,
    region_id bigint not null references region(id),
    name varchar(255) not null
);

create index city_index on city(name);

comment on index city_index is 'Индекс для поиска по названию города';

create table if not exists gender
(
    id smallint not null primary key,
    gender varchar(1) not null
);

insert into gender(id, gender) values(1, 'M');
insert into gender(id, gender) values(2, 'F');
insert into gender(id, gender) values(3, 'X');

comment on table gender is 'Пол пользователя';
comment on column gender.id is 'Первичный ключ';
comment on column gender.gender is 'Пол';

create table if not exists usr
(
    id uuid not null primary key,
    username varchar(50) not null,
    password varchar(64) not null
);

create unique index user_index on usr(username);

comment on table usr is 'Учетная запись пользователея';
comment on column usr.id is 'Первичный ключ';
comment on column usr.password is 'Пароль';
comment on index user_index is 'Уникальный индекс для поиска по логину';

create table if not exists profile
(
    id uuid not null primary key,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    email varchar(255) not null,
    birthday date,
    city bigserial not null references city(id),
    gender smallint not null references gender(id),
    interests varchar(2048),
    active boolean not null
);

comment on table profile is 'Профиль пользователя';
comment on column profile.id is 'Первичный ключ';
comment on column profile.firstname is 'Имя';
comment on column profile.lastname is 'Фамилия';
comment on column profile.birthday is 'Дата рождения';
comment on column profile.city is 'Город';
comment on column profile.gender is 'Пол';
comment on column profile.interests is 'Интересы';


create table if not exists friends
(
    user_id uuid not null references profile(id),
    friend_id uuid not null references profile(id),
    status boolean,
    primary key (user_id, friend_id)
);

create index users_index on friends(user_id);
create index friends_index on friends(friend_id);

comment on table friends is 'Друзья пользователя';
comment on column friends.user_id is 'Составной первичный ключ';
comment on column friends.friend_id is 'Составной первичный ключ';
comment on index users_index is 'Индекс для поиска по user_id';
comment on index friends_index is 'Индекс для поиск по friend_id';
