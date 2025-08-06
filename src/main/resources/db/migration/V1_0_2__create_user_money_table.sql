create type type_transaction as enum('IN','OUT');

create table if not exists user_money(
    user_id int references "user"(id) on delete cascade not null,
    money_id int references money(id) on delete cascade not null,
    type_transaction type_transaction not null,
    creation_instant timestamp with time zone not null default now(),
    constraint user_money_id primary key (user_id,money_id)
);

create index user_money_type on user_money(type_transaction);