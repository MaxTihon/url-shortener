DROP TABLE IF EXISTS shortened_url;

CREATE TABLE IF NOT EXISTS shortened_url
(
    id SERIAL PRIMARY KEY,
    token varchar(20) not null unique,
    original_url varchar,
    created_at timestamp,
    redirects bigint
);