-- create roles table
create table roles (
    id integer not null,
    name varchar (100) not null,
    primary key (id)
);

create sequence role_sequence as integer increment 1;

-- create user_roles table
create table user_roles (
    id integer not null,
    active boolean not null,
    created_at timestamp not null,
    user_id bigint,
    rol_id integer,
    primary key (id)
);
alter table user_roles add constraint fk_user_roles_Ref_Users foreign key (user_id)
    references users (id) on delete restrict on update restrict;
alter table user_roles add constraint fk_user_roles_Ref_Roles foreign key (rol_id)
    references roles (id) on delete restrict on update restrict;

create sequence user_role_sequence as integer increment 1;