CREATE TABLE json_tbl
(
    id      uuid    NOT NULL UNIQUE,
    name    varchar NOT NULL,
    json1   json,
    json2   json,
    version bigint  NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE jsonb_tbl
(
    id      uuid    NOT NULL UNIQUE,
    name    varchar NOT NULL,
    json1   jsonb,
    json2   jsonb,
    version bigint  NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
);
