insert into products (title, price)
values
    ('tomato', 10.0),
    ('potato', 20.0),
    ('cucumber', 120.0),
    ('tv', 1000.0),
    ('car', 1000000.0),
    ('fish', 500.0),
    ('butter', 120.0),
    ('milk', 50.0),
    ('dress', 2000.0),
    ('shoes', 5000.0),
    ('hummer', 500.0),
    ('paper', 200.0),
    ('laptop', 50000.0),
    ('bread', 40.0),
    ('cheese', 800.0),
    ('carrot', 30.0),
    ('book', 500.0),
    ('socks', 100.0),
    ('meat', 800.0),
    ('ice cream', 50.0);

insert into roles (name)
values
    ('ROLE_ADMIN'),
    ('ROLE_MANAGER'),
    ('ROLE_SUPERADMIN');

insert into users (username, password, email)
values
    ('user1', '$2a$12$K3q7XwCbSMoc/RNmKh0Lk.HtF883C1ICVqbR.iS5v3.ke65oh3J76', 'user1@gmail.com'),
    ('user2', '$2a$12$uzaVLKXhyaO/mB2U6aMyP.6.hYxwACaP6ZL6qNwIJQlvLcgrxqYhK', 'user2@gmail.com'),
    ('user3', '$2a$12$aIbEWSfC5VhUJAZv2eHUBeGizkwJ2ZtejXzARdxkAVQ4JxVzq.WRS', 'user3@gmail.com');

insert into users_roles (user_id, role_id)
    values
        (1, 1),
        (2, 2),
        (3, 3);