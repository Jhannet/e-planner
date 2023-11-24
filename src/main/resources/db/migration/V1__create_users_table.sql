-- create users table
create table users (
     id bigint not null,
     username varchar(150) not null,
     password varchar(150) not null,
     email varchar(150) not null,
     created_at TIMESTAMP,
     primary key (id)
);

-- create a sequence for users id
create sequence user_sequence as integer increment 1;