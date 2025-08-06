create table if not exists money(
    id bigserial primary key,
    amount decimal not null,
    creation_instant timestamp with time zone not null default now()
);