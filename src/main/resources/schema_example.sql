CREATE SCHEMA IF NOT EXISTS library;

create table IF NOT EXISTS library.authors (
    id serial Primary Key,
    full_name varchar(240) not null,
    description varchar(2000)
);

create table IF NOT EXISTS library.book_genres (
    id serial primary key,
    genre varchar(240) unique not null
);

create table IF NOT EXISTS library.books (
    id serial primary key,
    title varchar(240) not null,
    genre_id integer not null references library.book_genres(id),
    description varchar(2000)
);

create table IF NOT EXISTS library.books_authors (
    id serial primary key,
    book_id integer not null references library.books(id),
    author_id integer not null references library.authors(id)
);

create table if not exists library.posts (
    id serial primary key,
    book_id integer not null references library.books(id),
    description varchar(2000)
);

insert into library.book_genres (genre) values
    ('romance'),
    ('history'),
    ('horror'),
    ('children''s books')
ON CONFLICT DO NOTHING;
