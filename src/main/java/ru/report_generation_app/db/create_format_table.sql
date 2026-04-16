create table formats
(
    id      serial primary key,
    name    varchar(55) not null unique,
    pattern varchar(55) not null unique
);
create index format_pattern_index on formats (name);
