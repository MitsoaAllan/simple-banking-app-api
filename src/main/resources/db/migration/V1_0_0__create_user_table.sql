create table if not exists "user"(
    id bigserial primary key,
    full_name varchar not null,
    email varchar not null unique,
    birthdate date not null,
    creation_instant timestamp with time zone not null default now()
);
create index user_email_index on "user"(email);