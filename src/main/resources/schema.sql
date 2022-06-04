DROP TABLE IF EXISTS shortened_url;

CREATE TABLE short_url
(
    token varchar(10) PRIMARY KEY,
    original_url varchar NOT NULL,
    created_at timestamp NOT NULL,
    expired_at timestamp,
    redirects bigint NOT NULL
);