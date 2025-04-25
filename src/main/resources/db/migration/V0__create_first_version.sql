create table users(
    id bigserial primary key,
    login varchar(40) unique not null,
    password varchar(60) not null
);

create table locations(
    id bigserial primary key,
    name varchar not null,
    user_id int references users(id),
    latitude Decimal not null,
    longitude Decimal not null
);

create table sessions(
    id uuid primary key,
    user_id int references users(id),
    expires_at timestamptz not null
)