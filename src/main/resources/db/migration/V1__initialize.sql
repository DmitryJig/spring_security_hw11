create table products (
    id bigserial primary key,
    title varchar(100),
    price double precision
);

create table users(
    id bigserial primary key,
    username varchar(30) not null ,
    password varchar(80) not null ,
    email varchar(50) unique
);

create table roles (
    id serial primary key,
    name varchar(50) not null
);

create table users_roles (
    user_id bigint not null ,
    role_id int not null ,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

