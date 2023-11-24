-- create user_details table
create table user_details (
     id bigint not null,
     first_name varchar (100) not null,
     last_name varchar (100) not null,
     age int,
     birth_day date,
     user_id bigint,
     primary key (id)
);
alter table user_details add constraint fk_user_details_Ref_Users foreign key (user_id)
    references users (id) on delete restrict on update restrict;

create sequence user_detail_sequence as integer increment 1;